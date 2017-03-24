package com.ibaixiong.bbs.service;

import com.ibaixiong.entity.BbsReport;

public interface BbsReportService {
	 BbsReport checkReportByUserArticle(  Long userId,  Long articleId);
	 Long save(BbsReport report);
}
