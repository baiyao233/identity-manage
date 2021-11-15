package com.baiyao.identity.security.filter;

import com.alibaba.fastjson.JSON;
import com.baiyao.identity.security.enums.LoginCode;
import com.baiyao.identity.to.SuccessResultTO;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author baiyao
 * @date 2021/10/28 13:44
 * @description 会话信息过期策略
 */
@Component
public class CustomizeSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent sessionInformationExpiredEvent) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = sessionInformationExpiredEvent.getResponse();
        httpServletResponse.setContentType("text/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(SuccessResultTO.createSuccessInstance(LoginCode.USER_ACCOUNT_USE_BY_OTHERS)));
    }
}
