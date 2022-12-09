package com.here.modules.ums.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWTUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.here.common.exception.Asserts;
import com.here.domain.AdminUserDetails;
import com.here.modules.ums.dto.UmsAdminParam;
import com.here.modules.ums.dto.UpdateAdminPasswordParam;
import com.here.modules.ums.model.*;
import com.here.modules.ums.mapper.HereUserMapper;
import com.here.modules.ums.service.HereUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.here.modules.ums.service.UmsAdminCacheService;
import com.here.security.util.JwtTokenUtil;
import com.here.security.util.SpringUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(HereUserServiceImpl.class);

}
