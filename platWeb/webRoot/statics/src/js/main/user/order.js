/**
 *description:服务单下单
 *author:fanwei
 *date:2015/1/25
 */
define(function(require, exports, module) {
	
	var global = require("../../driver/global");
	var ajaxForm = require('../../widget/form/ajaxForm');
	var oTip = require('../../widget/dom/tip');
	var bodyParse = require('../../util/http/bodyParse');
	var LayData = require('../../widget/dom/layData');
	var oTplTicheng = require('../../tpl/member/tichengList');
	var Calendar = require('../../widget/form/calendar');
	var toDouble = require('../../util/string/toDouble');

	var Order = R.Class.create(R.util, {

		initialize: function() {
			
			this.oOrderWrap = $('[order-wrap]');
			this.oAll = $('[all]');
			this.oTicheng = $('[ticheng]');
			this.oTichengWrap = $('[ticheng-list-wrap]');
			this.oSinHair = $('[sinHair]');
			this.oSave = $('[script-role = confirm-btn]');
			this.aSelectMoney = $('[name = fairermoney]');
			this.reserve = $('[name=reserve]');
			this.pageInfo = bodyParse();
			this.pkShop = this.parse().pkShop;
			this.fairername = $('[name=fairername]');

			this.pkorder = bodyParse().pkorder;

			this.showCalendar();
			this.judge();
			this.reqFairerList();
			this.sum();
			this.events();
			this.form();

			if(this.pkorder){
				this.getEditData();
			}

		},
		getEditData: function() {

			var _this = this;

			this.fairermoney = null;
			this.reqUrl = R.interfaces.member.getEditReserveInfo;
			this.reqParam = {
				pkOrder: this.pkorder
			};
			this.req(function(data) {
				
				_this.fairername.val(data.data.list.fairername);

				$('[name=fairermoney]').each(function() {
					if($(this).attr('pkprice') == data.data.list.pkPrice){
						this.checked = true;
						_this.oSinHair.attr('fairprice', $(this).attr('fairprice'));
						_this.fairermoney = $(this);
					}
				});

				$('[reserve-time]').val(data.data.list.appointtime);
				$('[all]').html(data.data.list.ordermoney);
				$('[name=note]').val(data.data.list.ordercontent);

				_this.getTichengBypkPrice(data.data.list.pkPrice, function() {
					var commissionpeople = eval(data.data.list.commissionpeople);

					if(commissionpeople && _this.fairermoney){
						for(var i=0;i<commissionpeople.length;i++){
							$('[otherfairer]').eq(i).val(commissionpeople[i].fairerName);
							$('[otherfairer]').eq(i).attr('oldFairerName',commissionpeople[i].fairerName);
							$('[otherfairer]').eq(i).attr('oldPkFairer',commissionpeople[i].pkFairer);
							$('[otherfairer]').eq(i).attr('oldAwardmoney',commissionpeople[i].ardmoney);
							$('[otherfairer]').eq(i).attr('oldIsrate',commissionpeople[i].israte);
						}
					}else{
						_this.oTicheng.hide();
					}
					
				});	

				if(data.data.list.nocontainhair == 'Y'){
					_this.oSinHair.attr('checked', 'checked');
				}else{
					_this.oSinHair.removeAttr('checked');
				}
				
			});
		},
		showCalendar: function() {

			var _this = this;
			var oDate = new Date();
			var today = oDate.getFullYear() + '-' + toDouble(oDate.getMonth()+1) + '-' + toDouble(oDate.getDate());

			var oCalendar = new Calendar({
				ele: '[calendar]',
				format: 'yyyy-MM-dd HH:mm',
				minDate: today
			});

		},
		events: function() {
			
			var _this = this;	

			this.oOrderWrap.on('click', 'input:[name=fairermoney]', function(){
				_this.getTicheng($(this));
				_this.sum($(this));
				_this.getNowFariPirce($(this));
				_this.sinHair();
			});

			this.oSinHair.on('click', function(){
				_this.sum();
				_this.sinHair($(this));
			});

		},
		getTicheng: function(oThis) {

			//根据价格查询提成;
			var pkPrice = oThis.attr('pkprice');

			if(pkPrice == this.lastClickPrice) {
				return;
			}

			this.lastClickPrice = pkPrice;
			var _this = this;
			this.reqUrl = R.interfaces.member.getTicheng;
			this.reqParam = {
				pkPrice: pkPrice,
				pkShop: this.pkShop
			};
			this.req(function(data){

				if(data.data) {
					_this.oTicheng.show();
					_this.render(_this.oTichengWrap, oTplTicheng, data);
				} else {
					_this.oTicheng.hide();
				}

			});

		},
		getTichengBypkPrice: function(pkPrice, fn) {
			//根据价格查询提成;
			var _this = this;
			this.reqUrl = R.interfaces.member.getTicheng;
			this.reqParam = {
				pkPrice: pkPrice,
				pkShop: this.pkShop
			};
			this.req(function(data){

				if(data.data) {
					_this.oTicheng.show();
					_this.render(_this.oTichengWrap, oTplTicheng, data);
				} else {
					_this.oTicheng.hide();
				}

				fn && fn();

			});
		},	
		getTichengData: function() {

			//获取提成需要提交的数据
			var aList = $('[ticheng-list]');
			var oList;
			var awardmoney;
			var israte;
			var param;
			var pkFairer;
			var fairerName;
			var _this = this;
			var arr = [];

			aList.each(function(i){

				oList = aList.eq(i);
				israte = oList.attr('israte');
				awardmoney = oList.attr('awardmoney');
				awardmoney = israte == 1 ? awardmoney : awardmoney/100;
				pkFairer = oList.attr('pkfairer');
				fairerName = oList.attr('_fairername');

				if(pkFairer) {
					param = {
						awardmoney: awardmoney,
						israte: israte,
						pkFairer: pkFairer,
						fairerName: fairerName
					};
					arr.push(param);	
				}

			});

			return arr;

		},
		sinHair: function() {

			//去除理发价格
			var isChecked = this.oSinHair.attr('checked');
			var fairPrice = this.oSinHair.attr('fairPrice');
			var orgPrice = this.oSinHair.attr('orgPrice');
			var nAll;
			var nPrice;
			var nOrgPrice;
			var nowMoney;

			nAll = parseInt(this.oAll.html());
			nPrice = parseInt(fairPrice);
			nOrgPrice = parseInt(orgPrice);

			if(isChecked) {
				
				if(fairPrice) {
					nowMoney = nAll - nPrice;
					this.oAll.html( nowMoney );
					this.nowFairPrice = nPrice;
				}
				this.isSinHair = 'Y';

			} else {
				
				if(fairPrice) {
					this.oAll.html( nowMoney );
					this.nowFairPrice = nPrice;
				}
				this.isSinHair = 'N';

			}

			this.oSave.attr('price', nowMoney);

			return this.isSinHair;

		},
		reqFairerList: function() {

			//获取理发师
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
		showHairerInfo: function() {
			
			var _this = this;
			var hairTpl = 
			'{{each list}}' +
				'<a class="ba-mr-10 ba-mb-10" lay-list fairername="{{$value.fairername}}" id="{{$value.pkFairer}}" href="javascript:;">'+
					'{{$value.fairername}}' +
				'</a>'+
			'{{/each}}';

			//理发师
			this.oFairer = new LayData({
				width: '300',
				ele: '[fairername]',
				data: {list: this.fairerList},
				sTpl: hairTpl,
				onClick: function(oTarget, oThis) {
					
					var oTip = _this.oOderForm.getTip(oTarget);
					var fairername = oThis.attr('fairername');
					var pkfairer = oThis.attr('id');

					_this.oOderForm.tipRight(oTarget, oTip);
					oTarget.val(fairername);
					oTarget.attr('fairername', fairername);
					_this.oOderForm.oSub.attr('pkFairer', pkfairer);

				}
			});

			//附加项理发师选择
			this.oOtherFairer = new LayData({
				width: '300',
				ele: '[otherFairer]',
				data: {list: this.fairerList},
				sTpl: hairTpl,
				onClick: function(oTarget, oThis) {
					
					var fairername = oThis.attr('fairername');
					oTarget.val(oThis.attr('fairername'));
					oTarget.attr('_fairername', fairername);
					oTarget.attr('pkFairer', oThis.attr('id'));

				}
			});

		},
		judge: function() {

			if(this.pkorder){
				this.nowSubUrl = R.interfaces.member.editPreorder;
			}else{
				this.nowSubUrl = R.interfaces.member.order;
			}

		},
		form: function() {

			var _this = this;

			this.oOderForm = new ajaxForm({

				subUrl: _this.nowSubUrl,
				fnSumbit: function( data ) {

					if(!_this.oAll.html()) {
						oTip.say('请检查金额填写是否正确');
						return false;
					}

					if(_this.oAll.html() < 0) {
						oTip.say('优惠金额不能大于服务金额');
						return false;
					}

					var tichengData = _this.getTichengData();
					
					if(tichengData.length && $('[ticheng]').css('display') != 'none'){
						data.commissionpeople = JSON.stringify(tichengData);
					}else{
						data.commissionpeople = '';
					} 
					data.customername = _this.pageInfo.name;
					data.comboname = _this.pageInfo.pkName;
					data.pkShopCombo = _this.pageInfo.spid; 
					data.pkCustomer = _this.pageInfo.pkCustomer;
					data.ordermoney = $('[all]').html();
					data.pkPrice = $('[name=fairermoney]:checked').attr('pkPrice');
					data.pkFairer = this.oSub.attr('pkFairer') || bodyParse().pkfairer;
					data.shopname = R.shopName;
					data.pkCustomerCombo = _this.pageInfo.comboid;
					data.nocontainhair = _this.sinHair();
					data.fairprice = _this.nowFairPrice || '';
					data.pkShop = _this.pkShop;
					data.isappointment = 'Y';

					//订单编号
					if(_this.pkorder){
						data.pkOrder = _this.pkorder;
					}
					
					if($('[reserve-time]').val()){
						data.appointtime = $('[reserve-time]').val();
					}else{
						oTip.say('请填写预约时间');
						return false;
					}
				
					return data;

				},
				sucDo: function(data) {

					oTip.say(data.msg);

					window.location = R.route['user/index'].url;

				},
				failDo: function(data) {

					oTip.say(data.msg);

				}

			});

			this.oOderForm.upload();

		},
		getNowFariPirce: function(oPrice) {

			//获取当前理发价格
			var fairPrice = oPrice.attr('fairPrice');
			var orgPrice = oPrice.val();

			if(fairPrice) {
				this.oSinHair.attr('fairPrice', fairPrice);
				this.oSinHair.attr('orgPrice', orgPrice);
			}

		},
		sum: function() {

			var count = 0;	
			var _this = this;
			var nMoney = 0;
			var all = 0;
			var _this = this;

			_this.aSelectMoney.each(function(i){

				var nowSelect = _this.aSelectMoney.eq(i);
				var isChecked = nowSelect.attr('checked');

				if(isChecked == 'checked') {
					nMoney = parseInt(nowSelect.val());
				}

			});


			if(!isNaN(count)) {
				all = count + nMoney;
				this.oAll.html(all);
				this.oSave.attr('price', all);
				this.oSinHair.attr('orgPrice', all);
			} else {
				this.oAll.html('');
			}

		}

	});

	var oOrder = new Order();

});
