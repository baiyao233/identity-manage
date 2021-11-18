package com.baiyao.identity.jwt.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 自定义加密工具
 * @author feng
 * @version 1.0
 * @date 2021/11/16 下午3:42
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(rawPassword.toString());
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return false;
    }
}
