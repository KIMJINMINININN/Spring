package sec02.ex01;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberController
 */
//@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	MemberDAO memberDAO;
	
	public void init(ServletConfig config) throws ServletException {
		memberDAO = new MemberDAO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}


	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;chartset=utf-8");
		String action = request.getPathInfo();
		System.out.println("action :" + action);
		if(action == null || action.equals("/listMemebers.do")){// 최초이거나, memberList일때
			List membersList = memberDAO.listMembers();
			request.setAttribute("memberList", membersList);
			nextPage = "/test02/listMembers.jsp";
		}else if(action.equals("/addMember.do")){ //action이 addMember일때
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.addMember(memberVO);
			nextPage = "/member/listMembers.do";
		}else if(action.equals("/memberForm.do")){ //memberForm 일때
			nextPage = "/test02/memberForm.jsp";
		}else{ // 회원 목록 출력
			List membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test02/listMembers.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}
