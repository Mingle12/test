package com.Mingle.controller;

import com.Mingle.person.Person;
import com.Mingle.server.PersonServer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "角色管理")
@RestController
public class PersonController {
    @Autowired
    private PersonServer personServer;


    @ApiOperation(value = "查询用户",notes = "所有用户")
    @GetMapping("/list")
    public List<Person> getPerson(){
        List<Person> list = personServer.getPersonList();
        return list;
    }


    @ApiOperation(value = "新增用户",notes = "新增加一个用户")
    @PostMapping("/add")
    public void addPerson(@RequestBody Person person){
        personServer.addPerson(person);
    }


    @ApiOperation(value = "删除用户",notes = "选择删除一个用户")
    @PostMapping("/delete")
    public void deletePerson(int id){
        personServer.deletePeron(id);
    }

    @ApiOperation(value="更新用户",notes = "选这更新一个用户")
    @PostMapping("/update")
    public void updatePerson(Person person){
        personServer.updatePerson(person);
    }
}
