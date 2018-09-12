package authorization.manager.impl;

import authorization.manager.TokenManager;
import common.TokenModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Description: 通过Redis操作token的实现类
 * @Author: chenfeixiang
 * @Date: Created in 19:02 2018/9/11
 */
@Component
@Slf4j
public class RedisTokenManager implements TokenManager {

    public TokenModel createToken(Long userId) {
        return null;
    }

    public Boolean checkToken(TokenModel model) {
        return null;
    }

    public TokenModel getToken(String authentication) {
        return null;
    }

    public void deleteToken(Long userId) {

    }
}
