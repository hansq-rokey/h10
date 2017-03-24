package com.ibaixiong.bbs.service;

import com.ibaixiong.entity.BbsArticleDetail;

public interface BbsArticleDetailService {
	Long saveArticleDetail(BbsArticleDetail articleDetail);
	void deleteArticleDetailByArticleId(Long articleId);
}
