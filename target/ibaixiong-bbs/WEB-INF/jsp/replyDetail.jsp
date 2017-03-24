<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
  <jsp:include page="include/head.jsp"></jsp:include>
  <link href="http://fe.ibaixiong.com/bbs/css/activedetail.css" rel="stylesheet" type="text/css">
  <link href="http://fe.ibaixiong.com/bbs/css/postdetail.css" rel="stylesheet" type="text/css">
  <script type="text/javascript" charset="utf-8" src="/ueditor.config.js"></script>
   <script type="text/javascript" charset="utf-8" src="/ueditor.all.min.js"> </script>
   <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
   <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
   <script type="text/javascript" charset="utf-8" src="/lang/zh-cn/zh-cn.js"></script>
  <script type="text/javascript">
  	 var ue = UE.getEditor('editor', {
  	  //初始化工具栏菜单
      		toolbars: [
	                   ['bold','fontsize','forecolor','backcolor','rowspacingtop','justifyleft','justifycenter','justifyright','justifyjustify','simpleupload','insertimage','insertvideo','link','unlink','attachment']
             ]
         });
	  function checkReplySave2(){
		   var content = UE.getEditor('editor').getContent();
		   if(content == null || content ==undefined || content == ''){
			   alertLayel("回复内容不可为空！");
			   return false;
		   }
		   return true;
	  }
  </script>
</head>
<body>
  <jsp:include page="include/top.jsp">
  	<jsp:param value="${headType }" name="headType"/>
  </jsp:include>
 <div class="content" style="margin-top:80px;">
    <div class="content-left">
        <!-- 回复-->
        <div class="content-bottom" style="overflow:hidden;">
        	<div class="row" style="padding-left:50px;padding-top:20px;">
        		<div class="col-xs-12 light smallsize">
                    <p class="replytext dark" style="font-size:20px;padding-left:15px;line-height:20px;margin-top:18px;margin-bottom:11px;">回复:<span style="font-size:20px;" class="light">"${reply.replyContent}"</span></p>
                </div>
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
	            		<script id="editor" name="replyContent" type="text/plain" style="width:1100px;height:500px;margin-left:65px;"></script>
	            </div>
	            <div class="row">
	                <div class="col-lg-12">
	                    <input type="submit" class="publishreply" value="发表回复" style="margin-right:64px;margin-top:18px;">
	                </div>
	            </div>
            </form>
        </div>
    </div>
</div>
<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>
