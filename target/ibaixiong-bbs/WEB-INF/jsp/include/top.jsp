<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%
String headType = request.getParameter("headType");
%>
<link rel="icon" href="http://fe.ibaixiong.com/bbs/image/xLogin.ico"
	mce_href="http://fe.ibaixiong.com/bbs/image/xLogin.ico"
	type="image/x-icon">
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
  </script>
  	<input type="hidden" id="headType" value="<%=headType%>"/>
	<div class="avbar-fixed-top">
	    <div class="header">
	        <a href="http://www.ibaixiong.com/"><img src="http://fe.ibaixiong.com/common/image/logo01.png" class="bbs-logo"> </a>
	        <ul class="nav nav-pills nav-justified ">
	            <li id="bxhp"><a class="navlink" href="http://www.ibaixiong.com">首页</a></li>
	            <!-- <li id="bxhp"><a class="navlink" href="http://www.ibaixiong.com">产品</a></li> -->
	            <li id="bxhp"><a class="navlink" target="_blank" href="http://www.ibaixiong.com/attract.html">合作伙伴</a></li>
	           	<c:forEach items="${commonFormList }" var="item">
	           		<li class='${item.permissionsTag==headType?"currentTop":"" }'><a class='navlink' href='${item.url }'>${item.name}</a></li>
	           	</c:forEach>
	            <li id="loginBtn"><a class="navlink" href="http://login.ibaixiong.com?service=http://bbs.ibaixiong.com/index.html">登录/注册</a></li>
	            <li style="display:none ;float:right;padding:0;margin:0;width:48px;" id="yBtn">
		            <a><img src="http://baixiongbasicimage.oss-cn-hangzhou.aliyuncs.com/default_user_image/1.png" class="headImg" style="width:25px;height:25px;margin-right:8px;border-radius:50%;"><img src="http://fe.ibaixiong.com/bbs/image/jiantou.png"></a>
					<span class="sanjiao"></span>
					<div class="person">
						<p><img src="http://baixiongbasicimage.oss-cn-hangzhou.aliyuncs.com/default_user_image/1.png" class="headImg" ><a href="/u/user/index/bbs.html" id="userName"></a></p>
						<p><a href="/u/user/index/bbs.html">个人资料</a></p>
						<!--  
						<p><a href="/u/user/index/bbs.html">发布的帖子</a></p>
						<p><a href="/u/user/index/bbs.html">帖子动态</a></p>
						-->
						<p style="border-bottom:none;"> <a href="/logout.html">退出</a>
						<!--<a href="http://login.ibaixiong.com/logout?service=http://bbs.ibaixiong.com/index.html">退出</a> -->
						</p>
					</div>
				</li>
	            </li>
	        </ul>
	    </div>
	</div>
	<!-- 弹窗 -->
	<div class="remindpop">
    <div class="remindpopbg"></div>
    <p class="remindword">惬意游魔都同住屋檐下</p>
</div>

<style>
.avbar-fixed-top{
    width: 100%;
    background: #fff;
    position: fixed;
    top:0;
    left:0;
    z-index: 9999;
}
.header{
    width: 1200px;
    height:70px;
    margin: 0 auto;
    background: #fff;
}
.nav{
    float: right;
    width: 900px;
    height:70px;
    line-height:70px;
    text-align: right;
}
.bbs-logo{
    width: 179px;
    float: left;
    margin-top:19px;
}
.nav-justified>li{
    width:90px;
    display: inline-block;
    height:70px;
    line-height: 70px;
}
.nav>li>a{
    padding:0;
    font-size:14px;
    color:#666;
}
.current a{
    color:#ff6200;
    float: inherit;
}
</style>