package com.ibaixiong.bbs.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import com.ibaixiong.bbs.service.BbsArticleApplyService;
import com.ibaixiong.bbs.service.BbsArticleConllectionService;
import com.ibaixiong.bbs.service.BbsArticleDetailService;
import com.ibaixiong.bbs.service.BbsArticlePariseService;
import com.ibaixiong.bbs.service.BbsArticleService;
import com.ibaixiong.bbs.service.BbsFormArticleService;
import com.ibaixiong.bbs.service.BbsReportService;
import com.ibaixiong.bbs.service.FormService;
import com.ibaixiong.bbs.service.GradeService;
import com.ibaixiong.bbs.service.PointsHistoryService;
import com.ibaixiong.bbs.service.ReplyService;
import com.ibaixiong.bbs.service.SignHistoryService;
import com.ibaixiong.bbs.service.UserService;
import com.ibaixiong.bbs.util.FormTag;
import com.ibaixiong.bbs.util.LoginUserUtils;
import com.ibaixiong.bbs.util.Response;
import com.ibaixiong.constant.Constant;
import com.ibaixiong.constant.PointsTypeConstant;
import com.ibaixiong.core.utils.ALiYunUtil;
import com.ibaixiong.core.utils.DateUtil;
import com.ibaixiong.core.utils.ResponseResult;
import com.ibaixiong.entity.BbsArticle;
import com.ibaixiong.entity.BbsArticleApply;
import com.ibaixiong.entity.BbsArticleDetail;
import com.ibaixiong.entity.BbsArticleParise;
import com.ibaixiong.entity.BbsCollection;
import com.ibaixiong.entity.BbsForm;
import com.ibaixiong.entity.BbsFormArticle;
import com.ibaixiong.entity.BbsReply;
import com.ibaixiong.entity.BbsReport;
import com.ibaixiong.entity.Grade;
import com.ibaixiong.entity.User;
import com.sun.media.sound.FFT;

/**
 * 帖子相关操作，带了/u的路径是需要验证登录后才能操作的标志
 * @description
 * @author zhaolei
 * @create 2015年7月27日
 */
