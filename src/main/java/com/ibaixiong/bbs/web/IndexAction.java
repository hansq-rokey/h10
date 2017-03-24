package com.ibaixiong.bbs.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.ibaixiong.bbs.service.ActiveUserService;
import com.ibaixiong.bbs.service.BbsArticleService;
import com.ibaixiong.bbs.service.FormService;
import com.ibaixiong.bbs.util.FormTag;
import com.ibaixiong.entity.BbsArticle;
import com.ibaixiong.entity.BbsForm;

/**
 * 基础数据管理
 * @description
 * @author zhaolei
 * @create 2015年7月27日
 */
@Controller
public class IndexAction {
	@Resource
	private ActiveUserService activeUserService;
	@Resource
	private BbsArticleService bbsArticleService;
	@Resource
	private FormService formService;
	
	/**
	 * 公共查询版块下级列表
	 * @author zhaolei
	 * @date 2015年7月28日
	 * @return
	 */
	@RequestMapping("/index.html")
	public String queryFormList(
			Model model){
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
		return "redirect:/news.html";
	}
	
}
