package org.study.spring.model.message.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PointDAOImpl implements PointDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public void point(String userID, int point) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userID", userID);
		map.put("point", point);
		sqlSession.update("point.point", map);
	}

}
