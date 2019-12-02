package com.hust.hydroelectric_backend.Service;

import com.alibaba.fastjson.JSONArray;
import com.hust.hydroelectric_backend.Dao.NoticeMapper;
import com.hust.hydroelectric_backend.Entity.Notice;
import com.hust.hydroelectric_backend.utils.JedisUtil;
import com.hust.hydroelectric_backend.utils.result.Result;
import com.hust.hydroelectric_backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    JedisUtil jedisUtil;

    public ResultData addNotice(Notice notice){
        notice.setPublishTime(System.currentTimeMillis()/1000);
        String enprNo = notice.getEnprNo();
        jedisUtil.hDel(enprNo, "NoticeList");
        return Result.success(noticeMapper.addNotice(notice));
    }

    public ResultData noticeList(String enprNo){
        if(jedisUtil.hGet(enprNo, "NoticeList") == null) {
            List<Notice> noticeList = noticeMapper.noticeList(enprNo);
            jedisUtil.hSet(enprNo, "NoticeList", JSONArray.toJSONString(noticeList));
            return Result.success(noticeList);
        } else {
            List<Notice> noticeList = JSONArray.parseArray(jedisUtil.hGet(enprNo, "NoticeList"), Notice.class);
            return Result.success(noticeList);
        }
    }

    public ResultData uptNotice(Notice notice){
        String enprNo = notice.getEnprNo();
        jedisUtil.hDel(enprNo, "NoticeList");
        return Result.success(noticeMapper.uptNotice(notice));
    }

    public ResultData delNotice(int id, String enprNo){
        jedisUtil.hDel(enprNo, "NoticeList");
        return Result.success(noticeMapper.delNotice(id));
    }

}
