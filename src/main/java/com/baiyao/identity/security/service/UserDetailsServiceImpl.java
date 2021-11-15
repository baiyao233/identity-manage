package com.baiyao.identity.security.service;

import com.baiyao.identity.entity.SysPermissionEntity;
import com.baiyao.identity.entity.SysUserEntity;
import com.baiyao.identity.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


/**
 * @author baiyao
 * @date 2021/10/27 13:44
 * @description
 */
@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    private UserMapper userMapper;

    /**
     * 这里根据传进来的用户账号进行用户信息的构建
     * 通常的做法是
     * 1 根据username查询数据库对应的用户信息
     * 2 根据用户信息查询出用户权限信息  例如菜单添加权限  sys:menu:add
     * 3 根据用户账号，密码，权限构建对应的UserDetails对象返回
     * 这里实际上是没有进行用户认证功能的，真正的验证是在UsernamePasswordAuthenticationFilter对象当中
     * UsernamePasswordAuthenticationFilter对象会自动根据前端传入的账号信息和UserDetails对象对比进行账号的验证
     * 通常情况下，已经满足常见的使用常见，不过如果有特殊需求，需要使用自己实现的具体认证方式，可以继承UsernamePasswordAuthenticationFilter对象
     * 重写attemptAuthentication 方法和successfulAuthentication方法
     * 最后在WebSecurityConfiguration里面添加自己的过滤器即可
     *
     * @param username 用户账号
     * @return UserInfo
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new RuntimeException("用户不能为空");
        }
        LambdaQueryWrapper<SysUserEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserEntity::getAccount, username);
        SysUserEntity user = userMapper.selectOne(queryWrapper);
        if (null == user) {
            log.info(String.format("找不到用户%s", username));
            return null;
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        List<SysPermissionEntity> sysPermissionEntities = userMapper.getPermissionByUserId(user.getId());
        sysPermissionEntities.stream().filter(Objects::nonNull).forEach(data -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(data.getPermissionCode());
            grantedAuthorities.add(grantedAuthority);
        });
        return new User(user.getAccount(),
                user.getPassword(),
                user.getEnabled(),
                user.getNotExpired(),
                user.getCredentialsNotExpired(),
                user.getAccountNotLocked(),
                grantedAuthorities);
    }
}
