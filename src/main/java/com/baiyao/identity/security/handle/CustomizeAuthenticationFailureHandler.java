package com.baiyao.identity.security.handle;

import com.alibaba.fastjson.JSON;
import com.baiyao.identity.config.ErrorCode;
import com.baiyao.identity.security.enums.LoginCode;
import com.baiyao.identity.to.ErrorResultTO;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author baiyao
 * @date 2021/10/28 13:23
 * @description 登录失败处理逻辑
 */
@Component
public class CustomizeAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //返回json数据
        ErrorResultTO result;
        if (e instanceof AccountExpiredException) {
            //账号过期
            result = ErrorResultTO.createFailInstance(LoginCode.USER_ACCOUNT_EXPIRED.getCode().toString(), LoginCode.USER_ACCOUNT_EXPIRED.getMessage());
        } else if (e instanceof BadCredentialsException) {
            //密码错误
            result = ErrorResultTO.createFailInstance(LoginCode.USER_CREDENTIALS_ERROR.getCode().toString(), LoginCode.USER_CREDENTIALS_ERROR.getMessage());
        } else if (e instanceof CredentialsExpiredException) {
            //密码过期
            result = ErrorResultTO.createFailInstance(LoginCode.USER_CREDENTIALS_EXPIRED.getCode().toString(), LoginCode.USER_CREDENTIALS_EXPIRED.getMessage());
        } else if (e instanceof DisabledException) {
            //账号不可用
            result = ErrorResultTO.createFailInstance(LoginCode.USER_ACCOUNT_DISABLE.getCode().toString(), LoginCode.USER_ACCOUNT_DISABLE.getMessage());
        } else if (e instanceof LockedException) {
            //账号锁定
            result = ErrorResultTO.createFailInstance(LoginCode.USER_ACCOUNT_LOCKED.getCode().toString(), LoginCode.USER_ACCOUNT_LOCKED.getMessage());
        } else if (e instanceof InternalAuthenticationServiceException) {
            //用户不存在
            result = ErrorResultTO.createFailInstance(LoginCode.USER_ACCOUNT_NOT_EXIST.getCode().toString(), LoginCode.USER_ACCOUNT_NOT_EXIST.getMessage());
        } else {
            //其他错误
            result = ErrorResultTO.createFailInstance(ErrorCode.INTERNAL_ERROR.getErrorCode(), ErrorCode.INTERNAL_ERROR.getErrorInfo());
        }
        //处理编码方式，防止中文乱码的情况
        httpServletResponse.setContentType("text/json;charset=utf-8");
        //塞到HttpServletResponse中返回给前台
        httpServletResponse.getWriter().write(JSON.toJSONString(result));
    }
}
