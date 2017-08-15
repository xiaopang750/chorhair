/**
 *description:库存列表
 *author:wangweicheng
 *date:2015/3/11
 */
define(function(require, exports, module) {
	
	var global = require("../../driver/global");
	var Dialog = require('../../widget/dom/dialog');
	var oTip = require('../../widget/dom/tip');
	var stockEdit = require('../../tpl/goods/stockEdit');
	var addNumTpl = require('../../tpl/goods/addNum');
	var ajaxForm = require('../../widget/form/ajaxForm');
	var fenye = require('../../widget/dom/fenye');
	var oTplList = require('../../tpl/goods/stockList');
	var enterDo = require('../../widget/dom/enterDo');
	var Select = require('../../widget/dom/select');

	var StockList = R.Class.create(R.util, {

		initialize: function() {
		
			this.oSearchInput = $('[search-input]');
			this.oSearchBtn = $('[search-btn]');
			this.oWrap = $('[list-wrap]');
			
			this.searchStock();
			this.events();
			this.initEditBox();
		},
		events: function() {
			
			var _this = this;

			this.oWrap.on('click', '[modify-price]', function(){

				_this.modifyPrice($(this));

			});

			this.oWrap.on('click', '[add-num]', function(){

				_this.addNum($(this));

			});

			this.oSearchBtn.on('click', function(){

				var sValue = _this.oSearchInput.val();
				_this.searchStock(sValue);

			});

			enterDo(this.oSearchInput, function(oInput){
				var sValue = oInput.val();
				_this.searchStock(sValue);
			});

			//修改价格
			$(document).on('click', '[confirm-price]', function(){
				_this.confirmPrice();
			});

			//修改数量
			$(document).on('click', '[confirm-num]', function(){
				_this.confirmNum();
			});

			//校验数字
			$(document).on('keyup', '[now-price],[now-num]', function(){
				var sValue = $(this).val();
				var re = /^[0-9]*[1-9][0-9]*$/g;

				if(!re.test(sValue) && sValue != ''){
					oTip.say('请填写大于0的正整数');
					$(this).val('');
				}	
			});

		},
		initEditBox: function() {

			//修改标准价格
			this.oEditBox = new Dialog({
				boxTpl: stockEdit
			});

			//新增数量
			this.oAddBox = new Dialog({
				boxTpl: addNumTpl
			});

		},
		modifyPrice: function(oThis) {
			//修改价格
			this.oEditBox.show();

			var editBox = $('[edit-price-box]');

			editBox.find('[now-price]').val(oThis.parents('tr').find('[productprice]').html());
			editBox.attr('pkProduct', oThis.attr('pkProduct'));

		},
		addNum: function(oThis) {

			this.oAddBox.show();

			var editBox = $('[add-num-box]');

			editBox.find('[now-num]').val('');
			editBox.attr('pkProduct', oThis.attr('pkProduct'));

		},
		confirmPrice: function() {

			var _this = this;

			this.reqUrl = R.interfaces.goods.stockPriceEdit;
			this.reqParam = {
				pkProduct: $('[edit-price-box]').attr('pkProduct'),
				productprice: $('[now-price]').val()
			};

			this.req(function(data) {
				oTip.say(data.msg);
				_this.oEditBox.close();
				_this.searchStock();
			}, function(data) {
				oTip.say(data.msg);
			});

		},
		confirmNum: function() {

			var _this = this;

			this.reqUrl = R.interfaces.goods.addProductNum;
			this.reqParam = {
				pkProduct: $('[add-num-box]').attr('pkProduct'),
				productnum: $('[now-num]').val()
			};

			this.req(function(data) {
				oTip.say(data.msg);
				_this.oAddBox.close();
				_this.searchStock();
			}, function(data) {
				oTip.say(data.msg);
			});

		},
		
		searchStock: function(sValue) {

			var _this = this;

			var param = {
				pagesize: 10,
				productname: sValue
			};

			if(!this.oPage){
				this.oPage = new fenye(R.interfaces.goods.platStockList, oTplList, param);
			}else{
				this.oPage.refresh(param, R.interfaces.goods.platStockList, oTplList);
			}

		}

	});

	var oStockList = new StockList();

});
