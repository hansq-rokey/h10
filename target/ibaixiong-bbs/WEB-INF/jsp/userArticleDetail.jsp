<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
  <jsp:include page="include/head.jsp"></jsp:include>
  <link href="http://fe.ibaixiong.com/bbs/css/prods.css" rel="stylesheet" type="text/css">
  <link href="http://fe.ibaixiong.com/bbs/css/prodtalk.css" rel="stylesheet" type="text/css">
  <link href="http://fe.ibaixiong.com/bbs/css/activedetail.css" rel="stylesheet" type="text/css">
  <link href="http://fe.ibaixiong.com/bbs/css/postdetail.css" rel="stylesheet" type="text/css">
  <script src="http://fe.ibaixiong.com/bbs/js/postdetail.js" type="text/javascript" ></script>
  <script type="text/javascript" charset="utf-8" src="/ueditor.config.js"></script>
  <script type="text/javascript" charset="utf-8" src="/ueditor.all.min.js"> </script>
   <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
   <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
  <script type="text/javascript" charset="utf-8" src="/lang/zh-cn/zh-cn.js"></script>
  <script type="text/javascript">
  	 var ue = UE.getEditor('editor', {
  	  //初始化工具栏菜单
      		toolbars: [
                 ['source', '|', 'undo', 'redo', '|',
                  'bold', 'italic', 'underline', 'fontborder', 'strikethrough', 'superscript', 'subscript', 'removeformat', 'formatmatch', 'autotypeset', 'blockquote', 'pasteplain', '|', 'forecolor', 'backcolor', 'insertorderedlist', 'insertunorderedlist', 'selectall', 'cleardoc', '|',
                  'rowspacingtop', 'rowspacingbottom', 'lineheight', '|',
                  'customstyle', 'paragraph', 'fontfamily', 'fontsize', '|',
                  'directionalityltr', 'directionalityrtl', 'indent', '|',
                  'justifyleft', 'justifycenter', 'justifyright', 'justifyjustify', '|', 'touppercase', 'tolowercase', '|',
                  'link', 'unlink', 'anchor', '|', 'imagenone', 'imageleft', 'imageright', 'imagecenter', '|',
                  'simpleupload', 'insertimage', 'emotion', 'scrawl', 'attachment', 'insertframe', 'pagebreak', 'template', 'background', '|',
                  'horizontal', 'date', 'time', 'spechars', 'snapscreen', 'wordimage', '|',
                  'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol', 'mergecells', 'mergeright', 'mergedown', 'splittocells', 'splittorows', 'splittocols', 'charts', '|',
                  'print', 'preview', 'searchreplace', 'help', 'drafts']
             ]
         });
	  $(document).ready(function(){
	  		$.ajax({
			   url: "/u/user/getUserInfo.html",
			   type: "get",
			   dataType:"json",
			   cache:false,
			   success: function(obj){
			 	if ( obj.code == 0 ) {
					//获取登陆用户成功之后给个人头像赋值
					$("#detailHeadImg").attr("src",obj.result.user.headImg);
				}
			   }
			});
	  		$(document).ready(function() {
		    	$('.cancel').click(function () {
		            $('.popup').hide();
		        });	    	
		    });
	  })
	  function checkReplySave1(){
		  var b = true;//页面是否发生跳转
		  $.ajax({
	  		   url: "/u/user/getUserInfo.html",
	  		   type: "POST",
	  		   dataType:"json",
	  		   cache:false,
	  		   async: false,
	  		   success: function(obj){
				 	if ( obj.code == 0 ) {
						//获取当前登陆用户是否拥有昵称如果没有，弹出设置昵称页面当前页面不进行继续跳转操作
						var nickName = obj.result.user.nickName
						if(nickName == null || nickName ==undefined || nickName == ''){
						   $(".nickpop").show();
						   b = false;
					    }
					}else{
						b = false;
					}
				 	//可能未登陆跳转到单点登陆页面
	  		   }
  		   });
		   if(b==false){
			   return false;
		   }
		   var content = $("#content").val();
		   if(content == null || content ==undefined || content == ''){
			   alertLayel("回复内容不可为空！");
			   return false;
		   }
		   return true;
	  }
	  function checkReplySave2(){
		  var b = true;//页面是否发生跳转
		  $.ajax({
	  		   url: "/u/user/getUserInfo.html",
	  		   type: "POST",
	  		   dataType:"json",
	  		   cache:false,
	  		   async: false,
	  		   success: function(obj){
				 	if ( obj.code == 0 ) {
						//获取当前登陆用户是否拥有昵称如果没有，弹出设置昵称页面当前页面不进行继续跳转操作
						var nickName = obj.result.user.nickName
						if(nickName == null || nickName ==undefined || nickName == ''){
						   $(".nickpop").show();
						   b = false;
					    }
					}else{
						b = false;
					}
				 	//可能未登陆跳转到单点登陆页面
	  		   }
  		   });
		   if(b==false){
			   return false;
		   }
		   var content = UE.getEditor('editor').getContent();
		   if(content == null || content ==undefined || content == ''){
			   alertLayel("回复内容不可为空！");
			   return false;
		   }
		   return true;
	  }
	  function checkUserNick(){
		  var b = true;//页面是否发生跳转
		  $.ajax({
	  		   url: "/u/user/getUserInfo.html",
	  		   type: "POST",
	  		   dataType:"json",
	  		   cache:false,
	  		   async: false,
	  		   success: function(obj){
				 	if ( obj.code == 0 ) {
						//获取当前登陆用户是否拥有昵称如果没有，弹出设置昵称页面当前页面不进行继续跳转操作
						var nickName = obj.result.user.nickName
						if(nickName == null || nickName ==undefined || nickName == ''){
						   $(".nickpop").show();
						   b = false;
					    }
					}else{
						b = false;
					}
				 	//可能未登陆跳转到单点登陆页面
	  		   }
  		  });
		  return b;
	  }
	 function setNickName(){
		var nickNameText = $("#nickNameText").val();
		if(nickNameText == null || nickNameText ==undefined || nickNameText == ''){
			alertLayel("设置的昵称不可为空！");
		   return false;
	    }
		$.ajax({
	  		   url: "/u/user/setNickName.html",
	  		   type: "POST",
	  		   data:{"nickName":nickNameText},
	  		   dataType:"json",
	  		   cache:false,
	  		   async: false,
	  		   success: function(obj){
				 	if ( obj.code == 0 ) {
						//获取当前登陆用户是否拥有昵称如果没有，弹出设置昵称页面当前页面不进行继续跳转操作
				 		$(".nickpop").hide();
					}else{
						alertLayel("设置失败");
					}
	  		   }
		});
	 }
  </script>
