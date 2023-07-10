package com.Mingle.server.impl;

import com.Mingle.dao.AdminDao;
import com.Mingle.person.Admin;
import com.Mingle.server.AdminServer;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class AdminServerImpl extends ServiceImpl<AdminDao,Admin> implements AdminServer {

    @Resource
    AdminDao adminDao;

    @Override
    public boolean checkAdminInfo(Admin admin) {
            Admin admin1 = adminDao.selectOne(new QueryWrapper<Admin>().eq("id", admin.getId()).eq("username", admin.getUsername()));
            if (admin1==null){
                return false;
            }
        return true;
        }
    }
