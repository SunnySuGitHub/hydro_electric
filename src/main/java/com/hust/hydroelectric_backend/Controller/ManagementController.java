package com.hust.hydroelectric_backend.Controller;

import com.hust.hydroelectric_backend.Entity.Ammeters.AmmeterRate;
import com.hust.hydroelectric_backend.Entity.Notice;
import com.hust.hydroelectric_backend.Entity.Repair;
import com.hust.hydroelectric_backend.Service.AmmeterServices.RateService;
import com.hust.hydroelectric_backend.Service.LadderPriceService;
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

    @Autowired
    LadderPriceService ladderPriceService;

    @Autowired
    RateService rateService;

    /**
     * 用户报修
     */
    @PostMapping("/repair")
    public ResultData repair(@RequestBody Repair repair){
        return ResponseHandler.doHandle(() -> repairService.add(repair));
    }

    /**
     * 查看公司报修单
     */
    @GetMapping("/repair/list/{enprNo}")
    public ResultData list(@PathVariable("enprNo") String enprNo,
                           @RequestParam(value = "state", defaultValue = "-1") int state,
                           @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                           @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        return ResponseHandler.doHandle(() -> repairService.list(enprNo, state, pageNum, pageSize));
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
    @GetMapping("/notice/list/{enprNo}")
    public ResultData noticeList(@PathVariable("enprNo") String enprNo,
                                 @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        return ResponseHandler.doHandle(() -> noticeService.noticeList(enprNo, pageNum, pageSize));
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
    public ResultData delNotice(@RequestParam("id") int id){
        return ResponseHandler.doHandle(() -> noticeService.delNotice(id));
    }

    /**
     * 获取阶梯水价
     * @param enprNo
     * @param waterType
     * 水表类型
     * 1 居民生活用水
     * 2 工业用水
     * 3 行政事业单位用水
     * 4 经营用水
     * 5 特种行业用水
     */
    @GetMapping("/ladder/watermeter/{enprNo}")
    public ResultData getWatermeterLadder(@PathVariable("enprNo") String enprNo,
                                          @RequestParam("waterType") int waterType){
        return ResponseHandler.doHandle(() -> ladderPriceService.getWatermeterLadder(enprNo, waterType));
    }

    /**
     * 获取阶梯电价
     */
    @GetMapping("/ladder/ammeter/{enprNo}")
    public ResultData getAmmeterLadder(@PathVariable("enprNo") String enprNo,
                                       @RequestParam("voltageType") int vType){
        return ResponseHandler.doHandle(() -> ladderPriceService.getAmmeterLadder(enprNo, vType));
    }

    /**
     * 获取电表不同费率
     * 0：总量
     * 1：尖峰
     * 2：峰
     * 3：平
     * 4：谷
     */
    @GetMapping("/rate/ammeter/{enprNo}")
    public ResultData getAmmeterRate(@PathVariable("enprNo") String enprNo){
        return ResponseHandler.doHandle(() -> rateService.getRateList(enprNo));
    }

    /**
     * 修改电表费率
     */
    @PutMapping("/rate/ammeter")
    public ResultData getAmmeterRate(@RequestBody AmmeterRate ammeterRate){
        return ResponseHandler.doHandle(() -> rateService.uptRate(ammeterRate));
    }




}
