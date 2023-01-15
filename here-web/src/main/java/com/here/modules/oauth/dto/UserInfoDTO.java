package com.here.modules.oauth.dto;

import com.here.modules.oauth.entity.HereUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName UserInfoDTO.java
 * @Description TODO
 * @createTime 2023年01月15日 15:50:00
 */
@Data
@ApiModel(value="登录返回类", description="登录返回类")
public class UserInfoDTO {

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "tokenHead")
    private String tokenHead;

    @ApiModelProperty(value = "用户信息")
    private HereUser user;

}
