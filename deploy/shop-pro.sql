/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50617
Source Host           : localhost:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 50617
File Encoding         : 65001

Date: 2015-01-25 21:45:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `address`
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父节点',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '城市名称',
  `priority` int(11) DEFAULT '10' COMMENT '排序',
  PRIMARY KEY (`Id`),
  KEY `fk_address_parent` (`parent_id`),
  CONSTRAINT `fk_address_parent` FOREIGN KEY (`parent_id`) REFERENCES `address` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='省、市、县表';

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address` VALUES ('1', null, '江西省', '1');
INSERT INTO `address` VALUES ('7', '1', '南昌', '10');
INSERT INTO `address` VALUES ('8', '7', '西湖区', '10');
INSERT INTO `address` VALUES ('9', null, '安徽省', '10');
INSERT INTO `address` VALUES ('10', null, '北京市', '10');
INSERT INTO `address` VALUES ('11', null, '上海市', '2');
INSERT INTO `address` VALUES ('12', '1', '抚州市', null);
INSERT INTO `address` VALUES ('13', '12', '南丰县', null);
INSERT INTO `address` VALUES ('14', '12', '南城县', null);
INSERT INTO `address` VALUES ('15', '10', '通州区', null);
INSERT INTO `address` VALUES ('16', '15', '中仓街道', null);
INSERT INTO `address` VALUES ('17', '11', '黄浦区', null);
INSERT INTO `address` VALUES ('18', '17', '南京东路区域', null);

-- ----------------------------
-- Table structure for `core_admin`
-- ----------------------------
DROP TABLE IF EXISTS `core_admin`;
CREATE TABLE `core_admin` (
  `admin_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用',
  `is_viewonly_admin` tinyint(3) NOT NULL DEFAULT '0' COMMENT '是否只读用户',
  PRIMARY KEY (`admin_id`),
  KEY `fk_admin_user` (`user_id`),
  KEY `fk_admin_website` (`website_id`),
  CONSTRAINT `fk_admin_user` FOREIGN KEY (`user_id`) REFERENCES `core_user` (`user_id`),
  CONSTRAINT `fk_admin_website` FOREIGN KEY (`website_id`) REFERENCES `core_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='核心管理员表';

-- ----------------------------
-- Records of core_admin
-- ----------------------------
INSERT INTO `core_admin` VALUES ('1', '1', '1', '2009-06-13 00:00:00', '0', '0');
INSERT INTO `core_admin` VALUES ('2', '1', '5', '2014-07-12 11:03:23', '0', '1');

-- ----------------------------
-- Table structure for `core_admin_role`
-- ----------------------------
DROP TABLE IF EXISTS `core_admin_role`;
CREATE TABLE `core_admin_role` (
  `role_id` int(11) NOT NULL DEFAULT '0',
  `admin_id` bigint(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`role_id`,`admin_id`),
  KEY `fk_core_role_admin` (`role_id`),
  KEY `fk_core_admin_role` (`admin_id`),
  CONSTRAINT `fk_core_admin_role` FOREIGN KEY (`admin_id`) REFERENCES `core_admin` (`admin_id`),
  CONSTRAINT `fk_core_role_admin` FOREIGN KEY (`role_id`) REFERENCES `core_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of core_admin_role
-- ----------------------------
INSERT INTO `core_admin_role` VALUES ('1', '1');
INSERT INTO `core_admin_role` VALUES ('1', '2');

