package com.ibaixiong.bbs.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ibaixiong.bbs.dao.ActiveHistoryCountDao;
import com.ibaixiong.bbs.dao.ActiveHistoryDao;
import com.ibaixiong.bbs.dao.BbsArticleDao;
import com.ibaixiong.bbs.dao.PointsHistoryDao;
import com.ibaixiong.bbs.dao.UserDao;
import com.ibaixiong.bbs.service.TaskService;
import com.ibaixiong.constant.Constant;
import com.ibaixiong.entity.ActiveHistory;
import com.ibaixiong.entity.ActiveHistoryCount;
import com.ibaixiong.entity.BbsArticle;
import com.ibaixiong.entity.PointsHistory;
import com.ibaixiong.entity.User;
@Service
public class TaskServiceImpl implements TaskService{
	@Resource
	PointsHistoryDao pointsHistoryDao;
	@Resource
	UserDao userDao;
	@Resource
	ActiveHistoryDao activeHistoryDao;
	@Resource
	ActiveHistoryCountDao activeHistoryCountDao;
	@Resource
	BbsArticleDao bbsArticleDao;
	@Override
	public void active_up() {
		List<ActiveHistory> list = activeHistoryDao.queryActiveListGroupByUserForm();
		if(list != null && list.size()>0){
			for (ActiveHistory activeHistory : list) {
				if(activeHistory.getUser() != null && activeHistory.getForm() != null){
					ActiveHistoryCount activeHistoryCount = activeHistoryCountDao.getByUserAndForm(activeHistory.getForm().getId(), activeHistory.getUser().getId());
					if(activeHistoryCount != null){
						int t = 0;
						int j = 0;
						if(activeHistoryCount.getActiveNum() != null){
							t = activeHistoryCount.getActiveNum();
						}
						if(activeHistory.getActiveNum() != null){
							j = activeHistory.getActiveNum();
						}
						int activeNum = t+j;
						activeHistoryCount.setActiveNum(activeNum);
						activeHistoryCountDao.updateByPrimaryKeySelective(activeHistoryCount);
					}else{
						//等于null说明没有查到该用户相关信息新增一下
						activeHistoryCount = new ActiveHistoryCount();
						activeHistoryCount.setUser(activeHistory.getUser());
						activeHistoryCount.setForm(activeHistory.getForm());
						activeHistoryCount.setActiveNum(activeHistory.getActiveNum());
						activeHistoryCount.setCreateDateTime(new Date());
						activeHistoryCount.setStatus(Constant.Status.NORMAL.getStatus());
						activeHistoryCountDao.insertSelective(activeHistoryCount);
					}
				}
			}
		}
	}

	@Override
	public void user_up() {
		//计算用户等级和积分，经验
		//按照用户分组查询需要变化积分与等级的
		List<PointsHistory> list =pointsHistoryDao.queryPointsHistoryGroupByUserId();
		if(list != null && list.size()>0){
			for (PointsHistory pointsHistory : list) {
				if(pointsHistory.getUserId() != null){
					User user = userDao.selectByPrimaryKey(pointsHistory.getUserId());
					if(user != null){
						if(user.getExpNum() == null){
							user.setExpNum(0);
						}
						if(user.getPoints() == null){
							user.setPoints(0);
						}
						int exp = user.getExpNum()+pointsHistory.getExpNum();
						int points = user.getPoints()+pointsHistory.getPointsNum();
						Long grade = 0L;
						//设置等级
						if(exp<80){//一级
							grade = 1L;
						}
						if(exp>=80 && exp<200){//2级
							grade = 2L;
						}
						if(exp>=200 && exp<350){//3级
							grade = 3L;
						}
						if(exp>=350 && exp<550){//4级
							grade = 4L;
						}
						if(exp>550){//5级
							grade = 5L;
						}
						user.setPoints(points);
						user.setExpNum(exp);
						user.setGradeId(grade);
						userDao.updateExpPointsGradeById(user);
					}
				}
			}
		}
	}

	@Override
	public void activity_up() {
		List<BbsArticle> list = bbsArticleDao.getIsActivityEndList();
		if(list != null && list.size()>0){
			for (BbsArticle bbsArticle : list) {
				bbsArticle.setActivityIsEnd(1);
				bbsArticleDao.updateByPrimaryKeySelective(bbsArticle);
			}
		}
	}

}
