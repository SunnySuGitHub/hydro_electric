package com.hust.hydroelectric_backend.Controller;

import com.hust.hydroelectric_backend.Entity.Operator;
import com.hust.hydroelectric_backend.Service.OperatorService;
import com.hust.hydroelectric_backend.Service.PayService;
import com.hust.hydroelectric_backend.utils.ResponseHandler;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


/**
 * @author: suxinyu
 * @DateTme: 2019/11/18 20:13
 * 管理员相关
 */
@RestController
@CrossOrigin("*")
public class OperatorController {

    @Autowired
    OperatorService operatorService;

    @Autowired
    PayService payService;

    @GetMapping("/Login")
    public ResultData login(@RequestParam("account")String account,
                            @RequestParam("password")String password,
                            @RequestParam("enprNo")String enprNo){
        return ResponseHandler.doHandle(() -> operatorService.login(account, password, enprNo));
    }

    @GetMapping("/operator")
    public ResultData getOprator(@RequestParam(value = "operatorId", defaultValue = "-1") int id,
                                 @RequestParam("enprNo") String enprNo){
        return ResponseHandler.doHandle(() -> operatorService.getOperator(id, enprNo));
    }

    @PostMapping("/operator")
    public ResultData addOperator(@RequestBody Operator operator){
        return ResponseHandler.doHandle(() -> operatorService.addOperator(operator));
    }

    @DeleteMapping("/operator")
    public ResultData delOperator(@RequestParam(value = "operatorId", defaultValue = "-1") int id,
                                  @RequestParam("enprNo") String enprNo){
        return ResponseHandler.doHandle(() -> operatorService.delOperator(id, enprNo));
    }

    @PutMapping("/operator")
    public ResultData uptOperator(@RequestBody Operator operator){
        return ResponseHandler.doHandle(() -> operatorService.uptOperator(operator));
    }

    @GetMapping("/operator/list")
    public ResultData operatorList(@RequestParam("enprNo") String enprNo){
        if(StringUtils.isNotBlank(enprNo)) {
            return ResponseHandler.doHandle(() -> operatorService.operatorList(enprNo));
        } else {
            return Result.error(HttpStatus.BAD_REQUEST, "公司信息缺失");
        }
    }

    /**
     * 获取时间段内缴费记录详情
     */
    @GetMapping("/GetPayHistory")
    public ResultData getPayHistory(@RequestParam("enprNo") String enprNo,
                                    @RequestParam(value = "startDateLine", defaultValue = "-1") long startLine,
                                    @RequestParam(value = "endDateLine", defaultValue = "-1") long endLine) {
        return ResponseHandler.doHandle(() -> payService.getPayHistory(enprNo, startLine, endLine));
    }

    /**
     * 获取管理员的收费记录
     */
    @GetMapping("/GetOperatorPayHistory")
    public ResultData getOperatorPayHistory(@RequestParam("operatorId") int operatorId,
                                            @RequestParam(value = "startDateLine", defaultValue = "-1") long startLine,
                                            @RequestParam(value = "endDateLine", defaultValue = "-1") long endLine) {
        return ResponseHandler.doHandle(() -> payService.getOperatorPayHistory(operatorId, startLine, endLine));
    }


}
