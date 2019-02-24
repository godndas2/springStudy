package org.study.spring.controller.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.study.spring.model.message.dto.MessageDTO;
import org.study.spring.service.message.MessageService;

@RestController
@RequestMapping("/message/*")
public class MessageController {
	
	@Autowired
	MessageService messageService;
	
	// @ResponseEntity : Http Status Code(Http 상태 코드) + Data 전달
	// @RequestBody : Client - server(json 데이터 입력 될 때)
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<String> insertMessage(@RequestBody MessageDTO dto){
		ResponseEntity<String> entity = null;
		try {
			 messageService.insertMessage(dto);
			 // new ResponseEntity<자료형>(리턴해줄 값, http 상태 코드) 
			 entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
}
