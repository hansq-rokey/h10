//登录注册切换
$(document).ready(function(){
    //判断IE浏览器版本
    if( $.browser.msie && ( $.browser.version == '6.0' || $.browser.version == '7.0') ){
        alert("您的浏览器版本过低，请尽快升级，否则会影响网页性能和操作！");
        return;
    }

    function validate(data) {
        if (!data.name) {
            $('.username').next('.msg').show();
            return false;
        }
        if (!data.yzm) {
            $('.yzm-img').next('.msg').show();
            return false;
        }
        if (!data.password) {
            $('.password').next('.msg').show();
            return false;
        }

    }
    $('.form-control').focus(function(){
        $(this).nextAll('.msg').hide();
    });
    //注册
    $('.reset-btn').click(function(){
        var data = {};
        data.name = $('.username').val();
        data.password = $('.password').val();
        data.yzm = $('.yzm-input').val();
        console.log(data)
        validate(data);
    });
    //倒计时
    $('.yzm-btn').click(function () {
        var count = 90;
        var countdown = setInterval(CountDown, 1000);
        function CountDown() {
            $(".yzm-btn").attr("disabled", true);
            $(".yzm-btn").val(  count + " 秒");
            if (count == 0) {
                $(".yzm-btn").val("重新获取").removeAttr("disabled");
                clearInterval(countdown);
            }
            count--;
        }
    })
});
