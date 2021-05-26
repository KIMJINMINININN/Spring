package com.spring.ex03;

import java.io.Reader;
import java.util.List;

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
				//myBatis�� �̿��ϴ� sqlMapper ��ü ��������
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
		//���ڵ尡 �Ѱ��϶� selectOne ���, id�� ���ǰ����� ����Ѵ�.
		//mappers.member��� ��Ī���� id�� selectMemberById�ΰ��� ��ȸ�ؼ� �����Ű�� ���
		MemberVO memberVO = (MemberVO) session.selectOne("mapper.member.selectMemberById", id);
		return memberVO;
	}

	public List<MemberVO> selectMemberByPwd(int pwd) {
		//sql�� instance�� �����ͼ� session�� open
		sqlMapper = getInstance();
		SqlSession session = sqlMapper.openSession();
		List<MemberVO> membersList = null;
		//session�� selectList �޼ҵ带 ���(�������� ������ �����Ƿ� List�� �޾ƿ´�) 
		membersList = session.selectList("mapper.member.selectMemberByPwd", pwd);
		return membersList;
	}
}