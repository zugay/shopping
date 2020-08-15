package com.gec.shopping.controller;


import com.gec.shopping.pojo.*;
import com.gec.shopping.service.SpecificationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/specification-Ms")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    //查询所有品牌
    @RequestMapping("/findAll")
    public List<TbSpecification> findAll() {
        List<TbSpecification> list = specificationService.findAll();
          System.out.println(list);
        return list;

    }

    @GetMapping("/findById/{id}")
    public Specification findById(@PathVariable Long id) {
        System.out.println(id);
        return specificationService.findById(id);
    }

    @PostMapping("/add")
    public RespBean add(@RequestBody Specification specification) {
        try {
            System.out.println("tbSpecification id:" + specification.getSpecification().getId());
            specificationService.add(specification);
            return new  RespBean(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RespBean(true,"添加失败");
        }
    }

    @RequestMapping("/update")
    public RespBean updateBrand(@RequestBody Specification specification) {
        try {
            System.out.println("tbSpecification的getSpecName"+specification.getSpecification().getSpecName());
            specificationService.update(specification);

            return new  RespBean(true,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RespBean(true,"修改失败");
        }
    }

    @RequestMapping("/delete")
    public RespBean delete(Long[] ids) {
        try {
            System.out.println("TbSpecification id:" + ids[0]);
            specificationService.delete(ids);
            return new  RespBean(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RespBean(true,"删除失败");
        }
    }


    @RequestMapping("/search")
    public List<TbSpecification> search(@RequestBody TbSpecification specification ){
        System.out.println(specification.getId());
        System.out.println("查询规格"+specification);
        return specificationService.search(specification);
    }


    @GetMapping("/findByPage")
    public ResultPage findByPage(int pageNum, int pageSize) {
        System.out.println("findByPage--------------->");

        System.out.println("页码：" +  pageNum);
        System.out.println("每页记录数：" + pageSize);
        PageHelper.startPage(pageNum,pageSize);

        Page<TbSpecification> page = (Page<TbSpecification>) specificationService.findAll();
        ResultPage resultPage = new ResultPage();
        resultPage.setRows(page.getResult());
        resultPage.setTotal(page.getTotal());

        return resultPage;
    }

    @RequestMapping("/selectOptionList")
    public List<Map> selectOptionList(){
        return specificationService.selectOptionList();

    }
}
