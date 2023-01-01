package com.here.modules.badge.mapper;

import com.baomidou.mybatisplus.annotation.TableName;
import com.here.modules.nameplate.mapper.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

/**
 * 徽章表数据库对象
 * @author Lzk
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Component
@TableName("here_badge")
public class BadgeDO extends BaseDO {

    private static final long serialVersionUID = -6718784211448346583L;

    /**
     * 徽章名称
     */
    private String name;

    /**
     * 徽章所属用户ID
     */
    private Integer userId;

    /**
     * 徽章url
     */
    private String badgeUrl;

}
