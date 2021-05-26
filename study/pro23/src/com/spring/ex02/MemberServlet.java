package com.spring.ex02;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/mem2.do")
public class MemberServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	public void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		MemberDAO dao = new MemberDAO();
		List memberList = dao.selectAllMemberList();
		request.setAttribute("membersList", memberList);
		RequestDispatcher dispatch = request.getRequestDispatcher("test01/listMembers.jsp");
		dispatch.forward(request, response);
//		String name = dao.selectName();
//		PrintWriter pw = response.getWriter();
//		pw.write("<script>");
//		pw.write("alert(' ¿Ã∏ß : " + name + "');");
//		pw.write("</script>");
		
	}
}
