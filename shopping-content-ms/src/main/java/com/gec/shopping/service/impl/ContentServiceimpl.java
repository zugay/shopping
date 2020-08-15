package com.gec.shopping.service.impl;

import com.gec.shopping.mapper.TbContentMapper;
import com.gec.shopping.pojo.*;
import com.gec.shopping.service.ContentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentServiceimpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    @Autowired
     private RedisTemplate redisTemplate;
    @Override
    public List<TbContent> findAll() {
        return tbContentMapper.selectByExample(null);
    }

    @Override
    public TbContent findById(Long id) {
        return tbContentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void update(TbContent tbContent) {
        TbContent tbContent1 = tbContentMapper.selectByPrimaryKey(tbContent.getId());

        //清除之前分类的广告缓存
        redisTemplate.boundHashOps("tbContent").delete(tbContent1.getCategoryId());

        tbContentMapper.updateByPrimaryKey(tbContent);
        //清除缓存
        if ( tbContent.getCategoryId() != tbContent1.getCategoryId()){
            redisTemplate.boundHashOps("tbContent").delete(tbContent.getCategoryId());
        }

    }

    @Override
    public ResultPage findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbContent> page = (Page<TbContent>) tbContentMapper.selectByExample(null);
        return new ResultPage(page.getTotal(), page.getResult());
    }

    @Override
    public void add(TbContent tbContent) {
        tbContentMapper.insert(tbContent);

        redisTemplate.boundHashOps("tbContent").delete(tbContent.getCategoryId());

    }


    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            TbContent tbContent1 = tbContentMapper.selectByPrimaryKey(id);

            //清除之前分类的广告缓存
            redisTemplate.boundHashOps("tbContent").delete(tbContent1.getCategoryId());

            tbContentMapper.deleteByPrimaryKey(id);
        }

    }

    @Override
    public List<TbContent> findByCategoryId(Long categoryId) {
        List<TbContent> list = (List<TbContent>) redisTemplate.boundHashOps("tbContent").get(categoryId);
       if (list == null){
           System.out.println("查询数据库=======");
           TbContentExample example = new TbContentExample();
           TbContentExample.Criteria criteria = example.createCriteria();
           criteria.andStatusEqualTo("1");

           criteria.andCategoryIdEqualTo(categoryId);

           //排序
           example.setOrderByClause("sort_order");

        list = tbContentMapper.selectByExample(example);

        redisTemplate.boundHashOps("").put(categoryId,list);
       }else {
           System.out.println("");
       }
       return list;
    }

}
