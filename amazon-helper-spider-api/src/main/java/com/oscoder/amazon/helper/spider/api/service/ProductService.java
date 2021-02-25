package com.oscoder.amazon.helper.spider.api.service;

import com.oscoder.amazon.helper.spider.api.dto.PageDTO;
import com.oscoder.amazon.helper.spider.api.dto.ProductDTO;
import com.oscoder.amazon.helper.spider.api.dto.TaskInfoDTO;

/**
 * @author
 * @create 2021-02-21 11:55 AM
 **/
public interface ProductService {
	/**
	 * 根据UserId获取任务列表
	 * @param userId
	 * @return
	 */
	//TODO
	PageDTO<TaskInfoDTO> queryTaskPage(Integer userId);
	
	/**
	 * 查询批次内商品数据
	 * @param taskId
	 * @return
	 */
	//TODO
	PageDTO<ProductDTO> queryProductPage(Integer taskId);
}
