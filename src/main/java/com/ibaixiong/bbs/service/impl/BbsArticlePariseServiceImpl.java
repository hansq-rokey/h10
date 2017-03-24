package com.ibaixiong.bbs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.bbs.dao.BbsArticlePariseDao;
import com.ibaixiong.bbs.service.BbsArticlePariseService;
import com.ibaixiong.entity.BbsArticleParise;
@Service
public class BbsArticlePariseServiceImpl implements BbsArticlePariseService {
	@Resource
	private BbsArticlePariseDao bbsArticlePariseDao;
	@Override
	public BbsArticleParise checkPariseByArticleUser(Long userId, Long articleId) {
		return bbsArticlePariseDao.checkPariseByArticleUser(userId, articleId);
	}

	@Override
	public Long save(BbsArticleParise parise) {
		return bbsArticlePariseDao.insertSelective(parise);
	}

}
