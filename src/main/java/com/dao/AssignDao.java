package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.AssignBean;




/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author Manoj kumar
 *
 */
public class AssignDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/learnacad";
	private String jdbcUsername = "root";
	private String jdbcPassword = "@Mkumar7200";

	private static final String INSERT_USERS_SQL = "INSERT INTO assign" + "  (cname, subname, tname) VALUES "
			+ " (?, ?, ?);";

	private static final String DELETE_USERS_SQL = "delete from assign where id = ?;";
	private static final String SELECT_ALL_USERS = "select * from assign";
	public AssignDao() {
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

	public void insertUser(AssignBean assignbean) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, assignbean.getCname());
			preparedStatement.setString(2, assignbean.getSubname());
			preparedStatement.setString(3, assignbean.getTname());

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public List<AssignBean> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<AssignBean> assigned = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("id");
				String cname = rs.getString("cname");
				String subname = rs.getString("subname");
				String tname=rs.getString("tname");
				assigned.add(new AssignBean(id, cname, subname,tname));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return assigned;
	}

	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
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
