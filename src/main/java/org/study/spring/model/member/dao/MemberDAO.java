package org.study.spring.model.member.dao;

import java.util.List;

import org.study.spring.model.member.dto.MemberDTO;

public interface MemberDAO {
	public String loginCheck(MemberDTO dto);
	public List<MemberDTO> memberList();
	public void insertMember(MemberDTO dto);
	public MemberDTO readMember(String userid);
	public void deleteMember(String userid);
	public void updateMember(MemberDTO dto);
	// ��й�ȣ üũ
	public boolean checkPwd(String userid, String pwd);
}
