package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.bean.LoginBean;
import com.bean.StudentBean;

public class LoginDao {

//	public LoginDao() {
//		super();
//
//		String jdbcURL = getServletContext().getInitParameter("jdbcURL");
//	    String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
//	    String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");
//	 
//	}

	String url = "jdbc:mysql://localhost:3306/school";
	String username = "root";
	String password = "@Mkumar7200";
	private Connection con;

	protected void connect() throws SQLException {
		if (con == null || con.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			con = DriverManager.getConnection(url, username, password);
		}
	}

	protected void disconnect() throws SQLException {
		if (con != null && !con.isClosed()) {
			con.close();
		}
	}

	public boolean validate(LoginBean loginbean) {
		String sql = "select * from admin where username=? and Password=?";
		try {
			connect();
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, loginbean.getUsername());
			st.setString(2, loginbean.getPassword());
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
				return true;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;

	}
}
	