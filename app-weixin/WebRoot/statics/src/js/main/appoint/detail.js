/**
 *description:订单详情
 *author:wangwc
 *date:2015/05/5
 */
 define(function(require, exports, module){

 	require("../../driver/global");

 	var AppointDetail = R.Class.create(R.util, {
 		initialize: function() {
 			this.loadData();
        	this.events();
        },
        loadData: function(){

            var _this = this;
            var aid = this.parse().aid;

            var param = {
                pkOrder: aid
            };

            this.req('/order/querybyorder.php', param, function(data) {

                console.log(data);

                if(data.data != ''){
                  
                }else{
                    
                }
            });
            
        },
        events: function() {
        	var _this = this;

            
        },
       
 	});

 	var oAppointDetail = new AppointDetail();

 });