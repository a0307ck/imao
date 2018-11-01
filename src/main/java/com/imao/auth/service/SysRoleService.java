package com.imao.auth.service;
import java.util.List;

import com.imao.auth.model.SysRole;
import com.imao.code.Service;


/**
 * Created by ChineseKam on 2018/10/30.
 */
public interface SysRoleService extends Service<SysRole> {

	List<SysRole> findByUserName(String userName);

}
