package com.ibaixiong.bbs.dao;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.BbsArticleParise;

public interface BbsArticlePariseDao {
	Long insertSelective(BbsArticleParise record);
	BbsArticleParise checkPariseByArticleUser(@Param("userId")Long userId,@Param("articleId")Long articleId);
}