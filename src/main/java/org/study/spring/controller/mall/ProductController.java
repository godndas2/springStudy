package org.study.spring.controller.mall;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.study.spring.model.mall.dto.ProductDTO;
import org.study.spring.service.mall.ProductService;

@Controller
@RequestMapping("/mall/product/*") // ���� Url
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping("list") // ��ܿ� /mall/product/ �� ��������� ������ list�� �ۼ��ص� /mall/product/list�� �ν����ش�. 
	public ModelAndView list(ModelAndView mv) {
		mv.setViewName("/mall/productList"); // �ش� �������� �̵�
		mv.addObject("list", productService.listProduct());
		return mv;
	}

	@RequestMapping("/detail/{productID}")  
	public ModelAndView detail(@PathVariable("productID") int productID , ModelAndView mv) {
		mv.setViewName("/mall/productDetail");
		mv.addObject("dto", productService.detailProduct(productID));
		return mv;
	}
	
	@RequestMapping("insert")
	public String insert(ProductDTO dto) { 
		String fName = "-";
		
		if (!dto.getMf().isEmpty()) { // ������ �����Ѵٸ�
			fName = dto.getMf().getOriginalFilename();
			String path = "D:\\springShoppingMall\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\shoppingMall\\";
			try {
				new File(path).mkdir();
				dto.getMf().transferTo(new File(path + fName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dto.setImgURL(fName);
		productService.insertProduct(dto);
		return "redirect:/mall/product/list";
	}

	@RequestMapping("update")
	public String update(ProductDTO dto) { 
		String fName = "-";
		
		if (!dto.getMf().isEmpty()) { // ������ �����Ѵٸ�
			fName = dto.getMf().getOriginalFilename();
			String path = "D:\\springShoppingMall\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\shoppingMall\\";
			try {
				new File(path).mkdir();
				dto.getMf().transferTo(new File(path + fName));
			} catch (Exception e) {
				e.printStackTrace();
			}
			dto.setImgURL(fName);
		} else {
			ProductDTO dto2 = productService.detailProduct(dto.getProductID());
					   dto.setImgURL(dto2.getImgURL());
		}
		productService.updateProduct(dto);
		return "redirect:/mall/product/list";
	}
	
	@RequestMapping("delete")
	public String delete(@RequestParam int productID) { 
		// ÷������ �̸�
		String fName = productService.fileInfo(productID);
		
		if (fName != null && !fName.equals("-")) { 
			String path = "D:\\springShoppingMall\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\shoppingMall\\";
			File f = new File(path + fName);
			if (f.exists()) { // ������ �ִٸ�
				f.delete(); // ������
			}
		}
		productService.deleteProduct(productID);
		return "redirect:/mall/product/list";
	}
	
	// ������ ��ǰ ����
	@RequestMapping("/edit/{productID}")  
	public ModelAndView edit(@PathVariable("productID") int productID , ModelAndView mv) {
		mv.setViewName("/mall/productEdit");
		mv.addObject("dto", productService.detailProduct(productID));
		return mv;
	}
}
