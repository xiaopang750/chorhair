/**
 *description:我的积分
 *author:wangwc
 *date:2015/04/21
 */
define(function(require, exports, module){

	require("../../driver/global");

	var Integral = R.Class.create(R.util, {

 		initialize: function() {
            this.checkLogin();

            this.setHeight();
            this.userInfo();
 			this.load();
        },
        setHeight: function() {
            $('.user-img').height($('.user-img').width() - 10);
        },
        userInfo: function() {

            this.req('/weixin/userinfo.php', this.param, function(data) {

                var json = JSON.parse(data.data);

                $('[user-head-img]').attr('src', json.headimageurl);
                $('[user-nickname]').html(json.nickname);

            });

        },
        load: function() {

        	this.req('/weixinqrcode/querycodescan.php', this.param, function(data) {

                if(data.data){
                    var json = JSON.parse(data.data);

                    $('.integral-num .value').html(json.count);
                    $('.promotion-num .value').html(json.count);
                }
        	
        	});
        }
        
 	});

	var oIntegral = new Integral();

});