@Controller
@RequestMapping("/u/article")
public class UArticleAction {
	@Resource
	private BbsArticleService bbsArticleService;
	@Resource
	private BbsFormArticleService bbsFormArticleService;
	@Resource
	private BbsArticleDetailService bbsArticleDetailService;
	@Resource
	private SignHistoryService signHistoryService;
	@Resource
	private PointsHistoryService pointsHistoryService;
	@Resource
	private ReplyService replyService;
	@Resource
	private BbsReportService rbsReportService;
	@Resource
	private BbsArticlePariseService bbsArticlePariseService;
	@Resource
	private BbsArticleConllectionService bbsArticleConllectionService;
	@Resource
	private BbsArticleApplyService bbsArticleApplyService;
	@Resource
	private FormService formService;
	@Resource
	private GradeService gradeService;
	@Resource
	private UserService userService;
	/**
	 * 发布帖子
	 * @author zhaolei
	 * @date 2015年7月30日
	 * @param formId
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/toArticlePublish.html")
	public String toArticlePublish(
			@RequestParam(value = "headType", required = false) String headType,
			@RequestParam(value = "id", required = false) Long id,
			Model model) throws Exception{
		List<BbsForm> formList = null;
		List<Grade> gradeList = null;
		BbsArticle article = new BbsArticle();
		BbsForm checkForm = new BbsForm();
		List<BbsForm> formList2 = null;//三级下拉版块列表
		if(id != null){
			article = bbsArticleService.getArticleDetailById(id,Constant.Status.NORMAL.getStatus());
			if(StringUtils.isBlank(headType) && article != null){
				//说明该值没有传过来需要通过查询获得
				BbsForm form = formService.getFormById(article.getFormId());
				form = getTopForm(form);
				headType = form.getPermissionsTag();
			}
			//设置时间转化
			if(article != null && article.getActivityStartTime()!=null){
				article.setActivityStartTimeStr(DateUtil.formatDateToStringNoTime(article.getActivityStartTime()));
			}
			if(article != null && article.getActivityEndTime()!=null){
				article.setActivityEndTimeStr(DateUtil.formatDateToStringNoTime(article.getActivityEndTime()));
			}
		}
		if(StringUtils.isBlank(headType)){
			headType = FormTag.tag.BBS.getTag();
		}else{
			//社区讨论
			if(headType.equals(FormTag.tag.PRODUCT.getTag())){
				BbsForm form = formService.getFormByPerTag(FormTag.tag.PRODUCT.getTag());
				formList = formService.getFormByParentId(form.getId());
				//如果是修改需要重新设置查询一个之前设置过的form
				if(article.getId()!=null){
					BbsForm sform = formService.getFormById(article.getFormId());
					checkForm = sform.getParentForm();
					formList2 = formService.getFormByParentId(checkForm.getId());
				}
			}
			//活动帖子获取等级列表
			if(headType.equals(FormTag.tag.ACTIVITY.getTag())){
				gradeList = gradeService.getAll();
			}
		}
		//通过这个标志查询相关form对象
		model.addAttribute("headType", headType);
		model.addAttribute("formList", formList);//下拉列表二级
		model.addAttribute("gradeList", gradeList);//下拉列表等级
		model.addAttribute("article", article);
		model.addAttribute("checkForm", checkForm);
		model.addAttribute("formList2", formList2);//下拉列表三级
		return "articlePublish";
	}
	/**
	 * 保存帖子
	 * @author zhaolei
	 * @date 2015年7月30日
	 * @param article 帖子主对象
	 * @param publishType 发布帖子类型
	 * @param formId 帖子主类型
	 * @param checkFormId 选择的最终帖子ID
	 * @param model
	 * @return
	 */
	@RequestMapping("/save.html")
	public String save(
			@ModelAttribute("article") BbsArticle article,
			@RequestParam(value = "headType", required = false) String headType,
			@RequestParam(value = "checkFormId", required = false) Long checkFormId,
			HttpServletRequest request,
			Model model){
		article.setStatus(Constant.Status.NORMAL.getStatus());
		article = saveArticle(article, headType, checkFormId);
		model.addAttribute("headType", headType);
		model.addAttribute("article", article);
		BbsForm form=formService.getFormByPerTag(headType);
		if(headType.equals(FormTag.tag.BBS.getTag()))
			return "redirect:/bbs.html";
		if(headType.equals(FormTag.tag.PRODUCT.getTag()))
			return "redirect:/discuss.html";
		if(headType.equals(FormTag.tag.SCHOOL.getTag()))
			return "redirect:/school.html";
		if(headType.equals(FormTag.tag.ACTIVITY.getTag()))
			return "redirect:/activity.html";
		return "redirect:"+form.getUrl();
	}
	@ResponseBody
	@RequestMapping("/saveView.html")
	public String saveView(
			@ModelAttribute("article") BbsArticle article,
			@RequestParam(value = "headType", required = false) String headType,
			@RequestParam(value = "checkFormId", required = false) Long checkFormId,
			HttpServletRequest request,
			Model model){
		BbsArticleDetail detail = new BbsArticleDetail();
		detail.setMemo(article.getDetailMemo());
		detail.setContent(article.getDetailContent());
		article.setDetail(detail);
		if(article.getId() == null){
			//如果ID为空新增状态为待审核
			article.setStatus(Constant.Status.WAIT.getStatus());
		}
		article = saveArticle(article, headType, checkFormId);
		Response respone = new Response();
		respone.setSuccess(Boolean.TRUE);
		respone.setMessage("保存成功");
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("id", article.getId());
		respone.setResult(map);
		return JSON.toJSONString(respone);
	}
	private BbsArticle saveArticle(BbsArticle article,String headType,Long checkFormId){
		User loginUser = LoginUserUtils.getLoginUser();
		//设置基础默认信息
		//登录用户信息
		article.setUser(loginUser);//帖子发布人
		article.setIsOriginal(Byte.parseByte("1"));//是否原创
		article.setViewCount(0);
		article.setPariseCount(0);
		article.setApplyCount(0);//申请人数
		article.setReplyCount(0);
		article.setIsReport(Byte.parseByte("0"));//是否被举报 0未被举报 1被举报
		article.setReplyCount(0);//回复次数
		article.setReportcount(0);//被举报的次数
		article.setCreateDateTime(new Date());
		//article.setPicUrl(fileUrl);//设置图片
		if(StringUtils.isNotBlank(article.getActivityStartTimeStr())){
			article.setActivityStartTime(DateUtil.parse(article.getActivityStartTimeStr()));
			article.setActivityEndTime(DateUtil.parse(article.getActivityEndTimeStr()));
			article.setActivityIsEnd(0);//活动是否结束
		}
		//TODO 帖子类型，根据版块来填写
		Integer formType = 1;
		if(headType.equals(FormTag.tag.BBS.getTag())){//说明发的是社区帖子
			formType = 1;
		}
		if(headType.equals(FormTag.tag.PRODUCT.getTag())){//说明发的是产品讨论帖子
			formType = 1;
		}
		if(headType.equals(FormTag.tag.SCHOOL.getTag())){//说明发的是白熊学院帖子
			formType = 4;
			article.setStatus(Constant.Status.WAIT.getStatus());
		}
		if(headType.equals(FormTag.tag.ACTIVITY.getTag())){//说明发的是活动帖子
			formType = 2;
		}
		article.setFromType(formType);
		BbsForm form = formService.getFormByPerTag(headType);
		if(article.getId() == null){//新增
			bbsArticleService.saveArticle(article);
			if(checkFormId == null){
				checkFormId = form.getId();
			}
			//保存帖子类型中间表
			saveFArticle(article, checkFormId);
			//保存帖子详细子表
			saveArticleDetail(article);
			//插入积分记录表
			pointsHistoryService.savePointsHistory(loginUser.getId(), PointsTypeConstant.Tag.ARTICLE.getTag(),checkFormId);
		}else{
			bbsArticleService.updateArticle(article);
			bbsFormArticleService.deleteFormArticleByArticleId(article.getId());
			bbsArticleDetailService.deleteArticleDetailByArticleId(article.getId());
			if(checkFormId == null){
				checkFormId = form.getId();
			}
			//保存帖子类型中间表
			saveFArticle(article, checkFormId);
			//保存帖子详细子表
			saveArticleDetail(article);
		}
		return article;
	}
	private void saveFArticle(BbsArticle article,Long checkFormId){
		BbsFormArticle formArticle = new BbsFormArticle();
		formArticle.setArticleId(article.getId());
		formArticle.setFormId(checkFormId);
		formArticle.setCreateDateTime(new Date());
		formArticle.setStatus(Constant.Status.NORMAL.getStatus());
		bbsFormArticleService.saveFormArticle(formArticle);
	}
	private void saveArticleDetail(BbsArticle article){
		BbsArticleDetail ad = article.getDetail();
		ad.setArticle(article);
		ad.setStatus(Constant.Status.NORMAL.getStatus());
		ad.setCreateDateTime(new Date());
		bbsArticleDetailService.saveArticleDetail(ad);
	}
	
