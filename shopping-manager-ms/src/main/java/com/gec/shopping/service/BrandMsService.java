package com.gec.shopping.service;

import com.gec.shopping.pojo.ResultPage;
import com.gec.shopping.pojo.TbBrand;

import java.util.List;
import java.util.Map;

public interface BrandMsService {

    public List<TbBrand> findAll();

    public TbBrand findBrandById(Long id);

    public void updateBrand(TbBrand tbBrand);

    public ResultPage findPage(int pageNum, int pageSize);

    public void add(TbBrand tbBrand);

    public  void delete(Long[] ids);

    public List<Map> selectOptionList();


}
