/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : mouse

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 11/12/2019 13:07:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tbl_ad
-- ----------------------------
DROP TABLE IF EXISTS `tbl_ad`;
CREATE TABLE `tbl_ad` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL COMMENT '广告标题',
  `link` varchar(64) NOT NULL COMMENT '所广告的商品页面或者活动页面链接地址',
  `url` varchar(255) NOT NULL COMMENT '广告宣传图片',
  `position` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '广告位置：1则是首页',
  `content` varchar(255) NOT NULL COMMENT '活动内容',
  `start_time` datetime NOT NULL COMMENT '广告开始时间',
  `end_time` datetime NOT NULL COMMENT '广告结束时间',
  `enabled` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否启动',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_address
-- ----------------------------
DROP TABLE IF EXISTS `tbl_address`;
CREATE TABLE `tbl_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL COMMENT '收货人名称',
  `user_id` int(10) unsigned NOT NULL COMMENT '用户表的用户ID',
  `province` varchar(32) NOT NULL COMMENT '省',
  `city` varchar(32) NOT NULL COMMENT '城市',
  `county` varchar(32) NOT NULL COMMENT '县/区',
  `address_detail` varchar(32) NOT NULL COMMENT '详细收货地址',
  `area_code` varchar(32) NOT NULL COMMENT '地区编码',
  `postal_code` varchar(32) NOT NULL COMMENT '邮政编码',
  `tel` varchar(32) NOT NULL COMMENT '手机号码',
  `is_default` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否默认地址',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_admin
-- ----------------------------
DROP TABLE IF EXISTS `tbl_admin`;
CREATE TABLE `tbl_admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(64) NOT NULL COMMENT '管理员名称',
  `password` varchar(64) NOT NULL COMMENT '管理员密码',
  `last_login_ip` varchar(64) NOT NULL COMMENT '最近一次登录时间',
  `last_login_time` datetime NOT NULL COMMENT '最近一次登录时间',
  `avatar` varchar(64) NOT NULL COMMENT '头像图片',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `role_ids` varchar(127) NOT NULL DEFAULT '[]' COMMENT '角色列表',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_brand
