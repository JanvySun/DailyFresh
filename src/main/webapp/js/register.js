/**
 * 校验用户名：单词字符，5~20位，非空
 */
function check_user_name() {
    var $username = $('#user_name');
    var reg_username = /^\w{5,20}$/;
    var flag = reg_username.test($username.val());
    if (flag) {
        // 匹配成功，用户名合法
        $username.next().hide();
        $username.css("border", "");
    }
    else {
        // 匹配失败，用户名不合法
        $username.next().show();
        $username.css("border", "1px solid red");
    }
    return flag;
}

/**
 * 校验密码：单词字符，5~20位，非空
 */
function check_pwd() {
    var $pwd = $('#pwd');
    var reg_pwd = /^\w{5,20}$/;
    var flag = reg_pwd.test($pwd.val());
    if (flag) {
        $pwd.next().hide();
        $pwd.css("border", "");
    } else {
        // 不匹配
        $pwd.next().show();
        $pwd.css("border", "1px solid red");
    }
    return flag;
}

/**
 * 校验确认密码：和密码一样
 */
function check_cpwd() {
    var $pass = $('#pwd');
    var $cpss = $('#cpwd');

    if ($pass.val() != $cpss.val()) {
        // 不一致
        $cpss.next().show();
        $cpss.css("border", "1px solid red");
        return false;
    }
    else {
        $cpss.next().hide();
        $cpss.css("border", "");
        return true;
    }
}

/**
 * 校验邮箱: 邮箱基本格式
 */
function check_email() {
    var $email = $('#email');
    var re_email = /^[a-z0-9][\w+.\-]*@[a-z0-9\-]+(\.[a-z]{2,5}){1,2}$/;
    var flag = re_email.test($email.val());
    if (flag) {
        $email.next().hide();
        $email.css("border", "");
    }
    else {
        $email.next().show();
        $email.css("border", "1px solid red");
    }
    return flag;
}

