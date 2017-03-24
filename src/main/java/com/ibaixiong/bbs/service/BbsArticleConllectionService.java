package com.ibaixiong.bbs.service;

import com.ibaixiong.entity.BbsCollection;


public interface BbsArticleConllectionService {
	BbsCollection checkConllectionByArticleUser( Long userId, Long articleId);
	Long save(BbsCollection collection);
	void remove( Long userId, Long articleId);
}
