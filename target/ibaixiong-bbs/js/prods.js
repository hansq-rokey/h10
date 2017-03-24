/**
 * Created by junfei on 2015/7/23.
 */
$(document).ready(function() {
    $('.banner').unslider({
        speed: 500,               // 动画的滚动速度
        delay: 30000,              //  每个滑块的停留时间
        complete: function() {},  //  每个滑块动画完成时调用的方法
        keys: true,               //  是否支持键盘
        dots: false,               //  是否显示翻页圆点
        fluid: false              //  支持响应式设计（有可能会影响到响应式）
    });

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
    //轮播图
    $('.banner').css('height','401');
    var unslider = $('.banner').unslider();

    $('.unslider-arrow').click(function() {
        var fn = this.className.split(' ')[1];
        //  Either do unslider.data('unslider').next() or .prev() depending on the className
        unslider.data('unslider')[fn]();
    });

    $(".activetext").each(function(i){
        var divH = $(this).height();
        var $p = $("p", $(this)).eq(0);
        while ($p.outerHeight() > divH) {
            $p.text($p.text().replace(/(\s)*([a-zA-Z0-9]+|\W)(\.\.\.)?$/, "..."));
        };
    });
    //下拉框
    $('.selectinput').click(function(){
        $('.option').hide();
        $(this).find('.option').toggle();
        console.log(11)
    })
    $('.option li').click(function(e){
        var val=$(this).html();
        $(this).parent().parent('.selectinput').find('.selectvalue').text(val);
        console.log($(this).parent())
        $(this).parent().parent().find('.option').hide();
        e.stopPropagation();
    });
    //点击空白处下拉框隐藏
    $(document).bind("click",function(e){
        var target = $(e.target);
        if(target.closest(".option").length == 0&&target.closest(".selectinput").length == 0){
            $(".option").hide();
        }
    });
   /* $('.wx').mouseover(function(){
        $(this).parent().parent('.sharebox').css('width','217');
    });
    $('.wx').mouseout(function(){
        $(this).parent().parent('.sharebox').css({"width":"55","border-right":"0"});
    });*/
   
});