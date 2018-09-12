package com.cfx.common;

import lombok.Getter;

/**
 * @Description: 自定义返回结果
 * @Author: chenfeixiang
 * @Date: Created in 18:16 2018/9/11
 */
public class ResultModel {
    /**
     * 返回码
     */
    @Getter
    private Integer code;
    /**
     * 返回结果描述
     */
    @Getter
    private String message;
    /**
     * 返回内容
     */
    @Getter
    private Object content;

    public ResultModel(Integer code, String message, Object content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public ResultModel(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.content = "";
    }

    public ResultModel(ResultStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.content = "";
    }

    public ResultModel(ResultStatus status, Object content) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.content = content;
    }

    public static ResultModel ok(Object content) {
        return new ResultModel(ResultStatus.SUCCESS, content);
    }

    public static ResultModel ok() {
        return new ResultModel(ResultStatus.SUCCESS);
    }

    public static ResultModel error(ResultStatus error) {
        return new ResultModel(error);
    }
}
