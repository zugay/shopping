package com.gec.shopping.service;

import com.gec.shopping.pojo.Specification;
import com.gec.shopping.pojo.TbSpecification;
import com.gec.shopping.pojo.TbTypeTemplate;

import java.util.List;
import java.util.Map;

public interface TypeTemplateService {

    public List<TbTypeTemplate> findAll();

    public TbTypeTemplate findById(Long id);

    public void add(TbTypeTemplate tbTypeTemplate);

    public void update(TbTypeTemplate tbTypeTemplate);

    public List<TbTypeTemplate> search(TbTypeTemplate tbTypeTemplate);

    public void delete(Long[] ids);

    public List<Map> findSpecList(Long id);
}
