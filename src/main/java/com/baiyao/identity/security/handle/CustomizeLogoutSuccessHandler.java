package com.baiyao.identity.security.handle;

import com.alibaba.fastjson.JSON;
import com.baiyao.identity.to.SuccessResultTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author baiyao
 * @date 2021/10/28 13:29
 * @description 登出成功处理逻辑
 */
@Component
public class CustomizeLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(SuccessResultTO.createSuccessInstance("登出成功！")));
    }
}
