/**
* 判断ajax请求返回的code值
* @param object obj ajax返回的json对象
* @return boolen true代表成功，false代表失败
* @author zhaolei
* @date 2015年8月4日
*/
function checkCode( obj ) {
	if ( obj.code != 0 ) {
		alertLayel(obj.message);
		return false;
	} else {
		return true;
	}
}
function remind(){
    $('.remindpop').show();
    setTimeout(function(){
        $('.remindpop').fadeOut(1000);
    },1000);
}
//弹窗
function alertLayel(e){
	var html='<div class="alertpop" style="display: block;position: fixed;width: 100%;height:100%;top:0;left:0;z-index: 9999;"><div class="popbg closepop" style="    width: 100%;height:100%;position: absolute;top:0;left:0;z-index: 989;background: #000;opacity: 0.5;"></div><div class="alertLayel" style="width:400px;height:200px;padding-top:10px;background:#fff;position:absolute;margin:auto;top:0;left:0;right:0;bottom:0;z-index:9999;"><h3 style="height:30px;position:relative;"><i class="closeicon" style="margin-right:12px;margin-top:0;"></i> </h3><p class="alertContent" style="line-height:30px;font-size:14px;text-align:center;height:100px;">'+e+'</p><div class="row tc"><input type="button" value="确定" class="alertBtn" style="height:30px;border:none;width:80px;background:#ff6200;color:#fff;"></div></div></div>';
	$("body").append(html);
}
$(document).ready(function() {
    //判断IE浏览器版本
//    if( $.browser.msie && ( $.browser.version == '6.0' || $.browser.version == '7.0'|| $.browser.version == '8.0') ){
//        alert("您的浏览器版本过低，请尽快升级，否则会影响网页性能和操作！");
//        return;
//    }
	$('.contenttext').each(function(){
        var L=$(this).html().length;
        if(L>182){
            $(this).addClass('overflowtext');
        }
    });
	$('.replytext').each(function(){
        var L=$(this).html().length;
        if(L>190){
            $(this).addClass('overflowtext');
        }
    });
    $('.activetext').each(function(){
        var L=$(this).html().length;
        console.log(L)
        if(L>42){
            $(this).addClass('overflowtext');
        }
    });
    $('.noticetext').each(function(){
        var L=$(this).html().length;
        if(L>82){
            $(this).addClass('overflowtext');
        }
    });
  //分享二维码生成
    $('.shareWeixinImgBox').each(function(){
		var id=$(this).parent().attr('data-id');
		url = "http://bbs.ibaixiong.com/share/detail/"+id+".html";
		console.log(url);
		var qr = qrcode(10, 'M');
		qr.addData(url);
		qr.make();
		$(this).html(qr.createImgTag());
		//设置二维码大小显示
		$(".shareWeixinImgBox img").css({width:"120px",height:"120px"});
    })

    $(document).on('click','.alertBtn,.closeicon,.closepop',function(){
    	$('.alertpop').remove();
    });
    //新浪分享
    /*$('.wb').click(function(){
		window.sharetitle = $(this).attr('data-value');
		var articleId=$(this).attr('data-id');
		url="http://bbs.ibaixiong.com/article/articleDetail/"+articleId+".html";
		window.shareUrl = url;
		console.log(window.sharetitle,articleId,window.shareUrl);
		share();
	});*/
	function share(){
		//d指的是window
		(function(s,d,e){try{}catch(e){}var f='http://v.t.sina.com.cn/share/share.php?',u=window.shareUrl,p=['url=',e(u),'&title=',e(window.sharetitle),'&appkey=2924220432','&pic=',e(window.shareUrl)].join('');function a(){if(!window.open([f,p].join(''),'mb',['toolbar=0,status=0,resizable=1,width=620,height=450,left=',(s.width-620)/2,',top=',(s.height-450)/2].join('')))u.href=[f,p].join('');};if(/Firefox/.test(navigator.userAgent)){setTimeout(a,0)}else{a()}})(screen,document,encodeURIComponent);
	}
});
