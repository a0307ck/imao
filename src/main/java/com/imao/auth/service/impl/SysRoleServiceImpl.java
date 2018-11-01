package com.imao.auth.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imao.auth.mapper.SysRoleMapper;
import com.imao.auth.model.SysRole;
import com.imao.auth.service.SysRoleService;
import com.imao.code.AbstractService;


/**
 * Created by ChineseKam on 2018/10/30.
 */
@Service
@Transactional
public class SysRoleServiceImpl extends AbstractService<SysRole> implements SysRoleService {
    @Resource
    private SysRoleMapper roleMapper;

	@Override
	public List<SysRole> findByUserName(String userName) {
		return roleMapper.findByUserName(userName);
	}

}
