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

import com.bean.AssignBean;
import com.bean.ClassBean;
import com.bean.SubjectBean;
import com.bean.TeacherBean;
import com.dao.AssignDao;
import com.dao.ClassDao;
import com.dao.SubjectDao;
import com.dao.TeacherDao;




/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 * @email Manoj kumar
 */

@WebServlet(urlPatterns={"/ainsert","/adelete","/Assignedlist"})
public class AssignServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SubjectDao subjectdao;
	private ClassDao classdao;
	private AssignDao assigndao;
	private TeacherDao teacherDao;
	
	public void init() {
		subjectdao = new SubjectDao();
		classdao =new ClassDao();
		assigndao =new AssignDao();
		teacherDao = new TeacherDao();
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
			case "/ainsert":
				insertUser(request, response);
				break;
			case "/adelete":
				deleteUser(request, response);
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
		List<ClassBean> ClistUser = classdao.selectAllUsers();
		request.setAttribute("ClistUser", ClistUser);
		
		List<SubjectBean> SlistUser = subjectdao.selectAllUsers();
		request.setAttribute("SlistUser", SlistUser);
		
		List<TeacherBean> TlistUser = teacherDao.selectAllUsers();
		request.setAttribute("TlistUser", TlistUser);
		
		List<AssignBean> AlistUser = assigndao.selectAllUsers();
		request.setAttribute("AlistUser", AlistUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Assign.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		String cname = request.getParameter("cname");
		String subname = request.getParameter("subname");
		String tname = request.getParameter("tname");

		AssignBean newAssign = new AssignBean(cname,subname,tname);
		assigndao.insertUser(newAssign);
		listUser(request, response);
	}


	private void deleteUser(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		assigndao.deleteUser(id);
		listUser(request, response);
	}

}