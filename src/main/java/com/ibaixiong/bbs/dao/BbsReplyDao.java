package com.ibaixiong.bbs.dao;

import java.util.List;

import com.ibaixiong.entity.BbsReply;

public interface BbsReplyDao {
    Long deleteByPrimaryKey(Long id);

    Long insertSelective(BbsReply record);

    BbsReply selectByPrimaryKey(Long id);

    Long updateByPrimaryKeySelective(BbsReply record);

    List<BbsReply> queryReplyListByArticleId(Long id);
    
    List<BbsReply> queryReplyListByArticleIds(List<Long> item);
}