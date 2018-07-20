package com.gongshw.scp.base.server.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.experimental.UtilityClass;

/**
 * Spring Security Utility Class.
 *
 * @author gongshiwei@baidu.com
 */
@UtilityClass
public class SecurityHelper {

    public static String getUsername() {
        return getAuthentication().getName();
    }

    public static Set<String> getRoles() {
        Authentication authentication = getAuthentication();
        @SuppressWarnings("unchecked")
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
    }

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
