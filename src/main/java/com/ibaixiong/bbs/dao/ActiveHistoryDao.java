package com.ibaixiong.bbs.dao;

import java.util.List;

import com.ibaixiong.entity.ActiveHistory;

public interface ActiveHistoryDao {
	Long insertSelective(ActiveHistory history);
	List<ActiveHistory> queryActiveListGroupByUserForm();
}
