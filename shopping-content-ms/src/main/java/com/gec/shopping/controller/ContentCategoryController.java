package com.gec.shopping.controller;

import com.gec.shopping.pojo.RespBean;
import com.gec.shopping.pojo.ResultPage;
import com.gec.shopping.pojo.TbContent;
import com.gec.shopping.pojo.TbContentCategory;
import com.gec.shopping.service.ContentCategoryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ContentC-ms")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @GetMapping("/findAll")
    public List<TbContentCategory> findAll(){
        List<TbContentCategory> list = contentCategoryService.findAll();
        System.out.println(list);
        return list;
    }

    @GetMapping("/findById/{id}")
    public TbContentCategory findById(@PathVariable Long id) {
        System.out.println(id);
        return contentCategoryService.findById(id);
    }


    @PostMapping("/add")
    public RespBean add(@RequestBody TbContentCategory tbContentCategory) {
        try {
            System.out.println("tbContentCategory id:" + tbContentCategory.getId());
            contentCategoryService.add(tbContentCategory);
            return new  RespBean(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RespBean(true,"添加失败");
        }
    }

    @RequestMapping("/update")
    public RespBean update(@RequestBody TbContentCategory tbContentCategory) {
        try {
            System.out.println("tbContentCategory======"+tbContentCategory.getName());
            contentCategoryService.update(tbContentCategory);

            return new  RespBean(true,"修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RespBean(true,"修改失败");
        }
    }

    @RequestMapping("/delete")
    public RespBean delete(Long[] ids) {
        try {
            System.out.println("tbContentCategory id:" + ids[0]);
            contentCategoryService.delete(ids);
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

        Page<TbContentCategory> page = (Page<TbContentCategory>) contentCategoryService.findAll();
        ResultPage resultPage = new ResultPage();
        resultPage.setRows(page.getResult());
        resultPage.setTotal(page.getTotal());

        return resultPage;
    }

}
