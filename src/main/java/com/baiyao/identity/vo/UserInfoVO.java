package com.baiyao.identity.vo;

import com.baiyao.identity.entity.SysUserEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @author baiyao
 * @date 2021/10/28 15:49
 * @description
 */
@Data
public class UserInfoVO {
    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Integer id;
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    private String account;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;
    /**
     * 上次登录时间
     */
    @ApiModelProperty(value = "上次登录时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastLoginTime;

    /**
     * 账号是否可用。默认为1（可用）
     */
    @ApiModelProperty(value = "账号是否可用。默认为1（可用）")
    private Boolean enabled;

    /**
     * 是否过期。默认为1（没有过期）
     */
    @ApiModelProperty(value = "是否过期。默认为1（没有过期）")
    private Boolean notExpired;

    /**
     * 账号是否锁定。默认为1（没有锁定）
     */
    @ApiModelProperty(value = "账号是否锁定。默认为1（没有锁定）")
    private Boolean accountNotLocked;

    /**
     * 证书（密码）是否过期。默认为1（没有过期）
     */
    @ApiModelProperty(value = "证书（密码）是否过期。默认为1（没有过期）")
    private Boolean credentialsNotExpired;

    public UserInfoVO() {

    }

    public UserInfoVO(SysUserEntity userEntity) {
        this.id = userEntity.getId();
        this.account = userEntity.getAccount();
        this.username = userEntity.getUsername();
        this.lastLoginTime = userEntity.getLastLoginTime();
        this.enabled = userEntity.getEnabled();
        this.notExpired = userEntity.getNotExpired();
        this.accountNotLocked = userEntity.getAccountNotLocked();
        this.credentialsNotExpired = userEntity.getCredentialsNotExpired();
    }
}
