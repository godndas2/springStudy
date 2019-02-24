package org.study.spring.service.mall;

import java.util.List;

import org.study.spring.model.mall.dto.ProductDTO;

public interface ProductService {

	List<ProductDTO> listProduct();
	ProductDTO detailProduct(int productID);
	String fileInfo(int productID); 
	void insertProduct(ProductDTO dto);
	void updateProduct(ProductDTO dto);
	void deleteProduct(int productID);
}
