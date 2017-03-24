package com.ibaixiong.bbs.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.ibaixiong.bbs.service.BbsArticlePariseService;
import com.ibaixiong.bbs.service.BbsArticleService;
import com.ibaixiong.bbs.service.FormService;
import com.ibaixiong.bbs.service.ReplyService;
import com.ibaixiong.bbs.util.LoginUserUtils;
import com.ibaixiong.bbs.util.Response;
import com.ibaixiong.constant.Constant;
import com.ibaixiong.core.utils.DateUtil;
import com.ibaixiong.entity.BbsArticle;
import com.ibaixiong.entity.BbsArticleParise;
import com.ibaixiong.entity.BbsForm;
import com.ibaixiong.entity.User;

/**
 * 帖子相关
 * @description
 * @author zhaolei
 * @create 2015年7月27日
 */
@Controller
public class ArticleAction {
	@Resource
	private BbsArticleService bbsArticleService;
	@Resource
	private FormService formService;
	@Resource
	private ReplyService replyService;
	@Resource
	private BbsArticlePariseService bbsArticlePariseService;
	
	/**
	 * 获取版块的头信息
	 * @author zhaolei
	 * @date 2015年7月28日
	 * @param formId
	 * @param model
	 * @return
	
	@RequestMapping("/getForm.html")
	public String getForm(
			@RequestParam(value = "formId", required = false) Long formId,
			Model model){
		model.addAttribute("form", formService.getFormById(formId));
		return "form";
	} */
	/**
	 * 查询社区版块数据列表
	 * @author zhaolei
	 * @date 2015年7月28日
	 * @return
	 */
	@RequestMapping("/bbs.html")
	public String queryBbsList(
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			Model model){
		Long formId = 4l;
		BbsForm form = formService.getFormById(formId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("queryType", 1);//设置查询类型为社区版块
		map.put("formId", form.getId());//设置查询帖子的版块ID
		map.put("pageNo", pageNo);
		List<BbsArticle> list = bbsArticleService.queryArticleList(map);
		list = setArticleBean(list);
		PageInfo<BbsArticle> pageInfo=new PageInfo<BbsArticle>(list);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("bbsList",list);
		model.addAttribute("queryType", form.getPermissionsTag());//模块标示符
		model.addAttribute("headType", form.getPermissionsTag());//控制标题头选中属性
		return "index";
	}
	
	/**
	 * 
	 * @author yaoweiguo
	 * @date 2016年8月11日
	 * @param pageNo
	 * @param model
	 * @return
	 */
	@RequestMapping("/{PerTag}.html")
	public String queryNewsList(@PathVariable String PerTag,
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			Model model){
		BbsForm form =formService.getFormByPerTag(PerTag);
		//跳转至错误页面
		if(form==null){
			return "";
		}
		if(form.getDisplayType().equalsIgnoreCase("common")){
			List<BbsArticle> list=bbsArticleService.queryArticleCommonList(form.getId(), pageNo);
			list = setArticleBean(list);
			PageInfo<BbsArticle> pageInfo=new PageInfo<BbsArticle>(list);
			model.addAttribute("pageInfo",pageInfo);
			model.addAttribute("bbsList",list);
			model.addAttribute("queryType", form.getPermissionsTag());//模块标示符
			model.addAttribute("headType", form.getPermissionsTag());//控制标题头选中属性			
			return form.getDisplayType()+"_list";
		}else{
			return null;
		}
		
	}
	
	/**
	 * 资讯接口
	 * @author yaoweiguo
	 * @date 2016年8月12日
	 * @param formId
	 * @param pageNo
	 * @param pageSize
	 * @since V1.5.0
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/article/api/{formId}")
	public String articleApi(@PathVariable Long formId,HttpServletRequest request,
			@RequestParam(value="pageNo",defaultValue="0")Integer pageNo,
			@RequestParam(value="size",defaultValue="5")Integer pageSize){
		Response response=new Response();
		List<BbsArticle> list=bbsArticleService.queryArticleCommonListApi(formId, pageNo, pageSize);
		response.setResult(list);
		String jsonpCallback = request.getParameter("jsonpCallback");// ajax客户端请求参数
		return jsonpCallback + "(" + JSON.toJSONString(response) + ")";
	}
	
	
	private List<BbsArticle> setArticleBean(List<BbsArticle> list){
		User loginUser = LoginUserUtils.getLoginUser();
		if(loginUser != null){
			//说明用户登录了设置帖子是否赞了帖子标志
			for (BbsArticle bbsArticle : list) {
				BbsArticleParise parise = bbsArticlePariseService.checkPariseByArticleUser(loginUser.getId(), bbsArticle.getId());
				if(parise != null){
					//说明该登录人为该贴子点过赞
					bbsArticle.setPariseyes(1);
				}
			}
			
		}
		return list;
	}
	/**
	 * 查询产品讨论版块数据列表
	 * @author zhaolei
	 * @date 2015年7月28日
	 * @param formId 版块ID
	 * @param dateType 时间选择下拉 （全部时间、今天、三天、一周、一个月）对应值从0开始
	 * @param sortType 排序选择下拉  （默认排序、回复数、查看数、点赞数）对应值从0开始
	 * @param allType 查询总条件点击 （全部、最新、精华）对应值从1开始
	 * @param model
	 * @return
	 */
	@RequestMapping("/discuss.html")
	public String queryProductList(
			//@RequestParam(value = "formId", required = false) Long formId,//顶级版块ID
			@RequestParam(value = "formId1", required = false) Long formId1,//二级版块Id
			@RequestParam(value = "formId2", required = false) Long formId2,//三级版块ID查询种类时使用
			@RequestParam(value = "dateType", required = false) Integer dateType,//时间选择下拉 （全部时间、今天、三天、一周、一个月、三个月）对应值从0开始
			@RequestParam(value = "sortType", required = false) Integer sortType,//排序选择下拉  （默认排序、回复数、查看数、点赞数）对应值从0开始
			@RequestParam(value = "allType", required = false) Integer allType,//查询总条件点击 （全部、最新、精华）对应值从1开始
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			Model model){
		//根据三个formID确认帖子的查询加载
		//1.第一次加载这两个一定为空
		//设置初始值
		Long formId = 1l;
		if(allType == null){
			allType = 0;
		}
		if(sortType == null){
			sortType = 0;
		}
		Long quyerFormId = 0L;
		BbsForm form2 = null;
		List<BbsForm> form2List = null;
		List<BbsForm> form3List = null;
		if(formId1 == null && formId2 == null){
			List<BbsForm> bfList1 = formService.getFormByParentId(formId);//查询二级版块目录
			if(bfList1 != null && bfList1.size()>0){
				BbsForm b2 = bfList1.get(0);
				formId1 = b2.getId();
				List<BbsForm> bfList2 = formService.getFormByParentId(formId1);//查询三级版块目录
				//设置页面显示数据
				form2 = b2;
				form3List = bfList2;
				if(bfList2 != null && bfList2.size()>0){
					BbsForm b3 = bfList2.get(0);
					formId2 = b3.getId();
					quyerFormId = formId2;//设置默认需要查询的帖子数据
				}
			}
			form2List = bfList1;
		}
		//2.当点击右侧的选择模块时这个一定为空
		else if(formId1 != null &&formId2 == null){
			form2List = formService.getFormByParentId(formId);//查询二级版块目录
			form2 = formService.getFormById(formId1);
			List<BbsForm> bfList2 = formService.getFormByParentId(formId1);//查询三级版块目录
			form3List = bfList2;
			if(bfList2 != null && bfList2.size()>0){
				BbsForm b3 = bfList2.get(0);
				formId2 = b3.getId();
				quyerFormId = formId2;
			}
		}else{
			//这种情况一定是选中了其中的一个小模块了
			quyerFormId = formId2;
			//设置页面需要显示的
			form2 = formService.getFormById(formId1);
			form2List = formService.getFormByParentId(formId);//查询二级版块目录
			List<BbsForm> bfList2 = formService.getFormByParentId(formId1);//查询三级版块目录
			form3List = bfList2;
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("queryType", 2);//设置查询类型为产品版块
		map.put("formId", quyerFormId);//设置查询帖子的版块ID
		map.put("dateType", dateType);
		map.put("sortType", sortType);
		map.put("allType", allType);
		if(allType!= null && allType.intValue()>0){
			//说明点击了查询总条件块的快捷方式其他条件重置不启用
			if(allType == 2){
				//说明是查询最新的帖子查询前三天的
				map.put("startDate", DateUtil.format(DateUtil.reduceDate(3,Short.parseShort("1")))+" 00:00:00");//时间前推三天
				map.put("endDate",  DateUtil.format(DateUtil.getTodayEndTime(), "yyyy-MM-dd")+" 23:59:59");
			}
			if(allType == 3){
				map.put("good", 1);//查询精华帖
			}
		}else{
			if(dateType!= null && dateType.intValue() > 0){//说明选择了除全部时间以外的条件
				if(dateType == 1){
					//今天
					map.put("startDate", DateUtil.format(DateUtil.getTodayStartTime(), "yyyy-MM-dd")+" 00:00:00");
				}
				if(dateType == 2){
					//三天
					map.put("startDate", DateUtil.format(DateUtil.reduceDate(3,Short.parseShort("1")), "yyyy-MM-dd")+" 00:00:00");
				}
				if(dateType == 3){
					//一周
					map.put("startDate", DateUtil.format(DateUtil.reduceDate(7,Short.parseShort("1")), "yyyy-MM-dd")+" 00:00:00");
				}
				if(dateType == 4){
					//一个月
					map.put("startDate", DateUtil.format(DateUtil.reduceDate(1,Short.parseShort("2")), "yyyy-MM-dd")+" 00:00:00");
				}
				if(dateType == 5){
					//三个月
					map.put("startDate", DateUtil.format(DateUtil.reduceDate(3,Short.parseShort("2")), "yyyy-MM-dd")+" 00:00:00");
				}
				map.put("endDate", DateUtil.format(DateUtil.getTodayEndTime(), "yyyy-MM-dd")+" 23:59:59");
			}
		}
		map.put("pageNo", pageNo);
		List<BbsArticle> list = bbsArticleService.queryArticleList(map);
		PageInfo<BbsArticle> pageInfo=new PageInfo<BbsArticle>(list);
		list = setArticleBean(list);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("bbsList",list);
		model.addAttribute("formId1",formId1 );
		model.addAttribute("formId2",formId2 );
		model.addAttribute("dateType",dateType );
		model.addAttribute("sortType",sortType );
		model.addAttribute("allType",allType );
		model.addAttribute("form",form2.getParentForm());
		model.addAttribute("form2",form2 );
		model.addAttribute("form2List",form2List );
		model.addAttribute("form3List",form3List );
		model.addAttribute("queryType", form2.getPermissionsTag());//模块标示符
		model.addAttribute("headType", form2.getParentForm().getPermissionsTag());//控制标题头选中属性
		return "productList";
	}
	/**
	 * 查询白熊学院版块数据列表
	 * @author zhaolei
	 * @date 2015年7月28日
	 * @return
	 */
	@RequestMapping("/school.html")
	public String queryBxSchoolList(
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			Model model){
		Long formId = 2l;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("queryType", 3);//设置查询类型为白熊学院版块
		map.put("formId", formId);//设置查询帖子的版块ID
		map.put("pageNo", pageNo);
		List<BbsArticle> list = bbsArticleService.queryArticleList(map);
		list = setArticleBean(list);
		PageInfo<BbsArticle> pageInfo=new PageInfo<BbsArticle>(list);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("bbsList",list);
		BbsForm form = formService.getFormById(formId);
		model.addAttribute("queryType", form.getPermissionsTag());//模块标示符
		model.addAttribute("headType", form.getPermissionsTag());//控制标题头选中属性
		return "bxSchoolList";
	}
	/**
	 * 查询热门活动版块数据列表
	 * @author zhaolei
	 * @date 2015年7月28日
	 * @return
	 */
	@RequestMapping("/activity.html")
	public String queryActivityList(
			@RequestParam(value="pageNo",defaultValue="1")Integer pageNo,
			Model model){
		Long formId = 3l;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("queryType", 4);//设置查询类型为热门活动版块
		map.put("formId", formId);//设置查询帖子的版块ID
		map.put("pageNo", pageNo);
		List<BbsArticle> list = bbsArticleService.queryArticleList(map);
		list = setArticleBean(list);
		PageInfo<BbsArticle> pageInfo=new PageInfo<BbsArticle>(list);
		model.addAttribute("pageInfo",pageInfo);
		model.addAttribute("bbsList",list);
		BbsForm form = formService.getFormById(formId);
		model.addAttribute("queryType", form.getPermissionsTag());//模块标示符
		model.addAttribute("headType", form.getPermissionsTag());//控制标题头选中属性
		return "activityList";
	}
	/**
	 * 帖子详情查看
	 * @author zhaolei
	 * @date 2015年7月29日
	 * @param articleId
	 * @param model
	 * @return
	 */
	@RequestMapping("/detail/{articleId}.html")
	public String articleDetail(
			@PathVariable Long articleId,
			@RequestParam(value = "headType", required = false) String headType,
			@RequestParam(value = "isView", required = false) String isView,
			Model model){
		//点击查看详细时该帖子的查看次数要加1
		bbsArticleService.updateViewCount(articleId);
		/*byte status = Constant.Status.NORMAL.getStatus();
		if(StringUtils.isNotBlank(isView)){
			status = Constant.Status.WAIT.getStatus();
		}*/
		BbsArticle article = bbsArticleService.getArticleDetailById(articleId,null);
		if(StringUtils.isBlank(headType)){
			//说明该值没有传过来需要通过查询获得
			BbsForm form = formService.getFormById(article.getFormId());
			form = getTopForm(form);
			headType = form.getPermissionsTag();
		}
		User loginUser = LoginUserUtils.getLoginUser();
		if(loginUser != null){
			//说明用户登录了设置帖子是否赞了帖子标志
			BbsArticleParise parise = bbsArticlePariseService.checkPariseByArticleUser(loginUser.getId(), article.getId());
			if(parise != null){
				//说明该登录人为该贴子点过赞
				article.setPariseyes(1);
			}
			
		}
		//如果查看的详情是产品讨论则需要查询下版块描述特殊处理
		if(headType.equals("product")){
			BbsForm bf = formService.getFormById(article.getFormId());
			if(bf != null && bf.getParentForm() != null){
				model.addAttribute("form", bf.getParentForm());//版块
			}
		}
		model.addAttribute("articleDetail", article);//帖子相关内容
		model.addAttribute("replyList", replyService.queryReplyListByArticleId(articleId));//帖子回复内容
		model.addAttribute("headType", headType);//控制标题头选中属性
		model.addAttribute("isView", isView);//是否为浏览功能
		return "articleDetail";
	}
	
	/**
	 * 帖子详情查看
	 * @author zhaolei
	 * @date 2015年7月29日
	 * @param articleId
	 * @param model
	 * @return
	 */
	@RequestMapping("/share/detail/{articleId}.html")
	public String shareDetail(
			@PathVariable Long articleId,
			Model model){
		//点击查看详细时该帖子的查看次数要加1
		bbsArticleService.updateViewCount(articleId);
		BbsArticle article = bbsArticleService.getArticleDetailById(articleId,Constant.Status.NORMAL.getStatus());
		model.addAttribute("articleDetail", article);//帖子相关内容
		return "shareDetail";
	}
	private BbsForm getTopForm(BbsForm form){
		if(form.getParentForm() == null){
			return form;
		}else{
			if(form.getParentForm().getId().intValue()==0){
				return form;
			}else{
				BbsForm form1 = formService.getFormById(form.getParentForm().getId());
				return getTopForm(form1);
			}
		}
	}
}
