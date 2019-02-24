package org.study.spring.service.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.spring.model.member.dao.MemberDAO;
import org.study.spring.model.member.dto.MemberDTO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDAO memberDao;
	
	@Override
	public String loginCheck(MemberDTO dto, HttpSession session) {
		
		String id = memberDao.loginCheck(dto);
		// login check
		if (id != null) { // login ���� ��
			session.setAttribute("userid", dto.getUserid());
			session.setAttribute("id", id);
		}
		return id;
	}

	@Override
	public void logout(HttpSession session) {
		session.invalidate(); // session�� �ʱ�ȭ��Ű�鼭 �α׾ƿ��� �ȴ�.
	}
	
	@Override
	public List<MemberDTO> memberList() {
		return memberDao.memberList();
	}

	@Override
	public void insertMember(MemberDTO dto) {
		memberDao.insertMember(dto);

	}

	@Override
	public MemberDTO readMember(String userid) {
		return memberDao.readMember(userid);
	}

	@Override
	public void deleteMember(String userid) {
		memberDao.deleteMember(userid);
	}

	@Override
	public void updateMember(MemberDTO dto) {
		memberDao.updateMember(dto);
	}

	@Override
	public boolean checkPwd(String userid, String pwd) {
		return memberDao.checkPwd(userid, pwd);
	}

}
