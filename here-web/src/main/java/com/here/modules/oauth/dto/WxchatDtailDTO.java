package com.here.modules.oauth.dto;

import com.here.modules.oauth.entity.HereUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author loukaikai
 * @version 1.0.0
 * @ClassName WxchatDtailDTO.java
 * @Description TODO
 * @createTime 2022年12月20日 19:04:00
 */
public class WxchatDtailDTO implements UserDetails {

    private HereUser hereUser;

    public WxchatDtailDTO(HereUser hereUser) {
        this.hereUser = hereUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的角色
        return null;
    }

    @Override
    public String getPassword() {
        return "password";
    }

    @Override
    public String getUsername() {
        return hereUser.getOpenId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return hereUser.getUserStatus().equals(0);
    }
}

