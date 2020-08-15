package com.gec.shopping.service;
import java.util.List;

import com.gec.shopping.pojo.ResultPage;
import com.gec.shopping.pojo.TbGoodsDesc;

public interface GoodsDescService {

	public List<TbGoodsDesc> findAll();

	public ResultPage findPage(int pageNum, int pageSize);
	
	public void add(TbGoodsDesc goodsDesc);
	
	public void update(TbGoodsDesc goodsDesc);
	
	public TbGoodsDesc findOne(Long id);
	
	public void delete(Long[] ids);

	public ResultPage findPage(TbGoodsDesc goodsDesc, int pageNum, int pageSize);
	
	
}
