package com.ibaixiong.bbs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.BbsArticleApply;

public interface BbsArticleApplyDao {
    List<BbsArticleApply> queryApplyListByArticleId(Long articleId); 
    Long insertSelective(BbsArticleApply record);
    BbsArticleApply checkApplyByArticleUser(@Param("userId")Long userId,@Param("articleId")Long articleId);
    void remove(@Param("userId")Long userId,@Param("articleId")Long articleId);
}