/**
 *description:我的推广
 *author:wangwc
 *date:2015/04/14
 */
define(function(require, exports, module){

	require("../../driver/global");
    require('../../touch/hammer');

	var Promotion = R.Class.create(R.util, {

 		initialize: function() {
            this.checkLogin();
 			this.loadData();
 			this.events();
        },
        loadData: function() {

        	var _this = this;

            this.req('/weixinqrcode/customerqrcode.php', this.param, function(data) {
         
            	var json = JSON.parse(data.data);
            	// console.log(data);
            	if(data.issuccess){
            		var oImg = $('<img src="'+json.qrcodeurl+'">');
            		$('.loading').remove();
            		$('.promotion-wrap').append(oImg);
            	}else{
            		alert(data.msg);
            	}
                
            });

        },
        events: function() {
            var _this = this;

            $('.promotion-btn').hammer().on('tap', function() {
                window.location = './integral.jsp';
            });
        }
        
 	});

	var oPromotion = new Promotion();

});