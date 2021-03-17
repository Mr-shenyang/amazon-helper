package com.oscoder.amazon.helper.notify.service.impl;

import com.oscoder.amazon.helper.common.dto.ResponseDTO;
import com.oscoder.amazon.helper.notify.api.dto.EmailDTO;
import com.oscoder.amazon.helper.notify.api.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    // 后期考虑引入其他类型邮件的发送
    @Override
    public ResponseDTO<String> notify(Integer msgType,EmailDTO emailDTO) {
            //创建简单邮件消息
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(sender);
            message.setTo(emailDTO.getReceivers().stream().toArray(String[]::new));
            message.setSubject(emailDTO.getSubject());
            message.setText(emailDTO.getContent());
            try {
                mailSender.send(message);
                return ResponseDTO.success("邮件发送成功");
            } catch (MailException e) {
                log.error("send email error",e);
                return ResponseDTO.fail("邮件发送失败");
            }
    }
}
