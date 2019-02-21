package com.loan.supermarket.web.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

//@Component
public class CredentialMatcher extends SimpleCredentialsMatcher
{
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info)
    {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String password = new String(usernamePasswordToken.getPassword());
        String dbPassword = (String) info.getCredentials();
        if(StringUtils.isNotBlank(password)&&StringUtils.isNotBlank(dbPassword)){
            return this.equals(password, dbPassword);
        }
        return false;
    }
}
