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
		
		String savedName = uid.toString() + "_" + originalName; //UUID�� �߰��� file name
		// upload �� ���� ����
		String savedPath = calcPath(uploadPath);
		File target = new File(uploadPath + savedPath, savedName);
		FileCopyUtils.copy(fileData, target); // �ӽ� ������ ���ε� �� ������ ������ ������ ����
		
		String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
		String uploadFileName = null;
		// ����� ����
		if (MediaUtil.getMediaType(formatName) != null) {
			uploadFileName = makeThumbNail(uploadPath, savedPath, savedName);
		} else { // �����ܻ���
			uploadFileName = makeIcon(uploadPath, savedPath, savedName);
		}
		return uploadFileName;
	}
	
	private static String makeIcon(String uploadPath, String path, String fileName) throws Exception {
		// Icon Name
		String iconName = uploadPath + path + File.separator + fileName; //File.separator : ���� ������
		return iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	private static String makeThumbNail(String uploadPath, String path, String fileName) throws Exception {
		// �̹����� �б� ���� ����
		BufferedImage img = ImageIO.read(new File(uploadPath + path, fileName));
		// 100px ���� ����� ����
		BufferedImage tImage = Scalr.resize(img, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);
		// ����� �̸�
		String tName = uploadPath + path + File.separator + "thumb_" + fileName;
		File newFile = new File(tName);
		String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
		// ����� ����
		ImageIO.write(tImage, formatName.toUpperCase(), newFile);
		// ����� �̸� ����
		return tName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	}
	
	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		String year = File.separator + cal.get(Calendar.YEAR);
		String month = year + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		String date = month + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));
		
		//���� ����
		makeDir(uploadPath, year, month, date);
		logger.info(date);
		return date;
	}
	
	private static void makeDir(String uploadPath, String... paths) { //String... : �迭�� �Ű������� �޴´�
																	 // ex: [0] = year, [1] = month, [2] = date 
		// ������ �ִٸ�
		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}
		for (String path : paths) {
			File dirPath = new File(uploadPath + path);
			if (!dirPath.exists()) { // ������ ������
				dirPath.mkdir(); // ���� ����
			}
		}
	}
}
