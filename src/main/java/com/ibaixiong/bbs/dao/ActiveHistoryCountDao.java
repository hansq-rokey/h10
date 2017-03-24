package com.ibaixiong.bbs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.ActiveHistoryCount;

public interface ActiveHistoryCountDao {
	List<ActiveHistoryCount> getActiveUserByFormId(Long formId);
	List<ActiveHistoryCount> getActiveUserByFormIds(List<Long> item);
	ActiveHistoryCount getByUserAndForm(@Param("formId") Long formId, @Param("userId") Long userId);
	Long insertSelective(ActiveHistoryCount record);
    Long updateByPrimaryKeySelective(ActiveHistoryCount record);
}
