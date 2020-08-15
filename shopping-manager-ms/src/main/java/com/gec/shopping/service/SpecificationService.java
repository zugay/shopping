package com.gec.shopping.service;

import com.gec.shopping.pojo.RespBean;

import com.gec.shopping.pojo.Specification;
import com.gec.shopping.pojo.TbSpecification;

import java.util.List;
import java.util.Map;

public interface SpecificationService {


    public List<TbSpecification> findAll();

    public Specification findById(Long id);

    public void add(Specification specification);

    public void update(Specification specification);

    public List<TbSpecification> search(TbSpecification specification);


    public  void delete(Long[] ids);

    public List<Map> selectOptionList();
}
