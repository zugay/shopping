package com.gec.shopping.controller;

import com.gec.shopping.pojo.RespBean;
import com.gec.shopping.pojo.ResultPage;
import com.gec.shopping.pojo.TbBrand;
import com.gec.shopping.service.BrandMsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/brand-Ms")
public class BrandMsController {

    @Autowired
    private  BrandMsService brandMsService;

   //查询所有品牌
    @RequestMapping("/findAll")
    public List<TbBrand> findAll() {
        List<TbBrand> list = brandMsService.findAll();
        //  System.out.println(list);
        return list;

    }

   @GetMapping("/findBrandById/{id}")
    public TbBrand findBrandById(@PathVariable Long id) {
        return brandMsService.findBrandById(id);
    }

    @RequestMapping("/add")
    public RespBean add(@RequestBody TbBrand tbBrand) {

        try {
            System.out.println("tbBrand id:" + tbBrand.getId());
            brandMsService.add(tbBrand);
            return new  RespBean(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RespBean(true,"添加失败");
        }
    }

    @RequestMapping("/updateBrand")
    public RespBean updateBrand(@RequestBody TbBrand tbBrand) {
        try {
            System.out.println("tbBrand.getName"+tbBrand.getName());
            brandMsService.updateBrand(tbBrand);

            return new  RespBean(true,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RespBean(true,"修改失败");
        }
    }

    @RequestMapping("/delete")
    public RespBean delete(Long[] ids) {
        try {
            System.out.println("tbBrand id:" + ids[0]);
            brandMsService.delete(ids);
            return new  RespBean(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RespBean(true,"删除失败");
        }
    }
    @GetMapping("/findByPage")
    public ResultPage findByPage(int pageNum, int pageSize) {
        System.out.println("findByPage--------------->");

        System.out.println("页码：" +  pageNum);
        System.out.println("每页记录数：" + pageSize);
        PageHelper.startPage(pageNum,pageSize);

        Page<TbBrand> page = (Page<TbBrand>) brandMsService.findAll();
        ResultPage resultPage = new ResultPage();
        resultPage.setRows(page.getResult());
        resultPage.setTotal(page.getTotal());

        return resultPage;
    }

    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList(){
        System.out.println(brandMsService.selectOptionList());
        return brandMsService.selectOptionList();

    }
}



