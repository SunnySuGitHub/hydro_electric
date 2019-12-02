package com.hust.hydroelectric_backend.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hust.hydroelectric_backend.Dao.OperatorMapper;
import com.hust.hydroelectric_backend.Entity.Operator;
import com.hust.hydroelectric_backend.utils.JedisUtil;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/18 20:13
 */
@Service
public class OperatorService {

    @Resource
    OperatorMapper operatorMapper;

    @Autowired
    JedisUtil jedisUtil;

    public ResultData login(String account, String password, String enprNo){
        Operator operator = operatorMapper.login(account, password, enprNo);
        if(operator == null){
            return Result.error(HttpStatus.BAD_REQUEST, "不存在此管理员");
        } else {
            return Result.success(operator);
        }
    }

    public ResultData getOperator(int id, String enprNo){
        if(id == -1){
            return Result.error(HttpStatus.BAD_REQUEST, "参数不规范");
        }
        if(jedisUtil.hGet(enprNo, "operatorId"+id) == null) {
            Operator operator = operatorMapper.getOperator(id);
            jedisUtil.hSet(enprNo, "operatorId"+id, JSON.toJSONString(operator));
            return Result.success(operator);
        } else {
            Operator operator = JSON.parseObject(jedisUtil.hGet(enprNo, "operatorId"+id), Operator.class);
            return Result.success(operator);
        }
    }

    public ResultData addOperator(Operator operator){
        if(operator.getOperatorType() == null){
            return Result.error(HttpStatus.BAD_REQUEST, "参数不规范");
        } else {
            String enprNo = operator.getEnprNo();
            jedisUtil.hDel(enprNo, "operatorList");
            return Result.success(operatorMapper.addOperator(operator));
        }
    }

    public ResultData uptOperator(Operator operator){
        if(operator.getOperatorId() == null){
            return Result.error(HttpStatus.BAD_REQUEST, "参数不规范");
        } else {
            String enprNo = operator.getEnprNo();
            jedisUtil.hDel(enprNo, "operatorId"+operator.getOperatorId());
            jedisUtil.hDel(enprNo, "operatorList");
            return Result.success(operatorMapper.uptOperator(operator));
        }
    }

    public ResultData delOperator(int id, String enprNo){
        if(id == -1){
            return Result.error(HttpStatus.BAD_REQUEST, "参数不规范");
        } else {
            int res = operatorMapper.delOperator(id);
            jedisUtil.hDel(enprNo, "operatorId"+id);
            jedisUtil.hDel(enprNo, "operatorList");
            if(res == 1) return Result.success("删除成功");
            return Result.error(HttpStatus.INTERNAL_SERVER_ERROR, "删除失败");

        }
    }

    public ResultData operatorList(String enprNo){
        if(jedisUtil.hGet(enprNo, "operatorList") == null) {
            List<Operator> operatorList = operatorMapper.operatorList(enprNo);
            String operatorDetailList = JSONArray.toJSONString(operatorList);
            jedisUtil.hSet(enprNo, "operatorList", operatorDetailList);
            return Result.success(operatorList);
        } else {
            String res = jedisUtil.hGet(enprNo, "operatorList");
            List<Operator> operators = JSONArray.parseArray(res, Operator.class);
            return Result.success(operators);
        }
    }


}
