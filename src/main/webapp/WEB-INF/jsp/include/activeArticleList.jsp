<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <script>
   $(document).ready(function(){
       //ajax请求获取版块列表添加
       var queryType = $("#queryType").val();
       $.ajax({
		   url: "/base/queryActiveArticleTop3.html",
		   type: "POST",
		   dataType:"json",
		   cache:false,
		   success: function(obj){
			  if ( !checkCode( obj ) ) {
					return;
			  }
			  //获取数据 生成菜单部分
			  var html = "";
			  var data = obj.result.articles;
			  for(var o in data){
				 html = html+ "<div class='row'>";
				html = html+ "<div class='col-xs-12'><img class='activeimg' src="+data[o].picurl+"> </div>";
				html = html+ "<div class='col-xs-12'><p class='activetext'><a  href='/detail/"+data[o].id+".html' _id="+data[o].id+">"+data[o].title+"</a></p></div>";
				 html = html+ "</div>";
		      }
			  $("#activeArticle").after(html);
		   }
		});
   })
 </script>
<div class="active">
	<h3 class="top" id="activeArticle">最新活动</h3>
</div>
