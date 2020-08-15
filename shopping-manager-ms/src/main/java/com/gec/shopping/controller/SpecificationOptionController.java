package com.gec.shopping.controller;


import com.gec.shopping.pojo.RespBean;
import com.gec.shopping.pojo.TbSpecificationOption;
import com.gec.shopping.service.SpecificationOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SpecificationOptionController {

    @Autowired
    private SpecificationOptionService specificationOptionService;

      //查询所有品牌
    @RequestMapping("/findAll")
    public List<TbSpecificationOption> findAll() {
        List<TbSpecificationOption> list = specificationOptionService.findAll();
        //  System.out.println(list);
        return list;

    }

    @GetMapping("/findById/{id}")
    public TbSpecificationOption findById(@PathVariable Long id) {
        return specificationOptionService.findById(id);
    }

    @RequestMapping("/add")
    public RespBean add(@RequestBody TbSpecificationOption tbSpecificationOption) {

        try {
            System.out.println("tbBrand id:" + tbSpecificationOption.getId());
            specificationOptionService.add(tbSpecificationOption);
            return new  RespBean(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RespBean(true,"添加失败");
        }
    }

    @RequestMapping("/update")
    public RespBean update(@RequestBody TbSpecificationOption tbSpecificationOption) {
        try {
            System.out.println("tbSpecificationOption"+tbSpecificationOption);
            specificationOptionService.update(tbSpecificationOption);

            return new  RespBean(true,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RespBean(true,"修改失败");
        }
    }

    @RequestMapping("/delete")
    public RespBean delete(Long[] ids) {
        try {
            System.out.println("tbSpecificationOption id:" + ids[0]);
            specificationOptionService.delete(ids);
            return new  RespBean(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RespBean(true,"删除失败");
        }
    }

}
