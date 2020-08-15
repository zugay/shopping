package com.gec.shopping.service.impl;

import com.gec.shopping.mapper.TbContentCategoryMapper;
import com.gec.shopping.pojo.TbContent;
import com.gec.shopping.pojo.TbContentCategory;
import com.gec.shopping.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Service
public class ContentCategoryServiceimpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;
    @Override
    public List<TbContentCategory> findAll() {
        return tbContentCategoryMapper.selectByExample(null);
    }

    @Override
    public TbContentCategory findById(Long id) {
        return tbContentCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(TbContentCategory tbContentCategory) {
        tbContentCategoryMapper.updateByPrimaryKey(tbContentCategory);
    }

    @Override
    public void add(TbContentCategory tbContentCategory) {
        tbContentCategoryMapper.insert(tbContentCategory);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            tbContentCategoryMapper.deleteByPrimaryKey(id);
        }
    }
}
