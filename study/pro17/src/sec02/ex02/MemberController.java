package sec02.ex02;

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

import sec02.ex01.MemberVO;

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
		}else if(action.equals("/modMemberForm.do")){ //id를 수정페이지로 이동
			String id = request.getParameter("id");
			MemberVO memInfo = memberDAO.findMember(id);
			request.setAttribute("memInfo", memInfo);
			nextPage = "/test03/modMemberForm.jsp";
		}else if(action.equals("/modMember.do")){// 테이블의 회원정보 수정
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
//			System.out.println("id: "+ id + "pwd: " + pwd + "name: " + name + "email: " + email);
			MemberVO memberVO = new MemberVO(id, pwd, name, email);
			memberDAO.modMember(memberVO);
			request.setAttribute("msg", "modified");
			nextPage = "/member/listMembers.do";
		}else if(action.equals("/delMember.do")){// 회원 ID 삭제하기
			String id = request.getParameter("id");
			memberDAO.delMember(id);
			request.setAttribute("msg", "deleted");
			nextPage = "/member/listmembers.do";
		}else{ // 회원 목록 출력
			List membersList = memberDAO.listMembers();
			request.setAttribute("membersList", membersList);
			nextPage = "/test03/listMembers.jsp";
		}
		RequestDispatcher dispatch = request.getRequestDispatcher(nextPage);
		dispatch.forward(request, response);
	}
}
