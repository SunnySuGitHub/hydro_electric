package com.hust.hydroelectric_backend.utils;

import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import java.util.concurrent.TimeoutException;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/18 19:07
 */
public class ResponseHandler {

    public interface RequestHandler {
        ResultData handle() throws Exception;
    }

    /**
     * 参数实现Request Handler接口，从而将一个方法实现作为一个参数进行传递，省去大量的try catch
     */
    public static ResultData doHandle(RequestHandler handler) {
        try {
            return handler.handle();
        } catch (TimeoutException et) {
            String message = "可以尝试减小查询时间范围";
            return Result.error(HttpStatus.REQUEST_TIMEOUT, "【hydro】查询超时,请稍后重试," + message);
        } catch (Exception e) {
            return dealException(e);
        }
    }

    private static ResultData dealException(Exception e) {
        int messageLength = 50;
        String message = e.getMessage();
        if (StringUtils.isNotBlank(message) && message.length() > messageLength) {
            message = message.substring(0, messageLength);
        }
        e.printStackTrace();
        return Result.error(HttpStatus.INTERNAL_SERVER_ERROR, "【hydro】系统错误,请稍后重试,错误信息:" + message);
    }
}
