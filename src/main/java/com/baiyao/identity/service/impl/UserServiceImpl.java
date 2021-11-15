package com.baiyao.identity.service.impl;

import com.baiyao.identity.config.ErrorCode;
import com.baiyao.identity.dto.CreateUserDTO;
import com.baiyao.identity.entity.SysUserEntity;
import com.baiyao.identity.mapper.UserMapper;
import com.baiyao.identity.security.enums.LoginCode;
import com.baiyao.identity.service.UserService;
import com.baiyao.identity.to.ErrorResultTO;
import com.baiyao.identity.to.SuccessResultTO;
import com.baiyao.identity.vo.UserInfoVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @author baiyao
 * @date 2021/10/27 9:47
 * @description
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> addUser(CreateUserDTO user, HttpServletRequest request) {
        // 校验账号是否重复
        if (checkValid(SysUserEntity::getAccount, user.getAccount())) {
            return ResponseEntity.status(ErrorCode.DUPLICATE_PARAMETER.getResponseStatus())
                    .body(ErrorResultTO.createFailInstance(ErrorCode.DUPLICATE_PARAMETER.getErrorCode(),
                            ErrorCode.DUPLICATE_PARAMETER.getErrorInfo("account")));
        }
        // 校验用户名是否重复
        if (checkValid(SysUserEntity::getUsername, user.getAccount())) {
            return ResponseEntity.status(ErrorCode.DUPLICATE_PARAMETER.getResponseStatus())
                    .body(ErrorResultTO.createFailInstance(ErrorCode.DUPLICATE_PARAMETER.getErrorCode(),
                            ErrorCode.DUPLICATE_PARAMETER.getErrorInfo("username")));
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // 构建user实体类
        SysUserEntity userEntity = SysUserEntity.builder()
                .account(user.getAccount())
                .username(user.getUsername())
                .password(user.getPassword())
                .createTime(LocalDateTime.now())
                .updateTime(LocalDateTime.now())
                .createUser(Optional.ofNullable(getCurrentUserInfo(request)).orElse(new UserInfoVO()).getId())
                .updateUser(Optional.ofNullable(getCurrentUserInfo(request)).orElse(new UserInfoVO()).getId())
                .build();
        userMapper.insert(userEntity);
        return ResponseEntity.ok(SuccessResultTO.createSuccessInstance("新增用户成功！"));
    }

    @Override
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        UserInfoVO userInfo = getCurrentUserInfo(request);
        if (null == userInfo) {
            return ResponseEntity.status(ErrorCode.NOT_EXIST.getResponseStatus())
                    .body(ErrorResultTO.createFailInstance(LoginCode.USER_NOT_LOGIN.getCode().toString(),
                            LoginCode.USER_NOT_LOGIN.getMessage()));
        }
        return ResponseEntity.ok(userInfo);
    }

    /**
     * 获取当前登录用户信息
     *
     * @param request http流
     * @return UserInfoVO
     */
    private UserInfoVO getCurrentUserInfo(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (null != principal) {
            String name = principal.getName();
            LambdaQueryWrapper<SysUserEntity> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SysUserEntity::getAccount, name);
            SysUserEntity user = userMapper.selectOne(queryWrapper);
            return new UserInfoVO(user);
        }
        return null;
    }

    /**
     * 校验
     *
     * @param consumer 要校验的字段
     * @param val      要校验的值
     * @return boolean
     */
    private boolean checkValid(SFunction<SysUserEntity, ?> consumer, Object val) {
        LambdaQueryWrapper<SysUserEntity> query = new LambdaQueryWrapper<>();
        query.eq(consumer, val);
        Long checkValid = userMapper.selectCount(query);
        return checkValid > 0;
    }
}
