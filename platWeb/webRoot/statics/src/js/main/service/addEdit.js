/**
 *description:服务管理添加编辑
 *author:fanwei
 *date:2015/1/25
 */
define(function(require, exports, module) {
	
	var global = require("../../driver/global");
	var Tag =  require('../../widget/form/tag');
	var bodyParse = require('../../util/http/bodyParse');
	var placeHolder = require('../../widget/dom/placeholder');
	var ajaxForm = require('../../widget/form/ajaxForm');
	var oTip = require('../../widget/dom/tip');
	var Select = require('../../widget/dom/select');
	var Upload = require('../../widget/upload/main');

	var Service = R.Class.create(R.util, {

		initialize: function() {
			
			this.oTagInput = $('[tag-input]');
			this.province = $('[province]');
			this.city = $('[city]');
			this.area = $('[area]');
			this.aEmergency = $('[emergency-list]');
			this.oIsDisableLogin = $('[isDisableLogin]');
			this.oLoadingWrap = $('[loading-wrap]');
			this.oLoadingText = $('[loading-text]');
			this.oLoadingPercent = $('[loading-percent]');

			this.shopprovince = $('[shopprovince]');
			this.shopcity = $('[shopcity]');
			this.shoparea = $('[shoparea]');
			this.shop = $('[shop]');
			
			this.pageInfo = bodyParse();
			this.cid = this.pageInfo.cid;
			this.judge();
			this.initTagWidget();
			this.showHolderTip();
			this.initUpload();
			this.events();

		},
		initUpload: function() {

			this.oUpload = new Upload({
				viewTpl: 
				'<span class="webuploader-view-list" webuploader-list>'+
					'<div class="webuploader-view-list-toolbar-wrap" webuploader-view-list-toolbar-wrap>' +
						'<div class="webuploader-view-list-toolbar-shadow"></div>' +
						'<div class="webuploader-view-list-toolbar">'+
							'<div class="tool-detail">' +
								'<span class="fa fa-trash-o ba-font-16" upload-remove title="删除"></span>' +
							'</div>' +
						'</div>' +
					'</div>' +
					'<img src="{{src}}" width="100%">' +
				'</span>'
			});

			this.oUpload.init();

		},
		judge: function() {

			if(this.cid) {
				//编辑
				this.nowSubUrl = R.interfaces.service.edit;

				this.areaRelated();
				this.findShop();
				this.showAreaData();

			} else {
				this.nowSubUrl = R.interfaces.service.add;
				this.areaRelated();
				this.findShop();
			}

			this.sub();	

		},
		showHolderTip: function() {

			//标签 placeholder
			new placeHolder(this.oTagInput);

		},
		initTagWidget: function() {

			//标签
			this.oTag = new Tag({
				oWrap: $('[tag-wrap]')
			});

			this.oTag.start();

		},
		sub: function() {

			var _this = this;

			this.oSubForm = new ajaxForm({

				subUrl: this.nowSubUrl,
				otherJude: [

					function() {

						var nTag = _this.oTag.getTagLength();

						if(nTag) {

							return true;

						} else {

							oTip.say('请至少输入一个技能');
							return false;
						}

					}

				],
				noSub: function(data) {

					this.loadingShow();

					data.skill = JSON.stringify(_this.oTag.getData().all);
					data.pkProvince = _this.oProvince.getNowOption().attr('code');
					data.pkCity = _this.oCity.getNowOption().attr('code');
					data.pkCounty = _this.oArea.getNowOption().attr('code');
					data.provincename = _this.oProvince.getNowOption().attr('value');
					data.cityname = _this.oCity.getNowOption().attr('value');
					data.countyname = _this.oArea.getNowOption().attr('value');
					data.pkShop = _this.shop.children().eq(_this.shop[0].selectedIndex).attr('pkkey');

					_this.oUpload.data = data;

					_this.oUpload.start();

					var nowUploadNum = _this.oUpload.getNum();
					
					if(!nowUploadNum) {
						data.attachment = _this.oUpload.getPicData();
						_this.subForm(data);
					}
	
				}

			});

			this.oSubForm.upload();

		},
		findShop: function(param) {

			var _this = this;

			_this.shop.html('<option value>请选择店铺</option>');

			this.reqUrl = R.interfaces.shop.shopList;
			this.reqParam = param || {};
			this.req(function(data) {
				var shopList = data.data.list;

				for(var i=0;i<shopList.length;i++){
					var option = $('<option value="'+shopList[i].shopname+'" pkKey="'+shopList[i].pkShop+'">'+shopList[i].shopname+'</option>');
					_this.shop.append(option);
				}

				_this.shop.val(_this.shop.attr('shopname'));

			});

		},
		events: function() {
			
			var _this = this;

			//查询店铺
			$(document).on('change', '[shop-area-wrap]', function() {
				_this.areaParam = {
					province: _this.shopprovince.children().eq(_this.shopprovince[0].selectedIndex).attr('code'),
					city: _this.shopcity.children().eq(_this.shopcity[0].selectedIndex).attr('code'),
					county: _this.shoparea.children().eq(_this.shoparea[0].selectedIndex).attr('code')
				};		
				_this.findShop(_this.areaParam);
			});

			//uploadEnd
			this.oUpload.onEnd = function(arr) {

				this.data.attachment = this.getPicData();
				console.log(this.data);
				_this.subForm(this.data);
			};

			//uploadProgress
			this.oUpload.onProgress = function(percent) {

				var percentText = percent * 100;

				_this.oLoadingWrap.show();
				_this.oLoadingText.html(percentText + '%');
				_this.oLoadingPercent.css('width', percentText + '%');

			};

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

			this.oShopProvince = new Select({
				ele: this.shopprovince,
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

							_this.oShopCity.clear();
							_this.oShopCity.render(data);

						});

					} else {

						_this.oShopCity.clear();
						_this.oShopArea.clear();

					}

				},
				onReady: function() {
					_this.ShopAreaCb && _this.ShopAreaCb();
				}	
			});

			this.oShopCity = new Select({
				ele: this.shopcity,
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

							_this.oShopArea.clear();
							_this.oShopArea.render(data);

						});

					} else {

						_this.oShopArea.clear();

					}

				}
			});

			this.oShopArea = new Select({
				ele: this.shoparea,
				tpl: 
				'{{each data}}'+
					'<option code="{{$value.pkArea}}" value="{{$value.areaname}}" id="{{$value.areaname}}">'+
						'{{$value.areaname}}'+
					'</option>'+
				'{{/each}}'
			});

		},
		showAreaData: function() {

			var provinceName = this.province.attr('areaname');
			var cityName = this.city.attr('areaname');
			var areaName = this.area.attr('areaname');

			var shopProvinceName = this.shopprovince.attr('areaname');
			var shopCityName = this.shopcity.attr('areaname');
			var shopAreaName = this.shoparea.attr('areaname');
			var shopName = this.shop.attr('shopname');

			//编辑时显示联动数据
			var _this = this;

			this.areaCb = function(){

				_this.oProvince.match('value', provinceName);

				//匹配店铺
				_this.shop.val(shopName);

				var nowCode = _this.oProvince.getNowOption().attr('code');

				_this.reqUrl = R.interfaces.global.getArea;
				_this.reqParam = {
					pkFather: nowCode
				};
				_this.req(function(data){

					_this.oCity.clear();
					_this.oCity.render(data);
					_this.oCity.match('value', cityName);

					var cityCode = _this.oCity.getNowOption().attr('code');

					_this.reqUrl = R.interfaces.global.getArea;
					_this.reqParam = {
						pkFather: cityCode
					};					
					_this.req(function(data){

						_this.oArea.clear();
						_this.oArea.render(data);
						_this.oArea.match('value', areaName);

					});

				});

			};

			this.ShopAreaCb = function(){

				_this.oShopProvince.match('value', shopProvinceName);

				var nowCode = _this.oShopProvince.getNowOption().attr('code');

				_this.reqUrl = R.interfaces.global.getArea;
				_this.reqParam = {
					pkFather: nowCode
				};
				_this.req(function(data){

					_this.oShopCity.clear();
					_this.oShopCity.render(data);
					_this.oShopCity.match('value', shopCityName);

					var cityCode = _this.oShopCity.getNowOption().attr('code');

					_this.reqUrl = R.interfaces.global.getArea;
					_this.reqParam = {
						pkFather: cityCode
					};					
					_this.req(function(data){

						_this.oShopArea.clear();
						_this.oShopArea.render(data);
						_this.oShopArea.match('value', shopAreaName);

					});

				});

			};


		},
		getEditData: function() {



		},
		subForm: function(data) {

			var _this = this;
			var isDisableLogin = this.oIsDisableLogin.attr('checked') ? "Y" : "N";
			//表单提交
			this.reqUrl = this.nowSubUrl;
			this.reqParam = data;
			this.reqParam.islogin = isDisableLogin;
			this.reqParam = this.getEmergencyData(this.reqParam);

			if(this.cid) this.reqParam.pkFairer = this.cid;
			
			this.req(function(data){

				oTip.say(data.msg);
				_this.oSubForm.loadingHide();

				window.location = data.data;

			}, function(data){

				oTip.say(data.msg);
				_this.oSubForm.loadingHide();

			});

		},
		getEmergencyData: function(orgData) {

			//获取紧急联系人数据
			var oEmergency;
			var oName;
			var oTel;
			var oRelate;
			var sName;
			var sTel;
			var sRelate;
			
			var _this = this;
			arr = [];

			this.aEmergency.each(function(i){
				var param = {};
				oEmergency = _this.aEmergency.eq(i);
				oName = oEmergency.find('[upload-namer = name]');
				oTel = oEmergency.find('[upload-namer = tel]');
				oRelate = oEmergency.find('[upload-namer = relate]');
				sName = oName.val();
				sTel = oTel.val();
				sRelate = oRelate.val();

				if(sName || sTel || sRelate) {
					param.name = sName;
					param.tel = sTel;
					param.relate = sRelate;
					arr.push(param);
				}

			});

			if(arr.length) {
				orgData.urgency = JSON.stringify(arr);
			}

			return orgData;

		}

	});

	var oService = new Service();

});
