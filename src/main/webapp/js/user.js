/**
 * Created by junfei on 2015/7/22.
 */
$(document).ready(function() {
    //判断IE浏览器版本
    if ($.browser.msie && ( $.browser.version == '6.0' || $.browser.version == '7.0')) {
        alert("您的浏览器版本过低，请尽快升级，否则会影响网页性能和操作！");
        return;
    }
    function page(){
        $("div.holder").jPages({
            containerID : "itemContainer",
            previous: '＜',
            next: '＞',
            perPage : 10,
            delay : 100
        });
    };
    page();
    conheight();
    //rightheight();
    //左侧导航切换
    /*$('.switch').click(function(){
        $('.selected').removeClass('selected');
        $(this).parent().addClass('selected');
        $('.secswitched').removeClass('secswitched');
        $('.current-art').removeClass('current-art');
        $('.right-box').removeClass('block');
        $(this).next('.right-box').addClass('block');
        $('#itemContainer').find('.row').css('display','block').removeAttr('id');
        $('#itemContainer').removeAttr('id');
        $(this).next('.right-box').children().find('li:first-child').find('.article').addClass('current-art');
        $(this).next('.right-box').children().find('li:first-child').find('.secswitch').addClass('secswitched');
        $(this).next('.right-box').children().find('li:first-child').find('.article').find('.itemContainer').attr('id','itemContainer');
        page();
        rightheight();
        conheight();
    });*/
 // 个人中心导航
	$contentLeft = $('.content-left'),// 左侧Dom
	$li = $contentLeft.find('li');// 导航项
	$contentLeft.append('<div class="line"></div>');// 插入标记
	$li.off('mouseover.overli mouseout.outli').on('mouseover.overli mouseout.outli', function(event){// 事件绑定
		var _this = $(this);//当前对象
		if(event.type == "mouseover"){// mouseover 事件
			var _top = _this.position().top;// 取对应top值
			//鼠标悬浮
			$contentLeft.find('.line').stop().animate({'top': _top}, 100);// 跟随滑动
		}else if(event.type == "mouseout"){// mouseout 事件
			var _top = $contentLeft.find('li.on').position().top;// 获取当前选中top值
			//鼠标离开还原
			$contentLeft.find('.line').stop().animate({'top': _top}, 200);// 离开滑动还原
		};
	}).trigger('mouseout');// 初始化
    //帖子切换
    $('.secswitch').on('click',function(){
        $('.secswitched').removeClass('secswitched');
        $('.current-art').removeClass('current-art');
        $(this).addClass('secswitched');
        $(this).next('.article').addClass('current-art');
        $('#itemContainer').find('.row').css('display','block').removeAttr('id');
        $('#itemContainer').removeAttr('id');
        $(this).next('.article').children('.itemContainer').attr('id','itemContainer');
        console.log($('#itemContainer').find('.row').length)
        page();
        rightheight();
        conheight();
    });
    $('.replytext').mouseover(function(){
        $(this).children('.col-lg-3').find('.replydel').show();
    });
    $('.replytext').mouseout(function(){
        $(this).children('.col-lg-3').find('.replydel').hide();
    });
    $('.publishtext').mouseover(function(){
        $(this).children('.col-lg-2').find('.pubdelete').show();
    });
    $('.publishtext').mouseout(function(){
        $(this).children('.col-lg-2').find('.pubdelete').hide();
    });
    //设置昵称
    $('.setnickname').click(function(){
        $(this).next('.setnamebox').show();
        $(this).hide();
    });
    $('.nicksure').click(function(){
        var nickname=$('.nickname').val();
        $('.setnickname').before('<p class="red">'+nickname+'</p>')
        $('.setnamebox').hide();
    });
    $('.nickcancel').click(function(){
        $('.setnamebox').hide();
        $('.setnickname').show();
    });
    //弹窗
    $('.pubdelete').click(function () {
        $('.popup').show();
    });
    $('.close').click(function () {
        $('.popup').hide();
    });
    $('.cansel').click(function () {
        $('.popup').hide();
    });

    $(document).on('click','.alertBtn,.closeicon',function(){
    	$('.alertpop').remove();
    });
    $(document).on('click','.closepop',function(){
    	$('.alertpop').remove();
    });
    //导航
    //var verNav = $(".leftnav"),
    //    line = verNav.siblings(".selected")
    ////verNavFisrt = verNav.children("li:first-child"),
    //    curY = verNav.find(".secswitched").position().top;
    //
    ////line.height(verNavFisrt.outerHeight()-1).width(verNavFisrt.outerWidth());//设置辅助线初始化高度和宽度，也可以直接用css设置好，这里就不用js计算了
    //
    //verNav.find("li").mouseenter(function(){
    //    var thisY = $(this).position().top;
    //    console.log(thisY)
    //    line.stop(true,true).animate({top:thisY},200);
    //    return false
    //})
    //    .end()
    //    .mouseleave(function(){
    //        line.stop(true,true).animate({top:curY},300)
    //    }).trigger("mouseleave");

});
//弹窗
function alertLayel(e){
	var html='<div class="alertpop" style="display: block;position: fixed;width: 100%;height:100%;top:0;left:0;z-index: 9999;"><div class="popbg closepop" style="    width: 100%;height:100%;position: absolute;top:0;left:0;z-index: 989;background: #000;opacity: 0.5;"></div><div class="alertLayel" style="width:400px;height:200px;padding-top:10px;background:#fff;position:absolute;margin:auto;top:0;left:0;right:0;bottom:0;z-index:9999;"><h3 style="height:30px;position:relative;"><i class="closeicon" style="margin-right:12px;margin-top:0;"></i> </h3><p class="alertContent" style="line-height:30px;font-size:14px;text-align:center;height:100px;">'+e+'</p><div class="row tc"><input type="button" value="确定" class="alertBtn" style="height:30px;border:none;width:80px;background:#ff6200;color:#fff;"></div></div></div>';
	$("body").append(html);
}
function conheight(){
    var height="";
    if($('#itemContainer')){
        height=$('#itemContainer').height();
        height=height+190;
        if(height>530){
            $('.selected').find('.right-box').css('height',height);
            $('.content').css('height',height);
        }else{
            $('.content').css('height',"");
        }
    }else{
        height=$('.selected').find('.right-box').height();
        height=height+80;
        $('.content').css('height',height);
    }
}
function rightheight(){
    var height=$('.current-art').height();
    $('.right-box').css('height',height+230)
}
