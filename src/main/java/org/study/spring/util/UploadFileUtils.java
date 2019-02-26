package org.study.spring.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;

public class UploadFileUtils {

	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);
	
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception{
		
		UUID uid = UUID.randomUUID();
		
		String savedName = uid.toString() + "_" + originalName; //UUID를 추가한 file name
		// upload 할 폴더 생성
		String savedPath = calcPath(uploadPath);
		File target = new File(uploadPath + savedPath, savedName);
		FileCopyUtils.copy(fileData, target); // 임시 폴더에 업로드 된 파일을 지정한 폴더로 복사
		
		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
		String uploadFileName = null;
		// 썸네일 생성
		if (MediaUtil.getMediaType(formatName) != null) {
			uploadFileName = makeThumbNail(uploadPath, savedPath, savedName);
		} else { // 아이콘생성
			uploadFileName = makeIcon(uploadPath, savedPath, savedName);
		}
		return uploadFileName;
	}
	
	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {
		// Icon Name
		String iconName = uploadPath + path + File.separator + fileName; //File.separator : 폴더 구분자
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	private static String makeThumbNail(String uploadPath, String path, String fileName) throws Exception {
		// 이미지를 읽기 위한 버퍼
		BufferedImage img = ImageIO.read(new File(uploadPath + path, fileName));
		// 100px 단위 썸네일 생성
		BufferedImage tImage = Scalr.resize(img, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		// 썸네일 이름
		String tName = uploadPath + path + File.separator + "thumb_" + fileName;
		File newFile = new File(tName);
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		// 썸네일 생성
		ImageIO.write(tImage, formatName.toUpperCase(), newFile);
		// 썸네일 이름 리턴
		return tName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		String year = File.separator + cal.get(Calendar.YEAR);
		String month = year + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String date = month + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		//폴더 생성
		makeDir(uploadPath, year, month, date);
		logger.info(date);
		return date;
	}
	
	private static void makeDir(String uploadPath, String... paths) { //String... : 배열로 매개변수를 받는다
																	 // ex: [0] = year, [1] = month, [2] = date 
		// 폴더가 있다면
		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}
		for (String path : paths) {
			File dirPath = new File(uploadPath + path);
			if (!dirPath.exists()) { // 폴더가 없으면
				dirPath.mkdir(); // 폴더 생성
			}
		}
	}
}
