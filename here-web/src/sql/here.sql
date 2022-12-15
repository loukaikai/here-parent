create table here_badge
(
    id          int auto_increment
        primary key,
    name        varchar(50) null comment '徽章名称',
    model       varchar(50) null comment '获取方式/url',
    create_time datetime    null,
    update_time datetime    null
)
    comment '徽章';

create table here_goods
(
    id                int          not null
        primary key,
    goods_title       varchar(255) null,
    goods_name        varchar(255) null,
    shop_id           int          null,
    ship_name         varchar(255) null,
    user_id           int          null,
    type              int          null comment '商品类型',
    trad_volume       int          null comment '成交数',
    goods_description varchar(255) null,
    create_time       datetime     null
)
comment '商品';

create table here_goods_detail
(
    id               int auto_increment
        primary key,
    goods_id         int            null,
    specifications   varchar(50)    null comment '规格',
    description      varchar(255)   null,
    price            decimal(10, 2) null comment '商品价格',
    status           int            null comment '商品状态 下架 缺货 正常等',
    Inventory        int            null comment '库存',
    shipping_address varchar(50)    null comment '发货地',
    create_time      datetime       null comment '上架时间',
    update_time      datetime       null
) comment '商品规格';

create table here_nameplate_power
(
    id               int auto_increment
        primary key,
    user_id          int         null comment '所属用户id',
    power_user_id    int         null comment '助力用户id',
    power_user_heads varchar(50) null comment '助力用户头像url',
    create_time      datetime    null comment '助力时间'
)
    comment '铭牌助力';

create table here_shop
(
    id          int auto_increment
        primary key,
    user_id     int         null,
    shop_name   varchar(50) null,
    status      int         null,
    create_time datetime    null,
    update_time datetime    null
) comment '店铺';

create table here_user
(
    id               int auto_increment
        primary key,
    here_code        varchar(50) null,
    phone            varchar(12) null comment '微信绑定的手机号',
    wechar_name      varchar(50) null comment '微信名',
    wechat_no        varchar(50) null comment '微信号',
    wechar_heads_url varchar(50) null comment '头像url',
    invitation_id    int         null comment '邀请人id',
    Invitation_code  varchar(50) null comment '邀请码',
    my_code          varchar(50) null comment '我的邀请码',
    nameplate_flag   int         null comment '铭牌状态 0未点亮 1点亮',
    user_status      int         null comment '  停用 启用',
    union_id         varchar(255) null comment '小程序unionid',
    open_id          varchar(255) null comment '小程序openid',
    create_time      datetime    null,
    update_time      datetime    null
)
    comment '用户表';

create table here_user_badge
(
    id          int      not null
        primary key,
    user_id     int      null,
    badge_id    int      null comment '徽章id',
    create_time datetime null comment '获取时间'
)
    comment '用户徽章关联表';


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_log 系统操作日志
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
                           `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
                           `module_name` varchar(256) DEFAULT NULL COMMENT '模块名称',
                           `browser_name` varchar(1024) DEFAULT NULL COMMENT '浏览器名称',
                           `os_name` varchar(256) DEFAULT NULL COMMENT '操作系统名称',
                           `ip_addr` varchar(256) DEFAULT NULL COMMENT '请求ip',
                           `app_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '服务名称',
                           `class_name` varchar(1024) DEFAULT NULL COMMENT '类名',
                           `method_name` varchar(512) DEFAULT NULL COMMENT '方法',
                           `request_url` varchar(1024) DEFAULT NULL COMMENT '请求url',
                           `request_method` varchar(255) DEFAULT NULL COMMENT '请求方式，POST、GET',
                           `request_param` text COMMENT '请求参数',
                           `result_text` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '响应参数',
                           `status` tinyint(1) DEFAULT NULL COMMENT '接口状态（0成功 1失败）',
                           `error_text` text COMMENT '错误信息',
                           `take_up_time` varchar(64) DEFAULT NULL COMMENT '耗时',
                           `edit_table_id` bigint(20) DEFAULT NULL COMMENT '编辑的表主键，只有修改时才有值',
                           `edit_table_name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '编辑的表名称，只有修改时才有值',
                           `create_time` datetime DEFAULT NULL COMMENT '操作时间',
                           `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
                           `create_phone_number` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '创建人手机号',
                           `create_user_name` varchar(64) DEFAULT NULL COMMENT '创建人姓名',
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='系统操作日志';

SET FOREIGN_KEY_CHECKS = 1;

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
                               `status` int(11) DEFAULT NULL COMMENT '订单状态 0待付款 1待发货 2运送中 3待收货 4已送达待确认 5交易成功 99退款中 100退款成功',
                               `create_time` datetime DEFAULT NULL,
                               `payment_time` datetime DEFAULT NULL COMMENT '付款时间',
                               `delivery_time` datetime DEFAULT NULL COMMENT '发货时间',
                               `update_time` datetime DEFAULT NULL,
                               PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '订单表';

CREATE TABLE `here_user_address` (
                                     `id` int(11) NOT NULL AUTO_INCREMENT,
                                     `user_id` int(11) DEFAULT NULL,
                                     `consignee_name` varchar(50) DEFAULT NULL COMMENT '收货人',
                                     `consignee_phone` int(11) DEFAULT NULL,
                                     `province` varchar(10) DEFAULT NULL,
                                     `city` varchar(10) DEFAULT NULL,
                                     `area` varchar(10) DEFAULT NULL,
                                     `street` varchar(100) DEFAULT NULL,
                                     `address` varchar(255) DEFAULT NULL,
                                     `default` int(11) DEFAULT NULL COMMENT '1默认 0否',
                                     `create_time` datetime DEFAULT NULL,
                                     `update_time` datetime DEFAULT NULL,
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 comment '用户地址表';



