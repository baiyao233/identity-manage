package com.baiyao.identity.controller;

import com.baiyao.identity.dto.CreateUserDTO;
import com.baiyao.identity.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author baiyao
 * @date 2021/10/27 10:04
 * @description
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<?> addUser(@RequestBody @Valid CreateUserDTO user, HttpServletRequest request) {
        return userService.addUser(user, request);
    }

    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        return userService.getCurrentUser(request);
    }

}
