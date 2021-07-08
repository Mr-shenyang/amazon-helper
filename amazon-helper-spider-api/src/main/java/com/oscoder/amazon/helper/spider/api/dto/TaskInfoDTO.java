package com.oscoder.amazon.helper.spider.api.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 * @create 2021-02-21 12:06 PM
 **/
@ToString
@Getter
@Setter
public class TaskInfoDTO implements Serializable {
	/**
	 * 任务Id
	 */
	private Integer id;
	/**
	 * 用户Id
	 */
	private Integer userId;
	/**
	 * 站点名称
	 * @see com.oscoder.amazon.helper.spider.api.enums.TaskStateEnum
	 */
	private String site;
	/**
	 * 关键字
	 */
	private String keyWord;
	/**
	 * 状态
	 */
	private Integer state;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 完成时间
	 */
	private Date completeTime;

}
