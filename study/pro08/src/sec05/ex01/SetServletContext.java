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

@WebServlet("/cset")
public class SetServletContext extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		//ServletContext ��ü�� ��������
		ServletContext context = getServletContext();
		List member = new ArrayList();
		member.add("�̼���");
		member.add(30);
		// SerlvetContext ��ü�� �����͸� ���ε�
		context.setAttribute("member", member);
		out.print("<html><body>");
		out.print("�̼��Ű� 30 ����");
		out.print("</body></html>");
	}
}