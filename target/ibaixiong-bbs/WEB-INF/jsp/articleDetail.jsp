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
 <script src="http://fe.ibaixiong.com/common/js/common.js" type="text/javascript"></script>
 <title>${articleDetail.title }-熊爸爸社区</title>
 <meta name="keywords" content="熊爸爸社区,产品讨论,白熊学院,热门活动"/>
 <meta name="description" content="${articleDetail.title }"/>

  <link href="http://fe.ibaixiong.com/bbs/css/prods.css" rel="stylesheet" type="text/css">
  <link href="http://fe.ibaixiong.com/bbs/css/prodtalk.css" rel="stylesheet" type="text/css">
  <link href="http://fe.ibaixiong.com/bbs/css/activedetail.css" rel="stylesheet" type="text/css">
  <link href="../css/postdetail.css" rel="stylesheet" type="text/css">
  <script src="http://fe.ibaixiong.com/bbs/js/postdetail.js" type="text/javascript" ></script>
  <script type="text/javascript" charset="utf-8" src="/ueditor.config.js"></script>
  <script type="text/javascript" charset="utf-8" src="/ueditor.all.min.js"> </script>
  <style type="text/css">
  .signTemp{
  width: 274px;
  height: 55px;
  background: #ff6200 none repeat scroll 0% 0%;
  border: medium none;
  font-size: 24px;
  color: #FFF;
  margin-bottom: 10px;
  }
  .imgwidth img{
  max-width:100%; 
  }
  </style>
   <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
   <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
  <script type="text/javascript" charset="utf-8" src="/lang/zh-cn/zh-cn.js"></script>
  <script type="text/javascript">
    browserRedirect('/share/detail/${articleDetail.id}.html');
     var ue = UE.getEditor('editor', {
      //初始化工具栏菜单 
          toolbars: [
                     ['bold','fontsize','forecolor','backcolor','rowspacingtop','justifyleft','justifycenter','justifyright','justifyjustify','simpleupload','insertimage','insertvideo','link','unlink','attachment']
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
          //二维码显示隐藏
          $('.wx').mouseover(function(){
            console.log(111)
              $(this).parent().parent('.sharebox').css('width','217');
          });
          $('.wx').mouseout(function(){
              $(this).parent().parent('.sharebox').css({"width":"55","border-right":"0"});
          });
        });
        //
        $('.replytext').each(function(){
          if($(this).text()==''){
            $(this).remove();
          }
        })
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
            var nickName = obj.result.user.nickName;
            if(nickName == null || nickName ==undefined || nickName == ''){
               $(".nickpop").show();
               b = false;
              }
          }else{
            b = false;
          }
          //可能未登陆跳转到单点登陆页面
           },error: function(XMLHttpRequest) {
            if(XMLHttpRequest.readyState==0&&XMLHttpRequest.responseText==""){
              alertLayel("请登录后重试");
              b = false;
                }
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
            var nickName = obj.result.user.nickName;
            if(nickName == null || nickName ==undefined || nickName == ''){
               $(".nickpop").show();
               b = false;
              }
          }else{
            b = false;
          }
          //可能未登陆跳转到单点登陆页面
           },error: function(XMLHttpRequest) {
            if(XMLHttpRequest.readyState==0&&XMLHttpRequest.responseText==""){
              alertLayel("请登录后重试");
              b = false;
                }
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
           },error: function(XMLHttpRequest) {
            if(XMLHttpRequest.readyState==0&&XMLHttpRequest.responseText==""){
              alertLayel("请登录后重试");
              b = false;
                }
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
           dataType:"json",
           data:{"nickName":nickNameText},
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
              <p>熊熊大本营，一起来撒欢</p>
          </div>
        </c:if>
        <c:if test="${headType == 'product'}">
          <img src="${form.thumUrl }" class="themebg">
          <div class="themetext">
              <h3>${form.name}</h3>
              <p>${form.description }</p>
          </div>
        </c:if>
        <c:if test="${headType == 'school'}">
          <img src="http://fe.ibaixiong.com/bbs/image/hotactive.png" class="themebg">
          <div class="themetext">
              <h3>白熊学院</h3>
              <p>这里有各种各样的“冷”知识，多学点没错的，万一明天吹牛时用得上呢？</p>
          </div>
        </c:if>
        <c:if test="${headType == 'activity'}">
          <img src="http://fe.ibaixiong.com/bbs/image/hotactive.png" class="themebg">
          <div class="themetext">
              <h3>热门活动</h3>
              <p>熊爸爸说：即使你不在家，也始终为你保留爱的温度。</p>
          </div>
        </c:if>
        <a href="http://www.ibaixiong.com/attract.html"><img src="http://baixiong-fe.oss-cn-hangzhou.aliyuncs.com/shop/image/attheader.jpg"></a>
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
                    <span class="iconlist">
                    <c:if test="${articleDetail.pariseyes == 1 }">
                      <i class="icon praise" style="background-position:-12px -133px;cursor:auto"></i><span>${articleDetail.pariseCount }</span>
                    </c:if>
                    <c:if test="${articleDetail.pariseyes == null }">
                      <i class="icon praise" onclick="parise(${articleDetail.id})"></i><span id="pariseCount${articleDetail.id}">${articleDetail.pariseCount }</span>
                    </c:if>
                    </span><!-- 点赞次数 -->
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
            <div class="row imgwidth">
                <p class="articletext">${articleDetail.detail.content}</p>
            </div>
            <c:if test="${empty isView}">
            <shiro:hasPermission name="bbs:reply">
              <div class="row applyrow">
                <form action="/u/article/replySave.html" onsubmit="return checkReplySave1()" method="post">
                  <input type="hidden" name="headType" value="${headType }" id="headType"/>
                  <input type="hidden" name="article.id" value="${articleDetail.id }" id="articleId"/>
                    <div class="col-xs-12">
                        <img src="http://fe.ibaixiong.com/bbs/image/userimg.png" class="applyimg" id="detailHeadImg">
                        <input type="text" class="applytext" name="replyContent" id="content">
                        <input type="submit" class="applybtn" value="发表"><input type="button" class="collectbtn" onclick="collection(${articleDetail.id })" value="收藏">
                        <!-- <div class="cancelcollectbox">  取消收藏
                          <input type="button" class="cancelcollectItem" value="取消收藏">
                          <div class="cancelcollectpop tc">
                            <p>确定要取消收藏吗？</p>
                            <div class="tc"><input type="button" class="cancelcollectsure" value="确定"><input type="button" class="cancelcollectNo" value="取消"></div>
                          </div>
                        </div> -->
                    </div>
                  </form>
              </div>
          </shiro:hasPermission>
            </c:if>
        </div>
        <c:if test="${empty isView}">
        <ul class="cllist" style="min-height:0;">
          <c:forEach items="${replyList}" var="reply" varStatus="status">
            <li>
                <div class="row">
                    <div class="col-xs-1">
                        <img src="${reply.user.avatarImg }" class="replyimg">
                    </div>
                    <div class="col-xs-11">
                        <div class="row" style="margin-top:6px;">
                            <div class="col-xs-12 light">
                                <a href="/user/index/${reply.user.id}/${headType }.html" class="bluetext"> ${reply.user.nickName}<img src="${reply.user.gradeEntity.url }" style="padding-bottom:2px; margin-right:5px;margin-left:2px;"/> </a>
                                <span><fmt:formatDate value="${reply.createDateTime}" pattern="yyyy/MM/dd HH:mm:ss"/></span>
                                <span class="floor">
                                  <c:if test="${status.index == 0 }">沙发</c:if>
                                  <c:if test="${status.index == 1 }">板凳</c:if>
                                  <c:if test="${status.index == 2 }">地板</c:if>
                                </span>
                            </div>
                            <div class="col-xs-12">
                                <div class="row">

                                </div>
                            </div>
                            <c:if test="${reply.quoteId != null }"><!-- 说明有回复的内容 -->
                              <div class="col-xs-12 light smallsize secondreply">
                                  <div class="row">
                                      <div class="col-xs-12">
                                          <img src="${reply.quoteAvatarImg}" class="secondreplyimg">
                                          <span class="secondreplyname">${reply.quoteUserNick}</span>
                                          <span><fmt:formatDate value="${reply.quoteCreateDateTime}" pattern="yyyy/MM/dd HH:mm:ss"/></span>
                                      </div>
                                  </div>
                                  <c:if test="${reply.quoteContent != null }">
                                  <p class="replytext dark">${reply.quoteContent}</p>
                                  </c:if>
                              </div>
                            </c:if>
                            <div class="col-xs-12 light smallsize">
                                  <c:if test="${reply.replyContent != null }">
                                  <p class="replytext dark">${reply.replyContent}</p>
                                  </c:if>
                            </div>
                            <shiro:hasPermission name="bbs:reply">
                            <div class="col-xs-12 tr light smallsize">
                                <span class="iconlist"><a href="/u/article/toReply/${reply.id }/${headType }.html" onclick="return checkUserNick()">回复</a></span>
                            </div>
                            </shiro:hasPermission>
                        </div>
                    </div>
                </div>
            </li>
            </c:forEach>
        </ul>
        <!-- 回复-->
        <shiro:hasPermission name="bbs:reply">
        <div class="content-bottom">
            <div class="row">
                <div class="col-lg-12"> <h3 class="replytop">发表回复</h3></div>
            </div>
            <form action="/u/article/replySave.html" onsubmit="return checkReplySave2()" method="post">
              <input type="hidden" name="headType" value="${headType }" id="headType"/>
              <input type="hidden" name="article.id" value="${articleDetail.id }" id="articleId"/>
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
        </shiro:hasPermission>
        </c:if>
    </div>
    <div class="content-right">
      
        <div class="userinfor" style="margin-bottom: 10px;height:142px;">
            <div class="row">
                <div class="col-xs-4" style="width:115px;">
                    <img src="${articleDetail.user.avatarImg }" class="userimg">
                </div>
                <div class="col-xs-8" style="width:160px;">
                    <div class="row">
                        <div class="col-xs-12" style="text-align:left;padding-top:10px;">${articleDetail.user.nickName }<img src="${articleDetail.user.gradeEntity.url }" style="margin-left:3px;padding-bottom:2px"/> </div>
                        <div class="col-xs-12" style="text-align:left;padding-top:6px;"><a href="/user/index/${articleDetail.user.id}/${headType }.html" ><input type="button" value="个人主页" class="userhome"> </a></div>
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
<style type="text/css">
  .content-top img{
    width: 100%;
    height: 100%;
  }
</style>