package com.ibaixiong.bbs.dao;

import com.ibaixiong.entity.BbsPermissions;

public interface BbsPermissionsDao {
	Long deleteByPrimaryKey(Long id);

	Long insertSelective(BbsPermissions record);

    BbsPermissions selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(BbsPermissions record);
}