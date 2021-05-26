package sec02.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
//	private static final String driver = "oracle.jdbc.driver.OracleDriver";
//	private static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
//	private static final String user = "wemb1";
//	private static final String pwd = "wemb1";
	private Connection con;
	private PreparedStatement pstmt;
	private DataSource dataFactory;
	
	public MemberDAO(){
		try{
			//JNDI에 접근하기 위한 지정
			Context ctx = new InitialContext();
			Context envContext = (Context)ctx.lookup("java:/comp/env");
			// 톰켓에서 DataSource를 가져온다
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<MemberVO> listMembers(){
		List<MemberVO> list = new ArrayList<MemberVO>();
		try{
//			connDB();
			con = dataFactory.getConnection();
			String query = "select * from t_member";
			System.out.println("prepareStatement : " + query );
			//sql문을 전달하여 객체 생성
			pstmt = con.prepareStatement(query);
			//설정 sql을 실행 
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()){
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(pwd);
				vo.setEmail(pwd);
				vo.setJoinDate(joinDate);
				list.add(vo);
			}
			rs.close();
			pstmt.close();
			con.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
//	private void connDB(){
//		try{
//			Class.forName(driver);
//			System.out.println("Oracle 드라이버 로딩 성공");
//			// Driver Manager 객체 생성
//			con = DriverManager.getConnection(url,user, pwd);
//			System.out.println("Connection 생성 성공");
//			// DriverManager로 statement를 생성
////			stmt = con.createStatement();
//		} catch(Exception e){
//			e.printStackTrace();
//		}
//	}
}
