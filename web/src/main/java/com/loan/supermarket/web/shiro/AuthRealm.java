package com.loan.supermarket.web.shiro;

import com.loan.supermarket.mapper.Permission;
import com.loan.supermarket.mapper.Role;
import com.loan.supermarket.mapper.User;
import com.loan.supermarket.service.UserServiece;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private UserServiece userServiece;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 获取User用户
        User user = (User) principals.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissionList = new ArrayList<>();
        List<String> roleNameList = new ArrayList<>();
        Set<Role> roleSet = user.getRoles();
        if (roleSet != null) {
            for (Role role : roleSet) {
                roleNameList.add(role.getRname());
                Set<Permission> permissionSet = role.getPermissions();
                if (permissionSet != null) {
                    for (Permission permission : permissionSet) {
                        permissionList.add(permission.getName());
                    }
                }
            }
        }

        // 需要把角色和权限放入info中
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 权限设定
        info.addStringPermissions(permissionList);

        // 角色设定
        info.addRoles(roleNameList);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        User user = userServiece.findByUsername(username);
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }
}
