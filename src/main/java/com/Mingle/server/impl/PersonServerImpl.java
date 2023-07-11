package com.Mingle.server.impl;

import com.Mingle.dao.PersonDao;
import com.Mingle.person.Person;
import com.Mingle.server.PersonServer;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class PersonServerImpl extends ServiceImpl<PersonDao,Person> implements PersonServer {

}
