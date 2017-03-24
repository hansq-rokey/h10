<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=Edge">
	<link href="http://fe.ibaixiong.com/common
	/plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
	<link href="http://fe.ibaixiong.com/bbs/css/style.css" rel="stylesheet" type="text/css">
	<link href="http://fe.ibaixiong.com/bbs/css/user.css" rel="stylesheet" type="text/css">
	<script src="http://fe.ibaixiong.com/common
	/plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
	<script src="http://fe.ibaixiong.com/common
	/plug/jQuery/jquery-migrate-1.2.1.js" type="text/javascript" ></script>
	<script src="http://fe.ibaixiong.com/common
	/plug/jQuery/jPages.min.js" type="text/javascript" ></script>
	<script src="http://fe.ibaixiong.com/common
	/plug/jQuery/unslider.min.js" type="text/javascript" ></script>
    <script src="http://fe.ibaixiong.com/bbs/js/user.js" type="text/javascript" ></script>
    <script src="http://fe.ibaixiong.com/bbs/js/prods.js" type="text/javascript" ></script>
    <script src="http://fe.ibaixiong.com/bbs/js/base.js" type="text/javascript"></script>
	<title>个人中心</title>
</head>
 <script>
    $(document).ready(function(){
        $.ajax({
  		   url: "/u/user/getUserInfo.html",
  		   type: "POST",
  		   dataType:"json",
  		   cache:false,
  		   success: function(obj){
			 	if ( obj.code == 0 ) {
			 		$("#nameSpan").text(obj.result.user.bxNum);
					$("#nikeNameSpan").text(obj.result.user.nickName);
					$("#gradeImg1").attr("src",obj.result.user.gradeImg);
					$("#roleSpan").text(obj.result.user.roleNames);
				}
  		   }
  		});
        $('.replytext').find('*').css({display:'inline-block',width:'auto'});
        $('.replytext').find('.replyLink').css({maxWidth:'560px'});
        $('.replyLink').find('*').css({maxWidth:'560px',overflow:'hidden',whiteSpace:'nowrap',textOverflow:'ellipsis',height:'28px'});
    });
    function delColl(articleId){
    	$.ajax({
   		   url: "/u/user/removeCollectionArticle.html?articleId="+articleId,
   		   type: "POST",
   		   dataType:"json",
   		   cache:false,
   		   success: function(obj){
 			 	if ( obj.code == 0 ) {
 			 		$("#del"+articleId).remove();//删除完成这一行
 				}
   		   }
   		});
    }
    function delPushArticle(articleId){
    	$.ajax({
   		   url: "/u/user/removeArticle.html?articleId="+articleId,
   		   type: "POST",
   		   dataType:"json",
   		   cache:false,
   		   success: function(obj){
  			 	if ( obj.code == 0 ) {
  			 		$("#delpublis"+articleId).remove();//删除完成这一行
  				}
    		}
    	});
    }
    function test(){
    	alertLayel("tt");
    }
    function toQuery(openType,queryType){
    	$('#pageNo').val(1);
    	$('#openType').val(openType);
    	$('#queryType').val(queryType);
		$('.search').click();
    }
    function toQuery1(){
    	$('#pageNo').val(1);
    	$('#openType').val(null);
    	$('#queryType').val(null);
		$('.search').click();
    }
</script>
<body>
<!-- 分页使用 -->
<div style="display: none;">
	<form action="/u/user/index/${headType}.html" method="post">
		<input type="hidden" name="queryType" id="queryType" value="${queryType }">
	 	<input type="hidden" name="pageNo" id="pageNo" value="">
	 	<input type="hidden" name="openType" id="openType" value="${openType }">
	    <input type="submit" id="pageSubmit" class="search" value="搜索">        	
	</form>
</div>
<jsp:include page="include/top.jsp">
  	<jsp:param value="${headType }" name="headType"/>
