package com.gec.shopping.service;


import com.gec.shopping.pojo.ResultPage;
import com.gec.shopping.pojo.TbSpecificationOption;

import java.util.List;

public interface SpecificationOptionService {
	public List<TbSpecificationOption> findAll();

	public TbSpecificationOption findById(Long id);

	public void update(TbSpecificationOption specificationOption);

	public ResultPage findPage(int pageNum, int pageSize);

	public void add(TbSpecificationOption specificationOption);

	public  void delete(Long[] ids);


}
