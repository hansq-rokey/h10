<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
  <script>
    $(document).ready(function(){
    	var Rand = Math.random();  
        $.ajax({
  		   url: "/u/user/getUserInfo.html?"+Rand,
  		   type: "POST",
  		   dataType:"json",
  		   cache:false,
  		   success: function(obj){
			 	if ( obj.code == 0 ) {
					//获取登陆用户成功之后给值
					$("#signBtn").hide();//隐藏签到按钮
					$("#userDiv").show();
					$("#loginUserName").find('#username').text(obj.result.user.nickName);
					$('#expId').text(obj.result.user.expNum);
					$('#pointsId').text(obj.result.user.points);
					$("#userHeadImg").attr("src",obj.result.user.headImg);
					$("#grade").attr("src",obj.result.user.gradeImg);
					isSign();
				}
			 	//可能未登陆跳转到单点登陆页面 
  		   }
  		}); 
        /*$.ajax({
    		url: '/u/user/getUserInfo.html?callbackName=getUserInfo',
    		dataType:'jsonp',
    		jsonp:'callback'
    	});*/
    	
    	$(".sign").click(function(){
    		$(".signed").animate({left:'0px'},800);
    		$(".sign").animate({marginLeft:'180px'},800);
    	});
    });

    function getUserInfo( obj ) {
    	if ( obj.code == 0 ) {
    		$("#userDiv").show();
			$("#loginUserName").find('#username').text(obj.result.user.nickName);
			$("#userHeadImg").attr("src",obj.result.user.headImg);
			$("#grade").attr("src",obj.result.user.gradeImg);
    	}
    }
    function isSign(){
    	$.ajax({
    	   url: "/u/user/isSign.html",
    	   type: "get",
    	   dataType:"json",
    	   cache:false,
    	   success: function(obj){
    		 	if ( obj.code == 1 ) {
    		 		//说明已签到了
    		 		$(".signed").css({left:'0px'});
		    		$(".sign").css({marginLeft:'180px'});
    			}else{
    				$("#signBtn").show();//隐藏签到按钮
    			}
    	   }
    	});
    }
  </script>
      <div class="signbox" id="userDiv" style="display: none;">
        	<div class="personal">
        		<a href="/u/user/index/${headType }.html"><img src="" class="userimg" id="userHeadImg" style="float:left;"></a>
        		<div class="pers-content">
        			<p class="name" id="loginUserName"><a href="/u/user/index/${headType }.html"><span id="username"></span></a><img src="" id="grade"/></p>
        			<div class="experiencebox">
	        			<p class="number" id="expId">32256</p>
	        			<span class="experience">经验</span>
	        		</div>
	        		<div class="integralbox">
	        			<p class="number" id="pointsId">623</p>
	        			<span class="integral">积分</span>
	        		</div>
        		</div>
        	</div>
       <div class="qiandaobtn" style="width:180px;height:38px; margin:0 auto;position:relative;overflow:hidden;margin-top:20px;">
      	    <input type="button" value="已 签 到" class="signed" onclick="signed()" style="position:absolute;left:-180px;top:0px;">
      	    <input type="button" value="签      到" class="sign" id="signBtn" onclick="sign()">
       </div>
        </div>
