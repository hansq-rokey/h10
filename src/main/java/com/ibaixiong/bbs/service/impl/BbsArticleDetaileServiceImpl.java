package com.ibaixiong.bbs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.bbs.dao.BbsArticleDetailDao;
import com.ibaixiong.bbs.service.BbsArticleDetailService;
import com.ibaixiong.entity.BbsArticleDetail;
@Service
public class BbsArticleDetaileServiceImpl implements BbsArticleDetailService {
	@Resource
	private BbsArticleDetailDao bbsArticleDetailDao;
	@Override
	public Long saveArticleDetail(BbsArticleDetail articleDetail) {
		return bbsArticleDetailDao.insertSelective(articleDetail);
	}
	@Override
	public void deleteArticleDetailByArticleId(Long articleId) {
		bbsArticleDetailDao.deleteArticleDetailByArticleId(articleId);
	}
}
