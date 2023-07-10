package com.Mingle.server;

import com.Mingle.person.Admin;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;

public interface AdminServer extends IService<Admin> {
    boolean checkAdminInfo(Admin admin);
}
