package com.here.modules.nameplate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.here.common.exception.BizException;
import com.here.modules.nameplate.mapper.NameplateAssistDO;
import com.here.modules.nameplate.mapper.NameplateAssistMapper;
import com.here.modules.nameplate.service.NameplateAssistService;
import com.here.modules.oauth.entity.HereUser;
import com.here.modules.oauth.mapper.HereUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 铭牌助力模块Service
 * @author Lzk
 */
@Service
public class NameplateAssistServiceImpl implements NameplateAssistService {

    @Autowired
    private NameplateAssistMapper nameplateAssistMapper;

    @Autowired
    private HereUserMapper hereUserMapper;

    @Override
    public List<String> getAssistUserUrls(String phone) {
        LambdaQueryWrapper<HereUser> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(HereUser::getPhone, phone);
        HereUser hereUser = hereUserMapper.selectOne(userWrapper);

        LambdaQueryWrapper<NameplateAssistDO> nameplateAssistWrapper = new LambdaQueryWrapper<>();
        nameplateAssistWrapper.eq(NameplateAssistDO::getApplyUserId, hereUser.getId());
        List<NameplateAssistDO> nameplateAssistDOs = nameplateAssistMapper.selectList(nameplateAssistWrapper);

        List<String> userUrls = new ArrayList<>();
        if (Objects.nonNull(nameplateAssistDOs)) {
            for (NameplateAssistDO nameplateAssistDO : nameplateAssistDOs) {
                userUrls.add(hereUserMapper.selectById(nameplateAssistDO.getAssistUserId()).getWecharHeadsUrl());
            }
        }
        return userUrls;
    }

    @Override
    public void assistUser(String userPhone, String assistUserPhone) {
        LambdaQueryWrapper<HereUser> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(HereUser::getPhone, userPhone);
        HereUser hereUser = hereUserMapper.selectOne(userWrapper);
        if (Objects.isNull(hereUser)) {
            throw new BizException("当前用户不存在");
        }

        userWrapper.clear();
        userWrapper.eq(HereUser::getPhone, assistUserPhone);
        HereUser assistHereUser = hereUserMapper.selectOne(userWrapper);
        if (Objects.isNull(assistHereUser)) {
            throw new BizException("助力用户不存在");
        }

        NameplateAssistDO nameplateAssistDO = new NameplateAssistDO();
        nameplateAssistDO.setApplyUserId(hereUser.getId());
        nameplateAssistDO.setAssistUserId(assistHereUser.getId());
        nameplateAssistMapper.insert(nameplateAssistDO);
    }
}
