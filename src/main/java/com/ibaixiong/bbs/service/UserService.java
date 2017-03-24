package com.ibaixiong.bbs.service;

import java.util.List;

import com.ibaixiong.entity.User;

/**
 * 用户
 * @author 赵磊
 *
 */
public interface UserService {
	User getUserInfoById(Long id);
	
	/**
	 * 根据用户ID查询角色信息
	 * @param userId   用户id
	 * @return   
	 */
	List<String> queryRoles(Long userId);
	
	/**
     * 根据用户ID查询权限
     * @param userId
     * @return
     */
	List<String> queryPermissions(Long userId);
	
	void updateNickName(User user);
	void updateHeadImg(User user);
}
