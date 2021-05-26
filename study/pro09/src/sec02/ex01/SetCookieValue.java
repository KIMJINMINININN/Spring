package sec02.ex01;

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

/**
 * Servlet implementation class LoginServlet
 */
//@WebServlet("/set")
public class SetCookieValue extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 메서드 호출");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Date d = new Date();
		//Cookie 객체를 생성한후 한글 정보를 인코딩해서 쿠키에 저장
		Cookie c = new Cookie("cookieTest", URLEncoder.encode("what프로그래밍입니다.", "utf-8"));
		//유효 기간을 설정
		c.setMaxAge(24*60*60);
		//생성된 쿠키 브라우저도 전송
		response.addCookie(c);
		out.println("현재시간 :"+d);
		out.println("현재시간을 Cookie로 저장합니다.");
	}
	
	public void destroy(){
	      System.out.println("destroy 메서드 호출");
	   }


}