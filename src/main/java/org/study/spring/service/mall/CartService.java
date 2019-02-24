package org.study.spring.service.mall;

import java.util.List;

import org.study.spring.model.mall.dto.CartDTO;

public interface CartService {

	public List<CartDTO> cartMoney();
	public List<CartDTO> listCart(String userid);
	public void insert(CartDTO dto);
	public void delete(int cartID);
	public void deleteAll(String userid);
	public void update(int cartID);
	public int totalMoney(String userid);
	public int countCart(String userid, int productID);
	public void updateCart(CartDTO dto);
	public void modifyCart(CartDTO dto);
	
}
