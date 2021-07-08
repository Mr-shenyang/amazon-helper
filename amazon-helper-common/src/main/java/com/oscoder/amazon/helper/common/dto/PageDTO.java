package com.oscoder.amazon.helper.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class PageDTO<T extends Serializable>{
    /**
     * 每页大小
     */
    private Integer pageSize;
    /**
     * 页码
     */
    private Integer pageNum;
    /**
     * 总数
     */
    private Integer total;
    /**
     * 记录
     */
    private List<T> records;
}
