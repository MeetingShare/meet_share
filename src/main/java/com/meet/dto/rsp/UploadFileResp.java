package com.meet.dto.rsp;

import lombok.Data;

@Data
public class UploadFileResp extends ApiResponse{
	// 图片ID
	private int imageId;
	// 图片访问地址
	private String filePath;
	//压缩访问文件
	private String zipPath;
}