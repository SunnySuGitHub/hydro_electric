package com.hust.hydroelectric_backend.Service;

import com.hust.hydroelectric_backend.Dao.NoticeMapper;
import com.hust.hydroelectric_backend.Entity.Notice;
import com.hust.hydroelectric_backend.utils.result.PageQuery;
import com.hust.hydroelectric_backend.utils.result.PageUtils;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/27 21:31
 */
@Service
public class NoticeService {

    @Resource
    NoticeMapper noticeMapper;

    public ResultData addNotice(Notice notice) {
        notice.setPublishTime(System.currentTimeMillis() / 1000);
        String enprNo = notice.getEnprNo();
        return Result.success(noticeMapper.addNotice(notice));
    }

    public ResultData noticeList(String enprNo, int pagNum, int pageSize) {
        PageUtils.startPage(new PageQuery(pagNum, pageSize));
        return Result.success(noticeMapper.noticeList(enprNo));
    }

    public ResultData uptNotice(Notice notice) {
        return Result.success(noticeMapper.uptNotice(notice));
    }

    public ResultData delNotice(int id) {
        return Result.success(noticeMapper.delNotice(id));
    }

}
