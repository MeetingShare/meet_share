package com.meet.common;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

import com.meet.common.constants.MeetConstants;
import com.meet.exception.BusinessException;
import lombok.Data;
import net.coobird.thumbnailator.Thumbnails;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 图片处理 规则如下： a，图片宽或者高均小于或等于1280时图片尺寸保持不变，但仍然经w过图片压缩处理，得到小文件的同尺寸图片
 * 
 * b，宽或者高大于1280，但是图片宽度高度比小于或等于2，则将图片宽或者高取大的等比压缩至1280
 * 
 * c，宽或者高大于1280，但是图片宽高比大于2时，并且宽以及高均大于1280，则宽或者高取小的等比压缩至1280
 * 
 * d，宽或者高大于1280，但是图片宽高比大于2时，并且宽或者高其中一个小于1280，则压缩至同尺寸的小文件图片 Created by bzhx on
 * 2017年3月3日 上午9:49:14
 */
@Data
public class ImgCompress {
	private Logger log = LoggerFactory.getLogger(MeetConstants.LOG_NAME);
	private static int IMAGE_SIZE = 1280;
	private Image img;
	private int width;
	private int height;
	private String sourceFilePath;

	public ImgCompress(String sourceFilePath) throws Exception {
		this.sourceFilePath = sourceFilePath;
		File file = new File(sourceFilePath);// 读入文件
		img = ImageIO.read(file); // 构造Image对象
		width = img.getWidth(null); // 得到源图宽
		height = img.getHeight(null); // 得到源图长
	}

	public String resize() throws Exception {
		try {
			String newFileName=sourceFilePath.substring(sourceFilePath.lastIndexOf("/") + 1, sourceFilePath.length());
			newFileName=newFileName.substring(0,newFileName.lastIndexOf("."))+"_zip"+newFileName.substring(newFileName.lastIndexOf("."));
			log.info("压缩原图片文件大小,压缩后文件名：{}",newFileName);
			log.info("原图片宽：{} 高:{}" ,width,height );
			String zipFilePath=sourceFilePath.substring(0, sourceFilePath.lastIndexOf("/")+1);
			log.info("压缩文件路径:{}",zipFilePath + newFileName);
			File newFile = new File(zipFilePath + newFileName);
			if (!newFile.getParentFile().exists())
				newFile.getParentFile().mkdirs();

			if (width > IMAGE_SIZE && height > IMAGE_SIZE) {
				Thumbnails.of(sourceFilePath).scale(0.2f).toFile(newFile);
			} else {
				Thumbnails.of(sourceFilePath).size(400, 500).toFile(newFile);
			}
			return newFileName;
		} catch (Exception e) {
			log.error("压缩图片异常:" + e.getMessage());
			throw new BusinessException("压缩图片异常:" + e.getMessage());
		}
	}
	public static void main(String[] args) throws Exception {
		ImgCompress imag=new ImgCompress("f:/1.jpg");
		imag.resize();
	}
}
