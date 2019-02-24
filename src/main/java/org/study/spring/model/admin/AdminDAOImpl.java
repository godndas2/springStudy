package org.study.spring.model.admin;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.study.spring.model.member.dto.MemberDTO;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public String loginCheck(MemberDTO dto) {
		return sqlSession.selectOne("admin.loginCheck", dto);
	}

}
