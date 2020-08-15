package com.gec.shopping.service;

import com.gec.shopping.pojo.ResultPage;
import com.gec.shopping.pojo.TbGoods;
import com.gec.shopping.pojo.TbItem;
import com.gec.shopping.pojogroup.Goods;

import java.util.List;

public interface GoodsService {

	public List<TbGoods> findAll();

	public ResultPage findPage(int pageNum, int pageSize);

	public void add(Goods goods);

	public void update(Goods goods);

	public Goods findOne(Long id);

	public void delete(Long[] ids);

	public ResultPage findPage(TbGoods goods, int pageNum, int pageSize);
	
	public void updateStatus(Long[] ids, String status);

	public List<TbItem> findItemListByGoodsIdListAndStatus(Long[] ids, String status);
	
}
