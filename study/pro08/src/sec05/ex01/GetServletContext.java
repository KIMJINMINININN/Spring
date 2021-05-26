package sec05.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cget")
public class GetServletContext extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// ServletContext 객체 가져오기
		ServletContext context = getServletContext();
		// member 로 이전에 바인딩된 회원정보 가져오기 
		List member = (ArrayList)context.getAttribute("member");
		String name = (String)member.get(0);
		int age = (Integer)member.get(1);
		out.print("<html><body>");
		out.print(name + "<br>");
		out.print(age + "<br>");
		out.print("</body></html>");
	}
}
