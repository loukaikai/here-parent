package com.here.common.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *     系统操作日志
 * </p>
 * @author loukaikai
 * @version 1.0.0
 * @ClassName SysLog.java
 * @Description TODO
 * @createTime 2022年12月12日 22:56:00
 */
@Data
@TableName("sys_log")
@ApiModel(value = "SysLogDO对象", description = "系统操作日志")
public class SysLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "模块名称")
    private String moduleName;

    @ApiModelProperty(value = "浏览器名称")
    private String browserName;

    @ApiModelProperty(value = "操作系统名称")
    private String osName;

    @ApiModelProperty(value = "请求ip")
    private String ipAddr;

    @ApiModelProperty(value = "服务名称")
    private String appName;

    @ApiModelProperty(value = "类名")
    private String className;

    @ApiModelProperty(value = "方法")
    private String methodName;

    @ApiModelProperty(value = "请求url")
    private String requestUrl;

    @ApiModelProperty(value = "请求方式，POST、GET")
    private String requestMethod;

    @ApiModelProperty(value = "请求参数")
    private String requestParam;

    @ApiModelProperty(value = "响应参数")
    private String resultText;

    @ApiModelProperty(value = "接口状态（0成功 1失败）")
    private Byte status;

    @ApiModelProperty(value = "错误信息")
    private String errorText;

    @ApiModelProperty(value = "耗时")
    private String takeUpTime;

    @ApiModelProperty(value = "编辑的表主键，只有修改时才有值")
    private Long editTableId;

    @ApiModelProperty(value = "编辑的表名称，只有修改时才有值")
    private String editTableName;

    @ApiModelProperty(value = "操作时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人id")
    private Long createUserId;

    @ApiModelProperty(value = "创建人手机号")
    private String createPhoneNumber;

    @ApiModelProperty(value = "创建人姓名")
    private String createUserName;

}
