package org.study.spring.model.mall.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.study.spring.model.mall.dto.ProductDTO;

@Repository
public class ProductDAOImpl implements ProductDAO {
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<ProductDTO> listProduct() {
		return sqlSession.selectList("product.listProduct");
	}

	@Override
	public ProductDTO detailProduct(int productID) {
		return sqlSession.selectOne("product.detailProduct", productID);
	}

	@Override
	public String fileInfo(int productID) {
		return sqlSession.selectOne("product.fileInfo", productID);
	}

	@Override
	public void insertProduct(ProductDTO dto) {
		sqlSession.insert("product.insert", dto);
	}

	@Override
	public void updateProduct(ProductDTO dto) {
		sqlSession.update("product.updateProduct", dto);

	}

	@Override
	public void deleteProduct(int productID) {
		sqlSession.delete("product.deleteProduct", productID);
	}

}
