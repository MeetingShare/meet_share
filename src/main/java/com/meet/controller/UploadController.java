package com.meet.controller;

import com.meet.common.constants.MeetConstants;
import com.meet.common.date.DateUtil;
import com.meet.dto.rsp.UploadFileResp;
import com.meet.orm.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 
 * @Title: ApiUploadController.java
 * @Package com.car.controller.api
 * @Description: TODO(文件上传接口controller)
 * @author baizhixing
 * @date 2017年5月8日 下午10:58:35
 * @version V1.0
 */
@RestController
@RequestMapping("/api/upload")
public class UploadController extends BaseController {

	@Autowired
	private ImageService imageService;

	/**
	 * 头像上传接口
	 */
	@RequestMapping("/headImage")
	public Object imgUpload(@RequestParam("file") MultipartFile file) throws IOException {
		logger.info("头像上传：{}", file.getOriginalFilename());
		UploadFileResp resp = new UploadFileResp();
		String saveDirectory = MeetConstants.IMAGE_PATH + "/head/";
		logger.info("头像上传路径：{}", saveDirectory);
		File tempFile = new File(saveDirectory + file.getOriginalFilename());
		if (!tempFile.exists()) {
			tempFile.mkdirs();
		}
		file.transferTo(tempFile);
		// 重命名
		String id = UUID.randomUUID().toString();
		String newFile = id + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
		tempFile.renameTo(new File(saveDirectory + newFile));
		resp.setMsg("图片上传完成");
		logger.info("头像访问地址：{}", MeetConstants.IMAGE_URL + "head/" + newFile);
		resp.setFilePath(MeetConstants.IMAGE_URL + "head/" + newFile);
		resp.setCode(MeetConstants.SYS_SUCCESS);
		return resp;
	}
	
	/**
	 * 会议室图片上传接口
	 */
	@RequestMapping("/meetUpload")
	public Object meetUpload(@RequestParam("file") MultipartFile file) throws IOException {
		logger.info("店铺文件上传：{}", file.getOriginalFilename());
		UploadFileResp json = new UploadFileResp();
		String dateTime= DateUtil.format("yyyyMMdd");
		String saveDirectory = MeetConstants.IMAGE_PATH + "/meet/"+dateTime+"/";
		logger.info("文件上传路径：{}", saveDirectory);
		File tempFile = new File(saveDirectory + file.getOriginalFilename());
		if (!tempFile.exists()) {
			tempFile.mkdir();
		}
		file.transferTo(tempFile);
		// 重命名
		String id = UUID.randomUUID().toString();
		String newFileName = id + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length());
		File newFile = new File(saveDirectory + newFileName);
		tempFile.renameTo(newFile);
		try {
			logger.info("图片上传成功！");
			json = imageService.saveImage(saveDirectory, "meet/" +dateTime+"/"+ newFileName, newFileName);
			json.setMsg("图片上传完成");
			json.setCode(MeetConstants.SYS_SUCCESS);
		} catch (Exception e) {
			logger.error("图片上传失败：{}", e.getMessage());
			json.setMsg("图片上传失败");
			e.printStackTrace();
			json.setCode(MeetConstants.SYS_FAILE);
		}
		return json;
	}
}
