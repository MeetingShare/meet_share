/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50622
Source Host           : localhost:3306
Source Database       : car

Target Server Type    : MYSQL
Target Server Version : 50622
File Encoding         : 65001

Date: 2017-05-16 18:35:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for car_accident_compensation
-- ----------------------------
DROP TABLE IF EXISTS `car_accident_compensation`;
CREATE TABLE `car_accident_compensation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '会员ID',
  `type` char(1) DEFAULT '1' COMMENT '1-单车事故 2-多车事故',
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `front_img` varchar(255) DEFAULT NULL COMMENT '侧前方照片',
  `rear_img` varchar(255) DEFAULT NULL COMMENT '侧后方照片',
  `ower_full_img` varchar(255) DEFAULT NULL COMMENT '我方车全景',
  `other_full_img` varchar(255) DEFAULT NULL COMMENT '对方车全景',
  `touch_part` varchar(255) DEFAULT NULL COMMENT '碰撞部位',
  `other_img` varchar(255) DEFAULT NULL COMMENT '其他现场照片',
  `status` char(1) DEFAULT '0' COMMENT '状态 0-未处理 1-处理中 2-处理失败 3-处理i成功',
  `remark` varchar(255) DEFAULT NULL COMMENT '补充说明',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '提交时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car_accident_compensation
-- ----------------------------
INSERT INTO `car_accident_compensation` VALUES ('1', '1', '1', '213', '123', 'http://www.baidu.com', 'http://www.baidu.com', 'http://www.baidu.com', 'http://www.baidu.com', 'http://www.baidu.com', 'http://www.baidu.com', '0', 'sfasdfa', '2017-05-16 17:41:53');

-- ----------------------------
-- Table structure for car_driver_info
-- ----------------------------
DROP TABLE IF EXISTS `car_driver_info`;
CREATE TABLE `car_driver_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '添加会员ID',
  `name` varchar(255) DEFAULT NULL COMMENT '驾驶人姓名',
  `card_no` int(11) DEFAULT NULL COMMENT '证件号',
  `file_number` int(11) DEFAULT NULL COMMENT '档案编号',
  `to_license_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '领证日期',
  `idcard_img` varchar(255) DEFAULT NULL COMMENT '身份证照片',
  `driver_img` varchar(255) DEFAULT NULL COMMENT '驾驶证照片',
  `driver_copy_img` varchar(255) DEFAULT NULL COMMENT '驾驶证副本照片',
  `is_defaul` char(1) DEFAULT '0' COMMENT '0-非默认 1-默认',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `is_delete` char(1) DEFAULT '0' COMMENT '0-正常 1-逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car_driver_info
-- ----------------------------
INSERT INTO `car_driver_info` VALUES ('1', '2', '测试', '123123', '1900192873', '2017-05-15 14:49:09', 'http://www.baidu.com', 'http://www.baidu.com', 'http://www.baidu.com', '1', '2017-05-15 14:49:09', '0');
INSERT INTO `car_driver_info` VALUES ('2', '2', '测试1', '123124134', '1891043827', '2017-05-15 14:49:27', 'http://www.baidu.com', 'http://www.baidu.com', 'http://www.baidu.com', '0', '2017-05-15 14:49:27', '0');

-- ----------------------------
-- Table structure for car_info
-- ----------------------------
DROP TABLE IF EXISTS `car_info`;
CREATE TABLE `car_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '会员用户ID',
  `plate_number` varchar(12) NOT NULL COMMENT '车牌号',
  `engine_number` varchar(50) NOT NULL COMMENT '发动机号',
  `chassis_number` varchar(50) NOT NULL COMMENT '车架号',
  `resiger_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP COMMENT '注册时间',
  `status` char(1) DEFAULT '0' COMMENT '状态 0-启用 1-禁用',
  `is_default` char(1) DEFAULT '0' COMMENT '是否默认 1-默认 0-不默认',
  `is_delete` char(1) DEFAULT '0' COMMENT '是否删除 0-不删除 1-物理删除',
  `image_url` varchar(255) DEFAULT NULL COMMENT '车辆图片',
  `gmt_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_plate_number` (`plate_number`),
  UNIQUE KEY `index_engine_number` (`engine_number`),
  KEY `index_uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car_info
