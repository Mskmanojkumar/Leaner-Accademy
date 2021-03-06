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
import com.dao.ClassDao;




/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @email Manoj kumar
 */

@WebServlet(urlPatterns={"/cnew","/cinsert","/cdelete","/cedit","/cupdate","/classlist"})
public class ClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClassDao classdao;
	
	public void init() {
		classdao = new ClassDao();
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
			case "/cnew":
				showNewForm(request, response);
				break;
			case "/cinsert":
				insertUser(request, response);
				break;
			case "/cdelete":
				deleteUser(request, response);
				break;
			case "/cedit":
				showEditForm(request, response);
				break;
			case "/cupdate":
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
		List<ClassBean> listUser = classdao.selectAllUsers();
		request.setAttribute("listUser", listUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ClassList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("ClassForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int cid = Integer.parseInt(request.getParameter("cid"));
		ClassBean existingClass = classdao.selectUser(cid);
		RequestDispatcher dispatcher = request.getRequestDispatcher("ClassForm.jsp");
		request.setAttribute("classbean", existingClass);
		dispatcher.forward(request, response);

	}

	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		String cname = request.getParameter("cname");
		ClassBean newClass = new ClassBean(cname);
		classdao.insertUser(newClass);
		response.sendRedirect("classlist");
	}

	private void updateUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int cid = Integer.parseInt(request.getParameter("cid"));
		String cname = request.getParameter("cname");
		ClassBean newClass = new ClassBean(cid, cname);
		classdao.updateUser(newClass);
		response.sendRedirect("classlist");
	}

	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int cid = Integer.parseInt(request.getParameter("cid"));
		classdao.deleteUser(cid);
		response.sendRedirect("classlist");

	}

}