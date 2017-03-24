package com.ibaixiong.bbs.service;

import java.util.List;

import com.ibaixiong.entity.BbsReply;

public interface ReplyService {
	List<BbsReply> queryReplyListByArticleId(Long id);
	
	Long saveReply(BbsReply reply);
	/**
	 * 查询帖子回复
	 * @author zhaolei
	 * @date 2015年8月3日
	 * @param item
	 * @return
	 */
	List<BbsReply> queryReplyListByArticleIds(List<Long> item,Integer pageNo);
	
	BbsReply getReplyById(Long id);
	
	void updateReply(BbsReply reply);
}
