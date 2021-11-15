package com.baiyao.identity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author baiyao
 * @date 2021/10/18 14:45
 * @description
 */
@Data
@TableName("sys_user")
@Builder
public class SysUserEntity {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 用户密码
     */
    @ApiModelProperty(value = "用户密码")
    private String password;

    /**
     * 上一次登录时间
     */
    @TableField("last_login_time")
    @ApiModelProperty(value = "上一次登录时间")
    private LocalDateTime lastLoginTime;

    /**
     * 账号是否可用。默认为1（可用）
     */
    @ApiModelProperty(value = "账号是否可用。默认为1（可用）")
    private Boolean enabled;

    /**
     * 是否过期。默认为1（没有过期）
     */
    @TableField("not_expired")
    @ApiModelProperty(value = "是否过期。默认为1（没有过期）")
    private Boolean notExpired;

    /**
     * 账号是否锁定。默认为1（没有锁定）
     */
    @TableField("account_not_locked")
    @ApiModelProperty(value = "账号是否锁定。默认为1（没有锁定）")
    private Boolean accountNotLocked;

    /**
     * 证书（密码）是否过期。默认为1（没有过期）
     */
    @TableField("credentials_not_expired")
    @ApiModelProperty(value = "证书（密码）是否过期。默认为1（没有过期）")
    private Boolean credentialsNotExpired;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("update_time")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @TableField("create_user")
    @ApiModelProperty(value = "创建人")
    private Integer createUser;

    /**
     * 修改人
     */
    @TableField("update_user")
    @ApiModelProperty(value = "修改人")
    private Integer updateUser;
}
