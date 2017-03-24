package com.ibaixiong.bbs.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.github.pagehelper.PageInfo;
import com.ibaixiong.bbs.service.BbsArticleApplyService;
import com.ibaixiong.bbs.service.BbsArticleConllectionService;
import com.ibaixiong.bbs.service.BbsArticleService;
import com.ibaixiong.bbs.service.FormService;
import com.ibaixiong.bbs.service.PointsHistoryService;
import com.ibaixiong.bbs.service.ReplyService;
import com.ibaixiong.bbs.service.SignHistoryService;
import com.ibaixiong.bbs.service.UserService;
import com.ibaixiong.bbs.util.FormTag;
import com.ibaixiong.bbs.util.LoginUserUtils;
import com.ibaixiong.constant.Constant;
import com.ibaixiong.constant.PointsTypeConstant;
import com.ibaixiong.core.utils.ALiYunUtil;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.entity.BbsArticle;
import com.ibaixiong.entity.BbsForm;
import com.ibaixiong.entity.BbsReply;
import com.ibaixiong.entity.Grade;
import com.ibaixiong.entity.SignHistory;
import com.ibaixiong.entity.User;

/**
 * 用户个人信息相关信息
 * @description
 * @author zhaolei
 * @create 2015年7月27日
 */
@Controller
@RequestMapping("/u/user")
public class UUserAction {
	
	Logger logger=LoggerFactory.getLogger(getClass());
	@Resource
	private BbsArticleService bbsArticleService;
	@Resource
	private UserService userService;
	@Resource
	private ReplyService replyService;
	@Resource
	private SignHistoryService signHistoryService;
	@Resource
	private PointsHistoryService pointsHistoryService;
	@Resource
	private BbsArticleApplyService bbsArticleApplyService;
	@Resource
	private BbsArticleConllectionService bbsArticleConllectionService;
	@Resource
	private FormService formService;
	
	@RequestMapping("/index.html")
	public String afterIndex(Model model){
//		BbsForm form = formService.getFormByPerTag(FormTag.tag.BBS.getTag());//第一次加载默认设置为社区模块
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("queryType", 1);//设置查询类型为社区版块
//		map.put("formId", form.getId());//设置查询帖子的版块ID
//		map.put("pageNo", 1);
//		List<BbsArticle> list = bbsArticleService.queryArticleList(map);
//		PageInfo<BbsArticle> pageInfo=new PageInfo<BbsArticle>(list);
//		model.addAttribute("pageInfo",pageInfo);
//		model.addAttribute("bbsList",list);
//		model.addAttribute("queryType", FormTag.tag.BBS.getTag());//默认打开的是社区模块
//		model.addAttribute("headType", FormTag.tag.BBS.getTag());//控制标题头选中属性
		return "redirect:/index.html";
	}
	
