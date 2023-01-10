package com.here.modules.oauth.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author macro
 * @since 2022-12-09
 */
@Getter
@Setter
@TableName("here_user")
@ApiModel(value = "HereUser对象", description = "用户表")
public class HereUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String hereCode;

    @ApiModelProperty("微信绑定的手机号")
    private String phone;

    @ApiModelProperty("微信名")
    private String wecharName;

    @ApiModelProperty("微信号")
    private String wechatNo;

    @ApiModelProperty("头像url")
    private String wecharHeadsUrl;

    @ApiModelProperty("邀请人id")
    private Integer invitationId;

    @ApiModelProperty("邀请码")
    private String invitationCode;

    @ApiModelProperty("我的邀请码")
    private String myCode;

    @ApiModelProperty("铭牌状态 0未点亮 1点亮")
    private Integer nameplateFlag;

    @ApiModelProperty("  停用 启用")
    private Integer userStatus;

    @ApiModelProperty("小程序unionid")
    private String unionId;

    @ApiModelProperty("小程序openid")
    private String openId;

    // 没有区号的手机号
    @ApiModelProperty("没有区号的手机号")
    private String purePhoneNumber;

    @ApiModelProperty("区号")
    private String countryCode;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;


}
