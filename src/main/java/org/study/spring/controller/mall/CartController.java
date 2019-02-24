package org.study.spring.controller.mall;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.study.spring.model.mall.dto.CartDTO;
import org.study.spring.service.mall.CartService;

@Controller
@RequestMapping("/cart/*")
public class CartController {

	@Autowired
	CartService cartService;
	 
	@RequestMapping("insert")
	public String insert(@ModelAttribute CartDTO dto, HttpSession session) {
		
		// 로그인 여부를 확인하기 위해 session에 저장된  "userid" 확인
		// 이후에 Interceptor 학습떈 아래 if문을 주석으로 막아주시기 바랍니다.
		String userid = (String) session.getAttribute("userid");
		
		if (userid == null) {
			return "redirect:/member/login";
		}
		// 로그인 성공시
		dto.setUserid(userid);
		cartService.insert(dto); // 장바구니에 상품 저장
		return "redirect:/cart/list";
	}
	
	@RequestMapping("list")
	public ModelAndView list(HttpSession session, ModelAndView mv) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String userid = (String) session.getAttribute("userid");
		
		if(userid != null) { // 로그인 한 경우
			
			List<CartDTO> list = cartService.listCart(userid); // 장바구니 목록
			
			int totalMoney = cartService.totalMoney(userid); // 장바구니 금액 총액
			
			// 5만원 이상이면 배송비무료
			int free = totalMoney >= 50000 ? 0 : 2500; // 50000이면 0, 아니라면 2500
			map.put("totalMoney", totalMoney);
			map.put("free", free);
			map.put("total", totalMoney + free);
			map.put("list", list); // 장바구니 목록
			map.put("cnt", list.size());
			
			mv.setViewName("mall/cartList");
			mv.addObject("map",map);
			return mv; // mall/cartList로 보내기
		} else {
			// login을 안했으면 login 페이지로 보낸다
			// 이후에 Interceptor 학습떈 아래 코드를 return null; 로 바꿔주시기 바랍니다.
			return new ModelAndView("member/login","",null); 
		}
	}
	
	@RequestMapping("update")
	public String update(int[] cnt, int[] cartID, HttpSession session) {
		String userid = (String) session.getAttribute("userid");
		for (int i = 0; i < cartID.length; i++) {
			if (cnt[i] == 0) { // 장바구니의 수량이 0이되면 지워준다
				cartService.delete(cartID[i]);
			} else {
				CartDTO dto = new CartDTO();
				dto.setUserid(userid);
				dto.setCartID(cartID[i]);
				dto.setCnt(cnt[i]);
				cartService.modifyCart(dto);
			}
		}
		return "redirect:/cart/list";
	}
	
	@RequestMapping("delete")
	public String delete(@RequestParam int cartID) {
		cartService.delete(cartID);
		return "redirect:/cart/list";
	}

	@RequestMapping("deleteAll")
	public String deleteAll(HttpSession session) {
		String userid = (String) session.getAttribute("userid");
		if (userid != null) {
			cartService.deleteAll(userid);
		}
		return "redirect:/cart/list";
	}
	
}
