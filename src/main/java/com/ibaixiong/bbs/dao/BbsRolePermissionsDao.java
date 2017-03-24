package com.ibaixiong.bbs.dao;

import com.ibaixiong.entity.BbsRolePermissions;

public interface BbsRolePermissionsDao {
	Long deleteByPrimaryKey(Long id);

    Long insertSelective(BbsRolePermissions record);

    BbsRolePermissions selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(BbsRolePermissions record);

}