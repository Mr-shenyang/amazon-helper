package com.oscoder.amazon.helper.test.service;

import com.oscoder.amazon.helper.common.dto.ResponseDTO;
import com.oscoder.amazon.helper.notify.api.EmailMsgType;
import com.oscoder.amazon.helper.notify.api.dto.EmailDTO;
import com.oscoder.amazon.helper.notify.api.service.EmailService;
import com.oscoder.amazon.helper.test.BaseTest;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailServiceTest extends BaseTest {
    @Autowired
    private EmailService emailService;
    @Test
    public void testNotify() {
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setReceivers(Lists.newArrayList("shenyang0613@qq.com"));
        emailDTO.setSubject("测试Email");
        emailDTO.setContent("126343");
        ResponseDTO<String> notify = emailService.notify(EmailMsgType.SIMPLE.getType(), emailDTO);
        Assert.assertTrue(notify.getFailMsg(),notify.isSuccess());
    }
}
