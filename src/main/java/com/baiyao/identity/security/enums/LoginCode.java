package com.baiyao.identity.security.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author baiyao
 * @date 2021/10/28 10:36
 * @description
 */
@Getter
@AllArgsConstructor
public enum LoginCode {

    /* 用户错误 */
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "账号下线"),
    /* 业务错误 */
    NO_PERMISSION(3001, "没有权限");;


    private final Integer code;

    private final String message;


}
