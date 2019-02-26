package org.study.spring.controller.upload;

import java.awt.PageAttributes.MediaType;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.study.spring.util.MediaUtil;
import org.study.spring.util.UploadFileUtils;

@Controller
public class AjaxUploadController {

	private static final Logger logger = LoggerFactory.getLogger(AjaxUploadController.class);
	
	// servlet-context.xml Ȯ��
	@Resource(name = "uploadPath")
	String uploadPath;
	
	@RequestMapping(value = "/upload/uploadAjax", method = RequestMethod.GET)
	public void uploadAjax() {}// uploadAjax.jsp �� �̵�
	
	@RequestMapping(value = "/upload/uploadAjax", method = RequestMethod.POST, produces = "text/plain;charset=utf-8")
	@ResponseBody
	public ResponseEntity<String> uploadAjax (MultipartFile file) throws Exception { // ResponseEntity : message�� Error Code�� ���� ������
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
			// ���� Ȯ���� �˻�
			String formatName = fileName.substring(fileName.lastIndexOf(".") +  1);
			org.springframework.http.MediaType mType = MediaUtil.getMediaType(formatName);
			// ��� ����
			HttpHeaders h = new HttpHeaders();
			is = new FileInputStream(uploadPath + fileName);
			if (mType != null) {
				h.setContentType(mType);;
			}else {
				fileName = fileName.substring(fileName.indexOf("_") + 1);
				h.setContentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM); // content type
				fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1"); // ���� �̸��� �ѱ��� ��� ���� ����
				h.add("Content-Disposition", "attachment; filename=\""+fileName+"\""); // ÷����������
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
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1); //Ȯ���� �˻�
		org.springframework.http.MediaType mType = MediaUtil.getMediaType(formatName);
		if (mType != null) { // �̹��� ������ �� ���� ���� ����
			String front = fileName.substring(0, 12);
			String end = fileName.substring(14);
			new File(uploadPath + (front+end).replace('/', File.separatorChar)).delete(); // ����� ����
		}
		// �̹��� ������ ��� ����� ����
		new File(uploadPath + fileName.replace('/', File.separatorChar)).delete(); // file ����
		return new ResponseEntity<String>("deleted", HttpStatus.OK);
	}
}
