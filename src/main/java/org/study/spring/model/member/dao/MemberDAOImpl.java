package org.study.spring.model.member.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.study.spring.model.member.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public String loginCheck(MemberDTO dto) {
		return sqlSession.selectOne("member.loginCheck", dto);
	}
	@Override
	public List<MemberDTO> memberList() {
		return sqlSession.selectList("member.memberList"); // xml�� id�� �����ϰ� ���ش�.
	}

	@Override
	public void insertMember(MemberDTO dto) {
		sqlSession.insert("member.insertMember", dto);
	}

	@Override
	public MemberDTO readMember(String userid) {
		return sqlSession.selectOne("member.readMember",userid); // selectOne : �� ���� �ุ(row) ��ȸ , �� ���̻� �� selectList
	}

	@Override
	public void deleteMember(String userid) {
		sqlSession.delete("member.deleteMember", userid);
	}

	@Override
	public void updateMember(MemberDTO dto) {
		sqlSession.update("member.updateMember", dto);
		
	}

	@Override
	public boolean checkPwd(String userid, String pwd) {
		boolean result = false;
		
		// Map : mapper(SQL)�� ���� �ѱ�°�  �� �� �̻��� ��� map�� ����ؼ� �����Ѵ�.
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("pwd", pwd);
		
		int cnt = sqlSession.selectOne("member.checkPwd", map);
		if (cnt == 1) {
			result = true;
		}
		return result;
	}

}
