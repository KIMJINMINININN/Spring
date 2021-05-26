package sec03.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/sess")
public class SessionTest extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 메서드 호출");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		out.println("세션 아이디 : " + session.getId() + "<br>");
		out.println("최초 세션 생성 시각  : " + new Date(session.getCreationTime()) + "<br>");
		out.println("최초 세션 접근 시각  : " + new Date(session.getLastAccessedTime()) + "<br>");
		out.println("세션 유효 시간  : " + session.getMaxInactiveInterval() + "<br>");
		if(session.isNew()){
			out.print("새 새션이 만들어졌습니다.");
		}
	}
	
	public void destroy(){
	      System.out.println("destroy 메서드 호출");
	   }


}