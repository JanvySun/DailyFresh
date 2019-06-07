package cn.hnist.pojo;

import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String email;
    private Character status;
    private String code;
    private Integer id;
    private Character is_superuser;

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

    public void setId(Integer id) {
        this.id = id;
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
