package com.gec.shopping.service.impl;

import com.gec.shopping.mapper.TbItemCatMapper;
import com.gec.shopping.pojo.ResultPage;
import com.gec.shopping.pojo.TbItemCat;
import com.gec.shopping.pojo.TbItemCatExample;
import com.gec.shopping.service.ItemCatService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemCatServiceimpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    @Override
    public List<TbItemCat> findAllTbItemCat() {
        return itemCatMapper.selectByExample(null);
    }

    @Override
    public ResultPage findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbItemCat> page=   (Page<TbItemCat>) itemCatMapper.selectByExample(null);
        return new ResultPage(page.getTotal(), page.getResult());
    }

    @Override
    public void add(TbItemCat itemCat) {
        itemCatMapper.insert(itemCat);
    }

    @Override
    public void update(TbItemCat itemCat) {
        itemCatMapper.updateByPrimaryKey(itemCat);
    }

    @Override
    public TbItemCat findOne(Long id) {
        return itemCatMapper.selectByPrimaryKey(id);
    }

    @Override
    public void delete(Long[] ids) {
        for(Long id:ids){
            itemCatMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public ResultPage findPage(TbItemCat itemCat, int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);

        TbItemCatExample example=new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();

        if(itemCat!=null){
            if(itemCat.getName()!=null && itemCat.getName().length()>0){
                criteria.andNameLike("%"+itemCat.getName()+"%");
            }

        }

        Page<TbItemCat> page= (Page<TbItemCat>)itemCatMapper.selectByExample(example);
        return new ResultPage(page.getTotal(), page.getResult());
    }

    //根据父ID查询分类的方法
    @Override
    public List<TbItemCat> findByParentId(Long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        // 设置条件:
        criteria.andParentIdEqualTo(parentId);
        // 条件查询
        return itemCatMapper.selectByExample(example);
    }
}
