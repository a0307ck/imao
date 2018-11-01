package com.imao.auth.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.imao.auth.model.SysLog;
import com.imao.auth.service.SysLogService;
import com.imao.code.Result;
import com.imao.code.ResultGenerator;

/**
* Created by ChineseKam on 2018/10/31.
*/
@RestController
@RequestMapping("/sys/log")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;

    @PostMapping("/add")
    public Result add(SysLog sysLog) {
        sysLogService.save(sysLog);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/delete")
    public Result delete(@RequestParam Long id) {
        sysLogService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/update")
    public Result update(SysLog sysLog) {
        sysLogService.update(sysLog);
        return ResultGenerator.genSuccessResult();
    }

    @PostMapping("/detail")
    public Result detail(@RequestParam Long id) {
        SysLog sysLog = sysLogService.findById(id);
        return ResultGenerator.genSuccessResult(sysLog);
    }

    @PostMapping("/list")
    public Result list(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<SysLog> list = sysLogService.findAll();
        PageInfo<SysLog> pageInfo = new PageInfo<SysLog>(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
