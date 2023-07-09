package com.Mingle.controller;

import com.Mingle.common.R;
import com.Mingle.person.Admin;
import com.Mingle.server.AdminServer;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(tags = "admin登录")
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    @Autowired
    private AdminServer adminServer;

    @ApiOperation(value = "管理员登录",notes = "输入id,名字,密码实现登录")
    @PostMapping("/Login")
    public R<Admin> Login(HttpServletRequest request, @RequestBody @Valid Admin admin){
        String getPassword = admin.getPassword();
        getPassword = DigestUtils.md5DigestAsHex(getPassword.getBytes());
        LambdaQueryWrapper<Admin> adminLambdaQueryWrapper = new LambdaQueryWrapper<>();
        adminLambdaQueryWrapper.eq(Admin::getUsername,admin.getUsername());
        Admin addm = adminServer.getOne(adminLambdaQueryWrapper);
        if (addm == null){
            return R.error("登录失败!");
        }
        //比对密码
        if (!addm.getPassword().equals(getPassword)){
            return R.error("登录失败！");
        }
        if (addm.getStatus()==0){
            return R.error("账号已禁用！");
        }
        request.getSession().setAttribute("admin",addm.getId());
        return R.success(addm);
    }

    @PostMapping("/Logout")
    public R<String> logout(HttpServletRequest request){
        //清理session中保存的admin
        request.getSession().removeAttribute("admin");
        return R.success("退出成功！");
    }


}
