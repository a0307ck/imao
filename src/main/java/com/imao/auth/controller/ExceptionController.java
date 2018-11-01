package com.imao.auth.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.imao.code.Result;
import com.imao.code.ResultGenerator;


@RestControllerAdvice
public class ExceptionController {

    // 捕捉shiro的异常
    @ExceptionHandler(ShiroException.class)
    public Result handle401() {
        return ResultGenerator.genFailResult("您没有权限访问！");
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    public Result globalException(HttpServletRequest request, Throwable ex) {
        return ResultGenerator.genFailResult("访问出错，无法访问: " + ex.getMessage());
    }

}
