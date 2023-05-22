package com.wuqin.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
@Slf4j
public class EmailUtil {
    @Autowired
    private JavaMailSender mailSender;

    /**
     * 发送纯文本邮件信息
     * @param from  发送邮箱地址
     * @param to    接收方
     * @param subject   邮件主题
     * @param text   邮件内容
     */
    public void sendMessage(String from,String to,String subject,String text){
        //创建一个邮件对象
        SimpleMailMessage msg= new SimpleMailMessage();
        msg.setFrom(from);
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);
        mailSender.send(msg);
    }

    /**
     * 发送带附件的邮件信息
     * @param from  发送邮箱地址
     * @param toList    接收方
     * @param subject   邮件主题
     * @param text   邮件内容
     * @param file 单个文件
     */
    public void sendMessageCarryFile(String from, String toList, String subject, String text, File file){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            if(StringUtils.isEmpty(toList)){
                return;
            }
            String[] tos=  toList.split(",");
            MimeMessageHelper helper= new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(from);
            helper.setTo(tos);
            helper.setSubject(subject);
            helper.setText(text);
            helper.addAttachment(file.getName(),file);
            mailSender.send(mimeMessage);
        } catch (MessagingException e){
            e.printStackTrace();
        }
    }

    /**
     * 发送带附件的邮件信息
     * @param from  发送邮箱地址
     * @param to    接收方
     * @param subject   邮件主题
     * @param text   邮件内容
     * @param files 文件数组/支持多个文件
     */
    public void sendMessageCarryFiles(String from, String to, String subject, String text, File[] files){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper= new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);
            if(files != null && files.length > 0){ //添加多个附件
                for(File file:files){
                    helper.addAttachment(file.getName(),file);
                }
            }
            mailSender.send(mimeMessage);
        } catch (MessagingException e){
            e.printStackTrace();
        }
    }
}
