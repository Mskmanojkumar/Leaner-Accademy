package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.ClassBean;
import com.bean.StudentBean;
import com.dao.ClassDao;
import com.dao.StudentDao;




/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @email Manoj kumar
 */

@WebServlet(urlPatterns={"/snew","/sinsert","/sdelete","/sedit","/supdate","/studentlist"})
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDao studentDAO;
	private ClassDao classdao;
	
	public void init() {
		studentDAO = new StudentDao();
		classdao =new ClassDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();

		try {
			switch (action) {
			case "/snew":
				showNewForm(request, response);
				break;
			case "/sinsert":
				insertUser(request, response);
				break;
			case "/sdelete":
				deleteUser(request, response);
				break;
			case "/sedit":
				showEditForm(request, response);
				break;
			case "/supdate":
				updateUser(request, response);
				break;
			default:
				listUser(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void listUser(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<StudentBean> listUser = studentDAO.selectAllUsers();
		request.setAttribute("listUser", listUser);
		List<ClassBean> ClistUser = classdao.selectAllUsers();
		request.setAttribute("ClistUser", ClistUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("StudentList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<ClassBean> ClistUser = classdao.selectAllUsers();
		request.setAttribute("ClistUser", ClistUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("StudentFrom.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		StudentBean existingStudent = studentDAO.selectUser(id);
		List<ClassBean> ClistUser = classdao.selectAllUsers();
		request.setAttribute("ClistUser", ClistUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("StudentFrom.jsp");
		request.setAttribute("studentbean", existingStudent);
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String dob = request.getParameter("dob");
		String address=request.getParameter("address");
		String cname=request.getParameter("cname");
		StudentBean newStudent = new StudentBean(fname, lname, dob, address,cname);
		studentDAO.insertUser(newStudent);
		response.sendRedirect("studentlist");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String address = request.getParameter("address");
		String dob = request.getParameter("dob");
		String cname=request.getParameter("cname");
		StudentBean newStudent = new StudentBean(id, fname, lname, dob, address,cname);
		studentDAO.updateUser(newStudent);
		response.sendRedirect("studentlist");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		studentDAO.deleteUser(id);
		response.sendRedirect("studentlist");

	}

}