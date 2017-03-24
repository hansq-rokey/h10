package com.ibaixiong.bbs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.bbs.dao.GradeDao;
import com.ibaixiong.bbs.service.GradeService;
import com.ibaixiong.entity.Grade;
@Service
public class GradeServiceImpl implements GradeService {
	@Resource
	private GradeDao gradeDao;
	@Override
	public List<Grade> getAll() {
		return gradeDao.getAll();
	}

}
