package com.Mingle.controller;

import com.Mingle.person.Admin;
import com.Mingle.person.Person;
import com.Mingle.server.AdminServer;
import com.Mingle.util.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "admin登录")
@RestController
public class AdminController {
    @Autowired
    private AdminServer adminServer;

    @ApiOperation(value = "管理员登录",notes = "输入id,名字,密码实现登录")
    @PostMapping("/AdminLogin")
    public ResultData<Admin> Login(@RequestBody Admin admin){
        Admin selectAdmin = adminServer.SelectAdmin(admin.getId());
        if (selectAdmin.getUsername().equals(admin.getUsername())){
            if (selectAdmin.getPassword()==admin.getPassword()){
                return ResultData.success(admin,"登录成功!");
            }
        }
        return ResultData.error("500","登录失败");
    }
}
