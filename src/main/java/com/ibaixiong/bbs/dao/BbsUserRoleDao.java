package com.ibaixiong.bbs.dao;

import java.util.List;

import com.ibaixiong.entity.BbsUserRole;

public interface BbsUserRoleDao {
	Long deleteByPrimaryKey(Long id);

    Long insertSelective(BbsUserRole record);

    BbsUserRole selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(BbsUserRole record);

    List<BbsUserRole> getUserRoleByUserId(Long id);
}