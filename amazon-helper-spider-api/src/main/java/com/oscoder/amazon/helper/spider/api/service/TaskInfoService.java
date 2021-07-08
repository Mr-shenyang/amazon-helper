package com.oscoder.amazon.helper.spider.api.service;

import com.oscoder.amazon.helper.common.dto.PageDTO;
import com.oscoder.amazon.helper.common.dto.ResponseDTO;
import com.oscoder.amazon.helper.common.request.PageRequest;
import com.oscoder.amazon.helper.spider.api.dto.ProductDTO;
import com.oscoder.amazon.helper.spider.api.dto.TaskInfoDTO;

/**
 * @author
 * @create 2021-02-21 11:55 AM
 **/
public interface TaskInfoService {
	/**
	 * 根据UserId获取任务列表
	 * @param userId
	 * @return
	 */
	PageDTO<TaskInfoDTO> queryTaskPage(Integer userId, PageRequest pageRequest);

	/**
	 * 创建任务
	 * @param createInfo
	 * @return
	 */
	ResponseDTO<Integer> createTaskInfo(TaskInfoDTO createInfo);

}
