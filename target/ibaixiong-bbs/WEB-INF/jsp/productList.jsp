<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %> 
<!DOCTYPE html>
<html>
<head>
 <meta charset="UTF-8">
 <meta http-equiv="X-UA-Compatible" content="IE=Edge">
 <link href="http://fe.ibaixiong.com/common/plug/bootstrap/bootstrap.min.css" rel="stylesheet" type="text/css">
 <link href="../../css/style.css" rel="stylesheet" type="text/css">
 <script src="http://fe.ibaixiong.com/common/plug/jQuery/jquery-1.8.3.min.js" type="text/javascript" ></script>
 <script src="http://fe.ibaixiong.com/common/plug/jQuery/jquery-migrate-1.2.1.js" type="text/javascript" ></script>
 <script src="http://fe.ibaixiong.com/common/plug/jQuery/jquery.dotdotdot.min.js" type="text/javascript" ></script>
 <script src="http://fe.ibaixiong.com/common/plug/jQuery/jPages.min.js" type="text/javascript" ></script>
 <script src="http://fe.ibaixiong.com/common/plug/jQuery/unslider.min.js" type="text/javascript" ></script>
  <script src="http://fe.ibaixiong.com/bbs/js/qrcode.js"></script>
 <script src="http://fe.ibaixiong.com/bbs/js/base.js" type="text/javascript"></script>
 <script src="http://fe.ibaixiong.com/bbs/js/head.js" type="text/javascript"></script>
 <title>产品讨论-熊爸爸</title>
 <meta name="keywords" content="熊爸爸社区,产品讨论,白熊学院,热门活动"/>
 <meta name="description" content="产品讨论-熊爸爸"/>

    <link href="http://fe.ibaixiong.com/bbs/css/prods.css" rel="stylesheet" type="text/css">
    <link href="http://fe.ibaixiong.com/bbs/css/prodtalk.css" rel="stylesheet" type="text/css">
    <link href="http://fe.ibaixiong.com/bbs/css/activelist.css" rel="stylesheet" type="text/css">
    <script src="http://fe.ibaixiong.com/bbs/js/activelist.js" type="text/javascript" ></script>
    <script>
	    $(document).ready(function() {
	    	$('.cancel').click(function () {
	            $('.popup').hide();
	        });	    	
	    	//二维码显示隐藏
	    	$('.wx').mouseover(function(){
		    	console.log(111)
		        $(this).parent().parent('.sharebox').css('width','217');
		    });
		    $('.wx').mouseout(function(){
		        $(this).parent().parent('.sharebox').css({"width":"55","border-right":"0"});
		    });
	    });
    	function queryF3Article(formId3){
    		//设置form3的值
    		$("#formId2").val(formId3);
    		$("#dateType").val("");
    		$("#sortType").val("");
    		$("#allType").val("");
    		$("#queryButton").click();
    	}
    	function queryF2Article(formId2){
    		//设置form3的值
    		$("#formId1").val(formId2);
    		$("#formId2").val("");
    		$("#dateType").val("");
    		$("#sortType").val("");
    		$("#allType").val("");
    		$("#queryButton").click();
    	}
    	function queryByTime(time){
    		$("#dateType").val(time);
    		$("#allType").val("");
    		$("#queryButton").click();
    	}
    	function queryByOther(o){
    		$("#sortType").val(o);
    		$("#allType").val("");
    		$("#queryButton").click();
    	}
    	function queryAll(q){
    		$("#dateType").val("");
    		$("#sortType").val("");
    		$("#allType").val(q);
    		$("#queryButton").click();
    	}
    </script>
