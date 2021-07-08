package com.oscoder.amazon.helper.test.mapper;

import com.github.pagehelper.Page;
import com.oscoder.amazon.helper.spider.api.enums.TaskStateEnum;
import com.oscoder.amazon.helper.spider.service.data.mapper.TaskInfoMapper;
import com.oscoder.amazon.helper.spider.service.data.po.TaskInfoPO;
import com.oscoder.amazon.helper.test.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class TaskInfoMapperTest extends BaseTest {
    @Autowired
    private TaskInfoMapper taskInfoMapper;
    @Test
    public void test_TaskInfo(){
        TaskInfoPO taskInfoPO = new TaskInfoPO();
        int userId = 6;
        taskInfoPO.setUserId(userId);
        taskInfoPO.setKeyWord("测试");
        taskInfoPO.setSite("德国站");
        taskInfoPO.setState(TaskStateEnum.COMPLETE.getState());
        int insert = taskInfoMapper.insert(taskInfoPO);
        Assert.assertTrue(insert > 0);
        Page<TaskInfoPO> taskInfoPOS = taskInfoMapper.pageTaskInfoByUserId(userId, 1, 4);
        Assert.assertTrue(taskInfoPOS.getTotal() > 0);
    }
}
