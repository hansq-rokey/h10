/**
 * Created by junfei on 2015/7/26.
 */
$(document).ready(function(){
    $('.title').bind('input', function() {
        var length = $(this).val().length;
        var sur=60;
        sur=sur-length;
    });
    $(".summarize").keyup(function(){
        var len = $(this).val().length;
        if(len > 139){
            $(this).val($(this).val().substring(0,140));
        }
        var num = 140 - len;
        $(".number").text(num);
    });
    $('.selectinput').click(function(){
        $('.option').hide();
        $(this).find('.option').toggle();
        console.log($(option).css('display')=='block')
    });
    //����հ״�����������
    $(document).bind("click",function(e){
        var target = $(e.target);
        if(target.closest(".option").length == 0&&target.closest(".selectinput").length == 0){
            $(".option").hide();
        }
    });
    $(document).on('click','.option li',function(e){
        var val=$(this).html();
        $(this).parent().parent('.selectinput').find('.selectvalue').text(val);
        console.log($(this).parent())
        $(this).parent().parent().find('.option').hide();
        e.stopPropagation();
    });
    $('.close').click(function () {
        $('.popup').hide();
    });
    $('.cansel').click(function () {
        $('.popup').hide();
    });
})