	/**
	 * 回复列表中的去回复页面
	 * @author zhaolei
	 * @date 2015年7月30日
	 * @param article
	 * @param response
	 */
	@RequestMapping("/toReply/{replyId}/{headType}.html")
	public String toReply(
			@PathVariable Long replyId,
			@PathVariable String headType,
			Model model){
		model.addAttribute("reply", replyService.getReplyById(replyId));
		//通过ID查询回复相关的对象
		model.addAttribute("headType", headType); 
		return "replyDetail";
	}
	/**
	 * 回复保存
	 * @author zhaolei
	 * @date 2015年7月30日
	 * @param article
	 * @param response
	 */
	@RequestMapping("/replySave.html")
	public String replySave(
			@ModelAttribute("reply")  BbsReply reply,
			@RequestParam(value = "headType", required = false) String headType,
			RedirectAttributes attr,
			Model model){
		User loginUser = LoginUserUtils.getLoginUser();
		reply.setUser(loginUser);
		reply.setCreateDateTime(new Date());
		reply.setStatus(Constant.Status.NORMAL.getStatus());
		if(StringUtils.isNotBlank(reply.getQuoteCreateDateTimeStr())){
			reply.setQuoteCreateDateTime(DateUtil.parse(reply.getQuoteCreateDateTimeStr(),"yyyy-MM-dd hh:mm:ss"));
		}
		replyService.saveReply(reply);
		//修改帖子被回复的次数
		if(reply.getQuoteId() == null || reply.getQuoteId().intValue() == 0){
			//说明回复的是帖子
			bbsArticleService.updateReply(reply.getArticle().getId());
		}
		//插入积分记录表
		BbsArticle article = bbsArticleService.getArticleDetailById(reply.getArticle().getId(),Constant.Status.NORMAL.getStatus());
		pointsHistoryService.savePointsHistory(loginUser.getId(), PointsTypeConstant.Tag.REPLY.getTag(),article.getFormId());
		attr.addAttribute("headType",  headType);//重定向页面时添加条件 
		return "redirect:/detail/"+reply.getArticle().getId()+".html";
	}
	
