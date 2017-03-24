package com.ibaixiong.bbs.service;

import java.util.List;

import com.ibaixiong.entity.ActiveHistoryCount;

public interface ActiveUserService {
	List<ActiveHistoryCount> getActiveUserByFormId(Long formId);
	List<ActiveHistoryCount> getActiveUserByFormIds(List<Long> item);
}
