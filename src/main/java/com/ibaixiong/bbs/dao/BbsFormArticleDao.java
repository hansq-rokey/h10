package com.ibaixiong.bbs.dao;

import com.ibaixiong.entity.BbsFormArticle;

public interface BbsFormArticleDao {
	Long deleteByPrimaryKey(Long id);

	Long insertSelective(BbsFormArticle record);

    BbsFormArticle selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(BbsFormArticle record);
    
    void deleteFormArticleByArticleId(Long id);
}