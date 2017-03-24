/**
 * ibaixiong.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.ibaixiong.bbs.service;

import java.util.List;

import com.ibaixiong.entity.BbsArticle;
import com.ibaixiong.entity.BbsForm;
import com.ibaixiong.entity.User;

/**
 * 社区模块
 * @author zhaolei
 *
 */
public interface FormService {
	/**
	 * 根据父节点ID返回社区模块列表
	 * @author zhaolei
	 * @date 2015年7月16日
	 * @param parentId
	 * @return
	 */
	List<BbsForm> getFormByParentId(Long parentId);
	/**
	 * 查询所有板块列表
	 * @author zhaolei
	 * @date 2015年7月16日
	 * @return
	 */
	List<BbsForm> queryAll();
	/**
	 * 保存版块
	 * @author zhaolei
	 * @date 2015年7月16日
	 * @param form
	 * @return
	 */
	Long saveForm(BbsForm form);
	/**
	 * 修改版块
	 * @author zhaolei
	 * @date 2015年7月16日
	 * @param form
	 * @return
	 */
	Long updateForm(BbsForm form);
	/**
	 * 获取版块相关信息
	 * @author zhaolei
	 * @date 2015年7月28日
	 * @param id
	 * @return
	 */
	BbsForm getFormById(Long id);
	/**
	 * 根据权限标签值返回
	 * @author zhaolei
	 * @date 2015年8月4日
	 * @param perTag 权限标签
	 * @return
	 */
	BbsForm getFormByPerTag(String perTag);
}