-- ----------------------------
INSERT INTO `car_info` VALUES ('1', '2', '京A.5U87', '123123123', '123123123', '2017-05-15 14:04:01', '0', '0', '1', null, '2017-05-15 14:04:01');
INSERT INTO `car_info` VALUES ('2', '2', 'asda', 'asda', 'asd', '2017-05-15 13:48:17', '1', '1', '0', null, '2017-05-15 13:48:17');

-- ----------------------------
-- Table structure for car_insuranage
-- ----------------------------
DROP TABLE IF EXISTS `car_insuranage`;
CREATE TABLE `car_insuranage` (
  `id` int(11) NOT NULL,
  `insured_ctiy` varchar(50) DEFAULT NULL COMMENT '投保城市',
  `car_id` int(11) DEFAULT NULL COMMENT '车牌ID',
  `driver_name` varchar(15) DEFAULT NULL COMMENT '车主姓名',
  `id_card` varchar(18) DEFAULT NULL COMMENT '身份证号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car_insuranage
-- ----------------------------

-- ----------------------------
-- Table structure for car_maintain_log
-- ----------------------------
DROP TABLE IF EXISTS `car_maintain_log`;
CREATE TABLE `car_maintain_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `car_id` int(11) DEFAULT NULL COMMENT '车辆ID',
  `name` varchar(15) DEFAULT NULL COMMENT '联系人姓名',
  `phone` varchar(18) DEFAULT NULL COMMENT '联系人电话',
  `driving_mileage` double DEFAULT NULL COMMENT '行驶里程',
  `descri` varchar(255) DEFAULT NULL COMMENT '描述',
  `image_url` varchar(255) DEFAULT NULL COMMENT '图pain',
  `status` char(1) DEFAULT '0' COMMENT '状态 0-未处理 1-处理中 -2 处理成功 3-处理失败',
  `is_delete` char(1) DEFAULT '0' COMMENT '是否逻辑删除 0正常 1-逻辑删除',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car_maintain_log
-- ----------------------------

-- ----------------------------
-- Table structure for car_news
-- ----------------------------
DROP TABLE IF EXISTS `car_news`;
CREATE TABLE `car_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text COMMENT '新闻内容',
  `image_url` varchar(255) DEFAULT NULL COMMENT '新闻图片',
  `title` varchar(100) DEFAULT NULL COMMENT '新闻标题',
  `uid` int(11) DEFAULT NULL COMMENT '添加用户',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car_news
-- ----------------------------
INSERT INTO `car_news` VALUES ('2', '啊打发手动阀全额退全额退', 'http://localhost/upload/busi/a95d83a6-1dd1-4d5d-bac5-caa8b56ca9ba.png', '阿迪斯发士大夫', '2', '2017-04-24 20:43:15');

-- ----------------------------
-- Table structure for car_violation
-- ----------------------------
DROP TABLE IF EXISTS `car_violation`;
CREATE TABLE `car_violation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL COMMENT '违章路段',
  `reason` varchar(255) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL COMMENT '违章扣款金额',
  `score` int(11) DEFAULT NULL COMMENT '违章扣分',
  `violation_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '违章时间',
  `car_id` int(11) DEFAULT NULL COMMENT '车牌号',
  `is_delete` char(1) DEFAULT '0' COMMENT '是否逻辑删除 0-正常 1-逻辑删除',
  `is_handle` char(1) DEFAULT '0' COMMENT '0-未处理 1-待处理 2-已处理 3-处理失败',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `index_plate_number` (`car_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car_violation
-- ----------------------------
INSERT INTO `car_violation` VALUES ('1', '北京丰台犄角旮旯', '扣的就是你咋地吧', '500', '12', '2017-04-26 17:01:10', '2', '0', '0', '2017-04-26 17:01:10');
INSERT INTO `car_violation` VALUES ('2', '你自己知道你去哪了', '无所谓了', '900', '18', '2017-04-26 17:02:33', '2', '0', '0', '2017-04-26 17:02:33');

-- ----------------------------
-- Table structure for car_violation_handle_log
-- ----------------------------
DROP TABLE IF EXISTS `car_violation_handle_log`;
CREATE TABLE `car_violation_handle_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `car_id` int(11) DEFAULT NULL COMMENT '车辆ID',
  `violation_id` varchar(255) DEFAULT NULL COMMENT '违章ID，用“，”分开',
  `driver_id` varchar(255) DEFAULT NULL COMMENT '驾驶人ID，用“，”分开',
  `gmt_create` varchar(255) DEFAULT NULL COMMENT '处理时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of car_violation_handle_log
-- ----------------------------

-- ----------------------------
-- Table structure for life_insurance
-- ----------------------------
DROP TABLE IF EXISTS `life_insurance`;
CREATE TABLE `life_insurance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '保险名称',
  `descri` varchar(255) DEFAULT NULL COMMENT '描述',
  `img_url` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `company` varchar(50) DEFAULT NULL COMMENT '所属保险公司',
  `start_amount` decimal(10,0) DEFAULT NULL COMMENT '保费起底金额',
  `service_phone` varchar(15) DEFAULT NULL COMMENT '服务电话',
  `protection_length` int(11) DEFAULT NULL COMMENT '保证天数 以天为单位',
  `claims_process` text COMMENT '索赔流程',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of life_insurance
-- ----------------------------
INSERT INTO `life_insurance` VALUES ('1', '10元报平安', '人寿保险', 'http://www.baidu.com', '人寿保险', '10', '9559', '365', '阿克苏的好伐哦史蒂夫哈发', '2017-05-16 15:06:19');

-- ----------------------------
-- Table structure for life_insurance_extend
-- ----------------------------
DROP TABLE IF EXISTS `life_insurance_extend`;
CREATE TABLE `life_insurance_extend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount` int(11) DEFAULT NULL COMMENT '保额',
  `content` varchar(255) DEFAULT NULL COMMENT '保障内容',
  `rate_amount` decimal(10,2) DEFAULT NULL COMMENT '费用',
  `lafe_id` int(11) DEFAULT NULL COMMENT '保险ID',
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of life_insurance_extend
-- ----------------------------
INSERT INTO `life_insurance_extend` VALUES ('1', '10', '啊实打实', '4.50', '1', '2017-04-27 17:43:07');
INSERT INTO `life_insurance_extend` VALUES ('2', '50', 'a', '5.00', '1', '2017-04-27 17:43:06');
INSERT INTO `life_insurance_extend` VALUES ('3', '100', 'asda', '15.00', '1', '2017-04-27 17:43:05');

-- ----------------------------
-- Table structure for life_order
-- ----------------------------
DROP TABLE IF EXISTS `life_order`;
CREATE TABLE `life_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `life_id` int(11) DEFAULT NULL COMMENT '保险ID',
  `life_extend_id` int(11) DEFAULT NULL,
  `effective_date` varchar(22) DEFAULT NULL COMMENT '生效日期',
  `uid` int(11) DEFAULT NULL COMMENT '投保人ID',
  `state` char(1) DEFAULT '0' COMMENT '0-已下单 1-已投保 2-未生效 3-已生效 4-已过期',
  `order_no` varchar(55) DEFAULT NULL,
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '投保日期',
  PRIMARY KEY (`id`),
  KEY `index_state` (`state`),
  KEY `index_uid` (`uid`),
  KEY `index_lifeid` (`life_id`),
  KEY `index_lifeextend_id` (`life_extend_id`),
  KEY `index_orderno` (`order_no`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of life_order
-- ----------------------------
INSERT INTO `life_order` VALUES ('1', '1', '2', '2017-04-27 17:46:16', '2', '0', '12312313', '2017-04-27 17:46:16');
INSERT INTO `life_order` VALUES ('7', '1', '1', '2017-09-01', '2', '0', '20170516163102573211', '2017-05-16 16:31:02');

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `account` varchar(15) DEFAULT NULL COMMENT '用户账户名-手机号唯一',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '状态-1禁用 0-启用',
  `active` char(1) DEFAULT '0' COMMENT '0-未锁定 1-锁定',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `lastmodify_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '最后更改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `Index_account` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='前段用户表';

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('2', '18910432851', '123', '0', '0', '2017-02-28 17:20:24', '2017-02-28 17:20:24');
INSERT INTO `member` VALUES ('3', '18912435851', '2', '0', '0', '2017-03-01 15:59:25', '2017-03-01 15:59:25');
INSERT INTO `member` VALUES ('4', '18912437851', '3', '0', '1', '2017-03-01 16:09:29', '2017-03-01 16:09:29');

-- ----------------------------
-- Table structure for member_attention_new
-- ----------------------------
DROP TABLE IF EXISTS `member_attention_new`;
CREATE TABLE `member_attention_new` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `news_id` int(11) DEFAULT NULL,
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member_attention_new
-- ----------------------------
INSERT INTO `member_attention_new` VALUES ('1', '2', '2', '2017-05-12 14:50:35');
INSERT INTO `member_attention_new` VALUES ('2', '3', '2', '2017-05-12 14:50:35');
INSERT INTO `member_attention_new` VALUES ('3', '4', '2', '2017-05-12 15:30:43');

-- ----------------------------
-- Table structure for member_extend
-- ----------------------------
DROP TABLE IF EXISTS `member_extend`;
CREATE TABLE `member_extend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL COMMENT '用户ID主键',
  `nick_name` varchar(32) DEFAULT NULL COMMENT '昵称',
  `descri` varchar(55) DEFAULT NULL COMMENT '介绍',
  `head_img` varchar(255) DEFAULT NULL COMMENT '头像',
  `sex` char(1) DEFAULT '1' COMMENT '性别1-男2-女',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `birthday` varchar(10) DEFAULT NULL COMMENT '生日',
  `real_name` varchar(15) DEFAULT NULL COMMENT '真实姓名',
  `id_card_no` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `index_uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member_extend
-- ----------------------------
INSERT INTO `member_extend` VALUES ('1', '2', 'asdfasd', '阿凡达的说法都是发士大夫', 'http://www.baidu.com', '2', '阿斯顿发射点发生', '20123840', '张三', '130312827257383', '2017-05-08 20:55:20');

-- ----------------------------
-- Table structure for member_login_log
-- ----------------------------
DROP TABLE IF EXISTS `member_login_log`;
CREATE TABLE `member_login_log` (
  `id` int(11) NOT NULL,
  `uid` int(11) DEFAULT NULL COMMENT '用户ID',
  `login_ip` varchar(15) DEFAULT NULL,
  `login_type` char(1) DEFAULT '0' COMMENT '0-账号密码登陆 1-短信验证吗登陆',
  `login_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '登陆时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member_login_log
-- ----------------------------

-- ----------------------------
-- Table structure for member_rescue_log
-- ----------------------------
DROP TABLE IF EXISTS `member_rescue_log`;
CREATE TABLE `member_rescue_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member_rescue_log
-- ----------------------------
INSERT INTO `member_rescue_log` VALUES ('1', '1', '2017-05-16 14:05:51');

-- ----------------------------
-- Table structure for member_sendcode
-- ----------------------------
DROP TABLE IF EXISTS `member_sendcode`;
CREATE TABLE `member_sendcode` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `phone` varchar(15) DEFAULT NULL COMMENT '手机号',
  `code` varchar(10) DEFAULT NULL COMMENT '验证码',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `editor_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '编辑时间',
  `remark` varchar(55) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `Index_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户发送验证码表';

-- ----------------------------
-- Records of member_sendcode
-- ----------------------------
INSERT INTO `member_sendcode` VALUES ('1', '18910432851', '184561', '2017-02-28 17:13:48', '2017-05-08 17:18:02', null);
INSERT INTO `member_sendcode` VALUES ('2', '18912435851', '547479', '2017-03-01 15:58:52', '2017-03-01 15:58:53', null);
INSERT INTO `member_sendcode` VALUES ('3', '18912437851', '937885', '2017-03-01 16:09:22', '2017-03-01 16:09:23', null);
INSERT INTO `member_sendcode` VALUES ('4', '18910432965', '798129', '2017-05-08 16:34:38', '2017-05-08 16:34:38', null);
INSERT INTO `member_sendcode` VALUES ('5', null, '689452', '2017-05-08 17:18:20', '2017-05-08 17:18:20', null);
INSERT INTO `member_sendcode` VALUES ('6', null, '348306', '2017-05-08 17:19:18', '2017-05-08 17:19:18', null);

-- ----------------------------
-- Table structure for refuel_card
-- ----------------------------
DROP TABLE IF EXISTS `refuel_card`;
CREATE TABLE `refuel_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_no` varchar(50) DEFAULT NULL COMMENT '加油卡号',
  `member_id` int(11) DEFAULT NULL COMMENT '所属会员',
  `is_default` char(1) DEFAULT '0' COMMENT '是否默认0-正常 1-默认',
  `is_delete` char(1) DEFAULT '0' COMMENT '是否删除 0正常 1-删除',
  `last_modify_date` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of refuel_card
-- ----------------------------
INSERT INTO `refuel_card` VALUES ('1', '12412434353453145', '2', '0', '0', '2017-05-16 14:26:16', '2017-05-16 14:26:16');
INSERT INTO `refuel_card` VALUES ('2', '34534534', '3', '0', '0', '2017-05-16 14:26:17', '2017-05-16 14:26:17');
INSERT INTO `refuel_card` VALUES ('3', '4634563456345634563', '2', '0', '0', '2017-05-16 14:26:18', '2017-05-16 14:26:18');

-- ----------------------------
-- Table structure for refuel_card_recharge_log
-- ----------------------------
DROP TABLE IF EXISTS `refuel_card_recharge_log`;
CREATE TABLE `refuel_card_recharge_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `card_id` int(11) DEFAULT NULL COMMENT '加油卡ID',
  `recharge_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '充值日期',
  `recharge_amount` double DEFAULT '0' COMMENT '充值金额',
  `is_delete` char(1) DEFAULT '0' COMMENT '逻辑删除 0-正常 1-逻辑删除',
  `last_modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of refuel_card_recharge_log
-- ----------------------------
INSERT INTO `refuel_card_recharge_log` VALUES ('1', '1', '2017-05-05 15:36:08', '100', '1', '2017-05-05 15:36:09', '2017-05-05 15:16:04');
INSERT INTO `refuel_card_recharge_log` VALUES ('2', '2', '2017-05-05 15:16:07', '200', '0', null, '2017-05-05 15:16:04');
INSERT INTO `refuel_card_recharge_log` VALUES ('3', '3', '2017-05-05 15:16:08', '5000', '0', null, '2017-05-05 15:16:04');
INSERT INTO `refuel_card_recharge_log` VALUES ('4', '1', '2017-05-05 15:16:08', '3000', '0', null, '2017-05-05 15:16:04');
INSERT INTO `refuel_card_recharge_log` VALUES ('5', '1', '2017-05-05 15:16:08', '4543', '0', null, '2017-05-05 15:16:04');
INSERT INTO `refuel_card_recharge_log` VALUES ('6', '1', '2017-05-05 15:16:09', '333', '0', null, '2017-05-05 15:16:04');
INSERT INTO `refuel_card_recharge_log` VALUES ('7', '2', '2017-05-05 15:16:09', '345', '0', null, '2017-05-05 15:16:04');
INSERT INTO `refuel_card_recharge_log` VALUES ('8', '3', '2017-05-05 15:16:10', '567', '0', null, '2017-05-05 15:16:04');
INSERT INTO `refuel_card_recharge_log` VALUES ('9', '1', null, '200', '0', null, '2017-05-16 14:53:10');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '模块ID',
  `f_id` int(11) unsigned zerofill DEFAULT NULL COMMENT '上级模块ID',
  `name` varchar(55) COLLATE utf8_bin DEFAULT NULL COMMENT '模块名称',
  `controller_url` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '控制器地址',
  `weight` int(11) DEFAULT NULL COMMENT '权重',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '00000000000', '系统管理', '#', '1', '2015-09-14 21:19:58');
INSERT INTO `sys_permission` VALUES ('9', '00000000001', '用户管理', 'admin/index', '100', '2017-03-05 11:47:14');
INSERT INTO `sys_permission` VALUES ('10', '00000000001', '角色管理', 'role/index', '99', '2015-08-13 15:26:24');
INSERT INTO `sys_permission` VALUES ('11', '00000000001', '模块管理', 'module/index', '98', '2015-08-13 15:26:26');
INSERT INTO `sys_permission` VALUES ('20', '00000000000', '会员管理', '#', '1', '2017-03-17 20:19:56');
INSERT INTO `sys_permission` VALUES ('21', '00000000020', '会员列表', 'member/list', '2', '2017-04-23 16:49:07');
INSERT INTO `sys_permission` VALUES ('23', '00000000000', '资讯管理', '#', '3', '2017-04-24 20:33:46');
INSERT INTO `sys_permission` VALUES ('24', '00000000023', '资讯列表', 'news/list', '1', '2017-04-24 20:34:06');
INSERT INTO `sys_permission` VALUES ('25', '00000000000', '车辆管理', '#', '4', '2017-04-24 20:44:17');
INSERT INTO `sys_permission` VALUES ('26', '00000000025', '车辆列表', 'carInfo/list', '1', '2017-04-24 21:11:40');
INSERT INTO `sys_permission` VALUES ('27', '00000000000', '违章管理', '#', '5', '2017-04-26 15:51:06');
INSERT INTO `sys_permission` VALUES ('28', '00000000027', '违章统计', 'carViolation/list', '1', '2017-04-26 16:45:50');
INSERT INTO `sys_permission` VALUES ('29', '00000000027', '违章列表', 'carViolation/vlist', '2', '2017-04-26 16:46:12');
INSERT INTO `sys_permission` VALUES ('30', '00000000000', '寿险管理', '#', '7', '2017-04-27 17:39:51');
INSERT INTO `sys_permission` VALUES ('31', '00000000030', '订单列表', 'life/list', '1', '2017-04-27 17:40:07');
INSERT INTO `sys_permission` VALUES ('32', '00000000000', '加油卡管理', '#', '8', '2017-05-02 18:16:16');
INSERT INTO `sys_permission` VALUES ('33', '00000000032', '加油卡列表', 'refuelCard/list', '1', '2017-05-02 18:16:39');
INSERT INTO `sys_permission` VALUES ('34', '00000000032', '充值记录', 'recharge/list', '2', '2017-05-05 15:01:59');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(55) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', '2015-02-05 18:59:18');
INSERT INTO `sys_role` VALUES ('2', '审核人员', '2017-03-09 11:01:44');
INSERT INTO `sys_role` VALUES ('3', '测试人员', '2017-03-09 11:01:49');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `permission_id` int(11) DEFAULT NULL COMMENT '模块ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=450 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('341', '4', '1');
INSERT INTO `sys_role_permission` VALUES ('342', '4', '9');
INSERT INTO `sys_role_permission` VALUES ('343', '4', '10');
INSERT INTO `sys_role_permission` VALUES ('344', '4', '11');
INSERT INTO `sys_role_permission` VALUES ('432', '1', '1');
INSERT INTO `sys_role_permission` VALUES ('433', '1', '9');
INSERT INTO `sys_role_permission` VALUES ('434', '1', '10');
INSERT INTO `sys_role_permission` VALUES ('435', '1', '11');
INSERT INTO `sys_role_permission` VALUES ('436', '1', '20');
INSERT INTO `sys_role_permission` VALUES ('437', '1', '21');
INSERT INTO `sys_role_permission` VALUES ('438', '1', '23');
INSERT INTO `sys_role_permission` VALUES ('439', '1', '24');
INSERT INTO `sys_role_permission` VALUES ('440', '1', '25');
INSERT INTO `sys_role_permission` VALUES ('441', '1', '26');
INSERT INTO `sys_role_permission` VALUES ('442', '1', '27');
INSERT INTO `sys_role_permission` VALUES ('443', '1', '28');
INSERT INTO `sys_role_permission` VALUES ('444', '1', '29');
INSERT INTO `sys_role_permission` VALUES ('445', '1', '30');
INSERT INTO `sys_role_permission` VALUES ('446', '1', '31');
INSERT INTO `sys_role_permission` VALUES ('447', '1', '32');
INSERT INTO `sys_role_permission` VALUES ('448', '1', '33');
INSERT INTO `sys_role_permission` VALUES ('449', '1', '34');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(55) COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `password` varchar(55) COLLATE utf8_bin NOT NULL COMMENT '密码',
  `datetime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `active` char(1) COLLATE utf8_bin NOT NULL DEFAULT '0' COMMENT '用户状态 0-启用 1-禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('2', 'admin', '1', '2017-04-23 16:50:41', '1');
INSERT INTO `sys_user` VALUES ('3', 'baizhixing', 'baizhixing', '2017-04-23 13:33:13', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('2', '37', '1', '2017-03-09 14:31:55');
INSERT INTO `sys_user_role` VALUES ('4', '2', '1', '2017-04-23 12:26:43');
INSERT INTO `sys_user_role` VALUES ('5', '2', '3', '2017-04-23 12:26:43');
INSERT INTO `sys_user_role` VALUES ('11', '3', '2', '2017-04-23 13:33:13');
