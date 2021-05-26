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
		System.out.println("init �޼��� ȣ��");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Date d = new Date();
		//Cookie ��ü�� �������� �ѱ� ������ ���ڵ��ؼ� ��Ű�� ����
		Cookie c = new Cookie("cookieTest", URLEncoder.encode("what���α׷����Դϴ�.", "utf-8"));
		//��ȿ �Ⱓ�� ����
		c.setMaxAge(24*60*60);
		//������ ��Ű �������� ����
		response.addCookie(c);
		out.println("����ð� :"+d);
		out.println("����ð��� Cookie�� �����մϴ�.");
	}
	
	public void destroy(){
	      System.out.println("destroy �޼��� ȣ��");
	   }


}