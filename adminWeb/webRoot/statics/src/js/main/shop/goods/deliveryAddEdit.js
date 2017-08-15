/**
 *description:出货单添加编辑
 *author:wangweicheng
 *date:2015/3/11
 */
define(function(require, exports, module) {
	
	var global = require("../../../driver/global");
	var oTip = require('../../../widget/dom/tip');
	var oAddTpl = require('../../../tpl/shop/goods/deliveryAdd');
	var oEditTpl = require('../../../tpl/shop/goods/deliveryEdit');
	var oDetailTpl = require('../../../tpl/shop/goods/deliveryDetail');
	var oGoodsTpl = require('../../../tpl/shop/goods/bookGoods');

	var DeliveryAddEdit = R.Class.create(R.util, {

		initialize: function() {
			
			this.oWrap = $('[product-list]');
			this.amountMoney = $('[amount-money]');
			this.pkProductDelivery = this.parse().bid;
			this.oldProduct = [];
			this.removeProduct = [];

			this.saveOldProduct();
			this.events();
			
		},
		saveOldProduct: function(){

			var prodocutList = $('[product-list]').find('[list]');
			var _this = this;

			prodocutList.each(function(){
				_this.oldProduct.push($(this).attr('pkProductDeliveryB'));
			});

		},
		events: function() {
			var _this = this;

			//查询商品
			$(document).on('keyup', '[goods-list-input]', function(){
				var sValue = $(this).val();
				var oRow = $(this).parents('[list]');
				_this.searchGoods(sValue, oRow);
			});

			//选择商品
			$(document).on('click', '[goods-item]', function(){
				var oInfo = {
					gid: $(this).attr('gid'),
					value: $(this).text(),
					unit: $(this).attr('unit'),
					capacity: $(this).attr('capacity'),
					productprice: $(this).attr('productprice') 
				};
				var oRow = $(this).parents('[list]');
				_this.selectGoods(oInfo, oRow);
				_this.countAmount();
			});

			//删除商品
			$(document).on('click', '[goods-remove]', function(){
				var oRow = $(this).parents('[list]');
				_this.goodsRemove(oRow);
				_this.countAmount();
			});

			//添加商品
			$(document).on('click', '[goods-add]', function(){
				_this.goodsAdd();
			});

			//填写商品数量
			$(document).on('keyup', '[delivery-num-input]', function(){
				var sValue = $(this).val();
				var re = /^[0-9]*[1-9][0-9]*$/g;

				if(!re.test(sValue) && sValue != ''){
					oTip.say('请填写大于0的正整数');
					$(this).val('');
				}else{
					_this.countAmount();
				}
				
			});

			//确认出库单
			$(document).on('click', '[delivery-confirm]', function(){
				_this.deliveryConfirm();
			});

		},
		searchGoods: function(sValue, oRow) {

			this.goodsWrap = oRow.find('[goods-list-wrap]');
			this.goodsList = oRow.find('[goods-list]');

			var _this = this;

			if(sValue == ''){
				this.goodsList.html('');
				this.goodsWrap.hide();
				oRow.find('[data]').html('');
				return;
			}

			this.reqUrl = R.interfaces.goods.stockList;
			this.reqParam = {content: sValue};
			this.req(function(data){

				_this.goodsWrap.show();
				_this.render(_this.goodsList, oGoodsTpl, data.data);

			},function(data){
				_this.goodsWrap.hide();
			});

		},
		selectGoods: function(oInfo, oRow) {

			var oGood = this.oWrap.find('[gid='+oInfo.gid+']');
			var oInput = oGood.find('[goods-list-input]');
			var bRepeat = false;
			
			oInput.each(function(){
				if($(this).val() == oInfo.value){
					bRepeat = true;
					return false;
				}
			});

			if(bRepeat && oRow.attr('gid') != oInfo.gid){
				oTip.say('您已经添加过该商品，请直接修改订货数量');
			}else{
				oRow.attr('gid', oInfo.gid);
				oRow.find('[goods-list-input]').val(oInfo.value);
				oRow.find('[data=unit]').html(oInfo.unit);
				oRow.find('[data=capacity]').html(oInfo.capacity);
				oRow.find('[data=productprice]').html(oInfo.productprice);
				oRow.find('[goods-list]').html('');
				oRow.find('[goods-list-wrap]').hide();
			}

		},
		goodsRemove: function(oRow) {

			if(this.oldProduct.indexOf(oRow.attr('pkProductDeliveryB')) != -1){

				var removeGoods = {
						pkProductDeliveryB: oRow.attr('pkProductDeliveryB'),
						pkProduct: oRow.attr('gid'),
						productname: oRow.find('[goods-list-input]').val(),
						productnum: oRow.find('[delivery-num-input]').val(),
						productprice: oRow.find('[data=productprice]').html(),
						productunit: oRow.find('[data=unit]').html(),
						dr: 1
					};

				this.removeProduct.push(removeGoods);
			}

			oRow.remove();
		},
		goodsAdd: function() {
			this.render(this.oWrap, oAddTpl, {}, 'append');
		},
		countAmount: function() {
			
			var aGoods = this.oWrap.children();
			var count = 0;

			if($('[delivery-num-input]').length){
				aGoods.each(function(){
					if(($(this).find('[delivery-num-input]').val() != '') && ($(this).find('[data=productprice]').html() != '')){
						count += $(this).find('[delivery-num-input]').val() * $(this).find('[data=productprice]').html();
					}
				});	
			}else{
				aGoods.each(function(){
					count += $(this).find('[data=productnum]').html() * $(this).find('[data=productprice]').html();
				});	
			}

			this.amountMoney.html(count);
			return count;
		},
		deliveryConfirm: function() {

			var _this = this;

			if($('[delivery-note]').val().length > 200) {
				oTip.say('备注信息不得超过200字');
				return;
			}
			
			this.isCheck = true;

			var param = {
				deliverymoney: this.countAmount(),
				note: $('[delivery-note]').val()
			};

			var productsArr = [];
			var aGoods = this.oWrap.children();			

			//判断是编辑还是添加
			if(this.pkProductDelivery){

				param.pkProductDelivery = this.pkProductDelivery;

				aGoods.each(function(){
					if(($(this).find('[delivery-num-input]').val() != '') && ($(this).find('[data=productprice]').html() != '')){
						var goodsItem = {
							pkProductDeliveryB: $(this).attr('pkProductDeliveryB'),
							pkProduct: $(this).attr('gid'),
							productname: $(this).find('[goods-list-input]').val(),
							productnum: $(this).find('[delivery-num-input]').val(),
							productprice: $(this).find('[data=productprice]').html(),
							productunit: $(this).find('[data=unit]').html()
						};
						productsArr.push(goodsItem);
					}
				});
				
				this.reqUrl = R.interfaces.goods.deliveryEdit;

			}else{
				aGoods.each(function(){
					if(($(this).find('[delivery-num-input]').val() != '') && ($(this).find('[data=productprice]').html() != '')){
						var goodsItem = {
							pkProduct: $(this).attr('gid'),
							productname: $(this).find('[goods-list-input]').val(),
							productnum: $(this).find('[delivery-num-input]').val(),
							productprice: $(this).find('[data=productprice]').html(),
							productunit: $(this).find('[data=unit]').html()
						};
						productsArr.push(goodsItem);
					}
				});

				this.reqUrl = R.interfaces.goods.deliveryAdd;
			}	

			var productsArr1 = productsArr.concat(this.removeProduct);

			if(this.pkProductDelivery){
				param.products = JSON.stringify(productsArr1);
			}else{
				param.products = JSON.stringify(productsArr);
			}
			
			this.reqParam = param;

			if(!productsArr.length){
				oTip.say('请从商品列表中选择商品并填写订货数量');
				return;
			}

			$('[goods-list-input]').each(function() {
				if($(this).val() == ''){
					_this.isCheck = false;
				}
			});

			$('[delivery-num-input]').each(function() {
				if($(this).val() == ''){
					_this.isCheck = false;
				}
			});

			if(!this.isCheck){
				oTip.say('商品信息不正确');
				return;
			}

			this.req(function(data){
				oTip.say(data.msg);
				window.location = './deliveryList';
			}, function(data){
				oTip.say(data.msg);
			});

		}

	});

	var oDeliveryAddEdit = new DeliveryAddEdit();

});
