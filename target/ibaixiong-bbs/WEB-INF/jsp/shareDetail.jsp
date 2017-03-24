<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
  	<meta charset="UTF-8">
  	<link href="http://fe.ibaixiong.com/bbs/css/phone.css" rel="stylesheet" type="text/css">
    <script src="http://fe.ibaixiong.com/common/plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
	<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"  />
	<meta name="format-detection" content="telephone=no,address=no,email=no" />
	<meta name="mobileOptimized" content="width" />
	<meta name="handheldFriendly" content="true" />
	<meta name="apple-mobile-web-app-capable" content="yes" />
	<meta name="apple-mobile-web-app-status-bar-style" content="black" />
</head>
<style>
._black{
    border-bottom: 1px solid #ccc;
    overflow: hidden;
    width: 100%;
    height:40px;
    position: relative;
}
._black_top{
    background: url(../../image/new_black.png);
    background-size: cover;
    background-repeat: no-repeat;
    position: absolute;
    height: 20px;
    width: 20px;
    top: 10px;
    left: 10px;
    display: block;
    background-size: cover; padding-left: 20px;
}
</style>
<script>
	$(document).ready(function(){
		$('.content').find('img').css({maxWidth:'100%',height:'auto'})
})
/*pageshow*/
 var EventUtil = {
    addHandler: function (element, type, handler) {
        if (element.addEventListener) {
            element.addEventListener(type, handler, false);
        } else if (element.attachEvent) {
            element.attachEvent("on" + type, handler);
        } else {
            element["on" + type] = handler;
        }
    }
 };
(function () {
    var showCount = 0;
    EventUtil.addHandler(window, "load", function () {
       /*  alert("Load fired"); */
    });
    EventUtil.addHandler(window, "pageshow", function (event) {
        showCount++;
        /* alert("Show has been fired " + showCount + " times."); */
    });
})();
</script>
<body>
	<div class="_black">
     <a href="javascript:history.go(-1);" class="_black_top"></a>
    </div>
    <div class="avbar-fixed-top">
	    <div class="new_header">
	        <a href="http://www.ibaixiong.com/"><img src="http://fe.ibaixiong.com/common/image/logo01.png" class="bbs-logo"> </a>
	        <ul class="new_nav nav-pills nav-justified ">
	            <li id="bxhp"><a class="navlink" href="http://www.ibaixiong.com">首页</a></li>
	            <li id="bxhp"><a class="navlink" target="_blank" href="http://www.ibaixiong.com/attract.html">合作伙伴</a></li>
	           	<c:forEach items="${commonFormList }" var="item">
	           		<li class='${item.permissionsTag==headType?"currentTop":"" }'><a class='navlink' href='${item.url }'>${item.name}</a></li>
	           	</c:forEach>
	                <li id="loginBtn"><a class="navlink" href="http://login.ibaixiong.com?service=http://bbs.ibaixiong.com/u/user/index.html">登录/注册</a></li>
	                <li class="yBtn" id="yBtn" style="display:none">
			            <a><img src="http://baixiongbasicimage.oss-cn-hangzhou.aliyuncs.com/default_user_image/1.png" class="headImg" style="width:25px;height:25px;margin-right:8px;border-radius:50%;"><img src="http://fe.ibaixiong.com/bbs/image/jiantou.png"></a>
						<span class="sanjiao"></span>
						<div class="person">
							<p><img src="http://baixiongbasicimage.oss-cn-hangzhou.aliyuncs.com/default_user_image/1.png" class="headImg"><a href="/index.html" id="userName"></a></p>
							<p><a href="/u/user/index/bbs.html">个人资料</a></p>
							<p style="border-bottom:none;"> <a href="/logout.html">退出</a>
							</p>
						</div>
				</li>
	        </ul>
	    </div>
	</div>
    <div class="content">
		<p class="activetitle">${articleDetail.title }</p>
		<div class="light smallsize">
			发表在
			<span class="darker">${articleDetail.fromName }</span>
			<fmt:formatDate value="${articleDetail.createDateTime}" pattern="yyyy/MM/dd HH:mm:ss"/>
			<span class="iconlist"><i class="icon read"></i><span class="smallsize">${articleDetail.viewCount }</span></span>
            <span class="iconlist"><i class="icon reply"></i><span class="smallsize">${articleDetail.replyCount }</span></span>
		</div>
		<div class="row">
			<p class="articletext">${articleDetail.detail.content}</p>
		</div>
    </div>
</body>
</html>
<style>
.avbar-fixed-top {
        width: 100%;
        background: #fff;
        position:relative;
        top: 0;
        left: 0;
        z-index: 9999;
        overflow: hidden;
      }
    .yBtn{
		display:none ;
		float:right;
		padding:0;
		margin:0;
		width:48px;
		position: relative;
	}  
    ._new_content {
        width: 100%;
        margin-top:10px;
        overflow: hidden;
    }
   .new_header{
     text-align: center;
     width: 100%;
     height: 8em;
     margin: 0 auto;
     background: #fff;
   }
   .bbs-logo {
    width: 179px;
    float:inherit;
    margin:0.8em 0;
   }
   .new_header a{
    overflow: hidden;
    color:#666;
    text-decoration: none;
    display: inline-block;
   }
   #loginBtn,._new_right{
    display: none;
    opacity: 0;
   }
   .new_nav{
     width: 100%;
     height: 48px;;
     line-height:0;
     text-align:inherit;
     border-top:1px solid #D9D9D9;
     border-bottom:1px solid #D9D9D9;
   }
   .nav-justified>li {
    width:24%;
    display: inline-block;
    height:3em;
    float: left;
    line-height: 3em;
  }
   .new_nav li .currentTop a{
		color: #ff6200;
	}
</style>
