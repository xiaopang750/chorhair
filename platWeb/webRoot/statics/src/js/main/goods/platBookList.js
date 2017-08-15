/**
 *description:平台订单列表
 *author:wangweicheng
 *date:2015/3/11
 */
define(function(require, exports, module) {
	
	var global = require("../../driver/global");
	var Dialog = require('../../widget/dom/dialog');
	var fenye = require('../../widget/dom/fenye');
	var platBook = require('../../tpl/goods/platBook');
	var oTip = require('../../widget/dom/tip');
	var oTplList = require('../../tpl/goods/platBookList');
	var Calendar = require('../../widget/form/calendar');
	var Select = require('../../widget/dom/select');

	var PlatBookList = R.Class.create(R.util, {

		initialize: function() {

			this.oSearchBtn = $('[search-book-btn]');
			this.province = $('[province]');
			this.city = $('[city]');
			this.area = $('[area]');
			this.shop = $('[shop]');
			this.areaRelated();
			this.findShop();
			this.showCalendar();
			this.operatBook = null;
			this.currentRow = null;
			this.initEditBox();
			this.searchBookList();
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

			//查询历史订货单
			this.oSearchBtn.on('click', function(){
				_this.searchBookList();
				
			});

			//驳回弹窗
			$(document).on('click', '[operation=reject]', function(){
				$('[reject-textarea]').val('');
				_this.currentRow = $(this).parents('[list]');
				_this.operatBook = _this.currentRow.attr('pkproductbook');
				_this.oEditBox.show();
			});

			//确认驳回
			$(document).on('click', '[sc=confirm]', function(){
				var sText = $('[reject-textarea]').val();
				if(!sText){
					oTip.say('请填写驳回理由');
				}else{
					_this.confirmOperation('N', sText, _this.currentRow);
				}	
			});

			//确认通过
			$(document).on('click', '[operation=pass]', function(){
				_this.currentRow = $(this).parents('[list]');
				_this.operatBook = _this.currentRow.attr('pkproductbook');
				_this.confirmOperation('Y', null, _this.currentRow);
			});

			//发货
			$(document).on('click', '[operation=send]', function(){
				_this.currentRow = $(this).parents('[list]');
				_this.operatBook = _this.currentRow.attr('pkproductbook');
				_this.sendGoodsOpt(_this.currentRow);
			});

		},
		initEditBox: function() {
			//渲染弹框
			this.oEditBox = new Dialog({
				boxTpl: platBook
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
		showCalendar: function() {

			var _this = this;

			var oCalendar = new Calendar({
				ele: '[calendar]',
				format: 'yyyy-MM-dd HH:mm'
			});

		},
		confirmOperation: function(res, sText, oRow) {
			
			var _this = this;

			this.reqUrl = R.interfaces.goods.bookApprove;
			this.reqParam = {
				ispass: res,
				pkProductBook: this.operatBook
			};

			if(sText){
				this.reqParam.note = sText;
			}

			this.req(function(data){
				oTip.say(data.msg);
				_this.oEditBox.close();
				_this.searchBookList();
			}, function(data){
				oTip.say(data.msg);
			});

		},
		sendGoodsOpt: function(oRow) {
			
			var _this = this;

			this.reqUrl = R.interfaces.goods.sendGoods;
			this.reqParam = {
				pkProductBook: this.operatBook
			};

			this.req(function(data){
				oTip.say(data.msg);
				_this.searchBookList();
			}, function(data){
				oTip.say(data.msg);
			});

		},
		searchBookList: function() {

			var param = {
				pagesize: 10,
				begintime: $('[search=book-begintime]').val(),
				endtime: $('[search=book-endtime]').val(),
				province: $('[province]').children().eq($('[province]')[0].selectedIndex).attr('code'),
				city: $('[city]').children().eq($('[city]')[0].selectedIndex).attr('code'),
				county: $('[area]').children().eq($('[area]')[0].selectedIndex).attr('code'),
				pkShop: $('[shop]').children().eq($('[shop]')[0].selectedIndex).attr('pkKey'),
				vbillstatus: $('[vbillstatus]').val()
			};

			if(!this.oPage){
				this.oPage = new fenye(R.interfaces.goods.platBookList, oTplList, param);
			}else{
				this.oPage.refresh(param, R.interfaces.goods.platBookList, oTplList);
			}

		}


	});

	var oPlatBookList = new PlatBookList();

});
