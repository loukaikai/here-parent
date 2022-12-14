package com.here.modules.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.here.modules.entity.HereUser;
import com.here.modules.mapper.HereUserMapper;
import com.here.modules.service.HereUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.here.modules.vo.HereUserVo;
import com.here.security.SecurityUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author macro
 * @since 2022-12-09
 */
@Service
@Transactional
public class HereUserServiceImpl extends ServiceImpl<HereUserMapper, HereUser> implements HereUserService {

    @Override
    public Object getUser() {
        HereUserVo userVo = new HereUserVo();
        HereUser hereUser = SecurityUtil.getCurrentUser();
        //暂时没定头部有哪些数据，先用某个唯一值查db
        HereUser user = getOne(new LambdaQueryWrapper<HereUser>().eq(HereUser::getHereCode, hereUser.getHereCode()));
        BeanUtils.copyProperties(user,userVo);
        //todo 商品订单还没确认
        return null;
    }

    @Override
    public Boolean writeCode(String code) {
        HereUser hereUser = SecurityUtil.getCurrentUser();
        Integer changeCt = baseMapper.updateInvitationCode(code, hereUser.getHereCode());
        if (changeCt<=1){
            return true;
        }
        return false;
    }
}

