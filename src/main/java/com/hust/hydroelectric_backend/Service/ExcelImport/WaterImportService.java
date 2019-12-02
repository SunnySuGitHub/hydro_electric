package com.hust.hydroelectric_backend.Service.ExcelImport;

import com.hust.hydroelectric_backend.Dao.BlockMapper;
import com.hust.hydroelectric_backend.Dao.UserMapper;
import com.hust.hydroelectric_backend.Dao.WaterMeterMapper;
import com.hust.hydroelectric_backend.Entity.Areas.Block;
import com.hust.hydroelectric_backend.Entity.User;
import com.hust.hydroelectric_backend.Entity.Watermeters.Watermeter;
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
    WaterMeterMapper waterMeterMapper;

    @Override
    @Transactional
    public ResultData read(InputStream is, boolean isExcel2003, String enprNo, int communityId) {
        Object[] obs = ExcelImportUtil.readSheets(is, isExcel2003);
        List<List<String>>[] data = (List<List<String>>[]) obs[0];
        String[] blockNames = (String[]) obs[1];
        if (data.length != blockNames.length) return Result.error(HttpStatus.BAD_REQUEST, "sheet数目错误");
        for (int k = 0; k < data.length; k++) {
            String blockName = blockNames[k];
            List<List<String>> dataList = data[k];
            Block block = blockMapper.findByBlockNameAndCid(blockName, communityId);
            int bid = -1;
            /**
             * 不存在则创建楼栋
             */
            if (block == null) {
                Block curBlock = new Block();
                curBlock.setbName(blockName);
                curBlock.setcId(communityId);
                try {
                    blockMapper.saveBlock(curBlock);
                    bid = curBlock.getbId();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                bid = block.getbId();
            }
            for (int i = 2; i < dataList.size(); i++) {//循环每一行，即对应单个用户
                List<String> cellList = dataList.get(i);
                String uname = cellList.get(0);
                String tel = cellList.get(1);
                int meterType = Integer.valueOf(cellList.get(2));
                String uaddr = cellList.get(4);
                String meterNo = cellList.get(5);
                int caliber = Integer.valueOf(cellList.get(6));
                int valve = Integer.valueOf(cellList.get(7));
                BigDecimal readValue = new BigDecimal(cellList.get(8));
                /**
                 * 插入User表
                 */
                User user = userMapper.findByUnameAndTelAndEnprNo(uname, tel, enprNo);
                int uid = -1;
                if (user == null) {
                    User curUser = new User();
                    curUser.setuName(uname);
                    curUser.setuTel(tel);
                    curUser.setbId(bid);
                    curUser.setAddress(uaddr);
                    curUser.setAccountBalance(BigDecimal.ZERO);
                    curUser.setEnprNo(enprNo);
                    try{
                        userMapper.saveUser(curUser);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    uid = curUser.getuId();
                } else {
                    uid = user.getuId();
                }

                /**
                 * 插入水表
                 */
                Watermeter meter = new Watermeter();
                meter.setMeterNo(meterNo);
                meter.setuId(uid);
                meter.setcId(communityId);
                meter.setCaliber(caliber);
                long curUnixTime = System.currentTimeMillis()/1000;
                meter.setInstallTime(curUnixTime);
                meter.setReadTime(curUnixTime);
                meter.setReadValue(readValue);
                meter.setPreReadTime(curUnixTime);
                meter.setPreReadValue(readValue);
                meter.setMonthAmount(BigDecimal.ZERO);
                meter.setState(0);
                meter.setMeterType(meterType);
                meter.setValve(valve);
                try {
                    waterMeterMapper.saveMeter(meter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return Result.success("导入成功");
    }

    /**
     * 需要检查的有：
     * 各个字段是否存在漏填情况
     * 表编号是否数据库中已存在、导入数据中是否存在重复
     * <p>
     * 在判断导入数据中是否存在时，需要边添加边判断
     */
    @Override
    public ResultData check(InputStream is, boolean isExcel2003, String enprNo, int communityId) {
        Object[] obs = ExcelImportUtil.readSheets(is, isExcel2003);
        List<List<String>>[] data = (List<List<String>>[]) obs[0];
        //查询当前公司所有表编号
        Set<String> meterNoSet = waterMeterMapper.findAllWatermeterNoByEnprNo(enprNo);
        StringBuffer errstr = new StringBuffer();
        for (int i = 0; i < data.length; i++) {         //遍历每一个sheet
            List<List<String>> list = data[i];
            for (int j = 1; j < list.size(); j++) {//遍历sheet的每一行数据
                try {
                    List<String> cellList = list.get(j);
                    boolean hasError = false;
                    if (StringUtils.isBlank(cellList.get(0))) {
                        errstr.append("第" + (i + 1) + "个sheet的第" + j + "个用户" + "用户名为空");
                        hasError = true;
                    }
                    if (StringUtils.isBlank(cellList.get(1))) {
                        errstr.append("第" + (i + 1) + "个sheet的第" + j + "个用户" + "用户电话为空");
                        hasError = true;
                    }
                    if (StringUtils.isBlank(cellList.get(2))) {
                        errstr.append("第" + (i + 1) + "个sheet的第" + j + "个用户" + "水表类型为空");
                        hasError = true;
                    }
                    if (StringUtils.isBlank(cellList.get(4))) {
                        errstr.append("第" + (i + 1) + "个sheet的第" + j + "个用户" + "用户详细地址为空");
                        hasError = true;
                    }
                    if (StringUtils.isBlank(cellList.get(5))) {
                        errstr.append("第" + (i + 1) + "个sheet的第" + j + "个用户" + "表编号为空");
                        hasError = true;
                    }
                    if (StringUtils.isBlank(cellList.get(6))) {
                        errstr.append("第" + (i + 1) + "个sheet的第" + j + "个用户" + "口径为空");
                        hasError = true;
                    }
                    if (StringUtils.isBlank(cellList.get(7))) {
                        errstr.append("第" + (i + 1) + "个sheet的第" + j + "个用户" + "阀门状态为空");
                        hasError = true;
                    }
                    if (StringUtils.isBlank(cellList.get(8))) {
                        errstr.append("第" + (i + 1) + "个sheet的第" + j + "个用户" + "水表初始读数为空");
                        hasError = true;
                    }
                    if (hasError) continue;
                    String meterNo = cellList.get(5);
                    if (meterNoSet.contains(meterNo)) {
                        errstr.append("编号为" + meterNo + "的水表出现重复");
                    } else {
                        meterNoSet.add(meterNo);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.err.println("检测第" + (i + 1) + "个sheet的第" + j + "个用户出现错误");
                }
            }
        }
        if ("".equals(errstr.toString())) {
            return Result.success("资料符合要求");
        } else {
            return Result.error(HttpStatus.BAD_REQUEST, errstr.toString());
        }
    }
}
