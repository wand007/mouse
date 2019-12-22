package com.mouse.core.utils;


import java.util.List;

/**
 * @author ; lidongdong
 * @Description 分页工具
 * @Date 2019-12-21
 */
public class PageNation<T> {

    private final int DEFAULT_PAGE_SIZE = 10;

    private int pageNum;
    private int pageSize = DEFAULT_PAGE_SIZE;
    private long totalCount;
    private int totalPageCount;
    private List<T> list;

    public PageNation(int pageNum, int pageSize, long totalCount, int totalPageCount, List<T> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPageCount = totalPageCount;
        this.list = list;
    }

    public static PageNation of(int pageNum, int pageSize, long totalCount, int totalPageCount, List list) {
        return new PageNation(pageNum, pageSize, totalCount, totalPageCount, list);
    }

    PageNation() {
    }

    public int getPageNum() {
        return pageNum;
    }

    public PageNation setPageNum(int pageNum) {
        this.pageNum = pageNum;
        return this;
    }

    public int getPageSize() {
        return pageSize;
    }

    public PageNation setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public PageNation setTotalCount(long totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public PageNation setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
        return this;
    }

    public List<T> getList() {
        return list;
    }

    public PageNation setList(List<T> list) {
        this.list = list;
        return this;
    }
}
