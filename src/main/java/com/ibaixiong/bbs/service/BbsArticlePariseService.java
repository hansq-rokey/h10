package com.ibaixiong.bbs.service;

import com.ibaixiong.entity.BbsArticleParise;

public interface BbsArticlePariseService {
	BbsArticleParise checkPariseByArticleUser( Long userId, Long articleId);
	Long save(BbsArticleParise parise);
}
