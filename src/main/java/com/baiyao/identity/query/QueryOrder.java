package com.baiyao.identity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author baiyao
 * @date 2021/10/11 20:27
 * @description
 */
@Data
@ApiModel(value = "查询排序", description = "查询排序")
public class QueryOrder {
    /**
     * 排序字段名称
     */
    @ApiModelProperty(value = "排序字段名称")
    private String fieldName;

    /**
     * 排序顺序：ASC,DESC
     */
    @ApiModelProperty(value = "排序顺序：ASC,DESC")
    private String sortOrder;
}