</head>
<body>
  <jsp:include page="include/hide.jsp">
  	<jsp:param value="${headType }" name="headType"/>
  </jsp:include>
  <jsp:include page="include/top.jsp">
  	<jsp:param value="${headType }" name="headType"/>
  </jsp:include>
  <div class="content-top">
        <img src="${form2.thumUrl }" class="themebg">
        <div class="themetext">
            <h3>${form2.name }</h3>
            <p>${form2.description }</p>
        </div>
        <shiro:hasPermission name="product:publish">
	        <input type="button" value="发布主题" class="pubtheme" onclick="toPublishArticle()">
        </shiro:hasPermission>
    </div>
 <div class="content">
 	<!-- 隐藏域提交与取值区域 -->
 	<div style="display: none;">
 		<form name="productForm" action="${form.url }" method="post">
			<input type="hidden" name="formId1" id="formId1" value="${formId1 }"/>
			<input type="hidden" name="formId2" id="formId2" value="${formId2 }"/>
			<input type="hidden" name="dateType" id="dateType" value="${dateType }"/>
			<input type="hidden" name="sortType" id="sortType" value="${sortType }"/>
			<input type="hidden" name="allType" id="allType" value="${allType }"/>
			<input type="hidden" name="pageNo" id="pageNo" value="">
			<button type="submit" id="queryButton" class="search" >提交</button>
		</form>
	</div>
 	<!-- 主体内容页面 -->
 	<!-- 二级form显示 -->
    <div class="content-left">
    	<!-- 3级目录选择显示 -->
        <div class="row navlist">
            <div class="col-xs-12" style="position: absolute;top:0;left:0;height:60px;border-bottom:1px solid #dcdcdc;">
            	<c:forEach items="${form3List}" var="form3">
            		<c:if test="${form3.id == formId2  }">
            			<!-- 
            			<span class="talknav selectednav" onclick="queryF3Article(${form3.id})"><i class="navicon" style="background-image: url('${form3.thumUrl}'); margin-right:3px;"></i>${form3.name }</span>
            			 -->
            			 <a href="${form.url }?formId1=${formId1 }&formId2=${form3.id}" class="talknav selectednav"><i class="navicon" style="background-image: url('${form3.thumUrl}'); margin-right:3px;"></i>${form3.name }</a>
            		</c:if>
            		<c:if test="${form3.id != formId2  }">
            			<!-- <span class="talknav" onclick="queryF3Article(${form3.id})"><i class="navicon" style="background-image: url('${form3.thumUrl}');margin-right:3px;"></i>${form3.name }</span> -->
            			<a href="${form.url }?formId1=${formId1 }&formId2=${form3.id}" class="talknav" ><i class="navicon" style="background-image: url('${form3.thumUrl}');margin-right:3px;"></i>${form3.name }</a>
            			
            		</c:if>
            	</c:forEach>
            </div>
        </div>
        <div class="row condrow">
            <div class="col-xs-6">
                <span class="selectinput classfiy">
	                <c:if test="${dateType == null || dateType == 0  }">
	                	<span class="selectvalue">全部时间</span>
	                </c:if>
	                <c:if test="${dateType == 1 }">
	                	<span class="selectvalue">今天</span>
	                </c:if>
	                <c:if test="${dateType == 2  }">
	                	<span class="selectvalue">三天</span>
	                </c:if>
	                <c:if test="${dateType == 3  }">
	                	<span class="selectvalue">一周</span>
	                </c:if>
	                <c:if test="${dateType == 4 }">
	                	<span class="selectvalue">一个月</span>
	                </c:if>
	                <c:if test="${dateType == 5  }">
	                	<span class="selectvalue">三个月</span>
	                </c:if>
                    <i class="arrowright"></i>
                    <ul class="option">
                        <i class="arrowtop"></i>
                        <li onclick="queryByTime(0)">全部时间</li>
                        <li onclick="queryByTime(1)">今天</li>
                        <li onclick="queryByTime(2)">三天</li>
                        <li onclick="queryByTime(3)">一周</li>
                        <li onclick="queryByTime(4)">一个月</li>
                        <li onclick="queryByTime(5)">三个月</li>
                    </ul>
                </span>
                <span class="selectinput classfiy">
                	<c:if test="${sortType == null || sortType == 0  }">
	                	<span class="selectvalue">默认排序</span>
	                </c:if>
	                <c:if test="${sortType == 1  }">
	                	<span class="selectvalue">回复数</span>
	                </c:if>
	                <c:if test="${sortType == 2  }">
	                	<span class="selectvalue">查看数</span>
	                </c:if>
	                <c:if test="${sortType == 3  }">
	                	<span class="selectvalue">点赞数</span>
	                </c:if>
                    <i class="arrowright"></i>
                    <ul class="option">
                        <i class="arrowtop"></i>
                        <li onclick="queryByOther(0)">默认排序</li>
                        <li onclick="queryByOther(1)">回复数</li>
                        <li onclick="queryByOther(2)">查看数</li>
                        <li onclick="queryByOther(3)">点赞数</li>
                    </ul>
                </span>
            </div>
            <div class="col-xs-6 tr" style="color:#dcdcdc;">
            	<c:if test="${allType == 0}">
            		<a href="#" onclick="queryAll(1)"><span class="condition smallsize" style="color:#ff6200;">全部</span></a>|
                	<a href="#" onclick="queryAll(3)"><span class="condition smallsize">精华</span></a>|
                	<a href="#" onclick="queryAll(2)"><span class="condition smallsize">最新</span></a>
            	</c:if>
                <c:if test="${allType == 1}">
            		<a href="#" onclick="queryAll(1)"><span class="condition selectedcond smallsize style="color:#ff6200;">全部</span></a>|
                	<a href="#" onclick="queryAll(3)"><span class="condition smallsize">精华</span></a>|
                	<a href="#" onclick="queryAll(2)"><span class="condition smallsize">最新</span></a>
            	</c:if>
            	<c:if test="${allType == 2}">
            		<a href="#" onclick="queryAll(1)"><span class="condition smallsize style="color:#ff6200;">全部</span></a>|
                	<a href="#" onclick="queryAll(3)"><span class="condition smallsize">精华</span></a>|
                	<a href="#" onclick="queryAll(2)"><span class="condition selectedcond smallsize">最新</span></a>
            	</c:if>
            	<c:if test="${allType == 3}">
            		<a href="#" onclick="queryAll(1)"><span class="condition smallsize style="color:#ff6200;">全部</span></a>|
                	<a href="#" onclick="queryAll(3)"><span class="condition selectedcond smallsize">精华</span></a>|
                	<a href="#" onclick="queryAll(2)"><span class="condition smallsize">最新</span></a>
            	</c:if>
            </div>
        </div>
        <ul class="cllist">
        	<c:forEach items="${bbsList}" var="article">
            <li style="height:100px;">
                <div class="row"> 
                    <div class="col-xs-1">
                        <img src="${article.user.avatarImg }" class="personimg">
                    </div>
                    <div class="col-xs-11" style="margin-top:6px;">
                        <div class="row">
                            <div class="col-xs-12" style="padding-left:0px;"><a href="/detail/${article.id}.html" class="link" style="float: left;"> ${article.title }</a>
	                            <c:if test="${article.good == 1}"><i class="jing" style="float:left;margin-top:11px;margin-left:5px;"></i></c:if>
	                            <c:if test="${article.top == 1}"><i class="ding" style="float:left;margin-top:11px;margin-left:5px;"></i></c:if>
	                            <c:if test="${article.officialCertification == 1}"><i class="guan" style="float:left;margin-top:11px;margin-left:5px;"></i></c:if>
                            </div>
                            <div class="col-xs-6 light smallsize" style="padding-left:0px;">
                                <a href="/user/index/${article.user.id}/${headType }.html" class="light smallsize link">${article.user.nickName}<img src="${article.user.gradeEntity.url }"  style="margin-left:2px;"/> </a>
                                <span class="span" style="margin-left:10px;"></span>
                                	发表于
                                <span class="span"  style="margin-left:10px;"></span>
                                <fmt:formatDate value="${article.createDateTime}" pattern="yyyy/MM/dd HH:mm:ss"/>
                            </div>
                            <div class="col-xs-6 tr light smallsize">
                                <span class="iconlist report" onclick="toReport(${article.id})">举报</span>
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
                                <span class="iconlist" style="float:right;">
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
                    </div>
                </div>
            </li>
            </c:forEach>
        </ul>
        <jsp:include page="include/pages.jsp">
        	<jsp:param value="/discuss.html?formId1=${formId1 }&formId2=${formId2}&" name="pageUrl"/>
        </jsp:include>
    </div>
    <!-- 右边页面 -->
    <div class="content-right">
        <jsp:include page="include/loginUser.jsp"></jsp:include>
        <!-- 活跃成员 -->
        <jsp:include page="include/activeUser.jsp">
        	<jsp:param value="${queryType }" name="queryType"/>
        </jsp:include>
        <!-- 版块推荐 -->
        <div class="forum">
            <h3 class="top">版块推荐</h3>
            <c:forEach items="${form2List}" var="form2">
            	<div class="row smallsize">
            	<!-- 
                	<div class="col-xs-12 link" style="cursor:pointer;" onclick="queryF2Article(${form2.id})">${form2.name }</div> -->
                	<a href="${form.url }?formId1=${form2.id}" class="col-xs-12 link" style="cursor:pointer;">${form2.name }</a>
            	</div>
            </c:forEach>
        </div>
        <!-- 最新活动 -->
        <jsp:include page="include/activeArticleList.jsp"></jsp:include>
    </div>
