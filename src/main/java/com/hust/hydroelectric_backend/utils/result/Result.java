package com.hust.hydroelectric_backend.utils.result;

import com.github.pagehelper.Page;
import org.springframework.http.HttpStatus;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/18 18:46
 * 结果工具类
 */
public class Result<E> extends ResultData {

    public Result(int error, String msg, E data) {
        super(error, msg, data);
    }

    public static <E> Result<?> success(E result) {
        if (result instanceof Page) {
            return success(new PageResult((Page) result));
        }
        return of(0, HttpStatus.OK.getReasonPhrase(), result);
    }

    private static <E> Result<E> of(int code, String message, E result) {
        return new Result(code, message, result);
    }

    public static Result error(String result) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, result);
    }

    public static Result error(HttpStatus status, String result) {
        return new Result(status.value(), result, null);
    }

}
