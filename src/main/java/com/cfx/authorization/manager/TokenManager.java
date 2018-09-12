package com.cfx.authorization.manager;

import com.cfx.common.TokenModel;

/**
 * @Description: 对token操作的接口
 * @Author: chenfeixiang
 * @Date: Created in 18:55 2018/9/11
 */
public interface TokenManager {

    /**
     * 创建token并关联一个指定的账户
     * @param userId 指定账户的id
     * @return
     */
    TokenModel createToken(Long userId);

    /**
     * 校验token有效性
     * @param model token
     * @return
     */
    Boolean checkToken(TokenModel model);

    /**
     * 从字符串中解析token
     * @param authentication 加密后的字符串
     * @return
     */
    TokenModel getToken(String authentication);

    /**
     * 删除token
     * @param userId 用户id
     */
    void deleteToken(Long userId);

}
