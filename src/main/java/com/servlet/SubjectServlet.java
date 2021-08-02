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

import com.bean.SubjectBean;
import com.dao.SubjectDao;




/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @email Manoj kumar
 */

@WebServlet(urlPatterns={"/subnew","/subinsert","/subdelete","/subedit","/subupdate","/subjectlist"})
public class SubjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SubjectDao subjectdao;
	
	public void init() {
		subjectdao = new SubjectDao();
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
			case "/subnew":
				showNewForm(request, response);
				break;
			case "/subinsert":
				insertUser(request, response);
				break;
			case "/subdelete":
				deleteUser(request, response);
				break;
			case "/subedit":
				showEditForm(request, response);
				break;
			case "/subupdate":
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
		List<SubjectBean> listUser = subjectdao.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("SubjectList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("SubjectForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int subid = Integer.parseInt(request.getParameter("subid"));
		SubjectBean existingSubject = subjectdao.selectUser(subid);
		RequestDispatcher dispatcher = request.getRequestDispatcher("SubjectForm.jsp");
		request.setAttribute("subjectbean", existingSubject);
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String subname = request.getParameter("subname");
		SubjectBean newSubject = new SubjectBean(subname);
		subjectdao.insertUser(newSubject);
		response.sendRedirect("subjectlist");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int subid = Integer.parseInt(request.getParameter("subid"));
		String subname = request.getParameter("subname");
		SubjectBean newSubject = new SubjectBean(subid, subname);
		subjectdao.updateUser(newSubject);
		response.sendRedirect("subjectlist");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int subid = Integer.parseInt(request.getParameter("subid"));
		subjectdao.deleteUser(subid);
		response.sendRedirect("subjectlist");

	}

}