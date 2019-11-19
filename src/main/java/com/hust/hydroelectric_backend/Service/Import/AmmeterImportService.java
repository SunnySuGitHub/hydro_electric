package com.hust.hydroelectric_backend.Service.Import;

import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 11:05
 * 电表导入相关操作
 */
@Service
public class AmmeterImportService extends ImportBase {

    @Override
    public ResultData read(InputStream is, boolean isExcel2003, String enprNo, int communityId) {
        return null;
    }

    @Override
    public ResultData check(InputStream is, boolean isExcel2003, String enprNo, int communityId) {
        return null;
    }
}
