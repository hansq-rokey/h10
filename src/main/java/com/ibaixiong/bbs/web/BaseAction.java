package com.ibaixiong.bbs.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.ibaixiong.bbs.service.ActiveUserService;
import com.ibaixiong.bbs.service.BbsArticleService;
import com.ibaixiong.bbs.service.FormService;
import com.ibaixiong.bbs.service.GradeService;
import com.ibaixiong.bbs.service.UserService;
import com.ibaixiong.bbs.util.LoginUserUtils;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.entity.ActiveHistoryCount;
import com.ibaixiong.entity.BbsArticle;
import com.ibaixiong.entity.BbsForm;
import com.ibaixiong.entity.Grade;
import com.ibaixiong.entity.User;

/**
 * 基础数据管理
 * @description
 * @author zhaolei
 * @create 2015年7月27日
 */
@Controller
@RequestMapping("/base")
public class BaseAction {
	@Resource
	private ActiveUserService activeUserService;
	@Resource
	private BbsArticleService bbsArticleService;
	@Resource
	private FormService formService;
	@Resource
	private UserService userService;
	@Resource
	private GradeService gradeService;
	
	/**
	 * 公共查询版块下级列表
	 * @author zhaolei
	 * @date 2015年7月28日
	 * @return
	 * 
	 * {
		"code": 0,								 
	    "message": null, 
	    "result": {
	    	"form": [
	            {
	                "id": 1,					//ID
	                "name": 社区,					//名字
	                "url": "/articList.html",   //访问的路径
	                "tag": "bbs"
	            }, 
	            ......
	        ]
	    	}
		}
	 */
	@RequestMapping("/queryFormList.html")
	public void queryFormList(
			@RequestParam(value = "formId", required = false) Long formId,
			HttpServletResponse response){
		if(formId == null)
			formId = 0L;
		//改为ajax请求获取数据
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapData = new ArrayList<Map<String,Object>>();
		List<BbsForm> formList = formService.getFormByParentId(formId);
		for (BbsForm bbsForm : formList) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", bbsForm.getId());
			m.put("name", bbsForm.getName());
			m.put("url", bbsForm.getUrl());
			m.put("tag", bbsForm.getPermissionsTag());
			mapData.add(m);
		}
		map.put("form", mapData);
		String outStr = JSON.toJSONString(ResponseResult.result(0, "",map));
		PrintWriter writer = null;
        try {
       	 	writer = response.getWriter();
            writer.write(outStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
	}
	/**
	 * 公共查询活跃用户前五个根据版块查询
	 * @author zhaolei
	 * @date 2015年7月27日
	 * @return
	 * {
		"code": 0,								 
	    "message": null, 
	    "result": {
	    	"users": [
	            {
	                "id": 1,					//ID
	                "nickName": 小赛,				//昵称
	                "headImg": "1.png",//头像
	                "gradeImg": "1.png",//等级
	            }, 
	            ......
	        ]
	    	}
		}
	 */
	@RequestMapping("/queryActiveUser.html")
	public void queryActiveUser(
			@RequestParam(value = "tag", required = false) String tag,
			HttpServletResponse response){
		//通过指定的标签值查form
		BbsForm form = formService.getFormByPerTag(tag);
		//查询子模块的
		List<BbsForm> forms = formService.getFormByParentId(form.getId());
		List<ActiveHistoryCount> list =null;
		if(forms != null && forms.size()>0){
			List<Long> item = new ArrayList<Long>();
			for (BbsForm tform : forms) {
				item.add(tform.getId());
			}
			//说明有子模块
			list = activeUserService.getActiveUserByFormIds(item);
		}else{
		  //这说明没有子模块
		  list = activeUserService.getActiveUserByFormId(form.getId());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapData = new ArrayList<Map<String,Object>>();
		for (ActiveHistoryCount ahc : list) {
			Map<String, Object> m = new HashMap<String, Object>();
			User user = ahc.getUser();
			if(user != null){
				m.put("id", user.getId());
				m.put("nickName", user.getNickName());//昵称
				m.put("headImg", user.getAvatarImg());//头像
				Grade g = user.getGradeEntity();
				if(g != null){
					m.put("gradeImg", g.getUrl());//等级图片
				}
				mapData.add(m);
			}
		}
		map.put("users", mapData);
		String outStr = JSON.toJSONString(ResponseResult.result(0, "", map));
		System.out.println(outStr);
		PrintWriter writer = null;
        try {
       	 	writer = response.getWriter();
            writer.write(outStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
	}
	/**
	 * 查询活动帖子前三个
	 * @author zhaolei
	 * @date 2015年7月28日
	 * @param model
	 * @return
	 * {
		"code": 0,								 
	    "message": null, 
	    "result": {
	    	"articles": [
	            {
	                "id": 1,					//ID
	                "title": 标题党,				//帖子标题
	                "picurl": "/articList.html",//活动的配图
	            }, 
	            ......
	        ]
	    	}
		}
	 */
	@RequestMapping("/queryActiveArticleTop3.html")
	public void queryActiveArticleTop3(
			HttpServletResponse response){
		List<BbsArticle> list = bbsArticleService.queryActiveArticleT3();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapData = new ArrayList<Map<String,Object>>();
		for (BbsArticle ahc : list) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", ahc.getId());
			m.put("title", ahc.getTitle());//帖子标题
			m.put("picurl", ahc.getPicUrl());//帖子配图
			mapData.add(m);
		}
		map.put("articles", mapData);
		String outStr = JSON.toJSONString(ResponseResult.result(0, "", map));
		System.out.println(outStr);
		PrintWriter writer = null;
        try {
       	 	writer = response.getWriter();
            writer.write(outStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
	}
	/**
	 * 查询等级列表
	 * @author zhaolei
	 * @date 2015年7月28日
	 * @param model
	 * @return
	 * {
		"code": 0,								 
	    "message": null, 
	    "result": {
	    	"grades": [
	            {
	                "id": 1,					//ID
	                "name": 一级				//
	            }, 
	            ......
	        ]
	    	}
		}
	 */
	@RequestMapping("/gradeList.html")
	public void gradeList(
			HttpServletResponse response){
		List<Grade> list = gradeService.getAll();
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> mapData = new ArrayList<Map<String,Object>>();
		for (Grade ahc : list) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("id", ahc.getId());
			m.put("name", ahc.getName());//名字
			mapData.add(m);
		}
		map.put("grades", mapData);
		String outStr = JSON.toJSONString(ResponseResult.result(0, "", map));
		System.out.println(outStr);
		PrintWriter writer = null;
        try {
       	 	writer = response.getWriter();
            writer.write(outStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
	}
}
