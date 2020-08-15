package com.gec.shopping.service;

import com.gec.shopping.pojo.ResultPage;
import com.gec.shopping.pojo.TbContent;
import com.gec.shopping.pojo.TbContentCategory;

import java.util.List;

public interface ContentCategoryService {

    public List<TbContentCategory> findAll ();

    public TbContentCategory findById(Long id);

    public void update(TbContentCategory tbContentCategory);



    public void add(TbContentCategory tbContentCategory);



    public  void delete(Long[] ids);
}
