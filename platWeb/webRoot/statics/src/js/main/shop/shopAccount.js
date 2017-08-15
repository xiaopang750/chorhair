/**
 *description:店铺账号
 *author:fanwei
 *date:2015/1/25
 */
define(function(require, exports, module) {
	
	var global = require("../../driver/global");
	var Dialog = require('../../widget/dom/dialog');
	var oTip = require('../../widget/dom/tip');
	var ajaxForm = require('../../widget/form/ajaxForm');
	var oTplList = require('../../tpl/shop/shopAccount');
	var fenye = require('../../widget/dom/fenye');
	var Select = require('../../widget/dom/select');
	var oAccountEditTpl = require('../../tpl/shop/accountEdit');
	
	var ShopAccount = R.Class.create(R.util, {

		initialize: function() {

			this.province = $('[province]');
			this.city = $('[city]');
			this.area = $('[area]');
			this.shop = $('[shop]');

			this.areaRelated();
			this.findShop();
			this.initDialogBox();
			this.searchShop();
			this.events();
			
		},
		initDialogBox: function() {

			var _this = this;

			//店铺账号编辑弹窗
			this.oAccountEditBox = new Dialog({
				boxTpl: oAccountEditTpl
			});

		},	
		searchShop: function() {
			var param = {
				pagesize: 10,
				province: $('[province]').children().eq($('[province]')[0].selectedIndex).attr('code'),
				city: $('[city]').children().eq($('[city]')[0].selectedIndex).attr('code'),
				county: $('[area]').children().eq($('[area]')[0].selectedIndex).attr('code'),
				pkShop: $('[shop]').children().eq($('[shop]')[0].selectedIndex).attr('pkKey')
			};

			if(!this.oPage){
				this.oPage = new fenye(R.interfaces.shop.shopAccount, oTplList, param);
			}else{
				this.oPage.refresh(param, R.interfaces.shop.shopAccount, oTplList);
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

			//弹框查询店铺
			$(document).on('change', '[area-wrap1]', function() {
				_this.areaParam = {
					province: _this.province1.children().eq(_this.province1[0].selectedIndex).attr('code'),
					city: _this.city1.children().eq(_this.city1[0].selectedIndex).attr('code'),
					county: _this.area1.children().eq(_this.area1[0].selectedIndex).attr('code'),
				};		
				_this.findShop1(_this.areaParam);
			});

			//店铺搜索
			$(document).on('click', '[search-shop-btn]', function(){
				_this.searchShop();
			});

			//店铺账号添加
			$(document).on('click', '[account-add]', function() {
				_this.oAccountEditBox.show();
				_this.addAccount();
			});

			//店铺账号编辑
			$(document).on('click', '[account-edit]', function() {
				_this.oAccountEditBox.show();
				_this.getEditData($(this));
			});

			//店铺账号保存
			$(document).on('click', '[account-confirm]', function() {
				_this.saveAccount();
			});

		},
		addAccount: function() {

			var editBox = $('[account-edit-box]');

			editBox.removeAttr('pkShop');
			editBox.find('[search-area]').show();
			editBox.find('[shopusercode]').parent().hide();
			editBox.find('[shopusername]').val('');
			editBox.find('[cellphone]').val('');
			editBox.find('[password]').val('');
			editBox.find('[repeat-password]').val('');

			this.province1 = $('[province1]');
			this.city1 = $('[city1]');
			this.area1 = $('[area1]');
			this.shop1 = $('[shop1]');

			this.areaRelated1();
			this.findShop1();

		},
		getEditData: function(oThis) {

			var editBox = $('[account-edit-box]');

			editBox.attr('pkShop', oThis.attr('pkShop'));
			editBox.find('[search-area]').hide();
			editBox.find('[shopusercode]').parent().show();
			editBox.find('[shopusercode]').val(oThis.attr('shopusercode'));
			editBox.find('[shopusercode]').attr('pkShopuser', oThis.attr('pkShopuser'));
			editBox.find('[shopusername]').val(oThis.attr('shopusername'));
			editBox.find('[cellphone]').val(oThis.attr('cellphone'));
			editBox.find('[password]').val('');
			editBox.find('[repeat-password]').val('');

		},
		saveAccount: function() {

			var editBox = $('[account-edit-box]');
			var _this = this;

			if(editBox.attr('pkShop')){
				this.reqUrl = R.interfaces.shop.editAccount;

				this.reqParam = {
					shopusername: editBox.find('[shopusername]').val(),
					pkShopuser: editBox.find('[pkShopuser]').attr('pkShopuser'),
					cellphone: editBox.find('[cellphone]').val(),
					loginpassword: editBox.find('[password]').val()
				};
			}else{
				this.reqUrl = R.interfaces.shop.addAccount;

				var pkShop = this.shop1.children().eq(this.shop1[0].selectedIndex).attr('pkKey')

				if(!pkShop){
					oTip.say('请选择店铺');
					return;
				}

				this.reqParam = {
					pkShop: pkShop,
					shopusername: editBox.find('[shopusername]').val(),
					cellphone: editBox.find('[cellphone]').val(),
					loginpassword: editBox.find('[password]').val()
				};
			}

			
			if(this.checkAccount(this.reqParam)) {
				this.req(function(data) {
					oTip.say(data.msg);
					_this.searchShop();
					_this.oAccountEditBox.close();
				},function(data) {
					oTip.say(data.msg);
				});
			}

		},
		checkAccount: function(param) {

			if($.trim(param.shopusername) == ''){
				oTip.say('请输入操作人');
				return false;
			}

			//联系电话
			var reCellphone = /^(1[3|4|5|8][0-9]\d{8}$)/;
			if($.trim(param.cellphone) == ''){
				oTip.say('请填写联系电话');
				return false;
			}else if(!reCellphone.test(param.cellphone)){
				oTip.say('联系电话格式不正确');
				return false;
			}

			if($.trim(param.loginpassword) == ''){
				oTip.say('请输入密码');
				return false;
			}

			//验证两次密码是否一致
			var password = $('[password]').val();
			var rPassword = $('[repeat-password]').val();

			if(password != rPassword){
				oTip.say('两次密码不一致');
				return false;
			}

			return true;

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
		areaRelated1: function() {

			//各店铺区域联动
			var _this = this;

			this.oProvince1 = new Select({
				ele: this.province1,
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

							_this.oCity1.clear();
							_this.oCity1.render(data);

						});

					} else {

						_this.oCity1.clear();
						_this.oArea1.clear();

					}

				},
				onReady: function() {
					_this.areaCb1 && _this.areaCb1();
				}	
			});

			this.oCity1 = new Select({
				ele: this.city1,
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

							_this.oArea1.clear();
							_this.oArea1.render(data);

						});

					} else {

						_this.oArea1.clear();

					}

				}
			});

			this.oArea1 = new Select({
				ele: this.area1,
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
		findShop1: function(param) {

			var _this = this;

			_this.shop1.html('<option>请选择店铺</option>');

			this.reqUrl = R.interfaces.shop.shopList;
			this.reqParam = param || {};
			this.req(function(data) {
				var shopList = data.data.list;

				for(var i=0;i<shopList.length;i++){
					var option = $('<option pkKey="'+shopList[i].pkShop+'">'+shopList[i].shopname+'</option>');
					_this.shop1.append(option);
				}
			});
		}

	});

	var oShopAccount = new ShopAccount();

});
