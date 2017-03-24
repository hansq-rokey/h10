package com.ibaixiong.bbs.dao;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.BbsCollection;

public interface BbsCollectionDao {

    Long insertSelective(BbsCollection record);
    BbsCollection checkCollectionByArticleUser(@Param("userId")Long userId,@Param("articleId")Long articleId);
    void remove(@Param("userId")Long userId,@Param("articleId")Long articleId);
}