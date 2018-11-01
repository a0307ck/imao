package com.imao.auth.controller;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imao.auth.model.SysRole;
import com.imao.auth.service.SysRoleService;
import com.imao.code.Result;
import com.imao.code.ResultGenerator;

/**
* Created by ChineseKam on 2018/10/30.
*/
@RestController
@RequestMapping("/sys/role")
public class SysRoleController {
    @Resource
    private SysRoleService sysRoleService;

    @PostMapping("/add")
    public Result add(SysRole sysRole) {
        sysRoleService.save(sysRole);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Long id) {
        sysRoleService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SysRole sysRole) {
        sysRoleService.update(sysRole);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Long id) {
        SysRole sysRole = sysRoleService.findById(id);
        return ResultGenerator.genSuccessResult(sysRole);
    }

    
	@PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysRole> list = sysRoleService.findAll();
        PageInfo<SysRole> pageInfo = new PageInfo<SysRole>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
