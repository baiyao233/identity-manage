package com.baiyao.identity.security.filter;

import com.alibaba.fastjson.JSON;
import com.baiyao.identity.security.enums.LoginCode;
import com.baiyao.identity.to.ErrorResultTO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author baiyao
 * @date 2021/10/28 12:00
 * @description 匿名用户访问无权限资源时的异常
 */
@Component
public class CustomizeAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter()
                .write(JSON.toJSONString(ErrorResultTO.createFailInstance(LoginCode.USER_NOT_LOGIN.getCode().toString(),
                        LoginCode.USER_NOT_LOGIN.getMessage())));
    }
}
