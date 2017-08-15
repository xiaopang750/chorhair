/**
 *description:订单列表
 *author:wangwc
 *date:2015/04/10
 */
 define(function(require, exports, module){

 	require("../../driver/global");
    require('../../touch/hammer');

 	var Appoint = R.Class.create(R.util, {
 		initialize: function() {
            this.checkLogin();
            this.orderstatus = '001';
 			this.loadData(this.orderstatus);
        	this.events();
        },
        loadData: function(orderstatus){

            var _this = this;
            var param = {
                orderstatus: orderstatus
            };

            this.req('/order/findbystatus.php', param, function(data) {

                var oWrap = $('.appoint-list');
                
                if(data.data != ''){
                    var appointArr = JSON.parse(data.data);
                    var jsonData = {
                        appointArr: appointArr
                    };  
                    var tpl = require("../../tpl/appoint/list");
                    
                    _this.render(oWrap, tpl, jsonData);
                    $('[appoint-wrap]').find('.no-result').remove();

                }else{
                    oWrap.html('');

                    if(_this.orderstatus == '001'){
                        var oNone = $('<p class="no-result">您当前没有服务单</p>');
                    }else{
                        var oNone = $('<p class="no-result">暂无订单</p>');
                    }
                    
                    if(!$('[appoint-wrap]').find('.no-result').length){
                        $('[appoint-wrap]').append(oNone);
                    }
                    
                }
            });
            
        },
        events: function() {
        	var _this = this;

            //切换服务状态
            $('.appoint-tab-item').hammer().on('tap',function() {

                $(this).addClass('active').siblings().removeClass('active');

                if($(this).attr('orderstatus') == 'servering'){
                    _this.orderstatus = '001';
                }else if($(this).attr('orderstatus') == 'servered'){
                    _this.orderstatus = '002';
                }else if($(this).attr('orderstatus') == 'appointment'){
                    _this.orderstatus = '003';
                }

                _this.loadData(_this.orderstatus);

            });

            //查看订单详情
           /* $('[appoint-list]').hammer().on('tap', 'li', function() {
                var aid = $(this).attr('aid');
                window.location = './detail.jsp?aid='+aid+'';
            });*/
        },
       
 	});

 	var oAppoint = new Appoint();

 });