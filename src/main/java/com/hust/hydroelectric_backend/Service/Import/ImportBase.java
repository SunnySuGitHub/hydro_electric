package com.hust.hydroelectric_backend.Service.Import;

import com.hust.hydroelectric_backend.utils.ExcelImportUtil;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/19 10:57
 */
public abstract class ImportBase {

    public ResultData checkAndRead(HttpServletRequest request){
        MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
        MultipartFile file = params.getFile("file");
        String enprNo = params.getParameter("enprNo");
        int communityId = Integer.parseInt(params.getParameter("communityId"));
        int check = Integer.parseInt(params.getParameter("check"));
        boolean isExcel2003 = true;
        String fileName = file.getOriginalFilename();
        if (ExcelImportUtil.isExcel2007(fileName)) {
            isExcel2003 = false;
        }
        InputStream is = null;
        try {
            is = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(check == 1){ //代表检测
            return check(is, isExcel2003, enprNo, communityId);
        } else {
            return read(is, isExcel2003, enprNo, communityId);
        }
    }

    public abstract ResultData read(InputStream is, boolean isExcel2003, String enprNo, int communityId);
    public abstract ResultData check(InputStream is, boolean isExcel2003, String enprNo, int communityId);
}
