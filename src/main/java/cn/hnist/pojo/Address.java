package cn.hnist.pojo;

import java.io.Serializable;

/**
 * 用户地址实体类
 */
public class Address implements Serializable {

    private Integer id;
    private String receiver;    // 收件人
    private String addr;        // 地址
    private String zip_code;    // 邮编
    private String phone;       // 手机号
    private Character is_default;
    private Integer user_id;    // 对应的userID

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getZip_code() {
        return zip_code;
    }

    public void setZip_code(String zip_code) {
        this.zip_code = zip_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Character getIs_default() {
        return is_default;
    }

    public void setIs_default(Character is_default) {
        this.is_default = is_default;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

}
