/**
 *description:套餐修改
 *author:fanwei
 *date:2015/3/25
 */
define(function(require, exports, module) {
	
	var global = require("../../driver/global");
	var ajaxForm = require('../../widget/form/ajaxForm');
	var bodyParse = require('../../util/http/bodyParse');
	var oTip = require('../../widget/dom/tip');
	var oTplTicheng = require('../../tpl/goods/ticheng');
	var awardsTpl = require('../../tpl/goods/awardsList');
	var Select = require('../../widget/dom/select');

	var PackageEdit = R.Class.create(R.util, {

		initialize: function() {
			
			this.oWrap = $('[awards-wrap]');
			this.oSubAddBtn = $('[sub-add]');
			this.oReduceBtn = $('[sub-reduce]');
			this.oFuncBtn = $('[func-btn]');
			this.removeArr = []; //删除的集合
			this.pageInfo = bodyParse();
			this.pid = null;

			this.pkServicegroup = $('[pkServicegroup]');
			this.pkAdditiongroup = $('[pkAdditiongroup]');
			this.combonote = $('[combonote]');
			this.awardsList = $('[awards-wrap]');

			this.province = $('[province]');
			this.city = $('[city]');
			this.area = $('[area]');
			this.shop = $('[shop]');

			this.pkShop = null;

			this.areaRelated();
			this.findShop();
			this.showComboInfo();
			this.initAjaxForm();
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

			//查询店铺套餐提成组
			$('[shop]').on('change', function() {

				_this.pkShop = _this.shop.children().eq(_this.shop[0].selectedIndex).attr('pkkey');

				if(_this.pkShop){
					_this.getShopCombo(_this.pkShop);
				}else{
					_this.pkServicegroup.html('<option value="">请选择</option>');
					_this.pkAdditiongroup.html('<option value="">请选择</option>');
					_this.awardsList.html('');
				}
				
			});

			this.oWrap.on('change', '[sub-name = israte]', function(){
				_this.changeCheck($(this));
			});

			this.oWrap.on('click', '[sub-add]', function(){
				_this.subAdd();
				_this.packForm.reload();
			});

			this.oWrap.on('click', '[sub-reduce]', function(){
				_this.subReduce($(this));
			});

			this.oWrap.on('mouseenter', '[awards-list]', function(){

				_this.oFuncBtn.show();
				$(this).append(_this.oFuncBtn);

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
		showComboInfo: function() {
			$('[comboname]').html(this.parse().comboname);
			$('[combomoney]').val(this.parse().combomoney);
		},
		getShopCombo: function(pkShop) {

			var _this = this;

			this.reqUrl = R.interfaces.goods.shopComboAttr;

			this.reqParam = {
				pkShop: pkShop,
				pkCombo: this.parse().pkCombo
			};

			this.req(function(data){
				
				//获取店铺套餐提成组
				if(data.data.pricegroup){
					for(var i=0;i<data.data.pricegroup.length;i++){
						var option = $('<option value="'+data.data.pricegroup[i].pkShopGroup+'">'+data.data.pricegroup[i].groupname+'</option>');
						_this.pkServicegroup.append(option);
					}
				}else{
					oTip.say('请先维护该店铺套餐提成组');
					return;
				}
				

				//获取附加项目组
				if(data.data.additiongroup){
					for(var i=0;i<data.data.additiongroup.length;i++){
						var option = $('<option value="'+data.data.additiongroup[i].pkShopGroup+'">'+data.data.additiongroup[i].groupname+'</option>');
						_this.pkAdditiongroup.append(option);
					}
				}else{
					oTip.say('请先维护该店铺附加项目组');
					return;
				}
				
				_this.pkServicegroup.val(data.data.pkServicegroup);
				_this.pkAdditiongroup.val(data.data.pkAdditiongroup);
				_this.combonote.val(data.data.combonote);
				_this.pid = data.data.pkShopCombo;

				//获取提成信息
				_this.render(_this.awardsList, awardsTpl, data.data);
				_this.judgeLen();

			}, function(data) {
				oTip.say(data.msg);
				_this.pkServicegroup.html('<option value="">请选择</option>');
				_this.pkAdditiongroup.html('<option value="">请选择</option>');
				_this.combonote.val('');
				_this.awardsList.html('');
			});

		}
		,
		initAjaxForm: function() {

			var _this = this;

			this.packForm = new ajaxForm({

				subUrl: R.interfaces.goods.saveShopCombo,
				fnSumbit: function( data ) {

					var result = _this.getAwards();
					result = result.concat(_this.removeArr);
					data.currentmoney = data.shopmoney;
					data.pkShopCombo = _this.pid;
					data.awards = JSON.stringify(result);
					data.pkShop = _this.pkShop;
					data.pkServicegroup = _this.pkServicegroup.val();
					data.pkAdditiongroup = _this.pkAdditiongroup.val();

					return data;
				},
				sucDo: function(data) {

					oTip.say(data.msg);

					window.location = R.route['goods/list'].url;

				},
				failDo: function(data) {

					oTip.say(data.msg);

				}

			});

			this.packForm.upload();

		},
		changeCheck: function(oThis){

			//更换验证
			var status = oThis.val();
			var oTarget = oThis.parents('[awards-list]').find('[sub-name = awardmoney]');
			
			var re;
			var msg;
			var tip;
			var wrong;
			var ignorecheck;

			if(status == 1) {
				re = '^\\d+$';
				tip = '固定额度不能为空';
				msg = '固定额度为数字';
				wrong = '固定额度为数字';
				ignorecheck = 'no';
			} else if(status == 2) {
				re = '^(([1-9]\\d?)|100)$';
				tip = '提成比例不能为空';
				msg = '提成比例为100以内的数字';
				wrong = '提成比例为100以内的数字';
				ignorecheck = 'no';
			} else {
				re = '';
				tip = '请填写提成值';
				msg = '请填写提成值';
				ignorecheck = 'yes';
			}

			oTarget.attr('re', re);
			oTarget.attr('tip', tip);
			oTarget.attr('msg', msg);
			oTarget.attr('wrong', wrong);
			oTarget.attr('ignorecheck', ignorecheck);
		},
		
		getAwards: function() {

			var aList = $('[awards-list]');
			var i,
				num,
				result,
				allResult,
				arr,
				oList;

			num = aList.length;
			allResult = true;
			arr = [];

			for (i=0; i<num; i++) {
				oList = aList.eq(i);
				result = this.checkAward(oList, true);
				
				if(result) {
					arr.push(result);
				} 

			}

			return arr;

		},
		subAdd: function() {

			var oList = $('[awards-list]').last();
			var result = this.checkAward(oList);

			if(result) {

				this.render(this.oWrap, oTplTicheng, {}, 'append');

			}

		},
		judgeLen: function() {

			//如果一条提成都没有了，则再新增一条
			var len;
			len = $('[awards-list]').length;
			if(!len){
				this.render(this.oWrap, oTplTicheng, {});
			}

		},
		subReduce: function(oThis){

			var oNowList = oThis.parents('[awards-list]');
			var nowResult = this.checkAward(oNowList, true);
			oNowList.remove();
			this.judgeLen();

			if(!nowResult || !nowResult.pkFaireraward) return;

			nowResult.dr = 1;
			this.removeArr.push(nowResult);

		},
		checkAward: function(oList, noTip) {

			var aSubName = oList.find('[sub-name]');
			var i,
				num,
				tip,
				msg,
				re,
				sVal,
				name,
				ignorecheck,
				oSub,
				param,
				result;

			num = aSubName.length;
			result = true;
			param = {};

			for (i=0; i<num; i++) {

				oSub = aSubName.eq(i);
				re = new RegExp(oSub.attr('re'), 'gi');
				tip = oSub.attr('tip');
				msg = oSub.attr('msg');
				name = oSub.attr('sub-name');
				ignorecheck = oSub.attr('ignorecheck');
				sVal = oSub.val() || oSub.text().replace(/\s+/gi, '');

				param[name] = sVal;	

				if(ignorecheck == 'yes') continue;
					
				if(!sVal) {

					if(!noTip) {
						oTip.say(tip);
					}
					
					result = false;
					break;
				}

				if(!re.test(sVal)) {

					if(!noTip) {
						oTip.say(msg);
					}
					
					result = false;
					break;
				}			

			}	

			if(result) {
				return param;
			} else {
				return false;
			}		

		}

	});

	var oPackageEdit = new PackageEdit();

});
