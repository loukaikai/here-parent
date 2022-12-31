package com.here.modules.oauth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.here.modules.oauth.entity.HereUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2022-12-09
 */
@Mapper
public interface HereUserMapper extends BaseMapper<HereUser> {

    @Update("{update here_user set invitation_id=#{invitationId}, invitation_code=#{code} where id=#{userId}")
    Integer updateInvitationCode(int invitation_id, String code,int userId);
}
