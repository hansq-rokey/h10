package com.ibaixiong.bbs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.bbs.dao.BbsCollectionDao;
import com.ibaixiong.bbs.service.BbsArticleConllectionService;
import com.ibaixiong.entity.BbsCollection;
@Service
public class BbsArticleConllectionServiceImpl implements
		BbsArticleConllectionService {
	@Resource
	private BbsCollectionDao bbsCollectionDao;
	@Override
	public BbsCollection checkConllectionByArticleUser(Long userId,
			Long articleId) {
		return bbsCollectionDao.checkCollectionByArticleUser(userId, articleId);
	}

	@Override
	public Long save(BbsCollection collection) {
		return bbsCollectionDao.insertSelective(collection);
	}
	@Override
	public void remove(Long userId, Long articleId) {
		bbsCollectionDao.remove(userId, articleId);
	}
}
