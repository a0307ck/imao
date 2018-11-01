package com.imao.auth.shiro;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imao.auth.model.SysRole;
import com.imao.auth.model.SysUser;
import com.imao.auth.service.SysRoleService;
import com.imao.auth.service.SysUserService;
import com.imao.utils.JWTUtil;


@Component
public class CustomRealm extends AuthorizingRealm {
    
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysRoleService  sysRoleService;

   

    /**
     * 必须重写此方法，不然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        String token = (String) authenticationToken.getCredentials();
        // 解密获得username，用于和数据库进行对比
        String userName = JWTUtil.getUsername(token);
        if (userName == null || !JWTUtil.verify(token, userName)) {
            throw new AuthenticationException("token认证失败！");
        }
//        String password = userMapper.getPassword(userName);
        SysUser user =  sysUserService.findByUserName(userName);
        if (user == null) {
            throw new AuthenticationException("该用户不存在！");
        }
//        int ban = userMapper.checkUserBanStatus(username);
//        if (ban == 1) {
//            throw new AuthenticationException("该用户已被封号！");
//        }
//        if (! JWTUtil.verify(token, userName,user.getPassWord())) {
//            throw new AuthenticationException("密码错误！");
//        }
        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(token, token, "MyRealm");
        return authcInfo;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("————权限认证————");
        String userName = JWTUtil.getUsername(principals.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        List<SysRole> roleList = sysRoleService.findByUserName(userName);
        
        Set<String> roleSet = new HashSet<>();
        for(SysRole role : roleList)
        roleSet.add(role.getRoleCode());
        //设置该用户拥有的角色和权限
        info.setRoles(roleSet);
        
//        //每个角色拥有默认的权限
//        String rolePermission = userMapper.getRolePermission(userName);
//        //每个用户可以设置新的权限
//        String permission = userMapper.getPermission(userName);
//        Set<String> permissionSet = new HashSet<>();
//        permissionSet.add(rolePermission);
//        permissionSet.add(permission);
//        info.setStringPermissions(permissionSet);
        
        return info;
    }
}
