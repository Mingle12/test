package com.Mingle.controller;

import com.Mingle.person.Person;
import com.Mingle.server.PersonServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {
    @Autowired
    private PersonServer personServer;

    @GetMapping("/list")
    public List<Person> getPerson(){
        List<Person> list = personServer.getPersonList();
        return list;
    }

    @PostMapping("/add")
    public void addPerson(@RequestBody Person person){
        personServer.addPerson(person);
    }

    @PostMapping("/delete")
    public void deletePerson(){

    }

}
