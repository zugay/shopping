package com.gec.shopping.service.impl;

import com.alibaba.fastjson.JSON;
import com.gec.shopping.mapper.TbSpecificationOptionMapper;
import com.gec.shopping.mapper.TbTypeTemplateMapper;
import com.gec.shopping.pojo.TbSpecificationOption;
import com.gec.shopping.pojo.TbSpecificationOptionExample;
import com.gec.shopping.pojo.TbTypeTemplate;
import com.gec.shopping.pojo.TbTypeTemplateExample;
import com.gec.shopping.service.TypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;



@Service
public class TypeTemplateServiceimpl implements TypeTemplateService {

    @Autowired
    private TbTypeTemplateMapper tbTypeTemplateMapper;

    @Autowired
    private TbSpecificationOptionMapper tbSpecificationOptionMapper;
    @Override
    public List<TbTypeTemplate> findAll() {
        return tbTypeTemplateMapper.selectByExample(null);
    }

    @Override
    public TbTypeTemplate findById(Long id) {
        return tbTypeTemplateMapper.selectByPrimaryKey(id);
    }

    @Override
    public void add(TbTypeTemplate tbTypeTemplate) {
        tbTypeTemplateMapper.insert(tbTypeTemplate);

    }

    @Override
    public void update(TbTypeTemplate tbTypeTemplate) {
        tbTypeTemplateMapper.updateByPrimaryKey(tbTypeTemplate);

    }

    @Override
    public List<TbTypeTemplate> search(TbTypeTemplate tbTypeTemplate) {
        TbTypeTemplateExample example = new TbTypeTemplateExample();
        TbTypeTemplateExample.Criteria criteria = example.createCriteria();

        if(tbTypeTemplate!=null){

            if (tbTypeTemplate.getId() != null) {
                criteria.andIdEqualTo(tbTypeTemplate.getId());
            }
            if(tbTypeTemplate.getName()!=null && tbTypeTemplate.getName().length()>0){
                criteria.andNameLike("%"+tbTypeTemplate.getName()+"%");
            }
        }

        List<TbTypeTemplate> list = tbTypeTemplateMapper.selectByExample(example);

        return list;
    }

    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            tbTypeTemplateMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public List<Map> findSpecList(Long id) {
        //根据ID查询到模板对象
        TbTypeTemplate typeTemplate = tbTypeTemplateMapper.selectByPrimaryKey(id);
        // 获得规格的数据spec_ids
        String specIds = typeTemplate.getSpecIds();// [{"id":27,"text":"网络"},{"id":32,"text":"机身内存"}]
        // 将specIds的字符串转成JSON的List<Map>
        List<Map> list = JSON.parseArray(specIds, Map.class);
        // 获得每条记录:
        for (Map map : list) {
            // 根据规格的ID 查询规格选项的数据:
            // 设置查询条件:
            TbSpecificationOptionExample example = new TbSpecificationOptionExample();
            TbSpecificationOptionExample.Criteria criteria = example.createCriteria();
            criteria.andSpecIdEqualTo(new Long((Integer)map.get("id")));

            List<TbSpecificationOption> specOptionList = tbSpecificationOptionMapper.selectByExample(example);
            //查询tb_specification_oprion表，加载option_name字段，然后封装到map中
            map.put("options", specOptionList);//{"id":27,"text":"网络",options:[{id：xxx,optionName:移动2G}]}
        }
        return list;
    }
}
