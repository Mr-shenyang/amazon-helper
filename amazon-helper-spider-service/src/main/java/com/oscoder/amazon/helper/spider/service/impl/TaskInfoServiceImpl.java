package com.oscoder.amazon.helper.spider.service.impl;

import com.github.pagehelper.Page;
import com.oscoder.amazon.helper.common.dto.PageDTO;
import com.oscoder.amazon.helper.common.dto.ResponseDTO;
import com.oscoder.amazon.helper.common.request.PageRequest;
import com.oscoder.amazon.helper.spider.api.dto.TaskInfoDTO;
import com.oscoder.amazon.helper.spider.api.service.TaskInfoService;
import com.oscoder.amazon.helper.spider.service.data.mapper.TaskInfoMapper;
import com.oscoder.amazon.helper.spider.service.data.po.TaskInfoPO;
import com.oscoder.amazon.helper.spider.service.transform.TaskInfoTransform;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * @author
 * @create 2021-02-21 12:25 PM
 **/
@Slf4j
@Component
public class TaskInfoServiceImpl implements TaskInfoService {

	@Autowired
	private TaskInfoMapper taskInfoMapper;

	@Override
	public PageDTO<TaskInfoDTO> queryTaskPage(Integer userId, PageRequest pageRequest) {
		Page<TaskInfoPO> pageRest = taskInfoMapper.pageTaskInfoByUserId(userId, pageRequest.getPageNum(), pageRequest.getPageSize());
		PageDTO<TaskInfoDTO> pageDTO = new PageDTO<>();
		pageDTO.setPageNum(pageRest.getPageNum());
		pageDTO.setPageSize(pageRest.getPageSize());
		pageDTO.setTotal(((Long)pageRest.getTotal()).intValue());
		pageDTO.setRecords(pageRest.stream().map(TaskInfoTransform.INSTANCE::po2Dto).collect(Collectors.toList()));
		return pageDTO;
	}

	@Override
	public ResponseDTO<Integer> createTaskInfo(TaskInfoDTO createInfo) {
		try {
			TaskInfoPO taskInfoPO = TaskInfoTransform.INSTANCE.dtoToPo(createInfo);
			int insert = taskInfoMapper.insert(taskInfoPO);
			if (insert > 0){
				return ResponseDTO.success(taskInfoPO.getId());
			}
			return ResponseDTO.fail("创建失败");
		}catch (Exception e){
			log.error("create taskInfo error",e);
			return ResponseDTO.fail("创建异常,msg:" + e.getMessage());
		}
	}
}
