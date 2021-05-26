package sec02.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
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
//@WebServlet("/get")
public class GetCookiedValue extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init 메서드 호출");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//request의 getCookeis 메서드 호출해서 쿠키정보 요청한후에 쿠키정보를 배열로 가져온다
		Cookie[] allValues = request.getCookies();
		for(int i=0; i<allValues.length;i++){
			if(allValues[i].getName().equals("cookieTest")){
				out.println("<h2>Cookie 값 가져오기 : " + URLDecoder.decode(allValues[i].getValue(), "utf-8"));
			}
		}
	}
	
	public void destroy(){
	      System.out.println("destroy 메서드 호출");
	   }


}