package sec04.ex01;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class LoginImpl implements HttpSessionBindingListener {
	String user_id;
	String user_pw;
	static int total_user=0;
	public LoginImpl(){
		
	}
	
	public LoginImpl(String user_id, String user_pw){
		this.user_id = user_id;
		this.user_pw = user_pw;
	}
	
	@Override
	public void valueBound(HttpSessionBindingEvent arg0){
		System.out.println("사용자 접속");
		++total_user;
	}
	
	@Override
	public void valueUnbound(HttpSessionBindingEvent arg0){
		System.out.println("사용자 접속 해제");
//		total_user--;
	}
}
