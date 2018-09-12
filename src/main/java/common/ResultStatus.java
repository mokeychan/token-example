package common;

/**
 * @Description: 返回状态码
 * @Author: chenfeixiang
 * @Date: Created in 18:23 2018/9/11
 */
public enum ResultStatus {

    SUCCESS(100, "成功"),
    USERNAME_OR_PASSWORD_ERROR(-1001, "用户名或密码错误"),
    USER_NOT_EXIST(-1002, "用户不存在"),
    USER_NOT_LOGIN(-1003, "用户未登录");

    /**
     * 返回码
     */
    private Integer code;
    /**
     * 返回结果描述
     */
    private String message;

    ResultStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
