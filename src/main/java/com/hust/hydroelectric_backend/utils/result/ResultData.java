package com.hust.hydroelectric_backend.utils.result;


/**
 * @author: suxinyu
 * @DateTme: 2019/11/18 18:45
 * 结果类基类
 */
public class ResultData<T> {
    private int code;
    private String msg;
    private T data;

    public ResultData(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
