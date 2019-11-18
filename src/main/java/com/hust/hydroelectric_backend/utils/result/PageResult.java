package com.hust.hydroelectric_backend.utils.result;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author: suxinyu
 * @DateTme: 2019/11/18 18:48
 */
public class PageResult<T> {
    private List<T> content;
    private int size;
    private int number;
    private int totalPages;
    private long totalElements;
    private boolean first;
    private boolean last;
    private int numberOfElements;
    private String order;

    public PageResult(Page<T> page) {
        this.content = page.getResult();
        this.size = page.getPageSize();
        this.number = page.getPageNum();
        this.totalPages = page.getPages();
        this.totalElements = page.getTotal();
        this.order = page.getOrderBy();
        this.numberOfElements = this.content.size();
        // 来自 com.github.pagehelper.PageInfo.judgePageBoudary()
        this.first = 1 == page.getPageNum();
        this.last = this.number == this.totalPages || this.totalPages == 0;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<T> getContent() {
        return content;
    }

    public int getSize() {
        return size;
    }

    public int getNumber() {
        return number;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public boolean isFirst() {
        return first;
    }

    public boolean isLast() {
        return last;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
