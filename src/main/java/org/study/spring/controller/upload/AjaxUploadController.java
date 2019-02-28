package org.study.spring.controller.upload;

import java.awt.PageAttributes.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.study.spring.service.board.BoardService;
import org.study.spring.util.MediaUtil;
import org.study.spring.util.UploadFileUtils;

@Controller
public class AjaxUploadController {

	private static final Logger logger = LoggerFactory.getLogger(AjaxUploadController.class);
	
	// servlet-context.xml 확인
	@Resource(name = "uploadPath")
	String uploadPath;
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value = "/upload/uploadAjax", method = RequestMethod.GET)
	public void uploadAjax() {}// uploadAjax.jsp 로 이동
	
	@RequestMapping(value = "/upload/uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ResponseEntity<String> uploadAjax (MultipartFile file) throws Exception { // ResponseEntity : message와 Error Code를 같이 보여줌
		logger.info("originalName : " + file.getOriginalFilename());
		logger.info("size : " + file.getSize());
		logger.info("contentType : " + file.getContentType());
		
		return new ResponseEntity<String>(
				UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes()), HttpStatus.OK);
	}
	
	@ResponseBody
	@RequestMapping("/upload/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		InputStream is = null;
		ResponseEntity<byte[]> entity = null;
		
		try {
			// 파일 확장자 검사
			String formatName = fileName.substring(fileName.lastIndexOf(".") +  1);
			org.springframework.http.MediaType mType = MediaUtil.getMediaType(formatName);
			// 헤더 생성
			HttpHeaders h = new HttpHeaders();
			is = new FileInputStream(uploadPath + fileName);
			if (mType != null) {
				h.setContentType(mType);;
			}else {
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				h.setContentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM); // content type
				fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1"); // 파일 이름이 한글일 경우 께짐 방지
				h.add("Content-Disposition", "attachment; filename=\""+fileName+"\""); // 첨부파일정보
			}
			entity = new ResponseEntity<byte[]>(
					IOUtils.toByteArray(is), h, HttpStatus.OK
					);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(
					HttpStatus.BAD_REQUEST);
		} finally {
			if (is != null) {
				is.close();
			}
		}
		return entity;
	}
	
	@ResponseBody
	@RequestMapping("/upload/deleteFile")
	public ResponseEntity<String> deleteFile(String fileName) {
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1); //확장자 검사
		org.springframework.http.MediaType mType = MediaUtil.getMediaType(formatName);
		if (mType != null) { // 이미지 파일일 때 원본 파일 삭제
			String front = fileName.substring(0, 12);
			String end = fileName.substring(14);
			new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete(); // 썸네일 삭제
		}
		// 이미지 파일의 경우 썸네일 삭제
		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete(); // file 삭제
		// 레코드 삭제
		boardService.deleteFile(fileName);
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
}
