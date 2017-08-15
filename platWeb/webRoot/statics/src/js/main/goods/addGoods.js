/**
 *description:新增商品
 *author:wangwc
 *date:2015/6/1
 */
define(function(require, exports, module) {
	
	var global = require("../../driver/global");
	var oTip = require('../../widget/dom/tip');

	var AddGoods = R.Class.create(R.util, {

		initialize: function() {
			this.events();
		},
		events: function() {
			
			var _this = this;

			$('[add-confirm-btn]').on('click', function() {
				_this.addConfirm();
			});

			//校验数字
			$(document).on('keyup', '[capacity],[productnum],[productprice]', function(){
				var sValue = $(this).val();
				var re = /^[0-9]*[1-9][0-9]*$/g;

				if(!re.test(sValue) && sValue != ''){
					oTip.say('请填写大于0的正整数');
					$(this).val('');
				}
					
			});

		},
		addConfirm: function() {

			this.reqUrl = R.interfaces.goods.platAddProduct;
			this.reqParam = {
				brand: $('[brand]').val(), 
				series: $('[series]').val(),
				itemno: $('[itemno]').val(), 
				productname: $('[productname]').val(), 
				unit: $('[unit]').val(),
				capacity: $('[capacity]').val(), 
				productunit: $('[productunit]').val(),
				productnum: $('[productnum]').val(),
				productprice: $('[productprice]').val()
			};

			if(this.checkGoods(this.reqParam)){
				this.req(function(data){
					oTip.say(data.msg);
					window.location = R.route['goods/stockList'].url;
				}, function(data){
					oTip.say(data.msg);
				});
			}
			
		},
		checkGoods: function(param) {

			if($.trim(param.brand) == ''){
				oTip.say('请输入品牌');
				return false;
			}

			if($.trim(param.series) == ''){
				oTip.say('请输入系列');
				return false;
			}

			/*if(!param.itemno){
				oTip.say('请输入货号');
				return false;
			}*/

			if($.trim(param.productname) == ''){
				oTip.say('请输入商品名称');
				return false;
			}

			if($.trim(param.unit) == ''){
				oTip.say('请选择容量单位');
				return false;
			}

			if($.trim(param.capacity) == ''){
				oTip.say('请输入容量');
				return false;
			}

			if($.trim(param.productunit) == ''){
				oTip.say('请选择数量单位');
				return false;
			}

			if($.trim(param.productnum) == ''){
				oTip.say('请输入数量');
				return false;
			}

			if($.trim(param.productprice) == ''){
				oTip.say('请输入标准价格');
				return false;
			}

			return true;

		}

	});

	var oAddGoods = new AddGoods();

});
