//package com.baiyao.identity.security.filter;
//
//
//import com.baiyao.identity.entity.SysPermissionEntity;
//import com.baiyao.identity.mapper.UserMapper;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.access.SecurityConfig;
//import org.springframework.security.web.FilterInvocation;
//import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
//import org.springframework.stereotype.Component;
//import org.springframework.util.AntPathMatcher;
//
//import javax.annotation.Resource;
//import java.util.Collection;
//import java.util.List;
//
///**
// * @author baiyao
// * @date 2021/10/28 14:12
// * @description
// */
//@Component
//public class CustomizeFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
//    AntPathMatcher antPathMatcher = new AntPathMatcher();
//    @Resource
//    private UserMapper userMapper;
//
//    @Override
//    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
//        // 获取请求地址
//        String requestUrl = ((FilterInvocation) o).getRequestUrl();
//        // 查询具体某个接口的权限
//        List<SysPermissionEntity> permissionList = userMapper.getPermissionByPath(requestUrl);
//        if (permissionList == null || permissionList.size() == 0) {
//            // 请求路径没有配置权限，表明该请求接口可以任意访问
//            return null;
//        }
//        String[] attributes = new String[permissionList.size()];
//        for (int i = 0; i < permissionList.size(); i++) {
//            attributes[i] = permissionList.get(i).getPermissionCode();
//        }
//        return SecurityConfig.createList(attributes);
//    }
//
//    @Override
//    public Collection<ConfigAttribute> getAllConfigAttributes() {
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> aClass) {
//        return true;
//    }
//
//}
