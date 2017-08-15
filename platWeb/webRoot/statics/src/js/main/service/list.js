/**
 *description:服务管理列表
 *author:fanwei
 *date:2015/1/25
 */
define(function(require, exports, module) {
	
	var global = require("../../driver/global");
	var Dialog = require("../../widget/dom/dialog");
	var oTip = require("../../widget/dom/tip");
	var fenye = require('../../widget/dom/fenye');
	var oTplList = require('../../tpl/service/fairList');
	var Select = require('../../widget/dom/select');

	var ServiceList = R.Class.create(R.util, {

		initialize: function() {
			
			this.oWrap = $('[list-wrap]');
			this.province = $('[province]');
			this.city = $('[city]');
			this.area = $('[area]');
			this.shop = $('[shop]');

			this.areaRelated();
			this.findShop();
			this.searchFairer();
			this.initRemoveBox();
			this.events();

		},
		searchFairer: function() {

			var param = {
				pagesize: 10,
				province: $('[province]').children().eq($('[province]')[0].selectedIndex).attr('code'),
				city: $('[city]').children().eq($('[city]')[0].selectedIndex).attr('code'),
				county: $('[area]').children().eq($('[area]')[0].selectedIndex).attr('code'),
				pkShop: $('[shop]').children().eq($('[shop]')[0].selectedIndex).attr('pkKey'),
				querylike: $('[fairer-name]').val()
			};

			if(!this.oPage){
				this.oPage = new fenye(R.interfaces.service.list, oTplList, param);
			}else{
				this.oPage.refresh(param, R.interfaces.service.list, oTplList);
			}

		},
		initRemoveBox: function() {

			this.oRemovBox = new Dialog({
				content: '确认删除么?'
			});
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

			this.oWrap.on('click', '[remove]', function(){

				_this.removeId = $(this).attr('cid');
				_this.oRemovBox.show();

			});

			this.oRemovBox.onConfirm = function() {

				_this.reqUrl = R.interfaces.content.remove;
				_this.reqParam = {cid: _this.removeId};
				_this.req(function(data){
					oTip.say(data.msg);
					this.close();
				}, function(data){
					oTip.say(data.msg);
				});

			};

			this.oWrap.on('click', '[reguid]', function(){
				
				var uid = $(this).attr('reguid');
				_this.registApp(uid, $(this));

			});

			$(document).on('click', '[search-fairer-btn]', function(){
				_this.searchFairer();
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
		registApp: function(uid, oThis) {

			var _this = this;

			this.reqUrl = R.interfaces.service.regist;
			this.reqParam = {
				pkFairer: uid
			}
			this.req(function(data){

				_this.registSuc(oThis);
				oTip.say(data.msg);

			}, function(data){

				oTip.say(data.msg);

			});

		},
		registSuc: function(oBtn) {

			oBtn.hide();
			var oSpan = $('<span>已注册app</span>');
			oBtn.after(oSpan);

		}

	});

	var oServiceList = new ServiceList();

});
