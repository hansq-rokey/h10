//登录注册切换
$(document).ready(function(){
    //判断IE浏览器版本
    if( $.browser.msie && ( $.browser.version == '6.0' || $.browser.version == '7.0') ){
        alert("您的浏览器版本过低，请尽快升级，否则会影响网页性能和操作！");
        return;
    }
    //窗口高度
    var height=$(window).height();
    mheight=height-345;
    $('.login-content').css('min-height',mheight);

    $('.switch').click(function(){
        $('.switch').removeClass('selected');
        $(this).addClass('selected')
        $('.login-infor').removeClass('current');
        $(this).next().addClass('current');
        $(this).next('.login-infor').find('.msg').hide();
    });

    function validate(data) {
        if (!data.name) {
            $('.username').next('.msg').show();
            return false;
        }
        if(!data.name.match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)&&!data.name.match(/^(((13[0-9]{1})|159|153)+\d{8})$/)){
            $('.zc-name').next('.msg').html("<font color='red'>用户名格式不正确！请重新输入！</font>").show();
            return false;
        }
        if (!data.password) {
            $('.password').next('.msg').show();
            return false;
        }
        if (data.password.length<6||data.password.length>16) {
            $('.password').next('.msg').html("<font color='red'>密码长度不对！请重新输入！</font>").show();
            return false;
        }
        if (!data.yzm) {
            $('.yzm-img').next('.msg').show();
            return false;
        }
    }
    //登录
    $('.login-btn').click(function(){
        var data = {};
        data.name = $('.dl-name').val();
        data.password = $('.dl-pass').val();
        data.yzm = $('.dl-yzm').val();
        validate(data);
    });
    $('.form-control').focus(function(){
        $(this).nextAll('.msg').hide();
    })

    //注册
    $('.register-btn').click(function(){
        var data = {};
        data.name = $('.zc-name').val();
        data.password = $('.zc-pass').val();
        data.yzm = $('.zc-yzm').val();
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
})
