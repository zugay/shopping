package com.gec.shopping.controller;


import com.gec.shopping.pojo.*;
import com.gec.shopping.service.ContentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.minio.MinioClient;
import io.minio.policy.PolicyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/content-ms")
public class ContentController {

    @Autowired
    private ContentService contentService;


    @Value("${minio.endpoint}")
    private String endpoint;
    @Value("${minio.bucketName}")
    private String bucketName;
    @Value("${minio.accessKey}")
    private String accessKey;
    @Value("${minio.secretKey}")
    private String secretKey;


    @RequestMapping("/findAll")
    public List<TbContent> findAll(){
        List<TbContent> list = contentService.findAll();
          System.out.println(list);
        return list;
    }

    @GetMapping("/findById/{id}")
    public TbContent findById(@PathVariable Long id) {
        System.out.println(id);
        return contentService.findById(id);
    }

    @GetMapping("findByCategoryId/{categoryId}")
    public List<TbContent> findByCategoryId(@PathVariable Long categoryId){
        System.out.println("查找广告的id"+categoryId);
        return contentService.findByCategoryId(categoryId);
    }

    @PostMapping("/add")
    public RespBean add(@RequestBody TbContent tbContent) {
        try {
            System.out.println("tbContent id:" + tbContent.getId());
            contentService.add(tbContent);
            return new  RespBean(true,"添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new RespBean(true,"添加失败");
        }
    }

    @RequestMapping("/update")
    public RespBean update(@RequestBody TbContent tbContent) {
        try {
            System.out.println("tbContent======"+tbContent.getTitle());
            contentService.update(tbContent);

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
            contentService.delete(ids);
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

        Page<TbContent> page = (Page<TbContent>) contentService.findAll();
        ResultPage resultPage = new ResultPage();
        resultPage.setRows(page.getResult());
        resultPage.setTotal(page.getTotal());

        return resultPage;
    }

    @PostMapping("/uploadFile")
    public RespBean uploadFile(MultipartFile file){
        try {
            //先创建minio的文件上传客户端
            MinioClient minioClient = new MinioClient(endpoint,accessKey,secretKey);
            //创建文件存储桶
            boolean isExist = minioClient.bucketExists(bucketName);
            if (!isExist) {
                minioClient.makeBucket(bucketName);
                minioClient.setBucketPolicy(bucketName,"*.*", PolicyType.READ_ONLY);
            }
            //设置存储对象的名称
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String filename = file.getOriginalFilename();
            String objectName = sdf.format(new Date()) + "/" + filename;

            //把存储对象上传至存储桶中
            minioClient.putObject(bucketName,objectName,file.getInputStream(),file.getContentType());
            System.out.println("文件上传成功");

            //文件的访问地址
            String objectUrl = minioClient.getObjectUrl(bucketName,objectName);
            System.out.println(objectUrl);

            return new RespBean(true,objectUrl);
        } catch (Exception e) {
            e.printStackTrace();
            return new RespBean(true,"上传失败");
        }
    }

}
