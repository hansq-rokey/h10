package com.ibaixiong.bbs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.bbs.dao.SignHistoryDao;
import com.ibaixiong.bbs.service.SignHistoryService;
import com.ibaixiong.entity.SignHistory;
@Service
public class SignHistoryServiceImpl implements SignHistoryService {
	@Resource
	private SignHistoryDao signHistoryDao;
	@Override
	public SignHistory checkSign(Long userId, Integer year, Integer month,
			Integer day) {
		return signHistoryDao.checkSign(userId, year, month, day);
	}
	@Override
	public void saveSignHistory(SignHistory signHistory) {
		signHistoryDao.insertSelective(signHistory);
	}
}
