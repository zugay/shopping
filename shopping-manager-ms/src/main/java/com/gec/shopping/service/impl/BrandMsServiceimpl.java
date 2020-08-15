package com.gec.shopping.service.impl;

import com.gec.shopping.mapper.TbBrandMapper;
import com.gec.shopping.pojo.ResultPage;
import com.gec.shopping.pojo.TbBrand;
import com.gec.shopping.service.BrandMsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;


@Service
public class BrandMsServiceimpl implements BrandMsService {

    @Autowired
    private TbBrandMapper tbBrandMapper;



    @Override
    public List<TbBrand> findAll() {
        return tbBrandMapper.selectByExample(null);
    }


    @Override
    public TbBrand findBrandById(Long id) {
        return tbBrandMapper.selectByPrimaryKey(id);
    }



    @Override
    public void updateBrand(TbBrand tbBrand) {
        tbBrandMapper.updateByPrimaryKeySelective(tbBrand);

    }

    @Override
    public ResultPage findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbBrand> page = (Page<TbBrand>) tbBrandMapper.selectByExample(null);
        return new ResultPage(page.getTotal(), page.getResult());
    }


    @Override
    public void add(TbBrand tbBrand) {
        tbBrandMapper.insert(tbBrand);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            tbBrandMapper.deleteByPrimaryKey(id);
        }

    }

    @Override
    public List<Map> selectOptionList() {
        return tbBrandMapper.selectOptionList();
    }


}
