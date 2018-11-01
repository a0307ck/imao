package com.imao.auth.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imao.auth.mapper.SysUserMapper;
import com.imao.auth.model.SysUser;
import com.imao.auth.service.SysUserService;
import com.imao.code.AbstractService;


/**
 * Created by ChineseKam on 2018/10/29.
 */
@Service
@Transactional
public class SysUserServiceImpl extends AbstractService<SysUser> implements SysUserService {
    @Autowired
    private SysUserMapper userMapper;

	@Override
	public SysUser findByUserName(String userName) {
		SysUser user = new SysUser();
		user.setUserName(userName);
		List<SysUser> userList = userMapper.selectByEntity(user);
		if(userList!=null&&userList.size()>0)return userList.get(0);
		return null;
	}

}
