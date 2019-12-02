package com.hust.hydroelectric_backend.Controller;

import com.hust.hydroelectric_backend.Entity.Notice;
import com.hust.hydroelectric_backend.Entity.Repair;
import com.hust.hydroelectric_backend.Service.NoticeService;
import com.hust.hydroelectric_backend.Service.RepairService;
import com.hust.hydroelectric_backend.utils.ResponseHandler;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/27 21:13
 * 日常小区/公司管理相关
 */
@RestController
@CrossOrigin("*")
public class ManagementController {

    @Autowired
    RepairService repairService;

    @Autowired
    NoticeService noticeService;

    /**
     * 用户报修
     */
    @PostMapping("/repair")
    public ResultData repair(@RequestBody Repair repair){
        return ResponseHandler.doHandle(() -> repairService.add(repair));
    }

    /**
     * 查看小区报修单
     */
    @GetMapping("/repair/list")
    public ResultData list(@RequestParam("enprNo") String enprNo,
                             @RequestParam(value = "state", defaultValue = "-1") int state){
        return ResponseHandler.doHandle(() -> repairService.list(enprNo, state));
    }

    /**
     * 报修进度更新
     */
    @PutMapping("/repair")
    public ResultData upt(@RequestBody Repair repair){
        return ResponseHandler.doHandle(() -> repairService.upt(repair));
    }

    /**
     * 发布公告
     */
    @PostMapping("/notice")
    public ResultData addNotice(@RequestBody Notice notice){
        return ResponseHandler.doHandle(() -> noticeService.addNotice(notice));
    }

    /**
     * 查看公告发布历史
     */
    @GetMapping("/notice/list")
    public ResultData noticeList(@RequestParam("enprNo") String enprNo){
        return ResponseHandler.doHandle(() -> noticeService.noticeList(enprNo));
    }

    /**
     * 编辑公告
     */
    @PutMapping("/notice")
    public ResultData noticeList(@RequestBody Notice notice){
        return ResponseHandler.doHandle(() -> noticeService.uptNotice(notice));
    }

    /**
     * 删除公告
     */
    @DeleteMapping("/notice")
    public ResultData delNotice(@RequestParam("id") int id,
                                @RequestParam("enprNo") String enprNo){
        return ResponseHandler.doHandle(() -> noticeService.delNotice(id, enprNo));
    }




}
