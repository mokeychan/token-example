package com.cfx.controller;

import com.cfx.authorization.annotation.Authorization;
import com.cfx.authorization.annotation.CurrentUser;
import com.cfx.authorization.manager.TokenManager;
import com.cfx.common.ResultModel;
import com.cfx.common.ResultStatus;
import com.cfx.common.TokenModel;
import com.cfx.entity.User;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.cfx.repository.UserRepository;

/**
 * @Description: 请求控制--创建token和删除token(对应登录与登出)
 * @Author: chenfeixiang
 * @Date: Created in 11:52 2018/9/12
 */
@RestController
@RequestMapping("/tokens")
@Slf4j
public class TokenController {

    @Autowired
    TokenManager tokenManager;

    @Autowired
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<ResultModel> login(@RequestParam String username, @RequestParam String password) {
        log.info("method_login param is {},{}" ,username,password);
        User user = userRepository.findByUsername(username);
        // 如果用户未注册或者密码错误
        if(null == user || !user.getPassword().equals(password)) {
            return new ResponseEntity<>(ResultModel.error(ResultStatus.USERNAME_OR_PASSWORD_ERROR), HttpStatus.NOT_FOUND);
        }
        // 登录成功，生成token,保存用户登录状态
        TokenModel tokenModel = tokenManager.createToken(user.getId());
        return new ResponseEntity<>(ResultModel.ok(tokenModel), HttpStatus.OK);
    }

    @DeleteMapping
    @Authorization
    public ResponseEntity<ResultModel> logout(@CurrentUser User user) {
        log.info("current user is" + user.toString());
        tokenManager.deleteToken(user.getId());
        return new ResponseEntity<>(ResultModel.ok(), HttpStatus.OK);
    }

}
