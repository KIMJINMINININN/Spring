package sec01.ex02;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	private PreparedStatement pstmt;
	 
	public List<MemberVO> listMembers(){
		List<MemberVO> list = new ArrayList<MemberVO>();
		try{
			connDB();
			String query = "select * from t_member";
			System.out.println("prepareStatement : " + query );
			//sql���� �����Ͽ� ��ü ����
			pstmt = con.prepareStatement(query);
			//���� sql�� ���� 
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
	
	private void connDB(){
		try{
			Class.forName(driver);
			System.out.println("Oracle ����̹� �ε� ����");
			// Driver Manager ��ü ����
			con = DriverManager.getConnection(url,user, pwd);
			System.out.println("Connection ���� ����");
			// DriverManager�� statement�� ����
//			stmt = con.createStatement();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
