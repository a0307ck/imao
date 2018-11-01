package com.imao.auth.service;
import com.imao.auth.model.SysUser;
import com.imao.code.Service;


/**
 * Created by ChineseKam on 2018/10/29.
 */
public interface SysUserService extends Service<SysUser> {

	SysUser findByUserName(String userName);

	

}