	/**
	 * 举报帖子
	 * @author zhaolei
	 * @date 2015年7月30日
	 */
	@RequestMapping("/reportArticle.html")
	public void reportArticle(
			@RequestParam(value = "articleId", required = false) Long articleId,
			@RequestParam(value = "reportType", required = false) Integer reportType,
			HttpServletResponse response) {
		//查询是否举报过了，如果举报过了就不在进行举报操作
		String msg = "";
		int code = 0;
		User loginUser = LoginUserUtils.getLoginUser();
		BbsReport report = rbsReportService.checkReportByUserArticle(loginUser.getId(), articleId);
		if(report == null){
			report = new BbsReport();
			report.setUserId(loginUser.getId());
			report.setArticleId(articleId);
			report.setType(reportType);
			report.setContent(getReportTypeContent(reportType));
			report.setCreateDateTime(new Date());
			report.setStatus(Constant.Status.NORMAL.getStatus());
			rbsReportService.save(report);
			//修改帖子的举报状态和举报次数
			bbsArticleService.updateReport(articleId);
			//插入积分记录表
			pointsHistoryService.savePointsHistory(loginUser.getId(), PointsTypeConstant.Tag.REPORTARTICLE.getTag());
		}else{
			code = 1;
			msg = "该帖你已经举报过了，亲请去其他地方逛逛!";
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
	private String getReportTypeContent(int type){
		if(type == 1){
			return "广告垃圾";
		}
		if(type == 2){
			return "恶意灌水";
		}
		if(type == 3){
			return "重复发帖";
		}
		if(type == 4){
			return "谩骂";
		}
		if(type == 5){
			return "违规信息";
		}
		if(type == 6){
			return "其他";
		}
		return "";
	}
	/**
	 * 点赞
	 * @author zhaolei
	 * @date 2015年7月30日
	 */
	@RequestMapping("/pariseArticle.html")
	public void pariseArticle(
			@RequestParam(value = "articleId", required = false) Long articleId,
			HttpServletResponse response) {
		//查询是否对帖子点赞过了，如果帖子点赞过了就不在进行举报操作
		String msg = "";
		int code = 0;
		User loginUser = LoginUserUtils.getLoginUser();
		BbsArticleParise parise = bbsArticlePariseService.checkPariseByArticleUser(loginUser.getId(), articleId);
		if(parise == null){
			parise = new BbsArticleParise();
			User user = new User();
			user.setId(loginUser.getId());
			parise.setUser(user);
			BbsArticle article = bbsArticleService.getArticleByKey(articleId);
			parise.setArticle(article);
			parise.setCreateDateTime(new Date());
			parise.setStatus(Constant.Status.NORMAL.getStatus());
			bbsArticlePariseService.save(parise);
			//修改帖子的点赞次数
			bbsArticleService.updateParise(articleId);
			//插入积分记录表
			//这个记录积分是在帖子上面的发布人
			pointsHistoryService.savePointsHistory(article.getUser().getId(), PointsTypeConstant.Tag.PARISE.getTag());
		}else{
			code = 1;
			msg = "亲，你已经赞美过他了已美的不行了，请去其他地方逛逛!";
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
	 * 收藏
	 * @author zhaolei
	 * @date 2015年7月30日
	 */
	@RequestMapping("/collectionArticle.html")
	public void collectionArticle(
			@RequestParam(value = "articleId", required = false) Long articleId,
			HttpServletResponse response) {
		//查询是否对帖子点赞过了，如果帖子点赞过了就不在进行举报操作
		String msg = "";
		int code = 0;
		User loginUser = LoginUserUtils.getLoginUser();
		BbsCollection collection = bbsArticleConllectionService.checkConllectionByArticleUser(loginUser.getId(), articleId);
		if(collection == null){
			collection = new BbsCollection();
			collection.setUserId(loginUser.getId());
			collection.setArticleId(articleId);
			collection.setCreateDateTime(new Date());
			collection.setStatus(Constant.Status.NORMAL.getStatus());
			bbsArticleConllectionService.save(collection);
			//插入积分记录表
			pointsHistoryService.savePointsHistory(loginUser.getId(), PointsTypeConstant.Tag.COLLECTION.getTag());
		}else{
			code = 1;
			msg = "亲，你已经收藏过了!";
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
	 * 报名
	 * @author zhaolei
	 * @date 2015年7月30日
	 */
	@RequestMapping("/applyArticle.html")
	public void applyArticle(
			@RequestParam(value = "articleId", required = false) Long articleId,
			HttpServletResponse response) {
		//查询是否对帖子点赞过了，如果帖子点赞过了就不在进行举报操作
		//判断帖子是否过期了是否为活动贴
		String msg = "";
		int code = 0;
		User loginUser = LoginUserUtils.getLoginUser();
		User user = userService.getUserInfoById(loginUser.getId());
		BbsArticle article = bbsArticleService.getArticleByKey(articleId);
		if(article.getFromType() == null || article.getFromType().intValue() != 2){
			//此标记为活动贴
			code = 1;
			msg = "该帖子不是活动帖子无法报名!";
		}else if(article.getActivityIsEnd() == null || article.getActivityIsEnd().intValue() == 1){
			code = 1;
			msg = "该报名帖子已过期无法完成报名!";
		}else if(article.getActivityGrade().intValue()>user.getGradeEntity().getId().intValue()){
			code = 1;
			msg = "等级不够,无法完成报名!";
		}else{
			BbsArticleApply apply = bbsArticleApplyService.checkApplyByArticleUser(user.getId(), articleId);
			if(apply == null){
				apply = new BbsArticleApply();
				apply.setUser(user);
				apply.setArticle(article);
				apply.setCreateDateTime(new Date());
				apply.setStatus(Constant.Status.NORMAL.getStatus());
				bbsArticleApplyService.save(apply);
				//更改帖子报名人数
				bbsArticleService.updateApply(articleId);
			}else{
				code = 1;
				msg = "亲，你已经报名参加过了，静候佳音!";
			}
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
	 * 帖子详情回复
	 * @author zhaolei
	 * @date 2015年7月29日
	 * @param articleId
	 * @param model
	 * @return
	 */
	@RequestMapping("/articleDetail/{articleId}/{replyId}/{headType}.html")
	public String articleDetail(
			@PathVariable Long articleId,
			@PathVariable Long replyId,
			@PathVariable String headType,
			Model model){
		//点击查看详细时该帖子的查看次数要加1
		//bbsArticleService.updateViewCount(articleId);
		BbsArticle article = bbsArticleService.getArticleDetailById(articleId,Constant.Status.NORMAL.getStatus());
		if(StringUtils.isBlank(headType)){
			//说明该值没有传过来需要通过查询获得
			BbsForm form = formService.getFormById(article.getFormId());
			form = getTopForm(form);
			headType = form.getPermissionsTag();
		}
		BbsReply reply = replyService.getReplyById(replyId);
		model.addAttribute("articleDetail", article);//帖子相关内容
		//model.addAttribute("replyList", replyService.queryReplyListByArticleId(articleId));//帖子回复内容
		model.addAttribute("reply",reply);//回复对象
		model.addAttribute("headType", headType);//控制标题头选中属性
		//设置回复为已读状态
		BbsReply ureply = new BbsReply();
		ureply.setId(reply.getId());
		ureply.setReadStatus(Byte.parseByte("1"));
		replyService.updateReply(ureply);
		return "userArticleDetail";
	}
	private BbsForm getTopForm(BbsForm form){
		if(form.getParentForm() == null || form.getParentForm().getId().intValue()==0){
			return form;
		}else{
			BbsForm form1 = formService.getFormById(form.getParentForm().getId());
			return getTopForm(form1);
		}
	}
	/**
	 * 异步上传图片
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/upload")
	public String uploadPic(@RequestParam(value = "file") MultipartFile file) {
		Response respone = new Response();
		Map<String, Object> map = new HashMap<String, Object>();
		if (file == null || file.isEmpty()) {
			respone.setSuccess(Boolean.FALSE);
			respone.setMessage("图片不能为空！");
			return JSON.toJSONString(respone);
		}
		BufferedImage image = null;
		try {
			image = ImageIO.read(file.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		int height = image.getHeight();
		int width = image.getWidth();
		if (height == 154 && width == 220) {
			User loginUser = LoginUserUtils.getLoginUser();
			String original = file.getOriginalFilename();
			String suffx = original.substring(original.lastIndexOf(".") + 1, original.length());
			String key = ALiYunUtil.createBbsKey(suffx, loginUser.getId());
			String url = "";
			try {
				ALiYunUtil.uploadFile(key, file);
				url = ALiYunUtil.IMAGE_URL + key;
			} catch (OSSException e) {
				e.printStackTrace();
			} catch (ClientException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			map.put("url", url);
			map.put("width", width);
			map.put("heigth", height);
			respone.setResult(map);
			respone.setMessage("成功");
			return JSON.toJSONString(respone);
		}else{
			respone.setSuccess(Boolean.FALSE);
			respone.setMessage("图片尺寸不符合");
			return JSON.toJSONString(respone);
		}
	}
}

