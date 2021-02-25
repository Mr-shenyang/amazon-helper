package com.oscoder.amazon.helper.spider.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author
 * @create 2021-02-21 12:02 PM
 **/
@ToString
@Setter
@Getter
public class PageDTO<T> {
	private Integer pageNo;
	private Integer pageSize;
	private Integer total;
	List<T> records;
}
