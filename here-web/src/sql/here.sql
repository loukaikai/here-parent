/*
 Navicat MySQL Data Transfer

 Source Server         : 121.40.214.140
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : 121.40.214.140:3306
 Source Schema         : here_admin

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 08/01/2023 22:53:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for here_award_usr_detail
-- ----------------------------
DROP TABLE IF EXISTS `here_award_usr_detail`;
CREATE TABLE `here_award_usr_detail` (
                                         `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                         `award_sub_type_id` int NOT NULL COMMENT '奖励类型 1-现金；2-优惠券；3-虚拟物品',
                                         `badge_id` int NOT NULL COMMENT '徽章ID',
                                         `award_status` int DEFAULT NULL COMMENT '0-领取；1-使用；2-过期',
                                         `user_id` int DEFAULT NULL COMMENT '用户',
                                         `status` int NOT NULL COMMENT '状态 1-启用 2-停用 3-删除',
                                         `receive_time` datetime DEFAULT NULL COMMENT '领取时间',
                                         `use_time` datetime DEFAULT NULL COMMENT '使用时间',
                                         `expired_time` datetime DEFAULT NULL COMMENT '过期时间',
                                         `CREATED_BY` int DEFAULT NULL COMMENT '创建人',
                                         `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
                                         `UPDATED_BY` int DEFAULT NULL COMMENT '更新人',
                                         `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
                                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='奖励使用详情';

-- ----------------------------
-- Table structure for here_badge
-- ----------------------------
DROP TABLE IF EXISTS `here_badge`;
CREATE TABLE `here_badge` (
                              `id` int NOT NULL AUTO_INCREMENT,
                              `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '徽章名称',
                              `user_id` int NOT NULL COMMENT '徽章所属用户ID',
                              `badge_url` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '徽章url',
                              `create_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3),
                              `update_time` datetime(3) DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
                              PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='徽章';

-- ----------------------------
-- Table structure for here_nameplate
-- ----------------------------
DROP TABLE IF EXISTS `here_nameplate`;
CREATE TABLE `here_nameplate` (
                                  `id` int NOT NULL COMMENT '唯一标识',
                                  `user_id` int NOT NULL COMMENT '铭牌所属用户ID',
                                  `nameplate_number` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '铭牌编号',
                                  `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '记录创建时间',
                                  `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '记录更新时间',
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `uniq_namplate_user` (`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='铭牌信息表';

-- ----------------------------
-- Table structure for here_nameplate_assist
-- ----------------------------
DROP TABLE IF EXISTS `here_nameplate_assist`;
CREATE TABLE `here_nameplate_assist` (
                                         `id` int NOT NULL COMMENT '唯一标识',
                                         `apply_user_id` int NOT NULL COMMENT '申请铭牌用户ID',
                                         `assist_user_id` int NOT NULL COMMENT '助力用户ID',
                                         `create_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '记录创建时间',
                                         `update_time` datetime(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '记录更新时间',
                                         PRIMARY KEY (`id`),
                                         UNIQUE KEY `uniq_apply_assist_user` (`apply_user_id`,`assist_user_id`) USING BTREE,
                                         KEY `idx_apply_user` (`apply_user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='铭牌助力表';

-- ----------------------------
-- Table structure for here_nameplate_power
-- ----------------------------
DROP TABLE IF EXISTS `here_nameplate_power`;
CREATE TABLE `here_nameplate_power` (
                                        `id` int NOT NULL AUTO_INCREMENT,
                                        `user_id` int DEFAULT NULL COMMENT '所属用户id',
                                        `power_user_id` int DEFAULT NULL COMMENT '助力用户id',
                                        `power_user_heads` varchar(50) DEFAULT NULL COMMENT '助力用户头像url',
                                        `create_time` datetime DEFAULT NULL COMMENT '助力时间',
                                        PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='铭牌助力';

-- ----------------------------
-- Table structure for here_orders
-- ----------------------------
DROP TABLE IF EXISTS `here_orders`;
CREATE TABLE `here_orders` (
                               `id` int(11) NOT NULL AUTO_INCREMENT,
                               `order_no` varchar(50) DEFAULT NULL COMMENT '订单号',
                               `user_id` int(11) DEFAULT NULL,
                               `user_code` varchar(255) DEFAULT NULL COMMENT '用户code',
                               `goods_id` int(11) DEFAULT NULL,
                               `goods_title` varchar(255) DEFAULT NULL COMMENT '商品标题 商品名',
                               `goods_detail_id` int(11) DEFAULT NULL,
                               `goods_specifications` varchar(255) DEFAULT NULL COMMENT '规格',
                               `address_id` int(11) DEFAULT NULL,
                               `buy_count` int(11) DEFAULT NULL COMMENT '购买数量',
                               `unit_price` decimal(10,2) DEFAULT NULL COMMENT '单价',
                               `sum_price` decimal(10,2) DEFAULT NULL COMMENT '总价',
                               `freight_price` decimal(10,2) DEFAULT NULL COMMENT '运费',
                               `discount_amount` decimal(10,2) DEFAULT NULL COMMENT '优惠金额',
                               `actual_payment` decimal(10,2) DEFAULT NULL COMMENT '实付款',
                               `status` int DEFAULT NULL COMMENT '订单状态 0待付款 1待发货 2运送中 3待收货 4已送达待确认 5交易成功 99退款中 100退款成功',
                               `create_time` datetime DEFAULT NULL,
                               `payment_time` datetime DEFAULT NULL COMMENT '付款时间',
                               `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
                               `update_time` datetime DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb3 COMMENT='订单表';

-- ----------------------------
-- Table structure for here_user
-- ----------------------------
DROP TABLE IF EXISTS `here_user`;
CREATE TABLE `here_user` (
                             `id` int NOT NULL AUTO_INCREMENT,
                             `here_code` varchar(50) DEFAULT NULL,
                             `phone` varchar(18) DEFAULT NULL COMMENT '用户绑定的手机号（国外手机号会有区号',
                             `wechar_name` varchar(50) DEFAULT NULL COMMENT '微信名',
                             `wechat_no` varchar(50) DEFAULT NULL COMMENT '微信号',
                             `wechar_heads_url` varchar(50) DEFAULT NULL COMMENT '头像url',
                             `invitation_id` int DEFAULT NULL COMMENT '邀请人id',
                             `Invitation_code` varchar(50) DEFAULT NULL COMMENT '邀请码',
                             `my_code` varchar(50) DEFAULT NULL COMMENT '我的邀请码',
                             `nameplate_flag` int DEFAULT NULL COMMENT '铭牌状态 0未点亮 1点亮',
                             `user_status` int DEFAULT NULL COMMENT '  停用 启用',
                             `union_id` varchar(255) DEFAULT NULL COMMENT '小程序unionid',
                             `open_id` varchar(255) DEFAULT NULL COMMENT '小程序openid',
                             `pure_phone_number` varchar(15) DEFAULT NULL COMMENT '没有区号的手机号',
                             `country_code` varchar(4) DEFAULT NULL COMMENT '区号',
                             `create_time` datetime DEFAULT NULL,
                             `update_time` datetime DEFAULT NULL,
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1195 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Table structure for pms_award_rule
-- ----------------------------
DROP TABLE IF EXISTS `pms_award_rule`;
CREATE TABLE `pms_award_rule` (
                                  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                  `source_id` int DEFAULT NULL,
                                  `user_id` int DEFAULT NULL,
                                  `times` int DEFAULT NULL COMMENT '抽奖次数',
                                  `status` varchar(2) NOT NULL COMMENT '标识 0-生效；1-失效。',
                                  `platform_flag` varchar(2) DEFAULT NULL COMMENT '平台标识 1-微信小程序；2-抖音;3-通用',
                                  `CREATED_BY` int DEFAULT NULL COMMENT '创建人',
                                  `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
                                  `UPDATED_BY` int DEFAULT NULL COMMENT '更新人',
                                  `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='奖励规则达标表';

-- ----------------------------
-- Table structure for pms_award_source
-- ----------------------------
DROP TABLE IF EXISTS `pms_award_source`;
CREATE TABLE `pms_award_source` (
                                    `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                    `award_source_type` int NOT NULL COMMENT '奖励类型 1-宝箱；2-邀请奖励',
                                    `award_rule` varchar(500) NOT NULL COMMENT '奖励规则',
                                    `award_source_name` varchar(100) DEFAULT NULL COMMENT '奖励类型名称',
                                    `award_rule_id` varchar(255) DEFAULT NULL COMMENT '奖励规则id',
                                    `status` varchar(32) NOT NULL COMMENT '状态 0-启用 2-停用 3-删除',
                                    `start_time` datetime DEFAULT NULL COMMENT '开始时间',
                                    `end_time` datetime DEFAULT NULL COMMENT '结束时间',
                                    `CREATED_BY` int DEFAULT NULL COMMENT '创建人',
                                    `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
                                    `UPDATED_BY` int DEFAULT NULL COMMENT '更新人',
                                    `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='奖励来源配置';

-- ----------------------------
-- Table structure for pms_award_sub_type
-- ----------------------------
DROP TABLE IF EXISTS `pms_award_sub_type`;
CREATE TABLE `pms_award_sub_type` (
                                      `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                      `award_type_id` int NOT NULL COMMENT '奖励类型id',
                                      `award_subtype_type_name` varchar(100) DEFAULT NULL COMMENT '奖励子类名称',
                                      `award_virtual_id` int DEFAULT NULL COMMENT '虚拟奖励id',
                                      `status` varchar(32) NOT NULL COMMENT '状态 0-启用 2-停用 3-删除',
                                      `effctive_days` int DEFAULT NULL COMMENT '有效时间（-1为永远不过期）',
                                      `CREATED_BY` varchar(32) DEFAULT NULL COMMENT '创建人',
                                      `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
                                      `UPDATED_BY` varchar(32) DEFAULT NULL COMMENT '更新人',
                                      `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
                                      PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='奖励子类型（具体的奖励）';

-- ----------------------------
-- Table structure for pms_award_type
-- ----------------------------
DROP TABLE IF EXISTS `pms_award_type`;
CREATE TABLE `pms_award_type` (
                                  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键id',
                                  `award_type` int NOT NULL COMMENT '奖励类型 1-现金；2-优惠券；3-虚拟物品',
                                  `award_type_name` varchar(100) DEFAULT NULL COMMENT '奖励类型名称',
                                  `award_budget` int DEFAULT NULL COMMENT '奖励预算',
                                  `awrad_budget_use` int DEFAULT NULL COMMENT '奖励预算使用',
                                  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '状态 1-启用 2-停用 3-删除',
                                  `CREATED_BY` int DEFAULT NULL COMMENT '创建人',
                                  `CREATED_TIME` datetime DEFAULT NULL COMMENT '创建时间',
                                  `UPDATED_BY` int DEFAULT NULL COMMENT '更新人',
                                  `UPDATED_TIME` datetime DEFAULT NULL COMMENT '更新时间',
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='奖励类型';

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
                           `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `module_name` varchar(256) DEFAULT NULL COMMENT '模块名称',
                           `browser_name` varchar(1024) DEFAULT NULL COMMENT '浏览器名称',
                           `os_name` varchar(256) DEFAULT NULL COMMENT '操作系统名称',
                           `ip_addr` varchar(256) DEFAULT NULL COMMENT '请求ip',
                           `app_name` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '服务名称',
                           `class_name` varchar(1024) DEFAULT NULL COMMENT '类名',
                           `method_name` varchar(512) DEFAULT NULL COMMENT '方法',
                           `request_url` varchar(1024) DEFAULT NULL COMMENT '请求url',
                           `request_method` varchar(255) DEFAULT NULL COMMENT '请求方式，POST、GET',
                           `request_param` text COMMENT '请求参数',
                           `result_text` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci COMMENT '响应参数',
                           `status` tinyint(1) DEFAULT NULL COMMENT '接口状态（0成功 1失败）',
                           `error_text` text COMMENT '错误信息',
                           `take_up_time` varchar(64) DEFAULT NULL COMMENT '耗时',
                           `edit_table_id` bigint DEFAULT NULL COMMENT '编辑的表主键，只有修改时才有值',
                           `edit_table_name` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '编辑的表名称，只有修改时才有值',
                           `create_time` datetime DEFAULT NULL COMMENT '操作时间',
                           `create_user_id` bigint DEFAULT NULL COMMENT '创建人id',
                           `create_phone_number` varchar(11) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL COMMENT '创建人手机号',
                           `create_user_name` varchar(64) DEFAULT NULL COMMENT '创建人姓名',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2595 DEFAULT CHARSET=utf8mb3 COMMENT='系统操作日志';

SET FOREIGN_KEY_CHECKS = 1;
