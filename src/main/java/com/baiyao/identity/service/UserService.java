package com.baiyao.identity.service;


import com.baiyao.identity.dto.CreateUserDTO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * @author baiyao
 * @date 2021/10/27 9:46
 * @description
 */
public interface UserService {

    /**
     * 新增用户
     *
     * @param user 用户
     * @param request http流
     * @return ResponseEntity<?>
     */
    ResponseEntity<?> addUser(CreateUserDTO user, HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request http流
     * @return ResponseEntity<?>
     */
    ResponseEntity<?> getCurrentUser(HttpServletRequest request);
}
