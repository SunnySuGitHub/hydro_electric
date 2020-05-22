package com.hust.hydroelectric_backend.Controller;

import com.hust.hydroelectric_backend.Entity.Areas.Block;
import com.hust.hydroelectric_backend.Entity.Areas.Community;
import com.hust.hydroelectric_backend.Entity.Areas.Enpr;
import com.hust.hydroelectric_backend.Service.AreaServices.BlockService;
import com.hust.hydroelectric_backend.Service.AreaServices.CommunityService;
import com.hust.hydroelectric_backend.Service.AreaServices.EnprService;
import com.hust.hydroelectric_backend.utils.ResponseHandler;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 16:34
 * 区域相关操作
 */
@RestController
@CrossOrigin("*")
public class AreaController {

    @Autowired
    EnprService enprService;

    @Autowired
    CommunityService communityService;

    @Autowired
    BlockService blockService;

    /**
     * 获取公司相关信息
     */
    @GetMapping("/enpr/{epnrNo}")
    public ResultData getEnprMsg(@PathVariable("enprNo") String enprNo){
        return ResponseHandler.doHandle(() -> enprService.getByEnprNo(enprNo));
    }

    /**
     * 修改公司信息
     */
    @PutMapping("/enpr")
    public ResultData uptEnprMsg(@RequestBody Enpr enpr){
        return ResponseHandler.doHandle(() -> enprService.uptByEnprNo(enpr));
    }

    /**
     * 获取小区信息
     */
    @GetMapping("/community")
    public ResultData getCommunity(@RequestParam(value = "cId") int id) {
        return ResponseHandler.doHandle(() -> communityService.getCommunity(id));
    }

    /**
     * 增加小区信息
     */
    @PostMapping("/community")
    public ResultData addCommunity(@RequestBody Community community) {
        return ResponseHandler.doHandle(() -> communityService.addCommunity(community));
    }

    /**
     * 删除小区信息
     */
    @DeleteMapping("/community")
    public ResultData delCommunity(@RequestParam(value = "cId") int id) {
        return ResponseHandler.doHandle(() -> communityService.delCommunity(id));
    }

    /**
     * 更改小区信息
     */
    @PutMapping("/community")
    public ResultData uptCommunity(@RequestBody Community community) {
        return ResponseHandler.doHandle(() -> communityService.uptCommunity(community));
    }

    /**
     * 获取公司下所有小区信息
     */
    @GetMapping("/community/list/{enprNo}")
    public ResultData communityList(@PathVariable("enprNo") String enprNo,
                                    @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "10") int pageSize) {
        if(StringUtils.isNotBlank(enprNo))
            return ResponseHandler.doHandle(() -> communityService.communityList(enprNo, pageNum, pageSize));
        return Result.error(HttpStatus.BAD_REQUEST, "缺失公司编码");
    }

    /**
     * 获取小区下的所有楼栋信息
     */
    @GetMapping("/block/list/{cId}")
    public ResultData getBlockList(@PathVariable(value = "cId") int cid) {
        return ResponseHandler.doHandle(() -> blockService.getBlockByCid(cid));
    }

    /**
     * 获取楼栋信息
     */
    @GetMapping("/block")
    public ResultData getBlock(@RequestParam(value = "bId") int bid) {
        return ResponseHandler.doHandle(() -> blockService.getBlock(bid));
    }

    /**
     * 删除楼栋信息
     */
    @DeleteMapping("/block")
    public ResultData delBlock(@RequestParam(value = "bId") int bid) {
        return ResponseHandler.doHandle(() -> blockService.delBlock(bid));
    }

    /**
     * 更改楼栋信息
     */
    @PutMapping("/block")
    public ResultData uptBlock(@RequestBody Block block) {
        return ResponseHandler.doHandle(() -> blockService.uptBlock(block));
    }
}
