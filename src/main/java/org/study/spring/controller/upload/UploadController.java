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
	
	@Resource(name = "uploadPath") // servlet-context.xml의 bean id가 uplaodPath이기 때문에 Resource에서 name을 uploadPath로 지정해준다.
	String uploadPath;
	
	@RequestMapping(value = "/upload/upload", method = RequestMethod.GET)
	public void upload() {} // GET방식일 땐 upload/upload.jsp의 파일업로드 화면으로 보내준다.
	
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
		
		// UUID : 고유식별자(파일이름 중복방지)
		UUID uid = UUID.randomUUID(); // Random으로 파일명 앞에 생성해서 붙여줌
		String savedFileName = uid.toString() + "_" + orgFileNm;
		File file = new File(uploadPath, savedFileName);
		FileCopyUtils.copy(fileData, file); // 지정한 디렉토리로 복사 (byte 배열, file 객체 )
		return savedFileName;
	}
}
