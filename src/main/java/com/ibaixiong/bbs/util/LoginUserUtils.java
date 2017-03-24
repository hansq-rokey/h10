package com.ibaixiong.bbs.util;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.ibaixiong.entity.User;

public class LoginUserUtils {
	/**
	 * 获取当前登陆人员信息
	 * @author zhaolei
	 * @date 2015年8月6日
	 * @return
	 */
	public static User getLoginUser(){
		Subject subject= SecurityUtils.getSubject();
		SimplePrincipalCollection spc=(SimplePrincipalCollection) subject.getPrincipals();
		if(spc == null)
			return null;
		List list=spc.asList();
		if(list == null)
			return null;
		Map<String, String> userMap=(Map<String, String>) list.get(1);
		if(userMap == null)
			return null;
		String id=userMap.get("id");//id,email,phone,nickName
		String email=userMap.get("email");
		String phone=userMap.get("phone");
		String nickName=userMap.get("nickName");
		User user = null;
		if(StringUtils.isNotBlank(id)){
			user = new User();
			user.setId(Long.parseLong(id));
			user.setEmail(email);
			user.setPhone(phone);
			user.setNickName(nickName);
		}
		return user;
	}
}
