/**
 *description:商品列表
 *author:fanwei
 *date:2015/1/25
 */
define(function(require, exports, module) {
	
	var global = require("../../driver/global");
	var Dialog = require('../../widget/dom/dialog');
	var oTip = require('../../widget/dom/tip');
	var goodsListTpl = require('../../tpl/goods/goodsEdit');
	var selectSaleBoxTpl = require('../../tpl/goods/selectSaleBoxTpl');
	var ajaxForm = require('../../widget/form/ajaxForm');
	var fenye = require('../../widget/dom/fenye');
	var oTplList = require('../../tpl/goods/goodsList');
	var oTplSaleList = require('../../tpl/goods/saleList');
	var select = require('../../widget/dom/select');
	var enterDo = require('../../widget/dom/enterDo');

	var GoodsList = R.Class.create(R.util, {

		initialize: function() {
			this.oWrap = $('[list-wrap]');
			this.searchCombo();
			this.initEditBox();
			this.events();
			this.packageRemoveArr = [];
		},
		initEditBox: function() {

			//选择抵用券弹框
			this.oSelectBox = new Dialog({
				boxTpl: selectSaleBoxTpl
			});

			this.oSaleSelect = $('[select-wrap]');

		},
		events: function() {
				
			var _this = this;
			
			this.oWrap.on('click', '[get-sale]', function(){
				_this.getSale($(this));
			});

			$(document).on('click', '[search-btn]', function(){
				_this.searchCombo();
			});

			enterDo($('[goods-name]'), function(){
				_this.searchCombo();
			});

			this.oSelectBox.onConfirm = function() {
				_this.confirmSale();
			};

			this.oSaleSelect.on('click', '[sale-item]', function(){
				_this.removeItem($(this));
			});

		},
		removeItem: function(oThis) {

			//已经选择过的删除
			var selectid = oThis.attr('selectid');
			var isSelect = oThis.attr('checked');
			var param;

			if(selectid && isSelect!='checked') {
				param = this.getPackageInfo(oThis);
				param.dr = 1;
				this.packageRemoveArr.push(param);
			}

		},
		getSale: function(oThis) {

			//绑定抵用券
			var pkCombo = oThis.attr('pid');
			var _this = this;
			this.nowPkCombo = pkCombo;
			this.oSelectBox.show();

			this.reqUrl = R.interfaces.goods.getSale;
			this.reqParam = {
				pkCombo: pkCombo
			};
			this.req(function(data){

				_this.render(_this.oSaleSelect, oTplSaleList, data);

			});

		},
		getAllPackInfo: function() {

			var allItem = $('[sale-item]');
			var oItem;
			var isChecked;
			var arr = [];
			var param;
			var _this = this;

			allItem.each(function(i){

				oItem = allItem.eq(i);
				isChecked = oItem.attr('checked');

				if(isChecked == 'checked') {
					param = _this.getPackageInfo(oItem);
					arr.push(param);
				}

			});

			return arr;

		},
		getPackageInfo: function(oItem) {

			//获取单条套餐选择数据
			var saleId;
			var selectedId;
			var saleName;
			var isChecked;
			var awardvalue;
			var param;

			param = {};
			saleId = oItem.attr('nowId');
			selectedId = oItem.attr('selectId');
			saleName = oItem.attr('awardname');
			awardvalue = oItem.attr('awardvalue');
			param.pkCustomeraward = saleId;
			param.pkComboAward = selectedId;
			param.awardname = saleName;
			param.awardvalue = awardvalue;

			return param;

		},
		confirmSale: function() {

			var arr = this.getAllPackInfo();
			var _this = this;
			arr = arr.concat(this.packageRemoveArr);

			if(!arr.length) {
				oTip.say('请至少选择一张抵用券');
				return;
			}

			//确认选择
			this.reqUrl = R.interfaces.goods.confirmSale;
			this.reqParam = {
				pkCombo: this.nowPkCombo,
				awards: JSON.stringify(arr)
			};
			this.req(function(data){

				_this.oSelectBox.close();
				oTip.say(data.msg);

			}, function(data){

				oTip.say(data.msg);
			});

		},
		searchCombo: function() {

			var querylike = $('[goods-name]').val();
			var param = {
				pagesize: 10,
				querylike: querylike
			};

			if(!this.oPage){
				this.oPage = new fenye(R.interfaces.member.platPackageInfo, oTplList, param);
			}else{
				this.oPage.refresh(param, R.interfaces.member.platPackageInfo, oTplList);
			}

		}

	});

	var oGoodsList = new GoodsList();

});
