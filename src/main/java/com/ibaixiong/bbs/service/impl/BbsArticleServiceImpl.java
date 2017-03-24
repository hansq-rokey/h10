package com.ibaixiong.bbs.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.ibaixiong.bbs.dao.BbsArticleDao;
import com.ibaixiong.bbs.service.BbsArticleService;
import com.ibaixiong.constant.PageConstant;
import com.ibaixiong.entity.BbsArticle;

/**
 * 
 * 
 * @author zhaolei yaoweiguo
 * @email  280435353@qq.com
 * @date   2016年8月12日
 * @since  1.0.0
 */
@Service
public class BbsArticleServiceImpl implements BbsArticleService {
	@Resource
	private BbsArticleDao bbsArticleDao;
	@Override
	public List<BbsArticle> queryActiveArticleT3() {
		return bbsArticleDao.queryActiveArticleT3();
	}
	@Override
	public List<BbsArticle> queryArticleList(Map<String, Object> map) {
		PageHelper.startPage(Integer.parseInt(map.get("pageNo").toString()), PageConstant.bbspageSize, true);
		return bbsArticleDao.queryArticleList(map);
	}

	@Override
	public List<BbsArticle> queryArticleCommonList(Long formId,Integer pageNo) {
		PageHelper.startPage(pageNo, PageConstant.bbspageSize, "a.create_date_time desc");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("formId", formId);
		return bbsArticleDao.queryArticleCommonList(map);
	}
	
	/* (non-Javadoc)
	 * @see com.ibaixiong.bbs.service.BbsArticleService#queryArticleCommonListApi(java.lang.Long, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public List<BbsArticle> queryArticleCommonListApi(Long formId, Integer pageNo, Integer pageSize) {
//		PageHelper.startPage(pageNo, pageSize, "a.top desc ,a.good DESC ,a.create_date_time DESC");
		PageHelper.startPage(pageNo, pageSize, "a.top desc ,a.create_date_time DESC");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("formId", formId);
//		map.put("good", 1);
		map.put("api", 1);
		return bbsArticleDao.queryArticleCommonList(map);
	}
	
	
	/* (non-Javadoc)
	 * @see com.ibaixiong.bbs.service.BbsArticleService#queryArticleProductList(java.util.Map)
	 */
	@Override
	public List<BbsArticle> queryArticleProductList(Map<String, Object> map) {
		PageHelper.startPage(Integer.parseInt(map.get("pageNo").toString()), PageConstant.bbspageSize, true);
		return bbsArticleDao.queryArticleList(map);
	}
	
	@Override
	public BbsArticle getArticleDetailById(Long id,Byte status) {
		return bbsArticleDao.getArticleDetailById(id,status);
	}
	@Override
	public void updateViewCount(Long id) {
		bbsArticleDao.updateViewCount(id);
	}
	@Override
	public Long saveArticle(BbsArticle article) {
		return bbsArticleDao.insertSelective(article);
	}
	@Override
	public Long updateArticle(BbsArticle article) {
		return bbsArticleDao.updateByPrimaryKeySelective(article);
	}
	
	@Override
	public Long updateReport(Long id) {
		return bbsArticleDao.updateReport(id);
	}
	@Override
	public Long updateParise(Long id) {
		return bbsArticleDao.updateParise(id);
	}
	@Override
	public Long updateReply(Long id) {
		return bbsArticleDao.updateReply(id);
	}
	
	@Override
	public BbsArticle getArticleByKey(Long id) {
		return bbsArticleDao.selectByPrimaryKey(id);
	}
	@Override
	public List<BbsArticle> queryUserArticleList(Map<String, Object> map) {
		PageHelper.startPage(Integer.parseInt(map.get("pageNo").toString()), PageConstant.bbspageSize, true);
		return bbsArticleDao.queryUserArticleList(map);
	}
	@Override
	public List<BbsArticle> queryArticleByUserId(Long id,Integer pageNo) {
		PageHelper.startPage(pageNo, PageConstant.bbspageSize, true);
		return bbsArticleDao.queryArticleByUserId(id);
	}
	@Override
	public Long updateApply(Long id) {
		return bbsArticleDao.updateApply(id);
	}
	
	@Override
	public void remove(Long userId, Long id) {
		bbsArticleDao.remove(userId, id);
	}


}
