package com.baiyao.identity.security.handle;

import com.alibaba.fastjson.JSON;
import com.baiyao.identity.entity.SysUserEntity;
import com.baiyao.identity.mapper.UserMapper;
import com.baiyao.identity.to.SuccessResultTO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author baiyao
 * @date 2021/10/28 13:17
 * @description 登录成功处理逻辑
 */
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    private UserMapper userMapper;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //更新用户表上次登录时间、更新人、更新时间等字段
        User userDetails = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        LambdaQueryWrapper<SysUserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserEntity::getAccount, userDetails.getUsername());
        SysUserEntity sysUser = userMapper.selectOne(queryWrapper);
        sysUser.setLastLoginTime(LocalDateTime.now());
        sysUser.setUpdateUser(sysUser.getId());
        userMapper.updateById(sysUser);
        //此处还可以进行一些处理，比如登录成功之后可能需要返回给前台当前用户有哪些菜单权限，
        //进而前台动态的控制菜单的显示等，具体根据自己的业务需求进行扩展
        //处理编码方式，防止中文乱码的情况
        httpServletResponse.setContentType("text/json;charset=utf-8");
        //塞到HttpServletResponse中返回给前台
        httpServletResponse.getWriter().write(JSON.toJSONString(SuccessResultTO.createSuccessInstance("登录成功！")));
    }
}
