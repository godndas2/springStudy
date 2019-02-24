package org.study.spring.model.admin;

import org.study.spring.model.member.dto.MemberDTO;

public interface AdminDAO {
	public String loginCheck(MemberDTO dto);
}
