<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html lang="en">
<head>
	<jsp:include page="include/head.jsp"></jsp:include>
    <link href="http://fe.ibaixiong.com/bbs/css/addpost.css" rel="stylesheet" type="text/css">
    <script src="http://fe.ibaixiong.com/common/plug/adddatetime.js" type="text/javascript"></script>
    <script src="http://fe.ibaixiong.com/bbs/js/addpost.js" type="text/javascript" ></script>
    <title>发表帖子</title>
    <script type="text/javascript" charset="utf-8" src="/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/lang/zh-cn/zh-cn.js"></script>
    <style type="text/css">
        div{
            width:100%;
        }
        
    </style>
    <script type="text/javascript">
	    //实例化编辑器
	    //建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
	    var ue = UE.getEditor('editor', {
	    	//初始化工具栏菜单
	        		toolbars: [
	                   ['bold','fontsize','forecolor','backcolor','rowspacingtop','justifyleft','justifycenter','justifyright','justifyjustify','simpleupload','insertimage','insertvideo','link','unlink','attachment']
	               ]
	           });
	   function check(){
		  //添加一个检查用户昵称的问题
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
	  		   }
  		   });
		   if(b==false){
			   return false;
		   }
		   var title = $("#title").val();
		   if(title == null || title ==undefined || title == ''){
			   alertLayel("帖子标题不可为空！");
			   return false;
		   }
		   var headType = $("#headType").val();
		   if(headType == 'product'){
			   var checkFormId = $("#checkFormId").val();
			   if(checkFormId == null || checkFormId ==undefined || checkFormId == ''){
				   alertLayel("三级版块一定要设置否则无法发布！");
				   return false;
			   }
		   }else{
			   var file = $("#picUrl").val();
			   if(file == null || file == undefined || file == ''){
				   alertLayel("配图不可为空！");
				   return false;
			   }
			   if(headType == 'activity'){
				   //热门活动相关检查
				   var gradeId = $("#gradeId").val();
				   if(gradeId == null || gradeId == undefined || gradeId == ''){
					   alertLayel("等级不可为空！");
					   return false;
				   }
				   var activityStartTime = $("#activityStartTimeStr").val();
				   if(activityStartTime == null || activityStartTime == undefined || activityStartTime == ''){
					   alertLayel("开始时间不可为空！");
					   return false;
				   }
				   var activityEndTime = $("#activityEndTimeStr").val();
				   if(activityEndTime == null || activityEndTime == undefined || activityEndTime == ''){
					   alertLayel("结束时间不可为空！");
					   return false;
				   }
				   var myDate = new Date();
				   var date1 = new Date(Date.parse(activityStartTime));  
		           var date2 = new Date(Date.parse(activityEndTime)); 
		           if(myDate.getTime()>date1.getTime()){
		        	   alertLayel("开始时间不得小于当前时间。");  
		               return false;   
		           }
		           if (date1.getTime() > date2.getTime()) {  
		                alertLayel("结束时间不得小于开始时间。");  
		                return false;  
		           }  
			   }
			   var memo = $("#memo").val();
			   if(memo == null || memo ==undefined || memo == ''){
				   alertLayel("概述不可为空！");
				   return false;
			   }
		   }
		   var v = UE.getEditor('editor').hasContents();
		   if(!v){
			   alertLayel("请输入内容！");
			   return false;
		   }
		   return true;
	   }
	 //图片上传
		$(document).on('change','#file',function(){
			var typebtn=$(this);
			var imgPath = $(this).val();
			//判断是否有选择上传文件
			if (imgPath == "") {
				alertLayel("请选择上传图片！");
				return;
			}
			//判断上传文件的后缀名
			var strExtension = imgPath.substr(imgPath.lastIndexOf('.') + 1);
			if (strExtension != 'jpg' && strExtension != 'gif'
					&& strExtension != 'png' && strExtension != 'bmp'&& strExtension != 'JPG'&& strExtension != 'PNG') {
				alertLayel("请选择图片文件");
				return;
			}
			//创建FormData对象
            var data = new FormData();
            //为FormData对象添加数据
            //
            $.each($(this)[0].files, function(i, file) {
                   	data.append('file', file);
                  });
			$.ajax({
				type: "POST",
				url: "/u/article/upload.html",
				data: data,
				cache: false,
				contentType: false,    //不可缺
				processData: false,    //不可缺
				dataType:"json",
				success: function(data) {
					if(!data.success){
						alertLayel(data.message);
					}else{
						$("#picUrl").val(data.result.url);
						$('.uploadImgBox').css('background-image','url('+data.result.url+')');
						$('.promptword').css('opacity','0');
						$('.uploadImgBtn').addClass('pasted');
					}
				},
				error: function(XMLHttpRequest, textStatus, errorThrown) {
					alertLayel("上传失败，请检查网络后重试");
				}
			});
		});
	   //设置处理二级版块处理事件
	   function setForm1Val(id,name){
		   $("#form1NameSel").text(name);
		   //清空三级的下拉
		   $("#form2NameUl").find("li").remove();
		   $("#form2NameSel").text("-选择版块-");
		   $("#checkFormId").val("");
		   //请求设置选择第三级的模块
		   $.ajax({
	 		   url: "/base/queryFormList.html?formId="+id,
	 		   type: "POST",
	 		   dataType:"json",
	 		   cache:false,
	 		   success: function(obj){
	 			  if ( !checkCode( obj ) ) {
	 					return;
	 			  }
	 			  //获取数据 生成菜单部分
	 			  var html = "<i class='arrowtop'></i>";
	 			  var data = obj.result.form;
	 			  for(var o in data){
	 				 html = html+ "<li onclick='setForm2Val("+data[o].id+")' id='li"+data[o].id+"' _name='"+data[o].name+"'>"+data[o].name+"</li>";
	 			  }
	 			  $("#form2NameUl").append(html);
	 		   }
	 		});
	   }
	   //设置处理3级版块处理事件
	   function setForm2Val(id){
		   var _name = $("#li"+id).attr("_name");
		   $("#form2NameSel").text(_name);
		   $("#checkFormId").val(id);
		   $("#form2NameUl").hide();
	   }
	   function setGradeVal(id,name){
		   $("#gradeId").val(id);
		   $("#gradeSel").text(name);
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
		  		   data:{"nickName":nickNameText},
		  		   dataType:"json",
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
	   function test(){
		   var v = UE.getEditor('editor').hasContents();
		   alertLayel(v);
	   }
	   function showView(){
		   var b = check();
		   if(b){
			   var obj={};
			   obj.title=$("#title").val();
			   var headType = $("#headType").val();
			   obj.headType=headType;
			   if(headType == 'product'){
				   obj.checkFormId = $("#checkFormId").val();
			   }else{
				   obj.picUrl = $("#picUrl").val();
				   if(headType == 'activity'){
					   //热门活动相关检查
					   obj.activityGrade = $("#gradeId").val();
					   obj.activityStartTimeStr = $("#activityStartTimeStr").val();
					   obj.activityEndTimeStr = $("#activityEndTimeStr").val();
				   }
				   obj.detailMemo = $("#memo").val();
			   }
			   var v = UE.getEditor('editor').getContent();
			   obj.detailContent=v;
			   obj.id=$("#id").val();
			   $.ajax({
		            url: "/u/article/saveView.html",
		            type: "POST",
		            data:obj,
		            dataType: "json",
		            async: false,
		            success: function (data) {
		            	if(!data.success){
							alertLayel(data.message);
						}else{
							var url = "/detail/"+data.result.id+".html?isView=1";
							$('#id').val(data.result.id);
							$('#showView').attr("href",url);
							$('#showClick').click();
						}
		            }
		       });
		   }
	   }
	   $(document).ready(function(){
		   var id=$("#id").val();
		   if(id != null){
			    //说明是点击过来的修改
			    //设置图片显示
			    var picUrl=$("#picUrl").val();
			    if(picUrl!=null && picUrl != undefined && picUrl != ""){
			    	$('.uploadImgBox').css('background-image','url('+picUrl+')');
					$('.promptword').css('opacity','0');
					$('.uploadImgBtn').addClass('pasted');
			    }
		   		//设置下拉
		   		var headType = $("#headType").val();
		  		if(headType == 'product'){
			   		var form1NameSel = $("#checkForm1Name").val();
			   		$("#form1NameSel").text(form1NameSel);
			   		var form2NameSel = $("#checkForm2Name").val();
			   		$("#form2NameSel").text(form2NameSel);
		  		}
		  		//设置等级
		  		if(headType == 'activity'){
		  			var gradeId = $("#gradeId").val();
		  			var gradeName = "";
		  			if(gradeId == 1){
		  				gradeName = "一级";
		  			}
		  			if(gradeId ==2){
		  				gradeName = "二级";
		  			}
		  			if(gradeId ==3){
		  				gradeName = "三级";
		  			}
		  			if(gradeId ==4){
		  				gradeName = "四级";
		  			}
		  			if(gradeId ==5){
		  				gradeName = "五级";
		  			}
		  			$("#gradeSel").text(gradeName);
		  		}
		   }
		   
	   });
    </script>
</head>
<body>
<jsp:include page="include/top.jsp">
	<jsp:param value="${headType }" name="headType"/>
</jsp:include>
<div class="content">
	<form action="/u/article/save.html" onsubmit="return check()" method="post" enctype="multipart/form-data">  
		<input type="hidden" name="headType" value="${headType }" id="headType"/>
		<input type="hidden" name="checkFormId" value="${article.formId}" id="checkFormId"/>
		<input type="hidden" name="activityGrade" value="${article.activityGrade }" id="gradeId"/><!-- 活动等级 -->
		<input type="hidden" name="picUrl" value="${article.picUrl }" id="picUrl"/><!-- 选中图片 -->
		<input type="hidden" name="id" value="${article.id }" id="id"/><!-- 选中图片 -->
		<input type="hidden" name="uediterVal" value="" id="uediterVal"/>
		<input type="hidden" name="checkForm1Name" value="${checkForm.name}" id="checkForm1Name"/>
		<input type="hidden" name="checkForm2Name" value="${article.fromName}" id="checkForm2Name"/>
		<img src="" class="updateImg" style="visibility:hidden;">
	    <div class="row">
	    <h3 class="addtheme">发表新主题</h3></div>
	    <div class="row">
	        <div class="col-xs-12" style="padding:0;">
	            <input type="text" class="title" value="${article.title}" id="title" name="title" placeholder="标题" maxlength="50">
	            <c:if test="${headType == 'product'}">
		            <span class="selectinput plate">
		                <span class="selectvalue" id="form1NameSel">-选择版块-</span>
		                <i class="arrowright"></i>
		                <ul class="option">
		                    <i class="arrowtop"></i>
		                    <c:if test="${formList != null}">
		                    	<c:forEach items="${formList }" var="form1">
		                    		 <li onclick="setForm1Val(${form1.id},'${form1.name}')">${form1.name }</li>
		                    	</c:forEach>
		                    </c:if>
		                </ul>
		            </span>
		            <span class="selectinput classfiy">
		                <span class="selectvalue" id="form2NameSel">-选择版块-</span>
		                <i class="arrowright"></i>
		                <ul class="option" id="form2NameUl">
		                	<i class="arrowtop"></i>
		                    <c:if test="${formList2 != null}">
		                    	<c:forEach items="${formList2 }" var="form2">
		                    		 <li onclick="setForm2Val(${form2.id})" id="li${form2.id}" _name="${form2.name }">${form2.name }</li>
		                    	</c:forEach>
		                    </c:if>
		                </ul>
		            </span>
	            </c:if>
	        </div>
	    </div>
	    <c:if test="${headType != 'product'}">
		    <div class="row">
		        <!-- <span class="uploadbox">配图：<input type="button" value="选择文件" class="uploadimg"> <input type="file" name="file" id="file" /></span> -->
		    	<c:if test="${headType == 'activity'}">
			        <span class="selectinput grade">
			                <span class="selectvalue" id="gradeSel">-等级-</span>
			                <i class="arrowright"></i>
			                <ul class="option">
			                    <i class="arrowtop"></i>
			                    <c:if test="${gradeList != null}">
			                    	<c:forEach items="${gradeList }" var="grade">
			                    		 <li onclick="setGradeVal(${grade.id},'${grade.name}')">${grade.name }</li>
			                    	</c:forEach>
			                    </c:if>
			                </ul>
			            </span>
			        <input type="text" name="activityStartTimeStr" id="activityStartTimeStr" value="${article.activityStartTimeStr }" placeholder="开始时间" class="datetimepicker startdata" onclick="SelectDate(this,'yyyy-MM-dd')">
			        <span class="space"></span><span class="space"></span>至
			        <input type="text" name="activityEndTimeStr" id="activityEndTimeStr" value="${article.activityEndTimeStr }" placeholder="结束时间" class="datetimepicker enddata" onclick="SelectDate(this,'yyyy-MM-dd')">
		    	</c:if>
		    </div>
		    <div class="row">
		    	<div class="uploadImgBox">
		    		<div class="blackBg"></div>
		    		<input type="button" class="uploadImgBtn">
		    		<input style="display:inline-block;" type="file" name="file" id="file"/>
		    		<p class="promptword" style="margin-top:230px;position:relative;z-index:96;">(上传图片尺寸为<span class="red">220X154</span>像素)</p>
		    		
		    	</div>
		    </div>
		    <div class="row">
		        <textarea class="summarize"  placeholder="概述" name="summary" id="memo" maxlength="100">${article.summary }</textarea>
		    </div>
	    </c:if>
	    <div class="row">
	        <script id="editor" name="detail.content" type="text/plain" style="width:100%;height:500px;">
			${article.detail.content}
			</script>
	    </div>
	    <div class="row">
	        <div class="col-xs-12" style="padding:0;">
	        	<div style="display: none;">
	        		<a href="" id="showView" target="_blank"><span id="showClick">浏览</span></a>
	        	</div>
	            <input type="submit" class="publish" value="发表帖子">
	            <input type="button" class="publish" style="margin-right:20px;" onclick="showView()" value="浏览">
	        </div>
	    </div>
    </form>
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