package com.meet.wechat.api;

import com.alibaba.fastjson.JSON;
import com.meet.common.constants.MeetConstants;
import com.meet.wechat.client.LocalHttpClient;
import com.meet.wechat.common.MapUtil;
import com.meet.wechat.common.SignatureUtil;
import com.meet.wechat.common.XMLConverUtil;
import com.meet.wechat.pojo.QrcodeTicket;
import com.meet.wechat.pojo.User;
import com.meet.wechat.pojo.menu.Menu;
import com.meet.wechat.pojo.menu.MenuButtons;
import com.meet.wechat.pojo.pay.Unifiedorder;
import com.meet.wechat.pojo.pay.UnifiedorderResult;
import com.meet.wechat.pojo.template.TemplateMessage;
import com.meet.wechat.pojo.template.TemplateMessageResult;
import com.meet.wechat.pojo.token.BaseResult;
import com.meet.wechat.pojo.token.SnsToken;
import com.meet.wechat.pojo.token.Token;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * @Title: WechatApi.java
 * @Package com.order.wechat.api
 * @Description: TODO(微信API接口)
 * @author baizhixing
 * @date 2017年5月22日 下午3:43:34
 * @version V1.0
 */
public class WechatApi {

    protected static Header jsonHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
    protected static Header xmlHeader = new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_XML.toString());

    /**
     * 获取用户openId
     *
     * @param code
     * @return
     */
    public static SnsToken oauth2AccessToken(String code) {
        HttpUriRequest httpUriRequest = RequestBuilder.post().setUri(MeetConstants.WECHAT_URL + "sns/oauth2/access_token").addParameter("appid", MeetConstants.WECHAT_APPID)
                .addParameter("secret", MeetConstants.WECHAT_SECRET).addParameter("code", code).addParameter("grant_type", "authorization_code").build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, SnsToken.class);
    }

    /**
     * 获取access_token
     * @return
     */
    public static Token token() {
        HttpUriRequest httpUriRequest = RequestBuilder.get().setUri(MeetConstants.WECHAT_URL + "cgi-bin/token").addParameter("grant_type", "client_credential")
                .addParameter("appid", MeetConstants.WECHAT_APPID).addParameter("secret", MeetConstants.WECHAT_SECRET).build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, Token.class);
    }
    /**
     * 拉取用户信息(需scope为 snsapi_userinfo)
     * @param access_token
     * @param openid
     * @param lang 国家地区语言版本，zh_CN 简体，zh_TW 繁体，en 英语
     * @return
     */
    public static User userinfo(String access_token, String openid, String lang){
        HttpUriRequest httpUriRequest = RequestBuilder.post()
                .setUri(MeetConstants.WECHAT_URL + "/sns/userinfo")
                .addParameter("access_token", access_token)
                .addParameter("openid", openid)
                .addParameter("lang", lang)
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest,User.class);
    }
    /**
     * 创建持久二维码
     *
     * @param access_token
     * @param scene_id
     *            场景值ID 1-100000
     * @return
     */
    public static QrcodeTicket qrcodeCreateFinal(String access_token, int scene_id) {
        return qrcodeCreate(access_token, null, "QR_LIMIT_SCENE", scene_id);
    }

    /**
     * 创建菜单
     *
     * @param access_token
     * @param menuJson
     *            菜单json 数据 例如{\
     *            "button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\"},{\"type\":\"click\",\"name\":\"歌手简介\",\"key\":\"V1001_TODAY_SINGER\"},{\"name\":\"菜单\",\"sub_button\":[{\"type\":\"view\",\"name\":\"搜索\",\"url\":\"http://www.soso.com/\"},{\"type\":\"view\",\"name\":\"视频\",\"url\":\"http://v.qq.com/\"},{\"type\":\"click\",\"name\":\"赞一下我们\",\"key\":\"V1001_GOOD\"}]}]
     *            }
     * @return
     */
    public static BaseResult menuCreate(String access_token, String menuJson) {
        HttpUriRequest httpUriRequest = RequestBuilder.post().setHeader(jsonHeader).setUri(MeetConstants.WECHAT_URL + "cgi-bin/menu/create")
                .addParameter("access_token", access_token).setEntity(new StringEntity(menuJson, Charset.forName("utf-8"))).build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, BaseResult.class);
    }

    /**
     * 创建菜单
     *
     * @param access_token
     * @param menuButtons
     * @return
     */
    public static BaseResult menuCreate(String access_token, MenuButtons menuButtons) {
        String str = JSON.toJSONString(menuButtons);
        System.out.println(str);
        return menuCreate(access_token, str);
    }

    /**
     * 获取菜单
     *
     * @param access_token
     * @return
     */
    public static Menu menuGet(String access_token) {
        HttpUriRequest httpUriRequest = RequestBuilder.post().setUri(MeetConstants.WECHAT_URL + "/cgi-bin/menu/get").addParameter("access_token", access_token).build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, Menu.class);
    }

    /**
     * 删除菜单
     *
     * @param access_token
     * @return
     */
    public static BaseResult menuDelete(String access_token) {
        HttpUriRequest httpUriRequest = RequestBuilder.post().setUri(MeetConstants.WECHAT_URL + "cgi-bin/menu/delete").addParameter("access_token", access_token).build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, BaseResult.class);
    }

    /**
     * 统一下单
     *
     * @param unifiedorder
     * @param key
     * @return
     */
    public static UnifiedorderResult payUnifiedorder(Unifiedorder unifiedorder, String key) {
        Map<String, String> map = MapUtil.objectToMap(unifiedorder);
        if (key != null) {
            String sign = SignatureUtil.generateSign(map, key);
            unifiedorder.setSign(sign);
        }
        String unifiedorderXML = XMLConverUtil.convertToXML(unifiedorder);
        HttpUriRequest httpUriRequest = RequestBuilder.post().setHeader(xmlHeader).setUri(MeetConstants.WECHAT_PAY_URL + "/pay/unifiedorder")
                .setEntity(new StringEntity(unifiedorderXML, Charset.forName("utf-8"))).build();
        return LocalHttpClient.executeXmlResult(httpUriRequest, UnifiedorderResult.class);
    }

    /**
     * 创建二维码
     *
     * @param access_token
     * @param qrcodeJson
     *            json 数据 例如{"expire_seconds": 1800, "action_name": "QR_SCENE",
     *            "action_info": {"scene": {"scene_id": 123}}}
     * @return
     */
    private static QrcodeTicket qrcodeCreate(String access_token, String qrcodeJson) {
        System.out.println(qrcodeJson);
        HttpUriRequest httpUriRequest = RequestBuilder.post().setHeader(jsonHeader).setUri(MeetConstants.WECHAT_URL + "cgi-bin/qrcode/create")
                .addParameter("access_token", access_token).setEntity(new StringEntity(qrcodeJson, Charset.forName("utf-8"))).build();
        return LocalHttpClient.executeJsonResult(httpUriRequest, QrcodeTicket.class);
    }

    /**
     * 创建二维码
     *
     * @param access_token
     * @param expire_seconds
     *            该二维码有效时间，以秒为单位。 最大不超过1800秒。
     * @param action_name
     *            二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久
     * @param scene_id
     *            场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     * @return
     */
    private static QrcodeTicket qrcodeCreate(String access_token, Integer expire_seconds, String action_name, long scene_id) {
        return qrcodeCreate(
                access_token,
                String.format("{" + (expire_seconds == null ? "%1$s" : "\"expire_seconds\": %1$s, ")
                        + "\"action_name\": \"%2$s\", \"action_info\": {\"scene\": {\"scene_id\": %3$d}}}", expire_seconds == null ? "" : expire_seconds, action_name, scene_id));
    }

    /**
     * 模板消息发送
     * @param access_token
     * @param templateMessage
     * @return
     */
    public static TemplateMessageResult messageTemplateSend(String access_token, TemplateMessage templateMessage){
        String messageJson = JSON.toJSONString(templateMessage);
        HttpUriRequest httpUriRequest = RequestBuilder.post()
                .setHeader(jsonHeader)
                .setUri(MeetConstants.WECHAT_URL+"/cgi-bin/message/template/send")
                .addParameter("access_token", access_token)
                .setEntity(new StringEntity(messageJson,Charset.forName("utf-8")))
                .build();
        return LocalHttpClient.executeJsonResult(httpUriRequest,TemplateMessageResult.class);
    }
}
