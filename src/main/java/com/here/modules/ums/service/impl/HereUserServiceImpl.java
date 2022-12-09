package com.here.modules.ums.service.impl;

import com.here.modules.ums.model.HereUser;
import com.here.modules.ums.mapper.HereUserMapper;
import com.here.modules.ums.service.HereUserService;
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
