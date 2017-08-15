/**
 *description:店铺详情
 *author:wangwc
 *date:2015/04/02
 */
 define(function(require, exports, module){

 	require("../../driver/global");

 	var Shop = R.Class.create(R.util, {
 		initialize: function() {
            this.setHeight();
 			this.loadData();
        	this.events();
        },
        setHeight: function() {
            
            $('.shop-head').height($('.shop-bg').width());

        },
        loadData: function() {
        	var _this = this;
        	var param = {
                pagesize: '10', 
                curpage: '1', 
                contenttype: 'shop', 
            };

            this.req('/content/querybykey.php', param, function(data) {

                if(data.data != ''){
                    var shopArr = JSON.parse(data.data);
                    var jsonData = {
                        shopArr: shopArr,
                        pkUser: localStorage.pkUser
                    };

                    var oWrap = $('.shop-list');
                    var tpl = require("../../tpl/shop/list");

                    _this.render(oWrap, tpl, jsonData, 'append');

                    //设置商品列表项高度
                    var shopW = $('.shop-list .img').width();
                    $('.shop-list li').height(shopW);
                }   

            });
        },
        events: function() {
        	
        },
       
 	});

 	var oShop = new Shop();

 });