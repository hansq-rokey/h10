<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<script>
  $(document).ready(function(){
	  //这个方法显得有点多余，暂时这样处理检查是否用户登录了
	  $.ajax({
 		   url: "/u/user/getUserInfo.html",
 		   type: "POST",
 		   dataType:"json",
 		   cache:false,
 		   success: function(obj){
			 	if ( obj.code == 0 ) {
					//获取登陆用户成功之后给值
			 		$("#signDiv").show();
			 		$("#publishDiv").show();
				}
 		   }
 	 }); 
  })
</script>
<div id="signDiv" style="display: none;">
<input type="button" value="" class="sign" onclick="sign()">
</div>