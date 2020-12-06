package com.example.demo.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/*
 *
 *@author:zzf
 *@time:2020-12-05
 *
 */
public class MailUtil {
    private String email;//收件人邮箱
    private Integer id;//激活码
    public MailUtil(String email,Integer id){
        this.email=email;
        this.id=id;
    }

    public void sendEmail(){
        String from="1762063213@qq.com";
        String host="smtp.qq.com";
        //获取系统属性
        Properties properties=System.getProperties();
        properties.setProperty("mail.smtp.host",host);
        //打开认证
        properties.setProperty("mail.smtp.auth","true");
        try{
            MailSSLSocketFactory sf=new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            properties.put("mail.smtp.ssl.enable",true);
            properties.put("mail.smtp.ssl.socketFactory",sf);
            //获取默认session对象
            Session session=Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("1762063213@qq.com","whhcxqzgtokjbcfh");
                }
            });
            //创建邮件对象
            Message message=new MimeMessage(session);
            //设置发件人
            message.setFrom(new InternetAddress(from));

            //设置接收人
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(email));

            //设置邮件主题
            message.setSubject("账号激活");

            //设置邮件内容
            Object key;
            String content = "<html>" +
                    "<head></head>" +
                    "<body>" +
                    "<h1>这是一封激活邮件，点击一下链接激活</h1>" +
                    "<h3>" +
                    "<a href='http://localhost:8081/active?id="+id+
                    "'>active</a>" +
                    "</h3>" +
                    "</body>" +
                    "</html>";
            message.setContent(content,"text/html;charset=UTF-8");

            //发送邮件
            Transport.send(message);
            System.out.println("邮件发送成功");

        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
