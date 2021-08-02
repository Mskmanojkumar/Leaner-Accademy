package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bean.LoginBean;
import com.dao.LoginDao;

/**
 * Servlet implementation class Login
 */

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 
		String uname=request.getParameter("uname");
		String pass=request.getParameter("pass");
		LoginBean loginbean=new LoginBean();
		LoginDao dao=new LoginDao();
		loginbean.setUsername(uname);
		loginbean.setPassword(pass);
		
		try {
			if(dao.validate(loginbean)) {
				
				HttpSession session= request.getSession();
				session.setAttribute("username", uname);
				response.sendRedirect("studentlist");
			}
			else {
				
				response.sendRedirect("LoginPage.jsp");
				
			}
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
