package com.gec.shopping.pojo;

import java.util.List;

/**
 * angular前端 分页插件的响应对象
 */
public class ResultPage {

    private long total;//总记录数
    private List rows;//当前页记录

    public ResultPage() {

    }

    public ResultPage(long total, List rows) {
        super();
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }
    public void setTotal(long total) {
        this.total = total;
    }
    public List getRows() {
        return rows;
    }
    public void setRows(List rows) {
        this.rows = rows;
    }
}
