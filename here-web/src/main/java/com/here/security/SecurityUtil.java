package com.here.security;

import com.here.modules.entity.HereUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtil {
    public static HereUser getCurrentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof  HereUser){
            return (HereUser)principal;
        }
        return null;
    }
}
