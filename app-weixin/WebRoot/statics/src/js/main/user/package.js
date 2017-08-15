/**
 *description:我的套餐
 *author:wangwc
 *date:2015/04/13
 */
define(function(require, exports, module){

	require("../../driver/global");
    require('../../touch/hammer');

	var Package = R.Class.create(R.util, {

 		initialize: function() {
            this.checkLogin();
            this.param = {};
        	this.loadData(this.param);
 			this.events();
        },
        loadData: function(param) {

            var _this = this;

            this.req('/customercombo/querybyuser.php', param, function(data) {
                var oWrap = $('.package-list');

                if(data.data != ''){
                    var packageArr = JSON.parse(data.data);
                    var jsonData = {
                        packageArr: packageArr
                    };  
                    var tpl = require("../../tpl/user/package");
                    
                    _this.render(oWrap, tpl, jsonData);
                    $('[package-wrap]').find('.no-result').remove();
                    
                }else{
                    oWrap.html('');

                    if(!param){
                        var oNone = $('<p class="no-result">暂无办理套餐</p>');
                    }else{
                        var oNone = $('<p class="no-result">暂无办理该类型套餐</p>');
                    }

                    if(!$('[package-wrap]').find('.no-result').length){
                        $('[package-wrap]').append(oNone);
                    }
                    
                }
            });

        },
        events: function() {
            var _this = this;

            //切换套餐类型
            $('.package-tab-item').hammer().on('tap',function(){

                $(this).addClass('active').siblings().removeClass('active');

                 _this.param.fairtype = $(this).attr('package-type');

                _this.loadData( _this.param);

            });
        }
        
 	});

	var oPackage = new Package();

});