package com.oscoder.amazon.helper.spider.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author
 * @create 2021-02-21 11:56 AM
 **/
@Getter
@Setter
@ToString
public class ProductDTO implements Serializable {
	/**
	 * 商品唯一标识符
	 */
	private String asin;
	/**
	 * 评论个数
	 */
	private Integer commentNo;
	/**
	 * 品牌名称
	 */
	private String brandName;
	/**
	 * 星级
	 */
	private String star;
	/**
	 * 价格
	 */
	private String price;
}
