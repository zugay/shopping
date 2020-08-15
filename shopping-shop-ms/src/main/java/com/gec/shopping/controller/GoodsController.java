package com.gec.shopping.controller;


import com.gec.shopping.pojo.RespBean;
import com.gec.shopping.pojo.ResultPage;
import com.gec.shopping.pojo.TbGoods;
import com.gec.shopping.pojogroup.Goods;
import com.gec.shopping.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goods-ms")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    // 返回全部列表
    @GetMapping("/findAll")
    public List<TbGoods> findAll(){
        return goodsService.findAll();
    }

    @GetMapping("/findPage")
    public ResultPage  findPage(int pageNum,int pageSize){
        return goodsService.findPage(pageNum, pageSize);
    }

    @RequestMapping("/add")
    public RespBean add(@RequestBody Goods goods){
        System.out.println("----"+goods.getGoods().getGoodsName()+"-----");
        System.out.println("----"+goods.getGoodsDesc().getItemImages());
        System.out.println("----"+goods.getGoodsDesc().getSpecificationItems());
        System.out.println("----#"+goods.getGoodsDesc().getCustomAttributeItems());

        try {
            // 获得商家信息:
            //String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
            String sellerId = "yqtech"; //暂时设定
            goods.getGoods().setSellerId(sellerId);

            goodsService.add(goods);
            return new RespBean(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RespBean(false, "增加失败");
        }
    }



    @RequestMapping("/update")
    public RespBean update(@RequestBody Goods goods){
        // 获得商家信息:
        //String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
        String sellerId = "yqtech"; //暂时设定
        Goods goods2 = goodsService.findOne(goods.getGoods().getId());
        if(!sellerId.equals(goods2.getGoods().getSellerId()) || !sellerId.equals(goods.getGoods().getSellerId())){
            return new RespBean(false, "非法操作");
        }

        try {
            goodsService.update(goods);
            return new RespBean(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RespBean(false, "修改失败");
        }
    }

    @GetMapping("/findOne/{id}")
    public Goods findOne(@PathVariable Long id){
        return goodsService.findOne(id);
    }


    @GetMapping("/delete")
    public RespBean delete(Long [] ids){
        try {
            goodsService.delete(ids);
            return new RespBean(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RespBean(false, "删除失败");
        }
    }

    //查询+分页
    @RequestMapping("/search")
    public ResultPage search(@RequestBody TbGoods goods, int pageNum, int pageSize  ){

        //String sellerId = SecurityContextHolder.getContext().getAuthentication().getName();
        String sellerId = "yqtech"; //暂时设定

        goods.setSellerId(sellerId);

        return goodsService.findPage(goods, pageNum, pageSize);
    }
}

