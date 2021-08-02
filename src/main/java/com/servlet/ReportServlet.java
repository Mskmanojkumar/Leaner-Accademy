package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.AssignBean;
import com.bean.ClassBean;
import com.bean.StudentBean;
import com.bean.SubjectBean;
import com.bean.TeacherBean;
import com.dao.AssignDao;
import com.dao.ClassDao;
import com.dao.StudentDao;

/**
 * Servlet implementation class ReportServlet
 */
@WebServlet("/report")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ClassDao classdao = new ClassDao();
		AssignDao assigndao = new AssignDao();
		StudentDao studentDao = new StudentDao();
		List<ClassBean> ClistUser = classdao.selectAllUsers();
		request.setAttribute("ClistUser", ClistUser);
		
		List<StudentBean> students = studentDao.selectAllUsers();
		request.setAttribute("listStudent", students);
		List<AssignBean> AlistUser = assigndao.selectAllUsers();
		request.setAttribute("AlistUser", AlistUser);
		RequestDispatcher dispatcher = request.getRequestDispatcher("report.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
