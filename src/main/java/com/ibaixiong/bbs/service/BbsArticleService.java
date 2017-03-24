package com.ibaixiong.bbs.service;

import java.util.List;
import java.util.Map;

import com.ibaixiong.entity.BbsArticle;

public interface BbsArticleService {
	/**
	 * 页面查询活动帖子前三个，按照发布时间倒叙排列
	 * @author zhaolei
	 * @date 2015年7月28日
	 * @return
	 */
	List<BbsArticle> queryActiveArticleT3();
	/**
	 * 查询帖子列表
	 * @author zhaolei
	 * @date 2015年7月28日
	 * @param map
	 * @return
	 */
	List<BbsArticle> queryArticleList(Map<String, Object> map);
	
	/**
	 * 查询通用帖子列表
	 * @author yaoweiguo
	 * @date 2016年8月12日
	 * @param formId	版块ID
	 * @param pageNo	页码
	 * @since V1.5.0
	 * @return
	 */
	List<BbsArticle> queryArticleCommonList(Long formId,Integer pageNo);
	
	/**
	 * 查询通用帖子列表
	 * @author yaoweiguo
	 * @date 2016年8月12日
	 * @param formId	版块ID
	 * @param pageNo	页码
	 * @param pageSize  页面大小
	 * @since V1.5.0
	 * @return
	 */
	List<BbsArticle> queryArticleCommonListApi(Long formId,Integer pageNo,Integer pageSize);
	
	/**
	 * 查询产品展示页面效果的帖子列表
	 * @author yaoweiguo
	 * @date 2016年8月12日
	 * @param formId	版块ID
	 * @param pageNo	页码
	 * @since V1.5.0
	 * @return
	 */
	List<BbsArticle> queryArticleProductList(Map<String, Object> map);
	
	
	/**
	 * 查看详情
	 * @author zhaolei
	 * @date 2015年7月29日
	 * @param id
	 * @return
	 */
	BbsArticle getArticleDetailById(Long id,Byte status);
	/**
	 * 修改帖子查看次数
	 * @author zhaolei
	 * @date 2015年7月29日
	 * @param id
	 */
	void updateViewCount(Long id);
	/**
	 * 保存
	 * @author zhaolei
	 * @date 2015年7月30日
	 * @param article
	 * @return
	 */
    Long saveArticle(BbsArticle article);
	/**
	 * 修改
	 * @author zhaolei
	 * @date 2015年7月30日
	 * @param article
	 * @return
	 */
	Long updateArticle(BbsArticle article);
	/**
	 * 举报帖子修改
	 * @author zhaolei
	 * @date 2015年7月31日
	 * @param id
	 * @return
	 */
	Long updateReport(Long id);
	/**
	 * 点赞次数
	 * @author zhaolei
	 * @date 2015年7月31日
	 * @param id
	 * @return
	 */
	Long updateParise(Long id);
	/**
	 * 回复次数
	 * @author zhaolei
	 * @date 2015年7月31日
	 * @param id
	 * @return
	 */
	Long updateReply(Long id);
	/**
	 * 报名次数
	 * @author zhaolei
	 * @date 2015年7月31日
	 * @param id
	 * @return
	 */
	Long updateApply(Long id);
	/**
	 * ID返回对象
	 * @author zhaolei
	 * @date 2015年7月31日
	 * @param id
	 * @return
	 */
	BbsArticle getArticleByKey(Long id);
	/**
	 * 查询个人帖子列表
	 * @author zhaolei
	 * @date 2015年8月2日
	 * @param map
	 * @return
	 */
	List<BbsArticle> queryUserArticleList(Map<String, Object> map);
	/**
	 * 根据个人ID查询个人帖子
	 * @author zhaolei
	 * @date 2015年8月3日
	 * @param id
	 * @return
	 */
	List<BbsArticle> queryArticleByUserId(Long id,Integer pageNo);
	
	void remove(Long userId,Long id);
	
}
