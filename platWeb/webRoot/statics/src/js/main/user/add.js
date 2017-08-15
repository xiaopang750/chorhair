/**
 *description:用户添加
 *author:fanwei
 *date:2015/1/25
 */
define(function(require, exports, module) {
	
	var global = require("../../driver/global");
	var ajaxForm = require('../../widget/form/ajaxForm');
	var oTip = require('../../widget/dom/tip');
	var bodyParse = require('../../util/http/bodyParse');
	var packageTpl = require('../../tpl/member/package');
	var editBoxTpl = require('../../tpl/member/editPackage');
	var Dialog = require('../../widget/dom/dialog');
	var Calendar = require('../../widget/form/calendar');
	var LayData = require('../../widget/dom/layData');
    var getNowTime = require('../../util/time/getNowTime');
    var fenye = require('../../widget/dom/fenye');
    var jsonStr = require('../../util/string/jsonStr');
    var oTplTichengList = require('../../tpl/member/tichengList');
    var select = require('../../widget/dom/select');
    var toDouble = require('../../util/string/toDouble');
    var Select = require('../../widget/dom/select');

	var Add = R.Class.create(R.util, {

		initialize: function() {
			
			this.oPageInfoWrap = $('[pageInfoWrap]');
			this.oAddForm = $('[script-bound = form-check]');
			this.oPackageWrap = $('[data-ele = data-wrap]');
			this.oPackageSave = $('[save]');
			this.oName = $('[name = username]');
			this.oSexWrap = $('[sex-wrap]');
			this.aSex = $('[name=sex]');
			this.oTel = $('[name = cellphone]');
			this.oNote = $('[name = note]');
			this.oModify = $('[unlock]');
			this.oCancel = $('[cancel]');
			this.aLock = $('[lock]');
			this.oAddPackageBtn = $('[add-package]');
			this.oPackageSex = $('[package-sex]');
			this.oPageckageBtn = $('[fast-order-btn]');

			this.curdate = null;
			this.isChose = true;

			this.province = $('[province]');
			this.city = $('[city]');
			this.area = $('[area]');
			this.shop = $('[shop]');

			this.shopprovince = $('[shopprovince]');
			this.shopcity = $('[shopcity]');
			this.shoparea = $('[shoparea]');
			
			this.nowAwardValue = 0;
			this.maxSelect = 2;
			this.pageInfo = bodyParse();
			this.uid = this.pageInfo.uid;
			this.nowPackageId = null;
			this.showCanlendar();
			this.judge();
			this.renderEditBox();
			this.form();
			this.events();
			this.placeHolder();

		},
		reqFairerList: function() {

			var _this = this;
			
			this.reqUrl = R.interfaces.service.validate;
			this.reqParam = {
				pkShop: this.pkShop
			};
			this.req(function(data){

				_this.fairerList = data.data.list;
				_this.showHairerInfo();
			
			});

		},
		reqPackageList: function(key) {

			var _this = this;
			var nowSex = this.oPackageSex.attr('package-sex');
			var str = key || '';

			this.reqUrl = R.interfaces.member.packageInfo;
			this.reqParam = {sex: nowSex, querylike: str, pkShop: this.pkShop};

			this.req(function(data){

				_this.packageList = data.data.list;
				_this.showPackageInfo();

			});

		},
        relatedTime: function(comboType) {

            //选择套餐关联时间
            var oStart = $('[box-start]');
            var oEnd = $('[box-end]');
            var oTimeStart;
            var oTimeEnd;
            var sTimeStart;
            var sTimeEnd;

            /*
            * 1 为套餐
            * 2 为单次
            * */

            oTimeStart = new Date();
            oTimeEnd = new Date();

            if(comboType == 2) {

                oTimeEnd.setDate(oTimeEnd.getDate() + 1);

            } else {

                oTimeEnd.setFullYear(oTimeEnd.getFullYear() + 1);
                oTimeEnd.setDate( oTimeEnd.getDate() - 1 );
            }

            sStart = getNowTime(oTimeStart).time;
            sEnd = getNowTime(oTimeEnd).time;
            oStart.val(sStart);
            oEnd.val(sEnd);

        },
		showPackageInfo: function() {

            //选择套餐
			var _this = this;

			this.isChose = false;

			if(!this.oPackage) {
				
				this.oPackage = new LayData({
					width: '300',
					ele: '[comboname]',
					position: 'fixed',
					data: {list: this.packageList},
					sTpl: 
					'{{each list}}' +
						'<p class="ba-pt-10 ba-pb-10">'+
							'<a class="ba-mr-5 ba-mb-5" lay-list="data" money="{{$value.currentmoney}}" comboname="{{$value.comboname}}" pkCombo="{{$value.pkCombo}}" id="{{$value.pkShopCombo}}" combocount="{{$value.combocount}}" combotype="{{$value.combotype}}" fairtype="{{$value.fairtype}}" href="javascript:;">'+
								'{{$value.comboname}}' +
								'&nbsp;&nbsp;&nbsp;'+
								'{{$value.currentmoney}}' +
								'元'+
							'</a>'+
						'</p>'+
					'{{/each}}',
					onClick: function(oTarget, oThis) {
						
						var oTip = _this.oPackageForm.getTip(oTarget);
                        var comboType = oThis.attr('combotype');
                        var money = oThis.attr('money');
                        var packageId = oThis.attr('id');
                        var pkCombo = oThis.attr('pkCombo');
                        var combocount = oThis.attr('combocount');
                        var combotype = oThis.attr('combotype');
                        var fairtype = oThis.attr('fairtype');

						_this.oPackageForm.tipRight(oTarget, oTip);

						if(oThis.attr('combocount') == '0'){
							$('[pack-count]').val('全年不限次');
						}else{
							$('[pack-count]').val(oThis.attr('combocount'));
						}

						oTarget.val(oThis.attr('comboname'));

						_this.oPackageForm.oSub.attr('money', money);
						_this.oPackageForm.oSub.attr('pkShopCombo', packageId);
						_this.oPackageForm.oSub.attr('combocount', combocount);
						_this.oPackageForm.oSub.attr('combotype', combotype);
						_this.oPackageForm.oSub.attr('fairtype', fairtype);
						_this.getPackageMoney();
                        // _this.relatedTime(comboType);
                        _this.getAwardPepole(packageId);
                        _this.getSale(pkCombo);

                        _this.isChose = true;

					}
				});

			} else {

				this.oPackage.render({list: this.packageList});

			}

		},
		getSale: function(pkCombo) {

			var _this = this;

			var oSelect = new select({
				url: R.interfaces.member.packageGetSale,
				ele: $('[sale-list-wrap]'),
				param: {
					pkCombo: pkCombo,
					pkCustomer: this.uid
				},
				tpl:
				'<option value="">请选择</option>' + 
				'{{each data}}' +
					'<option awardname="{{$value.awardname}}" awardvalue="{{$value.awardvalue}}" pkCustomerOwnaward="{{$value.pkCustomerOwnaward}}" pkCustomeraward="{{$value.pkCustomeraward}}">{{$value.awardname}}({{$value.awardvalue}}元)</option>' +
				'{{/each}}',
				onChange: function(oThis, nowOption, nowIndex) {

					var arr = [];
					var param = {};
					var awardname = nowOption.attr('awardname');
					var awardvalue = nowOption.attr('awardvalue');
					var pkCustomeraward = nowOption.attr('pkCustomeraward');
					var pkCustomerOwnaward = nowOption.attr('pkCustomerOwnaward');
					param.awardname = awardname;
					param.awardvalue = awardvalue;
					param.pkCustomeraward = pkCustomeraward;
					param.pkCustomerOwnaward = pkCustomerOwnaward;
					arr.push(param);

					_this.nowSaleValue = JSON.stringify(arr);
					_this.nowAwardValue = awardvalue? parseInt(awardvalue) : 0;
					_this.getPackageMoney();

				}
			});

			var oSaleSelectWrap = $('[sale-wrap]');

			oSelect.onReady = function(oThis, nowOption, nowIndex, data) {

				//有抵用券
				if(data.data) {
					oSaleSelectWrap.show();
				} else {
					//无抵用券
					oSaleSelectWrap.hide();
				}

			};

		},
		getAwardPepole: function(pid) {

			var oTichengListWrap = $('[ticheng-list-wrap]');
			var _this = this;
			this.reqUrl = R.interfaces.member.packageGetTicheng;
			this.reqParam = {
				pkShop: _this.pkShop,
				pkShopCombo: pid
			};
			this.req(function(data){

				_this.render(oTichengListWrap, oTplTichengList, data);

			});

		},
		showHairerInfo: function() {
			
			var _this = this;

			//理发师
			this.oFairer = new LayData({
				width: '300',
				ele: '[fairer]',
				position: 'fixed',
				data: {list: this.fairerList},
				sTpl: 
				'{{each list}}' +
					'<a lay-list href="javascript:;" class="ba-mr-10 ba-mb-10 ba-ml-2" fairername="{{$value.fairername}}" id="{{$value.pkFairer}}">' +
						'{{$value.fairername}}' +
					'</a>' +
				'{{/each}}',
				onClick: function(oTarget, oThis) {
					
					var fairername = oThis.attr('fairername');
					var pkfairer = oThis.attr('id');

					oTarget.val(fairername);
					oTarget.attr('pkfairer', pkfairer);
					oTarget.attr('fairername', fairername);
					_this.getTichengWay();
				}
			});

		},
		getTichengWay: function() {

			//获取提成需要提交的数据
			var aList = $('[ticheng-list]');
			var oList;
			var awardmoney;
			var israte;
			var fairername;
			var awardname;
			var param;
			var pkFairer;
			var _this = this;
			var arr = [];

			aList.each(function(i){

				oList = aList.eq(i);
				israte = oList.attr('israte');
				fairername = oList.attr('fairername');
				awardname = oList.attr('awardname');
				awardmoney = oList.attr('awardmoney');
				awardmoney = israte == 1 ? awardmoney : awardmoney/100;
				pkFairer = oList.attr('pkfairer');

				if(pkFairer) {
					param = {
						awardmoney: awardmoney,
						israte: israte,
						pkFairer: pkFairer,
						fairername: fairername,
						awardname: awardname
					};
					arr.push(param);	
				}

			});

			this.oPackageForm.oSub.attr('fairerStr', JSON.stringify(arr));

		},
		showCanlendar: function() {

			var _this = this;

			var oDate = new Date();
			var today = oDate.getFullYear() + '-' + toDouble(oDate.getMonth()+1) + '-' + toDouble(oDate.getDate());

			var oCalendarBirthday = new Calendar({
				ele: '[birthday]',
				format: "yyyy-MM-dd",
				maxDate: today
			});

		},
		renderEditBox: function() {

			//初始化套餐编辑弹框
			this.oEditBox = new Dialog({
				boxTpl: editBoxTpl
			});
			
			this.packageForm();	

		},
		judge: function() {

			//判断是添加还是编辑
			if(this.uid) {

				this.nowWay = 'edit';
				this.nowSubUrl = R.interfaces.member.edit;
				this.getPackageInfo({pkCustomer: this.uid});
				
				this.areaRelated();
				this.findShop();
				this.showAreaData();
				this.reqFairerList();

			} else {
				this.nowWay = 'add';
				this.nowSubUrl = R.interfaces.member.add;
				this.areaRelated();
				this.findShop();
			}

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

			this.pkShop = this.shop.attr('pkShop');

			//编辑时显示联动数据
			var _this = this;

			this.areaCb = function(){

				_this.oProvince.match('value', provinceName);

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

				//匹配店铺
				_this.shop.val(shopName);

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
			
			//修改信息时，更新pkshop
			$(document).on('click', '[script-role=confirm-btn]', function(){
				_this.pkShop = _this.shop.children().eq(_this.shop[0].selectedIndex).attr('pkKey')
			});

			//下单
			this.oPackageWrap.on('click', '[order]', function(){

				var oThis = $(this);
 				
 				_this.nowEditList = $(this).parents('[list-info]');
 				_this.nowData = _this.nowEditList.data('info');

				_this.reqUrl = R.interfaces.member.getPrice;
				_this.reqParam = {
					pkShopCombo: $(this).attr('spid'),
					pkShop: _this.pkShop
				};
				_this.req(function(data) {
					
					if(_this.nowData.comboendtime && _this.nowData.combobegintime) {
						if(_this.curdate > _this.nowData.comboendtime || _this.curdate < _this.nowData.combobegintime){
							oTip.say('该套餐不在有效期内');
							return;
						}
					}
					
					var nowName = _this.oPageInfoWrap.attr('nowname');
					var url = oThis.attr('href') + '&name=' + nowName + '&pkShop=' + _this.pkShop;

					window.location = url;

				},function(data) {
					oTip.say(data.msg);
				});	

				return false;

			});	


			//modify
			this.oModify.on('click', function(){

				_this.aLock.removeAttr('disabled');

			});

			//取消修改
			this.oCancel.on('click', function(){

				_this.aLock.attr('disabled', 'disabled');
				_this.oRegistForm.refresh();
				_this.aLock.each(function(i){

					var orgSex = _this.oSexWrap.attr('org');
					_this.aSex.eq(orgSex).attr('checked', 'checked');
					_this.aLock.eq(i).val( _this.aLock.eq(i).attr('org') );

				});

			});

			//套餐添加
			this.oAddPackageBtn.on('click', function(){
				
				var sex = $(this).attr('package-sex');

				_this.nowWay = 'add';
				_this.oPackageForm.subUrl = R.interfaces.member.savePackage;
				_this.reqPackageList();

				_this.oEditBox.refreshData({});
				_this.oEditBox.show();

				_this.oPackageForm.reload({
					boundName: 'package-wrap',
					btnName: 'package-btn'
				});

			});

			//套餐查询
			$(document).on('keyup', '[comboname]', function() {
				_this.reqPackageList($(this).val());
			});

			//计算价格
			$(document).on('keyup', function(){
				_this.getPackageMoney();
			});
			$(document).on('keydown', function(){
				_this.getPackageMoney();
			});

			//快速下单
			this.oPageckageBtn.on('click', function() {
				_this.fastOrder(1);
			});

			//洗头下单
			$('[wash-order-btn]').on('click', function() {
				_this.fastOrder(2);
			});

			//造型下单
			$('[model-order-btn]').on('click', function() {
				_this.fastOrder(3);
			});
		},
		fastOrder: function(type) {
			
			var _this = this;
			var url;
			var info;
			var param;
			var realData;
			var uid = this.pageInfo.uid;

			this.reqUrl = R.interfaces.member.fastOrder;
			this.reqParam = {
				pkCustomer: uid,
				pkShop: this.pkShop,
				fasttype: type
			};

			this.req(function(data){

				realData = data.data;

				/*
					oderType: 'sinHair' 理发订单 
					orderType: 'package' 套餐订单
					pkName： 套餐名称
					spid: 店铺套餐主键
					comboid : 消费者套餐主键
					name: 消费者姓名
				*/

				info = {
					pkName: realData.comboname,
					pkCustomer: uid,
					spid: realData.pkShopcombo,
					comboid: realData.pkCustomerCombo,
					name: realData.customername,
					pkShop: _this.pkShop
				};

				param = jsonStr(info);

				url = R.route['user/order'].url + '?' + param;
				
				window.location = url;

			}, function(data) {
				oTip.say(data.msg);
			});

		},
		refreshList: function(param) {

			//刷新列表数据
			this.nowData.combobegintime = param.combobegintime;
			this.nowData.comboendtime = param.comboendtime;
			this.nowEditList.data('info', this.nowData);	

			var sCurdate = new Date( this.curdate.replace(/\-/gi, '\/') ).getTime();
			var sStartTime = new Date( param.combobegintime.replace(/\-/gi, '\/') ).getTime();
    		var sEndTime = new Date( param.comboendtime.replace(/\-/gi, '\/') ).getTime();
    		var cha = sEndTime - sCurdate;
    		var timeLeft = parseInt(cha / 1000 / 60 / 60 / 24, 10);
			this.nowEditList.find('[timeLeft]').html(timeLeft + '天');

		},
		addList: function(data) {

			/*this.render(this.oPackageWrap, packageTpl, {data: [data]}, 'prepend');
			var oNewList = $('[list-info]').eq(0);
			oNewList.data('info', data);*/
			this.nowPackageParam.curpage = 1;
			this.oPage.refresh(this.nowPackageParam);

		},
		getPackageMoney: function() {
			
			//(新增套餐/编辑套餐)时价格计算
			var oBtn = $('[script-role = package-btn]');
			var all = oBtn.attr('money');
			var discount = $.trim($('[sale]').val()) || 0;
			var nAll = parseInt(all);
			var nDiscount = !isNaN(discount) ? parseInt(discount) : 0;
			var nCha = nAll - nDiscount - parseInt(this.nowAwardValue);

			nCha = isNaN(nCha) ? 0 : nCha;

			$('[sum]').text(nCha);

		},
		hideEdit: function(oEdit) {

			oEdit.addClass('ba-hidden');

		},
		showEdit: function(oEdit) {

			oEdit.removeClass('ba-hidden');
		},
		getPackageInfo: function(param, cb) {

			//获取套餐信息
			var _this = this;

			this.oPageInfoWrap.show();
			this.nowPackageParam = param;
			this.nowPackageParam.pagesize = 10;

			this.oPage = new fenye(R.interfaces.member.hasedPackage, packageTpl, param, '', function(data){

				_this.curdate = data.curdate;

				var aList = $('[list-info]');
				aList.each(function(i){
					aList.eq(i).data('info', data.list[i]);
				});

				cb && cb();

			});

		},
		packageForm: function() {

			//套餐信息表单
			var _this = this;
			
			this.oPackageForm = new ajaxForm({

				boundName: 'package-wrap',
				btnName: 'package-btn',
				/*otherJude: [

					function() {
						var sStart = $('[box-start]').val();
						var sEnd = $('[box-end]').val();
						var sStartTime = new Date( sStart.replace(/\-/gi, '\/') ).getTime();
						var sEndTime = new Date( sEnd.replace(/\-/gi, '\/') ).getTime();

						if(sStartTime > sEndTime) {
							oTip.say('开始时间不能大于结束时间');
							return false;
						} else {
							return true;
						}
					}

				],*/
				otherJude: [

					function() {
						var sComboname = $('[comboname]').val();
						for(var i=0;i<_this.packageList.length;i++){
							if(_this.packageList[i].comboname == sComboname){
								return true;
							}
						}
						oTip.say('请从套餐列表中选择套餐');
						return false;
					}

				],
				fnSumbit: function(data) {

					data.pkFairer = this.oSub.attr('pkFairer');
					data.currentmoney = this.oSub.attr('money');
					data.pkShopCombo = this.oSub.attr('pkShopCombo');
					data.totalcount = this.oSub.attr('combocount');
					data.pkCustomerCombo = this.oSub.attr('pkCustomerCombo');
					data.pkCustomer = _this.uid;
					data.isselect = 'Y';
					data.commissionpeople = this.oSub.attr('fairerstr');
					data.discount = $('[name = discount]').val() || 0;
					data.combotype = this.oSub.attr('combotype');
					data.fairtype = this.oSub.attr('fairtype');
					data.awardvalue = _this.nowAwardValue ? _this.nowAwardValue : '';
					data.awards = _this.nowSaleValue;
					data.pkShop = _this.pkShop;

					var fairername = '';

					$('[fairer]').each(function() {
						if($(this).attr('fairername')){
							fairername += ($(this).attr('fairername') + ',');
						}	
					});

					data.fairername = fairername.substring(0, fairername.length-1);

					var nCurrentmoney = parseInt(data.currentmoney);
					var nDiscount = parseInt( data.discount );
					/*var nCommissionpeople = JSON.parse(data.commissionpeople).length;

					if(nCommissionpeople > _this.maxSelect) {
						oTip.say('提成人最多不能超过' + _this.maxSelect + '个');
						return false;
					}*/

					if(!_this.isChose){
						oTip.say('请从套餐列表中选择套餐');
						return false;
					}

					if(nDiscount > nCurrentmoney) {
						oTip.say('优惠金额不能大于套餐金额');
						return false;
					}

					return data;	

				},
				sucDo: function(data, oBtn, endParam) {

					_this.oEditBox.close();

					if(_this.nowWay == 'edit') {

						_this.refreshList(endParam);

					} else if(_this.nowWay == 'add') {

						_this.addList(data.data);

					}

					oTip.say(data.msg);

				},
				failDo: function(data) {

					oTip.say(data.msg);
				}

			});

			this.oPackageForm.upload();

		},
		form: function() {

			//pkShop
			//pkUser
			//用户编辑
			//用户信息表单
			var _this = this;

			this.oRegistForm = new ajaxForm({

				subUrl: this.nowSubUrl,
				fnSumbit: function(data) {

					if(_this.uid) {

						data.pkCustomer = _this.uid;
					}

					data.pkShop = $('[shop]').children().eq($('[shop]')[0].selectedIndex).attr('pkKey');
					data.province = $('[province]').children().eq($('[province]')[0].selectedIndex).attr('code');
					data.provincename = $('[province]').children().eq($('[province]')[0].selectedIndex).attr('value');
					data.city = $('[city]').children().eq($('[city]')[0].selectedIndex).attr('code');
					data.cityname = $('[city]').children().eq($('[city]')[0].selectedIndex).attr('value');
					data.county = $('[area]').children().eq($('[area]')[0].selectedIndex).attr('code');
					data.countyname = $('[area]').children().eq($('[area]')[0].selectedIndex).attr('value');
					data.addr = $('[addr]').val();

					return data;	

				},
				sucDo: function(data, oBtn, param) {

					oTip.say(data.msg);

					_this.oPageInfoWrap.show();
					
					var uid = data.data;
					
					if(_this.uid) {
						//编辑
						_this.oPageInfoWrap.attr('nowname', param.username);
						_this.aLock.each(function(i){
							
							var oLock = _this.aLock.eq(i);
							var lockName = oLock.attr('lock');
							oLock.attr('org', param[lockName]);

							_this.aLock.attr('disabled', 'disabled');

						});
					} else {

						//添加
						window.location = R.route['user/add'].url + '?uid=' + uid;
					}

					_this.oPackageSex.attr('package-sex', param.sex);

				},
				failDo: function(data) {

					oTip.say(data.msg);

				}

			});

			this.oRegistForm.upload();

		}

	});

	var oAdd = new Add();

});
