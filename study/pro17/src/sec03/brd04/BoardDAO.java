package sec03.brd04;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.catalina.util.URLEncoder;

import sec02.ex01.MemberVO;

public class BoardDAO {
	private DataSource dataFactory;
	private Connection conn;
	private PreparedStatement pstmt;

	public BoardDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//list 가져오기 (Read)
	public List selectAllArticles() {
		List articlesList = new ArrayList();
		try {
			conn = dataFactory.getConnection();
			String query = "select level, articleNO, parentNO, title, content, id, writeDate"
					+ " from t_board"
					+ " START with parentNO=0 connect by prior articleno=parentno" 
					+ " order siblings by articleno desc";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int level = rs.getInt("level");
				int articleNO = rs.getInt("articleNO");
				int parentNO = rs.getInt("parentNO");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String id = rs.getString("id");
				Date writeDate = rs.getDate("writeDate");
				ArticleVO article = new ArticleVO();
				article.setLevel(level);
				article.setArticleNO(articleNO);
				article.setParentNO(parentNO);
				article.setTitle(title);
				article.setContent(content);
				article.setId(id);
				article.setWriteDate(writeDate);
				articlesList.add(article);
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return articlesList;
	}
	//number 받아오기(최고번호 +1)
	private int getNewArticleNO(){
		try{
			conn = dataFactory.getConnection();
			String query = "SELECT max(articleNO) from t_board";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery(query);
			if(rs.next())
				return (rs.getInt(1) + 1);
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	public int insertNewArticle(ArticleVO article){
		int articleNO = getNewArticleNO();
		try{
			conn = dataFactory.getConnection();
			int parentNO = article.getParentNO();
			String title = article.getTitle();
			String content = article.getContent();
			String id = article.getId();
			String imageFileName = article.getImageFileName();
			String query = "INSERT INTO t_board (articleNO, parentNO, title, content, imageFileName, id)"
					+ "VALUES(?, ?, ?, ?, ?, ?)";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1,  articleNO);
			pstmt.setInt(2,  parentNO);
			pstmt.setString(3,  title);
			pstmt.setString(4,  content);
			pstmt.setString(5,  imageFileName);
			pstmt.setString(6,  id);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		return articleNO;
	}
	
	public ArticleVO selectArticle(int articleNO){
		ArticleVO article=new ArticleVO();
		try{
			conn = dataFactory.getConnection();
			String query ="select articleNO,parentNO,title,content,  NVL(imageFileName, 'null') as imageFileName, id, writeDate"
				                     +" from t_board" 
				                     +" where articleNO=?";
			System.out.println(query);
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, articleNO);
			ResultSet rs =pstmt.executeQuery();
			rs.next();
			int _articleNO =rs.getInt("articleNO");
			int parentNO=rs.getInt("parentNO");
			String title = rs.getString("title");
			String content =rs.getString("content");
			String imageFileName = rs.getString("imageFileName");//파일이름에 특수문자가 있을 경우 인코딩합니다.
			if(imageFileName.equals("null")) {
				imageFileName = null;
			}
			String id = rs.getString("id");
			Date writeDate = rs.getDate("writeDate");
	
			article.setArticleNO(_articleNO);
			article.setParentNO (parentNO);
			article.setTitle(title);
			article.setContent(content);
			article.setImageFileName(imageFileName);
			article.setId(id);
			article.setWriteDate(writeDate);
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();	
		}
		return article;
		}
}