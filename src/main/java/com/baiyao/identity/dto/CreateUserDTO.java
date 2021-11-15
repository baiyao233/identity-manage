package com.baiyao.identity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author baiyao
 * @date 2021/10/28 14:51
 * @description
 */
@Data
public class CreateUserDTO {
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @NotEmpty(message = "账号不能为空")
    private String account;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @NotNull(message = "用户名不能为空")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotNull(message = "密码不能为空")
    private String password;
}
