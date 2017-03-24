package com.ibaixiong.bbs.service;

public interface TaskService {
	/**
	 * 活跃度计算
	 * @author zhaolei
	 * @date 2015年11月13日
	 */
	void active_up();
	/**
	 * 用户等级,积分计算
	 * @author zhaolei
	 * @date 2015年11月13日
	 */
	void user_up();
	/**
	 * 活动帖子结束
	 * @author zhaolei
	 * @date 2015年11月13日
	 */
	void activity_up();
}
