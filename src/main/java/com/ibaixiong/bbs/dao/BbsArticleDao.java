package com.ibaixiong.bbs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.ibaixiong.entity.BbsArticle;

public interface BbsArticleDao {
	Long deleteByPrimaryKey(Long id);

	Long insertSelective(BbsArticle record);

    BbsArticle selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(BbsArticle record);
    
    Long updateSort(BbsArticle record);
    
    Long updateTagType(BbsArticle article);
    
    Long updateStatus(BbsArticle record);
    
    List<BbsArticle> queryActiveArticleT3();
    /**
     * 
     * @author yaoweiguo
     * @date 2016年8月12日
     * @param map   formatId,
     * @since V1.5.0
     * @return
     */
    List<BbsArticle> queryArticleCommonList(Map<String, Object> map);
    
    List<BbsArticle> queryArticleList(Map<String, Object> map);
    
    BbsArticle getArticleDetailById(@Param("id")Long id,@Param("status")Byte status);
    
    void updateViewCount(Long id);
    
    Long updateReport(Long id);
    
    Long updateParise(Long id);
    
    Long updateReply(Long id);
    
    List<BbsArticle> queryUserArticleList(Map<String, Object> map);
    
    List<BbsArticle> queryArticleByUserId(Long id);
    
    Long updateApply(Long id);
    
    List<BbsArticle> getIsActivityEndList();
    
    void remove(@Param("userId")Long userId, @Param("id")Long id);
}