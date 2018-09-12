package com.cfx.authorization.manager.impl;

import com.cfx.authorization.manager.TokenManager;
import com.cfx.common.Constants;
import com.cfx.common.TokenModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 通过Redis操作token的实现类
 * @Author: chenfeixiang
 * @Date: Created in 19:02 2018/9/11
 */
@Component
@Slf4j
public class RedisTokenManager implements TokenManager {

    private RedisTemplate<Long, String> redisTemplate;

    @Autowired
    public void setRedis(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        // 泛型设置成Long后必须更改对应的序列化方案
        redisTemplate.setKeySerializer(new JdkSerializationRedisSerializer());
    }

    public TokenModel createToken(Long userId) {
        // 使用uuid来生成token
        String token = UUID.randomUUID().toString().replace("-", "");
        log.info("存入redis中的token: " + token);
        TokenModel tokenModel = new TokenModel(userId, token);
        // 通过绑定key-useId,将token存入redis中，并设置过期时间（当前为1min）
        redisTemplate.boundValueOps(userId).set(token, Constants.TOKEN_EXPIRES_MINUTE, TimeUnit.MINUTES);
        return tokenModel;
    }

    public Boolean checkToken(TokenModel model) {
        if(null == model) {
            return false;
        }
        String token = redisTemplate.boundValueOps(model.getUserid()).get();
        log.info("请求token为{}，redis中的token为{}" + model.getToken(), token);
        if(null == token || token != model.getToken()) {
            return false;
        }
        // 如果验证成功，说明账户进行了有效的操作，延长账户的token有效期(重新更新key的有效期)
        redisTemplate.boundValueOps(model.getUserid()).expire(Constants.TOKEN_EXPIRES_MINUTE, TimeUnit.MINUTES);
        return true;
    }

    public TokenModel getToken(String authentication) {
        if(null == authentication || authentication.length() == 0) {
            return null;
        }
        String[] param = authentication.split("_");
        if(param.length != 2) {
            return null;
        }
        Long userId = Long.parseLong(param[0]);
        String token = param[1];
        log.info("通过认证信息获取到的token为： " + token);
        TokenModel tokenModel = new TokenModel(userId, token);
        return tokenModel;
    }

    public void deleteToken(Long userId) {
        redisTemplate.delete(userId);
    }
}
