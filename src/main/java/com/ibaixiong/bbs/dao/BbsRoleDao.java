package com.ibaixiong.bbs.dao;

import java.util.List;

import com.ibaixiong.entity.BbsRole;


public interface BbsRoleDao {
	BbsRole selectByPrimaryKey(Long id);
	List<BbsRole> getAll();
}