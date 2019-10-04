package com.wuheng.shiro;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfig {

	// 创建ShiroFilterFactoryBean
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(
			@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		// 设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 添加Shiro内置过滤器
		/*
		 * (常用过滤器) 1、anon:无需认证可以访问 2、authc:必须通过认证才可以访问 3、user:如果使用rememberMe的功能可以直接访问
		 * 4、perms:该资源必须得到资源权限才可以访问 5、role:该资源必须得到角色权限才可以访问
		 */
		Map<String, String> filterMap = new LinkedHashMap<String, String>();
		
		
		/*
		 * filterMap.put("/add", "authc"); filterMap.put("/update", "authc");
		 */
		filterMap.put("/doLogin", "anon");
		filterMap.put("/thymeleaf", "anon");
		
		//授权(要放在拦截所有的前面)
		filterMap.put("/add", "perms[user:add]");
		filterMap.put("/update","perms[user:update]");
		
		filterMap.put("/*", "authc");
		
		//修改调整登录页面
		shiroFilterFactoryBean.setLoginUrl("/toLogin");
		//设置未授权提示页面
		shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

		return shiroFilterFactoryBean;
	}

	// 创建DefaultWebSecurityManager
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(userRealm);
		return securityManager;
	}

	// 创建Realm
	@Bean(name = "userRealm")
	public UserRealm getRealm() {
		return new UserRealm();
	}
	
	@Bean
	public ShiroDialect getShiroDialect() {
		return new ShiroDialect();
	}

}
