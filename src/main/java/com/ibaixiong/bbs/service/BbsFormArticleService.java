package com.ibaixiong.bbs.service;

import com.ibaixiong.entity.BbsFormArticle;

public interface BbsFormArticleService {
	Long saveFormArticle(BbsFormArticle formArticle);
	void deleteFormArticleByArticleId(Long articleId);
}
