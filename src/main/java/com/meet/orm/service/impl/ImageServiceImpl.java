package com.meet.orm.service.impl;

import com.meet.common.ImgCompress;
import com.meet.common.constants.MeetConstants;
import com.meet.common.resources.Resources;
import com.meet.dto.rsp.UploadFileResp;
import com.meet.exception.BusinessException;
import com.meet.orm.dao.ImageFileMapper;
import com.meet.orm.dao.MeetImageMapper;
import com.meet.orm.pojo.ImageFile;
import com.meet.orm.pojo.MeetImage;
import com.meet.orm.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;

@Service
public class ImageServiceImpl implements ImageService {
	private Logger logger = LoggerFactory.getLogger(MeetConstants.LOG_NAME);

	@Autowired
	ImageFileMapper imageMapper;
	@Autowired
	MeetImageMapper meetImageMapper;

	@Override
	@Transactional(rollbackFor=Exception.class)
	public UploadFileResp saveImage(String srcPath, String httpUrl, String fileName) throws BusinessException {
		// TODO Auto-generated method stub
		try {
			UploadFileResp resp = new UploadFileResp();
			// 解析imagePath
			File srcFile = new File(srcPath + fileName);
			String type = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
			logger.info("imageName:{} type:{} size:{}", fileName, type, srcFile.length());
			// 存储上传的源文件
			ImageFile data = new ImageFile();
			data.setName(fileName);
			data.setSize((int) srcFile.length());
			data.setPath(httpUrl);
			data.setHttpUrl(MeetConstants.IMAGE_URL);
			data.setAbsolutePath(MeetConstants.IMAGE_PATH);
			data.setType(type);
			// 读取图片大小
			Image image = ImageIO.read(srcFile);
			data.setWidth(image.getWidth(null));
			data.setHeight(image.getHeight(null));
			// 源文件进行压缩
			ImgCompress imgCompress = new ImgCompress(srcPath + fileName);
			data.setZipPath(httpUrl.substring(0, httpUrl.lastIndexOf("/")+1)+imgCompress.resize());
			imageMapper.insertSelective(data);
			resp.setFilePath(data.getHttpUrl() + data.getPath());
			resp.setImageId(data.getId());
			resp.setZipPath(data.getHttpUrl() + data.getZipPath());
			return resp;
		} catch (Exception e) {
			logger.error("保存图片异常:{}", e.getMessage());
			throw new IllegalArgumentException("保存图片异常:" + e.getMessage());
		}
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void delImageById(Integer imageId) throws BusinessException {
		// TODO Auto-generated method stub
		ImageFile image = imageMapper.selectByPrimaryKey(imageId);
		if (image == null) {
			throw new BusinessException(Resources.getMessage("del_fail"));
		}
		// 先删除文件
		String filePath = image.getAbsolutePath() + image.getPath();
		new File(filePath).deleteOnExit();
		String zipFile = image.getAbsolutePath() + image.getZipPath();
		new File(zipFile).deleteOnExit();
		// 删除数据记录
		MeetImage goodImage = meetImageMapper.selectByImageId(imageId);
		if (goodImage != null) {
			meetImageMapper.deleteByImageId(imageId);
		}
		imageMapper.deleteById(imageId);
	}

}
