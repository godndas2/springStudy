package org.study.spring.service.mall;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.spring.model.mall.dao.ProductDAO;
import org.study.spring.model.mall.dto.ProductDTO;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDAO productDAO;

	@Override
	public List<ProductDTO> listProduct() {
		return productDAO.listProduct();
	}

	@Override
	public ProductDTO detailProduct(int productID) {
		return productDAO.detailProduct(productID);
	}

	@Override
	public String fileInfo(int productID) {
		return productDAO.fileInfo(productID);
	}

	@Override
	public void insertProduct(ProductDTO dto) {
		productDAO.insertProduct(dto);
	}

	@Override
	public void updateProduct(ProductDTO dto) {
		productDAO.updateProduct(dto);
	}

	@Override
	public void deleteProduct(int productID) {
		productDAO.deleteProduct(productID);
	}

}
