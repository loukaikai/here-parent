package com.here.modules.service.impl;

import com.here.modules.entity.HereUser;
import com.here.modules.mapper.HereUserMapper;
import com.here.modules.service.HereUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author macro
 * @since 2022-12-09
 */
@Service
public class HereUserServiceImpl extends ServiceImpl<HereUserMapper, HereUser> implements HereUserService {

}

