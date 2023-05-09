package com.Mingle.server.impl;

import com.Mingle.dao.AdminDao;
import com.Mingle.person.Admin;
import com.Mingle.server.AdminServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServerImpl implements AdminServer {
    @Resource
    private AdminDao adminDao;
    @Override
    public Admin SelectAdmin(int id) {
        Admin admin = adminDao.selectById(id);
        return admin;
    }
}
