package sec03.ex04;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionText4
 */
//@WebServlet("/login")
public class SessionText4 extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException  {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//로그인 할때마다 session은 새로 만들어지는데 
		HttpSession session = request.getSession();
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		if(session.isNew()){
			if(user_id != null){
				session.setAttribute("user_id", user_id);
				out.println("<a href='login'>로그인 상태 확인 </a>");
			}else{
				out.print("<a href='login2.html'>다시 로그인 하세요!!</a>");
				session.invalidate();
			}
		}else{
			user_id = (String) session.getAttribute("user_id");
			if(user_id != null && user_id.length() != 0){
				out.print("안녕하세요 " + user_id + "님 !!!!");
			}else{
				out.print("<a href='login2.html'>다시 로그인 하세요!!</a>");
				session.invalidate();
			}
		}
	}
}
