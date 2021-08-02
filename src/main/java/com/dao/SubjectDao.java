package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.SubjectBean;


/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author Manoj kumar
 *
 */
public class SubjectDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/learnacad";
	private String jdbcUsername = "root";
	private String jdbcPassword = "@Mkumar7200";

	private static final String INSERT_USERS_SQL = "INSERT INTO subject" + "  (sub) VALUES "
			+ " (?);";

	private static final String SELECT_USER_BY_ID = "select subid,sub from subject where subid=?";
	private static final String SELECT_ALL_USERS = "select * from subject";
	private static final String DELETE_USERS_SQL = "delete from subject where subid = ?;";
	private static final String UPDATE_USERS_SQL = "update subject set sub = ? where subid=?;";

	public SubjectDao() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
			System.out.println("connected");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public void insertUser(SubjectBean subjectbean) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, subjectbean.getSubname());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public SubjectBean selectUser(int subid) {
		SubjectBean subjectbean = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, subid);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String subname = rs.getString("sub");
				subjectbean = new SubjectBean(subid, subname);

			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return subjectbean;
	}

	public List<SubjectBean> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<SubjectBean> subjects = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int subid = rs.getInt("subid");
				String subname = rs.getString("sub");
				subjects.add(new SubjectBean(subid, subname));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return subjects;
	}

	public boolean deleteUser(int subid) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, subid);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateUser(SubjectBean subjectbean) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			System.out.println("updated USer:"+statement);
			statement.setString(1, subjectbean.getSubname());
			statement.setInt(2, subjectbean.getSubid()); 
			System.out.println("updated USer:"+statement);
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}

}
