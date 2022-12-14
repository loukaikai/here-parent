package com.here.modules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.here.modules.entity.HereUser;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2022-12-09
 */
public interface HereUserMapper extends BaseMapper<HereUser> {

    @Update("{update here_user set invitation_code=#{code} where here_code=#{hereCode}")
    Integer updateInvitationCode(String code,String hereCode);
}