</div>
<script type="text/javascript">
	function toReport(articleId){
		$('.reportpop').show();
		$("#repArticleId").val(articleId);
	}
	function setReportType(v){
		$("#reportType").val(v);
	}
	function report(){
		var repArticleId = $("#repArticleId").val();
		if(repArticleId == null || repArticleId ==undefined || repArticleId == ''){
			alertLayel("帖子ID不可为空！");
			return false;
		}
		var reportType = $("#reportType").val();
		if(reportType == null || reportType ==undefined || reportType == ''){
			alertLayel("请选择举报内容！");
			return false;
		}
		$.ajax({
  		   url: "/u/article/reportArticle.html?articleId="+repArticleId+"&reportType="+reportType,
  		   type: "get",
  		   dataType:"json",
  		   cache:false,
  		   success: function(obj){
  			 	$('.popup').hide();
  	  			if ( !checkCode( obj ) ) {
 					return;
 			    }
			 	if ( obj.code == 0 ) {
					//获取登陆用户成功之后给值
					alertLayel("举报成功!");
				}
  		   }
  		});
	}
</script>
<input type="hidden" id="repArticleId" value=""/>
<input type="hidden" id="reportType" value=""/>
<!--弹窗-->
<div class="popup reportpop">
    <div class="warnbg"></div>
    <div class="row warnpop">
        <div class="col-xs-12" style="padding:0;margin-bottom:17px;"><h3 class="wt">举报帖子<i class="close"></i> </h3></div>
        <div class="col-xs-6 leftpart">
            <input type="radio" name="reason" onclick="setReportType(1)"><span>广告垃圾</span>
        </div>
        <div class="col-xs-6">
            <input type="radio" name="reason" onclick="setReportType(2)"><span>恶意灌水</span>
        </div>
        <div class="col-xs-6 leftpart">
            <input type="radio" name="reason" onclick="setReportType(3)"><span>重复发帖</span>
        </div>
        <div class="col-xs-6">
            <input type="radio" name="reason" onclick="setReportType(4)"><span>谩骂</span>
        </div>
        <div class="col-xs-6 leftpart">
            <input type="radio" name="reason" onclick="setReportType(5)"><span>违规信息</span>
        </div>
        <div class="col-xs-6">
            <input type="radio" name="reason" onclick="setReportType(6)"><span>其他</span>
        </div>
        <div class="col-xs-12 tc">
            <input type="button" value="确定" class="delete" onclick="report()">
            <input type="button" value="取消" class="cancel">
        </div>
    </div>
</div>
<jsp:include page="include/footer.jsp"></jsp:include>
</body>
</html>
