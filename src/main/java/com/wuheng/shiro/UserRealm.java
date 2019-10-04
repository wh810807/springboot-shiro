package com.wuheng.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.wuheng.pojo.User;
import com.wuheng.service.UserService;

public class UserRealm extends AuthorizingRealm {
	
	@Autowired
	private UserService userService;

	/*
	 * 执行授权
	 * 
	 * */
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		System.out.println("执行授权");
		//添加授权
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		Subject subject = SecurityUtils.getSubject();
		User users =(User) subject.getPrincipal();
		System.out.println(users.getId()+"==>"+users.getName());
		User user = userService.findById(users.getId());
		info.addStringPermission(user.getPerms());
		return info;
	}
	

	/*
	 * 执行认证
	 * 
	 * */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		System.out.println("执行认证");
		UsernamePasswordToken token=(UsernamePasswordToken)arg0;
		User user = userService.findByName(token.getUsername());
		if(user==null) {
			return null;
		}
		return new SimpleAuthenticationInfo(user,user.getPassword(),"");
		
	}

}
