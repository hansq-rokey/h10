/**
 * Created by junfei on 2015/8/11.
 */
$(document).ready(function() {
	//分页
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
    $('.report').click(function () {
        $('.reportpop').show();
    });
    $('.close').click(function () {
        $('.popup').hide();
    });
    $('.cancel').click(function () {
        $('.popup').hide();
    });

    $('.reply').click(function () {
        $('.reportpop').show();
    });
    //�ֲ�ͼ
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
    //下拉菜单
    $('.selectinput').click(function(){
        $('.option').hide();
        $(this).find('.option').toggle();
        console.log(11)
    });
    $('.option li').click(function(e){
        var val=$(this).html();
        $(this).parent().parent('.selectinput').find('.selectvalue').text(val);
        console.log($(this).parent())
        $(this).parent().parent().find('.option').hide();
        e.stopPropagation();
    })
    //点击空白处下拉框隐藏
    $(document).bind("click",function(e){
        var target = $(e.target);
        if(target.closest(".option").length == 0&&target.closest(".selectinput").length == 0){
            $(".option").hide();
        }
    });
});