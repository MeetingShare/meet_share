package com.meet.orm.service;

import com.github.pagehelper.PageInfo;
import com.meet.dto.req.MeetSupportFacilitiesReq;
import com.meet.orm.pojo.MeetSupportFacilities;

import java.util.List;

/**
 * Created by bzhx on 2017-11-04 9:55.
 */
public interface MeetSupportFacilitiesService {

    /**
     * 获取主题列表基于分页
     * @param reqInfo
     * @return
     */
    PageInfo<MeetSupportFacilities> findMeetSupportFacilitiesListPage(MeetSupportFacilitiesReq reqInfo);

    /**
     * 获取主题列表
     * @param reqInfo
     * @return
     */
    List<MeetSupportFacilities> findMeetSupportFacilitiesList(MeetSupportFacilitiesReq reqInfo);

    /**
     * 添加主题
     *
     * @param reqInfo
     * @return
     * @throws Exception
     */
    int saveData(MeetSupportFacilitiesReq reqInfo) throws Exception;

    /**
     * 刪除用戶
     */
    int delData(int id) throws Exception;

    /**
     * 更新用戶
     */
    void updateData(MeetSupportFacilitiesReq reqInfo) throws Exception;

    /**
     * 获取主题根据主题编号
     */
    MeetSupportFacilities selectInfoById(int id);
}