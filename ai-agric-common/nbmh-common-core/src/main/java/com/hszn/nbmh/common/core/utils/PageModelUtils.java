package com.hszn.nbmh.common.core.utils;

import lombok.Data;

import java.util.List;

@Data
public class PageModelUtils<E> {


    //结果集
    private List<E> list;

    //查询记录数
    private int totalRecords;

    //每页多少条数据
    private int pageSize;

    //第几页
    private int pageNum;

    /**
     * 总页数
     *
     * @return
     */
    public int getTotalPages() {
        return (totalRecords + pageSize - 1) / pageSize;
    }

    /**
     * 取得首页
     *
     * @return
     */
    public int getTopPageNo() {
        return 1;
    }

    /**
     * 上一页
     *
     * @return
     */
    public int getPreviousPageNo() {
        if (pageNum <= 1) {
            return 1;
        }
        return pageNum - 1;
    }

    /**
     * 下一页
     *
     * @return
     */
    public int getNextPageNo() {
        if (pageNum >= getBottomPageNo()) {
            return getBottomPageNo();
        }
        return pageNum + 1;
    }

    /**
     * 取得尾页
     *
     * @return
     */
    public int getBottomPageNo() {
        return getTotalPages();
    }
}