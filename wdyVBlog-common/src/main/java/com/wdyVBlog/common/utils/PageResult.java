package com.wdyVBlog.common.utils;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.Collections;
import java.util.List;

/**
 * PageResult
 *
 * @author: jason-liu
 */
@Data
public class PageResult<T>{
    private long total;
    private long pageSize;
    private long currPage;
    private long pages;
    private List<T> records = Collections.emptyList();

    public PageResult() {
    }

    public PageResult(Page page, List<T> collect) {
        this.records = collect;
        this.total = page.getTotal();
        this.pageSize = page.getSize();
        this.currPage = page.getCurrent();
        this.pages = page.getPages();
    }

    public PageResult(Page page) {
        this.records = page.getRecords();
        this.total = page.getTotal();
        this.pageSize = page.getSize();
        this.currPage = page.getCurrent();
        this.pages = page.getPages();
    }

    public PageResult(Long total, Long pageSize, Long currPage, List<T> collect) {
        this.records = collect;
        this.total = total;
        this.pageSize = pageSize;
        this.currPage = currPage;
        this.pages = total==0? 0 : total % pageSize == 0? (total /pageSize) : (total/pageSize+1);
    }

}
