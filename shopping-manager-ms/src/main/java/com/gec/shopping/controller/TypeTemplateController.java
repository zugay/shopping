package com.gec.shopping.controller;

import com.gec.shopping.pojo.RespBean;
import com.gec.shopping.pojo.ResultPage;
import com.gec.shopping.pojo.TbTypeTemplate;
import com.gec.shopping.service.TypeTemplateService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/typeTemplate-Ms")
public class TypeTemplateController {

    @Autowired
    private TypeTemplateService typeTemplateService;

    @RequestMapping("/findAll")
    public List<TbTypeTemplate> findAll() {
        List<TbTypeTemplate> list = typeTemplateService.findAll();
        //  System.out.println(list);
        return list;

    }

    @GetMapping("/findById/{id}")
    public TbTypeTemplate findById(@PathVariable Long id) {
        System.out.println(id);
        return typeTemplateService.findById(id);
    }

    @PostMapping("/add")
    public RespBean add(@RequestBody TbTypeTemplate tbTypeTemplate) {
        try {
            System.out.println("tbTypeTemplate:" + tbTypeTemplate.getId());
            typeTemplateService.add(tbTypeTemplate);
            return new  RespBean(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RespBean(true,"添加失败");
        }
    }

    @RequestMapping("/update")
    public RespBean updateBrand(@RequestBody TbTypeTemplate tbTypeTemplate) {
        try {
            System.out.println("tbTypeTemplate"+tbTypeTemplate.getName());
            typeTemplateService.update(tbTypeTemplate);

            return new  RespBean(true,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RespBean(true,"修改失败");
        }
    }

    @RequestMapping("/delete")
    public RespBean delete(Long[] ids) {
        try {
            System.out.println("tbTypeTemplate id:" + ids[0]);
            typeTemplateService.delete(ids);
            return new  RespBean(true,"删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RespBean(true,"删除失败");
        }
    }


    @RequestMapping("/search")
    public List<TbTypeTemplate> search(@RequestBody TbTypeTemplate tbTypeTemplate ){
        System.out.println("查询规格"+tbTypeTemplate);
        return typeTemplateService.search(tbTypeTemplate);
    }


    @GetMapping("/findByPage")
    public ResultPage findByPage(int pageNum, int pageSize) {
        System.out.println("findByPage--------------->");

        System.out.println("页码：" +  pageNum);
        System.out.println("每页记录数：" + pageSize);
        PageHelper.startPage(pageNum,pageSize);

        Page<TbTypeTemplate> page = (Page<TbTypeTemplate>) typeTemplateService.findAll();
        ResultPage resultPage = new ResultPage();
        resultPage.setRows(page.getResult());
        resultPage.setTotal(page.getTotal());

        return resultPage;
    }

    @RequestMapping("/findBySpecList")
    public List<Map> findBySpecList(@PathVariable Long id){
        System.out.println(id);
        return typeTemplateService.findSpecList(id);

    }

}
