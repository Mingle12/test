package com.Mingle.controller;

import com.Mingle.common.R;
import com.Mingle.person.Person;
import com.Mingle.server.PersonServer;
import com.baomidou.mybatisplus.core.conditions.AbstractWrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mysql.cj.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "角色管理")
@RestController
@RequestMapping("/person")
@Slf4j
public class PersonController {
    @Autowired
    private PersonServer personServer;


    @PostMapping("/add")
    public R<String> getPerson(@RequestBody Person person) {
        Person person1 = new Person();
        person1.setId(person.getId());
        person1.setAge(person.getAge());
        person1.setName(person.getName());
        person1.setAddressInfo(person.getAddressInfo());
        person1.setPhone(person.getPhone());
        personServer.save(person1);
        return R.success("新增人员成功!");
    }

    /*
    分页查询
    * */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        log.info("page={},pageSize={},name={}",page,pageSize,name);
        //构造分页构造器
        Page pageInfo = new Page(page,pageSize);

        //构造条件构造器
        LambdaQueryWrapper<Person> queryWrapper = new LambdaQueryWrapper();
        //添加过滤条件
        queryWrapper.like(!StringUtils.isNullOrEmpty(name), Person::getName, name);
        queryWrapper.orderByDesc(Person::getId);
        //执行查询
        personServer.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
}
