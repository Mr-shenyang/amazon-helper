package com.oscoder.amazon.helper.spider.service.data.mapper;

import com.github.pagehelper.Page;
import com.oscoder.amazon.helper.spider.service.data.po.TaskInfoPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskInfoMapper {
    /**
     * 插入
     *
     * @param taskInfo
     * @return
     */
    int insert(@Param("taskInfo") TaskInfoPO taskInfo);

    Page<TaskInfoPO> pageTaskInfoByUserId(@Param("userId") Integer userId, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);
}
