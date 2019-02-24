package org.study.spring.controller.upload;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@Resource(name = "uploadPath") // servlet-context.xml�� bean id�� uplaodPath�̱� ������ Resource���� name�� uploadPath�� �������ش�.
	String uploadPath;
	
	@RequestMapping(value = "/upload/upload", method = RequestMethod.GET)
	public void upload() {} // GET����� �� upload/upload.jsp�� ���Ͼ��ε� ȭ������ �����ش�.
	
	@RequestMapping(value = "/upload/upload", method = RequestMethod.POST)
	public ModelAndView upload(MultipartFile file, ModelAndView mv) throws Exception {
		logger.info("FILE NAME : " + file.getOriginalFilename());
		String savedFileName = file.getOriginalFilename();
		logger.info("FILE SIZE : " + file.getSize());
		logger.info("FILE TYPE : " + file.getContentType());
		savedFileName = uploadFile(savedFileName, file.getBytes());
		mv.setViewName("upload/uploadSuccess");
		mv.addObject("savedFileName", savedFileName);
		return mv;
	}
	
	private String uploadFile(String orgFileNm, byte[] fileData) throws Exception {
		
		// UUID : �����ĺ���(�����̸� �ߺ�����)
		UUID uid = UUID.randomUUID(); // Random���� ���ϸ� �տ� �����ؼ� �ٿ���
		String savedFileName = uid.toString() + "_" + orgFileNm;
		File file = new File(uploadPath, savedFileName);
		FileCopyUtils.copy(fileData, file); // ������ ���丮�� ���� (byte �迭, file ��ü )
		return savedFileName;
	}
}
