/**
 *description:用户管理
 *author:fanwei
 *date:2015/1/25
 */
define(function(require, exports, module) {
	
	var global = require("../../driver/global");
	var oTip = require('../../widget/dom/tip');
	var bodyParse = require('../../util/http/bodyParse');
	var fenye = require('../../widget/dom/fenye');
	var oTplList = require('../../tpl/member/list');
	var Select = require('../../widget/dom/select');

	var Manage = R.Class.create(R.util, {

		initialize: function() {
			
			this.pageInfo = bodyParse();
			this.oWrap = $('[content-wrap]');

			this.province = $('[province]');
			this.city = $('[city]');
			this.area = $('[area]');
			this.shop = $('[shop]');
			this.searchUser();
			this.areaRelated();
			this.findShop();
			this.showScan();
			this.events();

		},
		searchUser: function() {

			var param = {
				pagesize: 10,
				province: $('[province]').children().eq($('[province]')[0].selectedIndex).attr('code'),
				city: $('[city]').children().eq($('[city]')[0].selectedIndex).attr('code'),
				county: $('[area]').children().eq($('[area]')[0].selectedIndex).attr('code'),
				pkShop: $('[shop]').children().eq($('[shop]')[0].selectedIndex).attr('pkKey'),
				likecontent: $('[user-name]').val()
			};

			if(!this.oPage){
				this.oPage = new fenye(R.interfaces.member.query, oTplList, param);
			}else{
				this.oPage.refresh(param, R.interfaces.member.query, oTplList);
			}

		},
		showScan: function() {

			var type = bodyParse().type;

			if(type == 'scan'){
				this.oPage = new fenye(R.interfaces.member.queryScan, oTplList, {
					pagesize: 10
				});
			}

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

			this.oWrap.on('click', '[regist-app]', function(){
				
				_this.registApp($(this));

			});

			$(document).on('click', '[search-user-btn]', function(){
				_this.searchUser();
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
		registApp: function(oThis) {

			var _this = this;

			//给用户发送app密码
			var aid = oThis.attr('aid');
			this.reqUrl = R.interfaces.member.registApp;
			this.reqParam = {
				pkCustomer: aid
			};
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

	var oManage = new Manage();

});
