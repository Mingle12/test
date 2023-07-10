package com.Mingle.controller;


import com.Mingle.common.R;
import com.Mingle.person.Goods;
import com.Mingle.server.GoodsServer;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "商品操作")
@RestController
@RequestMapping("/Goods")
@Slf4j
public class GoodsController {

    @Autowired
    GoodsServer goodsServer;

    @PostMapping("/add")
    public R<String> addGoods(@RequestBody Goods goods){
        Goods getGoods = new Goods();
        getGoods.setId(goods.getId());
        getGoods.setName(goods.getName());
        getGoods.setPicInfo(goods.getPicInfo());
        getGoods.setPrice(goods.getPrice());
        goodsServer.save(getGoods);
        return R.success("新增商品成功");
    }


}
