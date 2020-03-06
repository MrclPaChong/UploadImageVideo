package com.weirdo.response;

/**
 * 通用的响应状态码enum
 * Created by Administrator on 2019/7/29.
 */
public enum StatusCode {

    Success(0,"成功"),
    FailPath(-1,"上传失败，路径或目录名非法"),

    FileIsNull(401,"上传文件不能为空!"),
    FielExist(422,"文件已存在"),
    ;

    private Integer code;
    private String msg;

    StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
