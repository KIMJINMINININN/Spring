package com.spring.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spring.ex01.MemberVO;

//@WebServlet("/mem3.do")
public class MemberServlet extends HttpServlet{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	public void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		MemberDAO dao = new MemberDAO();
		MemberVO memberVO = new MemberVO();
		String action = request.getParameter("action");
		String nextPage = "";
		if(action == null || action.equals("listMembers")){
			List memberList = dao.selectAllMemberList();
			request.setAttribute("membersList", memberList);
			nextPage = "test02/listMembers.jsp";
		}else if(action.equals("selectMemberById")){
			//검색 조건이 selectMemberById 일때 dao의 메소드를 이용해서 id를 가져온다.
			String id = request.getParameter("value");
			memberVO = dao.selectMemberById(id);
			request.setAttribute("member", memberVO);
			nextPage = "test02/memberInfo.jsp";
		}else if(action.equals("selectMemberByPwd")){
			//검색조건이 selectMemberByPwd 일때 dao의 메소드를 이용해서 pwd를 가져온다.
			int pwd = Integer.parseInt(request.getParameter("value"));
			List<MemberVO> membersList = dao.selectMemberByPwd(pwd);
			request.setAttribute("membersList", membersList);
			nextPage = "test02/listMembers.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher("test01/listMembers.jsp");
		dispatch.forward(request, response);
	}
}
