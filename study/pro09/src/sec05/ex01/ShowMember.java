package sec05.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/show")
public class ShowMember extends HttpServlet{
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out= response.getWriter();
		String id="", pwd="";
		Boolean isLogon = false;
		//세션이 존재하면 session 반환, 존재하지 않으면 새로 생성하지않고 null 반환
		HttpSession session = request.getSession(false);
		if( session != null ){//session이 비어있지 않을때
			isLogon = (Boolean)session.getAttribute("isLogon");
			if(isLogon==true){
				id = (String)session.getAttribute("login.id");
				pwd = (String)session.getAttribute("lgoin.pwd");
				out.print("<html><body>");
				out.print("아이디 : " + id + "<br>");
				out.print("비밀번호 : " + pwd + "<br>");
				out.print("</body></html>");
			}else{
				response.sendRedirect("login3.html");
			}
		}else{ //session이 생성되지 않았을때
			response.sendRedirect("login3.html");
		}
	}
}
