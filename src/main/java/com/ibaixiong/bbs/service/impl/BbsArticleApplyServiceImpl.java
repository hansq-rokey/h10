package com.ibaixiong.bbs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.bbs.dao.BbsArticleApplyDao;
import com.ibaixiong.bbs.service.BbsArticleApplyService;
import com.ibaixiong.entity.BbsArticleApply;
@Service
public class BbsArticleApplyServiceImpl implements BbsArticleApplyService {
	@Resource
	private BbsArticleApplyDao bbsArticleApplyDao;
	@Override
	public BbsArticleApply checkApplyByArticleUser(Long userId, Long articleId) {
		return bbsArticleApplyDao.checkApplyByArticleUser(userId, articleId);
	}

	@Override
	public Long save(BbsArticleApply Apply) {
		return bbsArticleApplyDao.insertSelective(Apply);
	}

	@Override
	public void remove(Long userId, Long articleId) {
		bbsArticleApplyDao.remove(userId, articleId);
	}

}
