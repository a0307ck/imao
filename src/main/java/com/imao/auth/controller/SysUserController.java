package com.imao.auth.controller;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imao.auth.model.SysUser;
import com.imao.auth.service.SysUserService;
import com.imao.code.Result;
import com.imao.code.ResultGenerator;

/**
* Created by ChineseKam on 2018/10/29.
*/
@RestController
@RequestMapping("/auth/user")
public class SysUserController {
    @Resource
    private SysUserService sysUserService;

//    @RequiresRoles(logical = Logical.OR, value = {"user", "admin"})
//    @RequiresPermissions("vip")
//    @RequiresRoles("admin")
    @PostMapping("/add")
    public Result add(SysUser sysUser) {
        sysUserService.save(sysUser);
        return ResultGenerator.genSuccessResult();
    }

    
	@PostMapping("/delete")
    public Result delete(@RequestParam Long id) {
        sysUserService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SysUser sysUser) {
        sysUserService.update(sysUser);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Long id) {
        SysUser sysUser = sysUserService.findById(id);
        return ResultGenerator.genSuccessResult(sysUser);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysUser> list = sysUserService.findAll();
        PageInfo<SysUser> pageInfo = new PageInfo<SysUser>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
