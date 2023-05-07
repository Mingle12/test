package com.Mingle.server.impl;

import com.Mingle.dao.PersonDao;
import com.Mingle.person.Person;
import com.Mingle.server.PersonServer;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class PersonServerImpl extends ServiceImpl<PersonDao,Person> implements PersonServer {
    @Resource
    private PersonDao personDao;

    @Override
    public List<Person> getPersonList() {
        List<Person> people = personDao.selectList(new QueryWrapper<Person>());
        return people;
    }

    @Override
    public void addPerson(Person person) {
        personDao.insert(person);
    }

    @Override
    public void deletePeron(Person person) {
        personDao.deleteById(person.getId());
    }
}
