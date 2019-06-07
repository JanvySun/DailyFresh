package test;

import cn.hnist.utils.MailUtils;
import org.junit.Test;

public class MailUtilTest {

    @Test
    public void test() {
        String to = "1752852995@qq.com";
        String text = "测试邮件，无需回复!";
        String title = "测试邮件";
        MailUtils.sendMail(to,text,title);
        System.out.println("发送成功");
    }

}
