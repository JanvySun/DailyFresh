package cn.hnist.exception;

/**
 * 自定义异常类
 */
public class WebException extends Exception {
    private String message;

    public WebException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
