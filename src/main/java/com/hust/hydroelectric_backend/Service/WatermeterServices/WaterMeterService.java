package com.hust.hydroelectric_backend.Service.WatermeterServices;

import com.hust.hydroelectric_backend.Dao.WaterMeterMapper;
import com.hust.hydroelectric_backend.utils.result.PageQuery;
import com.hust.hydroelectric_backend.Entity.Watermeters.Watermeter;
import com.hust.hydroelectric_backend.utils.result.PageUtils;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 20:05
 */
@Service
public class WaterMeterService {

    @Resource
    WaterMeterMapper waterMeterMapper;

    public ResultData getWaterMeterDetail(String meterNo, String enprNo) {
        return Result.success(waterMeterMapper.getWaterMeterDetail(meterNo, enprNo));
    }

    public ResultData deleteMeter(String meterNo, String enprNo) {
        return Result.success(waterMeterMapper.delete(meterNo, enprNo));
    }

    public ResultData getAllWaterMeterDetail(String enprNo, int state, int pageNum, int pageSize) {
        // 分页查询
        PageUtils.startPage(new PageQuery(pageNum, pageSize));
        List<Watermeter> data = null;
        switch (state) {
            case -1:
                data = waterMeterMapper.getAllWaterMeterDetail(enprNo, state);
                return Result.success(data);
            case 0:
                data = waterMeterMapper.getAllWaterMeterDetail(enprNo, state);
                return Result.success(data);
            case 1:
                data = waterMeterMapper.getAllWaterMeterDetail(enprNo, state);
                return Result.success(data);
            default:
                return Result.success(null);
        }
    }

    public ResultData getWatermeterByUid(int uid) {
        return Result.success(waterMeterMapper.findWatermeterByUid(uid));
    }
}
