package com.gec.shopping.service.impl;

import com.gec.shopping.mapper.TbSpecificationOptionMapper;
import com.gec.shopping.pojo.ResultPage;
import com.gec.shopping.pojo.TbSpecificationOption;
import com.gec.shopping.service.SpecificationOptionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationOptionServiceimpl implements SpecificationOptionService {

   @Autowired
   private TbSpecificationOptionMapper tbSpecificationOptionMapper;

    @Override
    public List<TbSpecificationOption> findAll() {
        return tbSpecificationOptionMapper.selectByExample(null);
    }

    @Override
    public TbSpecificationOption findById(Long id) {
        return tbSpecificationOptionMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(TbSpecificationOption specificationOption) {
          tbSpecificationOptionMapper.updateByPrimaryKey(specificationOption);
    }

    @Override
    public ResultPage findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbSpecificationOption> page = (Page<TbSpecificationOption>) tbSpecificationOptionMapper.selectByExample(null);
        return new ResultPage(page.getTotal(), page.getResult());
    }

    @Override
    public void add(TbSpecificationOption specificationOption) {
        tbSpecificationOptionMapper.insert(specificationOption);
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            tbSpecificationOptionMapper.deleteByPrimaryKey(id);
        }
    }
}