-- ----------------------------
DROP TABLE IF EXISTS `tbl_brand`;
CREATE TABLE `tbl_brand` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '品牌商名称',
  `desc` varchar(255) NOT NULL COMMENT '品牌商简介',
  `pic_url` varchar(255) NOT NULL COMMENT '品牌商页的品牌商图片',
  `sort_order` tinyint(3) unsigned NOT NULL DEFAULT '50' COMMENT '排序',
  `floor_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '品牌商的商品低价，仅用于页面展示',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1046001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_cart
-- ----------------------------
DROP TABLE IF EXISTS `tbl_cart`;
CREATE TABLE `tbl_cart` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `goods_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '商品表的商品ID',
  `goods_sn` varchar(64) NOT NULL DEFAULT '[]' COMMENT '商品编号',
  `goods_name` varchar(127) NOT NULL DEFAULT '[]' COMMENT '商品名称',
  `product_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '商品货品表的货品ID',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品货品的价格',
  `number` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '商品货品的数量',
  `specifications` varchar(1023) NOT NULL DEFAULT '[]' COMMENT '商品规格值列表，采用JSON数组格式',
  `checked` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '购物车中商品是否选择状态',
  `pic_url` varchar(64) NOT NULL COMMENT '商品图片或者商品货品图片',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_category
-- ----------------------------
DROP TABLE IF EXISTS `tbl_category`;
CREATE TABLE `tbl_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL COMMENT '类目名称',
  `keywords` varchar(1023) NOT NULL COMMENT '类目关键字，以JSON数组格式',
  `desc` varchar(255) NOT NULL COMMENT '类目广告语介绍',
  `pid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '父类目ID',
  `icon_url` varchar(255) NOT NULL COMMENT '类目图标',
  `pic_url` varchar(255) NOT NULL COMMENT '类目图片',
  `level` varchar(32) NOT NULL COMMENT 'L1',
  `sort_order` tinyint(3) unsigned NOT NULL DEFAULT '50' COMMENT '排序',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1036005 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_collect
-- ----------------------------
DROP TABLE IF EXISTS `tbl_collect`;
CREATE TABLE `tbl_collect` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `value_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '如果type=0，则是商品ID；如果type=1，则是专题ID',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '收藏类型，如果type=0，则是商品ID；如果type=1，则是专题ID',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_comment
-- ----------------------------
DROP TABLE IF EXISTS `tbl_comment`;
CREATE TABLE `tbl_comment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '如果type=0，则是商品评论；如果是type=1，则是专题评论',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '评论类型，如果type=0，则是商品评论；如果是type=1，则是专题评论；如果type=3，则是订单商品评论',
  `content` varchar(1023) NOT NULL COMMENT '评论内容',
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `has_picture` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否含有图片',
  `pic_urls` varchar(1023) NOT NULL COMMENT '图片地址列表，采用JSON数组格式',
  `star` smallint(5) unsigned NOT NULL DEFAULT '1' COMMENT '评分， 1-5',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1012 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_coupon
-- ----------------------------
DROP TABLE IF EXISTS `tbl_coupon`;
CREATE TABLE `tbl_coupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL COMMENT '优惠券名称',
  `desc` varchar(127) NOT NULL COMMENT '优惠券介绍，通常是显示优惠券使用限制文字',
  `tag` varchar(64) NOT NULL COMMENT '优惠券标签，例如新人专用',
  `total` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '优惠券数量，如果是0，则是无限量',
  `discount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '优惠金额',
  `min` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '最少消费金额才能使用优惠券',
  `limit` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '用户领券限制数量，如果是0，则是不限制；默认是1，限领一张',
  `type` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '优惠券赠送类型，如果是0则通用券，用户领取；如果是1，则是注册赠券；如果是2，则是优惠券码兑换',
  `status` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '优惠券状态，如果是0则是正常可用；如果是1则是过期; 如果是2则是下架',
  `goods_type` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '商品限制类型，如果0则全商品，如果是1则是类目限制，如果是2则是商品限制',
  `goods_value` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '商品限制值，goods_type如果是0则空集合，如果是1则是类目集合，如果是2则是商品集合',
  `code` varchar(64) NOT NULL COMMENT '优惠券兑换码',
  `time_type` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '有效时间限制，如果是0，则基于领取时间的有效天数days；如果是1，则start_time和end_time是优惠券有效期',
  `days` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '基于领取时间的有效天数days',
  `start_time` datetime NOT NULL COMMENT '使用券开始时间',
  `end_time` datetime NOT NULL COMMENT '使用券截至时间',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_coupon_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_coupon_user`;
CREATE TABLE `tbl_coupon_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `coupon_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '优惠券ID',
  `status` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '使用状态, 如果是0则未使用；如果是1则已使用；如果是2则已过期；如果是3则已经下架',
  `used_time` datetime NOT NULL COMMENT '使用时间',
  `start_time` datetime NOT NULL COMMENT '有效期开始时间',
  `end_time` datetime NOT NULL COMMENT '有效期截至时间',
  `order_id` int(10) unsigned DEFAULT '0' COMMENT '订单ID',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_feedback
