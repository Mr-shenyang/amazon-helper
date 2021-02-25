package com.oscoder.amazon.helper.spider.service.impl;

import com.oscoder.amazon.helper.spider.api.dto.PageDTO;
import com.oscoder.amazon.helper.spider.api.dto.ProductDTO;
import com.oscoder.amazon.helper.spider.api.dto.TaskInfoDTO;
import com.oscoder.amazon.helper.spider.api.service.ProductService;
import org.springframework.stereotype.Component;

/**
 * @author
 * @create 2021-02-21 12:25 PM
 **/
@Component
public class ProductServiceImpl implements ProductService {
	@Override
	public PageDTO<TaskInfoDTO> queryTaskPage(Integer userId) {
		return null;
	}
	
	@Override
	public PageDTO<ProductDTO> queryProductPage(Integer taskId) {
		return null;
	}
}
