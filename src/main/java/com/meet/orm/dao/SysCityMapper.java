package com.meet.orm.dao;

import com.meet.orm.pojo.SysCity;

import java.util.List;

public interface SysCityMapper {
    List<SysCity> selectByProvinceId(Integer ProvinceID);
}