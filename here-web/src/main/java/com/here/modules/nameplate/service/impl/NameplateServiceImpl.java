package com.here.modules.nameplate.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.here.common.exception.BizException;
import com.here.modules.nameplate.mapper.NameplateAssistDO;
import com.here.modules.nameplate.mapper.NameplateAssistMapper;
import com.here.modules.nameplate.mapper.NameplateDO;
import com.here.modules.nameplate.mapper.NameplateMapper;
import com.here.modules.nameplate.service.NameplateService;
import com.here.modules.oauth.entity.HereUser;
import com.here.modules.oauth.mapper.HereUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 铭牌模块Service
 * @author Lzk
 */
@Service
public class NameplateServiceImpl implements NameplateService {

    @Autowired
    private NameplateMapper nameplateMapper;

    @Autowired
    private NameplateAssistMapper nameplateAssistMapper;

    @Autowired
    private HereUserMapper hereUserMapper;

    @Override
    public void addNameplate(String phone) {
        LambdaQueryWrapper<HereUser> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(HereUser::getPhone, phone);
        HereUser hereUser = hereUserMapper.selectOne(userWrapper);
        if (Objects.isNull(hereUser)) {
            throw new BizException("铭牌申请用户不存在");
        }

        Integer userId = hereUser.getId();
        LambdaQueryWrapper<NameplateAssistDO> nameplateAssistWrapper = new LambdaQueryWrapper<>();
        nameplateAssistWrapper.eq(NameplateAssistDO::getApplyUserId, userId);
        List<NameplateAssistDO> nameplateAssistDOs =
                Optional.ofNullable(nameplateAssistMapper.selectList(nameplateAssistWrapper)).orElse(Collections.emptyList());
        if (nameplateAssistDOs.size() < 3) {
            throw new BizException("助力人数不足，用户未满足铭牌领取条件");
        }

        LambdaQueryWrapper<NameplateDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(NameplateDO::getUserId, userId);
        NameplateDO nameplateDO = nameplateMapper.selectOne(wrapper);
        if (Objects.nonNull(nameplateDO)) {
            throw new BizException("用户已拥有铭牌");
        } else {
            nameplateDO = new NameplateDO();
            nameplateDO.setUserId(userId);
            StringBuilder builder = new StringBuilder("Here000000000");
            builder.replace(builder.length() - String.valueOf(userId).length(),
                    builder.length(), String.valueOf(userId));
            nameplateDO.setNameplateNumber(builder.toString());
            nameplateMapper.insert(nameplateDO);
        }
    }

    @Override
    public List<String> getNameplateList() {
        LambdaQueryWrapper<NameplateDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(NameplateDO::getCreateTime).last("limit 10");
        List<NameplateDO> nameplateDOs = nameplateMapper.selectList(wrapper);
        List<String> userNames = new ArrayList<>();
        for (NameplateDO nameplateDO : nameplateDOs) {
            HereUser hereUser = hereUserMapper.selectById(nameplateDO.getUserId());
            if (Objects.nonNull(hereUser)) {
                userNames.add(hereUser.getWecharName());
            }
        }
        return userNames;
    }

    @Override
    public String getNameplateNumber(String phone) {
        LambdaQueryWrapper<HereUser> userWrapper = new LambdaQueryWrapper<>();
        userWrapper.eq(HereUser::getPhone, phone);
        HereUser hereUser = hereUserMapper.selectOne(userWrapper);
        if (Objects.isNull(hereUser)) {
            throw new BizException("用户不存在");
        }

        LambdaQueryWrapper<NameplateDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(NameplateDO::getUserId, hereUser.getId());
        NameplateDO nameplateDO = nameplateMapper.selectOne(wrapper);
        if (Objects.isNull(nameplateDO)) {
            throw new BizException("用户尚未获得铭牌");
        } else {
            return nameplateDO.getNameplateNumber();
        }
    }
}
