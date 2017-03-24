<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String userId = request.getParameter("userId");
String headType = request.getParameter("headType");
%>
<!DOCTYPE html>
<html>
  <head>
  </head>
  <script>
    $(document).ready(function(){
    	var userId = $("#userId").val();
        $.ajax({
  		   url: "/user/getUserInfo.html?userId="+userId,
  		   type: "POST",
  		   dataType:"json",
  		   cache:false,
  		   success: function(obj){
			 	if ( obj.code == 0 ) {
			 		$("#headImg1").hide();
			 		$("#headImg2").hide();
					$("#nikeName").text(obj.result.user.nickName);
					$("#userHeadImg").attr("src",obj.result.user.headImg);
					$("#userHeadImgLog").attr("src",obj.result.user.headImg);
					$("#points").text(obj.result.user.points);
					$("#expNum").text(obj.result.user.expNum);
					if(obj.result.user.isupdate == 1){
						$("#headImg1").show();
					}
					if(obj.result.user.isupdate == 0){
						$("#headImg2").show();
					}
				}
  		   }
  		});
    });
    function setHeadImg(imgVal){
    	//选择完头像默认点击上传头像form
    	$("#subImg").click();
    }
    function check(){
    	var file = $("#file").val();
    	if(file == null || file == undefined || file == ''){
    		alertLayel("请选择头像！");
		   return false;
	    }
    	if(!isPicture(file)){
    		alertLayel("文件类型不合法,只能是jpg、gif、bmp、png、jpeg类型！");
  		   return false;
    	}
    	return true; 
    }
    function isPicture(fileName){
        if(fileName!=null && fileName !=""){
          //lastIndexOf如果没有搜索到则返回为-1
          	if (fileName.lastIndexOf(".")!=-1) {
    		    var fileType = (fileName.substring(fileName.lastIndexOf(".")+1,fileName.length)).toLowerCase();
    		    var suppotFile = new Array();
    		    suppotFile[0] = "jpg";
    		       suppotFile[1] = "gif";
    		       suppotFile[2] = "bmp";
    		       suppotFile[3] = "png";
    		       suppotFile[4] = "jpeg";
    		    for (var i =0;i<suppotFile.length;i++) {
    			    if (suppotFile[i]==fileType) {
    			    	return true;
    			    } else{
    			     	continue;
    			    }
    		    }
    		    //alertLayel("文件类型不合法,只能是jpg、gif、bmp、png、jpeg类型！");
    		    return false;
    	    } else{
    	    //alertLayel("文件类型不合法,只能是 jpg、gif、bmp、png、jpeg 类型！");
    	      return false;
    	    }
    	}
    } 
  </script>
  <body>
  	<input type="hidden" id="userId" value="<%=userId%>"/>
	<div class="left-top">
		   <form action="/u/user/saveHeadImg/<%=headType %>.html" onsubmit="return check()" method="post" enctype="multipart/form-data">  
           <div class="row" id="headImg1">
               <div class="col-xs-12"> <div class="userimgbox"><img src="" class="userimg" id="userHeadImg"><p class="changeuserimg">修改头像</p><input type="file" id="file" name="file" class="file" onchange="setHeadImg(this.value)"> </div></div>
           </div>
           <div class="row" id="headImg2">
               <div class="col-xs-12"> <div class="userimgbox"><img src="" class="userimg" id="userHeadImgLog"></div></div>
           </div>
           <div style="display: none;"><input type="submit" id="subImg"></div>
           </form>
           <div class="row">
               <div class="col-xs-12 personname"><span id="nikeName"></span></div>
           </div>
           <div class="row">
               <div class="col-xs-1"></div>
               <div class="col-xs-4 light"><span class="red" id="points"></span><br>积分</div>
               <div class="col-xs-1"><span class="line"></span></div>
               <div class="col-xs-4 light"><span class="red" id="expNum"></span><br>经验</div>
               <div class="col-xs-1"></div>
           </div>
       </div>
  </body>
</html>
