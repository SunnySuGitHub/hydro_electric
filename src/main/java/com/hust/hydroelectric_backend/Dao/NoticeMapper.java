package com.hust.hydroelectric_backend.Dao;

import com.hust.hydroelectric_backend.Entity.Notice;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/27 21:47
 */
@Repository
public interface NoticeMapper {
    int addNotice(Notice notice);
    List<Notice> noticeList(String enprNo);
    int uptNotice(Notice notice);
}
