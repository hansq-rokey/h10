<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html>
<html>
<head>
  <jsp:include page="include/head.jsp"></jsp:include>
  <link href="http://fe.ibaixiong.com/bbs/css/prods.css" rel="stylesheet" type="text/css">
  <script src="http://fe.ibaixiong.com/bbs/js/prods.js" type="text/javascript" ></script>
</head>
<body>
<!-- 分页使用 -->
<div style="display: none;">
	<form action="/article/queryactivityList/3.html" method="post">
	 	<input type="hidden" name="pageNo" id="pageNo" value="">
	    <input type="submit" id="pageSubmit" class="search" value="搜索">        	
	</form>
</div>
  <jsp:include page="include/hide.jsp">
  	<jsp:param value="${headType }" name="headType"/>
  </jsp:include>
  <jsp:include page="include/top.jsp">
  	<jsp:param value="${headType }" name="headType"/>
  </jsp:include>
   	<div class="content-top">
        <img src="http://fe.ibaixiong.com/bbs/image/hotActivity.png" class="themebg">
        <div class="themetext">
            <h3>热门活动</h3>
            <p>熊爸爸说：即使你不在家，也始终为你保留爱的温度。</p>
        </div>
        <shiro:hasPermission name="activity:publish">
        	<input type="button" value="发布主题" class="pubtheme" onclick="toPublishArticle()">
        </shiro:hasPermission>
    </div>
 <div class="content">
 	<!-- 主体内容页面 -->
    <div class="content-left">
        <ul class="cllist">
        <c:forEach items="${bbsList}" var="article">
        	<li>
                <div class="titleline">
                    <h3 class="title" style="margin-bottom:9px;margin-top:-2px;" ><a href="/detail/${article.id}.html">${article.title }</a></h3>
                    <span class="iconlist"><i class="icon flag"></i>${article.applyCount }</span><!-- 报名人数-->
                    <span class="iconlist"><i class="icon read"></i>${article.viewCount }</span><!-- 查看次数 -->
                    <span class="iconlist"><i class="icon reply"></i>${article.replyCount }</span><!-- 回复次数 -->
                    <span class="iconlist">
                    <c:if test="${article.pariseyes == 1 }">
                    	<i class="icon praise" style="background-position:-12px -133px;cursor:auto"></i><span>${article.pariseCount }</span>
                    </c:if>
                    <c:if test="${article.pariseyes == null }">
                    	<i class="icon praise" onclick="parise(${article.id})"></i><span id="pariseCount${article.id}">${article.pariseCount }</span>
                    </c:if>
                    </span><!-- 点赞次数 -->
                    <span class="iconlist">
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
                <p class="imgbox">
                <a href="/detail/${article.id}.html"><img src="${article.picUrl }"></a><!-- 发帖配图 -->
                </p>
                <p class="contenttext">
                	${article.summary }<!-- 内容 -->
                </p>
                <p class="bottomline">
                <fmt:formatDate value="${article.createDateTime}" pattern="yyyy/MM/dd HH:mm:ss"/> 
                <span class="space"></span> <a href="/detail/${article.id}.html" class="readall">阅读全文>></a> </p>
            </li>
        </c:forEach>
        </ul>
        <jsp:include page="include/pages.jsp">
        <jsp:param value="/activity.html?" name="pageUrl"/>
        </jsp:include>
    </div>
    <!-- 右边页面 -->
    <div class="content-right">
    	<div class="notice" style="margin-bottom:15px;">
            <h3 class="top">公告</h3>
            <div class="row">
                <div class="col-xs-12">
                     <p class="noticetext">熊爸爸系列产品即将召开新品发布会，敬请关注!</p>
                </div>
             </div>
        </div>
        <jsp:include page="include/loginUser.jsp"></jsp:include>
        <!-- 活跃成员 -->
        <jsp:include page="include/activeUser.jsp">
        	<jsp:param value="${queryType }" name="queryType"/>
        </jsp:include>
    </div>
</div>
<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>
