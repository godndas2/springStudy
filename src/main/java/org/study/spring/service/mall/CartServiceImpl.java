package org.study.spring.service.mall;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.spring.model.mall.dao.CartDAO;
import org.study.spring.model.mall.dto.CartDTO;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartDAO cartDao;
	
	@Override
	public List<CartDTO> cartMoney() {
		// TODO Auto-generated method stub
		return cartDao.cartMoney();
	}

	@Override
	public List<CartDTO> listCart(String userid) {
		// TODO Auto-generated method stub
		return cartDao.listCart(userid);
	}

	@Override
	public void insert(CartDTO dto) {
		cartDao.insert(dto);
	}

	@Override
	public void delete(int cartID) {
		cartDao.delete(cartID);
	}

	@Override
	public void deleteAll(String userid) {
		cartDao.deleteAll(userid);
	}

	@Override
	public void update(int cartID) {
//		cartDao.update(cartID);
	}

	@Override
	public int totalMoney(String userid) {
		return cartDao.totalMoney(userid);
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
		cartDao.modifyCart(dto);
	}

}
