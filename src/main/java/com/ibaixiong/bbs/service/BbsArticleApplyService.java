package com.ibaixiong.bbs.service;

import com.ibaixiong.entity.BbsArticleApply;

public interface BbsArticleApplyService {
	BbsArticleApply checkApplyByArticleUser( Long userId, Long articleId);
	Long save(BbsArticleApply Apply);
	void remove( Long userId, Long articleId);
}
