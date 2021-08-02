package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.ClassBean;


/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author Manoj kumar
 *
 */
public class ClassDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/learnacad";
	private String jdbcUsername = "root";
	private String jdbcPassword = "@Mkumar7200";

	private static final String INSERT_USERS_SQL = "INSERT INTO class" + "  (cname) VALUES "
			+ " (?);";

	private static final String SELECT_USER_BY_ID = "select cid,cname from class where cid=?";
	private static final String SELECT_ALL_USERS = "select * from class";
	private static final String DELETE_USERS_SQL = "delete from class where cid = ?;";
	private static final String UPDATE_USERS_SQL = "update class set cname = ? where cid=?;";

	public ClassDao() {
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

	public void insertUser(ClassBean classbean) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, classbean.getCname());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public ClassBean selectUser(int cid) {
		ClassBean classbean = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, cid);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String cname = rs.getString("cname");
				classbean = new ClassBean(cid, cname);

			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return classbean;
	}

	public List<ClassBean> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<ClassBean> classes = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int cid = rs.getInt("cid");
				String cname = rs.getString("cname");
				classes.add(new ClassBean(cid, cname));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return classes;
	}

	public boolean deleteUser(int cid) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, cid);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean updateUser(ClassBean classbean) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			System.out.println("updated USer:"+statement);
			statement.setString(1, classbean.getCname());
			statement.setInt(2, classbean.getCid()); 
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
