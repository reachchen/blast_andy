package com.yhbc.base.common.utils;

import java.util.List;

/**
 * 计算页数工具类
 * Created by xuhaijiang on 16/4/19.
 */
public class PageUtils {
    public static final int FIRST = 1;
    private int page = 1;
    private int pageSize =20 ;//每页多少条  默认十条

    public PageUtils() {
//        this.list = list;
    }

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

//    public void setPage(int page) {
//        this.page = page;
//    }

    public boolean hasMore(List<?> list) {
        return null != list && list.size() >= pageSize;
    }

    /**
     * 更具数据条数判断当前页数 <br/>
     * author xuhaijaing
     * created at 16/4/19 下午4:39
     */
    public void setListPage(int listCount) {
        int chu = listCount / pageSize + FIRST;
        //是否有余数
        boolean isYu = listCount % pageSize != 0;
        page = chu + (isYu ? 1 : 0);
    }
}