-- ----------------------------
DROP TABLE IF EXISTS `tbl_feedback`;
CREATE TABLE `tbl_feedback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `user_name` varchar(64) NOT NULL COMMENT '用户名称',
  `mobile` varchar(32) NOT NULL COMMENT '手机号',
  `feed_type` varchar(64) NOT NULL COMMENT '反馈类型',
  `content` varchar(1023) NOT NULL COMMENT '反馈内容',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '状态',
  `has_picture` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否含有图片',
  `pic_urls` varchar(1023) NOT NULL COMMENT '图片地址列表，采用JSON数组格式',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_footprint
-- ----------------------------
DROP TABLE IF EXISTS `tbl_footprint`;
CREATE TABLE `tbl_footprint` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `goods_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '商品表的商品ID',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_goods
-- ----------------------------
DROP TABLE IF EXISTS `tbl_goods`;
CREATE TABLE `tbl_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_sn` varchar(255) NOT NULL COMMENT '商品编号',
  `name` varchar(255) NOT NULL COMMENT '商品名称',
  `category_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '商品所属类目ID',
  `brand_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '品牌ID',
  `gallery` varchar(255) NOT NULL COMMENT '商品宣传图片列表，采用JSON数组格式',
  `keywords` varchar(255) NOT NULL COMMENT '商品关键字，采用逗号间隔',
  `brief` varchar(255) NOT NULL COMMENT '商品简介',
  `is_on_sale` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '是否上架',
  `sort_order` smallint(5) unsigned NOT NULL DEFAULT '100' COMMENT '排序',
  `pic_url` varchar(255) NOT NULL COMMENT '商品页面商品图片',
  `share_url` varchar(255) NOT NULL COMMENT '商品分享朋友圈图片',
  `is_new` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否新品首发，如果设置则可以在新品首发页面展示',
  `is_hot` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否人气推荐，如果设置则可以在人气推荐页面展示',
  `unit` varchar(255) NOT NULL DEFAULT '’件‘' COMMENT '商品单位，例如件、盒',
  `counter_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '专柜价格',
  `retail_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '零售价格',
  `detail` text NOT NULL COMMENT '商品详细介绍，是富文本格式',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=1181001 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_goods_attribute
-- ----------------------------
DROP TABLE IF EXISTS `tbl_goods_attribute`;
CREATE TABLE `tbl_goods_attribute` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '商品表的商品ID',
  `attribute` varchar(255) NOT NULL COMMENT '商品参数名称',
  `value` varchar(255) NOT NULL COMMENT '商品参数值',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=877 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_goods_product
-- ----------------------------
DROP TABLE IF EXISTS `tbl_goods_product`;
CREATE TABLE `tbl_goods_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '商品表的商品ID',
  `specifications` varchar(1023) NOT NULL DEFAULT '[]' COMMENT '商品规格值列表，采用JSON数组格式',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品货品价格',
  `number` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '商品货品数量',
  `url` varchar(500) NOT NULL COMMENT '商品货品图片',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=245 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_goods_specification
-- ----------------------------
DROP TABLE IF EXISTS `tbl_goods_specification`;
CREATE TABLE `tbl_goods_specification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '商品表的商品ID',
  `specifications` varchar(1023) NOT NULL DEFAULT '[]' COMMENT '商品规格值列表，采用JSON数组格式',
  `value` varchar(500) NOT NULL COMMENT '商品规格值',
  `pic_url` varchar(500) NOT NULL COMMENT '商品规格图片',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=244 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_groupon
-- ----------------------------
DROP TABLE IF EXISTS `tbl_groupon`;
CREATE TABLE `tbl_groupon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(10) unsigned DEFAULT '0' COMMENT '关联的订单ID',
  `groupon_id` int(10) unsigned DEFAULT '0' COMMENT '参与的团购ID，仅当user_type不是1',
  `rules_id` int(10) unsigned DEFAULT '0' COMMENT '团购规则ID，关联litemall_groupon_rules表ID字段',
  `user_id` int(10) unsigned DEFAULT '0' COMMENT '用户ID',
  `creator_user_id` int(10) unsigned DEFAULT '0' COMMENT '创建者ID',
  `share_url` varchar(500) NOT NULL COMMENT '团购分享图片地址',
  `payed` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否已经支付',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_groupon_rules
-- ----------------------------
DROP TABLE IF EXISTS `tbl_groupon_rules`;
CREATE TABLE `tbl_groupon_rules` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '商品表的商品ID',
  `goods_name` varchar(127) NOT NULL DEFAULT '[]' COMMENT '商品名称',
  `pic_url` varchar(255) NOT NULL COMMENT '商品图片或者商品货品图片',
  `discount` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '优惠金额',
  `discount_member` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '达到优惠条件的人数',
  `expire_time` datetime NOT NULL COMMENT '团购过期时间',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '团购规则状态，正常上线则0，到期自动下线则1，管理手动下线则2',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_issue
-- ----------------------------
DROP TABLE IF EXISTS `tbl_issue`;
CREATE TABLE `tbl_issue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(255) NOT NULL COMMENT '问题标题',
  `answer` varchar(255) NOT NULL COMMENT '问题答案',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_keyword
