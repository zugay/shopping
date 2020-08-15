package com.gec.shopping.controller;


import com.gec.shopping.pojo.RespBean;
import com.gec.shopping.pojo.ResultPage;
import com.gec.shopping.pojo.TbItemCat;
import com.gec.shopping.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itemCat-ms")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @GetMapping("/findAllTbItemCat")
    public List<TbItemCat> findAllTbItemCat(){
        System.out.println("返回findAllTbItemCat()");
        System.out.println(itemCatService.findAllTbItemCat().size());
        return itemCatService.findAllTbItemCat();
    }

    @GetMapping("/findPage")
    public ResultPage  findPage(int page,int rows){
        return itemCatService.findPage(page, rows);
    }

    @PostMapping("/add")
    public RespBean add(@RequestBody TbItemCat itemCat) {

        try {
            System.out.println("itemCat.getName:"+itemCat.getName());
            itemCatService.add(itemCat);
             return new RespBean(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
             return new RespBean(false, "删除失败");
        }

    }


    @PostMapping("/update")
    public RespBean update(@RequestBody TbItemCat itemCat) {

        try {
            System.out.println("itemCat.getName:"+itemCat.getName());
            itemCatService.update(itemCat);
             return new RespBean(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RespBean(false, "删除失败");
        }

    }


    @GetMapping("/findOne/{id}")
    public TbItemCat findOne(@PathVariable Long id) {
        System.out.println("----单个查询");
        System.out.println("findOne id="+id);
        return itemCatService.findOne(id);
    }


    @RequestMapping(value="/delete",method=RequestMethod.GET)
    public RespBean delete(Long[] ids) {

        try {
            System.out.println("id[0]:"+ids[0]);
            itemCatService.delete(ids);
            return new RespBean(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RespBean(false, "删除失败");
        }

    }

    @RequestMapping("/search")
    public ResultPage search(@RequestBody TbItemCat itemCat, int pageNum, int pageSize  ){
        return itemCatService.findPage(itemCat, pageNum, pageSize);
    }


    //根据父ID查询分类的方法
    @GetMapping("/findByParentId")
    public List<TbItemCat> findByParentId(Long parentId){
        return itemCatService.findByParentId(parentId);
    }
}
