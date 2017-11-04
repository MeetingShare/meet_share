package com.meet.orm.dao;

import com.meet.orm.pojo.ImageFile;

public interface ImageFileMapper {
    int insert(ImageFile record);

    int insertSelective(ImageFile record);

    ImageFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ImageFile record);

    int updateByPrimaryKey(ImageFile record);

    int deleteById(Integer id);
}