-- ----------------------------
DROP TABLE IF EXISTS `tbl_keyword`;
CREATE TABLE `tbl_keyword` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `keyword` varchar(32) NOT NULL COMMENT '关键字',
  `url` varchar(255) NOT NULL COMMENT '关键字的跳转链接',
  `is_hot` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否是热门关键字',
  `is_default` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '是否是默认关键字',
  `sort_order` tinyint(3) unsigned NOT NULL DEFAULT '100' COMMENT '排序',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_log
-- ----------------------------
DROP TABLE IF EXISTS `tbl_log`;
CREATE TABLE `tbl_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin` varchar(32) NOT NULL COMMENT '管理员',
  `ip` varchar(32) NOT NULL COMMENT '管理员地址',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '操作分类',
  `action` varchar(45) NOT NULL COMMENT '操作动作',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '操作状态',
  `result` varchar(255) DEFAULT NULL COMMENT '操作结果，或者成功消息，或者失败消息',
  `comment` varchar(255) DEFAULT NULL COMMENT '补充信息',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_order
-- ----------------------------
DROP TABLE IF EXISTS `tbl_order`;
CREATE TABLE `tbl_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `order_sn` char(15) NOT NULL COMMENT '订单编号',
  `order_status` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '订单状态',
  `consignee` char(15) NOT NULL COMMENT '收货人名称',
  `mobile` char(15) NOT NULL COMMENT '收货人手机号',
  `address` char(15) NOT NULL COMMENT '收货具体地址',
  `message` char(15) NOT NULL COMMENT '用户订单留言',
  `goods_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品总费用',
  `freight_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '配送费用',
  `coupon_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '优惠券减免',
  `integral_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '用户积分减免',
  `groupon_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '团购优惠价减免',
  `order_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '订单费用， = goods_price + freight_price - coupon_price',
  `actual_price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '实付费用， = order_price - integral_price',
  `pay_id` char(15) NOT NULL COMMENT '微信付款编号',
  `pay_time` datetime NOT NULL COMMENT '微信付款时间',
  `ship_sn` char(15) NOT NULL COMMENT '发货编号',
  `ship_channel` char(15) NOT NULL COMMENT '发货快递公司',
  `ship_time` datetime NOT NULL COMMENT '发货开始时间',
  `confirm_time` datetime NOT NULL COMMENT '用户确认收货时间',
  `comments` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '待评价订单商品数量',
  `end_time` datetime NOT NULL COMMENT '订单关闭时间',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_order_goods
-- ----------------------------
DROP TABLE IF EXISTS `tbl_order_goods`;
CREATE TABLE `tbl_order_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '订单表的订单ID',
  `goods_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '商品表的商品ID',
  `goods_sn` varchar(64) NOT NULL DEFAULT '[]' COMMENT '商品编号',
  `goods_name` varchar(127) NOT NULL DEFAULT '[]' COMMENT '商品名称',
  `product_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '商品货品表的货品ID',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品货品的价格',
  `number` smallint(5) unsigned NOT NULL DEFAULT '0' COMMENT '商品货品的数量',
  `specifications` varchar(1023) NOT NULL DEFAULT '[]' COMMENT '商品规格值列表，采用JSON数组格式',
  `checked` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '购物车中商品是否选择状态',
  `pic_url` varchar(64) NOT NULL COMMENT '商品图片或者商品货品图片',
  `comment` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '订单商品评论，如果是-1，则超期不能评价；如果是0，则可以评价；如果其他值，则是comment表里面的评论ID',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_permission
-- ----------------------------
DROP TABLE IF EXISTS `tbl_permission`;
CREATE TABLE `tbl_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '角色ID',
  `permission` varchar(1023) NOT NULL DEFAULT '[]' COMMENT '权限',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_region
