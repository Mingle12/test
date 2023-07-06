package com.Mingle.server.impl;

import com.Mingle.dao.AdminDao;
import com.Mingle.person.Admin;
import com.Mingle.server.AdminServer;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class AdminServerImpl implements AdminServer {
    @Resource
    private AdminDao adminDao;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Admin SelectAdmin(Admin admin) {
        Admin admin1 = adminDao.selectOne(new QueryWrapper<Admin>().eq("username", admin.getUsername()));
        Admin admin2 = new Admin();
        admin2.setUsername(admin1.getUsername());
        admin2.setPassword(admin1.getPassword());
        admin2.setId(admin1.getId());
        return admin;
    }
}
