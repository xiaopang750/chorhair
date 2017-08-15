/**
 *description:店铺库存列表
 *author:wangweicheng
 *date:2015/3/11
 */
define(function(require, exports, module) {
	
	var global = require("../../driver/global");
	var oTip = require('../../widget/dom/tip');
	var fenye = require('../../widget/dom/fenye');
	var oTplList = require('../../tpl/goods/shopStockList');
	var enterDo = require('../../widget/dom/enterDo');
	var Select = require('../../widget/dom/select');

	var StockList = R.Class.create(R.util, {

		initialize: function() {

			this.oSearchInput = $('[search-input]');
			this.oSearchBtn = $('[search-btn]');
			this.oWrap = $('[list-wrap]');
			this.province = $('[province]');
			this.city = $('[city]');
			this.area = $('[area]');
			this.shop = $('[shop]');
			this.pkShop = null; 

			this.areaRelated();
			this.findShop();
			this.events();
		},
		events: function() {
			
			var _this = this;

			//查询店铺
			$(document).on('change', '[area-wrap]', function() {
				_this.areaParam = {
					province: _this.province.children().eq(_this.province[0].selectedIndex).attr('code'),
					city: _this.city.children().eq(_this.city[0].selectedIndex).attr('code'),
					county: _this.area.children().eq(_this.area[0].selectedIndex).attr('code'),
				};		
				_this.findShop(_this.areaParam);
			});

			//查询店铺库存
			this.oSearchBtn.on('click', function(){

				var sValue = _this.oSearchInput.val();
				_this.searchStock(sValue);

			});

			enterDo(this.oSearchInput, function(oInput){
				var sValue = oInput.val();
				_this.searchStock(sValue);
			});

		},
		initEditBox: function() {

			//渲染弹框
			this.oEditBox = new Dialog({
				boxTpl: stockEdit
			});
		},
		areaRelated: function() {

			//区域联动
			var _this = this;

			this.oProvince = new Select({
				ele: this.province,
				url: R.interfaces.global.getArea,
				param: {belonglevel:1},
				tpl: 
				'{{each data}}'+
					'<option code="{{$value.pkArea}}" value="{{$value.areaname}}" id="{{$value.areaname}}">'+
						'{{$value.areaname}}'+
					'</option>'+
				'{{/each}}',
				onChange: function(oSelect, oOption, nowIndex) {

					var nowCode = oOption.attr('code');

					if(nowCode) {

						_this.reqUrl = R.interfaces.global.getArea;
						_this.reqParam = {
							pkFather: nowCode
						};
						_this.req(function(data){

							_this.oCity.clear();
							_this.oCity.render(data);

						});

					} else {

						_this.oCity.clear();
						_this.oArea.clear();

					}

				},
				onReady: function() {
					_this.areaCb && _this.areaCb();
				}	
			});

			this.oCity = new Select({
				ele: this.city,
				tpl: 
				'{{each data}}'+
					'<option code="{{$value.pkArea}}" value="{{$value.areaname}}" id="{{$value.areaname}}">'+
						'{{$value.areaname}}'+
					'</option>'+
				'{{/each}}',
				onChange: function(oSelect, oOption, nowIndex) {
					
					var nowCode = oOption.attr('code');

					if(nowCode) {
						
						_this.reqUrl = R.interfaces.global.getArea;
						_this.reqParam = {
							pkFather: nowCode
						};
						_this.req(function(data){

							_this.oArea.clear();
							_this.oArea.render(data);

						});

					} else {

						_this.oArea.clear();

					}

				}
			});

			this.oArea = new Select({
				ele: this.area,
				tpl: 
				'{{each data}}'+
					'<option code="{{$value.pkArea}}" value="{{$value.areaname}}" id="{{$value.areaname}}">'+
						'{{$value.areaname}}'+
					'</option>'+
				'{{/each}}'
			});

		},
		findShop: function(param) {

			var _this = this;

			_this.shop.html('<option>请选择店铺</option>');

			this.reqUrl = R.interfaces.shop.shopList;
			this.reqParam = param || {};
			this.req(function(data) {
				var shopList = data.data.list;

				for(var i=0;i<shopList.length;i++){
					var option = $('<option pkKey="'+shopList[i].pkShop+'">'+shopList[i].shopname+'</option>');
					_this.shop.append(option);
				}
			});
		}, 
		searchStock: function(sValue) {

			var _this = this;

			var pkShop = $('[shop]').children().eq($('[shop]')[0].selectedIndex).attr('pkKey');

			if(!pkShop){
				oTip.say('请选择店铺');
				return;
			}

			var param = {
				pagesize: 10,
				productname: sValue,
				pkShop: pkShop
			};

			if(!this.oPage){
				this.oPage = new fenye(R.interfaces.goods.shopStockList, oTplList, param);
			}else{
				this.oPage.refresh(param, R.interfaces.goods.shopStockList, oTplList);
			}

		}

	});

	var oStockList = new StockList();

});