</jsp:include>
<div class="content">
    <div class="left-box">
        <jsp:include page="userInfo.jsp">
        	<jsp:param value="${userId }" name="userId"/>
        	<jsp:param value="${headType }" name="headType"/>
        </jsp:include>
        <div class="left-bottom">
            <ul class="leftnav">
                <h5>个人资料</h5>
                <li class="${openType==null?'selected':'' }">
                    <span class="switch switched"  onclick="toQuery1()">资料</span>
                </li>
                <li class="${openType==1?'selected':'' }">
                    <span class="switch ${queryType!=4?'switched':'' }" onclick="toQuery(1,1)">帖子</span>
                </li>
            </ul>
            <ul>
                <h5>通知</h5>
                <li class="${openType==2?'selected':'' }">
                    <span class="switch ${queryType==4?'switched':'' }" onclick="toQuery(2,4)">帖子动态</span>
                </li>
            </ul>
        </div>
    </div>
    <div class="content-right">
	   	<div class="right-box tl ${openType==null?'block':'' }">
           <P class="title top">个人资料</P>
           <div class="row" style="padding:10px 0;">
               <div class="col-xs-1 tr">白熊号:</div>
               <div class="col-xs-10 light"><span id="nameSpan"></span></div>
           </div>
           <div class="row" style="padding:10px 0;">
               <div class="col-xs-1 tr">等级:</div>
               <div class="col-xs-10 light"><img src="" id="gradeImg1"></div>
           </div>
           <div class="row" style="padding:10px 0;">
               <div class="col-xs-1 tr">昵称:</div>
               <div class="col-xs-10 light">
                   <p class="red"><span id="nikeNameSpan"></span></p>
               </div>
           </div>
           <div class="row" style="padding:10px 0;">
               <div class="col-xs-1 tr">用户组:</div>
               <div class="col-xs-10 light"><span id="roleSpan"></span></div>
           </div>
       </div>
       <div class="right-box tl  ${openType==1?'block':'' }">
           <ul class="title top articlebox">
               <li>
                   <span class="secswitch ${queryType==1?'secswitched':'' }" onclick="toQuery(1,1)">发布的帖子</span>
               </li>
               <li>
                   <span class="secswitch ${queryType==2?'secswitched':'' }" onclick="toQuery(1,2)">回复的帖子</span>
               </li>
               <li>
                   <span class="secswitch ${queryType==3?'secswitched':'' }" onclick="toQuery(1,3)">收藏的帖子</span>
               </li>
           </ul>
           <div class="article-content">
           	<c:if test="${queryType==1 }">
                   <div class="article ${queryType==1?'current-art':'' }">
                       <div class="row title-row tc">
                           <div class="col-lg-6">帖子</div>
                           <div class="col-lg-2">板块</div>
                           <div class="col-lg-2">回复/查看</div>
                           <div class="col-lg-2"></div>
                       </div>
                       <div class="itemContainer">
                       	<c:forEach items="${publishList}" var="article">
                            <div class="row tc publishtext" id="delpublis${article.id }">
                                <div class="col-lg-6 tl"><a href="/detail/${article.id }.html"> ${article.title }</a> </div>
                                <div class="col-lg-2">${article.fromName }</div>
                                <div class="col-lg-2">${article.replyCount }/${article.viewCount }</div>
                                <div class="col-lg-2"><a href="/u/article/toArticlePublish.html?id=${article.id }"  class="pubdelete">编辑</a><a href="#" onclick="delPushArticle(${article.id })" class="pubdelete">删除</a></div>
                            </div>
                           </c:forEach>
                       </div>
                   </div>
                   </c:if>
                   <c:if test="${queryType==2 }">
                   <div class="article ${queryType==2?'current-art':'' }">
                       <div class="row title-row tc">
                           <div class="col-lg-8">帖子</div>
                           <div class="col-lg-2">板块</div>
                           <div class="col-lg-2">回复/查看</div>
                       </div>
                       <div  class="itemContainer">
                           <c:forEach items="${replyList}" var="article">
                            <div class="row tc">
                                <div class="col-lg-8 tl" style="padding-left:26px;"><a href="/detail/${article.id }.html"> ${article.title }</a> </div>
                                <div class="col-lg-2">${article.fromName }</div>
                                <div class="col-lg-2">${article.replyCount }/${article.viewCount }</div>
                            </div>
                            <div class="huifubox">
                            <c:if test="${article.quoteContent != null }">
                            <div class="row replytext">
                                 <div class="col-lg-9 tl" style="padding-left:62px;line-height:28px;vertical-align:middle;display:inline-block;"><span class="space"></span><span class="space"></span><img src="http://fe.ibaixiong.com/bbs/image/tuoyuan.png" style="margin-right:6px;vertical-align:middle;float:left;margin-top:11px;"><span class="light replyLink"> ${article.quoteContent}</span> </div>
                                 <div class="col-lg-3"></div >
                             </div>
                            </c:if>
                            <div class="row replytext">
                                <div class="col-lg-9 tl" style="padding-left:62px;"><span class="space"></span><span class="space"></span><img src="http://fe.ibaixiong.com/bbs/image/tuoyuan.png" style="margin-right:6px;vertical-align:middle;float:left;margin-top:11px;"><span class="light replyLink"> ${article.replyContent}</span> </div>
                                <div class="col-lg-3"><a href="#" class="replydel"></a> </div>
                            </div>
                            </div>
                           </c:forEach>
                       </div>
                   </div>
                   </c:if>
                   <c:if test="${queryType==3 }">
                   <div class="article collectlist ${queryType==3?'current-art':'' }">
                       <div class="row title-row tc">
                           <div class="col-lg-6">帖子</div>
                           <div class="col-lg-2">板块</div>
                           <div class="col-lg-2">回复/查看</div>
                           <div class="col-lg-2"></div>
                       </div>
                       <div id="" class="itemContainer">
                       	<c:forEach items="${collectionList}" var="article">
                           	<div class="row tc" id="del${article.id }">
                                <div class="col-lg-6 tl"><a href="/detail/${article.id }.html"> ${article.title }</a> </div>
                                <div class="col-lg-2">${article.fromName }</div>
                                <div class="col-lg-2">${article.replyCount }/${article.viewCount }</div>
                                <div class="col-lg-2"><a href="#" onclick="delColl(${article.id })" class="pubdelete">删除</a></div>
                           	</div>
                           </c:forEach>
                       </div>
                   </div>
                   </c:if>
           </div>
           <c:if test="${queryType!=4 }">
           <jsp:include page="include/pages.jsp"></jsp:include>
           </c:if>
       </div>
       <div class="right-box tl  ${queryType==4?'block':'' }">
            <c:if test="${queryType==4 }">
               <ul class="title top articlebox">
                   <li>
                       <span class="secswitch ${queryType==4?'secswitched':'' }" onclick="toQuery(2,4)">回复</span>
                   </li>
               </ul>
               <div class="article ${queryType==4?'current-art':'' }">
                   <div class="itemContainer">
                   	<c:forEach items="${dynamicList}" var="dynamic">
                        <div class="row tc publishtext">
                            <div class="col-lg-12 tl">
                                <div class="col-lg-1 tl"> <img src="${dynamic.user.avatarImg }" class="replyimg"> </div>
                                <div class="col-lg-11">
                                    <div class="row">
                                        <div class="col-lg-10" style="width: 800px;">
                                            <span class="darker">${dynamic.user.nickName }</span>
                                            <a href="/detail/${dynamic.article.id }.html" class="dark"> 回复了${dynamic.article.title }</a>
                                        </div>
                                        <div class="col-lg-2 light" style="width: 60px;">
                                        <fmt:formatDate value="${dynamic.createDateTime}" pattern="yyyy/MM/dd HH:mm:ss"/>&nbsp;&nbsp;&nbsp;&nbsp;
                                        <a href="/u/article/articleDetail/${dynamic.article.id }/${dynamic.id}/${headType }.html" class="seedetail black">回复</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                       </c:forEach>
                   </div>
               </div>
               <jsp:include page="include/pages.jsp"></jsp:include>
               </c:if>
           </div>
	   </div>
</div>
<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>
