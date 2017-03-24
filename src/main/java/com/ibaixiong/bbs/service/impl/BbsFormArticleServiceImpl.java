package com.ibaixiong.bbs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.bbs.dao.BbsFormArticleDao;
import com.ibaixiong.bbs.service.BbsFormArticleService;
import com.ibaixiong.entity.BbsFormArticle;
@Service
public class BbsFormArticleServiceImpl implements BbsFormArticleService {
	@Resource
	private BbsFormArticleDao bbsFormArticleDao;
	@Override
	public Long saveFormArticle(BbsFormArticle formArticle) {
		return bbsFormArticleDao.insertSelective(formArticle);
	}
	@Override
	public void deleteFormArticleByArticleId(Long articleId) {
		bbsFormArticleDao.deleteFormArticleByArticleId(articleId);
	}
}
