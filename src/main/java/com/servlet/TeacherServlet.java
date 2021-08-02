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

import com.bean.TeacherBean;
import com.dao.TeacherDao;




/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @email Manoj kumar
 */

@WebServlet(urlPatterns={"/tnew","/tinsert","/tdelete","/tedit","/tupdate","/teacherlist"})
public class TeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TeacherDao teacherDao;
	
	public void init() {
		teacherDao = new TeacherDao();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		System.out.println(action);

		try {
			switch (action) {
			case "/tnew":
				showNewForm(request, response);
				break;
			case "/tinsert":
				insertUser(request, response);
				break;
			case "/tdelete":
				deleteUser(request, response);
				break;
			case "/tedit":
				showEditForm(request, response);
				break;
			case "/tupdate":
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
		List<TeacherBean> listUser = teacherDao.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("TeacherList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("TeacherForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int tid = Integer.parseInt(request.getParameter("tid"));
		TeacherBean existingTeacher = teacherDao.selectUser(tid);
		RequestDispatcher dispatcher = request.getRequestDispatcher("TeacherForm.jsp");
		request.setAttribute("teacherbean", existingTeacher);
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String dob = request.getParameter("dob");
		String address=request.getParameter("address");
		String edueq=request.getParameter("eq");

		TeacherBean newTeacher = new TeacherBean(fname, lname, dob, address,edueq);
		teacherDao.insertUser(newTeacher);
		response.sendRedirect("teacherlist");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int tid = Integer.parseInt(request.getParameter("tid"));
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String dob = request.getParameter("dob");
		String address = request.getParameter("address");
		String edueq=request.getParameter("eq");
		TeacherBean newTeacher = new TeacherBean(tid, fname, lname, dob, address,edueq);
		teacherDao.updateUser(newTeacher);
		response.sendRedirect("teacherlist");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int tid = Integer.parseInt(request.getParameter("tid"));
		teacherDao.deleteUser(tid);
		response.sendRedirect("teacherlist");

	}

}