package com.ibaixiong.bbs.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.bbs.dao.BbsReportDao;
import com.ibaixiong.bbs.service.BbsReportService;
import com.ibaixiong.entity.BbsReport;
@Service
public class BbsReportServiceImpl implements BbsReportService{
	@Resource
	private BbsReportDao bbsReportDao;
	@Override
	public BbsReport checkReportByUserArticle(Long userId, Long articleId) {
		return bbsReportDao.checkReportByUserArticle(userId, articleId);
	}
	@Override
	public Long save(BbsReport report) {
		return bbsReportDao.insertSelective(report);
	}
}
