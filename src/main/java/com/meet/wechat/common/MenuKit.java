package com.meet.wechat.common;

import java.util.ArrayList;
import java.util.List;

import com.meet.wechat.pojo.menu.MenuButtons;
import com.meet.wechat.pojo.menu.MenuButtons.Button;
/**
 * 创建菜单
 * @author admin
 *
 */
public class MenuKit {
	/**
	 * 创建服务菜单
	 * @return
	 */
	protected  static Button createServiceMenu(){
		Button serviceButton=new Button();
		serviceButton.setKey("service");
		serviceButton.setName("用户注册");
		serviceButton.setType("view");
		serviceButton.setUrl("http://www.cbuzhihuo.com/order_system/api/open/gCode");
		return serviceButton;
	}
	/**
	 * 创建服务菜单
	 * @return
	 */
	protected  static Button createOrderMenu(){
		Button serviceButton=new Button();
		serviceButton.setKey("service1");
		serviceButton.setName("首页");
		serviceButton.setType("view");
		serviceButton.setUrl("http://cbuzhihuo.com/order_system/web/index.html");
		return serviceButton;
	}
	/**
	 * 创建服务菜单
	 * @return
	 */
	protected  static Button createSerMenu(){
		Button serviceButton=new Button();
		serviceButton.setKey("service2");
		serviceButton.setName("我的服务");
		serviceButton.setType("click");
		Button jhButton=new Button();
		jhButton.setKey("jh");
		jhButton.setName("链接1");
		jhButton.setType("view");
		jhButton.setUrl("http://www.dingzhita.com/dz/wx/server/index");
		Button bdButton=new Button();
		bdButton.setKey("bd");
		bdButton.setName("链接2");
		bdButton.setType("view");
		bdButton.setUrl("http://www.dingzhita.com/dz/wx/server/index1");
		Button bdButton3=new Button();
		bdButton.setKey("b2");
		bdButton.setName("链接3");
		bdButton.setType("view");
		bdButton.setUrl("http://www.dingzhita.com/dz/wx/server/index2");
		List<Button> jhButtonList=new ArrayList<Button>();
		jhButtonList.add(jhButton);
		jhButtonList.add(bdButton);
		jhButtonList.add(bdButton3);
		serviceButton.setSub_button(jhButtonList);
		return serviceButton;
	}
	/**
	 * 创建激活菜单 包括激活、绑定
	 * @return
	 */
	protected static Button createJHMenu(){
		Button jhButtons=new Button();
		jhButtons.setKey("jhs");
		jhButtons.setName("激活");
		jhButtons.setType("click");
		return jhButtons;
	}
	/**
	 * 创建个人中心菜单 包括：个人信息、我的产品、服务记录
	 * */
	protected static Button createPersonCenterMenu(){
		Button personCenterButtons=new Button();
		personCenterButtons.setKey("personCenter");
		personCenterButtons.setName("BOE");
		personCenterButtons.setType("click");
		//个人信息
		Button infoButton=new Button();
		infoButton.setKey("info");
		infoButton.setName("BOE集团");
		infoButton.setType("view");
		infoButton.setUrl("http://www.dingzhita.com/dz/wx/server/index2");
		//我的产品
		Button ourProductButton=new Button();
		ourProductButton.setKey("product");
		ourProductButton.setName("BOEPARK品牌");
		ourProductButton.setType("view");
		ourProductButton.setUrl("http://www.dingzhita.com/dz/wx/server/index2");
		//服务记录
		Button serviceRecordButton=new Button();
		serviceRecordButton.setKey("record");
		serviceRecordButton.setName("健康产业园区");
		serviceRecordButton.setType("view");
		serviceRecordButton.setUrl("http://www.dingzhita.com/dz/wx/server/index3");
		List<Button> personCenterButtonList=new ArrayList<Button>();
		personCenterButtonList.add(infoButton);
		personCenterButtonList.add(ourProductButton);
		personCenterButtonList.add(serviceRecordButton);
		personCenterButtons.setSub_button(personCenterButtonList);
		return personCenterButtons;
	}
	public static MenuButtons createButton(){
		Button[] buttons={createServiceMenu(),createOrderMenu()};
		MenuButtons menu=new MenuButtons();
		menu.setButton(buttons);
		return menu;
	}
}
