package com.ibaixiong.bbs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.ibaixiong.bbs.dao.BbsUserRoleDao;
import com.ibaixiong.bbs.dao.UserDao;
import com.ibaixiong.bbs.service.UserService;
import com.ibaixiong.entity.BbsUserRole;
import com.ibaixiong.entity.User;
@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;
	@Resource
	private BbsUserRoleDao bbsUserRoleDao;
	@Override
	public User getUserInfoById(Long id) {
		User user = userDao.selectByPrimaryKey(id);
		//查询角色列表
		List<BbsUserRole> userRoleList = bbsUserRoleDao.getUserRoleByUserId(id);
		String roleNames = "";
		for (BbsUserRole bbsUserRole : userRoleList) {
			roleNames = roleNames+bbsUserRole.getRole().getName()+",";
		}
		if(StringUtils.isNotBlank(roleNames))
			roleNames=roleNames.substring(0, roleNames.length()-1);
		user.setRoleNames(roleNames);
		return user;
	}
	@Override
	public List<String> queryRoles(Long userId) {
		
		return userDao.queryRoles(userId);
	}
	@Override
	public List<String> queryPermissions(Long userId) {
		
		return userDao.queryPermissions(userId);
	}
	@Override
	public void updateNickName(User user) {
		userDao.updateNickName(user);
	}
	@Override
	public void updateHeadImg(User user) {
		userDao.updateHeadImg(user);
	}
	
}
