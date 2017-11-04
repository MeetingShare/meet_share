package com.meet.orm.service;

import com.meet.orm.pojo.SysCity;
import com.meet.orm.pojo.SysDistrict;
import com.meet.orm.pojo.SysProvince;

import java.util.List;

/**
* @Title: ApiCityService.java 
* @Package com.order.orm.service.api 
* @Description: TODO(城市相关service) 
* @author baizhixing   
* @date 2017年5月20日 下午8:20:48 
* @version V1.0
 */
public interface CityService {
	/**
	 * 获取所有省份
	 * @return
	 */
	List<SysProvince> findprovince();
	/**
	 * 根据省份获取市
	 * @param provinceId
	 * @return
	 */
	List<SysCity> findCityByProvince(Integer provinceId);
	/**
	 * 获取所有区县根据市
	 * @param cityId
	 * @return
	 */
	List<SysDistrict> findDistrictByCityId(Integer cityId);
}
