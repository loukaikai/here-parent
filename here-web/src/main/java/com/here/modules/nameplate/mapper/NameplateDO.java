package com.here.modules.nameplate.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

/**
 * 铭牌表数据库对象
 * @author Lzk
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Component
@TableName("here_nameplate")
public class NameplateDO extends BaseDO{

    private static final long serialVersionUID = 8011680901042983170L;

    private Integer userId;

    private String nameplateNumber;

}
