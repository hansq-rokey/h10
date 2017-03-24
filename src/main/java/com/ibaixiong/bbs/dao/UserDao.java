package com.ibaixiong.bbs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.User;

public interface UserDao {
    List<User> queryUserList(@Param("status") Byte status, @Param("queryName")  String queryName,@Param("roleId")  Long roleId);
    void updateBlockStatus(User user);
    User selectByPrimaryKey(Long id);
    
    /**
     * 根据用户id查询角色名称
     * @param userId
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
    
    void updateExpPointsGradeById(User user);
}