/**
 *description:出货单添加编辑
 *author:wangweicheng
 *date:2015/3/11
 */
define(function(require, exports, module) {
	
	var global = require("../../../driver/global");
	var Dialog = require('../../../widget/dom/dialog');
	var fenye = require('../../../widget/dom/fenye');
	var oTip = require('../../../widget/dom/tip');
	var oDetailTpl = require('../../../tpl/shop/goods/bookDetail');
	var oCheckTpl = require('../../../tpl/shop/goods/bookSubmitDetail');
	var oEditTpl = require('../../../tpl/shop/goods/bookEdit');
	var oAddTpl = require('../../../tpl/shop/goods/bookAdd');
	var oGoodsTpl = require('../../../tpl/shop/goods/bookGoods');
	var oRealTpl = require('../../../tpl/shop/goods/modifyReal');

	var BookAddEdit = R.Class.create(R.util, {

		initialize: function() {
			
			this.oWrap = $('[product-list]');
			this.amountMoney = $('[amount-money]');
			this.pkProductBook = this.parse().bid;
			this.currentApprove = null;
			this.currentReal = null;
			this.pkProductBookB = null;
			this.productprice = null;
			this.oldProduct = [];
			this.removeProduct = [];

			this.initEditBox();
			this.saveOldProduct();
			this.events();
			
		},
		saveOldProduct: function(){

			var prodocutList = $('[product-list]').find('[list]');
			var _this = this;

			prodocutList.each(function(){
				_this.oldProduct.push($(this).attr('pkproductbookB'));
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

			//填写发货数量
			$(document).on('keyup', '[book-num-input]', function(){
				var sValue = $(this).val();
				var re = /^[0-9]*[1-9][0-9]*$/g;

				if(!re.test(sValue) && sValue != ''){
					oTip.say('请填写大于0的正整数');
					$(this).val('');
				}else{
					_this.countAmount();
				}
					
			});

			//确认订货单
			$(document).on('click', '[book-confirm]', function(){
				_this.bookConfirm();
			});

			//取消订货单
			$(document).on('click', '[book-cancel]', function(){
				window.location = './bookList';
			});

			//修改实收弹窗
			$(document).on('click', '[modify-real]', function(){

				_this.currentApprove = $(this).parents('[list]').find('[data=approvenum]');
				_this.currentReal = $(this).parents('[list]').find('[data=realnum]');
				_this.pkProductBookB = $(this).parents('[list]').attr('pkProductBookB');
				_this.productprice = $(this).parents('[list]').find('[data=productprice]').html();
				var currentReal = _this.currentReal.html();
				_this.oModifyBox.show();
				$('[modify-box]').find('[realnum]').val(currentReal);

			});

			//确认实收数量
			$(document).on('click','[sc=real-confirm]', function(){
				
				var num = $('[modify-box]').find('[realnum]').val();
				_this.confirmRealModify(num);

			});

			$(document).on('keyup', '[name=realnum]', function(){
				var sValue = $(this).val();
				var re = /^\d+$/g;
			
				if(!re.test(sValue) && sValue != ''){
					oTip.say('请填写数字');
					$(this).val('');
				}else if(sValue > 99999){
					oTip.say('请填写1-99999的数字');
					$(this).val('');
				}
					
			});	

			//确认收货
			$(document).on('click', '[operation=confirm]', function(){
				_this.confirmGet();
			});

		},
		initEditBox: function() {
			//渲染弹框
			this.oModifyBox = new Dialog({
				boxTpl: oRealTpl
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

				if(data.data.list.length){
					_this.goodsWrap.show();
					_this.render(_this.goodsList, oGoodsTpl, data.data);
				}else{
					_this.goodsWrap.hide();
				}	

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

			if(this.oldProduct.indexOf(oRow.attr('pkProductBookB')) != -1){
				var removeGoods = {
					pkProductBookB: oRow.attr('pkProductBookB'),
					pkProduct: oRow.attr('gid'),
					productname: oRow.find('[goods-list-input]').val(),
					productnum: oRow.find('[book-num-input]').val(),
					productprice: oRow.find('[data=productprice]').html(),
					productunit: oRow.find('[data=unit]').html(),
					productcapacity: oRow.find('[data=capacity]').html(),
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

			if($('[book-num-input]').length){
				aGoods.each(function(){
					if(($(this).find('[book-num-input]').val() != '') && ($(this).find('[data=productprice]').html() != '')){
						count += $(this).find('[book-num-input]').val() * $(this).find('[data=productprice]').html();
					}
				});
			}else{
				aGoods.each(function(){
					if(($(this).find('[data=approvenum]').html() != '') && ($(this).find('[data=productprice]').html() != '')){
						count += $(this).find('[data=approvenum]').html() * $(this).find('[data=productprice]').html();
					}
				});
			}

			this.amountMoney.html(count);
			return count;
		},
		bookConfirm: function() {

			var _this = this;

			if($('[book-note]').val().length > 200) {
				oTip.say('备注信息不得超过200字');
				return;
			}

			this.isCheck = true;

			var param = {
				bookmoney: this.countAmount(),
				note: $('[book-note]').val()
			};

			var productsArr = [];
			var aGoods = this.oWrap.children();			

			//判断是编辑还是添加
			if(this.pkProductBook && this.parse().sc != 'rebook'){

				param.pkProductBook = this.pkProductBook;

				aGoods.each(function(){
					if(($(this).find('[book-num-input]').val() != '') && ($(this).find('[data=productprice]').html() != '')){
						var goodsItem = {
							pkProductBookB: $(this).attr('pkProductBookB'),
							pkProduct: $(this).attr('gid'),
							productname: $(this).find('[goods-list-input]').val(),
							productnum: $(this).find('[book-num-input]').val(),
							productprice: $(this).find('[data=productprice]').html(),
							productunit: $(this).find('[data=unit]').html(),
							productcapacity: $(this).find('[data=capacity]').html()
						};
						productsArr.push(goodsItem);
					}
				});
				
				this.reqUrl = R.interfaces.goods.bookEdit;

			}else{
				aGoods.each(function(){
					if(($(this).find('[book-num-input]').val() != '') && ($(this).find('[data=productprice]').html() != '')){
						var goodsItem = {
							pkProduct: $(this).attr('gid'),
							productname: $(this).find('[goods-list-input]').val(),
							productnum: $(this).find('[book-num-input]').val(),
							productprice: $(this).find('[data=productprice]').html(),
							productunit: $(this).find('[data=unit]').html(),
							productcapacity: $(this).find('[data=capacity]').html()
						};
						productsArr.push(goodsItem);
					}
				});

				this.reqUrl = R.interfaces.goods.bookAdd;
			}	

			var productsArr1 = productsArr.concat(this.removeProduct);

			if(this.pkProductBook){
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

			$('[book-num-input]').each(function() {
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
				window.location = './bookList';
			}, function(data){
				oTip.say(data.msg);
			});

		},
		confirmRealModify: function(num) {

			var _this = this;

			this.reqUrl = R.interfaces.goods.bookRealModify;
			var amountMoney = this.countAmount() - this.productprice * this.currentApprove.html() + this.productprice * num;

			this.reqParam = {
				pkProductBook: this.pkProductBook,
				pkProductBookB: this.pkProductBookB,
				bookmoney: amountMoney,
				realnum: num
			};

			this.req(function(data){
				oTip.say(data.msg);
				_this.oModifyBox.close();
				_this.currentReal.html(num);
				_this.amountMoney.html(amountMoney);
			},function(data){
				oTip.say(data.msg);
			});

		},
		confirmGet: function() {

			var _this = this;

			this.reqUrl = R.interfaces.goods.bookConfirm;
			this.reqParam = {
				pkProductBook: this.pkProductBook
			};

			this.req(function(data){
				oTip.say(data.msg);
				window.location = './bookList';
			}, function(data){
				oTip.say(data.msg);
			});

		}

	});

	var oBookAddEdit = new BookAddEdit();

});