</head>
<body>
<jsp:include page="include/top.jsp">
 	<jsp:param value="${headType }" name="headType"/>
</jsp:include>
 <div class="content">
 	<div class="content-top">
 		<c:if test="${headType == 'bbs'}">
	        <img src="http://fe.ibaixiong.com/bbs/image/hotactive.png" class="themebg">
	        <div class="themetext">
	            <h3>社区</h3>
	            <p>纽约时代广场是美国纽约曼哈顿最繁华的街区之一，被称为“世界十字路口”，这里的年均游客流量4000万人次、人员流量1亿人次，被视为“吸引全球目光”的最佳窗口。</p>
	        </div>
        </c:if>
        <c:if test="${headType == 'product'}">
	        <img src="http://fe.ibaixiong.com/bbs/image/hotactive.png" class="themebg">
	        <div class="themetext">
	            <h3>产品讨论</h3>
	            <p>纽约时代广场是美国纽约曼哈顿最繁华的街区之一，被称为“世界十字路口”，这里的年均游客流量4000万人次、人员流量1亿人次，被视为“吸引全球目光”的最佳窗口。</p>
	        </div>
        </c:if>
        <c:if test="${headType == 'school'}">
	        <img src="http://fe.ibaixiong.com/bbs/image/hotactive.png" class="themebg">
	        <div class="themetext">
	            <h3>白熊学院</h3>
	            <p>纽约时代广场是美国纽约曼哈顿最繁华的街区之一，被称为“世界十字路口”，这里的年均游客流量4000万人次、人员流量1亿人次，被视为“吸引全球目光”的最佳窗口。</p>
	        </div>
        </c:if>
        <c:if test="${headType == 'activity'}">
	        <img src="http://fe.ibaixiong.com/bbs/image/hotactive.png" class="themebg">
	        <div class="themetext">
	            <h3>热门活动</h3>
	            <p>纽约时代广场是美国纽约曼哈顿最繁华的街区之一，被称为“世界十字路口”，这里的年均游客流量4000万人次、人员流量1亿人次，被视为“吸引全球目光”的最佳窗口。</p>
	        </div>
        </c:if>
    </div>
    <div class="content-left">
        <div class="content-detail">
            <div class="row" style="padding-bottom:4px;">
                <div class="col-xs-12">
                    <h3 class="activetitle">${articleDetail.title }</h3>
                    <c:if test="${headType == 'activity'}"><!-- 活动贴显示 -->
                    	<c:if test="${articleDetail.activityIsEnd == 0 }"><!-- 说明帖子允许报名 -->
                    		<input type="button" value="报名" class="signbtn" onclick="apply(${articleDetail.id })">
                    	</c:if>
                    	<c:if test="${articleDetail.activityIsEnd == 1 }"><!-- 说明帖子不允许报名 -->
                    		<input type="button" value="报名" class="signbtn" style="background-color: #c0c0c0;">
                    	</c:if>
                    </c:if>
                </div>
            </div>
            <div class="row" style="padding-top:0px;line-height:34px;">
                <div class="col-xs-6 light smallsize">
                                                            发表在
                    <span class="darker" style="margin: 0 16px 0 8px;font-size:12px;">${articleDetail.fromName }</span>
                    <fmt:formatDate value="${articleDetail.createDateTime}" pattern="yyyy/MM/dd HH:mm:ss"/> 
                </div>
                <div class="col-xs-6 tr light smallsize">
                    <span class="iconlist report">举报</span>
                    <span class="iconlist"><i class="icon read"></i>${articleDetail.viewCount }</span><!-- 查看次数 -->
                    <span class="iconlist"><i class="icon reply"></i>${articleDetail.replyCount }</span><!-- 回复次数 -->
                    <span class="iconlist"><i class="icon praise"></i>${articleDetail.pariseCount }</span><!-- 点赞次数 -->
                    <span class="iconlist read" style="float: right">
                        <i class="icon share"></i>分享
                        <div class="sharebox">
                            <a href="#"> <span data-id="${article.id}" data-value="${article.title }" class="shareacon wx"></span></a>
                            <a href="http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=http://bbs.ibaixiong.com/share/detail/${article.id}.html" target="_blank"> <span data-id="${article.id}" data-value="${article.title }" class="shareacon kj"></span></a>
                            <a href="http://connect.qq.com/widget/shareqq/index.html?title=${article.title}&url=http://bbs.ibaixiong.com/share/detail/${article.id}.html" target="_blank"> <span data-id="${article.id}" data-value="${article.title }" class="shareacon qq"></span></a>
                            <a href="http://v.t.sina.com.cn/share/share.php?url=http://bbs.ibaixiong.com/share/detail/${article.id}.html&title='${article.title }'" target="_blank"> <span data-id="${article.id}" data-value="${article.title }" class="shareacon wb"></span></a>
                            <div data-id="${article.id}" class="shareImgBox">
                            	<p class="shareWeixin">扫一扫分享到微信</p>
                            	<div class="shareWeixinImgBox">
                            		<img src="#" id="${article.id}" class="shareWeixinImg">
                            	</div>
                            </div>
                        </div>
                    </span>
                </div>
            </div>
            <!-- 
            <div class="row">
                <img src="/image/actdetail.png" class="actdetail">
            </div> -->
            <div class="row">
                <p class="articletext">${articleDetail.detail.content}</p>
            </div>
        </div>
        <ul class="cllist">
            <li>
                <div class="row">
                    <div class="col-xs-1">
                        <img src="${reply.user.avatarImg }" class="replyimg">
                    </div>
                    <div class="col-xs-11">
                        <div class="row">
                            <div class="col-xs-12 light">
                                <a href="/user/index/${reply.user.id}/${headType }.html" class="bluetext"> ${reply.user.nickName}<img src="${reply.user.gradeEntity.url }"/> </a>
                                <span><fmt:formatDate value="${reply.createDateTime}" pattern="yyyy/MM/dd HH:mm:ss"/></span>
                            </div>
                            <div class="col-xs-12">
                                <div class="row">

                                </div>
                            </div>
                            <div class="col-xs-12 light smallsize">
                                <p class="replytext dark">${reply.replyContent}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
        <!-- 回复-->
        <div class="content-bottom">
            <div class="row">
                <div class="col-lg-12"> <h3 class="replytop">发表回复</h3></div>
            </div>
            <form action="/u/article/replySave.html" onsubmit="return checkReplySave2()" method="post">
            	<input type="hidden" name="headType" value="${headType }" id="headType"/>
            	<input type="hidden" name="quoteId" value="${reply.id }" />
            	<input type="hidden" name="quoteContent" value="${reply.replyContent }" />
            	<input type="hidden" name="quoteUserNick" value="${reply.user.nickName }" />
            	<input type="hidden" name="quoteAvatarImg" value="${reply.user.avatarImg}" />
            	<input type="hidden" name="quoteCreateDateTimeStr" value="<fmt:formatDate value="${reply.createDateTime}" pattern="yyyy-MM-dd HH:mm:ss"/> " />
	            <input type="hidden" name="article.id" value="${reply.article.id }" id="articleId"/>
	            <div class="row">
	            		<script id="editor" name="replyContent" type="text/plain" style="width:900px;height:500px;"></script>
	            </div>
	            <div class="row">
	                <div class="col-lg-12">
	                    <input type="submit" class="publishreply" value="发表回复">
	                </div>
	            </div>
            </form>
        </div>
    </div>
    <div class="content-right">
   	 	<input type="button" value="" class="sign">
        <div class="userinfor">
            <div class="row">
                <div class="col-xs-4">
                    <img src="${articleDetail.user.avatarImg }" class="userimg">
                </div>
                <div class="col-xs-8">
                    <div class="row">
                        <div class="col-xs-12" style="padding-left:8px;">${articleDetail.user.nickName }<img src="${articleDetail.user.gradeEntity.url }"/> </div>
                        <div class="col-xs-12" style="padding-left:8px;"><a href="/user/index/${articleDetail.user.id}/${headType }.html" ><input type="button" value="个人主页" class="userhome"> </a></div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 最新活动 -->
        <jsp:include page="include/activeArticleList.jsp"></jsp:include>
    </div>
</div>

<!--昵称-->
<div class="popup nickpop">
    <div class="warnbg"></div>
    <div class="row warnpop">
        <div class="col-xs-12" style="padding:0;margin-bottom:17px;"><h3 class="wt">设置昵称<i class="close"></i> </h3></div>
        <div class="col-xs-12 tc"><span class="darker">昵称：</span><input type="text" class="nicktext" id="nickNameText"> </div>
        <div class="col-xs-12 tc"><h3 class="dark">设置昵称后才可以评论和回复哦！</h3></div>
        <div class="col-xs-12 tc">
            <input type="button" value="完成" class="finishbtn" onclick="setNickName()">
            <input type="button" value="取消" class="cancel">
        </div>
    </div>
</div>
<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>
