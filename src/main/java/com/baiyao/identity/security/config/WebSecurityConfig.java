package com.baiyao.identity.security.config;

import com.baiyao.identity.security.filter.*;
import com.baiyao.identity.security.handle.CustomizeAuthenticationFailureHandler;
import com.baiyao.identity.security.handle.CustomizeAuthenticationSuccessHandler;
import com.baiyao.identity.security.handle.CustomizeLogoutSuccessHandler;
import com.baiyao.identity.security.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;


import javax.annotation.Resource;


/**
 * @author baiyao
 * @date 2021/10/27 13:30
 * @description
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    private CustomizeAuthenticationEntryPoint authenticationEntryPoint;
    @Resource
    private CustomizeAuthenticationSuccessHandler authenticationSuccessHandler;
    @Resource
    private CustomizeAuthenticationFailureHandler authenticationFailureHandler;
    @Resource
    private CustomizeLogoutSuccessHandler logoutSuccessHandler;
    @Resource
    private CustomizeSessionInformationExpiredStrategy sessionInformationExpiredStrategy;
    @Resource
    private CustomizeAccessDecisionManager accessDecisionManager;
    @Resource
    private CustomizeFilterInvocationSecurityMetadataSource securityMetadataSource;
    @Resource
    private CustomizeAbstractSecurityInterceptor securityInterceptor;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 配置认证方式等
        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable();
        // http相关的配置，包括登入登出、异常处理、会话管理等
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        // 访问决策管理器
                        o.setAccessDecisionManager(accessDecisionManager);
                        // 安全元数据源
                        o.setSecurityMetadataSource(securityMetadataSource);
                        return o;
                    }
                })
                // 登出
                .and().logout()
                .permitAll()
                // 登出成功处理逻辑
                .logoutSuccessHandler(logoutSuccessHandler)
                // 登出之后删除cookie
                .deleteCookies("JSESSIONID")
                // 登录
                .and().formLogin()
                .permitAll()
                // 登录成功处理逻辑
                .successHandler(authenticationSuccessHandler)
                // 登录失败处理逻辑
                .failureHandler(authenticationFailureHandler)
                // 异常处理(权限拒绝、登录失效等)
                .and().exceptionHandling()
                // 匿名用户访问无权限资源时的异常处理
                .authenticationEntryPoint(authenticationEntryPoint)
                // 会话管理
                .and().sessionManagement()
                // 同一账号同时登录最大用户数
                .maximumSessions(1)
                // 会话失效(账号被挤下线)处理逻辑
                .expiredSessionStrategy(sessionInformationExpiredStrategy);
        http.addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        //获取用户账号密码及权限信息
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        // 设置默认的加密方式（强hash方式加密）
        return new BCryptPasswordEncoder();
    }

}
