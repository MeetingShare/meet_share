package com.meet.orm.service;

import com.github.pagehelper.PageInfo;
import com.meet.dto.req.SiteInfoReq;
import com.meet.orm.pojo.SysSiteInfo;

import java.util.List;

/**
 * Created by bzhx on 2017-11-04 14:41.
 */
public interface SiteInfoService {
    /**
     * 获取站点信息列表
     * @param reqInfo
     * @return
     */
    PageInfo<SysSiteInfo> findSiteInfoListPage(SiteInfoReq reqInfo);

    /**
     * 获取某个站点信息
     * @param type
     * @return
     */
    SysSiteInfo findSiteInfoByType(String type);

    /**
     * 添加站点
     *
     * @param reqInfo
     * @return
     * @throws Exception
     */
    int saveSiteInfo(SiteInfoReq reqInfo) throws Exception;

    /**
     * 刪除用戶
     */
    int delSiteInfo(int id) throws Exception;

    /**
     * 更新用戶
     */
    void updateSiteInfo(SiteInfoReq reqInfo) throws Exception;

    /**
     * 获取站点根据站点编号
     */
    SysSiteInfo selectSiteInfoById(int id);
}
