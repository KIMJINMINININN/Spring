package com.spring.ex04;

import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.spring.ex01.MemberVO;

public class MemberDAO {
	private static SqlSessionFactory sqlMapper=null;
	public static SqlSessionFactory getInstance(){
		if(sqlMapper==null){
			try{
				String resource = "mybatis/SqlMapConfig.xml";
				Reader reader = Resources.getResourceAsReader(resource);
				//myBatis를 이용하는 sqlMapper 객체 가져오기
				sqlMapper = new SqlSessionFactoryBuilder().build(reader);
				reader.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return sqlMapper;
	}
	
	public String selectName(){
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		String name = (String) session.selectOne("mapper.member.selectName");
		return name;
	}
	
	public int selectPwd(){
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int pwd = (int) session.selectOne("mapper.member.selectPwd");
		return pwd;
	}

	public List<MemberVO> selectAllMemberList() {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List<MemberVO> membersList = null;
		membersList = session.selectList("mapper.member.selectAllMemberList");
		return membersList;
	}

	public MemberVO selectMemberById(String id) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		//레코드가 한개일때 selectOne 사용, id는 조건값으로 사용한다.
		//mappers.member라는 명칭으로 id가 selectMemberById인것을 조회해서 실행시키는 방법
		MemberVO memberVO = (MemberVO) session.selectOne("mapper.member.selectMemberById", id);
		return memberVO;
	}

	public List<MemberVO> selectMemberByPwd(int pwd) {
		//sql의 instance를 가져와서 session을 open
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List<MemberVO> membersList = null;
		//session의 selectList 메소드를 사용(여러개가 있을수 있으므로 List로 받아온다) 
		membersList = session.selectList("mapper.member.selectMemberByPwd", pwd);
		return membersList;
	}

	public int insertMember(MemberVO memberVO) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = 0;
		result = session.insert("mapper.member.insertMember", memberVO);
		session.commit();
		return result;
	}

	public int insertMember2(Map<String, String> memberMap) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = session.insert("mapper.member.insertMember2", memberMap);
		session.commit();
		return result;
	}

	public int updateMember(MemberVO memberVO) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = session.update("mapper.member.updateMember", memberVO);
		session.commit();
		return result;
	}

	public int deleteMember(String id) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = 0;
		result = session.delete("mapper.member.deleteMember", id );
		session.commit();
		return result;
	}

	public List searchMember(MemberVO memberVO) {
		sqlMapper=getInstance();
		SqlSession session = sqlMapper.openSession();
		List list = session.selectList("mapper.member.searchMember", memberVO);
		return list;
	}

	public List foreachSelect(List<String> nameList) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List list = session.selectList("mapper.member.foreachSelect", nameList);
		return list;
	}

	public int foreachInsert(List<MemberVO> memList) {
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		int result = session.insert("mapper.member.foreachInsert", memList);
		session.commit();
		return result;
	}
}
