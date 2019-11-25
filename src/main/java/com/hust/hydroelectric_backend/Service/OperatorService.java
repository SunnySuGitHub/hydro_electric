package com.hust.hydroelectric_backend.Service;

import com.hust.hydroelectric_backend.Dao.OperatorMapper;
import com.hust.hydroelectric_backend.Entity.Operator;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/18 20:13
 */
@Service
public class OperatorService {

    @Resource
    OperatorMapper operatorMapper;

    public ResultData login(String account, String password, String enprNo){
        Operator operator = operatorMapper.login(account, password, enprNo);
        if(operator == null){
            return Result.error(HttpStatus.BAD_REQUEST, "不存在此管理员");
        } else {
            return Result.success(operator);
        }
    }

    public ResultData getOperator(int id){
        if(id == -1){
            return Result.error(HttpStatus.BAD_REQUEST, "参数不规范");
        } else {
            return Result.success(operatorMapper.getOperator(id));
        }
    }

    public ResultData addOperator(Operator operator){
        if(operator.getOperatorType() == null){
            return Result.error(HttpStatus.BAD_REQUEST, "参数不规范");
        } else {
            return Result.success(operatorMapper.addOperator(operator));
        }
    }

    public ResultData uptOperator(Operator operator){
        if(operator.getOperatorId() == null){
            return Result.error(HttpStatus.BAD_REQUEST, "参数不规范");
        } else {
            return Result.success(operatorMapper.uptOperator(operator));
        }
    }

    public ResultData delOperator(int id){
        if(id == -1){
            return Result.error(HttpStatus.BAD_REQUEST, "参数不规范");
        } else {
            int res = operatorMapper.delOperator(id);
            if(res == 1) return Result.success("删除成功");
            return Result.error(HttpStatus.INTERNAL_SERVER_ERROR, "删除失败");

        }
    }


}
