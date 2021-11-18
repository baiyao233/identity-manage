package com.baiyao.identity.jwt.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * @author feng
 * @version 1.0
 * @date 2021/11/16 下午3:38
 */
@Component("MyAuthenticationProvider")
public class MyAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 自定义验证方式
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        UserDetails user = userDetailsService.loadUserByUsername(username);
        if(user.getPassword().equals(password)) {
            return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> arg0) {
        return true;
    }
}