-- ----------------------------
DROP TABLE IF EXISTS `tbl_region`;
CREATE TABLE `tbl_region` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '行政区域父ID，例如区县的pid指向市，市的pid指向省，省的pid则是0',
  `name` varchar(64) NOT NULL COMMENT '行政区域名称',
  `type` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '行政区域类型，如如1则是省， 如果是2则是市，如果是3则是区县',
  `code` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '行政区域编码',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3232 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_role
-- ----------------------------
DROP TABLE IF EXISTS `tbl_role`;
CREATE TABLE `tbl_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL COMMENT '角色名称',
  `desc` varchar(64) NOT NULL COMMENT '角色描述',
  `enabled` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '是否启用',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_search_history
-- ----------------------------
DROP TABLE IF EXISTS `tbl_search_history`;
CREATE TABLE `tbl_search_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '用户表的用户ID',
  `keyword` varchar(32) NOT NULL COMMENT '关键字',
  `from` varchar(32) NOT NULL COMMENT '搜索来源，如pc、wx、app',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_storage
-- ----------------------------
DROP TABLE IF EXISTS `tbl_storage`;
CREATE TABLE `tbl_storage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(32) NOT NULL COMMENT '文件的唯一索引',
  `name` varchar(32) NOT NULL COMMENT '文件名',
  `type` varchar(32) NOT NULL COMMENT '文件类型',
  `size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '文件大小',
  `url` varchar(32) NOT NULL COMMENT '文件访问链接',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_system
-- ----------------------------
DROP TABLE IF EXISTS `tbl_system`;
CREATE TABLE `tbl_system` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key_name` varchar(32) NOT NULL COMMENT '文件名',
  `key_value` varchar(32) NOT NULL COMMENT '文件名',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_topic
-- ----------------------------
DROP TABLE IF EXISTS `tbl_topic`;
CREATE TABLE `tbl_topic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL COMMENT '专题标题',
  `subtitle` varchar(255) NOT NULL DEFAULT '' COMMENT '关键字',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '专题相关商品最低价',
  `read_count` varchar(32) NOT NULL COMMENT '关键字',
  `pic_url` varchar(32) NOT NULL COMMENT '关键字',
  `sort_order` tinyint(3) unsigned NOT NULL DEFAULT '100' COMMENT '排序',
  `goods` int(10) unsigned NOT NULL DEFAULT '100' COMMENT '排序',
  `content` text NOT NULL COMMENT '专题内容，富文本格式',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=315 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_user
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) NOT NULL COMMENT '用户名称',
  `password` varchar(32) NOT NULL COMMENT '用户密码',
  `gender` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '性别：0->未知；1->男；2->女',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `last_login_time` datetime DEFAULT NULL COMMENT '最近一次登录时间',
  `last_login_ip` varchar(32) NOT NULL COMMENT '最近一次登录IP地址',
  `user_level` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '0 普通用户，1 VIP用户，2 高级VIP用户',
  `nick_name` varchar(32) NOT NULL COMMENT '用户昵称或网络名称',
  `mobile` varchar(32) DEFAULT NULL COMMENT '用户手机号码',
  `avatar` varchar(32) DEFAULT NULL COMMENT '用户头像图片',
  `weixin_openid` varchar(32) DEFAULT NULL COMMENT '微信登录openid',
  `session_key` varchar(32) DEFAULT NULL COMMENT '微信登录会话KEY',
  `status` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for tbl_user_formid
-- ----------------------------
DROP TABLE IF EXISTS `tbl_user_formid`;
CREATE TABLE `tbl_user_formid` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `form_id` varchar(32) NOT NULL COMMENT '缓存的FormId',
  `is_prepay` tinyint(3) unsigned NOT NULL DEFAULT '1' COMMENT '是FormId还是prepayId',
  `use_amount` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '可用次数，fromId为1，prepay为3，用1次减1',
  `expire_time` datetime NOT NULL COMMENT '过期时间，腾讯规定为7天',
  `open_id` varchar(32) NOT NULL COMMENT '微信登录openid',
  `add_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `deleted` tinyint(3) unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

SET FOREIGN_KEY_CHECKS = 1;
