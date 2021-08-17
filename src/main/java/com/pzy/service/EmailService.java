package com.pzy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Author Nice
 * @Date 2021/7/1 17:54
 */

@Service
public class EmailService {

    @Autowired
    JavaMailSenderImpl mailSender;

    /**
     * 从配置文件中获取发件人
     */
    @Value("${spring.mail.username}")
    private String sender;

    /**
     * 邮件发送
     * @param receiver 收件人
     * @param code 验证码
     * @throws MailSendException 邮件发送错误
     */
    @Async
    public void sendEmailVerCode(String receiver, String code) throws MailSendException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("校验码");	//设置邮件标题
        message.setText("尊敬的用户,您好:\n"
                + "\n本次请求的注册验证码为:" + code + ",本验证码5分钟内有效，请及时输入。（请勿泄露此验证码）\n"
                + "\n如非本人操作，请忽略该邮件。\n(这是一封自动发送的邮件，请不要直接回复）");	//设置邮件正文
        message.setTo(receiver);	//设置收件人
        message.setFrom(sender);	//设置发件人
        mailSender.send(message);	//发送邮件
    }
}
