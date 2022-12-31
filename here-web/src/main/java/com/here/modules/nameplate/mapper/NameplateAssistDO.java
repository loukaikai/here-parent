package com.here.modules.nameplate.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

/**
 * 铭牌助力表数据库对象
 * @author Lzk
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Component
@TableName("here_nameplate_assist")
public class NameplateAssistDO extends BaseDO{

    private static final long serialVersionUID = 4973716831075688025L;

    /**
     * 申请铭牌用户ID
     */
    private Integer applyUserId;

    /**
     * 助力用户ID
     */
    private Integer assistUserId;

}
