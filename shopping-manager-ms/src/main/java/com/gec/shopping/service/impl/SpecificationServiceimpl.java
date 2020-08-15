package com.gec.shopping.service.impl;

import com.gec.shopping.mapper.TbSpecificationMapper;
import com.gec.shopping.mapper.TbSpecificationOptionMapper;
import com.gec.shopping.pojo.*;
import com.gec.shopping.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SpecificationServiceimpl implements SpecificationService {

    @Autowired
    private TbSpecificationMapper tbSpecificationMapper;

    @Autowired
    private TbSpecificationOptionMapper tbSpecificationOptionMapper;
    @Override
    public List<TbSpecification> findAll() {
        return tbSpecificationMapper.selectByExample(null);
    }

    @Override
    public Specification findById(Long id) {
        Specification specification = new Specification();
        // 根据规格ID查询规格对象
        TbSpecification tbSpecification = tbSpecificationMapper.selectByPrimaryKey(id);
        specification.setSpecification(tbSpecification);

        // 根据规格的ID查询规格选项
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(id);

        List<TbSpecificationOption> list = tbSpecificationOptionMapper.selectByExample(example);
        specification.setSpecificationOptionList(list);

        return specification;
    }

    @Override
    public void add(Specification specification) {
        //保存规格 一方的数据
        tbSpecificationMapper.insert(specification.getSpecification());
        //保存规格选项 多方的数据
        for(TbSpecificationOption specificationOption: specification.getSpecificationOptionList()){
            // 设置规格的ID:  主键回填
            specificationOption.setSpecId(specification.getSpecification().getId());

            tbSpecificationOptionMapper.insert(specificationOption);
        }

    }

    @Override
    public void update(Specification specification) {
        //修改规格 一方
        tbSpecificationMapper.updateByPrimaryKey(specification.getSpecification());
        // 先删除规格选项，再添加规格选项
        TbSpecificationOptionExample example = new TbSpecificationOptionExample();
        TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
        criteria.andSpecIdEqualTo(specification.getSpecification().getId());

        tbSpecificationOptionMapper.deleteByExample(example);
        // 保存规格选项
        for(TbSpecificationOption specificationOption: specification.getSpecificationOptionList()){
            // 设置规格的ID:
            specificationOption.setSpecId(specification.getSpecification().getId());

            tbSpecificationOptionMapper.insert(specificationOption);
        }

    }



    @Override
    public List<TbSpecification> search(TbSpecification specification) {

        TbSpecificationExample example=new TbSpecificationExample();
        TbSpecificationExample.Criteria criteria = example.createCriteria();

        if(specification!=null){

            if (specification.getId() != null) {
                criteria.andIdEqualTo(specification.getId());
            }
            if(specification.getSpecName()!=null && specification.getSpecName().length()>0){
                criteria.andSpecNameLike("%"+specification.getSpecName()+"%");
            }
        }

        List<TbSpecification> list = tbSpecificationMapper.selectByExample(example);
        return list;
    }




    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            tbSpecificationMapper.deleteByPrimaryKey(id);

            TbSpecificationOptionExample example = new TbSpecificationOptionExample();
            TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo(id);
            tbSpecificationOptionMapper.deleteByExample(example);
        }
    }

    @Override
    public List<Map> selectOptionList() {
        return tbSpecificationMapper.selectOptionList();
    }
}
