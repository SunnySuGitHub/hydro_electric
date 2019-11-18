package com.hust.hydroelectric_backend.utils.result;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hust.hydroelectric_backend.Entity.PageQuery;
import org.apache.commons.lang3.StringUtils;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/18 18:48
 */
public class PageUtils {
    public static char COLON = '.';
    public static char SPACE = ' ';

    public static <E> Page<E> startPage(PageQuery query) {
        if(query.isNoPage()){
            return null;
        }
        if (StringUtils.isBlank(query.getOrder())) {
            return PageHelper.startPage(query.getPageNum(), query.getPageSize());
        }
        String order = StringUtils.replaceChars(query.getOrder(), COLON, SPACE);
        return PageHelper.startPage(query.getPageNum(), query.getPageSize(), order);
    }
}
