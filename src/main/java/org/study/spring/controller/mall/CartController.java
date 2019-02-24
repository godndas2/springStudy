package org.study.spring.controller.mall;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.study.spring.controller.member.MemberController;
import org.study.spring.model.mall.dto.CartDTO;
import org.study.spring.service.mall.CartService;

@Controller
@RequestMapping("/cart/*")
public class CartController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	@Autowired
	CartService cartService;
	
	@RequestMapping("insert")
	public String insert(@ModelAttribute CartDTO dto, HttpSession session) {
		
		// �α��� ���θ� Ȯ���ϱ� ���� session�� �����  "userid" Ȯ��
		String userid = (String) session.getAttribute("userid");
		
		if (userid == null) {
			return "redirect:/member/login";
		}
		// �α��� ������
		dto.setUserid(userid);
		cartService.insert(dto); // ��ٱ��Ͽ� ��ǰ ����
		return "redirect:/mall/cart/list";
	}
	
	@RequestMapping("list")
	public ModelAndView list(HttpSession session, ModelAndView mv) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String userid = (String) session.getAttribute("userid");
		
		if(userid != null) { // �α��� �� ���
			
			List<CartDTO> list = cartService.listCart(userid);
			
			int totalMoney = cartService.totalMoney(userid); // ��ٱ��� �ݾ� �Ѿ�
			
			// 5���� �̻��̸� ��ۺ񹫷�
			int free = totalMoney >= 50000 ? 0 : 2500; // 50000�̸� 0, �ƴ϶�� 2500
			map.put("totalMoney", totalMoney);
			map.put("free", free);
			map.put("total", totalMoney + free);
			map.put("list", list); // ��ٱ��� ���
			map.put("count", list.size());
			
			mv.setViewName("mall/cartList");
			mv.addObject("map",map);
			return mv; // mall/cartList�� ������
		} else {
			return new ModelAndView("member/login","",null);
		}
	}
	
	@RequestMapping("update")
	public String update(int[] count, int[] cartID, HttpSession session) {
		String userid = (String) session.getAttribute("userid");
		for (int i = 0; i < cartID.length; i++) {
			if (count[i] == 0) { // ��ٱ����� ������ 0�̵Ǹ� �����ش�
				cartService.delete(cartID[i]);
			} else {
				CartDTO dto = new CartDTO();
				dto.setUserid(userid);
				dto.setCartID(cartID[i]);
				dto.setCount(count[i]);
				cartService.modifyCart(dto);
			}
		}
		return "redirect:/mall/cart/list";
	}
	
	@RequestMapping("delete")
	public String delete(@RequestParam int cartID) {
		cartService.delete(cartID);
		return "redirect:/mall/cartList";
	}

	@RequestMapping("deleteAll")
	public String deleteAll(HttpSession session) {
		String userid = (String) session.getAttribute("userid");
		if (userid != null) {
			cartService.deleteAll(userid);
		}
		return "redirect:/mall/cartList";
	}
	
}
