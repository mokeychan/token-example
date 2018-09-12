package common;

/**
 * @Description: Token的Model类，可以增加字段提高安全性，例如时间戳、url签名
 * @Author: chenfeixiang
 * @Date: Created in 18:38 2018/9/11
 */
public class TokenModel {
    /**
     * 用户id
     */
    private Long userid;

    /**
     * token
     */
    private String token;

    public TokenModel(Long userid, String token) {
        this.userid = userid;
        this.token = token;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
