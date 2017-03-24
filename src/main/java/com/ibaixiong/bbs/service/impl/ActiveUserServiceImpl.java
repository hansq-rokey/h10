package com.ibaixiong.bbs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.bbs.dao.ActiveHistoryCountDao;
import com.ibaixiong.bbs.service.ActiveUserService;
import com.ibaixiong.entity.ActiveHistoryCount;
@Service
public class ActiveUserServiceImpl implements ActiveUserService {
	@Resource
	private ActiveHistoryCountDao activeHistoryCountDao;
	
	@Override
	public List<ActiveHistoryCount> getActiveUserByFormId(Long formId) {
		return activeHistoryCountDao.getActiveUserByFormId(formId);
	}

	@Override
	public List<ActiveHistoryCount> getActiveUserByFormIds(List<Long> item) {
		return activeHistoryCountDao.getActiveUserByFormIds(item);
	}

}
