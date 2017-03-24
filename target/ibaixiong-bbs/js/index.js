/**
 * Created by junfei on 2015/9/1.
 */
$(document).ready(function(){
    var n;
    function slider(){
        //var index=$('.icon-circle').length;
        $('.icon-circle-thin').removeClass('icon-circle-thin');
        $('.icon-circle').eq(n).addClass('icon-circle-thin');
        $('.curslider').fadeOut(800).removeClass('curslider');
        $('.slider').eq(n).fadeIn(800).addClass('curslider');
        themecolor();
    }
    $('.bullet li').click(function(){
        n=$(this).index();
        slider();
    });
    var width=$(window).width();
    $('.prodbox').css('width',width);

    //鼠标移入产品导航
    $('.prodhover').mouseover(function(){
        $('header').addClass('white-bg');
        if($(this).find('a').hasClass('blacktheme')){
            $('.indexlogo').show();
            $('.white-logo').hide();
        }
    }).mouseout(function(){
        $('.white-bg').removeClass('white-bg')
        if($(this).find('a').hasClass('blacktheme')){
            $('.indexlogo').hide();
            $('.white-logo').show();
        }
    })
    $('.prodliatbox').mouseover(function(){
        $(this).css({opacity:'1'}).siblings().css({opacity:'0.3'});
    })
    //$('.prodbox').mouseover(function(){
    //    $('header').animate({height:'222px'},500).mouseout(function(){
    //        $('header').animate({height:'100'},500);
    //    });
    //})
    //导航文字颜色
    function themecolor(){
        if($('.curslider').hasClass('black')){
            $('.headnav>li>a,.line,.icon-circle').addClass('blacktheme');
            $('.indexlogo').hide();
            $('.white-logo').show();
        }else{
            $('.blacktheme').removeClass('blacktheme');
            $('.indexlogo').show();
            $('.white-logo').hide();
        }
    }
    //自动轮播
    $.autoscroll=function (){
        var index=$('.icon-circle').length;
        var curindex=$('.icon-circle-thin').index();
        if(curindex==index-1){
            curindex=0;
        }else{
            curindex=curindex+1;
        }
        $('.icon-circle-thin').removeClass('icon-circle-thin');
        $('.icon-circle').eq(curindex).addClass('icon-circle-thin');
        $('.curslider').fadeOut(800).removeClass('curslider');
        $('.slider').eq(curindex).fadeIn(800).addClass('curslider');
        themecolor();
    };
    setInterval(function(){
        $.autoscroll()
    },5000);
    //视频
    $('.index_img ').on('click',function(){
        alert("ss");
    	$(this).next('.popvideobox').show().find('.videobox').animate({top:'50%'},500);
    })
    
    $('.videoState').on('click',function(){
    	$(this).prev('.popvideobox').show().find('.videobox').animate({top:'50%'},500);
    })
    $(".videoimg").hover(function(){
        $(this).siblings('.videoState').css('background-image','url("http://fe.ibaixiong.com/shop/images/video_on.png")');
        },function(){
        	$(this).siblings('.videoState').css('background-image','url("http://fe.ibaixiong.com/shop/images/video_off.png")');
      });
    $('.videoState').hover(function(){
    	$(this).css('background-image','url("http://fe.ibaixiong.com/shop/images/video_on.png")');
    },function(){
    	$(this).css('background-image','url("http://fe.ibaixiong.com/shop/images/video_off.png")');
    })
    //$('.closevideo').on('click',function(){
    //    $(this).parent('.videobox').animate({'top':'-220'},1000).parent('.popvideobox').css({'width':0,"height":0});
    //    //$(this).parent('.videobox').parent('.popvideobox').hide();
    //})
    //关闭弹窗
    $('.closevideo').on('click',function(){
        $(this).parent('.videobox').animate({top:'-50%'},500).parent('.popvideobox').hide();
    });
    $('.feedback').on('click',function(){
        $('.pop').show();
    })
    $('.closeicon').on('click',function(){
        $(this).parent().parent().parent().hide();
    });
    //弹窗
    $(document).on('click','.alertBtn,.closeicon',function(){
    	$('.alertpop').remove();
    });
});
function alertLayel(e){
	var html='<div class="alertpop" style="display: block;position: fixed;width: 100%;height:100%;top:0;left:0;z-index: 9999;"><div class="popbg" style="    width: 100%;height:100%;position: absolute;top:0;left:0;z-index: 989;background: #000;opacity: 0.5;"></div><div class="alertLayel" style="width:400px;height:200px;padding-top:10px;background:#fff;position:absolute;margin:auto;top:0;left:0;right:0;bottom:0;z-index:9999;"><h3 style="height:30px;position:relative;"><i class="closeicon" style="margin-right:12px;margin-top:0;"></i> </h3><p class="alertContent" style="line-height:30px;font-size:14px;text-align:center;height:100px;">'+e+'</p><div class="row tc"><input type="button" value="确定" class="alertBtn" style="height:30px;border:none;width:80px;background:#ff6200;color:#fff;"></div></div></div>';
	$("body").append(html);
}