package com.meet.orm.dao;

import com.meet.orm.pojo.SysDistrict;

import java.util.List;

public interface SysDistrictMapper {
    List<SysDistrict> selectByCityId(Integer CityID);
}