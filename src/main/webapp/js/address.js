/**
 * 校验收件人
 */
function check_receiver() {
    var $receiver = $("#receiver");
    var reg_receiver = /^.{1,16}$/;
    var flag = reg_receiver.test($receiver.val());
    if (flag) {
        // 匹配成功
        $receiver.css("border", "");
    } else {
        $receiver.css("border", "1px solid red");
    }
    return flag;
}

/**
 * 校验地址
 */
function check_addr() {
    var $address = $("#address");
    var reg_addr = /^.+$/;
    var flag = reg_addr.test($address.val());
    if (flag) {
        $address.css("border", "");
    } else {
        $address.css("border", "1px solid red");
    }
    return flag;
}

/**
 * 校验邮编
 */
function check_zipCode() {
    var $zipCode = $("#zip_code");
    var reg_code = /^\d{0,6}$/;
    var flag = reg_code.test($zipCode.val());
    if (flag) {
        $zipCode.css("border", "");
    } else {
        $zipCode.css("border", "1px solid red");
    }
    return flag;
}

/**
 * 校验电话
 */
function check_phone() {
    var $phone = $("#phone");
    var reg_phone = /^1[34578]\d{9}$/;
    var flag = reg_phone.test($phone.val());
    if (flag) {
        $phone.css("border", "");
    } else {
        $phone.css("border", "1px solid red");
    }
    return flag;
}
