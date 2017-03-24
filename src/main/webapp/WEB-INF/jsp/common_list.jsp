<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" >
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1, user-scalable=no"> 
	<link rel="stylesheet" type="text/css" href="/plug/bootstrap/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="/css/index_new.css">
	<!-- <link href="/css/style.css" rel="stylesheet" type="text/css"> -->
	<!-- <link href="http://fe.ibaixiong.com/bbs/css/index.css" rel="stylesheet" type="text/css">
	<link href="http://fe.ibaixiong.com/bbs/css/prods.css" rel="stylesheet" type="text/css"> -->
	<link rel="stylesheet" type="text/css" href="/plug/swiper/swiper.css">
	<script type="text/javascript" src="/plug/jQuery/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="/plug/swiper/swiper.jquery.min.js"></script>
	<script src="http://fe.ibaixiong.com/bbs/js/qrcode.js"></script>
	<script src="http://fe.ibaixiong.com/bbs/js/head.js" type="text/javascript"></script>
	<script src="/js/base.js" type="text/javascript"></script>
 	<script src="/js/index.js" type="text/javascript" ></script>
	<title>白熊资讯</title>
   <script>
    $(document).ready(function(){
        $('.banner').css('height','428px');
        var Rand = Math.random();  
        $.ajax({
  		   url: "/u/user/getUserInfo.html",
  		   type: "get",
  		   dataType:"json",
  		   cache:false,
  		   success: function(obj){
			 	if ( obj.code == 0 ) {
					//获取登陆用户成功之后给值
					$("#yBtn").show();
					$("#loginBtn").hide();
					$("#regBtn").hide();
					var nickName=obj.result.user.nickName;
					if(nickName==''||nickName==null){
						nickName=obj.result.user.bxNum;
					}
					$("#userName").text(nickName);
					$("#headImg").attr("src",obj.result.user.headImg);
				}
  		   }
  		});
  		/*$.ajax({
    		url: '/u/user/getUserInfo.html?callbackName=getHeadUserInfo',
    		dataType:'jsonp',
    		jsonp:'callback'
    	});*/
    })
    function getHeadUserInfo( obj ) {
		if ( obj.code == 0 ) {
			$("#yBtn").show();
			$("#loginBtn").hide();
			$("#regBtn").hide();
			$("#userName").text(obj.result.user.nickName);
			$(".headImg").attr("src",obj.result.user.headImg);
		}
	}
    
   /*发帖*/
   function toPublishArticle(){
	   window.location.href='/u/article/toArticlePublish.html?headType=${headType}';
   }
  </script>
