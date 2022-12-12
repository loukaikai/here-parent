package com.here.modules.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author macro
 * @since 2022-12-09
 */
@Getter
@Setter
@TableName("here_shop")
@ApiModel(value = "HereShop对象", description = "店铺")
public class HereShop implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String shopName;

    private Integer status;

    private Date createTime;

    private Date updateTime;


}
