package com.ibaixiong.task;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ibaixiong.bbs.service.TaskService;
/**
 * 测试自动任务类的调度
 * @author zhaolei
 *
 */
@Component
public class MyTask {
	@Resource
	private TaskService taskService;
	 //晚上一点钟执行计算用户等级等 
	//@Scheduled(cron="30 53 15 * * ?")
	@Scheduled(cron="0 0 1 * * ?")
    public void taskUser(){  
		//taskService = ApplicationContextUtil.getContext().getBean("taskService", TaskService.class);
		taskService.user_up();
    }
	//@Scheduled(cron="20 21 16 * * ?") //计算活跃度
	@Scheduled(cron="0 0 2 * * ?")
    public void taskActive(){  
		//taskService = ApplicationContextUtil.getContext().getBean("taskService", TaskService.class);
		taskService.active_up();
    }
	//@Scheduled(cron="20 27 16 * * ?")//计算活动帖子停止时间
	@Scheduled(cron="0 0 23 * * ?")
    public void taskActivity(){  
		//taskService = ApplicationContextUtil.getContext().getBean("taskService", TaskService.class);
		taskService.activity_up();
    }
}
