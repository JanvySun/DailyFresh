package cn.hnist.pojo;

import java.io.Serializable;

public class ResultInfo implements Serializable {
    private boolean flag;
    private String message;

    public ResultInfo() { }

    public ResultInfo(boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
