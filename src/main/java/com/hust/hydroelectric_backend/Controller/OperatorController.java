package com.hust.hydroelectric_backend.Controller;

import com.hust.hydroelectric_backend.Entity.Operator;
import com.hust.hydroelectric_backend.Service.OperatorService;
import com.hust.hydroelectric_backend.utils.ResponseHandler;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author: suxinyu
 * @DateTme: 2019/11/18 20:13
 */
@RestController
public class OperatorController {

    @Autowired
    OperatorService operatorService;

    @GetMapping("/Login")
    public ResultData login(@RequestParam("account")String account,
                            @RequestParam("password")String password,
                            @RequestParam("enprNo")String enprNo){
        return ResponseHandler.doHandle(() -> operatorService.login(account, password, enprNo));
    }

    @GetMapping("/operator")
    public ResultData getOprator(@RequestParam(value = "operatorId", defaultValue = "-1") int id){
        return ResponseHandler.doHandle(() -> operatorService.getOperator(id));
    }

    @PostMapping("/operator")
    public ResultData addOperator(@RequestBody Operator operator){
        return ResponseHandler.doHandle(() -> operatorService.addOperator(operator));
    }

    @DeleteMapping("/operator")
    public ResultData delOperator(@RequestParam(value = "operatorId", defaultValue = "-1") int id){
        return ResponseHandler.doHandle(() -> operatorService.delOperator(id));
    }

    @PutMapping("/operator")
    public ResultData uptOperator(@RequestBody Operator operator){
        return ResponseHandler.doHandle(() -> operatorService.uptOperator(operator));
    }

}
