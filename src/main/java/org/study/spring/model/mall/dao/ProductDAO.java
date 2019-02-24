package org.study.spring.model.mall.dao;

import java.util.List;

import org.study.spring.model.mall.dto.ProductDTO;

public interface ProductDAO {

	List<ProductDTO> listProduct();
	ProductDTO detailProduct(int productID);
	String fileInfo(int productID); 
	void insertProduct(ProductDTO dto);
	void updateProduct(ProductDTO dto);
	void deleteProduct(int productID);
}
