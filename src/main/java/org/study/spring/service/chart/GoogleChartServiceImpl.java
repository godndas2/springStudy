package org.study.spring.service.chart;

import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.spring.model.mall.dto.CartDTO;
import org.study.spring.service.mall.CartService;

@Service
public class GoogleChartServiceImpl implements GoogleChartService {

	@Autowired
	CartService cartService;
	
	// "변수명" : "값"
	@Override
	public JSONObject getChartData() {
		List<CartDTO> cart = cartService.cartMoney();
		
		JSONObject data = new JSONObject(); // { } 형태로 리턴해준다. (jsonTest.json 참고)
		// JSON column
		JSONObject col1 = new JSONObject();
		JSONObject col2 = new JSONObject();
		// JSON Array
		JSONArray title = new JSONArray();
		col1.put("label", "상품명");
		col1.put("type", "string");
		col2.put("label", "금액");
		col2.put("type", "number");
		
		title.add(col1);
		title.add(col2);
		// { "cols" : [{"label" : "상품명", "type":"string"},{"label" : "금액", "type":"number"}]}
		data.put("cols", title);
		
		JSONArray arr = new JSONArray();
		for (CartDTO dto : cart) {
			JSONObject name = new JSONObject();
			// "v"와 "c"의 의미는 jsonTest.json 참조
			name.put("v", dto.getProductName());
			JSONObject money = new JSONObject();
			money.put("v", dto.getMoney());
			JSONArray row = new JSONArray();
			row.add(name);
			row.add(money);
			JSONObject cell = new JSONObject();
			cell.put("c", row);
			arr.add(cell);
		}
		data.put("rows", arr);
		return data;
	}

}
