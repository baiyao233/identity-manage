package com.baiyao.identity.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author baiyao
 * @date 2021/10/18 14:45
 * @description
 */
@Data
@TableName("sys_permission")
public class SysPermissionEntity implements Serializable {
    private static final long serialVersionUID = -94326922147981791L;
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "id")
    private Integer id;
    /**
     * 权限code
     */
    @TableField("permission_code")
    @ApiModelProperty(value = "权限code")
    private String permissionCode;
    /**
     * 权限名
     */
    @TableField("permission_name")
    @ApiModelProperty(value = "权限名")
    private String permissionName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

}