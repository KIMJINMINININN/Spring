package sec01.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private static final String user = "wemb1";
	private static final String pwd = "wemb1";
	private Connection con;
	private Statement stmt;
	 
	public List<MemberVO> listMembers(){
		List<MemberVO> list = new ArrayList<MemberVO>();
		try{
			connDB();
			String query = "select * from t_member";
			System.out.println(query);
			//statement를 가지고 query를 실행
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				String id = rs.getString("id");
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joinDate = rs.getDate("joinDate");
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joinDate);
				list.add(vo);
			}
			rs.close();
			stmt.close();
			con.close();
		}catch (Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	private void connDB(){
		try{
			Class.forName(driver);
			System.out.println("Oracle 드라이버 로딩 성공");
			// Driver Manager 객체 생성
			con = DriverManager.getConnection(url,user, pwd);
			System.out.println("Connection 생성 성공");
			// DriverManager로 statement를 생성
			stmt = con.createStatement();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
