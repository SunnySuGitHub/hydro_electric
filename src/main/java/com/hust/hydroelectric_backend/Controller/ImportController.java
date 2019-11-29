package com.hust.hydroelectric_backend.Controller;

import com.hust.hydroelectric_backend.Service.ExcelImport.AmmeterImportService;
import com.hust.hydroelectric_backend.Service.ExcelImport.WaterImportService;
import com.hust.hydroelectric_backend.utils.ResponseHandler;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 10:52
 * 导入操作
 */
@RestController("/import")
@CrossOrigin("*")
public class ImportController {

    @Autowired
    WaterImportService waterImportService;

    @Autowired
    AmmeterImportService ammeterImportService;

    @PostMapping("/watermeter")
    public ResultData watermeterImport(HttpServletRequest request){
        return ResponseHandler.doHandle(() -> waterImportService.checkOrRead(request));
    }

    @PostMapping("/ammeter")
    public ResultData ammeterImport(HttpServletRequest request){
        return ResponseHandler.doHandle(() -> ammeterImportService.checkOrRead(request));
    }
}