</head>
<body>
<%--   <jsp:include page="include/top.jsp">
  	<jsp:param value="${headType }" name="headType"/>
  </jsp:include> --%>
 	<div class="avbar-fixed-top">
	    <div class="new_header">
	        <a href="http://www.ibaixiong.com/"><img src="http://fe.ibaixiong.com/common/image/logo01.png" class="bbs-logo"> </a>
	        <ul class="new_nav nav-pills nav-justified ">
	            <li id="bxhp"><a class="navlink" href="http://www.ibaixiong.com">首页</a></li>
	            <li id="bxhp"><a class="navlink" target="_blank" href="http://www.ibaixiong.com/attract.html">合作伙伴</a></li>
	           	<c:forEach items="${commonFormList }" var="item">
	           		<li class='${item.permissionsTag==headType?"currentTop":"" }'><a class='navlink' href='${item.url }'>${item.name}</a></li>
	           	</c:forEach>
	        </ul>
	    </div>
	</div>
	
    <!--content-->
	<div class="_new_content">
		<div class="_new_box">
		<div class="_new_left">
			<div class="swiper-container">
			    <div class="swiper-wrapper">
			        <div class="swiper-slide"><a href="http://bbs.ibaixiong.com/detail/444.html"><img src="../../image/a1.jpg"></a></div>
			        <div class="swiper-slide"><a href="http://bbs.ibaixiong.com/detail/445.html"><img src="../../image/a2.jpg"></a></div>
			        <div class="swiper-slide"><a href="http://bbs.ibaixiong.com/detail/438.html"><img src="../../image/a3.jpg"></a></div>
			        <div class="swiper-slide"><a href="http://bbs.ibaixiong.com/detail/435.html"><img src="../../image/a4.jpg"></a></div>
			        
			    </div>
                 <!-- 如果需要分页器 -->
                 <div class="swiper-pagination"></div>
            </div>

            <div class="_new_section">
            	<c:forEach items="${bbsList }" var="item">
					<div class="_new_wrap row">
	                    <div class="col-lg-4 col-md-4 col-sm-4">
	                    	<div class="_new_img"><a href="/detail/${item.id}.html"><img title="${item.title }" src="${item.picUrl }"></a></div>
	                    </div>
	                    
	                    <div class="col-lg-8 col-md-8 col-sm-8">
		                    <div class="_new_text">
								<h3 class="_new_tag"><a href="/detail/${item.id}.html">${item.title }</a></h3>
								<p class="_new_brief">${item.summary }</p>
								<span class="_new_time"><fmt:formatDate value="${item.createDateTime}" pattern="yyyy.MM.dd"/></span>
							    <div class="titleline">
							    	<!--浏览次数-->
							    	<span class="iconlist">
							    		<i class="icon read"></i>
							    		${item.viewCount }
							    	</span>
							    	<!--评论-->
							    	<span class="iconlist">
							    		<i class="icon reply"></i>
							    		${item.replyCount }
							    	</span>
							    	<!--点赞-->
							    	<span class="iconlist">
							    		<c:if test="${item.pariseyes == 1 }">
					                    	<i class="icon praise" style="background-position:-12px -133px;cursor:auto"></i><span>${item.pariseCount }</span>
					                    </c:if>
					                    <c:if test="${item.pariseyes == null }">
					                    	<i class="icon praise" onclick="parise(${item.id})"></i><span id="pariseCount ${item.id}">${item.pariseCount }</span>
					                    </c:if>
							    	</span>
							    	<!--分享-->
							    	<div class="iconlist">
							    		<i class="icon share"></i>分享
							    		<div class="sharebox">
				                            <a class="hover_" href="###"> <span data-id="${item.id}" data-value="${item.title }" class="shareacon wx"></span></a>
				                            <a href="http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url=http://bbs.ibaixiong.com/share/detail/${item.id}.html" target="_blank"> <span data-id="${item.id}" data-value="${item.title }" class="shareacon kj"></span></a>
				                            <a href="http://connect.qq.com/widget/shareqq/index.html?title=${item.title}&url=http://bbs.ibaixiong.com/share/detail/${item.id}.html" target="_blank"> <span data-id="${item.id}" data-value="${item.title }" class="shareacon qq"></span></a>
				                            <a href="http://v.t.sina.com.cn/share/share.php?url=http://bbs.ibaixiong.com/share/detail/${item.id}.html&title='${item.title }'" target="_blank"> <span data-id="${item.id}" data-value="${item.title }" class="shareacon wb"></span></a>
				                            <div data-id="${item.id}" class="shareImgBox">
				                            	<p class="shareWeixin">扫一扫分享到微信</p>
				                            	<div class="shareWeixinImgBox">
				                            		<img src="#" id="${item.id}" class="shareWeixinImg">
				                            	</div>
				                            </div>
				                        </div>
							    	</div>
							    </div>
							</div>
	                    </div>
	                    
					</div>
            	</c:forEach>
				<!--page-->
		        <jsp:include page="include/pages.jsp">
			        <jsp:param value="/${headType }.html?" name="pageUrl"/>
			    </jsp:include>
		    </div>
		    <div class="loading_more">浏览更多<span class="icon-arrow-right"></span></div>
	   	</div>
		<div class="_new_right">
			<div class="_new_wx">
			  <p class="_new_wx1"><img src="/image/weixin_papabear.jpg"></p>
              <p class="_new_wx2">关注官方微信</p>          
              <p class="_new_wx3">实时获取最新资讯</p>          
			</div>
			<div class="_new_ig">
				<a href="http://www.ibaixiong.com/attract.html"><img src="/image/new3.jpg"></a>
			</div>
			<div id="publishDiv" style="display: block;">
	        	<shiro:hasRole name="administrator">
	        		<input type="button" id="publishBtn" class="publishbtn" value="发      帖" onclick="toPublishArticle()">
	        	</shiro:hasRole>
	        </div>
		</div>
		</div>
	</div>
	<jsp:include page="include/footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	var mySwiper = new Swiper ('.swiper-container', {
        pagination: '.swiper-pagination',
        slidesPerView: 1,
        autoplay :3000,
        speed:300,
        paginationClickable: true,
        spaceBetween: 30,
        loop: true
  }) 
	 $(function(){
		$('.iconlist').hover(function() {
			$(this).children('.sharebox').show();
		}, function() {
			$(this).children('.sharebox').removeClass("bigPic1").hide();
		});
		$('.wx').mouseover(function(){
	        $(this).parent().parent('.sharebox').css('width','217');
	    });
	    $('.wx').mouseout(function(){
	        $(this).parent().parent('.sharebox').css({"width":"55","border-right":"0"});
	    });
	}) 
</script>
<style>
#publishDiv{
    text-align: center;
}
.publishbtn{
    width: 210px;
    margin: 0 auto;
    height: 55px;
    background: #32a5e7;
    color: #fff;
    border: 0;
    font-size: 24px;
}
</style>

</html>