-- ----------------------------
-- Table structure for `core_global`
-- ----------------------------
DROP TABLE IF EXISTS `core_global`;
CREATE TABLE `core_global` (
  `global_id` bigint(20) NOT NULL,
  `context_path` varchar(20) DEFAULT NULL COMMENT '部署路径',
  `port` int(11) DEFAULT NULL COMMENT '端口号',
  `treaty` longtext COMMENT '用户协议',
  `activescore` int(11) NOT NULL COMMENT '激活积分',
  `stock_warning` int(11) NOT NULL DEFAULT '5' COMMENT '库存预警值',
  `def_img` varchar(255) NOT NULL DEFAULT '/r/gou/u/no_picture.gif' COMMENT '图片不存在时默认图片',
  PRIMARY KEY (`global_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='核心全局表';

-- ----------------------------
-- Records of core_global
-- ----------------------------
INSERT INTO `core_global` VALUES ('1', '', '8080', '<p><br />\r\n	&nbsp;</p>\r\n<p>&nbsp;</p>\r\n<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"border_blue mt20\" width=\"650\">\r\n	<tbody>\r\n		<tr>\r\n			<td class=\"left_title2\">\r\n				<strong class=\"blue14\">服务协议</strong></td>\r\n		</tr>\r\n		<tr>\r\n			<td align=\"left\" class=\"list_b\">\r\n				<div class=\"Section1 mlr15\" style=\"layout-grid:  15.6pt none\">\r\n					<p>《服务协议》（以下简称&ldquo;本协议&rdquo;）<br />\r\n						请在成为网站用户前，仔细阅读本协议中所述的所有规则、条款和条件，包括被本协议提及而纳入本协议的其他条款和条件。建议阅读本协议时，同时阅读本协议中提及的其他网页所包含的内容，因为其可能包含适用于网站用户的其他条款和条件。<br />\r\n						<br />\r\n						<strong>一、</strong><strong> </strong><strong>用户注册：</strong><strong> </strong><br />\r\n						1. 用户注册是指用户登陆网站，按要求填写相关信息并确认同意本服务协议的过程。用户因进行交易、获取有偿服务等而发生的所有应纳税赋由用户自行承担。<br />\r\n						2. 用户必须是具有完全民事行为能力的自然人，或者是具有合法经营资格的实体组织。无民事行为能力人、限制民事行为能力人以及无经营或特定经营资格的组织不得注册为用户或超过其民事权利或行为能力范围与网站进行交易，如与网站进行交易的，则服务协议自始无效，有权立即停止与该用户的交易、注销该用户账户，并有权要求其承担相应法律责任。<br />\r\n						&nbsp;</p>\r\n				</div>\r\n			</td>\r\n		</tr>\r\n	</tbody>\r\n</table>\r\n<p>&nbsp;</p>', '10', '100', '/r/gou/u/no_picture.gif');

-- ----------------------------
-- Table structure for `core_log`
-- ----------------------------
DROP TABLE IF EXISTS `core_log`;
CREATE TABLE `core_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `website_id` bigint(20) DEFAULT NULL,
  `category` int(11) NOT NULL COMMENT '日志类型',
  `log_time` datetime NOT NULL COMMENT '日志时间',
  `ip` varchar(50) DEFAULT NULL COMMENT 'IP地址',
  `url` varchar(255) DEFAULT NULL COMMENT 'URL地址',
  `title` varchar(255) DEFAULT NULL COMMENT '日志标题',
  `content` varchar(255) DEFAULT NULL COMMENT '日志内容',
  PRIMARY KEY (`log_id`),
  KEY `fk_log_site` (`website_id`),
  KEY `fk_log_user` (`user_id`),
  CONSTRAINT `fk_log_site` FOREIGN KEY (`website_id`) REFERENCES `core_website` (`website_id`),
  CONSTRAINT `fk_log_user` FOREIGN KEY (`user_id`) REFERENCES `core_user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='jspgou日志表';

-- ----------------------------
-- Records of core_log
-- ----------------------------

-- ----------------------------
-- Table structure for `core_member`
-- ----------------------------
DROP TABLE IF EXISTS `core_member`;
CREATE TABLE `core_member` (
  `member_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用',
  `is_active` tinyint(1) NOT NULL COMMENT '是否激活',
  `activation_Code` char(32) DEFAULT NULL COMMENT '激活码',
  PRIMARY KEY (`member_id`),
  KEY `fk_member_user` (`user_id`),
  KEY `fk_member_website` (`website_id`),
  CONSTRAINT `fk_member_user` FOREIGN KEY (`user_id`) REFERENCES `core_user` (`user_id`),
  CONSTRAINT `fk_member_website` FOREIGN KEY (`website_id`) REFERENCES `core_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='核心会员表';

-- ----------------------------
-- Records of core_member
-- ----------------------------
INSERT INTO `core_member` VALUES ('1', '1', '1', '2009-07-06 00:00:00', '0', '1', null);
INSERT INTO `core_member` VALUES ('4', '4', '1', '2014-07-09 15:37:30', '0', '1', 'fe184138d63842729ab608548e23d8a8');
INSERT INTO `core_member` VALUES ('5', '6', '1', '2014-08-05 15:33:52', '0', '1', '2dd7392944434b198d6bc35b4de8998a');
INSERT INTO `core_member` VALUES ('6', '7', '1', '2015-01-24 20:11:31', '0', '0', '1d288abfda294b16bddfb8c966bdea15');
INSERT INTO `core_member` VALUES ('7', '8', '1', '2015-01-24 20:16:02', '0', '0', '0b9bf1af39f741019c33f93850ce7def');
INSERT INTO `core_member` VALUES ('8', '9', '1', '2015-01-24 20:19:04', '0', '0', 'eb921b41e75b4b2fa3eba75c5eed770a');
INSERT INTO `core_member` VALUES ('9', '5', '1', '2015-01-24 20:36:19', '0', '1', null);

-- ----------------------------
-- Table structure for `core_message_tpl`
-- ----------------------------
DROP TABLE IF EXISTS `core_message_tpl`;
CREATE TABLE `core_message_tpl` (
  `website_id` bigint(20) NOT NULL,
  `message_name` varchar(50) NOT NULL COMMENT '信息名称',
  `message_subject` varchar(255) DEFAULT NULL COMMENT '信息主题',
  `message_text` longtext COMMENT '信息内容',
  `active_title` varchar(255) DEFAULT NULL COMMENT '用户激活标题',
  `active_txt` longtext COMMENT '用户激活内容',
  KEY `fk_msgtpl_website` (`website_id`),
  CONSTRAINT `fk_msgtpl_website` FOREIGN KEY (`website_id`) REFERENCES `core_website` (`website_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='信息模板';

-- ----------------------------
-- Records of core_message_tpl
-- ----------------------------
INSERT INTO `core_message_tpl` VALUES ('1', 'resetPassword', '找回密码请求', '<p>您好</p>\r\n<p>${username}: 我们收到您的发出的找回密码请求。如果这不是您本人发出的请求，可以不予理会；如果您频繁收到这样的邮件，请尽快与管理员联系。</p>\r\n<p>新密码为：${resetPwd} 点击此地址，新密码即可生效：${base}/reset_password.htm?uid=${uid}&amp;activationCode=${activationCode}</p>\r\n<p><img alt=\"\" src=\"http://tracker.yihaodian.com/tracker/info.do?1=1&amp;edmActivity=Y\" style=\"display: block\" width=\"0\" /></p>', '欢迎您注册', '<p>${userName}您好：</p>\r\n<p>欢迎您注册G商城会员请点击以下链接进行激活 ${base}/active.htm?userName=${userName}&amp;activationCode=${activationCode}</p>\r\n<p>请在24小时内进行激活，否则注册无效。</p>');

-- ----------------------------
-- Table structure for `core_role`
-- ----------------------------
DROP TABLE IF EXISTS `core_role`;
CREATE TABLE `core_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `priority` int(11) DEFAULT '10' COMMENT '排列顺序',
  `is_super` char(1) DEFAULT '0' COMMENT '拥有所有权限',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of core_role
-- ----------------------------
INSERT INTO `core_role` VALUES ('1', '管理员', '10', '1');

-- ----------------------------
-- Table structure for `core_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `core_role_permission`;
CREATE TABLE `core_role_permission` (
  `role_id` int(11) NOT NULL DEFAULT '0',
  `uri` varchar(100) NOT NULL DEFAULT '',
  KEY `fk_core_permission_role` (`role_id`),
  CONSTRAINT `fk_core_permission_role` FOREIGN KEY (`role_id`) REFERENCES `core_role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色授权表';

-- ----------------------------
-- Records of core_role_permission
-- ----------------------------
INSERT INTO `core_role_permission` VALUES ('1', '/top.do');
INSERT INTO `core_role_permission` VALUES ('1', '/right.do');
INSERT INTO `core_role_permission` VALUES ('1', '/main.do');
INSERT INTO `core_role_permission` VALUES ('1', '/left.do');
INSERT INTO `core_role_permission` VALUES ('1', '/index.do');
INSERT INTO `core_role_permission` VALUES ('1', '/logout.do');

-- ----------------------------
-- Table structure for `core_user`
-- ----------------------------
DROP TABLE IF EXISTS `core_user`;
CREATE TABLE `core_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) NOT NULL COMMENT '登录名',
  `email` varchar(100) DEFAULT NULL COMMENT '电子邮箱',
  `password` char(32) NOT NULL COMMENT '密码',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `login_count` bigint(20) NOT NULL DEFAULT '0' COMMENT '总共登录次数',
  `register_ip` varchar(50) DEFAULT NULL COMMENT '注册IP',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `last_login_ip` varchar(50) DEFAULT NULL COMMENT '最后登录IP',
  `current_login_time` datetime DEFAULT NULL COMMENT '当前登录时间',
  `current_login_ip` varchar(50) DEFAULT NULL COMMENT '当前登录IP',
  `reset_key` char(32) DEFAULT NULL COMMENT '找回密码KEY',
  `reset_pwd` char(10) DEFAULT NULL COMMENT '重置密码',
  `errTime` datetime DEFAULT NULL COMMENT '登入错误时间',
  `errCount` int(11) NOT NULL DEFAULT '0' COMMENT '登入错误次数',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `ak_login_name` (`username`),
  UNIQUE KEY `ak_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='统一用户表';

-- ----------------------------
-- Records of core_user
-- ----------------------------
INSERT INTO `core_user` VALUES ('1', 'admin', 'admin@hotmail.com', '5f4dcc3b5aa765d61d8327deb882cf99', '2009-08-22 00:00:00', '2345', null, '2015-01-25 19:42:25', '127.0.0.1', '2015-01-25 20:30:58', '127.0.0.1', null, null, null, '0');
INSERT INTO `core_user` VALUES ('4', 'test', '1220788677@qq.com', 'e10adc3949ba59abbe56e057f20f883e', '2014-07-09 15:35:28', '1', '127.0.0.1', '2014-07-09 15:38:44', '192.168.0.144', '2014-07-09 15:38:44', '192.168.0.144', null, null, null, '0');
INSERT INTO `core_user` VALUES ('5', 'test1', 'test@test.com', '5a105e8b9d40e1329780d62ea2265d8a', '2014-07-12 11:03:23', '10', '127.0.0.1', '2015-01-25 07:53:29', '127.0.0.1', '2015-01-25 12:45:43', '127.0.0.1', null, null, null, '0');
INSERT INTO `core_user` VALUES ('6', 'test2', '1873136502@qq.com', 'a1e8597f91a1ec724bc79eb03f7282ec', '2014-08-05 15:33:52', '1', '127.0.0.1', '2014-08-05 15:49:56', '192.168.0.144', '2014-08-05 15:49:56', '192.168.0.144', null, null, null, '0');
INSERT INTO `core_user` VALUES ('7', 'abc1', 'foodoon@qq.com', '96e79218965eb72c92a549dd5a330112', '2015-01-24 20:11:31', '0', '127.0.0.1', null, null, null, null, null, null, null, '0');
INSERT INTO `core_user` VALUES ('8', 'abc2', 'foodoon1@qq.com', '96e79218965eb72c92a549dd5a330112', '2015-01-24 20:16:02', '0', '127.0.0.1', null, null, null, null, null, null, null, '0');
INSERT INTO `core_user` VALUES ('9', 'abc3', 'foodoon2@qq.com', '96e79218965eb72c92a549dd5a330112', '2015-01-24 20:19:04', '0', '127.0.0.1', null, null, null, null, null, null, null, '0');

-- ----------------------------
-- Table structure for `core_website`
-- ----------------------------
DROP TABLE IF EXISTS `core_website`;
CREATE TABLE `core_website` (
  `website_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL COMMENT '创始人ID',
  `global_id` bigint(20) NOT NULL COMMENT '全局表ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父站点ID',
  `domain` varchar(100) NOT NULL COMMENT '域名',
  `name` varchar(150) NOT NULL COMMENT '站点名称',
  `additionalTitle` varchar(255) DEFAULT NULL COMMENT '附加标题',
  `current_system` varchar(20) NOT NULL COMMENT '当前系统',
  `suffix` varchar(20) NOT NULL DEFAULT '.jhtml' COMMENT '访问后缀',
  `lft` int(11) NOT NULL DEFAULT '1' COMMENT '树左边',
  `rgt` int(11) NOT NULL DEFAULT '2' COMMENT '树右边',
  `create_time` datetime NOT NULL COMMENT '站点创建时间',
  `base_domain` varchar(100) DEFAULT NULL COMMENT '根域名',
  `domain_alias` varchar(255) DEFAULT NULL COMMENT '多个别名用;分割',
  `short_name` varchar(20) DEFAULT NULL COMMENT '站点简称',
  `close_reason` varchar(255) DEFAULT '' COMMENT '关闭原因',
  `is_close` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否关闭网站（后台仍可访问）',
  `front_encoding` varchar(20) NOT NULL DEFAULT 'GBK' COMMENT '前台编码',
  `front_content_type` varchar(50) NOT NULL DEFAULT 'text/html; charset=gb2312' COMMENT '前台Content-Type',
  `locale_front` varchar(20) NOT NULL DEFAULT 'zh_CN' COMMENT '前台本地化信息',
  `locale_admin` varchar(20) NOT NULL DEFAULT 'zh_CN' COMMENT '后台本地化信息',
  `control_reserved` longtext COMMENT '用户信息保留字',
  `control_name_minlen` int(11) NOT NULL DEFAULT '4' COMMENT '用户名最短几个字符',
  `control_front_ips` longtext COMMENT '前台允许访问的IP',
  `control_admin_ips` longtext COMMENT '后台允许访问的IP',
  `company` varchar(255) NOT NULL DEFAULT '' COMMENT '公司名称',
  `copyright` varchar(255) NOT NULL DEFAULT '' COMMENT '版权信息',
  `record_code` varchar(255) NOT NULL DEFAULT '' COMMENT '备案号',
  `email` varchar(255) NOT NULL DEFAULT '' COMMENT '电子邮箱',
  `phone` varchar(255) NOT NULL DEFAULT '' COMMENT '电话号码',
  `mobile` varchar(255) NOT NULL DEFAULT '' COMMENT '手机号码',
  `is_relative_path` tinyint(1) NOT NULL DEFAULT '0' COMMENT '使用相对路径',
  `email_host` varchar(50) DEFAULT NULL COMMENT '邮件发送服务器',
  `email_encoding` varchar(20) DEFAULT NULL COMMENT '邮件发送编码',
  `email_username` varchar(100) DEFAULT NULL COMMENT '邮箱用户名',
  `email_personal` varchar(100) DEFAULT NULL COMMENT '邮箱发件人',
  `email_password` varchar(100) DEFAULT NULL COMMENT '邮箱密码',
  `version` varchar(255) DEFAULT NULL COMMENT '版本信息',
  `restart` tinyint(3) NOT NULL DEFAULT '0',
  PRIMARY KEY (`website_id`),
  UNIQUE KEY `ak_domain` (`domain`),
  KEY `fk_website_admin` (`admin_id`),
  KEY `fk_website_global` (`global_id`),
  KEY `fk_website_parent` (`parent_id`),
  CONSTRAINT `fk_website_admin` FOREIGN KEY (`admin_id`) REFERENCES `core_admin` (`admin_id`),
  CONSTRAINT `fk_website_global` FOREIGN KEY (`global_id`) REFERENCES `core_global` (`global_id`),
  CONSTRAINT `fk_website_parent` FOREIGN KEY (`parent_id`) REFERENCES `core_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='站点表';

-- ----------------------------
-- Records of core_website
-- ----------------------------
INSERT INTO `core_website` VALUES ('1', '1', '1', null, '192.168.0.144', '测试站点', '洽诚网络', '商城', '.htm', '1', '2', '2009-06-13 00:00:00', '', null, null, '网站暂时关闭', '0', 'GBK', 'text/html; charset=gbk', 'zh_CN', 'zh_CN', null, '4', null, null, '', '', '', '', '', '', '1', 'smtp.163.com', 'UTF-8', 'gang0119@163.com', 'G商城', 'gavin1217', '4.5', '0');

-- ----------------------------
-- Table structure for `data_backup`
-- ----------------------------
DROP TABLE IF EXISTS `data_backup`;
CREATE TABLE `data_backup` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `dataBasePath` varchar(255) DEFAULT NULL COMMENT '数据库路径',
  `address` varchar(255) DEFAULT NULL COMMENT '数据库地址',
  `dataBaseName` varchar(255) DEFAULT NULL COMMENT '数据库名称',
  `username` varchar(255) DEFAULT NULL COMMENT '数据库用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '数据库密码',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='数据备份信息';

-- ----------------------------
-- Records of data_backup
-- ----------------------------
INSERT INTO `data_backup` VALUES ('1', 'C:\\\\Program Files\\\\MySQL\\\\MySQL Server 5.0\\\\bin\\\\', 'localhost', 'gou', 'root', 'password');

-- ----------------------------
-- Table structure for `online_customerservice`
-- ----------------------------
DROP TABLE IF EXISTS `online_customerservice`;
CREATE TABLE `online_customerservice` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '在线客服昵称',
  `content` varchar(255) NOT NULL DEFAULT '' COMMENT 'QQ号',
  `priority` int(11) NOT NULL DEFAULT '10' COMMENT '排序',
  `is_disable` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='在线客服';

-- ----------------------------
-- Records of online_customerservice
-- ----------------------------
INSERT INTO `online_customerservice` VALUES ('1', 'test', '1220788677', '10', '0');

-- ----------------------------
-- Table structure for `popularity_group`
-- ----------------------------
DROP TABLE IF EXISTS `popularity_group`;
CREATE TABLE `popularity_group` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(150) DEFAULT NULL COMMENT '名称',
  `beginTime` datetime DEFAULT NULL COMMENT '开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '结束时间',
  `price` double(11,2) DEFAULT NULL COMMENT '组合价格',
  `description` varchar(255) DEFAULT NULL COMMENT '活动描述',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='人气组合';

-- ----------------------------
-- Records of popularity_group
-- ----------------------------
INSERT INTO `popularity_group` VALUES ('19', 'test', '2014-09-04 11:26:55', '2014-09-04 11:26:57', '60.00', '');
INSERT INTO `popularity_group` VALUES ('20', 'test2', '2014-09-01 17:11:56', '2014-09-10 17:12:01', '20.00', '');

-- ----------------------------
-- Table structure for `popularity_group_product`
-- ----------------------------
DROP TABLE IF EXISTS `popularity_group_product`;
CREATE TABLE `popularity_group_product` (
  `Id` bigint(20) NOT NULL DEFAULT '0' COMMENT '组合购买Id',
  `product_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '商品Id',
  PRIMARY KEY (`Id`,`product_id`),
  KEY `fk_popularity_group_product` (`Id`),
  KEY `fk_popularity_product_group` (`product_id`),
  CONSTRAINT `fk_popularity_product_group` FOREIGN KEY (`product_id`) REFERENCES `shop_product` (`product_id`),
  CONSTRAINT `popularity_group_product` FOREIGN KEY (`Id`) REFERENCES `popularity_group` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组合购买商品表';

-- ----------------------------
-- Records of popularity_group_product
-- ----------------------------

-- ----------------------------
-- Table structure for `popularity_item`
-- ----------------------------
DROP TABLE IF EXISTS `popularity_item`;
CREATE TABLE `popularity_item` (
  `popularityitem_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cart_id` bigint(20) NOT NULL COMMENT '购物车ID',
  `count` int(11) NOT NULL COMMENT '数量',
  `popularity_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`popularityitem_id`),
  KEY `fk_shop_popularityitem_cart` (`cart_id`),
  CONSTRAINT `fk_shop_popularityitem_cart` FOREIGN KEY (`cart_id`) REFERENCES `shop_cart` (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='购物车';

-- ----------------------------
-- Records of popularity_item
-- ----------------------------

-- ----------------------------
-- Table structure for `shop_admin`
-- ----------------------------
DROP TABLE IF EXISTS `shop_admin`;
CREATE TABLE `shop_admin` (
  `admin_id` bigint(20) NOT NULL,
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `firstname` varchar(100) DEFAULT NULL COMMENT '名',
  `lastname` varchar(100) DEFAULT NULL COMMENT '姓',
  PRIMARY KEY (`admin_id`),
  KEY `fk_shop_admin_website` (`website_id`),
  CONSTRAINT `fk_shop_admin_core` FOREIGN KEY (`admin_id`) REFERENCES `core_admin` (`admin_id`),
  CONSTRAINT `fk_shop_admin_website` FOREIGN KEY (`website_id`) REFERENCES `core_website` (`website_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城管理员';

-- ----------------------------
-- Records of shop_admin
-- ----------------------------
INSERT INTO `shop_admin` VALUES ('1', '1', '', null);
INSERT INTO `shop_admin` VALUES ('2', '1', '', null);

-- ----------------------------
-- Table structure for `shop_advertise`
-- ----------------------------
DROP TABLE IF EXISTS `shop_advertise`;
CREATE TABLE `shop_advertise` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `adspace_id` int(11) DEFAULT NULL COMMENT '广告位',
  `name` varchar(50) DEFAULT NULL COMMENT '广告名字',
  `weight` int(1) DEFAULT NULL COMMENT '广告权重',
  `display_count` int(11) DEFAULT NULL COMMENT '展现次数',
  `click_count` int(11) DEFAULT NULL COMMENT '点击次数',
  `starttime` datetime DEFAULT NULL COMMENT '开始时间',
  `endTime` datetime DEFAULT NULL COMMENT '节束时间',
  `is_enabled` char(1) DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`Id`),
  KEY `shop_adspace_fk` (`adspace_id`),
  CONSTRAINT `shop_adspace_fk` FOREIGN KEY (`adspace_id`) REFERENCES `shop_advertise_space` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='广告';

-- ----------------------------
-- Records of shop_advertise
-- ----------------------------
INSERT INTO `shop_advertise` VALUES ('1', '1', '.net云计算1', '1', '1291', '0', null, null, '1');

-- ----------------------------
-- Table structure for `shop_advertise_attr`
-- ----------------------------
DROP TABLE IF EXISTS `shop_advertise_attr`;
CREATE TABLE `shop_advertise_attr` (
  `Id` int(11) NOT NULL DEFAULT '0',
  `attr_name` varchar(100) DEFAULT NULL COMMENT '属性名字',
  `attr_value` varchar(255) DEFAULT NULL COMMENT '属性值',
  KEY `fk_params_advertising` (`Id`),
  CONSTRAINT `shop_advertise_fk` FOREIGN KEY (`Id`) REFERENCES `shop_advertise` (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_advertise_attr
-- ----------------------------
INSERT INTO `shop_advertise_attr` VALUES ('1', 'image_url', '/upload/201407/091116578hgi.jpg');
INSERT INTO `shop_advertise_attr` VALUES ('1', 'image_width', '981');
INSERT INTO `shop_advertise_attr` VALUES ('1', 'image_height', '70');
INSERT INTO `shop_advertise_attr` VALUES ('1', 'image_target', '_blank');
INSERT INTO `shop_advertise_attr` VALUES ('1', 'image_link', 'http://shop.foodoon.com');

-- ----------------------------
-- Table structure for `shop_advertise_space`
-- ----------------------------
DROP TABLE IF EXISTS `shop_advertise_space`;
CREATE TABLE `shop_advertise_space` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `ad_name` varchar(50) DEFAULT NULL COMMENT '版位名字',
  `description` varchar(255) DEFAULT NULL COMMENT '版位描述',
  `is_enabled` char(1) DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='广告版位';

-- ----------------------------
-- Records of shop_advertise_space
-- ----------------------------
INSERT INTO `shop_advertise_space` VALUES ('1', '页头广告位', '页头广告位', '1');

-- ----------------------------
-- Table structure for `shop_article`
-- ----------------------------
DROP TABLE IF EXISTS `shop_article`;
CREATE TABLE `shop_article` (
  `article_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `website_id` bigint(20) NOT NULL,
  `channel_id` bigint(20) NOT NULL,
  `title` varchar(150) NOT NULL COMMENT '标题',
  `publish_time` datetime NOT NULL COMMENT '发布时间',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用',
  `link` varchar(255) DEFAULT NULL COMMENT '外部链接',
  `param2` varchar(255) DEFAULT NULL COMMENT '扩展数据2',
  `param3` varchar(255) DEFAULT NULL COMMENT '扩展数据3',
  PRIMARY KEY (`article_id`),
  KEY `fk_shop_article_channel` (`channel_id`),
  KEY `fk_shop_article_website` (`website_id`),
  CONSTRAINT `fk_shop_article_channel` FOREIGN KEY (`channel_id`) REFERENCES `shop_channel` (`channel_id`),
  CONSTRAINT `fk_shop_article_website` FOREIGN KEY (`website_id`) REFERENCES `core_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='商城文章表';

-- ----------------------------
-- Records of shop_article
-- ----------------------------
INSERT INTO `shop_article` VALUES ('1', '1', '1', '积分说明', '2014-07-04 09:29:32', '0', '', '', '');
INSERT INTO `shop_article` VALUES ('2', '1', '1', '购买流程', '2014-07-04 09:30:10', '0', '', '', '');
INSERT INTO `shop_article` VALUES ('3', '1', '1', '账户注册', '2014-07-04 09:30:55', '0', '', '', '');
INSERT INTO `shop_article` VALUES ('5', '1', '2', '最新商品', '2014-07-04 09:42:32', '0', '', '', '');
INSERT INTO `shop_article` VALUES ('7', '1', '4', '热门促销', '2014-07-04 10:48:51', '0', '', '', '');
INSERT INTO `shop_article` VALUES ('8', '1', '5', '订单拆分', '2014-07-04 10:52:47', '0', '', '', '');
INSERT INTO `shop_article` VALUES ('10', '1', '5', '国内配送', '2014-07-04 10:55:03', '0', '', '', '');
INSERT INTO `shop_article` VALUES ('11', '1', '6', '其他支付方式', '2014-07-04 10:57:28', '0', '', '', '');
INSERT INTO `shop_article` VALUES ('12', '1', '6', '在线支付', '2014-07-04 10:58:41', '0', '', '', '');
INSERT INTO `shop_article` VALUES ('13', '1', '6', '货到付款', '2014-07-04 10:59:24', '0', '', '', '');

-- ----------------------------
-- Table structure for `shop_article_content`
-- ----------------------------
DROP TABLE IF EXISTS `shop_article_content`;
CREATE TABLE `shop_article_content` (
  `article_id` bigint(20) NOT NULL,
  `content` longtext,
  KEY `fk_shop_content_article` (`article_id`),
  CONSTRAINT `fk_shop_content_article` FOREIGN KEY (`article_id`) REFERENCES `shop_article` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城文章内容';

-- ----------------------------
-- Records of shop_article_content
-- ----------------------------
INSERT INTO `shop_article_content` VALUES ('1', '<div id=\"container\">\r\n	<div class=\"clearfix mt10\">\r\n		<div class=\"right_content\">\r\n			<div class=\"help_box\">\r\n				<div class=\"help_detail\">\r\n					<dl style=\"padding-bottom: 0px; padding-left: 40px; padding-right: 40px; padding-top: 0px\">\r\n						<dt>\r\n							一、积分获得</dt>\r\n						<dd>\r\n							<p><strong>购物</strong></p>\r\n							<p class=\"txt_indent\">1．您购买的商品由于积分不同，以订单提交时页面标注的积分分数为准。</p>\r\n							<p class=\"txt_indent\">2．客户购物且订单生成后，积分将自动打入客户的积分帐户，状态为冻结，此时积分不可用。当客户此次购物为有效购物后，积分变为完成状态，便可以使用。</p>\r\n						</dd>\r\n						<dd>\r\n							<p><strong>评论商品</strong></p>\r\n							<p class=\"txt_indent\">1．您购物成功后可以对所购的商品进行评论，每条评论展示后可获得2积分（积分获得初期为冻结状态，订单完成5天后解冻；若订单一直未完成，订单出库后7天解冻）</p>\r\n							<p class=\"txt_indent\">2．欢迎您提供优质的评论，将有机会被置顶展示并可最多获得积分50分额外奖励；</p>\r\n							<p class=\"txt_indent\">3．您购买家电或3C类商品后进行评论，并成功展示将获得更多的积分（以售出时价格为准）：</p>\r\n							<p class=\"txt_indent\">a)商品金额&le;200元 ，可获得积分：12分；</p>\r\n							<p class=\"txt_indent\">b)商品金额200－1000元（不含200元和1000元），可获得积分：32分；</p>\r\n							<p class=\"txt_indent\">c)商品金额&ge;1000元 可获得积分：62分；</p>\r\n							<p class=\"txt_indent\">4．每个商品页面前5名被展示的评论，将额外获得10积分；（可与置顶奖励累计获得）</p>\r\n							<p class=\"txt_indent\">5．单个订单中的商品只能参与一次评论。</p>\r\n						</dd>\r\n						<dd>\r\n							&nbsp;</dd>\r\n					</dl>\r\n				</div>\r\n			</div>\r\n		</div>\r\n	</div>\r\n</div>\r\n<p><br class=\"Apple-interchange-newline\" />\r\n	&nbsp;</p>');
INSERT INTO `shop_article_content` VALUES ('2', '<p align=\"left\">&nbsp;&nbsp;&nbsp;<span style=\"color: rgb(0,0,0)\"><span style=\"font-family: \'Microsoft YaHei\'; color: rgb(229,51,51); font-size: 14px\"><strong><span style=\"color: rgb(161,0,0)\">Step.1</span></strong></span><span style=\"font-family: \'Microsoft YaHei\'; color: rgb(229,51,51); font-size: 14px\"><strong>&nbsp;</strong><span style=\"font-family: Tahoma; color: rgb(0,0,0); font-size: x-small\"><font size=\"1\"><span class=\"Apple-converted-space\">&nbsp;</span></font></span></span>进入网站后，点击左上方&ldquo;注册&rdquo;按钮进行注册。</span></p>\r\n<p><br />\r\n	&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><span style=\"color: rgb(0,0,0)\"><span style=\"font-family: \'Microsoft YaHei\'; color: rgb(229,51,51); font-size: 14px\"><strong><span style=\"color: rgb(161,0,0)\">Step.2</span></strong></span>&nbsp;选择注册方式后，根据相关的提示内容填写注册信息。</span></p>\r\n<p align=\"center\">&nbsp;</p>\r\n<p>&nbsp;&nbsp;&nbsp;<span style=\"color: rgb(0,0,0)\"><span style=\"font-family: \'Microsoft YaHei\'; color: rgb(229,51,51); font-size: 14px\"><strong><span style=\"color: rgb(161,0,0)\">Step.3</span></strong></span><span style=\"color: rgb(229,51,51); font-size: 14px\"><span style=\"font-family: \'Microsoft YaHei\'\"><strong><em>&nbsp;</em></strong><span style=\"font-family: Tahoma; color: rgb(0,0,0); font-size: x-small\"><font size=\"1\"><span class=\"Apple-converted-space\">&nbsp;</span></font></span></span></span>完成注册后，系统自动登录。您可以进入个人中心</span><span style=\"color: rgb(0,0,0)\"><span class=\"Apple-converted-space\"> </span><span style=\"color: rgb(0,0,0)\">编</span>辑个人档案，或直接挑选所需购买的商品。</span></p>');
INSERT INTO `shop_article_content` VALUES ('3', '<div class=\"biaoti\"><b>账户注册</b></div>\r\n<div class=\"content\" id=\"helpcontent\" sizcache=\"0\" sizset=\"72\">\r\n	<p><br />\r\n		&nbsp;</p>\r\n	<p>&nbsp;&nbsp;&nbsp; 您只要通过注册开户，即可成为 会员。<br />\r\n		&nbsp;&nbsp;&nbsp; 在 网站注册开户有以下途径：<br />\r\n		<br />\r\n		&nbsp;&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><strong>1、网站注册</strong><br />\r\n		&nbsp;&nbsp;&nbsp; 1）进入网站后，点击页面左上方&ldquo;注册&rdquo;，将出现新页面，在新页面中选择相应的注册方式（Email注册），根据提示内容输入注册账号、密码（密码设置不要过于简单）、验证码等，确认无误后点击&ldquo;同意以上条款并注册&rdquo;即可，注册用户名是唯一的。注册成功后，您可以到网站进行个人信息的更新。</p>\r\n</div>\r\n<p>&nbsp;</p>');

-- ----------------------------
-- Table structure for `shop_brand`
-- ----------------------------
DROP TABLE IF EXISTS `shop_brand`;
CREATE TABLE `shop_brand` (
  `brand_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `alias` varchar(255) DEFAULT NULL COMMENT '别名',
  `web_url` varchar(255) DEFAULT NULL COMMENT '网址',
  `logo_path` varchar(255) DEFAULT NULL COMMENT 'LOGO',
  `priority` int(11) NOT NULL DEFAULT '10' COMMENT '排序',
  `is_sift` tinyint(3) DEFAULT NULL COMMENT '是否精选',
  `is_disabled` tinyint(3) NOT NULL COMMENT '是否禁用',
  `firstcharacter` varchar(255) NOT NULL COMMENT '品牌首字母',
  PRIMARY KEY (`brand_id`),
  KEY `fk_shop_brand_website` (`website_id`),
  CONSTRAINT `fk_shop_brand_website` FOREIGN KEY (`website_id`) REFERENCES `core_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='品牌';

-- ----------------------------
-- Records of shop_brand
-- ----------------------------
INSERT INTO `shop_brand` VALUES ('1', '1', '森马', '', 'http://www.semir.com', '/upload/201405/26084559f4fx.gif', '1', '0', '0', 's');
INSERT INTO `shop_brand` VALUES ('2', '1', '摩托罗拉', '', 'http://www.motorola.com.cn/', '/upload/201405/26084614quwz.png', '10', '0', '0', 'm');
INSERT INTO `shop_brand` VALUES ('3', '1', 'iPhone', '', 'http://www.apple.com/', '/upload/201405/26084706lpep.jpg', '10', '0', '0', 'i');
INSERT INTO `shop_brand` VALUES ('4', '1', '联想', '联想', 'http://www.lenovo.com.cn/', '/upload/201406/17114011pzq7.gif', '10', '0', '0', 'l');
INSERT INTO `shop_brand` VALUES ('5', '1', '美的', '美的', 'http://www.midea.com/cn/', '/upload/201406/17114535if8u.png', '10', '0', '0', 'm');
INSERT INTO `shop_brand` VALUES ('6', '1', '尼康', '尼康', '', '', '10', '0', '0', 'n');
INSERT INTO `shop_brand` VALUES ('7', '1', '康佳', '', '', '', '10', '0', '0', 'k');
INSERT INTO `shop_brand` VALUES ('8', '1', '梦特娇', '', '', '', '10', '0', '0', 'm');
INSERT INTO `shop_brand` VALUES ('9', '1', '欧莱若', '', '', '', '10', '0', '0', 'o');
INSERT INTO `shop_brand` VALUES ('10', '1', '仙子宜岱', '', '', '', '10', '0', '0', 'x');
INSERT INTO `shop_brand` VALUES ('11', '1', '诺基亚', '', '', '', '10', '0', '0', 'n');
INSERT INTO `shop_brand` VALUES ('12', '1', '海尔', '', '', '', '10', '0', '0', 'h');
INSERT INTO `shop_brand` VALUES ('13', '1', '特步', '', '', '', '10', '0', '0', 't');
INSERT INTO `shop_brand` VALUES ('14', '1', '惠普', '', '', '', '10', '0', '0', 'h');
INSERT INTO `shop_brand` VALUES ('15', '1', '华硕', '', '', '', '10', '0', '0', 'h');
INSERT INTO `shop_brand` VALUES ('16', '1', '戴尔', '', '', '', '10', '0', '0', 'd');
INSERT INTO `shop_brand` VALUES ('17', '1', '浪莎', '', '', '', '10', '0', '0', 'l');

-- ----------------------------
-- Table structure for `shop_brand_text`
-- ----------------------------
DROP TABLE IF EXISTS `shop_brand_text`;
CREATE TABLE `shop_brand_text` (
  `brand_id` bigint(20) NOT NULL,
  `text` longtext COMMENT '详细信息',
  PRIMARY KEY (`brand_id`),
  CONSTRAINT `fk_shop_text_brand` FOREIGN KEY (`brand_id`) REFERENCES `shop_brand` (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_brand_text
-- ----------------------------
INSERT INTO `shop_brand_text` VALUES ('1', '<p>森马，国内休闲服行业迅速崛起的领军品牌。森马集团有限公司以&ldquo;创大众服饰名牌&rdquo;为发展宗旨，积极推行特许经营发展模式，休闲装和童装品牌连锁网点遍布全国二十九个省、市、自治区、直辖市，形成了完整的市场网络格局。集团公司现有休闲装 &ldquo;semir&rdquo;及童装&ldquo;balabala&rdquo;等两个知名服装品牌。森马集团有限公司于1996年12月18日创立于浙江省温州市，是一家以虚拟经营模式为特色，以系列休闲服饰为主导产业的无区域集团。公司注册资本为人民币2.38亿元，总资产达10多亿元，是温州市大企业大集团之一。</p>');
INSERT INTO `shop_brand_text` VALUES ('2', '<p>&nbsp;</p>\r\n<p><a href=\"http://baike.baidu.com/view/195002.htm\" target=\"_blank\">托罗拉公司</a>（Motorola Inc ），原名：Galvin Manufacturing Corporation，成立于1928年。1947年，改名为<a href=\"http://baike.baidu.com/view/39480.htm\" target=\"_blank\">Motorola</a>，从1930年代开始作为商标使用。总部设在美国<a href=\"http://baike.baidu.com/view/190007.htm\" target=\"_blank\">伊利诺伊州</a>绍姆堡，位于<a href=\"http://baike.baidu.com/view/36045.htm\" target=\"_blank\">芝加哥</a>市郊。世界财富百强企业之一，是全球芯片制造、电子通讯的领导者。</p>\r\n<p>摩托罗拉经过90多年的发展，摩托罗拉使用无线电、宽频及网际网路，并提供嵌入晶片系统，以及端对端整体网路通讯解决方案，以达到加强个人、工作团体、车辆及家庭的操控及联系能力。</p>\r\n<p>摩托罗拉公司有三大业务集团，分别是企业移动解决方案部、宽带及移动网络事业部和<a href=\"http://baike.baidu.com/view/1954381.htm\" target=\"_blank\">移动终端</a>事业部。</p>');
INSERT INTO `shop_brand_text` VALUES ('3', '<div class=\"mod-top\" id=\"card-container\" style=\"clear: both\">\r\n	<div class=\"card-summary nslog-area clearfix\" data-nslog-type=\"72\">\r\n		<div class=\"card-summary-content\">\r\n			<div class=\"para\">iPhone，是苹果公司旗下研发的<a href=\"http://baike.baidu.com/view/535.htm\" target=\"_blank\">智能手机</a>系列，它搭载苹果公司研发的<a href=\"http://baike.baidu.com/view/158983.htm\" target=\"_blank\">iOS</a><a href=\"http://baike.baidu.com/view/148382.htm\" target=\"_blank\">手机操作系统</a>。第一代iPhone于2007年1月9日由<a href=\"http://baike.baidu.com/view/15181.htm\" target=\"_blank\">苹果公司</a>前首席执行官的<a href=\"http://baike.baidu.com/view/90660.htm\" target=\"_blank\">史蒂夫&middot;乔布斯</a>发布，并在同年6月29日正式发售。2013年9月10日，苹果公司在<a href=\"http://baike.baidu.com/view/2398.htm\" target=\"_blank\">美国</a><a href=\"http://baike.baidu.com/view/526914.htm\" target=\"_blank\">加州</a>举行新产品发布会上，推出第七代产品<a href=\"http://baike.baidu.com/view/7626773.htm\" target=\"_blank\">iPhone 5S</a>及<a href=\"http://baike.baidu.com/view/10817151.htm\" target=\"_blank\">iPhone 5C</a>首次发布会还在<a href=\"http://baike.baidu.com/view/2621.htm\" target=\"_blank\">北京</a>、<a href=\"http://baike.baidu.com/view/31528.htm\" target=\"_blank\">柏林</a>和<a href=\"http://baike.baidu.com/view/19319.htm\" target=\"_blank\">东京</a>三地设置分会场，再在各地分别举行了发布会。第七代的iPhone5S和iPhone5C于2013年9月10日发布，同年9月20日正式发售。第八代的iPhone6将于2014年八月中旬前后发布。<a href=\"http://baike.baidu.com/view/15181.htm\" target=\"_blank\">苹果公司</a>旗下智能手机iPhone，是全球销量第一的智能手机。</div>\r\n		</div>\r\n	</div>\r\n</div>\r\n<p>&nbsp;</p>');
INSERT INTO `shop_brand_text` VALUES ('4', '<p>&nbsp;</p>\r\n<div class=\"para\">联想集团成立于1984年，由中科院计算所投资20万元人民币、11名<a href=\"http://baike.baidu.com/view/1043923.htm\" target=\"_blank\">科技人员</a>创办，是一家在信息产业内多元化发展的大型<a href=\"http://baike.baidu.com/view/70550.htm\" target=\"_blank\">企业集团</a>，富有创新性的国际化的科技公司。由联想及原<a href=\"http://baike.baidu.com/view/1937.htm\" target=\"_blank\">IBM</a>个人电脑事业部所组成。从1996年开始，<a href=\"http://baike.baidu.com/view/16950.htm\" target=\"_blank\">联想电脑</a>销量一直位居中国国内市场首位，2013年；联想电脑销售量升居世界第1，成为全球最大的PC生产厂商。</div>\r\n<div class=\"para\">作为<a href=\"http://baike.baidu.com/view/151814.htm\" target=\"_blank\">全球</a><a href=\"http://baike.baidu.com/view/203808.htm\" target=\"_blank\">个人电脑</a>市场的领导企业，联想从事开发、制造并销售可靠的、安全易用的技术产品及优质专业的服务，帮助全球客户和合作伙伴取得成功。联想公司主要生产<a href=\"http://baike.baidu.com/view/3624172.htm\" target=\"_blank\">台式电脑</a>、<a href=\"http://baike.baidu.com/view/899.htm\" target=\"_blank\">服务器</a>、<a href=\"http://baike.baidu.com/view/7690.htm\" target=\"_blank\">笔记本电脑</a>、<a href=\"http://baike.baidu.com/view/7836.htm\" target=\"_blank\">打印机</a>、<a href=\"http://baike.baidu.com/view/30816.htm\" target=\"_blank\">掌上电脑</a>、<a href=\"http://baike.baidu.com/view/1143.htm\" target=\"_blank\">主板</a>、<a href=\"http://baike.baidu.com/view/1455.htm\" target=\"_blank\">手机</a> 、<a href=\"http://baike.baidu.com/view/2695326.htm\" target=\"_blank\">一体机电脑</a>等商品。</div>\r\n<div class=\"para\">自2014年4月1日起， 联想集团将会成立四个新的、相对独立的业务集团，分别是<a href=\"http://baike.baidu.com/subview/13621/4882082.htm\" target=\"_blank\">PC</a>业务集团、<a href=\"http://baike.baidu.com/subview/50463/6847219.htm\" target=\"_blank\">移动</a>业务集团、企业级业务集团、云服务业务集团。</div>');
INSERT INTO `shop_brand_text` VALUES ('5', '<p>&nbsp;</p>\r\n<div class=\"mod-top\" id=\"card-container\" style=\"CLEAR: both\">\r\n	<div class=\"card-summary nslog-area clearfix\" data-nslog-type=\"72\">\r\n		<div class=\"card-summary-content\">\r\n			<div class=\"para\">美的集团（SZ.000333）是一家以家电制造业为主，涉足<a href=\"http://baike.baidu.com/view/7927230.htm\" target=\"_blank\">照明电器</a>、<a href=\"http://baike.baidu.com/view/9346.htm\" target=\"_blank\">房地产</a>、物流宝贝等领域的<b>大型</b>综合性<a href=\"http://baike.baidu.com/view/70550.htm\" target=\"_blank\">企业集团</a>，于2013年9月18日在深交所上市，旗下拥有<a href=\"http://baike.baidu.com/subview/15662/5134480.htm\" target=\"_blank\">小天鹅</a>（SZ000418）、<a href=\"http://baike.baidu.com/view/770074.htm\" target=\"_blank\">威灵</a>控股（HK00382）两家子上市公司。2013年，美的集团整体实现销售收入达1209.75亿元，同比增长17.91%。2013年&ldquo;中国最有价值品牌&rdquo;评价中，美的品牌价值达到653.36亿元，名列全国最有价值品牌第5位。</div>\r\n		</div>\r\n	</div>\r\n</div>\r\n<p>&nbsp;</p>');
INSERT INTO `shop_brand_text` VALUES ('6', '');
INSERT INTO `shop_brand_text` VALUES ('7', '');
INSERT INTO `shop_brand_text` VALUES ('8', '');
INSERT INTO `shop_brand_text` VALUES ('9', '');
INSERT INTO `shop_brand_text` VALUES ('10', '');
INSERT INTO `shop_brand_text` VALUES ('11', '');
INSERT INTO `shop_brand_text` VALUES ('12', '');
INSERT INTO `shop_brand_text` VALUES ('13', '');
INSERT INTO `shop_brand_text` VALUES ('14', '');
INSERT INTO `shop_brand_text` VALUES ('15', '');
INSERT INTO `shop_brand_text` VALUES ('16', '');
INSERT INTO `shop_brand_text` VALUES ('17', '');

-- ----------------------------
-- Table structure for `shop_cardgift`
-- ----------------------------
DROP TABLE IF EXISTS `shop_cardgift`;
CREATE TABLE `shop_cardgift` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `cartId` bigint(20) DEFAULT NULL COMMENT '所属购物车',
  `websiteId` bigint(20) DEFAULT NULL COMMENT '所属站点',
  `giftId` bigint(20) DEFAULT NULL COMMENT '礼品id',
  `count` int(11) DEFAULT NULL COMMENT '礼品数量',
  PRIMARY KEY (`Id`),
  KEY `fk_cardgift_cart` (`cartId`),
  KEY `fk_cardgift_website` (`websiteId`),
  KEY `fk_cardgift_gift` (`giftId`),
  CONSTRAINT `fk_cardgift_cart` FOREIGN KEY (`cartId`) REFERENCES `shop_cart` (`cart_id`),
  CONSTRAINT `fk_cardgift_gift` FOREIGN KEY (`giftId`) REFERENCES `shop_gift` (`Id`),
  CONSTRAINT `fk_cardgift_website` FOREIGN KEY (`websiteId`) REFERENCES `core_website` (`website_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_cardgift
-- ----------------------------

-- ----------------------------
-- Table structure for `shop_cart`
-- ----------------------------
DROP TABLE IF EXISTS `shop_cart`;
CREATE TABLE `shop_cart` (
  `cart_id` bigint(20) NOT NULL,
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `total_items` int(11) NOT NULL DEFAULT '0' COMMENT '商品总数量',
  `total_gifts` int(11) DEFAULT NULL COMMENT '礼品总数量',
  PRIMARY KEY (`cart_id`),
  KEY `fk_shop_cart_website` (`website_id`),
  CONSTRAINT `fk_shop_cart_member` FOREIGN KEY (`cart_id`) REFERENCES `shop_member` (`member_id`),
  CONSTRAINT `fk_shop_cart_website` FOREIGN KEY (`website_id`) REFERENCES `core_website` (`website_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购物车';

-- ----------------------------
-- Records of shop_cart
-- ----------------------------
INSERT INTO `shop_cart` VALUES ('1', '1', '2', null);

-- ----------------------------
-- Table structure for `shop_cart_item`
-- ----------------------------
DROP TABLE IF EXISTS `shop_cart_item`;
CREATE TABLE `shop_cart_item` (
  `cartitem_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `website_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '站点ID',
  `cart_id` bigint(20) NOT NULL COMMENT '购物车ID',
  `product_id` bigint(20) NOT NULL COMMENT '产品ID',
  `productFash_id` bigint(20) DEFAULT NULL COMMENT '商品款式外键（新加的[wangzewu]）',
  `count` int(11) NOT NULL COMMENT '数量',
  `score` int(11) DEFAULT NULL COMMENT '购物车商品预送积分',
  `popularity_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`cartitem_id`),
  KEY `fk_shop_cartitem_product` (`product_id`),
  KEY `fk_shop_cartitem_website` (`website_id`),
  KEY `fk_shop_cartitem_cart` (`cart_id`),
  KEY `fk_shop_cartitem_productFash` (`productFash_id`),
  CONSTRAINT `fk_shop_cartitem_cart` FOREIGN KEY (`cart_id`) REFERENCES `shop_cart` (`cart_id`),
  CONSTRAINT `fk_shop_cartitem_product` FOREIGN KEY (`product_id`) REFERENCES `shop_product` (`product_id`),
  CONSTRAINT `fk_shop_cartitem_productFash` FOREIGN KEY (`productFash_id`) REFERENCES `shop_product_fashion` (`fashion_id`),
  CONSTRAINT `fk_shop_cartitem_website` FOREIGN KEY (`website_id`) REFERENCES `core_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='购物车';

-- ----------------------------
-- Records of shop_cart_item
-- ----------------------------
INSERT INTO `shop_cart_item` VALUES ('9', '1', '1', '7', null, '2', '0', null);

-- ----------------------------
-- Table structure for `shop_category`
-- ----------------------------
DROP TABLE IF EXISTS `shop_category`;
CREATE TABLE `shop_category` (
  `category_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `ptype_id` bigint(20) NOT NULL COMMENT '类型ID',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父类别ID',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `path` varchar(100) NOT NULL COMMENT '访问路径',
  `lft` int(11) NOT NULL DEFAULT '1' COMMENT '树左边',
  `rgt` int(11) NOT NULL DEFAULT '2' COMMENT '树右边',
  `priority` int(11) NOT NULL DEFAULT '10' COMMENT '排列顺序',
  `keywords` varchar(255) DEFAULT NULL COMMENT '页面关键字',
  `description` varchar(255) DEFAULT NULL COMMENT '页面描述',
  `tpl_channel` varchar(50) DEFAULT NULL COMMENT '栏目页模板',
  `tpl_content` varchar(50) DEFAULT NULL COMMENT '内容页模板',
  `image_path` varchar(100) DEFAULT NULL COMMENT '图片路径',
  `title` varchar(255) DEFAULT NULL COMMENT '页面标题',
  `is_colorsize` tinyint(1) DEFAULT '0' COMMENT '是否需要尺寸和样式',
  PRIMARY KEY (`category_id`),
  KEY `fk_shop_category_parent` (`parent_id`),
  KEY `fk_shop_category_ptype` (`ptype_id`),
  KEY `fk_shop_cateory_website` (`website_id`),
  CONSTRAINT `fk_shop_category_parent` FOREIGN KEY (`parent_id`) REFERENCES `shop_category` (`category_id`),
  CONSTRAINT `fk_shop_category_ptype` FOREIGN KEY (`ptype_id`) REFERENCES `shop_ptype` (`ptype_id`),
  CONSTRAINT `fk_shop_cateory_website` FOREIGN KEY (`website_id`) REFERENCES `core_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='类别';

-- ----------------------------
-- Records of shop_category
-- ----------------------------
INSERT INTO `shop_category` VALUES ('22', '1', '10', null, '电器', 'dq', '1', '2', '10', '', '', '', '', '', '', '0');
INSERT INTO `shop_category` VALUES ('23', '1', '11', null, '服装', 'fz', '3', '4', '10', '', '', '', '', '', '', '0');
INSERT INTO `shop_category` VALUES ('24', '1', '12', null, '运动', 'yd', '5', '6', '10', '', '', '', '', '', '', '0');
INSERT INTO `shop_category` VALUES ('25', '1', '13', null, '母婴', 'my', '7', '8', '10', '', '', '', '', '', '', '0');
INSERT INTO `shop_category` VALUES ('26', '1', '14', null, '食品', 'sp', '9', '10', '10', '', '', '', '', '', '', '0');

-- ----------------------------
-- Table structure for `shop_category_attr`
-- ----------------------------
DROP TABLE IF EXISTS `shop_category_attr`;
CREATE TABLE `shop_category_attr` (
  `category_id` bigint(20) NOT NULL DEFAULT '0',
  `attr_name` varchar(30) NOT NULL DEFAULT '' COMMENT '名称',
  `attr_value` varchar(255) DEFAULT NULL COMMENT '值',
  KEY `fk_shop_category_attr` (`category_id`),
  CONSTRAINT `fK_shop_category_attr` FOREIGN KEY (`category_id`) REFERENCES `shop_category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='jspgou类型扩展属性表';

-- ----------------------------
-- Records of shop_category_attr
-- ----------------------------

-- ----------------------------
-- Table structure for `shop_category_brand`
-- ----------------------------
DROP TABLE IF EXISTS `shop_category_brand`;
CREATE TABLE `shop_category_brand` (
  `brand_id` bigint(22) NOT NULL DEFAULT '0',
  `category_id` bigint(22) NOT NULL DEFAULT '0',
  PRIMARY KEY (`brand_id`,`category_id`),
  KEY `fk_shop_category_brand` (`category_id`),
  CONSTRAINT `fk_shop_brand_category` FOREIGN KEY (`brand_id`) REFERENCES `shop_brand` (`brand_id`),
  CONSTRAINT `fk_shop_category_brand` FOREIGN KEY (`category_id`) REFERENCES `shop_category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_category_brand
-- ----------------------------
INSERT INTO `shop_category_brand` VALUES ('4', '22');
INSERT INTO `shop_category_brand` VALUES ('5', '22');
INSERT INTO `shop_category_brand` VALUES ('8', '23');
INSERT INTO `shop_category_brand` VALUES ('17', '23');
INSERT INTO `shop_category_brand` VALUES ('13', '24');
INSERT INTO `shop_category_brand` VALUES ('1', '25');
INSERT INTO `shop_category_brand` VALUES ('10', '25');

-- ----------------------------
-- Table structure for `shop_category_property`
-- ----------------------------
DROP TABLE IF EXISTS `shop_category_property`;
CREATE TABLE `shop_category_property` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `category_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '关联类型Id',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '属性名称',
  `is_start` int(11) NOT NULL DEFAULT '0' COMMENT '是否启用',
  `is_notNull` int(11) NOT NULL DEFAULT '0' COMMENT '是否必填',
  `priority` varchar(255) NOT NULL DEFAULT '10' COMMENT '排序',
  PRIMARY KEY (`Id`),
  KEY `fk_shop_category_property` (`category_id`),
  CONSTRAINT `fk_shop_category_property` FOREIGN KEY (`category_id`) REFERENCES `shop_category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='类型属性表';

-- ----------------------------
-- Records of shop_category_property
-- ----------------------------

-- ----------------------------
-- Table structure for `shop_category_sdtype`
-- ----------------------------
DROP TABLE IF EXISTS `shop_category_sdtype`;
CREATE TABLE `shop_category_sdtype` (
  `category_id` bigint(20) NOT NULL DEFAULT '0',
  `standardtype_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`category_id`,`standardtype_id`),
  KEY `fk_shop_category_sdtype` (`category_id`),
  KEY `fk_shop_sdtype_category` (`standardtype_id`),
  CONSTRAINT `fk_shop_category_sdtype` FOREIGN KEY (`category_id`) REFERENCES `shop_category` (`category_id`),
  CONSTRAINT `fk_shop_sdtype_category` FOREIGN KEY (`standardtype_id`) REFERENCES `standard_type` (`standardtype_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='类别和规则类型关联表';

-- ----------------------------
-- Records of shop_category_sdtype
-- ----------------------------

-- ----------------------------
-- Table structure for `shop_channel`
-- ----------------------------
DROP TABLE IF EXISTS `shop_channel`;
CREATE TABLE `shop_channel` (
  `channel_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `website_id` bigint(20) NOT NULL,
  `path` varchar(50) DEFAULT NULL COMMENT '访问路径',
  `name` varchar(100) NOT NULL COMMENT '栏目名称',
  `type` int(11) NOT NULL COMMENT '栏目类型',
  `outer_url` varchar(255) DEFAULT NULL COMMENT '外部链接',
  `is_display` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否显示',
  `tpl_channel` varchar(50) DEFAULT NULL COMMENT '栏目页模板',
  `tpl_content` varchar(50) DEFAULT NULL COMMENT '内容页模板',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父栏目ID',
  `lft` int(11) NOT NULL DEFAULT '1' COMMENT '树左边',
  `rgt` int(11) NOT NULL DEFAULT '2' COMMENT '树右边',
  `is_blank` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否新窗口打开',
  `priority` int(11) NOT NULL DEFAULT '10' COMMENT '排列顺序',
  `param1` varchar(255) DEFAULT NULL COMMENT '扩展数据1',
  `param2` varchar(255) DEFAULT NULL COMMENT '扩展数据2',
  `param3` varchar(255) DEFAULT NULL COMMENT '扩展数据3',
  PRIMARY KEY (`channel_id`),
  KEY `fk_shop_channel_website` (`website_id`),
  KEY `fk_shop_channel_parent` (`parent_id`),
  CONSTRAINT `fk_shop_channel_parent` FOREIGN KEY (`parent_id`) REFERENCES `shop_channel` (`channel_id`),
  CONSTRAINT `fk_shop_channel_website` FOREIGN KEY (`website_id`) REFERENCES `core_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='商城栏目表';

-- ----------------------------
-- Records of shop_channel
-- ----------------------------
INSERT INTO `shop_channel` VALUES ('1', '1', 'xsrm', '新手入门', '2', null, '1', '/channel/notice_model.html', '/article/notice_content.html', null, '1', '2', '0', '10', '', '', '');
INSERT INTO `shop_channel` VALUES ('2', '1', 'wzgg', '网站公告', '2', null, '1', '/channel/help_model.html', '/article/help_content.html', null, '3', '4', '0', '10', '', '', '');
INSERT INTO `shop_channel` VALUES ('3', '1', 'news', '新闻中心', '2', null, '1', '', '', null, '5', '6', '0', '10', '', '', '');
INSERT INTO `shop_channel` VALUES ('4', '1', 'remen', '热门促销', '2', null, '1', '/channel/news_model.html', '/article/news_content.html', null, '7', '8', '0', '10', '', '', '');
INSERT INTO `shop_channel` VALUES ('5', '1', 'ps', '配送范围及时间', '2', null, '1', '', '', null, '9', '10', '0', '10', '', '', '');
INSERT INTO `shop_channel` VALUES ('6', '1', 'zffs', '支付方式', '2', null, '0', '', '', null, '11', '12', '0', '10', '', '', '');
INSERT INTO `shop_channel` VALUES ('7', '1', 'gsjj', '公司简介', '1', null, '1', '/channel/single.html', null, null, '13', '14', '0', '10', '', '', '');

-- ----------------------------
-- Table structure for `shop_channel_content`
-- ----------------------------
DROP TABLE IF EXISTS `shop_channel_content`;
CREATE TABLE `shop_channel_content` (
  `channel_id` bigint(20) NOT NULL,
  `content` longtext,
  KEY `fk_shop_content_channel` (`channel_id`),
  CONSTRAINT `fk_shop_content_channel` FOREIGN KEY (`channel_id`) REFERENCES `shop_channel` (`channel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='栏目内容';

-- ----------------------------
-- Records of shop_channel_content
-- ----------------------------
INSERT INTO `shop_channel_content` VALUES ('1', '');
INSERT INTO `shop_channel_content` VALUES ('2', '');
INSERT INTO `shop_channel_content` VALUES ('3', '');
INSERT INTO `shop_channel_content` VALUES ('4', '');
INSERT INTO `shop_channel_content` VALUES ('5', '');
INSERT INTO `shop_channel_content` VALUES ('6', '');

-- ----------------------------
-- Table structure for `shop_collect`
-- ----------------------------
DROP TABLE IF EXISTS `shop_collect`;
CREATE TABLE `shop_collect` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL COMMENT '收藏人',
  `product_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '商品Id',
  `fashion_id` bigint(20) DEFAULT NULL COMMENT '收藏商品款式',
  `time` datetime DEFAULT NULL COMMENT '收藏时间',
  PRIMARY KEY (`Id`),
  KEY `fk_shop_collect_member` (`member_id`),
  KEY `fk_shop_collect_product` (`product_id`),
  KEY `fk_shop_collect_fashion` (`fashion_id`),
  CONSTRAINT `fk_shop_collect_fashion` FOREIGN KEY (`fashion_id`) REFERENCES `shop_product_fashion` (`fashion_id`),
  CONSTRAINT `fk_shop_collect_member` FOREIGN KEY (`member_id`) REFERENCES `shop_member` (`member_id`),
  CONSTRAINT `fk_shop_collect_product` FOREIGN KEY (`product_id`) REFERENCES `shop_product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='收藏商品';

-- ----------------------------
-- Records of shop_collect
-- ----------------------------

-- ----------------------------
-- Table structure for `shop_config`
-- ----------------------------
DROP TABLE IF EXISTS `shop_config`;
CREATE TABLE `shop_config` (
  `config_id` bigint(20) NOT NULL,
  `shop_price_name` varchar(50) NOT NULL DEFAULT '商城价' COMMENT '商城价名称',
  `market_price_name` varchar(50) NOT NULL DEFAULT '市场价' COMMENT '市场价名称',
  `favorite_size` int(11) NOT NULL DEFAULT '200' COMMENT '收藏夹大小',
  `register_group_id` bigint(20) NOT NULL COMMENT '默认注册会员组ID',
  `register_auto` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否自动注册',
  PRIMARY KEY (`config_id`),
  KEY `fk_shop_config_group` (`register_group_id`),
  CONSTRAINT `fk_shop_config_group` FOREIGN KEY (`register_group_id`) REFERENCES `shop_member_group` (`group_id`),
  CONSTRAINT `fk_shop_config_website` FOREIGN KEY (`config_id`) REFERENCES `core_website` (`website_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城配置表';

-- ----------------------------
-- Records of shop_config
-- ----------------------------
INSERT INTO `shop_config` VALUES ('1', '商城价', '市场价', '200', '1', '1');

-- ----------------------------
-- Table structure for `shop_consult`
-- ----------------------------
DROP TABLE IF EXISTS `shop_consult`;
CREATE TABLE `shop_consult` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `consult` varchar(255) DEFAULT NULL COMMENT '咨询内容',
  `adminReplay` varchar(255) DEFAULT NULL COMMENT 'admin回复',
  `time` datetime DEFAULT NULL COMMENT '咨询时间',
  `member_id` bigint(11) DEFAULT NULL COMMENT '咨询会员',
  `product_id` bigint(11) DEFAULT NULL COMMENT '对应商品',
  PRIMARY KEY (`Id`),
  KEY `fk_product_consult` (`product_id`),
  KEY `fk_member_consult` (`member_id`),
  CONSTRAINT `fk_member_consult` FOREIGN KEY (`member_id`) REFERENCES `shop_member` (`member_id`),
  CONSTRAINT `fk_product_consult` FOREIGN KEY (`product_id`) REFERENCES `shop_product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='购买商品咨询表';

-- ----------------------------
-- Records of shop_consult
-- ----------------------------

-- ----------------------------
-- Table structure for `shop_coupon`
-- ----------------------------
DROP TABLE IF EXISTS `shop_coupon`;
CREATE TABLE `shop_coupon` (
  `Id` bigint(11) NOT NULL AUTO_INCREMENT,
  `website_id` bigint(20) DEFAULT NULL COMMENT '站点Id',
  `coupon_name` varchar(50) DEFAULT NULL COMMENT '优惠劵名称',
  `coupon_begintime` datetime DEFAULT NULL COMMENT '开始时间',
  `coupon_endTime` datetime DEFAULT NULL COMMENT '结束时间',
  `coupon_pic` varchar(50) DEFAULT NULL COMMENT '图片地址',
  `is_using` tinyint(3) DEFAULT NULL COMMENT '是否启用',
  `coupon_price` decimal(10,2) DEFAULT NULL COMMENT '优惠倦值',
  `coupon_leastPrice` decimal(10,2) DEFAULT NULL COMMENT '至少消费',
  `coupon_count` int(11) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`Id`),
  KEY `website_id` (`website_id`),
  CONSTRAINT `shop_coupon_ibfk_2` FOREIGN KEY (`website_id`) REFERENCES `core_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='优惠劵';

-- ----------------------------
-- Records of shop_coupon
-- ----------------------------
INSERT INTO `shop_coupon` VALUES ('1', '1', '优惠券', '2014-08-12 10:55:01', '2014-08-29 10:55:04', '/upload/201408/20141935bnbf.jpg', '1', '50.00', '0.00', '10');

-- ----------------------------
-- Table structure for `shop_dictionary`
-- ----------------------------
DROP TABLE IF EXISTS `shop_dictionary`;
CREATE TABLE `shop_dictionary` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) NOT NULL DEFAULT '0' COMMENT '字典表类型ID',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `priority` int(11) NOT NULL DEFAULT '10' COMMENT '排列顺序',
  PRIMARY KEY (`Id`),
  KEY `fk_dictionary_type` (`type_id`),
  CONSTRAINT `fk_dictionary_type` FOREIGN KEY (`type_id`) REFERENCES `shop_dictionary_type` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='字典表';

-- ----------------------------
-- Records of shop_dictionary
-- ----------------------------
INSERT INTO `shop_dictionary` VALUES ('1', '1', '学生', '10');
INSERT INTO `shop_dictionary` VALUES ('2', '1', '在职', '10');
INSERT INTO `shop_dictionary` VALUES ('3', '1', '自由职业', '10');
INSERT INTO `shop_dictionary` VALUES ('4', '1', '家庭主妇', '10');
INSERT INTO `shop_dictionary` VALUES ('5', '1', '退休', '10');
INSERT INTO `shop_dictionary` VALUES ('6', '2', '1人', '10');
INSERT INTO `shop_dictionary` VALUES ('7', '2', '2人', '10');
INSERT INTO `shop_dictionary` VALUES ('8', '2', '3人', '10');
INSERT INTO `shop_dictionary` VALUES ('9', '2', '4人以上', '10');
INSERT INTO `shop_dictionary` VALUES ('10', '3', '2000元及以下', '10');
INSERT INTO `shop_dictionary` VALUES ('11', '3', '2000―5000元(包含5000元)', '10');
INSERT INTO `shop_dictionary` VALUES ('12', '3', '5000―10000元(包含10000元)', '10');
INSERT INTO `shop_dictionary` VALUES ('13', '3', '10000―20000元(包含20000元)', '10');
INSERT INTO `shop_dictionary` VALUES ('14', '3', '20000―40000元(包含40000元)', '10');
INSERT INTO `shop_dictionary` VALUES ('15', '3', '40000元以上', '10');
INSERT INTO `shop_dictionary` VALUES ('16', '5', '高中以下', '1');
INSERT INTO `shop_dictionary` VALUES ('17', '5', '中专', '10');
INSERT INTO `shop_dictionary` VALUES ('18', '5', '大专', '10');
INSERT INTO `shop_dictionary` VALUES ('19', '5', '本科', '10');
INSERT INTO `shop_dictionary` VALUES ('20', '5', '硕士', '10');
INSERT INTO `shop_dictionary` VALUES ('21', '5', '博士', '10');
INSERT INTO `shop_dictionary` VALUES ('22', '4', '1年', '10');
INSERT INTO `shop_dictionary` VALUES ('23', '4', '2年', '10');
INSERT INTO `shop_dictionary` VALUES ('24', '4', '3年', '10');
INSERT INTO `shop_dictionary` VALUES ('25', '4', '4年以上', '10');
INSERT INTO `shop_dictionary` VALUES ('26', '6', '缺货', '10');
INSERT INTO `shop_dictionary` VALUES ('27', '6', '协商一致退款', '10');
INSERT INTO `shop_dictionary` VALUES ('28', '6', '未按约定时间发货', '10');
INSERT INTO `shop_dictionary` VALUES ('29', '6', '其他', '10');
INSERT INTO `shop_dictionary` VALUES ('30', '7', '卖家未发货，全额退款', '10');
INSERT INTO `shop_dictionary` VALUES ('31', '8', '质量有问题', '10');
INSERT INTO `shop_dictionary` VALUES ('32', '9', '我的账户', '10');
INSERT INTO `shop_dictionary` VALUES ('33', '9', '支付宝', '10');

-- ----------------------------
-- Table structure for `shop_dictionary_type`
-- ----------------------------
DROP TABLE IF EXISTS `shop_dictionary_type`;
CREATE TABLE `shop_dictionary_type` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '字典表的类型',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='字典表的类型';

-- ----------------------------
-- Records of shop_dictionary_type
-- ----------------------------
INSERT INTO `shop_dictionary_type` VALUES ('1', '身份');
INSERT INTO `shop_dictionary_type` VALUES ('2', '家庭成员');
INSERT INTO `shop_dictionary_type` VALUES ('3', '个人收入状况');
INSERT INTO `shop_dictionary_type` VALUES ('4', '工作年限');
INSERT INTO `shop_dictionary_type` VALUES ('5', '教育程度');
INSERT INTO `shop_dictionary_type` VALUES ('6', '未发货退款原因');
INSERT INTO `shop_dictionary_type` VALUES ('7', '退款类型');
INSERT INTO `shop_dictionary_type` VALUES ('8', '已收获退款原因');
INSERT INTO `shop_dictionary_type` VALUES ('9', '退款方式类型');

-- ----------------------------
-- Table structure for `shop_discuss`
-- ----------------------------
DROP TABLE IF EXISTS `shop_discuss`;
CREATE TABLE `shop_discuss` (
  `Id` bigint(22) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) DEFAULT NULL COMMENT '评论内容',
  `time` datetime DEFAULT NULL COMMENT '评论时间',
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员',
  `product_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  PRIMARY KEY (`Id`),
  KEY `fk_disucss_member` (`member_id`),
  KEY `fk_disucss_product` (`product_id`),
  CONSTRAINT `fk_disucss_member` FOREIGN KEY (`member_id`) REFERENCES `shop_member` (`member_id`),
  CONSTRAINT `fk_disucss_product` FOREIGN KEY (`product_id`) REFERENCES `shop_product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商品评论表';

-- ----------------------------
-- Records of shop_discuss
-- ----------------------------

-- ----------------------------
-- Table structure for `shop_exended`
-- ----------------------------
DROP TABLE IF EXISTS `shop_exended`;
CREATE TABLE `shop_exended` (
  `exended_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `field` varchar(255) DEFAULT NULL COMMENT '字段名称',
  `dataType` tinyint(3) NOT NULL DEFAULT '0' COMMENT '数据类型',
  `priority` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`exended_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='商品扩展属性';

-- ----------------------------
-- Records of shop_exended
-- ----------------------------
INSERT INTO `shop_exended` VALUES ('1', '价格', 'price', '2', '10');
INSERT INTO `shop_exended` VALUES ('2', '尺寸', 'measure', '2', '10');

-- ----------------------------
-- Table structure for `shop_exended_item`
-- ----------------------------
DROP TABLE IF EXISTS `shop_exended_item`;
CREATE TABLE `shop_exended_item` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '属性值',
  `priority` int(11) DEFAULT NULL COMMENT '排序',
  `exended_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_exended_item
-- ----------------------------
INSERT INTO `shop_exended_item` VALUES ('15', '1800-2699', null, '1');
INSERT INTO `shop_exended_item` VALUES ('16', '1300-1799', null, '1');
INSERT INTO `shop_exended_item` VALUES ('17', '2700-5499', null, '1');
INSERT INTO `shop_exended_item` VALUES ('18', '5500-9399', null, '1');
INSERT INTO `shop_exended_item` VALUES ('19', '700-1299', null, '1');
INSERT INTO `shop_exended_item` VALUES ('20', '9400以上', null, '1');
INSERT INTO `shop_exended_item` VALUES ('21', '0-699', null, '1');
INSERT INTO `shop_exended_item` VALUES ('22', '10.1英寸及以下', null, '2');
INSERT INTO `shop_exended_item` VALUES ('23', '11英寸', null, '2');
INSERT INTO `shop_exended_item` VALUES ('24', '12英寸', null, '2');
INSERT INTO `shop_exended_item` VALUES ('25', '13英寸', null, '2');
INSERT INTO `shop_exended_item` VALUES ('26', '14英寸', null, '2');
INSERT INTO `shop_exended_item` VALUES ('27', '15英寸', null, '2');
INSERT INTO `shop_exended_item` VALUES ('28', '16英寸', null, '2');
INSERT INTO `shop_exended_item` VALUES ('29', '17英寸17英寸以上', null, '2');

-- ----------------------------
-- Table structure for `shop_fashion_standard`
-- ----------------------------
DROP TABLE IF EXISTS `shop_fashion_standard`;
CREATE TABLE `shop_fashion_standard` (
  `fashion_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `standard_id` bigint(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`fashion_id`,`standard_id`),
  KEY `fk_shop_fashion_standard` (`fashion_id`),
  KEY `fk_shop_standard_fashion` (`standard_id`),
  CONSTRAINT `fk_shop_fashion_standard` FOREIGN KEY (`fashion_id`) REFERENCES `shop_product_fashion` (`fashion_id`),
  CONSTRAINT `fk_shop_standard_fashion` FOREIGN KEY (`standard_id`) REFERENCES `standard` (`standard_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='商品规格表';

-- ----------------------------
-- Records of shop_fashion_standard
-- ----------------------------

-- ----------------------------
-- Table structure for `shop_gift`
-- ----------------------------
DROP TABLE IF EXISTS `shop_gift`;
CREATE TABLE `shop_gift` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `giftName` varchar(100) DEFAULT NULL COMMENT '礼品名称',
  `giftScore` int(11) DEFAULT NULL COMMENT '礼品换取所需积分',
  `giftStock` int(11) DEFAULT NULL COMMENT '礼品库存',
  `giftPicture` varchar(100) DEFAULT NULL COMMENT '礼品图片',
  `isgift` tinyint(3) DEFAULT NULL COMMENT '是否发放',
  `introduced` longtext,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_gift
-- ----------------------------
INSERT INTO `shop_gift` VALUES ('1', '天天', '400', '1', '/upload/201407/09112222y6v6.png', '1', '');
INSERT INTO `shop_gift` VALUES ('2', 'test', '10000', '0', '/upload/201407/09112204ybg6.png', '1', '');

-- ----------------------------
-- Table structure for `shop_gift_exchange`
-- ----------------------------
DROP TABLE IF EXISTS `shop_gift_exchange`;
CREATE TABLE `shop_gift_exchange` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL COMMENT '会员',
  `gift_id` bigint(20) DEFAULT NULL COMMENT '礼品',
  `score` int(11) DEFAULT NULL COMMENT '积分',
  `amount` int(11) DEFAULT NULL COMMENT '数量',
  `create_time` datetime DEFAULT NULL COMMENT '兑换时间',
  `total_score` int(11) DEFAULT NULL COMMENT '总积分',
  `detailaddress` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `status` tinyint(3) DEFAULT NULL COMMENT '状态（1.待发货2.已发货3.完成）',
  `waybill` varchar(255) DEFAULT NULL COMMENT '货运单号',
  PRIMARY KEY (`Id`),
  KEY `fk_shop_exchange_member` (`member_id`),
  KEY `fk_shop_exchange_gift` (`gift_id`),
  CONSTRAINT `fk_shop_exchange_gift` FOREIGN KEY (`gift_id`) REFERENCES `shop_gift` (`Id`),
  CONSTRAINT `fk_shop_exchange_member` FOREIGN KEY (`member_id`) REFERENCES `shop_member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='积分兑换';

-- ----------------------------
-- Records of shop_gift_exchange
-- ----------------------------

-- ----------------------------
-- Table structure for `shop_keyword_q`
-- ----------------------------
DROP TABLE IF EXISTS `shop_keyword_q`;
CREATE TABLE `shop_keyword_q` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(50) DEFAULT NULL COMMENT '关键字',
  `times` int(11) DEFAULT NULL COMMENT '收索次数',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_keyword_q
-- ----------------------------
INSERT INTO `shop_keyword_q` VALUES ('5', '数码', '108');
INSERT INTO `shop_keyword_q` VALUES ('6', '服装', '43');
INSERT INTO `shop_keyword_q` VALUES ('7', '日用百货', '46');
INSERT INTO `shop_keyword_q` VALUES ('14', '梦特娇', '1');
INSERT INTO `shop_keyword_q` VALUES ('15', '钻石', '1');
INSERT INTO `shop_keyword_q` VALUES ('23', 'apple', '2');
INSERT INTO `shop_keyword_q` VALUES ('24', '手机', '3');
INSERT INTO `shop_keyword_q` VALUES ('27', '商品', '1');
INSERT INTO `shop_keyword_q` VALUES ('30', '电脑', '5');
INSERT INTO `shop_keyword_q` VALUES ('42', '谍影重重4', '1');
INSERT INTO `shop_keyword_q` VALUES ('43', '笔记本', '1');
INSERT INTO `shop_keyword_q` VALUES ('48', '璨经典奢华镶钻水晶开口戒 浅粉', '1');
INSERT INTO `shop_keyword_q` VALUES ('49', '璨经典奢华镶钻水晶开', '1');
INSERT INTO `shop_keyword_q` VALUES ('53', 'test', '2');
INSERT INTO `shop_keyword_q` VALUES ('54', '请输入关键词', '3');
INSERT INTO `shop_keyword_q` VALUES ('55', 'xie', '7');

-- ----------------------------
-- Table structure for `shop_logistics`
-- ----------------------------
DROP TABLE IF EXISTS `shop_logistics`;
CREATE TABLE `shop_logistics` (
  `logistics_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `web_url` varchar(255) DEFAULT NULL COMMENT '网址',
  `logo_path` varchar(255) DEFAULT NULL COMMENT 'LOGO',
  `priority` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`logistics_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='物流';

-- ----------------------------
-- Records of shop_logistics
-- ----------------------------
INSERT INTO `shop_logistics` VALUES ('4', '圆通速递', 'http://www.yto.net.cn', '/upload/201406/0513273912gq.png', '10');
INSERT INTO `shop_logistics` VALUES ('5', '顺丰速递', 'http://www.sf-express.com', '/upload/201406/05133220gtct.png', '10');

-- ----------------------------
-- Table structure for `shop_logistics_text`
-- ----------------------------
DROP TABLE IF EXISTS `shop_logistics_text`;
CREATE TABLE `shop_logistics_text` (
  `logistics_id` bigint(20) NOT NULL,
  `text` longtext COMMENT '详细信息',
  PRIMARY KEY (`logistics_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_logistics_text
-- ----------------------------
INSERT INTO `shop_logistics_text` VALUES ('4', '<p>圆通速递</p>');
INSERT INTO `shop_logistics_text` VALUES ('5', '<p>顺丰速递</p>');

-- ----------------------------
-- Table structure for `shop_member`
-- ----------------------------
DROP TABLE IF EXISTS `shop_member`;
CREATE TABLE `shop_member` (
  `member_id` bigint(20) NOT NULL,
  `group_id` bigint(20) NOT NULL COMMENT '会员组ID',
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `userdegree_id` bigint(20) DEFAULT NULL COMMENT '字典表身份ID',
  `familymembers_id` bigint(20) DEFAULT NULL COMMENT '字典表家庭成员',
  `incomedesc_id` bigint(20) DEFAULT NULL COMMENT '字典表个人收入情况',
  `workseniority_id` bigint(20) DEFAULT NULL COMMENT '字典表工作年限',
  `degree_id` bigint(20) DEFAULT NULL COMMENT '字典表教育程度',
  `realname` varchar(100) DEFAULT NULL COMMENT '真实姓名',
  `gender` tinyint(1) DEFAULT NULL COMMENT '性别',
  `birthday` date DEFAULT NULL COMMENT '生日日期',
  `mobile` varchar(30) DEFAULT NULL COMMENT '手机',
  `tel` varchar(30) DEFAULT NULL COMMENT '电话',
  `score` int(11) DEFAULT NULL COMMENT '积分',
  `freezeScore` int(11) DEFAULT '0' COMMENT '冻结的积分',
  `money` double(11,2) DEFAULT NULL COMMENT '账户金额',
  `company` varchar(100) DEFAULT NULL COMMENT '公司',
  `avatar` varchar(100) DEFAULT NULL COMMENT '会员头像',
  `marriage` tinyint(1) DEFAULT NULL COMMENT '婚姻状况(空,保密;1,已婚;0,未婚)',
  `is_car` tinyint(1) DEFAULT NULL COMMENT '是否有车(0:无 1：有)',
  `position` varchar(255) DEFAULT NULL COMMENT '职位',
  `schoolTag` varchar(255) DEFAULT NULL COMMENT '毕业学校',
  `schoolTagDate` date DEFAULT NULL COMMENT '毕业时间',
  `favoriteBrand` varchar(255) DEFAULT NULL COMMENT '最喜爱的品牌',
  `favoriteStar` varchar(255) DEFAULT NULL COMMENT '最喜爱的明星',
  `favoriteMovie` varchar(255) DEFAULT NULL COMMENT '最喜爱的电影',
  `favoritePersonage` varchar(255) DEFAULT NULL COMMENT '最喜爱的人物',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`member_id`),
  KEY `fk_shop_member_mgroup` (`group_id`),
  KEY `fk_shop_member_website` (`website_id`),
  KEY `fk_shop_dictionary_userdegree` (`userdegree_id`),
  KEY `fk_shop_dictionary_familymembers` (`familymembers_id`),
  KEY `fk_shop_dictionary_incomedesc` (`incomedesc_id`),
  KEY `fk_shop_dictionary_workseniority` (`workseniority_id`),
  KEY `fk_shop_dictionary_degree` (`degree_id`),
  CONSTRAINT `fk_shop_dictionary_familymembers` FOREIGN KEY (`familymembers_id`) REFERENCES `shop_dictionary` (`Id`),
  CONSTRAINT `fk_shop_dictionary_incomedesc` FOREIGN KEY (`incomedesc_id`) REFERENCES `shop_dictionary` (`Id`),
  CONSTRAINT `fk_shop_dictionary_userdegree` FOREIGN KEY (`userdegree_id`) REFERENCES `shop_dictionary` (`Id`),
  CONSTRAINT `fk_shop_dictionary_workseniority` FOREIGN KEY (`workseniority_id`) REFERENCES `shop_dictionary` (`Id`),
  CONSTRAINT `fk_shop_member_mgroup` FOREIGN KEY (`group_id`) REFERENCES `shop_member_group` (`group_id`),
  CONSTRAINT `fk_shop_member_website` FOREIGN KEY (`website_id`) REFERENCES `core_website` (`website_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商城会员';

-- ----------------------------
-- Records of shop_member
-- ----------------------------
INSERT INTO `shop_member` VALUES ('1', '3', '1', null, null, null, null, null, null, null, null, '', '', '40', '0', '4798.00', null, '/upload/201406/18140736wuc5.jpg', null, null, null, null, null, null, null, null, null, null);
INSERT INTO `shop_member` VALUES ('4', '2', '1', null, null, null, null, null, null, null, null, null, null, '10', '0', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `shop_member` VALUES ('5', '2', '1', null, null, null, null, null, null, null, null, null, null, '10', '0', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `shop_member` VALUES ('6', '1', '1', null, null, null, null, null, null, null, null, null, null, '0', '0', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `shop_member` VALUES ('7', '1', '1', null, null, null, null, null, null, null, null, null, null, '0', '0', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `shop_member` VALUES ('8', '1', '1', null, null, null, null, null, null, null, null, null, null, '0', '0', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `shop_member` VALUES ('9', '1', '1', null, null, null, null, null, null, null, null, null, null, '0', '0', null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `shop_member_address`
-- ----------------------------
DROP TABLE IF EXISTS `shop_member_address`;
CREATE TABLE `shop_member_address` (
  `address_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) NOT NULL COMMENT '会员',
  `province_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '省份',
  `city_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '城市Id',
  `country_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '县Id',
  `username` varchar(100) NOT NULL DEFAULT '' COMMENT '姓名',
  `gender` tinyint(1) DEFAULT NULL COMMENT '性别',
  `detailaddress` varchar(255) NOT NULL DEFAULT '' COMMENT '详细地址',
  `postcode` varchar(20) DEFAULT NULL COMMENT '邮编',
  `tel` varchar(30) DEFAULT NULL COMMENT '手机',
  `areacode` varchar(30) DEFAULT NULL COMMENT '电话区号',
  `phone` varchar(255) DEFAULT NULL COMMENT '电话部分',
  `extnumber` varchar(255) DEFAULT NULL COMMENT '分机号',
  `is_default` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否是默认地址',
  PRIMARY KEY (`address_id`),
  KEY `fk_address_province` (`province_id`),
  KEY `fk_address_city` (`city_id`),
  KEY `fk_address_country` (`country_id`),
  KEY `fk_address_member` (`member_id`),
  CONSTRAINT `fk_address_city` FOREIGN KEY (`city_id`) REFERENCES `address` (`Id`),
  CONSTRAINT `fk_address_country` FOREIGN KEY (`country_id`) REFERENCES `address` (`Id`),
  CONSTRAINT `fk_address_member` FOREIGN KEY (`member_id`) REFERENCES `shop_member` (`member_id`),
  CONSTRAINT `fk_address_province` FOREIGN KEY (`province_id`) REFERENCES `address` (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='地址';

-- ----------------------------
-- Records of shop_member_address
-- ----------------------------
INSERT INTO `shop_member_address` VALUES ('1', '1', '1', '7', '8', 'test', '1', 'test', '330000', '1599004480', '', '', '', '1');
INSERT INTO `shop_member_address` VALUES ('2', '9', '10', '15', '16', '测试', '1', 'XXX路', '100211', '17098158159', '', '', '', '1');

-- ----------------------------
-- Table structure for `shop_member_coupon`
-- ----------------------------
DROP TABLE IF EXISTS `shop_member_coupon`;
CREATE TABLE `shop_member_coupon` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(11) DEFAULT NULL COMMENT '优惠劵验证码',
  `is_use` tinyint(3) DEFAULT NULL COMMENT '是否使用',
  `member_id` bigint(20) DEFAULT NULL COMMENT '对应的会员',
  `coupon_id` bigint(20) DEFAULT NULL COMMENT '对应的优惠劵',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_member_coupon
-- ----------------------------
INSERT INTO `shop_member_coupon` VALUES ('1', null, '1', '1', '1');

-- ----------------------------
-- Table structure for `shop_member_favorite`
-- ----------------------------
DROP TABLE IF EXISTS `shop_member_favorite`;
CREATE TABLE `shop_member_favorite` (
  `product_id` bigint(20) NOT NULL,
  `member_id` bigint(20) NOT NULL,
  PRIMARY KEY (`product_id`,`member_id`),
  KEY `fk_shop_favorite_member` (`member_id`),
  KEY `fk_shop_favorite_product` (`product_id`),
  CONSTRAINT `fk_shop_favorite_member` FOREIGN KEY (`member_id`) REFERENCES `shop_member` (`member_id`),
  CONSTRAINT `fk_shop_favorite_product` FOREIGN KEY (`product_id`) REFERENCES `shop_product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品收藏夹';

-- ----------------------------
-- Records of shop_member_favorite
-- ----------------------------

-- ----------------------------
-- Table structure for `shop_member_group`
-- ----------------------------
DROP TABLE IF EXISTS `shop_member_group`;
CREATE TABLE `shop_member_group` (
  `group_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `score` int(11) NOT NULL DEFAULT '0' COMMENT '最低积分',
  `discount` int(11) NOT NULL DEFAULT '100' COMMENT '折扣',
  PRIMARY KEY (`group_id`),
  KEY `fk_shop_mgroup_website` (`website_id`),
  CONSTRAINT `fk_shop_mgroup_website` FOREIGN KEY (`website_id`) REFERENCES `core_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='商城会员组';

-- ----------------------------
-- Records of shop_member_group
-- ----------------------------
INSERT INTO `shop_member_group` VALUES ('1', '1', '普通会员', '0', '90');
INSERT INTO `shop_member_group` VALUES ('2', '1', '高级会员', '5', '80');
INSERT INTO `shop_member_group` VALUES ('3', '1', '金牌会员', '10', '70');

-- ----------------------------
-- Table structure for `shop_money`
-- ----------------------------
DROP TABLE IF EXISTS `shop_money`;
CREATE TABLE `shop_money` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '会员Id',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '来源/用途',
  `money` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '金额',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0是支出，1,是收入',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`Id`),
  KEY `fk_shop_money_member` (`member_id`),
  CONSTRAINT `fk_shop_money_member` FOREIGN KEY (`member_id`) REFERENCES `shop_member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户记录';

-- ----------------------------
-- Records of shop_money
-- ----------------------------

-- ----------------------------
-- Table structure for `shop_order`
-- ----------------------------
DROP TABLE IF EXISTS `shop_order`;
CREATE TABLE `shop_order` (
  `order_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `payment_id` bigint(20) NOT NULL COMMENT '支付方式',
  `member_id` bigint(20) NOT NULL COMMENT '会员ID',
  `shipping_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '配送方式',
  `code` bigint(20) NOT NULL DEFAULT '0' COMMENT '订单号',
  `status` int(11) DEFAULT '0' COMMENT '订单状态',
  `payment_status` int(11) DEFAULT '0' COMMENT '支付状态',
  `shipping_status` int(11) DEFAULT NULL COMMENT '配送状态',
  `product_price` double(11,2) DEFAULT NULL COMMENT '商品总价格',
  `weight` int(11) NOT NULL DEFAULT '0' COMMENT '商品重量',
  `freight` double(11,2) DEFAULT NULL COMMENT '运费',
  `total` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '订单总额',
  `score` int(11) NOT NULL DEFAULT '0' COMMENT '订单积分',
  `ip` varchar(50) NOT NULL COMMENT 'IP地址',
  `create_time` datetime NOT NULL COMMENT '下单日期',
  `finished_time` datetime DEFAULT NULL COMMENT '结单日期',
  `last_modified` datetime NOT NULL COMMENT '最后修改时间',
  `comments` varchar(255) DEFAULT NULL COMMENT '订单附言',
  `shipping_time` datetime DEFAULT NULL COMMENT '发货日期',
  `coupon_price` double(11,2) DEFAULT NULL,
  `productName` varchar(500) DEFAULT NULL COMMENT '订单商品名字的组合',
  `tradeno` varchar(255) DEFAULT NULL COMMENT '支付宝交易号',
  `return_id` bigint(1) DEFAULT NULL COMMENT '退款Id',
  `receive_name` varchar(255) DEFAULT NULL COMMENT '收货人姓名',
  `receive_address` varchar(255) DEFAULT NULL COMMENT '收货人地址',
  `receive_zip` varchar(255) DEFAULT NULL COMMENT '收货人邮编',
  `receive_phone` varchar(255) DEFAULT NULL COMMENT '收货人电话号码',
  `receive_mobile` varchar(255) DEFAULT NULL COMMENT '收货人手机号码',
  `payment_code` varchar(255) DEFAULT NULL COMMENT '支付方式',
  PRIMARY KEY (`order_id`),
  KEY `fk_shop_order_member` (`member_id`),
  KEY `fk_shop_order_payment` (`payment_id`),
  KEY `fk_shop_order_website` (`website_id`),
  KEY `fk_shop_order_shipping` (`shipping_id`),
  CONSTRAINT `fk_shop_order_member` FOREIGN KEY (`member_id`) REFERENCES `shop_member` (`member_id`),
  CONSTRAINT `fk_shop_order_payment` FOREIGN KEY (`payment_id`) REFERENCES `shop_payment` (`payment_id`),
  CONSTRAINT `fk_shop_order_shipping` FOREIGN KEY (`shipping_id`) REFERENCES `shop_shipping` (`shipping_id`),
  CONSTRAINT `fk_shop_order_website` FOREIGN KEY (`website_id`) REFERENCES `core_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COMMENT='订单';

-- ----------------------------
-- Records of shop_order
-- ----------------------------

-- ----------------------------
-- Table structure for `shop_order_gathering`
-- ----------------------------
DROP TABLE IF EXISTS `shop_order_gathering`;
CREATE TABLE `shop_order_gathering` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL,
  `admin_id` bigint(20) DEFAULT NULL,
  `bank` varchar(255) DEFAULT NULL COMMENT '银行',
  `accounts` varchar(255) DEFAULT NULL COMMENT '帐号',
  `money` double DEFAULT NULL COMMENT '金额',
  `drawee` varchar(255) DEFAULT NULL COMMENT '付款人',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='收款';

-- ----------------------------
-- Records of shop_order_gathering
-- ----------------------------
INSERT INTO `shop_order_gathering` VALUES ('1', '23', '1', 'ddd', '11', '11', '11', '');

-- ----------------------------
-- Table structure for `shop_order_item`
-- ----------------------------
DROP TABLE IF EXISTS `shop_order_item`;
CREATE TABLE `shop_order_item` (
  `orderitem_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `website_id` bigint(20) NOT NULL DEFAULT '0',
  `product_id` bigint(20) NOT NULL,
  `productFash_id` bigint(11) DEFAULT NULL COMMENT '商品款式外键（wangzewu）',
  `count` int(11) NOT NULL COMMENT '数量',
  `sale_price` double(11,2) DEFAULT NULL COMMENT '销售价',
  `member_price` double(11,2) DEFAULT NULL COMMENT '会员价',
  `cost_price` double(11,2) DEFAULT NULL COMMENT '成本价',
  `final_price` double(11,2) DEFAULT NULL COMMENT '成交价',
  `seckillprice` double(11,2) DEFAULT NULL COMMENT '秒杀价',
  PRIMARY KEY (`orderitem_id`),
  KEY `fk_shop_item_order` (`order_id`),
  KEY `fk_shop_orderitem_website` (`website_id`),
  KEY `fk_shop_orderitem_product` (`product_id`),
  KEY `fk_shop_orderitem_productFash` (`productFash_id`),
  CONSTRAINT `fk_shop_item_order` FOREIGN KEY (`order_id`) REFERENCES `shop_order` (`order_id`),
  CONSTRAINT `fk_shop_orderitem_product` FOREIGN KEY (`product_id`) REFERENCES `shop_product` (`product_id`),
  CONSTRAINT `fk_shop_orderitem_productFash` FOREIGN KEY (`productFash_id`) REFERENCES `shop_product_fashion` (`fashion_id`),
  CONSTRAINT `fk_shop_orderitem_website` FOREIGN KEY (`website_id`) REFERENCES `core_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='订单项';

-- ----------------------------
-- Records of shop_order_item
-- ----------------------------

-- ----------------------------
-- Table structure for `shop_order_return`
-- ----------------------------
DROP TABLE IF EXISTS `shop_order_return`;
CREATE TABLE `shop_order_return` (
  `return_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `code` varchar(255) NOT NULL DEFAULT '' COMMENT '退款编码',
  `order_id` bigint(20) NOT NULL DEFAULT '0',
  `reason_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '退款选项原因',
  `payType` int(11) NOT NULL DEFAULT '0' COMMENT '退款支付类型',
  `returnType` int(11) NOT NULL DEFAULT '0' COMMENT '退款类型',
  `alipayId` varchar(255) DEFAULT NULL COMMENT '支付宝账号',
  `reason` varchar(500) DEFAULT NULL COMMENT '退款书面原因',
  `status` int(3) NOT NULL DEFAULT '0' COMMENT '1待审，2已审，3拒绝',
  `money` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '退款数额',
  `sellerMoney` double(11,2) DEFAULT '0.00' COMMENT '支付给卖家的钱',
  `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '申请退款时间',
  `finished_time` datetime DEFAULT NULL COMMENT '退款完成时间',
  PRIMARY KEY (`return_id`),
  KEY `fk_shop_order_return` (`order_id`),
  KEY `fk_shop_reason_return` (`reason_id`),
  KEY `fk_shop_paytype_return` (`payType`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='FComment';

-- ----------------------------
-- Records of shop_order_return
-- ----------------------------
INSERT INTO `shop_order_return` VALUES ('1', '1408958255351', '23', '31', '2', '1', '', '', '6', '0.01', '0.00', '2014-08-25 17:17:35', null);

-- ----------------------------
-- Table structure for `shop_order_return_picture`
-- ----------------------------
DROP TABLE IF EXISTS `shop_order_return_picture`;
CREATE TABLE `shop_order_return_picture` (
  `return_id` bigint(20) NOT NULL DEFAULT '0',
  `priority` int(11) NOT NULL DEFAULT '0' COMMENT '排列顺序',
  `img_path` varchar(100) NOT NULL DEFAULT '' COMMENT '图片地址',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`return_id`,`priority`),
  KEY `fk_shop_order_return_picture` (`return_id`),
  CONSTRAINT `fk_shop_order_return_picture` FOREIGN KEY (`return_id`) REFERENCES `shop_order_return` (`return_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退款图片表';

-- ----------------------------
-- Records of shop_order_return_picture
-- ----------------------------
INSERT INTO `shop_order_return_picture` VALUES ('1', '0', '/upload/201408/25171730ktna.jpg', '');

-- ----------------------------
-- Table structure for `shop_order_shipments`
-- ----------------------------
DROP TABLE IF EXISTS `shop_order_shipments`;
CREATE TABLE `shop_order_shipments` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL,
  `admin_id` bigint(20) DEFAULT NULL,
  `waybill` varchar(255) DEFAULT NULL COMMENT '货运单号',
  `money` double DEFAULT NULL COMMENT '金额',
  `receiving` varchar(255) DEFAULT NULL COMMENT '收货信息',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='发货信息';

-- ----------------------------
-- Records of shop_order_shipments
-- ----------------------------
INSERT INTO `shop_order_shipments` VALUES ('8', '4', '1', '1111111', '1.11111111111111e17', null, '111111111111111');
INSERT INTO `shop_order_shipments` VALUES ('9', '1', '1', '111111111111', '20', null, 'qqqqqqqqqqqqqqqqqqqqqqqqq');
INSERT INTO `shop_order_shipments` VALUES ('10', '1', '1', '111111111', '11111111', null, '');
INSERT INTO `shop_order_shipments` VALUES ('11', '23', '1', 'test', '1', null, 'ddddddddddddddddd');

-- ----------------------------
-- Table structure for `shop_pay`
-- ----------------------------
DROP TABLE IF EXISTS `shop_pay`;
CREATE TABLE `shop_pay` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(100) DEFAULT NULL COMMENT '公司地址',
  `bankNum` varchar(100) DEFAULT NULL COMMENT '银行账户',
  `bankid` varchar(100) DEFAULT NULL COMMENT '网银在线商户id',
  `bankkey` varchar(100) DEFAULT NULL COMMENT '网银在线key',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='订单付款';

-- ----------------------------
-- Records of shop_pay
-- ----------------------------
INSERT INTO `shop_pay` VALUES ('1', '南昌×××.', '农业银行:3621×××,工商银行:121×××', '22304030', 'abcdefg');

-- ----------------------------
-- Table structure for `shop_payment`
-- ----------------------------
DROP TABLE IF EXISTS `shop_payment`;
CREATE TABLE `shop_payment` (
  `payment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `name` varchar(150) NOT NULL COMMENT '支付名称',
  `description` longtext COMMENT '描述',
  `priority` int(11) NOT NULL DEFAULT '10' COMMENT '排列顺序',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用',
  `is_default` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否是默认',
  `type` tinyint(3) DEFAULT NULL COMMENT '支付类型',
  `introduce` varchar(255) DEFAULT NULL COMMENT '介绍',
  PRIMARY KEY (`payment_id`),
  KEY `fk_shop_payment_website` (`website_id`),
  CONSTRAINT `fk_shop_payment_website` FOREIGN KEY (`website_id`) REFERENCES `core_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='支付方式';

-- ----------------------------
-- Records of shop_payment
-- ----------------------------
INSERT INTO `shop_payment` VALUES ('1', '1', '网上支付', '<p>支持支付宝、财付通、以及大多数网上银行支付</p>', '1', '0', '1', '1', '支持支付宝、财付通、以及大多数网上银行支付');
INSERT INTO `shop_payment` VALUES ('3', '1', '货到付款', '<p>由快递公司送货上门，您签收后直接将货款交付给快递员</p>', '10', '0', '0', '2', '由快递公司送货上门，您签收后直接将货款交付给快递员');

-- ----------------------------
-- Table structure for `shop_payment_plugins`
-- ----------------------------
DROP TABLE IF EXISTS `shop_payment_plugins`;
CREATE TABLE `shop_payment_plugins` (
  `plugins_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `description` longtext COMMENT '描述',
  `priority` int(11) DEFAULT NULL COMMENT '排列顺序',
  `partner` varchar(255) DEFAULT NULL COMMENT '合作身份者ID',
  `seller_email` varchar(255) DEFAULT NULL COMMENT '签约账号',
  `seller_key` varchar(255) DEFAULT NULL COMMENT '交易安全检验码',
  `img_path` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`plugins_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='支付插件';

-- ----------------------------
-- Records of shop_payment_plugins
-- ----------------------------
INSERT INTO `shop_payment_plugins` VALUES ('2', '支付宝担保交易', '', '2', '1111111111111111', '11111111111', 'cccccccccccccccccccccccccccccccc', '/upload/201408/29105140zh3l.gif', 'alipayPartner');
INSERT INTO `shop_payment_plugins` VALUES ('3', '支付宝即时交易', '', '1', '2222222222222222', '22222222222', 'eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee', '/upload/201408/291039531mhu.gif', 'alipay');

-- ----------------------------
-- Table structure for `shop_payment_shipping`
-- ----------------------------
DROP TABLE IF EXISTS `shop_payment_shipping`;
CREATE TABLE `shop_payment_shipping` (
  `payment_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `shipping_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`payment_id`,`shipping_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_payment_shipping
-- ----------------------------
INSERT INTO `shop_payment_shipping` VALUES ('1', '1');
INSERT INTO `shop_payment_shipping` VALUES ('1', '2');
INSERT INTO `shop_payment_shipping` VALUES ('2', '1');
INSERT INTO `shop_payment_shipping` VALUES ('2', '2');
INSERT INTO `shop_payment_shipping` VALUES ('2', '6');
INSERT INTO `shop_payment_shipping` VALUES ('3', '2');

-- ----------------------------
-- Table structure for `shop_poster`
-- ----------------------------
DROP TABLE IF EXISTS `shop_poster`;
CREATE TABLE `shop_poster` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `picUrl` varchar(255) DEFAULT NULL COMMENT '图片路径',
  `time` datetime DEFAULT NULL COMMENT '时间',
  `interUrl` varchar(100) DEFAULT NULL COMMENT '链接地址',
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_poster
-- ----------------------------
INSERT INTO `shop_poster` VALUES ('2', '/upload/201405/26084133ztri.jpg', '2014-05-26 08:44:12', '');
INSERT INTO `shop_poster` VALUES ('3', '/upload/201405/26084242g27o.jpg', '2014-05-26 08:44:12', '');
INSERT INTO `shop_poster` VALUES ('4', '/upload/201405/260843018b66.jpg', '2014-05-26 08:44:12', '');
INSERT INTO `shop_poster` VALUES ('5', '/upload/201405/2608432911kh.jpg', '2014-05-26 08:44:12', '');
INSERT INTO `shop_poster` VALUES ('6', '/upload/201407/21110157fyil.jpg', '2014-07-21 11:02:00', '');

-- ----------------------------
-- Table structure for `shop_product`
-- ----------------------------
DROP TABLE IF EXISTS `shop_product`;
CREATE TABLE `shop_product` (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ptype_id` bigint(20) NOT NULL COMMENT '类型ID',
  `brand_id` bigint(20) DEFAULT NULL COMMENT '品牌ID',
  `category_id` bigint(20) NOT NULL COMMENT '类别ID',
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `config_id` bigint(20) NOT NULL COMMENT '配置ID',
  `name` varchar(150) NOT NULL COMMENT '商品名称',
  `market_price` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '市场价',
  `sale_price` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '销售价',
  `cost_price` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '成本价',
  `view_count` bigint(20) NOT NULL DEFAULT '0' COMMENT '浏览次数',
  `sale_count` int(11) NOT NULL DEFAULT '0' COMMENT '销售量',
  `stock_count` int(11) NOT NULL DEFAULT '10' COMMENT '库存',
  `alert_inventory` int(11) DEFAULT NULL COMMENT '警戒库存',
  `create_time` datetime NOT NULL COMMENT '添加时间',
  `expire_time` datetime DEFAULT NULL COMMENT '到期时间',
  `on_sale` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否上架',
  `is_recommend` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否推荐',
  `is_special` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否特价',
  `is_hotsale` tinyint(3) NOT NULL COMMENT '是否热卖',
  `is_newProduct` tinyint(3) NOT NULL COMMENT '是否新品',
  `score` int(11) NOT NULL DEFAULT '0' COMMENT '商品送积分',
  `shareContent` varchar(255) NOT NULL COMMENT '分享内容',
  `lack_remind` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否库存预警',
  `li_run` double(11,2) NOT NULL DEFAULT '0.00' COMMENT '利润',
  PRIMARY KEY (`product_id`),
  KEY `fk_shop_product_brand` (`brand_id`),
  KEY `fk_shop_product_category` (`category_id`),
  KEY `fk_shop_product_config` (`config_id`),
  KEY `fk_shop_product_ptype` (`ptype_id`),
  KEY `fk_shop_product_website` (`website_id`),
  CONSTRAINT `fk_shop_product_brand` FOREIGN KEY (`brand_id`) REFERENCES `shop_brand` (`brand_id`),
  CONSTRAINT `fk_shop_product_category` FOREIGN KEY (`category_id`) REFERENCES `shop_category` (`category_id`),
  CONSTRAINT `fk_shop_product_config` FOREIGN KEY (`config_id`) REFERENCES `shop_config` (`config_id`),
  CONSTRAINT `fk_shop_product_ptype` FOREIGN KEY (`ptype_id`) REFERENCES `shop_ptype` (`ptype_id`),
  CONSTRAINT `fk_shop_product_website` FOREIGN KEY (`website_id`) REFERENCES `core_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='商品';

-- ----------------------------
-- Records of shop_product
-- ----------------------------
INSERT INTO `shop_product` VALUES ('6', '10', '5', '22', '1', '1', 'PAC35M1101W Air touch空气净化器', '3999.00', '2999.00', '2800.00', '0', '0', '100', null, '2015-01-25 16:40:54', null, '1', '0', '0', '0', '0', '0', '', '1', '0.00');
INSERT INTO `shop_product` VALUES ('7', '10', '5', '22', '1', '1', '海信（Hisense） LED40EC510N 40英寸 智能电视 丰富免费视频 WIFI（黑色）', '3600.00', '3300.00', '3000.00', '0', '0', '17', null, '2015-01-25 19:47:37', null, '1', '0', '0', '0', '0', '0', '', '1', '0.00');
INSERT INTO `shop_product` VALUES ('8', '10', '4', '22', '1', '1', '冰熊（BingXiong）BCD-245DCA 245升上对开门大容量多门家用冰箱', '1500.00', '1230.00', '1000.00', '0', '0', '300', null, '2015-01-25 21:25:15', null, '1', '0', '0', '0', '0', '0', '', '1', '0.00');
INSERT INTO `shop_product` VALUES ('9', '10', '4', '22', '1', '1', '索尼（SONY） KDL-40RM10B 40英寸全', '2800.00', '2599.00', '2300.00', '0', '0', '300', null, '2015-01-25 21:29:40', null, '1', '0', '0', '0', '0', '0', '', '1', '0.00');
INSERT INTO `shop_product` VALUES ('10', '11', '8', '23', '1', '1', '迷漾风情 2014冬季新款韩版女装外套长袖羽绒服女保暖撞色毛领上衣YTH888 米白蓝真毛领 XL', '599.00', '399.00', '299.00', '0', '0', '12', null, '2015-01-25 21:35:10', null, '1', '0', '0', '0', '0', '0', '', '1', '0.00');
INSERT INTO `shop_product` VALUES ('11', '11', '17', '23', '1', '1', '尚爱尚恋 2014冬装新款收腰修身显瘦带真毛领加厚中长款羽绒服女 YRF046 淡粉色 L', '899.00', '499.00', '300.00', '0', '0', '15', null, '2015-01-25 21:39:17', null, '1', '0', '0', '0', '0', '0', '', '1', '0.00');
INSERT INTO `shop_product` VALUES ('12', '11', '17', '23', '1', '1', '2014新款冬装羽绒服女款中长款韩版修身加厚大码连领外套女 黄色 L', '1299.00', '599.00', '300.00', '0', '0', '145', null, '2015-01-25 21:41:52', null, '1', '0', '0', '0', '0', '0', '', '1', '0.00');
INSERT INTO `shop_product` VALUES ('13', '11', '8', '23', '1', '1', '至多衫2014冬装新款女装外套韩版时尚百搭真貉子毛毛领修身中长款羽绒服女 玫红(真毛领)', '698.00', '398.00', '200.00', '0', '0', '124', null, '2015-01-25 21:45:23', null, '1', '0', '0', '0', '0', '0', '', '1', '0.00');

-- ----------------------------
-- Table structure for `shop_product_attr`
-- ----------------------------
DROP TABLE IF EXISTS `shop_product_attr`;
CREATE TABLE `shop_product_attr` (
  `product_id` bigint(20) NOT NULL DEFAULT '0',
  `attr_name` varchar(30) NOT NULL DEFAULT '' COMMENT '名称',
  `attr_value` varchar(255) DEFAULT NULL COMMENT '值',
  KEY `fK_shop_product_attr` (`product_id`),
  CONSTRAINT `fk_shop_product_attr` FOREIGN KEY (`product_id`) REFERENCES `shop_product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='jspgou商品扩展属性表';

-- ----------------------------
-- Records of shop_product_attr
-- ----------------------------

-- ----------------------------
-- Table structure for `shop_product_exended`
-- ----------------------------
DROP TABLE IF EXISTS `shop_product_exended`;
CREATE TABLE `shop_product_exended` (
  `product_id` bigint(20) NOT NULL DEFAULT '0',
  `priority` int(11) NOT NULL DEFAULT '10' COMMENT '排序',
  `attr_name` varchar(30) NOT NULL DEFAULT '' COMMENT '名称',
  `attr_value` varchar(255) DEFAULT NULL COMMENT '值',
  KEY `fk_shop_product_exended` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品和规则属性关联表';

-- ----------------------------
-- Records of shop_product_exended
-- ----------------------------
INSERT INTO `shop_product_exended` VALUES ('5', '0', 'price', '19');
INSERT INTO `shop_product_exended` VALUES ('5', '1', 'measure', '22');
INSERT INTO `shop_product_exended` VALUES ('1', '0', 'price', '16');
INSERT INTO `shop_product_exended` VALUES ('1', '1', 'measure', '25');
INSERT INTO `shop_product_exended` VALUES ('2', '0', 'price', '17');
INSERT INTO `shop_product_exended` VALUES ('2', '1', 'measure', '26');
INSERT INTO `shop_product_exended` VALUES ('3', '0', 'price', '16');
INSERT INTO `shop_product_exended` VALUES ('3', '1', 'measure', '26');
INSERT INTO `shop_product_exended` VALUES ('4', '0', 'price', '20');
INSERT INTO `shop_product_exended` VALUES ('4', '1', 'measure', '29');

-- ----------------------------
-- Table structure for `shop_product_ext`
-- ----------------------------
DROP TABLE IF EXISTS `shop_product_ext`;
CREATE TABLE `shop_product_ext` (
  `product_id` bigint(20) NOT NULL,
  `code` bigint(20) NOT NULL DEFAULT '0' COMMENT '商品编号',
  `description` varchar(255) DEFAULT NULL COMMENT '商品简介',
  `mtitle` varchar(255) DEFAULT NULL COMMENT '页面TITLE',
  `mkeywords` varchar(255) DEFAULT NULL COMMENT '页面KEYWORDS',
  `mdescription` varchar(255) DEFAULT NULL COMMENT '页面DESCRIPTION',
  `detail_img` varchar(150) DEFAULT '' COMMENT '详细图',
  `list_img` varchar(150) DEFAULT NULL COMMENT '列表图',
  `min_img` varchar(150) DEFAULT NULL COMMENT '最小图',
  `cover_img` varchar(255) DEFAULT NULL COMMENT '封面图片',
  `weight` int(11) NOT NULL DEFAULT '0' COMMENT '重量(g)',
  `unit` varchar(20) NOT NULL DEFAULT '' COMMENT '单位',
  `product_imgs` longtext COMMENT '商品全方位图片',
  `product_img_desc` longtext COMMENT '全方位图片描述',
  `is_limitTime` tinyint(3) DEFAULT NULL COMMENT '是否限时购买',
  `limitTime` datetime DEFAULT NULL COMMENT '限时时间',
  `seckillprice` double(11,2) DEFAULT NULL COMMENT '秒杀价',
  PRIMARY KEY (`product_id`),
  CONSTRAINT `fk_shop_ext_product` FOREIGN KEY (`product_id`) REFERENCES `shop_product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_product_ext
-- ----------------------------
INSERT INTO `shop_product_ext` VALUES ('6', '20150125164060', null, '', '', '', null, null, null, '/upload/201501/252121073w5s.jpg', '20', '', null, null, null, null, null);
INSERT INTO `shop_product_ext` VALUES ('7', '20150125194744', null, '', '', '', null, null, null, '/upload/201501/25211752u9s4.jpg', '15', '', null, null, null, null, null);
INSERT INTO `shop_product_ext` VALUES ('8', '20150125212522', null, '', '', '', null, null, null, '/upload/201501/25212338rsgu.jpg', '13', '', null, null, null, null, null);
INSERT INTO `shop_product_ext` VALUES ('9', '20150125212949', null, '', '', '', null, null, null, '/upload/201501/25212700sism.jpg', '12', '', null, null, null, null, null);
INSERT INTO `shop_product_ext` VALUES ('10', '20150125213519', null, '', '', '', null, null, null, '/upload/201501/252133106c1u.jpg', '200', '', null, null, null, null, null);
INSERT INTO `shop_product_ext` VALUES ('11', '20150125213927', null, '', '', '', null, null, null, '/upload/201501/25213658b3ra.jpg', '800', '', null, null, null, null, null);
INSERT INTO `shop_product_ext` VALUES ('12', '20150125214164', null, '', '', '', null, null, null, '/upload/201501/25214111m8qb.jpg', '500', '', null, null, null, null, null);
INSERT INTO `shop_product_ext` VALUES ('13', '20150125214536', null, '', '', '', null, null, null, '/upload/201501/25214422d09y.jpg', '0', '', null, null, null, null, null);

-- ----------------------------
-- Table structure for `shop_product_fashion`
-- ----------------------------
DROP TABLE IF EXISTS `shop_product_fashion`;
CREATE TABLE `shop_product_fashion` (
  `fashion_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) DEFAULT NULL,
  `stock_count` int(11) NOT NULL DEFAULT '0' COMMENT '库存',
  `on_sale` int(1) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `market_price` double(10,2) DEFAULT NULL COMMENT '市场价',
  `sale_price` double(10,2) DEFAULT NULL COMMENT '销售价',
  `cost_price` double(10,2) DEFAULT NULL COMMENT '成本价',
  `fashion_style` longtext,
  `sale_count` int(11) DEFAULT NULL COMMENT '销量',
  `product_code` varchar(255) DEFAULT NULL COMMENT '商品编号',
  `lackRemind` int(1) DEFAULT '1' COMMENT '是否提醒库存预警（作废）',
  `fashion_pic` varchar(255) DEFAULT NULL,
  `fashion_color` varchar(50) DEFAULT NULL COMMENT '商品款式颜色',
  `fashion_size` varchar(50) DEFAULT NULL COMMENT '商品款式尺码',
  `is_default` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否问默认显示款式',
  `nature` varchar(255) DEFAULT NULL,
  `attitude` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`fashion_id`),
  KEY `fk_fashion_product` (`product_id`),
  CONSTRAINT `fk_fashion_product` FOREIGN KEY (`product_id`) REFERENCES `shop_product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='商品款式';

-- ----------------------------
-- Records of shop_product_fashion
-- ----------------------------

-- ----------------------------
-- Table structure for `shop_product_keyword`
-- ----------------------------
DROP TABLE IF EXISTS `shop_product_keyword`;
CREATE TABLE `shop_product_keyword` (
  `product_id` bigint(20) NOT NULL,
  `name` varchar(100) NOT NULL,
  `priority` int(11) NOT NULL,
  KEY `fk_shop_keyword_product` (`product_id`),
  CONSTRAINT `fk_shop_keyword_product` FOREIGN KEY (`product_id`) REFERENCES `shop_product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品关键字';

-- ----------------------------
-- Records of shop_product_keyword
-- ----------------------------

-- ----------------------------
-- Table structure for `shop_product_picture`
-- ----------------------------
DROP TABLE IF EXISTS `shop_product_picture`;
CREATE TABLE `shop_product_picture` (
  `product_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '商品Id',
  `priority` int(11) NOT NULL DEFAULT '0' COMMENT '排列顺序',
  `picture_path` varchar(255) DEFAULT NULL COMMENT '商品款式图',
  `bigpicture_path` varchar(255) DEFAULT NULL COMMENT '大图',
  `apppicture_path` varchar(255) DEFAULT NULL COMMENT '放大图',
  PRIMARY KEY (`product_id`,`priority`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品图片';

-- ----------------------------
-- Records of shop_product_picture
-- ----------------------------
INSERT INTO `shop_product_picture` VALUES ('6', '0', '/upload/201501/25163653i18c.jpg', '/upload/201501/25212052xqa0.jpg', '/upload/201501/25212102zfn4.jpg');
INSERT INTO `shop_product_picture` VALUES ('7', '0', '/upload/201501/25194630y0tk.jpg', '/upload/201501/25211811tu9q.jpg', '/upload/201501/25211802sa7h.jpg');
INSERT INTO `shop_product_picture` VALUES ('8', '0', '/upload/201501/25212345hg0j.jpg', '/upload/201501/25212350f7mi.jpg', '/upload/201501/25212356s0xc.jpg');
INSERT INTO `shop_product_picture` VALUES ('9', '0', '/upload/201501/25212708izqw.jpg', '/upload/201501/2521271562hk.jpg', '/upload/201501/25212720k1sh.jpg');
INSERT INTO `shop_product_picture` VALUES ('9', '1', '/upload/201501/25212814cdhz.jpg', '/upload/201501/25212820qnvu.jpg', '/upload/201501/25212827h7et.jpg');
INSERT INTO `shop_product_picture` VALUES ('10', '0', '/upload/201501/25213317hv49.jpg', '/upload/201501/2521332305kz.jpg', '/upload/201501/2521332822ls.jpg');
INSERT INTO `shop_product_picture` VALUES ('10', '1', '/upload/201501/252133352zsi.jpg', '/upload/201501/25213341dwij.jpg', '/upload/201501/25213347zknl.jpg');
INSERT INTO `shop_product_picture` VALUES ('11', '0', '/upload/201501/25213706axbp.jpg', '/upload/201501/252137110z8l.jpg', '/upload/201501/252137168jba.jpg');
INSERT INTO `shop_product_picture` VALUES ('11', '1', '/upload/201501/252137232j1n.jpg', '/upload/201501/252137275lu7.jpg', '/upload/201501/252137324uwi.jpg');
INSERT INTO `shop_product_picture` VALUES ('12', '0', '/upload/201501/25214120a787.jpg', '/upload/201501/25214129tcd8.jpg', '/upload/201501/2521413782nx.jpg');
INSERT INTO `shop_product_picture` VALUES ('13', '0', '/upload/201501/25214428uq1k.jpg', '/upload/201501/25214433hiof.jpg', '/upload/201501/25214441ry8h.jpg');

-- ----------------------------
-- Table structure for `shop_product_standard`
-- ----------------------------
DROP TABLE IF EXISTS `shop_product_standard`;
CREATE TABLE `shop_product_standard` (
  `ps_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `product_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '与商品外键',
  `standard_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '与颜色规则外键',
  `dataType` tinyint(1) DEFAULT NULL COMMENT '数据类型',
  `type_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '规格类型Id',
  `img_path` varchar(255) DEFAULT NULL COMMENT '颜色图片',
  PRIMARY KEY (`ps_id`),
  KEY `fk_shop_product_color` (`product_id`),
  CONSTRAINT `fk_shop_product_standard` FOREIGN KEY (`product_id`) REFERENCES `shop_product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COMMENT='商品规则存储表';

-- ----------------------------
-- Records of shop_product_standard
-- ----------------------------

-- ----------------------------
-- Table structure for `shop_product_tag`
-- ----------------------------
DROP TABLE IF EXISTS `shop_product_tag`;
CREATE TABLE `shop_product_tag` (
  `stag_id` bigint(20) NOT NULL,
  `product_id` bigint(20) NOT NULL,
  PRIMARY KEY (`stag_id`,`product_id`),
  KEY `fk_shop_tag_product` (`product_id`),
  CONSTRAINT `fk_shop_product_tag` FOREIGN KEY (`stag_id`) REFERENCES `shop_tag` (`stag_id`),
  CONSTRAINT `fk_shop_tag_product` FOREIGN KEY (`product_id`) REFERENCES `shop_product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_product_tag
-- ----------------------------

-- ----------------------------
-- Table structure for `shop_product_text`
-- ----------------------------
DROP TABLE IF EXISTS `shop_product_text`;
CREATE TABLE `shop_product_text` (
  `product_id` bigint(20) NOT NULL,
  `text` longtext,
  `text1` longtext,
  `text2` longtext,
  PRIMARY KEY (`product_id`),
  CONSTRAINT `fk_shop_text_product` FOREIGN KEY (`product_id`) REFERENCES `shop_product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品详情';

-- ----------------------------
-- Records of shop_product_text
-- ----------------------------
INSERT INTO `shop_product_text` VALUES ('6', '<p><img alt=\"\" src=\"/upload/shop//201501/25194248azd7.jpg\" style=\"width: 750px; height: 411px;\" /></p>', '', null);
INSERT INTO `shop_product_text` VALUES ('7', '<p><img alt=\"\" src=\"/upload/shop//201501/25194659oddi.gif\" style=\"width: 750px; height: 400px;\" /></p>\r\n<p><img alt=\"\" src=\"/upload/shop//201501/25194714ocbh.gif\" style=\"width: 750px; height: 350px;\" /></p>\r\n<p><img alt=\"\" src=\"/upload/shop//201501/25194732ro44.jpg\" style=\"width: 750px; height: 280px;\" /></p>', '', null);
INSERT INTO `shop_product_text` VALUES ('8', '<p><img alt=\"\" src=\"/upload/shop//201501/25212440qik7.jpg\" style=\"width: 750px; height: 1428px;\" /></p>\r\n<p><img alt=\"\" src=\"/upload/shop//201501/25212502de75.jpg\" style=\"width: 750px; height: 1428px;\" /></p>', '', null);
INSERT INTO `shop_product_text` VALUES ('9', '<p><img alt=\"\" src=\"/upload/shop//201501/2521292108qe.jpg\" style=\"width: 750px; height: 300px;\" /></p>\r\n<p><img alt=\"\" src=\"/upload/shop//201501/2521293577ay.jpg\" style=\"width: 750px; height: 1791px;\" /></p>', '', null);
INSERT INTO `shop_product_text` VALUES ('10', '<p><img alt=\"\" src=\"/upload/shop//201501/2521344606j9.jpg\" style=\"width: 750px; height: 1324px;\" /></p>\r\n<p><img alt=\"\" src=\"/upload/shop//201501/25213504jwbc.jpg\" style=\"width: 750px; height: 807px;\" /></p>', '', null);
INSERT INTO `shop_product_text` VALUES ('11', '<p><img alt=\"\" src=\"/upload/shop//201501/252138584anp.jpg\" style=\"width: 750px; height: 567px;\" /></p>\r\n<p><img alt=\"\" src=\"/upload/shop//201501/25213911wtf7.jpg\" style=\"width: 750px; height: 567px;\" /></p>', '', null);
INSERT INTO `shop_product_text` VALUES ('12', '<p><img alt=\"\" src=\"/upload/shop//201501/2521414837lm.jpg\" style=\"width: 750px; height: 864px;\" /></p>', '', null);
INSERT INTO `shop_product_text` VALUES ('13', '<p><img alt=\"\" src=\"/upload/shop//201501/25214453wkve.jpg\" style=\"width: 750px; height: 856px;\" /></p>\r\n<p>&nbsp;</p>', '', null);

-- ----------------------------
-- Table structure for `shop_ptype`
-- ----------------------------
DROP TABLE IF EXISTS `shop_ptype`;
CREATE TABLE `shop_ptype` (
  `ptype_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `name` varchar(150) NOT NULL COMMENT '类型名称',
  `path` varchar(150) DEFAULT NULL COMMENT '访问路径',
  `alias` varchar(255) DEFAULT NULL COMMENT '类型别名',
  `channel_prefix` varchar(20) NOT NULL DEFAULT 'channel' COMMENT '栏目页模板前缀',
  `content_prefix` varchar(20) NOT NULL DEFAULT 'content' COMMENT '内容页模板前缀',
  `props` longtext COMMENT '属性',
  `detail_img_width` int(11) NOT NULL DEFAULT '310' COMMENT '详细图宽',
  `detail_img_height` int(11) NOT NULL DEFAULT '310' COMMENT '详细图高',
  `list_img_width` int(11) NOT NULL DEFAULT '140' COMMENT '列表图宽',
  `list_img_height` int(11) NOT NULL DEFAULT '140' COMMENT '列表图高',
  `min_img_width` int(11) NOT NULL DEFAULT '100' COMMENT '缩略图宽',
  `min_img_height` int(11) NOT NULL DEFAULT '100' COMMENT '缩略图高',
  PRIMARY KEY (`ptype_id`),
  KEY `fk_shop_ptype_website` (`website_id`),
  CONSTRAINT `fk_shop_ptype_website` FOREIGN KEY (`website_id`) REFERENCES `core_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='商品类型';

-- ----------------------------
-- Records of shop_ptype
-- ----------------------------
INSERT INTO `shop_ptype` VALUES ('10', '1', '电器', null, null, 'classify', 'item', null, '310', '310', '140', '140', '100', '100');
INSERT INTO `shop_ptype` VALUES ('11', '1', '服装', null, null, 'classify', 'item', null, '310', '310', '140', '140', '100', '100');
INSERT INTO `shop_ptype` VALUES ('12', '1', '运动', null, null, 'classify', 'item', null, '310', '310', '140', '140', '100', '100');
INSERT INTO `shop_ptype` VALUES ('13', '1', '母婴', null, null, 'classify', 'item', null, '310', '310', '140', '140', '100', '100');
INSERT INTO `shop_ptype` VALUES ('14', '1', '食品', null, null, 'classify', 'item', null, '310', '310', '140', '140', '100', '100');

-- ----------------------------
-- Table structure for `shop_ptype_exended`
-- ----------------------------
DROP TABLE IF EXISTS `shop_ptype_exended`;
CREATE TABLE `shop_ptype_exended` (
  `ptype_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '商品类型Id',
  `exended_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '扩展属性Id',
  PRIMARY KEY (`exended_id`,`ptype_id`),
  KEY `fk_shop_ptype_exended` (`ptype_id`),
  KEY `fk_shop_exended_ptype` (`exended_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品类型和扩展属性关联表';

-- ----------------------------
-- Records of shop_ptype_exended
-- ----------------------------

-- ----------------------------
-- Table structure for `shop_ptype_property`
-- ----------------------------
DROP TABLE IF EXISTS `shop_ptype_property`;
CREATE TABLE `shop_ptype_property` (
  `type_property_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ptype_id` bigint(20) DEFAULT NULL,
  `field` varchar(255) NOT NULL DEFAULT '' COMMENT '字段',
  `property_name` varchar(255) DEFAULT NULL COMMENT '属性名',
  `sort` int(11) DEFAULT NULL COMMENT '排序顺序',
  `def_value` varchar(255) DEFAULT NULL COMMENT '默认值',
  `opt_value` varchar(255) DEFAULT NULL COMMENT '可选项',
  `area_rows` varchar(255) DEFAULT NULL COMMENT '文本行数',
  `area_cols` varchar(255) DEFAULT NULL COMMENT '文本列数',
  `is_start` int(1) DEFAULT NULL COMMENT '是否启用',
  `is_notNull` int(1) DEFAULT NULL COMMENT '是否必填',
  `data_type` varchar(255) NOT NULL DEFAULT '1' COMMENT '数据类型',
  `is_single` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否独占一行',
  `is_category` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否类别模型项',
  `is_custom` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否自定义',
  PRIMARY KEY (`type_property_id`),
  KEY `fk_type_property` (`ptype_id`),
  CONSTRAINT `fk_type_property` FOREIGN KEY (`ptype_id`) REFERENCES `shop_ptype` (`ptype_id`)
) ENGINE=InnoDB AUTO_INCREMENT=489 DEFAULT CHARSET=utf8 COMMENT='商品类型属性表';

-- ----------------------------
-- Records of shop_ptype_property
-- ----------------------------
INSERT INTO `shop_ptype_property` VALUES ('391', '10', 'name', '分类名称', '10', null, null, null, null, null, null, '1', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('392', '10', 'path', '访问路径', '10', null, null, null, null, null, null, '1', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('393', '10', 'title', '页面标题', '10', null, null, null, null, null, null, '1', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('394', '10', 'keywords', '页面关键词', '10', null, null, null, null, null, null, '1', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('395', '10', 'description', '页面描述', '10', null, null, null, null, null, null, '4', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('396', '10', 'tplChannel', '分类模板', '10', null, null, null, null, null, null, '6', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('397', '10', 'tplContent', '商品模板', '10', null, null, null, null, null, null, '6', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('398', '10', 'priority', '排序', '10', null, null, null, null, null, null, '2', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('399', '10', 'image', '图片', '10', null, null, null, null, null, null, '1', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('400', '10', 'refBrand', '关联品牌', '10', null, null, null, null, null, null, '7', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('401', '10', 'standardTypeIds', '扩展属性', '10', null, null, null, null, null, null, '7', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('402', '10', 'colorsize', '规格', '10', null, null, null, null, null, null, '7', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('403', '11', 'name', '分类名称', '10', null, null, null, null, null, null, '1', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('404', '11', 'path', '访问路径', '10', null, null, null, null, null, null, '1', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('405', '11', 'title', '页面标题', '10', null, null, null, null, null, null, '1', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('406', '11', 'keywords', '页面关键词', '10', null, null, null, null, null, null, '1', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('407', '11', 'description', '页面描述', '10', null, null, null, null, null, null, '4', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('408', '11', 'tplChannel', '分类模板', '10', null, null, null, null, null, null, '6', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('409', '11', 'tplContent', '商品模板', '10', null, null, null, null, null, null, '6', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('410', '11', 'priority', '排序', '10', null, null, null, null, null, null, '2', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('411', '11', 'image', '图片', '10', null, null, null, null, null, null, '1', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('412', '11', 'refBrand', '关联品牌', '10', null, null, null, null, null, null, '7', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('413', '11', 'standardTypeIds', '扩展属性', '10', null, null, null, null, null, null, '7', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('414', '11', 'colorsize', '规格', '10', null, null, null, null, null, null, '7', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('415', '12', 'name', '分类名称', '10', null, null, null, null, null, null, '1', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('416', '12', 'path', '访问路径', '10', null, null, null, null, null, null, '1', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('417', '12', 'title', '页面标题', '10', null, null, null, null, null, null, '1', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('418', '12', 'keywords', '页面关键词', '10', null, null, null, null, null, null, '1', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('419', '12', 'description', '页面描述', '10', null, null, null, null, null, null, '4', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('420', '12', 'tplChannel', '分类模板', '10', null, null, null, null, null, null, '6', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('421', '12', 'tplContent', '商品模板', '10', null, null, null, null, null, null, '6', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('422', '12', 'priority', '排序', '10', null, null, null, null, null, null, '2', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('423', '12', 'image', '图片', '10', null, null, null, null, null, null, '1', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('424', '12', 'refBrand', '关联品牌', '10', null, null, null, null, null, null, '7', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('425', '12', 'standardTypeIds', '扩展属性', '10', null, null, null, null, null, null, '7', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('426', '12', 'colorsize', '规格', '10', null, null, null, null, null, null, '7', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('427', '13', 'name', '分类名称', '10', null, null, null, null, null, null, '1', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('428', '13', 'path', '访问路径', '10', null, null, null, null, null, null, '1', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('429', '13', 'title', '页面标题', '10', null, null, null, null, null, null, '1', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('430', '13', 'keywords', '页面关键词', '10', null, null, null, null, null, null, '1', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('431', '13', 'description', '页面描述', '10', null, null, null, null, null, null, '4', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('432', '13', 'tplChannel', '分类模板', '10', null, null, null, null, null, null, '6', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('433', '13', 'tplContent', '商品模板', '10', null, null, null, null, null, null, '6', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('434', '13', 'priority', '排序', '10', null, null, null, null, null, null, '2', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('435', '13', 'image', '图片', '10', null, null, null, null, null, null, '1', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('436', '13', 'refBrand', '关联品牌', '10', null, null, null, null, null, null, '7', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('437', '13', 'standardTypeIds', '扩展属性', '10', null, null, null, null, null, null, '7', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('438', '13', 'colorsize', '规格', '10', null, null, null, null, null, null, '7', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('439', '14', 'name', '分类名称', '10', null, null, null, null, null, null, '1', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('440', '14', 'path', '访问路径', '10', null, null, null, null, null, null, '1', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('441', '14', 'title', '页面标题', '10', null, null, null, null, null, null, '1', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('442', '14', 'keywords', '页面关键词', '10', null, null, null, null, null, null, '1', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('443', '14', 'description', '页面描述', '10', null, null, null, null, null, null, '4', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('444', '14', 'tplChannel', '分类模板', '10', null, null, null, null, null, null, '6', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('445', '14', 'tplContent', '商品模板', '10', null, null, null, null, null, null, '6', '0', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('446', '14', 'priority', '排序', '10', null, null, null, null, null, null, '2', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('447', '14', 'image', '图片', '10', null, null, null, null, null, null, '1', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('448', '14', 'refBrand', '关联品牌', '10', null, null, null, null, null, null, '7', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('449', '14', 'standardTypeIds', '扩展属性', '10', null, null, null, null, null, null, '7', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('450', '14', 'colorsize', '规格', '10', null, null, null, null, null, null, '7', '1', '1', '0');
INSERT INTO `shop_ptype_property` VALUES ('451', '10', 'name', '商品名称', '10', null, null, null, null, null, null, '1', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('452', '10', 'categoryId', '分类', '10', null, null, null, null, null, null, '6', '0', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('453', '10', 'brandId', '品牌', '10', null, null, null, null, null, null, '6', '0', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('454', '10', 'price', '价格', '10', null, null, null, null, null, null, '2', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('455', '10', 'stockCount', '商品库存', '10', null, null, null, null, null, null, '2', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('456', '10', 'weight', '商品重量', '10', null, null, null, null, null, null, '2', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('457', '10', 'attr', '设置', '10', null, null, null, null, null, null, '7', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('458', '10', 'score', '可得积分', '10', null, null, null, null, null, null, '2', '0', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('459', '10', 'alertInventory', '警戒库存', '10', null, null, null, null, null, null, '2', '0', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('460', '10', 'tagIds', '标签', '10', null, null, null, null, null, null, '7', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('461', '10', 'coverImg', '封面图片', '10', null, null, null, null, null, null, '1', '0', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('462', '10', 'productstyle', '商品款式集', '10', null, null, null, null, null, null, '1', '0', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('463', '10', 'shareContent', '分享内容', '10', null, null, null, null, null, null, '4', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('464', '10', 'mtitle', '页面标题', '10', null, null, null, null, null, null, '1', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('465', '10', 'productKeywords', '关键字', '10', null, null, null, null, null, null, '1', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('466', '10', 'mkeywords', '页面关键字', '10', null, null, null, null, null, null, '1', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('467', '10', 'mdescription', '页面描述', '10', null, null, null, null, null, null, '1', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('468', '10', 'text', '商品详情', '10', null, null, null, null, null, null, '4', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('469', '10', 'text1', '售后服务', '10', null, null, null, null, null, null, '4', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('470', '11', 'name', '商品名称', '10', null, null, null, null, null, null, '1', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('471', '11', 'categoryId', '分类', '10', null, null, null, null, null, null, '6', '0', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('472', '11', 'brandId', '品牌', '10', null, null, null, null, null, null, '6', '0', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('473', '11', 'price', '价格', '10', null, null, null, null, null, null, '2', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('474', '11', 'stockCount', '商品库存', '10', null, null, null, null, null, null, '2', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('475', '11', 'weight', '商品重量', '10', null, null, null, null, null, null, '2', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('476', '11', 'attr', '设置', '10', null, null, null, null, null, null, '7', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('477', '11', 'score', '可得积分', '10', null, null, null, null, null, null, '2', '0', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('478', '11', 'alertInventory', '警戒库存', '10', null, null, null, null, null, null, '2', '0', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('479', '11', 'tagIds', '标签', '10', null, null, null, null, null, null, '7', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('480', '11', 'coverImg', '封面图片', '10', null, null, null, null, null, null, '1', '0', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('481', '11', 'productstyle', '商品款式集', '10', null, null, null, null, null, null, '1', '0', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('482', '11', 'shareContent', '分享内容', '10', null, null, null, null, null, null, '4', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('483', '11', 'mtitle', '页面标题', '10', null, null, null, null, null, null, '1', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('484', '11', 'productKeywords', '关键字', '10', null, null, null, null, null, null, '1', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('485', '11', 'mkeywords', '页面关键字', '10', null, null, null, null, null, null, '1', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('486', '11', 'mdescription', '页面描述', '10', null, null, null, null, null, null, '1', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('487', '11', 'text', '商品详情', '10', null, null, null, null, null, null, '4', '1', '0', '0');
INSERT INTO `shop_ptype_property` VALUES ('488', '11', 'text1', '售后服务', '10', null, null, null, null, null, null, '4', '1', '0', '0');

-- ----------------------------
-- Table structure for `shop_score`
-- ----------------------------
DROP TABLE IF EXISTS `shop_score`;
CREATE TABLE `shop_score` (
  `Id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '会员关联',
  `name` varchar(255) DEFAULT NULL COMMENT '积分来源/用途',
  `score` int(11) DEFAULT NULL COMMENT '积分',
  `scoreTime` datetime DEFAULT NULL COMMENT '积分生成时间',
  `scoreType` int(11) NOT NULL DEFAULT '0' COMMENT '积分的类型',
  `useStatus` tinyint(1) NOT NULL DEFAULT '0' COMMENT '0为收入，1为支出',
  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '积分状况（0为冻结，1为可用）',
  `remark` varchar(255) DEFAULT '' COMMENT '备注',
  `code` varchar(255) DEFAULT NULL COMMENT '订单号',
  PRIMARY KEY (`Id`),
  KEY `fk_shop_score_member` (`member_id`),
  CONSTRAINT `fk_shop_score_member` FOREIGN KEY (`member_id`) REFERENCES `shop_member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8 COMMENT='积分明细';

-- ----------------------------
-- Records of shop_score
-- ----------------------------
INSERT INTO `shop_score` VALUES ('4', '1', '苹果4S', '0', '2014-06-19 11:09:53', '11', '0', '0', null, '1403147393251');
INSERT INTO `shop_score` VALUES ('5', '1', '佳能EOS450D数码相机', '0', '2014-06-19 11:24:05', '11', '0', '1', null, '1403148245954');
INSERT INTO `shop_score` VALUES ('6', '4', '邮箱验证送积分', null, '2014-07-09 15:38:36', '10', '0', '1', null, null);
INSERT INTO `shop_score` VALUES ('7', '1', '佳能EOS450D数码相机', '0', '2014-07-10 11:58:39', '11', '0', '0', null, '1404964719610');
INSERT INTO `shop_score` VALUES ('8', '1', '佳能EOS450D数码相机', '0', '2014-07-16 14:35:04', '11', '0', '0', null, '1405492504501');
INSERT INTO `shop_score` VALUES ('9', '1', '华硕R510VC', '0', '2014-07-16 15:09:40', '11', '0', '1', null, '1405494580344');
INSERT INTO `shop_score` VALUES ('10', '1', '苹果4S', '0', '2014-07-16 15:42:47', '11', '0', '1', null, '1405496567563');
INSERT INTO `shop_score` VALUES ('11', '1', '苹果4S', '0', '2014-07-16 15:48:22', '11', '0', '1', null, '1405496902672');
INSERT INTO `shop_score` VALUES ('12', '1', '佳能EOS450D数码相机', '0', '2014-07-16 15:48:51', '11', '0', '1', null, '1405496931501');
INSERT INTO `shop_score` VALUES ('19', '1', '美女', '1', '2014-07-24 15:12:56', '11', '1', '1', null, null);
INSERT INTO `shop_score` VALUES ('20', '1', '美女', '1', '2014-07-24 15:36:15', '11', '1', '1', null, null);
INSERT INTO `shop_score` VALUES ('21', '1', '佳能EOS450D数码相机', '0', '2014-08-01 08:48:47', '11', '0', '0', null, '1406854127797');
INSERT INTO `shop_score` VALUES ('22', '5', '邮箱验证送积分', null, '2014-08-05 15:34:13', '10', '0', '1', null, null);
INSERT INTO `shop_score` VALUES ('23', '1', '华硕R510VC', '0', '2014-08-07 16:40:15', '11', '0', '0', null, '1407400814954');
INSERT INTO `shop_score` VALUES ('24', '1', '佳能EOS450D数码相机', '0', '2014-08-12 10:56:28', '11', '0', '0', null, '1407812188829');
INSERT INTO `shop_score` VALUES ('25', '1', '华硕R510VC', '0', '2014-08-12 14:38:20', '11', '0', '0', null, '1407825500641');
INSERT INTO `shop_score` VALUES ('26', '1', '华硕R510VC', '0', '2014-08-18 10:52:20', '11', '0', '0', null, '1408330340644');
INSERT INTO `shop_score` VALUES ('27', '1', '佳能EOS450D数码相机', '0', '2014-08-18 10:54:51', '11', '0', '0', null, '1408330491707');
INSERT INTO `shop_score` VALUES ('28', '1', '佳能EOS450D数码相机', '0', '2014-08-19 11:06:04', '11', '0', '0', null, '1408417564407');
INSERT INTO `shop_score` VALUES ('29', '1', '支付测试', '0', '2014-08-19 14:01:39', '11', '0', '0', null, '1408428099188');
INSERT INTO `shop_score` VALUES ('30', '1', '支付测试', '0', '2014-08-19 14:11:18', '11', '0', '0', null, '1408428678141');
INSERT INTO `shop_score` VALUES ('31', '1', '支付测试', '0', '2014-08-19 15:12:00', '11', '0', '0', null, '1408432320797');
INSERT INTO `shop_score` VALUES ('32', '1', '支付测试', '0', '2014-08-21 16:59:02', '11', '0', '0', null, '1408611542954');
INSERT INTO `shop_score` VALUES ('33', '1', 'test', '0', '2014-08-21 17:03:14', '11', '0', '0', null, '1408611794360');
INSERT INTO `shop_score` VALUES ('50', '1', '支付测试', '0', '2014-08-25 10:32:49', '11', '0', '1', null, '1408933969453');
INSERT INTO `shop_score` VALUES ('51', '1', '支付测试', '0', '2014-08-25 10:34:21', '11', '0', '1', null, '1408934061531');
INSERT INTO `shop_score` VALUES ('57', '9', '佳能EOS450D数码相机', '0', '2015-01-25 12:46:45', '11', '0', '0', null, '1422161205354');
INSERT INTO `shop_score` VALUES ('58', '9', '佳能EOS450D数码相机', '0', '2015-01-25 12:46:45', '11', '0', '0', null, '1422161205354');
INSERT INTO `shop_score` VALUES ('59', '9', '支付测试', '0', '2015-01-25 12:49:24', '11', '0', '0', null, '1422161364303');
INSERT INTO `shop_score` VALUES ('60', '9', '佳能EOS450D数码相机', '0', '2015-01-25 12:52:02', '11', '0', '0', null, '1422161522339');
INSERT INTO `shop_score` VALUES ('61', '9', '佳能EOS450D数码相机', '0', '2015-01-25 12:53:39', '11', '0', '0', null, '1422161619157');

-- ----------------------------
-- Table structure for `shop_shipping`
-- ----------------------------
DROP TABLE IF EXISTS `shop_shipping`;
CREATE TABLE `shop_shipping` (
  `shipping_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `website_id` bigint(20) NOT NULL,
  `name` varchar(150) NOT NULL COMMENT '名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `uniform_price` double(11,2) DEFAULT NULL COMMENT '统一价格',
  `first_weight` int(11) DEFAULT NULL COMMENT '首重量(g)',
  `first_price` double(11,2) DEFAULT NULL COMMENT '首重价',
  `additional_weight` int(11) DEFAULT NULL COMMENT '续重量(g)',
  `additional_price` double(11,2) DEFAULT NULL COMMENT '续重价',
  `method` int(11) NOT NULL DEFAULT '1' COMMENT '1:固定价格;2按重量计价;:3:按国家计价;',
  `priority` int(11) NOT NULL DEFAULT '10' COMMENT '排列顺序',
  `is_disabled` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否禁用',
  `is_default` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否默认',
  `logistics_id` bigint(20) DEFAULT NULL,
  `logistics_type` varchar(255) DEFAULT NULL COMMENT '物流类型',
  PRIMARY KEY (`shipping_id`),
  KEY `fk_shop_shipping_website` (`website_id`),
  CONSTRAINT `fk_shop_shipping_website` FOREIGN KEY (`website_id`) REFERENCES `core_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='发货方式';

-- ----------------------------
-- Records of shop_shipping
-- ----------------------------
INSERT INTO `shop_shipping` VALUES ('1', '1', '普通快递', '系统将根据您的收货地址自动匹配快递公司进行配送，享受免运费服务', '0.00', '1', '1.00', '1', '1.00', '1', '1', '0', '1', '4', 'EXPRESS');
INSERT INTO `shop_shipping` VALUES ('2', '1', '顺丰快递', '需支付10元配送费用，不享受免运费服务', '10.00', '500', '20.00', '500', '10.00', '1', '2', '0', '0', '5', 'EXPRESS');

-- ----------------------------
-- Table structure for `shop_tag`
-- ----------------------------
DROP TABLE IF EXISTS `shop_tag`;
CREATE TABLE `shop_tag` (
  `stag_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `website_id` bigint(20) NOT NULL COMMENT '站点ID',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `count` int(11) NOT NULL DEFAULT '0' COMMENT '关联商品个数',
  PRIMARY KEY (`stag_id`),
  KEY `fk_shop_tag_website` (`website_id`),
  CONSTRAINT `fk_shop_tag_website` FOREIGN KEY (`website_id`) REFERENCES `core_website` (`website_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='标签';

-- ----------------------------
-- Records of shop_tag
-- ----------------------------
INSERT INTO `shop_tag` VALUES ('1', '1', '惠民', '28');

-- ----------------------------
-- Table structure for `standard`
-- ----------------------------
DROP TABLE IF EXISTS `standard`;
CREATE TABLE `standard` (
  `standard_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type_id` int(11) NOT NULL DEFAULT '0' COMMENT '规格类型Id',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '规格名称',
  `color` varchar(10) DEFAULT NULL COMMENT '颜色',
  `priority` int(11) NOT NULL DEFAULT '10' COMMENT '排序',
  PRIMARY KEY (`standard_id`),
  KEY `fc_standard_type` (`type_id`),
  CONSTRAINT `fc_standard_type` FOREIGN KEY (`type_id`) REFERENCES `standard_type` (`standardtype_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='商品规格表';

-- ----------------------------
-- Records of standard
-- ----------------------------
INSERT INTO `standard` VALUES ('22', '3', '黑', '#000000', '1');
INSERT INTO `standard` VALUES ('23', '3', '白', '#FFFFFF', '2');
INSERT INTO `standard` VALUES ('24', '5', '联通3G', '', '1');
INSERT INTO `standard` VALUES ('25', '5', '电信3G', '', '2');
INSERT INTO `standard` VALUES ('26', '6', '非合约机', '', '1');
INSERT INTO `standard` VALUES ('27', '6', '购机入网送话费', '', '2');
INSERT INTO `standard` VALUES ('28', '6', '0元购机', '', '3');
INSERT INTO `standard` VALUES ('29', '7', 'i5-3230M 750G 蓝牙', '', '1');
INSERT INTO `standard` VALUES ('30', '7', 'i3-3110M GT710M', '', '2');
INSERT INTO `standard` VALUES ('31', '7', 'i5-4200U 高速硬盘 GT820M 蓝牙', '', '3');
INSERT INTO `standard` VALUES ('32', '7', 'i5-3230M 500G', '', '4');
INSERT INTO `standard` VALUES ('33', '1', '红色', '#FF0000', '1');
INSERT INTO `standard` VALUES ('34', '1', '绿色', '#00FF40', '2');
INSERT INTO `standard` VALUES ('35', '1', '蓝色', '#0000FF', '3');
INSERT INTO `standard` VALUES ('36', '2', 's', '', '1');
INSERT INTO `standard` VALUES ('37', '2', 'm', '', '2');
INSERT INTO `standard` VALUES ('38', '2', 'l', '', '3');

-- ----------------------------
-- Table structure for `standard_type`
-- ----------------------------
DROP TABLE IF EXISTS `standard_type`;
CREATE TABLE `standard_type` (
  `standardtype_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '规格类型名称',
  `field` varchar(255) NOT NULL DEFAULT '' COMMENT '字段名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `dataType` tinyint(1) DEFAULT NULL COMMENT '数据类型',
  `priority` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`standardtype_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='商品规格类型表';

-- ----------------------------
-- Records of standard_type
-- ----------------------------
INSERT INTO `standard_type` VALUES ('1', '颜色', 'yanse', '服装', '1', '10');
INSERT INTO `standard_type` VALUES ('2', '尺寸', 'chicu', '服装', '0', '10');
INSERT INTO `standard_type` VALUES ('3', '颜色', 'yanse1', '手机', '1', '10');
INSERT INTO `standard_type` VALUES ('5', '版本', 'banben', '手机', '0', '10');
INSERT INTO `standard_type` VALUES ('6', '购买方式', 'goumaifangshi', '手机', '0', '10');
INSERT INTO `standard_type` VALUES ('7', '版本', 'bonben', '电脑', '0', '10');
