package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.StudentBean;


/**
 * AbstractDAO.java This DAO class provides CRUD database operations for the
 * table users in the database.
 * 
 * @author Manoj kumar
 *
 */
public class StudentDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/learnacad";
	private String jdbcUsername = "root";
	private String jdbcPassword = "@Mkumar7200";

	private static final String INSERT_USERS_SQL = "INSERT INTO student" + "  (fname, lname, dob, address,cname) VALUES "
			+ " (?, ?, ?, ? , ?);";

	private static final String SELECT_USER_BY_ID = "select sid,fname,lname,dob,address,cname from student where sid =?";
	private static final String SELECT_ALL_USERS = "select * from student";
	private static final String DELETE_USERS_SQL = "delete from student where sid = ?;";
	private static final String UPDATE_USERS_SQL = "update student set fname = ?,lname= ?, dob =? , address=?,cname=? where sid = ?;";

	public StudentDao() {
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

	public void insertUser(StudentBean studentbean) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, studentbean.getFname());
			preparedStatement.setString(2, studentbean.getLname());
			preparedStatement.setString(3, studentbean.getDob());
			preparedStatement.setString(4, studentbean.getAddress());
			preparedStatement.setString(5, studentbean.getCname());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

	public StudentBean selectUser(int id) {
		StudentBean studentbean = null;
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				String dod = rs.getString("dob");
				String address=rs.getString("address");
				String cname = rs.getString("cname");
				studentbean = new StudentBean(id, fname, lname, dod, address,cname);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return studentbean;
	}

	public List<StudentBean> selectAllUsers() {

		// using try-with-resources to avoid closing resources (boiler plate code)
		List<StudentBean> students = new ArrayList<>();
		// Step 1: Establishing a Connection
		try (Connection connection = getConnection();

				// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("sid");
				String fname = rs.getString("fname");
				String lname = rs.getString("lname");
				String dod = rs.getString("dob");
				String address=rs.getString("address");
				String cname =rs.getString("cname");
				students.add(new StudentBean(id, fname, lname, dod,address,cname));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return students;
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

	public boolean updateUser(StudentBean studentbean) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			System.out.println("updated USer:"+statement);
			statement.setString(1, studentbean.getFname());
			statement.setString(2, studentbean.getLname());
			statement.setString(3, studentbean.getDob());
			statement.setString(4, studentbean.getAddress());
			statement.setString(5, studentbean.getCname());
			statement.setInt(6, studentbean.getId());
			
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
