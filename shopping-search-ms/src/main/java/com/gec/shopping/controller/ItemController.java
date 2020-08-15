package com.gec.shopping.controller;


import com.gec.shopping.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/itemsearch-ms")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/search")
    public Map search(@RequestBody Map searchMap){
        System.out.println("开始搜索");
        return itemService.search(searchMap);

    }
}
