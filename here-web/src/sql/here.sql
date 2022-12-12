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

