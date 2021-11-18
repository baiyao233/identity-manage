package com.baiyao.identity.jwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户controller
 *
 * @author feng
 * @version 1.0
 * @date 2021/11/16 下午3:43
 */
@RestController
public class UserJWTController {


    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @PreAuthorize("hasAuthority('ROLE_USER')")
    @RequestMapping("/test1")
    public String test1() {
        return "test1";
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("/test2")
    public String test2() {
        return "test2";
    }
}
