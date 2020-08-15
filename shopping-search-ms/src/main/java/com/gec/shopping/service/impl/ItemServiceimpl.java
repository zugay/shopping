package com.gec.shopping.service.impl;


import com.gec.shopping.pojo.TbItem;
import com.gec.shopping.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceimpl implements ItemService {

    @Autowired
    private SolrTemplate solrTemplate;



    @Override
    public Map search(Map searchMap) {

        System.out.println("进入ItemSearchServiceImpl");

        Map map=new HashMap();

        Query query=new SimpleQuery("*:*");
        //item_keywords是solr的业务字段
        Criteria criteria=new Criteria("item_keywords").is(searchMap.get("keywords"));
        query.addCriteria(criteria);

        ScoredPage<TbItem> page = solrTemplate.queryForPage("collection1",query, TbItem.class);

        map.put("rows", page.getContent());

        return map;
    }


}
