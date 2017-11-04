package com.meet.controller;

import com.meet.common.constants.MeetConstants;
import com.meet.dto.rsp.ApiResponse;
import com.meet.orm.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
* @Title: ApiCityController.java 
* @Package com.order.controller.api 
* @Description: TODO(城市地址管理) 
* @author baizhixing   
* @date 2017年5月20日 下午8:18:33 
* @version V1.0
 */
@RestController
@RequestMapping("/api/city")
public class CityController extends BaseController{
	
	@Autowired
	private CityService apiCityService;
	/**
	 * 获取所有省份
	 */
	@RequestMapping("/provinces")
	public Object provinces() {
		logger.info("获取所有省份");
		ApiResponse resp = new ApiResponse();
		resp.setCode(MeetConstants.API_RET_SUCCESS);
		resp.setMsg("数据获取成功");
		resp.setData(apiCityService.findprovince());
		return resp;
	}

	/**
	 * 获取所有城市根据省份ID
	 */
	@RequestMapping("/gcity/{provinceId}")
	public Object gcitys( @PathVariable("provinceId") Integer provinceId) {
		ApiResponse resp = new ApiResponse();
		resp.setCode(MeetConstants.API_RET_SUCCESS);
		resp.setMsg("数据获取成功");
		resp.setData(apiCityService.findCityByProvince(provinceId));
		return resp;
	}

	/**
	 * 获取所有省份
	 */
	@RequestMapping("/gdistricts/{cityId}")
	public Object gdistricts(@PathVariable("cityId") Integer cityId) {
		ApiResponse resp = new ApiResponse();
		resp.setCode(MeetConstants.API_RET_SUCCESS);
		resp.setMsg("数据获取成功");
		resp.setData(apiCityService.findDistrictByCityId(cityId));
		return resp;
	}
}
