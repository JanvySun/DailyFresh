package cn.hnist.pojo;

import java.io.Serializable;

/**
 * 用户实体类
 */
public class User implements Serializable {

    private Integer id;
    private String username;    // 用户名
    private String password;    // 密码
    private String email;       // 邮箱
    private Character status;   // 是否激活('Y'/'N')
    private String code;        // 激活码
    private Character is_superuser;

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getId() {
        return id;
    }

    public Character getIs_superuser() {
        return is_superuser;
    }

    public void setIs_superuser(Character is_superuser) {
        this.is_superuser = is_superuser;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status +
                ", code='" + code + '\'' +
                ", id=" + id +
                ", is_superuser=" + is_superuser +
                '}';
    }
}
