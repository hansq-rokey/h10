package com.ibaixiong.bbs.service;

public interface PointsHistoryService {
	void savePointsHistory(Long userId,String tagName);
	void savePointsHistory(Long userId,String tagName,Long formId);
}
