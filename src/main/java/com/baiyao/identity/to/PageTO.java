package com.baiyao.identity.to;

import lombok.Data;

import java.util.List;

/**
 * @author baiyao
 * @date 2021/10/11 20:33
 * @description
 */
@Data
public class PageTO {
    /**
     * ErrorCodeTotalCount: 总数量
     **/
    private Long totalCount;

    /**
     * ErrorCodepageCount: 总页数
     **/
    private Long pageCount;

    /**
     * ErrorCodepageNum: 页码
     **/
    private Long pageNum;

    /**
     * ErrorCodepageSize: 每页显示数量
     **/
    private Long pageSize;

    /**
     * ErrorCodeContent: 显示内容
     **/
    private Object content;

    public PageTO(Long count, Long pageCount, Long pageNum, Long pageSize, Object content) {
        this.totalCount = count;
        this.pageCount = pageCount;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.content = content;
    }

    public PageTO(long total, List<?> list, long pageNum, long pageSize) {
        this.totalCount = total;
        this.content = list;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.pageCount = total % pageSize > 0 ? total / pageSize + 1 : total / pageSize;
    }
}
