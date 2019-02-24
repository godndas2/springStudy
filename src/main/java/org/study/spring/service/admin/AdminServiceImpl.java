package org.study.spring.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.spring.model.admin.AdminDAO;
import org.study.spring.model.member.dto.MemberDTO;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDAO adminDao;
	
	@Override
	public String loginCheck(MemberDTO dto) {
		return adminDao.loginCheck(dto);
	}

}
