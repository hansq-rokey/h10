//去详细页
function toDetail(id){
	//设置form的action的值
	$("#articleDetail").attr("action","/article/articleDetail/"+id+".html");
	$("#detailButton").click();
}
//去发布页面
function toPublishArticle(){
	$("#publishArticle").attr("action","/u/article/toArticlePublish.html");
	$("#publishButton").click();
}
//签到
function sign(){
	$.ajax({
	   url: "/u/user/sign.html",
	   type: "get",
	   dataType:"json",
	   cache:false,
	   success: function(obj){
  			if ( !checkCode( obj ) ) {
				return;
		    }
		 	if ( obj.code == 0 ) {
				//获取登陆用户成功之后给值
		 		alertLayel("签到成功!");
			}
	   },error: function(XMLHttpRequest) {
			if(XMLHttpRequest.readyState==0&&XMLHttpRequest.responseText==""){
				alertLayel("请登录后重试");
        	}
		}
	});
}
//点赞
function parise(articleId){
	if(!$("#pariseCount"+articleId).prev().hasClass('parise-temp')){
	$.ajax({
	   url: "/u/article/pariseArticle.html?articleId="+articleId,
	   type: "get",
	   dataType:"json",
	   cache:false,
	   success: function(obj){
  			if ( !checkCode( obj ) ) {
				return;
		    }
		 	if ( obj.code == 0 ) {
				//获取登陆用户成功之后给值
				var t = $("#pariseCount"+articleId).text();
				var v = parseInt(t)+1;
				$("#pariseCount"+articleId).text(v);
				$("#pariseCount"+articleId).prev().addClass('parise-temp').css('background-position','-12px -133px').css('cursor','auto');
			}
	   },
		error: function(XMLHttpRequest) {
			if(XMLHttpRequest.readyState==0&&XMLHttpRequest.responseText==""){
				alertLayel("请登录后重试");
        	}
		}
	});
	}
}
//收藏
function collection(articleId){
	$.ajax({
	   url: "/u/article/collectionArticle.html?articleId="+articleId,
	   type: "get",
	   dataType:"json",
	   cache:false,
	   success: function(obj){
  			if ( !checkCode( obj ) ) {
				return;
		    }
		 	if ( obj.code == 0 ) {
				//获取登陆用户成功之后给值
		 		alertLayel("收藏成功!");
			}
	   },error: function(XMLHttpRequest) {
			if(XMLHttpRequest.readyState==0&&XMLHttpRequest.responseText==""){
				alertLayel("请登录后重试");
        	}
		}
	});
}
//报名
function apply(articleId){
	$.ajax({
	   url: "/u/article/applyArticle.html?articleId="+articleId,
	   type: "get",
	   dataType:"json",
	   cache:false,
	   success: function(obj){
  			if ( !checkCode( obj ) ) {
				return;
		    }
		 	if ( obj.code == 0 ) {
				//获取登陆用户成功之后给值
		 		alertLayel("报名成功!");
			}
	   },error: function(XMLHttpRequest) {
			if(XMLHttpRequest.readyState==0&&XMLHttpRequest.responseText==""){
				alertLayel("请登录后重试");
        	}
		}
	});
}