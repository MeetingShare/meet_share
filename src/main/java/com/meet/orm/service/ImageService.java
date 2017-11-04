package com.meet.orm.service;

import com.meet.dto.rsp.UploadFileResp;

/**
* @Title: ImageService.java 
* @Package com.order.orm.service.back 
* @Description: TODO(图片上传业务处理) 
* @author baizhixing   
* @date 2017年5月18日 下午7:40:12 
* @version V1.0
 */
public interface ImageService {
	
	/**
	 * 图片上传服务
	 * @param srcPath 源文件地址
	 * @param httpUrl 请求url
	 * @param fileName 文件名称
	 * @return
	 * @throws Exception
	 */
	UploadFileResp saveImage(String srcPath, String httpUrl, String fileName) throws Exception;
    
    /**
     * 删除商品图片
     * @param imageId
     */
    void delImageById(Integer imageId) throws Exception;
}
