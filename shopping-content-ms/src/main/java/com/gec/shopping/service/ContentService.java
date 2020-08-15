package com.gec.shopping.service;

import com.gec.shopping.pojo.ResultPage;
import com.gec.shopping.pojo.TbContent;

import java.util.List;

public interface ContentService {

    public List<TbContent> findAll ();

    public TbContent findById(Long id);

    public void update(TbContent tbContent);

    public ResultPage findPage(int pageNum, int pageSize);

    public void add(TbContent tbContent);



    public  void delete(Long[] ids);

    public List<TbContent> findByCategoryId(Long categoryId);
}
