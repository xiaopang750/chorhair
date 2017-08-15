/**
 *description:库存列表
 *author:wangweicheng
 *date:2015/3/11
 */
define(function(require, exports, module) {
	
	var global = require("../../../driver/global");
	var oTip = require('../../../widget/dom/tip');
	var fenye = require('../../../widget/dom/fenye');
	var oTplList = require('../../../tpl/shop/goods/stockList');
	var enterDo = require('../../../widget/dom/enterDo');

	var StockList = R.Class.create(R.util, {

		initialize: function() {
			this.oSearchInput = $('[search-input]');
			this.oSearchBtn = $('[search-btn]');
			this.oWrap = $('[list-wrap]');
			this.searchStock();
			this.events();
		},
		events: function() {
			
			var _this = this;

			this.oSearchBtn.on('click', function(){

				var sValue = _this.oSearchInput.val();
				_this.searchStock(sValue);

			});

			enterDo(this.oSearchInput, function(oInput){
				var sValue = oInput.val();
				_this.searchStock(sValue);
			});

		},
		searchStock: function(sValue) {

			var _this = this;

			var param = {
				pagesize: 10,
				content: sValue
			};

			if(!this.oPage){
				this.oPage = new fenye(R.interfaces.goods.stockList, oTplList, param);
			}else{
				this.oPage.refresh(param, R.interfaces.goods.stockList, oTplList);
			}

		}

	});

	var oStockList = new StockList();

});
