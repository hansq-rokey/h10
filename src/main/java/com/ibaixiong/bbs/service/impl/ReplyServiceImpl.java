package com.ibaixiong.bbs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ibaixiong.bbs.dao.BbsReplyDao;
import com.ibaixiong.bbs.service.ReplyService;
import com.ibaixiong.constant.PageConstant;
import com.ibaixiong.entity.BbsReply;
@Service
public class ReplyServiceImpl implements ReplyService {
	@Resource
	private BbsReplyDao bbsReplyDao;
	@Override
	public List<BbsReply> queryReplyListByArticleId(Long id) {
		return bbsReplyDao.queryReplyListByArticleId(id);
	}
	@Override
	public Long saveReply(BbsReply reply) {
		return bbsReplyDao.insertSelective(reply);
	}
	@Override
	public List<BbsReply> queryReplyListByArticleIds(List<Long> item,Integer pageNo) {
		PageHelper page= new PageHelper();
		page.startPage(pageNo, PageConstant.bbspageSize, true);
		return bbsReplyDao.queryReplyListByArticleIds(item);
	}
	@Override
	public BbsReply getReplyById(Long id) {
		return bbsReplyDao.selectByPrimaryKey(id);
	}
	@Override
	public void updateReply(BbsReply reply) {
		bbsReplyDao.updateByPrimaryKeySelective(reply);
	}
}
