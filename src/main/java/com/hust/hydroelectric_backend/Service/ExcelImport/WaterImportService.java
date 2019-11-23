package com.hust.hydroelectric_backend.Service.ExcelImport;

import com.hust.hydroelectric_backend.Dao.hydro.*;
import com.hust.hydroelectric_backend.Entity.Block;
import com.hust.hydroelectric_backend.Entity.User;
import com.hust.hydroelectric_backend.Entity.Watermeter;
import com.hust.hydroelectric_backend.utils.ExcelImportUtil;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 11:05
 * 水表导入相关操作
 */
@Service
public class WaterImportService extends ImportBase {

    @Resource
    UserMapper userMapper;

    @Resource
    BlockMapper blockMapper;

    @Resource
    CommonMeterMapper commonMeterMapper;

    @Resource
    AmmeterMapper ammeterMapper;

    @Resource
    WaterMeterMapper waterMeterMapper;

    @Override
    @Transactional
    public ResultData read(InputStream is, boolean isExcel2003, String enprNo, int communityId) {
        Object[] obs = ExcelImportUtil.readSheets(is, isExcel2003);
        List<List<String>>[] data = (List<List<String>>[]) obs[0];
        String[] blockNames = (String[]) obs[1];
        if (data.length != blockNames.length) return Result.error(HttpStatus.BAD_REQUEST, "错误");
        Set<String> userSet = userMapper.findAllUserNo(enprNo);
        for (int k = 0; k < data.length; k++) {
            String blockName = null;
            if (data[k] != null) {
                blockName = blockNames[k];
            }
            List<List<String>> dataList = data[k];
            Block block0 = blockMapper.findByBlockNameAndCid(blockName, communityId);
            /**
             * 不存在则创建楼栋
             */
            if (block0 == null) {
                Block block = new Block();
                block.setbName(blockName);
                block.setcId(communityId);
                try {
                    blockMapper.saveBlock(block);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                Block block = blockMapper.findByBlockNameAndCid(blockName, communityId);
                for (int i = 2; i < dataList.size(); i++) { //循环每一行，即对应单个用户，sheet表的第一行是填写注释，所以从1开始
                    /**
                     * 插入User表
                     */
                    List<String> cellList = dataList.get(i);
                    if (StringUtils.isNotEmpty(cellList.get(0)) && StringUtils.isNotEmpty(cellList.get(7))) {
                        if (!userSet.contains(cellList.get(0))) {
                            User user = new User();
                            user.setuName("userName");
                            user.setbId(block.getbId());
                            user.setuNo(1);
                            user.setEnprNo(enprNo);
                            user.setAccountBalance(new BigDecimal("0"));
                            userSet.add(cellList.get(0));
                            try {
                                userMapper.saveUser(user);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        /**
                         * 插入水表
                         */
                        Watermeter device = waterMeterMapper.getWaterMeterDetail(cellList.get(6), enprNo);
                        if (device == null) {
                            int uid = userMapper.findUidByUnoAndEnprno(cellList.get(0), enprNo);
                            Watermeter meter = new Watermeter();
                            meter.setuId(uid);
                            meter.setMeterNo(cellList.get(6));
                            meter.setInstallTime(System.currentTimeMillis() / 1000);
                            meter.setReadTime(System.currentTimeMillis() / 1000);
                            meter.setReadValue(BigDecimal.ZERO);
                            try {
                                waterMeterMapper.saveMeter(meter);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return Result.error(HttpStatus.INTERNAL_SERVER_ERROR, "导入过程中失败");
            }
        }
        return Result.success("导入成功");
    }

    @Override
    public ResultData check(InputStream is, boolean isExcel2003, String enprNo, int communityId) {
        Object[] obs = ExcelImportUtil.readSheets(is, isExcel2003);
        List<List<String>>[] data = (List<List<String>>[]) obs[0];
        try {
            //查询当前公司所有表编号
            Set<String> meterNoSet = waterMeterMapper.findAllMeterNoByEnprNo(enprNo);
            StringBuffer errstr = new StringBuffer();
            for (int i = 0; i < data.length; i++) {
                List<List<String>> list = data[i];
                for (int k = 2; k < list.size(); k++) {
                    List<String> cellList = list.get(k);

                }
            }
            if ("".equals(errstr.toString())) {
                return Result.success("资料符合要求");
            } else {
                return Result.error(HttpStatus.BAD_REQUEST, errstr.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(HttpStatus.INTERNAL_SERVER_ERROR, "内部错误");
        }
    }
}
