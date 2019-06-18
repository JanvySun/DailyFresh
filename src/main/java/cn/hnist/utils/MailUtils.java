package cn.hnist.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发邮件工具类
 */
public final class MailUtils {

    // 发件人邮箱地址
    private static final String USER = "1374867470@qq.com";
    // 登录密码或授权码
    private static final String PASSWORD = "rioufctbhmlsijhj";

    /**
     * 发送邮件
     * @param to 收件人邮箱
     * @param text 邮件正文
     * @param title 标题
     */
    public static void sendMail(String to, String text, String title) throws MessagingException {

        // 配置发送邮件的环境属性
        final Properties props = new Properties();
        // 表示SMTP发送邮件，需要进行身份验证
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.qq.com");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.debug", "true");
        //遇到最多的坑就是下面这行，不加要报“A secure connection is requiered”错。
        props.put("mail.smtp.starttls.enable", "true");
        // 发件人的账号
        props.put("mail.user", USER);
        // 访问SMTP服务时需要提供的密码
        props.put("mail.password", PASSWORD);

        // 构建授权信息，用于进行SMTP进行身份验证
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // 用户名、密码
                String userName = props.getProperty("mail.user");
                String password = props.getProperty("mail.password");
                return new PasswordAuthentication(userName, password);
            }
        };
        // 使用环境属性和授权信息，创建邮件会话
        Session mailSession = Session.getInstance(props, authenticator);
        // 创建邮件消息
        MimeMessage message = new MimeMessage(mailSession);
        // 设置发件人
        InternetAddress form = new InternetAddress(props.getProperty("mail.user"));
        message.setFrom(form);

        // 设置收件人
        InternetAddress receiver = new InternetAddress(to);
        message.setRecipient(MimeMessage.RecipientType.TO, receiver);

        // 设置抄送，抄送和密送如果不写正确的地址也要报错。最好注释不用。
//        InternetAddress cc = new InternetAddress("");
//        message.setRecipient(RecipientType.CC, cc);
//
//        // 设置密送，其他的收件人不能看到密送的邮件地址
//        InternetAddress bcc = new InternetAddress("");
//        message.setRecipient(RecipientType.CC, bcc);

        // 设置邮件标题
        message.setSubject(title);

        // 设置邮件的内容体
        message.setContent(text, "text/html;charset=UTF-8");

        // 发送邮件
        Transport.send(message);

    }
}
