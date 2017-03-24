<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String queryType = request.getParameter("queryType");
%>
  <script>
    $(document).ready(function(){
        //ajax请求获取版块列表添加
        var queryType = $("#queryType").val();
        $.ajax({
 		   url: "/base/queryActiveUser.html?tag="+queryType,
 		   type: "POST",
 		   dataType:"json",
 		   cache:false,
 		   success: function(obj){
 			  if ( !checkCode( obj ) ) {
 					return;
 			  }
 			  //获取数据 生成菜单部分
 			  var html = "";
 			  var data = obj.result.users;
 			  var i=0;
 			  var className="";
 			  for(var o in data){
 				  if(i==0){
 					 className = "red link";
 					 
 				  }
 				 if(i==1||i==2){
 					 className = "blue";
 				  }
 				 if(i>2){
 					className = ""; 
 				 }
 				 html = html+ "<div class='row'>";
 				html = html+ "<div class='col-xs-3'><img class='userheadActive' src="+data[o].headImg+"> </div>";
 				html = html+ "<div class='col-xs-9 smallsize'><span class='space'></span><a class='"+className+"' href='/user/index/"+data[o].id+"/"+queryType+".html' _id="+data[o].id+">"+data[o].nickName+"</a><img class='rank' src="+data[o].gradeImg+"></div>";
 				 html = html+ "</div>";
 				 i = i+1;
 		      }
 			  $("#activeUser").after(html);
 		   }
 		});
    })
  </script>
  	<!-- 用来控制查询类型的 -->
   	<input type="hidden" id="queryType" value="<%=queryType%>"/>
	<div class="member">
        <h3 class="top" id="activeUser">活跃成员</h3>
    </div>
