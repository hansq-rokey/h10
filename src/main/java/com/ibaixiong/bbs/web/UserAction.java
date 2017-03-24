package com.ibaixiong.bbs.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.github.pagehelper.PageInfo;
import com.ibaixiong.bbs.service.BbsArticleService;
import com.ibaixiong.bbs.service.UserService;
import com.ibaixiong.bbs.util.LoginUserUtils;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.entity.BbsArticle;
import com.ibaixiong.entity.Grade;
import com.ibaixiong.entity.User;

/**
 * 用户个人信息相关信息
 * @description
 * @author zhaolei
 * @create 2015年7月27日
 */
@Controller
public class UserAction {
	@Resource
	private BbsArticleService bbsArticleService;
	@Resource
	private UserService userService;
	@RequestMapping("/user/index/{userId}/{headType}.html")
	public String index(
			@PathVariable Long userId,
			@PathVariable String headType,
			@RequestParam(value = "queryType", required = false) Integer queryType,
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			@RequestParam(value="openType",required = false)Integer openType,
			Model model){
		if(queryType == null || queryType.intValue() == 0){
			queryType = 1;
		}
		//首次查询一下个人发布的帖子列表
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("queryType",queryType);//查询个人1发布的帖子,2回复的帖子
		map.put("userId", userId);//查询人员id为登录人的ID
		map.put("pageNo", pageNo);
		List<BbsArticle> list = bbsArticleService.queryUserArticleList(map);
		PageInfo<BbsArticle> pageInfo=new PageInfo<BbsArticle>(list);
		model.addAttribute("pageInfo",pageInfo);
		if(queryType.intValue() == 1){
			model.addAttribute("publishList", list);//发布帖子列表
		}else{
			model.addAttribute("publishList", null);//发布帖子列表
		}
		if(queryType.intValue() == 2){
			model.addAttribute("replyList", list);//回复列表
		}else{
			model.addAttribute("replyList", null);//回复列表
		}
		model.addAttribute("userId", userId);//获取用户信息
		model.addAttribute("headType", headType);//控制标题头选中属性
		model.addAttribute("queryType", queryType);//查询类型
		model.addAttribute("openType", openType);//是否有展开菜单类型
		return "userOther";
	}
	/**
	 * 获取当前登陆人相关信息
	 * @author zhaolei
	 * @date 2015年8月2日
	 * @param response
	 */
	@RequestMapping("/user/getUserInfo.html")
	public void getUserInfo(
			@RequestParam(value = "userId", required = false) Long userId,
			HttpServletResponse response) {
		Map<String, Object> mapData = new HashMap<String, Object>();
		User loginUser = LoginUserUtils.getLoginUser();
		mapData.put("isupdate", 0);//是否让修改 0不让修改
		//如果登陆了并且，点过来的是自己的头像才让修改头像标志
		if(loginUser!=null){
			if(loginUser.getId().longValue() == userId){
				mapData.put("isupdate", 1);//是否让修改
			}
		}
		//获取用户相关信息
		User user = userService.getUserInfoById(userId);
		Map<String, Object> map = new HashMap<String, Object>();
		mapData.put("headImg", user.getAvatarImg());//头像
		mapData.put("bxNum", user.getBxNum());//用户名
		mapData.put("nickName", user.getNickName());//昵称
		mapData.put("points", user.getPoints());//积分
		Grade g=user.getGradeEntity();
		if(g!=null)
			mapData.put("gradeImg", user.getGradeEntity().getUrl());//等级
		mapData.put("expNum", user.getExpNum());//经验
		mapData.put("roleNames", user.getRoleNames());//用户所属角色
		map.put("user", mapData);
		PrintWriter writer = null;
        try {
       	 	writer = response.getWriter();
            writer.write(JSON.toJSONString(ResponseResult.result(0, "",map),SerializerFeature.WriteMapNullValue));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
	}
}
