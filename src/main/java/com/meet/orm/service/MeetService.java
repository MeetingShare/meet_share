package com.meet.orm.service;

import com.github.pagehelper.PageInfo;
import com.meet.dto.req.MeetingInfoReq;
import com.meet.orm.pojo.MeetingInfo;

/**
 * Created by bzhx on 2017-11-04 9:55.
 */
public interface MeetService {

    /**
     * 获取会议室列表
     * @param reqInfo
     * @return
     */
    PageInfo<MeetingInfo> findMeetListPage(MeetingInfoReq reqInfo);

    /**
     * 添加会议室
     * @param reqInfo
     * @return
     * @throws Exception
     */
    int addMeet(MeetingInfoReq reqInfo) throws Exception;
    /**
     * 更新会议室
     * @param reqInfo
     * @return
     * @throws Exception
     */
    int updateMeet(MeetingInfoReq reqInfo) throws Exception;

    /**
     * 删除会议室，物理删除
     * @param id
     * @return
     * @throws Exception
     */
    int delMeet(Integer id) throws Exception;

    MeetingInfo getMeetById(Integer id);
}