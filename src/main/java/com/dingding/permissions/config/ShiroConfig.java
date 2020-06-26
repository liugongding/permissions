package com.dingding.permissions.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author liudingding
 * @ClassName ShiroConfig
 * @description
 * @date 2020/6/23 10:02 下午
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        filterFactoryBean.setSecurityManager(securityManager);

        /**
         * anon:无需认证就能访问
         * authc：必须认证才能访问
         * user：必须拥有记住我功能才能使用
         * perms：拥有对某个资源的权限才能访问
         * 拥有某个角色权限才能访问
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        //授权
        filterMap.put("/user/add", "perms[user:add]");
        filterMap.put("/user/update", "perms[user:update]");
        //认证
        filterMap.put("/user/*", "authc");
        //设置登录
        filterFactoryBean.setLoginUrl("/toLogin");
        //设置未授权
        filterFactoryBean.setUnauthorizedUrl("/noauth");
        filterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return filterFactoryBean;
    }

    /**
     * DefaultWebSecurityManager
     */
    @Bean("securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    /**
     * 创建Realm
     */
    @Bean("userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }


    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
