package com.hust.hydroelectric_backend.Service.AreaServices;

import com.hust.hydroelectric_backend.Dao.hydro.EnprMapper;
import com.hust.hydroelectric_backend.Dao.hydro.OperatorMapper;
import com.hust.hydroelectric_backend.Entity.Areas.Enpr;
import com.hust.hydroelectric_backend.Entity.Operator;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/20 14:59
 */
@Service
public class EnprService {

    private static int ENPR_OPERATOR = 1;

    @Resource
    EnprMapper enprMapper;

    @Resource
    OperatorMapper operatorMapper;

    public ResultData addEnpr(Enpr enpr){
        enpr.setCreateTime(System.currentTimeMillis()/1000);
        int i = enprMapper.addEnpr(enpr);
        Operator operator = new Operator();
        operator.setAccount(enpr.getEnprNo() + "Admin");
        operator.setPassword(enpr.getEnprNo() + "123");
        operator.setEnprNo(enpr.getEnprNo());
        operator.setOperatorType(ENPR_OPERATOR);
        int j = operatorMapper.addOperator(operator);
        if((i==j) && (i==1)){
            return Result.success(1);
        } else {
            return Result.error(HttpStatus.INTERNAL_SERVER_ERROR, "创建失败");
        }
    }

    public ResultData findAll(){
        return Result.success(enprMapper.findAll());
    }

    public ResultData getByEnprNo(String enprNo) {
        return Result.success(enprMapper.findByEnprNo(enprNo));
    }

    public ResultData uptByEnprNo(String enprNo) {
        return Result.success(enprMapper.uptByEnprNo(enprNo));
    }
}
