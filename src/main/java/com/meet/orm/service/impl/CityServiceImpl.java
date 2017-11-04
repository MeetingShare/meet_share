package com.meet.orm.service.impl;

import com.meet.orm.dao.SysCityMapper;
import com.meet.orm.dao.SysDistrictMapper;
import com.meet.orm.dao.SysProvinceMapper;
import com.meet.orm.pojo.SysCity;
import com.meet.orm.pojo.SysDistrict;
import com.meet.orm.pojo.SysProvince;
import com.meet.orm.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CityServiceImpl implements CityService {
	
	@Autowired
	SysProvinceMapper provinceMapper;
	@Autowired
	SysCityMapper cityMapper;
	@Autowired
	SysDistrictMapper districtMapper;

	@Override
	public List<SysProvince> findprovince() {
		// TODO Auto-generated method stub
		return provinceMapper.selectAll();
	}

	@Override
	public List<SysCity> findCityByProvince(Integer provinceId) {
		// TODO Auto-generated method stub
		return cityMapper.selectByProvinceId(provinceId);
	}

	@Override
	public List<SysDistrict> findDistrictByCityId(Integer cityId) {
		// TODO Auto-generated method stub
		return districtMapper.selectByCityId(cityId);
	}

}
