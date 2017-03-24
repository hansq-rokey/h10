package com.ibaixiong.bbs.service;

import com.ibaixiong.entity.SignHistory;

public interface SignHistoryService {
	SignHistory checkSign( Long userId, Integer year, Integer month , Integer day);
	void saveSignHistory(SignHistory signHistory);
}
