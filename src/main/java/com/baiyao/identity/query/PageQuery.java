package com.baiyao.identity.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author baiyao
 * @date 2021/10/11 20:26
 * @description
 */
@Data
@ApiModel(value = "分页查询", description = "分页查询")
public class PageQuery {
    @ApiModelProperty(value = "页数")
    private Integer page;

    @ApiModelProperty(value = "每页显示大小")
    private Integer limit;

    /**
     * 分页查询偏移量
     */
    private Integer offset;

    @ApiModelProperty(value = "排序参数集合")
    List<QueryOrder> queryOrderList;

    public Integer getOffset() {
        return page == null || limit == null ? 0 : (page - 1) * limit;
    }
}

