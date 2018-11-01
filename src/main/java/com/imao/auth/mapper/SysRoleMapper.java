package com.imao.auth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imao.auth.model.SysRole;
import com.imao.code.DiyMapper;

public interface SysRoleMapper extends DiyMapper<SysRole>{

	List<SysRole> findByUserName(@Param(value = "userName") String userName);

}