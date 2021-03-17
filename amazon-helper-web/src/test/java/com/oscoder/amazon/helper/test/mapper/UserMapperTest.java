package com.oscoder.amazon.helper.test.mapper;

import com.oscoder.amazon.helper.test.BaseTest;
import com.oscoder.amazon.helper.user.api.enums.UserType;
import com.oscoder.amazon.helper.user.service.data.mapper.UserMapper;
import com.oscoder.amazon.helper.user.service.data.po.UserPO;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapperTest extends BaseTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test_User(){
        UserPO user = new UserPO();
        user.setEmail("shenyang@123");
        user.setType(UserType.ADMIN.getType());
        user.setName("张三");
        user.setTel("17633221231");
        user.setPassword("123456abcd");
        user.setStatus(1);
        int insert = userMapper.insert(user);
        Assert.assertTrue(insert > 0);
    }
}
