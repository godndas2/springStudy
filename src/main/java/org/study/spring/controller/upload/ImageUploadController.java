package org.study.spring.controller.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageUploadController {

	private static final Logger logger = LoggerFactory.getLogger(ImageUploadController.class);
	
	@ResponseBody
	@RequestMapping("imageUpload")
	public void imageUpload(HttpServletRequest req, HttpServletResponse res, @RequestParam MultipartFile upload) throws Exception {
		//HTTP HEADER
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		//HTTP BODY
		String fNmae = upload.getOriginalFilename(); // ���ε��� ���� ��
		byte[] bytes = upload.getBytes(); // byte �迭�� ��ȯ
		String uploadPath = "D:\\springShoppingMall\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\shoppingMall\\WEB-INF\\views\\image\\";
		OutputStream out = new FileOutputStream(new File(uploadPath + fNmae));
		// ��� �ƴ� ������ ����
		out.write(bytes);
		String call = req.getParameter("CKEditorFuncNum");
		PrintWriter writer =  res.getWriter();
		String fUrl = req.getContextPath()+"/image/"+fNmae;
		System.out.println("file ��� : " + fUrl);
		writer.println("<script>window.parent.CKEDITOR.tools.callFunction("+ call + ",'" + fUrl + "','�̹����� ���ε� �Ǿ����ϴ�')" + "</script>");
		// stream �ݱ�
		writer.flush();
		
	}
}
