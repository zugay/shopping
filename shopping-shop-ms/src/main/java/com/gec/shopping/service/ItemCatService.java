package com.gec.shopping.service;

import com.gec.shopping.pojo.ResultPage;
import com.gec.shopping.pojo.TbItem;
import com.gec.shopping.pojo.TbItemCat;

import java.util.List;

public interface ItemCatService {
    //返回全部列表
    public List<TbItemCat> findAllTbItemCat();

    //返回分页列表
    public ResultPage findPage(int pageNum,int pageSize);

    public void add(TbItemCat itemCat);

    public void update(TbItemCat itemCat);

    public TbItemCat findOne(Long id);

    //批量删除
    public void delete(Long [] ids);

    //分页
    public ResultPage findPage(TbItemCat itemCat, int pageNum,int pageSize);

    //根据父ID查询分类的方法
    public List<TbItemCat> findByParentId(Long parentId);

}