	@RequestMapping("/test.html")
	public String test(){
		Subject subject= SecurityUtils.getSubject();
//		boolean flag=subject.isAuthenticated();
//		Object tObject=subject.getPrincipal();
//		subject.login(token);
//		subject.logout();
		SimplePrincipalCollection spc=(SimplePrincipalCollection) subject.getPrincipals();
		Object obj=spc.getPrimaryPrincipal();
		List list=spc.asList();
//		Object o=list.get(1);
		Map<String, String>map=(Map<String, String>) list.get(1);
		String id=map.get("id");//id,email,phone,nickName
		System.out.println("----------");
		return "test";
	}
	
	
	/**
	 * 获取当前登陆人相关信息
	 * @author zhaolei
	 * @date 2015年8月2日
	 * @param response
	 * {
		"code": 0,								 
	    "message": null, 
	    "result": {
	    	"articles": 
	            {
	                "id": 1,					//ID
	                "title": 标题党,				//帖子标题
	                "picurl": "/articList.html",//活动的配图
	            }, 
	    	}
		}
	 */
	@RequestMapping("/getUserInfo.html")
	public void getUserInfo(
			HttpServletResponse response) {
		User loginUser = LoginUserUtils.getLoginUser();
		Map<String, Object> map = new HashMap<String, Object>();
		int code = 0;
		String msg  = "";
		/*
		if(loginUser == null){
			loginUser = new User();
			loginUser.setId(1L);
		}*/
		if(loginUser != null && loginUser.getId() != null){
			//获取用户相关信息
			User user = userService.getUserInfoById(loginUser.getId());
			Map<String, Object> mapData = new HashMap<String, Object>();
			mapData.put("headImg", user.getAvatarImg());//头像
			mapData.put("bxNum", user.getBxNum());//用户名
			mapData.put("nickName", user.getNickName());//昵称
			mapData.put("points", user.getPoints());//积分
			mapData.put("expNum", user.getExpNum());//经验
			Grade g=user.getGradeEntity();
			if(g!=null)
				mapData.put("gradeImg", user.getGradeEntity().getUrl());//等级
			mapData.put("expNum", user.getExpNum());//经验
			mapData.put("roleNames", user.getRoleNames());//用户所属角色
			map.put("user", mapData);
		}else{
			code = 1;
			msg = "用户未登陆";
		}
		PrintWriter writer = null;
        try {
       	 	writer = response.getWriter();
            writer.write(JSON.toJSONString(ResponseResult.result(code, msg,map),SerializerFeature.WriteMapNullValue));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
	}
	
	@RequestMapping("/index/{headType}.html")
	public String index(
			@PathVariable String headType,
			@RequestParam(value = "queryType", required = false) Integer queryType,
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			@RequestParam(value="openType",required = false)Integer openType,
			Model model){
		if(queryType == null || queryType.intValue() == 0){
			queryType = 1;
		}
		//首次查询一下个人发布的帖子列表
		User loginUser = LoginUserUtils.getLoginUser();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("queryType",queryType);//查询个人1发布的帖子,2回复的帖子,3收藏的帖子
		map.put("userId", loginUser.getId());//查询人员id为登录人的ID
		map.put("pageNo", pageNo);
		if(queryType.intValue() != 4){
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
			if(queryType.intValue() == 3){
				model.addAttribute("collectionList", list);//收藏列表
			}else{
				model.addAttribute("collectionList", null);//收藏列表
			}
		}
		//查询帖子动态
		//1.登录用户发布的帖子
		if(queryType.intValue() == 4){
			List<BbsArticle> list1 = bbsArticleService.queryArticleByUserId(loginUser.getId(),pageNo);
			List<Long> listItem = new ArrayList<Long>();
			for (BbsArticle bbsArticle : list1) {
				listItem.add(bbsArticle.getId());
			}
			if(listItem.size()>0){
				List<BbsReply> list = replyService.queryReplyListByArticleIds(listItem,pageNo);
				PageInfo<BbsReply> pageInfo=new PageInfo<BbsReply>(list);
				model.addAttribute("pageInfo",pageInfo);
				model.addAttribute("dynamicList",list);
			}else
				model.addAttribute("dynamicList",null);
		}else{
			model.addAttribute("dynamicList", null);//收藏列表
		}
		model.addAttribute("headType", headType);//控制标题头选中属性
		model.addAttribute("userId", loginUser.getId());//获取用户信息
		model.addAttribute("queryType", queryType);//查询类型
		model.addAttribute("openType", openType);//是否有展开菜单类型
		return "user";
	}
	
	/**
	 * 帖子动态
	 * @author zhaolei
	 * @date 2015年8月3日
	 * @param queryType
	 * @param model
	 * @return
	 */
	/*@RequestMapping("/queryDynamicArticleList.html")
	public String queryDynamicArticleList(
			Model model){
		//获取登录用户
		User loginUser = LoginUserUtils.getLoginUser();
		//1.登录用户发布的帖子
		List<BbsArticle> list = bbsArticleService.queryArticleByUserId(loginUser.getId());
		//2.查询回复记录
		List<Long> listItem = new ArrayList<Long>();
		for (BbsArticle bbsArticle : list) {
			listItem.add(bbsArticle.getId());
		}
		if(listItem.size()>0){
			model.addAttribute("replyList",replyService.queryReplyListByArticleIds(listItem));
		}else{
			model.addAttribute("replyList",null);
		}
		return "replyList";
	}*/
	
	/**
	 * 签到
	 * @author zhaolei
	 * @date 2015年7月30日
	 * @param article
	 * @param response
	 */
	@RequestMapping("/sign.html")
	public void sign(
			HttpServletResponse response) {
		//查询今天是否已签到
		User loginUser = LoginUserUtils.getLoginUser();
		Date date = new Date();
		Integer year = date.getYear()+1900;
		Integer month = date.getMonth()+1;
		Integer day = date.getDate();
		SignHistory sh = signHistoryService.checkSign(loginUser.getId(), year, month, day);
		int code = 0;
		String msg = "";
		if(sh == null){
			sh = new SignHistory();
			sh.setUserId(loginUser.getId());
			sh.setYear(year);
			sh.setMonth(month);
			sh.setDay(day);
			sh.setCreateDateTime(date);
			sh.setStatus(Constant.Status.NORMAL.getStatus());
			signHistoryService.saveSignHistory(sh);
			//插入积分记录表
			pointsHistoryService.savePointsHistory(loginUser.getId(), PointsTypeConstant.Tag.SIGN.getTag());
		}else{
			code = 1;
			msg = "今日已签到，明日在来吧!";
		}
		PrintWriter writer = null;
        try {
       	 	writer = response.getWriter();
            writer.write(JSON.toJSONString(ResponseResult.result(code, msg)));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
	}
	/**
	 * 是否签到
	 * @author zhaolei
	 * @date 2015年7月30日
	 * @param article
	 * @param response
	 */
	@RequestMapping("/isSign.html")
	public void isSign(
			HttpServletResponse response) {
		//查询今天是否已签到
		User loginUser = LoginUserUtils.getLoginUser();
		Date date = new Date();
		Integer year = date.getYear()+1900;
		Integer month = date.getMonth()+1;
		Integer day = date.getDate();
		SignHistory sh = signHistoryService.checkSign(loginUser.getId(), year, month, day);
		int code = 0;
		String msg = "";
		if(sh != null){
			code = 1;
		}
		PrintWriter writer = null;
        try {
       	 	writer = response.getWriter();
            writer.write(JSON.toJSONString(ResponseResult.result(code, msg)));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
	}
	/**
	 * 删除收藏的帖子
	 * @author zhaolei
	 * @date 2015年7月30日
	 * @param article
	 * @param response
	 */
	@RequestMapping("/removeCollectionArticle.html")
	public void removeCollectionArticle(
			@RequestParam(value = "articleId", required = false) Long articleId,
			HttpServletResponse response) {
		//查询今天是否已签到
		User loginUser = LoginUserUtils.getLoginUser();
		//根据当前登陆用户，选中要删除的帖子进行删
		bbsArticleConllectionService.remove(loginUser.getId(), articleId);
		int code = 0;
		String msg = "";
		PrintWriter writer = null;
        try {
       	 	writer = response.getWriter();
            writer.write(JSON.toJSONString(ResponseResult.result(code, msg)));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
	}
	/**
	 * 删除收藏的帖子
	 * @author zhaolei
	 * @date 2015年7月30日
	 * @param article
	 * @param response
	 */
	@RequestMapping("/removeArticle.html")
	public void removeArticle(
			@RequestParam(value = "articleId", required = false) Long articleId,
			HttpServletResponse response) {
		//查询今天是否已签到
		User loginUser = LoginUserUtils.getLoginUser();
		//根据当前登陆用户，选中要删除的帖子进行删
		bbsArticleService.remove(loginUser.getId(), articleId);
		int code = 0;
		String msg = "";
		PrintWriter writer = null;
        try {
       	 	writer = response.getWriter();
            writer.write(JSON.toJSONString(ResponseResult.result(code, msg)));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
	}
	@RequestMapping("/setNickName.html")
	public void setNickName(
			@RequestParam(value = "nickName", required = false) String nickName,
			HttpServletResponse response) {
		//查询今天是否已签到
		User loginUser = LoginUserUtils.getLoginUser();
		//根据当前登陆用户，选中要删除的帖子进行删
		loginUser.setNickName(nickName);
		userService.updateNickName(loginUser);
		int code = 0;
		String msg = "";
		PrintWriter writer = null;
        try {
       	 	writer = response.getWriter();
            writer.write(JSON.toJSONString(ResponseResult.result(code, msg)));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.close();
        }
	}
	@RequestMapping("/saveHeadImg/{headType}.html")
	public String saveHeadImg(
			@PathVariable String headType,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request,
			Model model){
		User loginUser = LoginUserUtils.getLoginUser();
		String fileUrl = "";
		if(file != null&&!file.isEmpty()&&file.getSize()>0){
			String fileName=file.getOriginalFilename();
			String contentType=fileName.substring(fileName.lastIndexOf(".")+1, fileName.length());
			String key=ALiYunUtil.createUserKey(contentType, loginUser.getId());
			try {
				ALiYunUtil.uploadFile(key, file);
			} catch (OSSException e) {
				e.printStackTrace();
			} catch (ClientException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        fileUrl = ALiYunUtil.IMAGE_URL + key;
		}
		
		loginUser.setAvatarImg(fileUrl);
		userService.updateHeadImg(loginUser);
		return "redirect:/u/user/index/"+headType+".html";
	}
	public static void main(String[] args) {
		String s = "1212.1212.1212.jpg.";
		System.out.println(s.substring(s.lastIndexOf("."), s.length()));
	}
}
