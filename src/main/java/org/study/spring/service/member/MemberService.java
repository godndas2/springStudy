package org.study.spring.service.member;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.study.spring.model.member.dto.MemberDTO;

public interface MemberService {

	public String loginCheck(MemberDTO dto, HttpSession session);
	public void logout(HttpSession session);
	public List<MemberDTO> memberList();
	public void insertMember(MemberDTO dto);
	public MemberDTO readMember(String userid);
	public void deleteMember(String userid);
	public void updateMember(MemberDTO dto);
	// ��й�ȣ üũ
	public boolean checkPwd(String userid, String pwd);
}
