package com.hust.hydroelectric_backend.utils.result;

import com.hust.hydroelectric_backend.Entity.User;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/18 18:45
 * 结果类基类
 */
public class ResultData<T> {
    private int error;
    private String msg;
    private T data;

    public ResultData(int error, String msg, T data) {
        this.error = error;
        this.msg = msg;
        this.data = data;
    }

    public int getError() {
        return this.error;
    }

    public void setError(int error) {
        this.error = error;
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
