package com.ibaixiong.bbs.dao;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.BbsReport;

public interface BbsReportDao {
	Long deleteByPrimaryKey(Long id);
	
    Long insertSelective(BbsReport record);

    BbsReport selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(BbsReport record);

    BbsReport checkReportByUserArticle(@Param("userId") Long userId,@Param("articleId") Long articleId);
}