package cn.hnist.pojo;

import java.io.Serializable;

/**
 * 页面信息类
 */
public class ResultInfo implements Serializable {

    private boolean flag;
    private String message;
    private Object obj;

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public ResultInfo() {
    }

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
