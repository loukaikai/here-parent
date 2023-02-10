/*
Navicat MySQL Data Transfer
Source Server         : localhost
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : mall_tiny
Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001
Date: 2020-08-24 14:06:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for ums_admin
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin`;
CREATE TABLE `ums_admin` (
                             `id` bigint(20) NOT NULL AUTO_INCREMENT,
                             `username` varchar(64) DEFAULT NULL,
                             `password` varchar(64) DEFAULT NULL,
                             `icon` varchar(500) DEFAULT NULL COMMENT '头像',
                             `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
                             `nick_name` varchar(200) DEFAULT NULL COMMENT '昵称',
                             `note` varchar(500) DEFAULT NULL COMMENT '备注信息',
                             `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                             `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
                             `status` int(1) DEFAULT '1' COMMENT '帐号启用状态：0->禁用；1->启用',
                             PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='后台用户表';

-- ----------------------------
-- Records of ums_admin
-- ----------------------------
INSERT INTO `ums_admin` VALUES ('1', 'test', '$2a$10$NZ5o7r2E.ayT2ZoxgjlI.eJ6OEYqjH7INR/F.mXDbjZJi9HF0YCVG', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', 'test@qq.com', '测试账号', null, '2018-09-29 13:55:30', '2018-09-29 13:55:39', '1');
INSERT INTO `ums_admin` VALUES ('3', 'admin', '$2a$10$.E1FokumK5GIXWgKlg.Hc.i/0/2.qdAwYFL1zc5QHdyzpXOr38RZO', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', 'admin@163.com', '系统管理员', '系统管理员', '2018-10-08 13:32:47', '2019-04-20 12:45:16', '1');
INSERT INTO `ums_admin` VALUES ('4', 'macro', '$2a$10$Bx4jZPR7GhEpIQfefDQtVeS58GfT5n6mxs/b4nLLK65eMFa16topa', 'string', 'macro@qq.com', 'macro', 'macro专用', '2019-10-06 15:53:51', '2020-02-03 14:55:55', '1');
INSERT INTO `ums_admin` VALUES ('6', 'productAdmin', '$2a$10$6/.J.p.6Bhn7ic4GfoB5D.pGd7xSiD1a9M6ht6yO0fxzlKJPjRAGm', null, 'product@qq.com', '商品管理员', '只有商品权限', '2020-02-07 16:15:08', null, '1');
INSERT INTO `ums_admin` VALUES ('7', 'orderAdmin', '$2a$10$UqEhA9UZXjHHA3B.L9wNG.6aerrBjC6WHTtbv1FdvYPUI.7lkL6E.', null, 'order@qq.com', '订单管理员', '只有订单管理权限', '2020-02-07 16:15:50', null, '1');
INSERT INTO `ums_admin` VALUES ('10', 'ceshi', '$2a$10$RaaNo9CC0RSms8mc/gJpCuOWndDT4pHH0u5XgZdAAYFs1Uq4sOPRi', null, 'ceshi@qq.com', 'ceshi', null, '2020-03-13 16:23:30', null, '1');


-- ----------------------------
-- Table structure for ums_admin_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_role_relation`;
CREATE TABLE `ums_admin_role_relation` (
                                           `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                           `admin_id` bigint(20) DEFAULT NULL,
                                           `role_id` bigint(20) DEFAULT NULL,
                                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COMMENT='后台用户和角色关系表';


-- ----------------------------
-- Table structure for ums_menu
-- ----------------------------
DROP TABLE IF EXISTS `ums_menu`;
CREATE TABLE `ums_menu` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `parent_id` bigint(20) DEFAULT NULL COMMENT '父级ID',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `title` varchar(100) DEFAULT NULL COMMENT '菜单名称',
                            `level` int(4) DEFAULT NULL COMMENT '菜单级数',
                            `sort` int(4) DEFAULT NULL COMMENT '菜单排序',
                            `name` varchar(100) DEFAULT NULL COMMENT '前端名称',
                            `icon` varchar(200) DEFAULT NULL COMMENT '前端图标',
                            `hidden` int(1) DEFAULT NULL COMMENT '前端隐藏',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='后台菜单表';

------------------------
-- Table structure for ums_resource
-- ----------------------------
DROP TABLE IF EXISTS `ums_resource`;
CREATE TABLE `ums_resource` (
                                `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                `name` varchar(200) DEFAULT NULL COMMENT '资源名称',
                                `url` varchar(200) DEFAULT NULL COMMENT '资源URL',
                                `description` varchar(500) DEFAULT NULL COMMENT '描述',
                                `category_id` bigint(20) DEFAULT NULL COMMENT '资源分类ID',
                                PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COMMENT='后台资源表';

-- ----------------------------
-- Table structure for ums_resource_category
-- ----------------------------
DROP TABLE IF EXISTS `ums_resource_category`;
CREATE TABLE `ums_resource_category` (
                                         `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                         `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                                         `name` varchar(200) DEFAULT NULL COMMENT '分类名称',
                                         `sort` int(4) DEFAULT NULL COMMENT '排序',
                                         PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='资源分类表';

-- ----------------------------
-- Records of ums_resource_category
-- ----------------------------


-- ----------------------------
-- Table structure for ums_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role` (
                            `id` bigint(20) NOT NULL AUTO_INCREMENT,
                            `name` varchar(100) DEFAULT NULL COMMENT '名称',
                            `description` varchar(500) DEFAULT NULL COMMENT '描述',
                            `admin_count` int(11) DEFAULT NULL COMMENT '后台用户数量',
                            `create_time` datetime DEFAULT NULL COMMENT '创建时间',
                            `status` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
                            `sort` int(11) DEFAULT '0',
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='后台用户角色表';

-- ----------------------------
-- Records of ums_role
-- ----------------------------

-- ----------------------------
-- Table structure for ums_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_menu_relation`;
CREATE TABLE `ums_role_menu_relation` (
                                          `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                          `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
                                          `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
                                          PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8 COMMENT='后台角色菜单关系表';

-- ----------------------------
-- Table structure for ums_role_resource_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_resource_relation`;
CREATE TABLE `ums_role_resource_relation` (
                                              `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                              `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
                                              `resource_id` bigint(20) DEFAULT NULL COMMENT '资源ID',
                                              PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=216 DEFAULT CHARSET=utf8 COMMENT='后台角色资源关系表';

-- ----------------------------
-- Records of ums_role_resource_relation
-- ----------------------------
