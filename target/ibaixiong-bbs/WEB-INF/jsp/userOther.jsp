<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <link href="http://fe.ibaixiong.com/common/plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="http://fe.ibaixiong.com/bbs/css/style.css" rel="stylesheet" type="text/css">
    <link href="http://fe.ibaixiong.com/bbs/css/user.css" rel="stylesheet" type="text/css">
    <script src="http://fe.ibaixiong.com/common/plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
    <script src="http://fe.ibaixiong.com/common/plug/jQuery/jquery-migrate-1.2.1.js" type="text/javascript" ></script>
    <script src="http://fe.ibaixiong.com/common/plug/jQuery/jPages.min.js" type="text/javascript" ></script>
    <script src="http://fe.ibaixiong.com/common/plug/jQuery/unslider.min.js" type="text/javascript" ></script>
    <script src="http://fe.ibaixiong.com/bbs/js/user.js" type="text/javascript" ></script>
    <script src="http://fe.ibaixiong.com/bbs/js/hotactive.js" type="text/javascript" ></script>
  <script src="http://fe.ibaixiong.com/bbs/js/prods.js" type="text/javascript" ></script>
  <script src="http://fe.ibaixiong.com/bbs/js/base.js" type="text/javascript"></script>
    <title>个人中心</title>
</head>
<style>
.pageTable{
	position:absolute;
	bottom:25px;
	left:0;
}
</style>
 <script>
    $(document).ready(function(){
        $.ajax({
  		   url: "/user/getUserInfo.html?userId="+${userId},
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
        var openType=$('#openType').val();
        if(openType==1){
        	$('.left-bottom').find('ul').find('li').eq(1).addClass('selected');
        	$('.left-bottom').find('ul').find('li').eq(1).find('.right-box').addClass('block');
        	$('.left-bottom').find('ul').find('li').eq(1).find('.switch').addClass('block');
        }else{
        	$('.left-bottom').find('ul').find('li').eq(0).addClass('selected');
        	$('.left-bottom').find('ul').find('li').eq(0).find('.right-box').addClass('block');
        }
    })
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
	<form action="/user/index/${userId}/${headType }.html" method="post">
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
        </jsp:include>
        <div class="left-bottom">
            <ul>
                <h5>个人资料</h5>
                <li class="">
                    <span class="switch" onclick="toQuery1()">资料</span>
                </li>
                <li>
                    <span class="switch" onclick="toQuery(1,1)">帖子</span>
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
           </ul>
           <div class="article-content" style="padding-bottom:80px;">
	           	<c:if test="${queryType==1 }">
		        <div class="article ${queryType==1?'current-art':'' }" >
		            <div class="row title-row tc">
		                <div class="col-lg-6">帖子</div>
		                <div class="col-lg-2">板块</div>
		                <div class="col-lg-2">回复/查看</div>
		                <div class="col-lg-2"></div>
		            </div>
		            <div class="itemContainer">
		            	<c:forEach items="${publishList}" var="article">
		                 <div class="row tc publishtext">
		                     <div class="col-lg-6 tl"  style="padding-left:26px;"><a href="/detail/${article.id }.html"> ${article.title }</a> </div>
		                     <div class="col-lg-2">${article.fromName }</div>
		                     <div class="col-lg-2">${article.replyCount }/${article.viewCount }</div>
		                     <div class="col-lg-2"></div>
		                 </div>
		                </c:forEach>
		            </div>
		        </div>
		        </c:if>
		        <c:if test="${queryType==2 }">
		        <div class="article ${queryType==2?'current-art':'' }" style="top:0;position:relative;">
		            <div class="row title-row tc">
		                <div class="col-lg-8">帖子</div>
		                <div class="col-lg-2">板块</div>
		                <div class="col-lg-2">回复/查看</div>
		                <div class="col-lg-2"></div>
		            </div>
		            <div class="itemContainer" id=" ${queryType==2?'itemContainer':'' }">
		            	<c:forEach items="${replyList}" var="article">
		                 <div class="row tc">
		                     <div class="col-lg-8 tl" style="padding-left:26px;"><a href="/detail/${article.id }.html" > ${article.title }</a> </div>
		                     <div class="col-lg-2">${article.fromName }</div>
		                     <div class="col-lg-2">${article.replyCount }/${article.viewCount }</div>
		                 </div>
		                 <div class="huifubox">
		                 <c:if test="${article.quoteContent != null }">
		                  <div class="row replytext">
		                      <div class="col-lg-9 tl" style="padding-left:62px;"><span class="space"></span><span class="space"></span><img src="http://fe.ibaixiong.com/bbs/image/tuoyuan.png" style="margin-right:6px;"><span class="light replyLink"> ${article.quoteContent}</span> </div>
		                      <div class="col-lg-3"></div>
		                  </div>
		                 </c:if>
		                 <div class="row replytext">
		                     <div class="col-lg-9 tl" style="padding-left:62px;"><span class="space"></span><span class="space"></span><img src="http://fe.ibaixiong.com/shop./image/tuoyuan.png" style="margin-right:6px;"><span class="light replyLink"> ${article.replyContent}</span> </div>
		                     <div class="col-lg-3"><a href="#" class="replydel"></a> </div>
		                 </div>
		                 </div>
		                </c:forEach>
		            </div>
		        </div>
		        </c:if>
		        <jsp:include page="include/pages.jsp"></jsp:include>
           </div>
        </div>
    </div>
</div>
<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>