package org.study.spring.model.mall.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.study.spring.model.mall.dto.CartDTO;

@Repository
public class CartDAOImpl implements CartDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public List<CartDTO> cartMoney() {
		return sqlSession.selectList("cart.cartMoney");
	}

	@Override
	public List<CartDTO> listCart(String userid) {
		return sqlSession.selectList("cart.listCart",userid);
	}

	@Override
	public void insert(CartDTO dto) {
		sqlSession.insert("cart.insert", dto);
	}

	@Override
	public void delete(int cartID) {
		sqlSession.delete("cart.delete",cartID);
	}

	@Override
	public void deleteAll(String userid) {
		sqlSession.delete("cart.deleteAll", userid);
	}

	@Override
	public void update(int cartID) {
//		sqlSession.update("cart.update", cartID);
	}

	@Override
	public int totalMoney(String userid) {
		return sqlSession.selectOne("cart.totalMoney", userid);
	}

	@Override
	public int countCart(String userid, int productID) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void updateCart(CartDTO dto) {
		// TODO Auto-generated method stub

	}

	@Override
	public void modifyCart(CartDTO dto) {
		sqlSession.update("cart.modifyCart", dto);
	}

}
