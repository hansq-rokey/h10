package com.ibaixiong.bbs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.bbs.dao.BbsFormDao;
import com.ibaixiong.bbs.service.FormService;
import com.ibaixiong.entity.BbsForm;

@Service
public class FormServiceImpl implements FormService{
	@Resource
	private BbsFormDao bbsFormDao;

	@Override
	public List<BbsForm> getFormByParentId(Long parentId) {
		return bbsFormDao.getFormByParentId(parentId);
	}

	@Override
	public List<BbsForm> queryAll() {
		return bbsFormDao.queryAll();
	}
	@Override
	public Long saveForm(BbsForm form) {
		return bbsFormDao.insertSelective(form);
	}
	@Override
	public Long updateForm(BbsForm form) {
		return bbsFormDao.updateByPrimaryKeySelective(form);
	}
	@Override
	public BbsForm getFormById(Long id) {
		return bbsFormDao.selectByPrimaryKey(id);
	}

	@Override
	public BbsForm getFormByPerTag(String perTag) {
		return bbsFormDao.getFormByPerTag(perTag);
	}
}
