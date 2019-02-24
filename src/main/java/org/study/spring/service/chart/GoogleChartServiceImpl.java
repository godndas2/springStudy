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
	
	// "������" : "��"
	@Override
	public JSONObject getChartData() {
		List<CartDTO> cart = cartService.cartMoney();
		
		JSONObject data = new JSONObject(); // { } ���·� �������ش�. (jsonTest.json ����)
		// JSON column
		JSONObject col1 = new JSONObject();
		JSONObject col2 = new JSONObject();
		// JSON Array
		JSONArray title = new JSONArray();
		col1.put("label", "��ǰ��");
		col1.put("type", "string");
		col2.put("label", "�ݾ�");
		col2.put("type", "number");
		
		title.add(col1);
		title.add(col2);
		// { "cols" : [{"label" : "��ǰ��", "type":"string"},{"label" : "�ݾ�", "type":"number"}]}
		data.put("cols", title);
		
		JSONArray arr = new JSONArray();
		for (CartDTO dto : cart) {
			JSONObject name = new JSONObject();
			// "v"�� "c"�� �ǹ̴� jsonTest.json ����
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
