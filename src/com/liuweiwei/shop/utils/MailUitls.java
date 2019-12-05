package com.liuweiwei.shop.utils;

import javax.mail.*;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUitls {

    public static void sendMail(String to, String code) {
        Properties props = new Properties();
        props.setProperty("mail.host", "localhost");
        Session session = Session.getInstance(props, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("admin@alibaba.com", "12345678");
            }

        });

        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress("admin@alibaba.com"));
            message.addRecipient(RecipientType.TO, new InternetAddress(to));
            message.setSubject("来自购物天堂阿里巴巴商城官方激活邮件");
            message.setContent("<h1>点下面链接完成激活操作</h1><h3><a href='http://192.168.36.103:8080/shop/user_active.action?code=" + code + "'>http://192.168.36.103:8080/shop/user_active.action?code=" + code + "</a></h3>", "text/html;charset=UTF-8");
            Transport.send(message);
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        sendMail("admin@alibaba.com", "12345678");
    }
}
