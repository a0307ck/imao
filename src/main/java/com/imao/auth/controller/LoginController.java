package com.imao.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.imao.auth.aop.SysLogControllerAs;
import com.imao.auth.model.SysUser;
import com.imao.auth.service.SysUserService;
import com.imao.code.Result;
import com.imao.code.ResultGenerator;
import com.imao.utils.JWTUtil;


@RestController
public class LoginController {
	@Autowired
    private SysUserService sysUserService;
    
	@SysLogControllerAs(module="权限模块",description="登录")
    @PostMapping("/login")
    public Result login(@RequestParam("userName") String userName,
                           @RequestParam("password") String password,HttpServletRequest request) {
    	SysUser user= sysUserService.findByUserName(userName);
        if (user == null) {
            return ResultGenerator.genFailResult("用户名错误");
        } else if (!user.getPassWord().equals(password)) {
            return ResultGenerator.genFailResult("密码错误");
        } else {
        	String token = JWTUtil.createToken(user.getUserName());
        	request.setAttribute("token", token);
            return ResultGenerator.genSuccessResult(token);
        }
    }
}
