/**
 *description:首页
 *author:wangwc
 *date:2015/04/02
 */
 define(function(require, exports, module){

    require("../../driver/global");
    require("../../touch/hammer");

 	var Index = R.Class.create(R.util,{
 		initialize: function() {
            this.windowH = $(window).height();
            this.menu = $('.user-info');
        
            this.userInfo();
            this.setHeight();

            if(sessionStorage.firstLoad == undefined){
                sessionStorage.firstLoad = true;
                this.animation();
            }else{
                sessionStorage.firstLoad = false;
                this.setPos();
            }

        	this.events();
        },
        userInfo: function() {

            this.req('/weixin/userinfo.php', this.param, function(data) {

                var json = JSON.parse(data.data);

                $('[user-head-img]').attr('src', json.headimageurl);
                $('[user-nickname]').html(json.nickname);
            
                if(json.addr != ''){
                    $('[user-addr]').html(json.addr);
                }else{
                    $('[addr-wrap]').hide();
                }

                if(json.cellphone){
                    sessionStorage.userPhone = json.cellphone;
                    $('[phone-wrap]').show();
                    $('[phone-num]').html(sessionStorage.userPhone);
                }else{
                    $('[phone-wrap]').hide();
                }
                
            });

        },
        setHeight: function() {
            $('body.home').height(this.windowH);
            $('.home-bg').height(this.windowH);
            $('.shadow').height(this.windowH);
            $('.user-info .img').height($('.user-info .img').width() - 10);
            this.menu.height(this.windowH);
        },
        setPos: function() {
            $('[animation]').addClass('old');
        },  
        animation: function() {

            setTimeout(function() {
                $('.shadow').css('-webkit-transform', 'translateX(0) translateY(0)');
            },100);

            setTimeout(function() {
                $('.text1').css('-webkit-transform', 'translateX(0) rotate(0)');
            },300);
            
            setTimeout(function() {
                $('.text2').css('-webkit-transform', 'translateX(0) rotate(0)');
            },650);

            setTimeout(function() {
                $('.line1').css('-webkit-transform', 'translateX(0)');
                $('.line2').css('-webkit-transform', 'translateX(0)');
            },1200);

            setTimeout(function() {
                $('.text3').css('opacity', '1');
                $('.text4').css('opacity', '1');
            },1700);

            setTimeout(function() {
                $('.btn-single').css('-webkit-transform', 'translateY(0)');
            },1900);

            setTimeout(function() {
                $('.btn-fullyear').css('-webkit-transform', 'translateY(0)');
            },2100);

        },
        events: function() {
           
            $('.btn-single').on('click', function() {
                window.location = '../goods/index.jsp?type=2';
            });

            $('.btn-fullyear').on('click', function() {
                window.location = '../goods/index.jsp?type=1';
            });

            $('.icons-user').on('click', function() {
                $('body').css('-webkit-transform', 'translateX(70%)');
                return false;
            });

            $('.user-info').hammer().on('tap', function() {
                return false;
            });

            $('body').hammer().on('tap', function() {
                $('body').css('-webkit-transform', 'translateX(0)');
            });

           $('.user-info .appoint').on('click', function() {
                if(!R.pkUser){
                    window.location = '../user/login.jsp';
                }else{
                    window.location = '../appoint/index.jsp';
                }
            });

            $('.user-info .package').on('click', function() {
                if(!R.pkUser){
                    window.location = '../user/login.jsp';
                }else{
                     window.location = '../user/package.jsp';
                }
            });

            $('.user-info .popular').on('click', function() {
                if(!R.pkUser){
                    window.location = '../user/login.jsp';
                }else{
                    window.location = '../user/promotion.jsp';
                }
            });

        }
       
 	});

 	var oIndex = new Index();

 });