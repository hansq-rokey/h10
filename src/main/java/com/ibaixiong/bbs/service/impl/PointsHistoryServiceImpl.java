package com.ibaixiong.bbs.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.bbs.dao.ActiveHistoryDao;
import com.ibaixiong.bbs.dao.PointsHistoryDao;
import com.ibaixiong.bbs.dao.PointsTypeDao;
import com.ibaixiong.bbs.service.PointsHistoryService;
import com.ibaixiong.constant.Constant;
import com.ibaixiong.core.utils.DateUtil;
import com.ibaixiong.entity.ActiveHistory;
import com.ibaixiong.entity.BbsForm;
import com.ibaixiong.entity.PointsHistory;
import com.ibaixiong.entity.PointsType;
import com.ibaixiong.entity.User;

@Service
public class PointsHistoryServiceImpl implements PointsHistoryService {
	@Resource
	private PointsTypeDao pointsTypeDao;
	@Resource
	private PointsHistoryDao pointsHistoryDao;
	@Resource
	private ActiveHistoryDao activeHistoryDao;
	@Override
	public void savePointsHistory(Long userId, String tagName) {
		//插入积分记录表是有规则的
		//1.根据积分规则表查询出积分规则
		//2.通过当前已插入的条数判断该次是否允许加积分记录表
		//3.满足条件插入
		PointsType pt = pointsTypeDao.getPointsTypeByTagName(tagName);
		if(pt != null && pt.getOpCounts().intValue()>0){
			//根据当前用户ID和积分规则ID查询当前日期的条数
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			map.put("pointsType", pt.getId());
			map.put("startDate", DateUtil.format(DateUtil.getTodayStartTime(), "yyyy-MM-dd")+" 00:00:00");
			map.put("endDate", DateUtil.format(DateUtil.getTodayEndTime(), "yyyy-MM-dd")+" 23:59:59");
			int i = pointsHistoryDao.getPointsHistoryCount(map);
			if(i<pt.getOpCounts().intValue()){
				//符合条件允许插入数据
				PointsHistory ph = new PointsHistory();
				ph.setUserId(userId);
				ph.setStatus(Constant.Status.NORMAL.getStatus());
				ph.setCreateDateTime(new Date());
				ph.setPointsType(pt.getId());
				ph.setPointsName(pt.getPointsName());
				ph.setPointsNum(pt.getPointsNum());
				ph.setExpNum(pt.getExpNum());
				ph.setActiveNum(pt.getActiveNum());
				pointsHistoryDao.insertSelective(ph);
			}
		}
	}
	@Override
	public void savePointsHistory(Long userId, String tagName, Long formId) {
		//插入积分记录表是有规则的
		//1.根据积分规则表查询出积分规则
		//2.通过当前已插入的条数判断该次是否允许加积分记录表
		//3.满足条件插入
		PointsType pt = pointsTypeDao.getPointsTypeByTagName(tagName);
		if(pt != null && pt.getOpCounts().intValue()>0){
			//根据当前用户ID和积分规则ID查询当前日期的条数
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			map.put("pointsType", pt.getId());
			map.put("startDate", DateUtil.format(DateUtil.getTodayStartTime(), "yyyy-MM-dd")+" 00:00:00");
			map.put("endDate", DateUtil.format(DateUtil.getTodayEndTime(), "yyyy-MM-dd")+" 23:59:59");
			int i = pointsHistoryDao.getPointsHistoryCount(map);
			if(i<pt.getOpCounts().intValue()){
				//符合条件允许插入数据
				PointsHistory ph = new PointsHistory();
				ph.setUserId(userId);
				ph.setStatus(Constant.Status.NORMAL.getStatus());
				ph.setCreateDateTime(new Date());
				ph.setPointsType(pt.getId());
				ph.setPointsName(pt.getPointsName());
				ph.setPointsNum(pt.getPointsNum());
				ph.setExpNum(pt.getExpNum());
				ph.setActiveNum(pt.getActiveNum());
				pointsHistoryDao.insertSelective(ph);
				//同步插入活跃用户表
				ActiveHistory history = new ActiveHistory();
				history.setActiveNum(pt.getActiveNum());
				history.setCreateDateTime(new Date());
				BbsForm form = new BbsForm();
				form.setId(formId);
				history.setForm(form);
				history.setStatus(Constant.Status.NORMAL.getStatus());
				User user = new User();
				user.setId(userId);
				history.setUser(user);
				activeHistoryDao.insertSelective(history);
			}
		}
	}

}
