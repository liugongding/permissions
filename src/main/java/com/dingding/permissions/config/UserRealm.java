package com.dingding.permissions.config;

import com.dingding.permissions.entity.User;
import com.dingding.permissions.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author liudingding
 * @ClassName UserRealm
 * @description
 * @date 2020/6/23 10:36 下午
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserServiceImpl userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //拿到当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        //拿到具体的user
        User currentUser = (User) subject.getPrincipal();
        //查询数据库对不同的用户授予不同的权限
        info.addStringPermission(currentUser.getPerms());
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");


        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.queryUserByName(token.getUsername());
        System.out.println(token);
        log.info("{}",user);
        if (user == null){
            //会抛出UnknowAccountExeception
            return null;
        }
        Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        session.setAttribute("loginUser", user);
        //密码认证
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
