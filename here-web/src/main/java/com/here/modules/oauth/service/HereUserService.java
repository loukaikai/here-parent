package com.here.modules.oauth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.here.common.api.ResultObject;
import com.here.domain.AdminUserDetails;
import com.here.modules.oauth.dto.UserInfoDTO;
import com.here.modules.oauth.entity.HereUser;
import com.here.modules.oauth.vo.InitVO;
import com.here.modules.oauth.vo.WriteInviCodeVO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author macro
 * @since 2022-12-09
 */
public interface HereUserService extends IService<HereUser> {

    Object getUser();

    ResultObject<HereUser> initUser(InitVO initVO);

    Boolean writeCode(WriteInviCodeVO writeInviCodeVO);

    /**
     * 根据微信号查询
     * **/
    HereUser getHereUserByWchatNo(String wechatNo);

    UserDetails loadUserByHereCod(String wechatNo);

    ResultObject<UserInfoDTO> weChartLogin(String openId);

}
