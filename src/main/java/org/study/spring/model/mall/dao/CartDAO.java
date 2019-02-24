package org.study.spring.model.mall.dao;

import java.util.List;

import org.study.spring.model.mall.dto.CartDTO;

public interface CartDAO {
	
	public List<CartDTO> cartMoney();
	public List<CartDTO> listCart(String userid);
	public void insert(CartDTO dto);
	public void delete(int cartID);
	public void deleteAll(String userid);
	public void update(int cartID);
	public int totalMoney(String userid);
	public int countCart(String userid, int productID); // ��ٱ��Ͼȿ� ��ǰ ����Ȯ��
	public void updateCart(CartDTO dto);
	public void modifyCart(CartDTO dto);
	
	
}
