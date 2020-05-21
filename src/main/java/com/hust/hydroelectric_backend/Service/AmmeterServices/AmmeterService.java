package com.hust.hydroelectric_backend.Service.AmmeterServices;

import com.hust.hydroelectric_backend.Dao.AmmeterMapper;
import com.hust.hydroelectric_backend.Entity.Ammeters.Ammeter;
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
public class AmmeterService {

    @Resource
    AmmeterMapper ammeterMapper;

    public ResultData getAmmeterDetail(String ammeterNo, String enprNo) {
        return Result.success(ammeterMapper.getAmmeterDetail(ammeterNo, enprNo));
    }

    public ResultData deleteMeter(String meterNo, String enprNo) {
        return Result.success(ammeterMapper.delete(meterNo, enprNo));
    }

    public ResultData getAllAmmeterDetail(String enprNo, int state) {
        List<Ammeter> data = null;
        switch (state) {
            case -1:
                data = ammeterMapper.getAllAmmeterDetail(enprNo, state);
                return Result.success(data);
            case 0:
                data = ammeterMapper.getAllAmmeterDetail(enprNo, state);
                return Result.success(data);
            case 1:
                data = ammeterMapper.getAllAmmeterDetail(enprNo, state);
                return Result.success(data);
            default:
                return Result.success(null);
        }
    }

    public ResultData getAmmeterByUid(int uid) {
        return Result.success(ammeterMapper.findAmmeterByUid(uid));
    }
}
