/**
 *description:操作台首页
 *author:fanwei
 *date:2015/1/25
 */
define(function(require, exports, module) {
	
	var global = require("../../../driver/global");
	var oTip = require('../../../widget/dom/tip');
	var enterDo = require('../../../widget/dom/enterDo');
	var LayData = require('../../../widget/dom/layData');
	var Dialog = require("../../../widget/dom/dialog");
	var toDouble = require("../../../util/string/toDouble");
	var Calendar = require('../../../widget/form/calendar');
	var dosageTpl = require('../../../tpl/shop/user/dosage');

	var Index = R.Class.create(R.util, {

		initialize: function() {
			
			var _this = this;

			this.oUserInput = $('[search-input]');
			this.oSearchBtn = $('[search-btn]');
			this.oPayWrap = $('[pay-wrap]');
			this.oAppointWrap = $('[appoint-time-wrap]');
			this.oOrderWrap = $('[order-time-wrap]');
			this.oPayNum = $('[pay-num]');
			this.oPayUl = this.oPayWrap.find('ul');
			this.defaultPagesize = 10;
			this.orderCurpage = 1;
			this.appointCurpage = 1;
			this.loadMore = $('[load-more]'); 
			this.paymode = $('[paymode]');
			this.dosageGoodsArr = [];

			this.showCalendar();
			this.initDialog();
			this.searchLink = this.oSearchBtn.attr('link');
			this.reqFairerList();

			if(R.userType == 1) {
				//店铺默认查询今天的订单
				this.reqPayList({
					begintime: this.toadyDate().begintime,
					endtime: this.toadyDate().endtime
				});
			}else if(R.userType == 2) {
				//平台默认查询所有预约单

			}	

			this.events();

		},
		events: function() {
			
			var _this = this;

			this.oSearchBtn.on('click', function(){

				var sValue = _this.oUserInput.val();
				_this.search(sValue);

			});

			enterDo(this.oUserInput, function(){
				var sValue = _this.oUserInput.val();
				_this.search(sValue);
			});


			//扫描二维码
			$('[queryscan]').on('click', function() {
				_this.queryScan();
			});

			//操作附加项目时获取价格
			this.oPayWrap.on('keyup', '[sum-item]', function(){
				var nowList = $(this).parents('[sum-list]');
				_this.getListPrice(nowList);
			});	

			//优惠金额
			this.oPayWrap.on('keyup', '[discount]', function(){
				var nowList = $(this).parents('[sum-list]');
				_this.getListPrice(nowList);
			});

			//优惠金额验证
			$(document).on('keyup', '[discount], [otherpay]', function(){
				var sValue = $(this).val();
				var re = /^\d+$/g;
				var nowList = $(this).parents('[sum-list]');

				if(!re.test(sValue) && sValue != ''){
					oTip.say('请填写数字');
					$(this).val('');
					_this.getListPrice(nowList);
				}
					
			});	

			//结算方式
			this.oPayWrap.on('change', '[paymode]', function(){
				var nowList = $(this).parents('[sum-list]');
				_this.getListPrice(nowList);
			});

			//套餐商品用量
			this.oPayWrap.on('click', '[dosage]', function(){
				var nowList = $(this).parents('[sum-list]');
				_this.dosageBox.show();
				_this.dosageBox.dom().attr('pid', $(this).parents('[sum-list]').attr('pid'));
				_this.queryComboGoods(nowList);
			});

			$('.dosage-dialog').on('mouseenter', '[dosage-list]', function() {
				$(this).find('[dosage-handle]').show();
			});

			$('.dosage-dialog').on('mouseleave', '[dosage-list]',function() {
				$('[dosage-handle]').hide();
			});

			//添加套餐商品操作
			$('.dosage-dialog').on('click', '[sub-add]', function() {
				var row = $(this).parents('li');
				_this.dosageAdd(row);
			});

			//删除商品套餐操作
			$('.dosage-dialog').on('click', '[sub-reduce]', function() {
				var row = $(this).parents('li');
				_this.dosageReduce(row);
			});

			$('.dosage-dialog').on('change', '[combo-goods]', function() {

				_this.checkDosage($(this));
				$(this).parent().find('[combo-goods-unit]').html($(this).children().eq($(this)[0].selectedIndex).attr('unit'));

			});

			//确认结算
			this.oPayWrap.on('click', '[sum-btn]', function(){
				_this.nowSumList = $(this);
				_this.oConfirmSumBox.show();
			});	

			this.oConfirmSumBox.onConfirm = function() {
				_this.sum(_this.nowSumList);
			};

			//订单预约单切换
			$(document).on('click', '[order-tab]', function() {

				if($(this).hasClass('active')) {
					return false;
				}

				$(this).addClass('active ba-default').siblings().removeClass('active ba-default');

				_this.oPayUl.html('');

				$('[order-begintime]').val('');
				$('[order-endtime]').val('');
				$('[appoint-begintime]').val('');
				$('[appoint-endtime]').val('');

				if($(this).attr('order-tab') == 'reserve') {

					_this.oAppointWrap.show();
					_this.oOrderWrap.hide();
					_this.loadMore.attr('load-more', 'appoint');

					var begintime = $('[search=appoint-begintime]').val();
					var endtime = $('[search=appoint-endtime]').val();

					if(begintime == '' && endtime == ''){
						_this.reqReserveList({
							begintime: _this.toadyDate().begintime,
							endtime: _this.toadyDate().endtime
						});
					}else{
						_this.reqReserveList({
							begintime: begintime,
							endtime: endtime
						});
					}

				}else if($(this).attr('order-tab') == 'order') {

					_this.oAppointWrap.hide();
					_this.oOrderWrap.show();
					_this.loadMore.attr('load-more', 'order');

					var begintime = $('[search=order-begintime]').val();
					var endtime = $('[search=order-endtime]').val();

					if(begintime == '' && endtime == ''){
						_this.reqPayList({
							begintime: _this.toadyDate().begintime,
							endtime: _this.toadyDate().endtime
						});
					}else{
						_this.reqPayList({
							begintime: begintime,
							endtime: endtime
						});
					}

				}
			});

			//按时间查询订单
			$('[search-order]').on('click', function() {

				_this.oPayUl.html('');

				var begintime = $('[search=order-begintime]').val();
				var endtime = $('[search=order-endtime]').val();

				_this.orderCurpage = 1;

				_this.reqPayList({
					begintime: begintime,
					endtime: endtime
				});

			});

			//按时间查询预约单
			$('[search-appoint]').on('click', function() {

				_this.oPayUl.html('');

				var begintime = $('[search=appoint-begintime]').val();
				var endtime = $('[search=appoint-endtime]').val();

				_this.orderCurpage = 1;
				
				_this.reqReserveList({
					begintime: begintime,
					endtime: endtime
				});

			});

			//加载更多
			$('[load-more]').on('click', function() {
				if($(this).attr('load-more') == 'order'){
					_this.loadMoreOrder();
				}else{
					_this.loadMoreAppoint();
				}
			});

			//取消预约单
			this.oPayWrap.on('click', '[cancel-reserve-btn]', function() {
				_this.cancelBtn = $(this);
				_this.oConfirmCancelBox.show();
			});

			//生成订单
			this.oPayWrap.on('click', '[create-order-btn]', function() {
				_this.createBtn = $(this);
				_this.oConfirmCreateBox.show();
			});

			this.oConfirmCancelBox.onConfirm = function() {
				_this.cancelReserve();
			};

			this.oConfirmCreateBox.onConfirm = function() {
				_this.createOrder();
			};

			//编辑预约单
			this.oPayWrap.on('click', '[edit-reserve-btn]', function() {
				window.location = R.route['user/appoint'].url + '?';
			});
			
		},
		showCalendar: function() {

			var _this = this;

			var oCalendar = new Calendar({
				ele: '[calendar]',
				format: 'yyyy-MM-dd HH:mm'
			});

		},
		initDialog: function() {

			var _this = this;

			this.dosageBox = new Dialog({
				boxTpl: dosageTpl,
				onConfirm: function(){
				//判断商品信息录入是否完整
                var box=_this.dosageBox.dom();               
				var dosageList= box.find('[dosage-list]');
			    var istrue = true;
			    dosageList.each(function(){                
	             if($(this).find('[combo-goods]').val() == '' || $(this).find('[combo-goods-num]').val() == ''){				
                    istrue=false;
				  }
                });

                if(istrue){
					var pid = _this.dosageBox.dom().attr('pid');
					_this.saveDosage(_this.dosageBox.dom(), pid);
				}else{
					 oTip.say('请录入完整的商品信息');
				}

				}
			});

			this.oConfirmSumBox = new Dialog({
				content: '确认结算么?'
			});

			this.oConfirmCancelBox = new Dialog({
				content: '确认取消预约单吗?'
			});

			this.oConfirmCreateBox = new Dialog({
				content: '确认生成订单吗?'
			});

		},
		reqFairerList: function() {

			//获取理发师
			var _this = this;

			this.reqUrl = R.interfaces.service.validate;
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
					
					var fairername = oThis.attr('fairername');
					var fairerid = oThis.attr('id');

					oTarget.val(fairername);
					oTarget.attr('fairername', fairername);
					oTarget.attr('pkfairer', fairerid);

				}
			});

		},
		toadyDate: function() {

			var oDate = new Date();
			var todayBegin = oDate.getFullYear() + '-' + toDouble(oDate.getMonth()+1) + '-' + toDouble(oDate.getDate()) + ' ' + '00:00';
			var todayEnd = oDate.getFullYear() + '-' + toDouble(oDate.getMonth()+1) + '-' + toDouble(oDate.getDate()) + ' ' + '24:00';

			var json = {
				begintime: todayBegin,
				endtime: todayEnd
			};

			return json;

		},
		reqPayList: function(opts) {

			var _this = this;
			var tpl = require("../../../tpl/shop/user/list");

			opts = opts || {};

			this.reqUrl = R.interfaces.member.queryPayList;

			this.reqParam = {
				begintime: opts.begintime || '',
				endtime: opts.endtime || '',
				pagesize: opts.pagesize || this.defaultPagesize,
				curpage: opts.curpage || this.orderCurpage
			};

			this.req(function(data){
				
				var fenyeCount = Math.ceil(data.data.total/_this.defaultPagesize);

				if(_this.orderCurpage >= fenyeCount){
					_this.loadMore.hide();

					if(data.data.list == 0){
						oTip.say('暂无订单');
					}

				}else{
					_this.loadMore.show();
				}

				for(var i=0;i<data.data.list.length;i++){
					var index = _this.getShort();
					var dd = data.data.list[i];

					_this.render(_this.oPayUl.eq(index), tpl, dd, 'append');
				}

				$('[sum-list]').each(function(i){
					_this.getListPrice($('[sum-list]').eq(i));
				});

			}, function(data){
				oTip.say(data.msg);
			});

		},
		reqReserveList: function(opts) {

			var _this = this;
			var tpl = require("../../../tpl/shop/user/reserveList");

			opts = opts || {};
			
			this.reqUrl = R.interfaces.member.queryReserveList;

			this.reqParam = {
				begintime: opts.begintime || '',
				endtime: opts.endtime || '',
				pagesize: opts.pagesize || this.defaultPagesize,
				curpage: opts.curpage || this.appointCurpage
			};

			this.req(function(data) {

				var fenyeCount = Math.ceil(data.data.total/_this.defaultPagesize);

				if(_this.appointCurpage >= fenyeCount){
					_this.loadMore.hide();

					if(data.data.list == 0){
						oTip.say('暂无预约单');
					}

				}else{
					_this.loadMore.show();
				}

				for(var i=0;i<data.data.list.length;i++){
					var index = _this.getShort();
					var dd = data.data.list[i];
					_this.render(_this.oPayUl.eq(index), tpl, dd, 'append');
				}

			}, function(data){
				oTip.say(data.msg);
			});

		},
		loadMoreOrder: function() {

			this.orderCurpage++;

			var begintime = $('[search=order-begintime]').val();
			var endtime = $('[search=order-endtime]').val();

			if(begintime == '' && endtime == ''){
				this.reqPayList({
					curpage: this.orderCurpage
				});
			}else{
				this.reqPayList({
					begintime: begintime,
					endtime: endtime,
					curpage: this.orderCurpage
				});
			}

		},
		loadMoreAppoint: function() {

			this.appointCurpage++;

			var begintime = $('[search=appoint-begintime]').val();
			var endtime = $('[search=appoint-endtime]').val();

			if(begintime == '' && endtime == ''){
				this.reqReserveList({
					curpage: this.appointCurpage
				});
			}else{
				this.reqReserveList({
					begintime: begintime,
					endtime: endtime,
					curpage: this.appointCurpage
				});
			}

		},
		cancelReserve: function() {

			var _this = this;
			var aid = this.cancelBtn.attr('aid');
			var reservelList = this.cancelBtn.parents('[reserve-list]');

			this.reqUrl = R.interfaces.member.cancelReserve;
			this.reqParam = {
				pkOrder: aid
			};
			this.req(function(data) {
				_this.oConfirmCancelBox.close();
				oTip.say(data.msg);
				reservelList.fadeOut();
			}, function(data) {
				_this.oConfirmCancelBox.close();
				oTip.say(data.msg);
			});

		},
		createOrder: function() {

			var _this = this;
			var aid = this.createBtn.attr('aid');
			var reservelList = this.createBtn.parents('[reserve-list]');

			this.reqUrl = R.interfaces.member.createOrder;
			this.reqParam = {
				pkOrder: aid
			};
			this.req(function(data) {
				_this.oConfirmCreateBox.close();
				oTip.say(data.msg);
				reservelList.fadeOut();
			}, function(data) {
				_this.oConfirmCreateBox.close();
				oTip.say(data.msg);
			});

		},
		sum: function(oBtn) {

			var aid = oBtn.attr('aid');
			var price = oBtn.attr('price');
			var discount = oBtn.attr('discount');
			var paymode = oBtn.attr('paymode');
			var nowList = oBtn.parents('[sum-list]');
			var arr = this.getOtherPay(oBtn);

			if(price >= 0) {
				this.reqSum(aid, price, arr, discount, paymode, nowList);
			} else if(price < 0) {
				oTip.say('优惠金额不能大于服务金额');
			}else{
				oTip.say('价格输入有误');
			}

		},
		getOtherPay: function(oThis) {

			//获取附加项目
			var aOtherPay = oThis.parents('[sum-list]').find('[otherpay]');

			var arrAll = [];
			var _this = this;
			var result;

			aOtherPay.each(function(i){

				result = getSingle(aOtherPay.eq(i));

				if(result) {
					arrAll.push(result);
				}
			});

			function getSingle(oPrice) {

				var param = {};
				
				param.additionmoney = oPrice.val();
				param.additionname = oPrice.attr('additionname');
				param.additionmoney = param.additionmoney ? param.additionmoney : 0;
				param.additionmoney = !isNaN(param.additionmoney) ? param.additionmoney : 0;
				param.pkAddition = oPrice.attr('pkAddition');
				param.pkDetail = oPrice.attr('pkDetail');

				var arr = [];
				var oNowPepole;
				var israte;
				var awardmoney;
				var fairername;
				var awardname;
				var pkFairer;
				var param2;
				var aNowPepole = oPrice.parents('[other-list-wrap]').find('[other-list]');

				aNowPepole.each(function(i){

					oNowPepole = aNowPepole.eq(i);
					pkFairer = oNowPepole.attr('pkfairer');
					israte = oNowPepole.attr('israte');
					awardmoney = oNowPepole.attr('awardmoney');
					if(israte == 2 && !param.pkDetail) {
						awardmoney = awardmoney/100;
					}else{
						awardmoney = awardmoney;
					}
					
					fairername = oNowPepole.attr('fairername');
					awardname = oNowPepole.attr('awardname');

					if(pkFairer){
						param2 = {};
						param2.pkFairer = pkFairer;
						param2.israte = israte;
						param2.awardmoney = awardmoney;
						param2.fairername = fairername;
						param2.awardname = awardname;
						arr.push(param2);
					}
				});	

				if(param.additionmoney && param.pkAddition && arr.length) {
					param.awardsplit = arr;
					return param;
				} else {
					return;
				}

			}
			
			return arrAll;

		},
		reqSum: function(aid, price, addition, discount, paymode, nowList) {
			
			var _this = this;
			var pid = nowList.attr('pid');
			var productlist = nowList.data('dosage');

			//结算请求
			this.reqUrl = R.interfaces.member.sum;
			this.reqParam = {
				pkOrder: aid, 
				ordermoney: price,
				discount: discount,
				paymode: paymode,
				addition: JSON.stringify(addition),
				productlist: JSON.stringify(productlist)
			};

			this.req(function(data){

				_this.oConfirmSumBox.close();
				oTip.say(data.msg);
				// _this.reduce();
				nowList.fadeOut();

			}, function(data){

				_this.oConfirmSumBox.close();
				oTip.say(data.msg);

			});

		},
		getListPrice: function(oList) {

			//获取每一列价格
			var allItem = oList.find('[sum-item]');
			var oBase = oList.find('[base]');
			var nBase = parseInt( (oBase.html() || 0) );
			var oSum = oList.find('[sum-all]');
			var oSumBtn = oList.find('[sum-btn]');

			var count = 0;
			var sinPrice;
			var discount = $.trim(oList.find('[discount]').val()) || 0;
			var paymode = oList.find('[paymode]').val();

			allItem.each(function(i){

				sinPrice = allItem.eq(i).val();
				sinPrice = sinPrice ? sinPrice : 0;
				sinPrice = parseInt(sinPrice);
				count += sinPrice;

			});

			count += nBase;

			count -= discount;

			if(!isNaN(count)) {

				oSum.html(count);
				oSumBtn.attr('price', count);
				oSumBtn.attr('discount', discount);
				oSumBtn.attr('paymode', paymode);

			}else {

				oSum.html('');
				oSumBtn.attr('price', '');
				oSumBtn.attr('discount', '');
				oSumBtn.attr('paymode', '');

			}

		},
		getShort: function() {

			var index = 0;
			var ih = this.oPayUl.eq(index).height();

			for(var i=1; i<this.oPayUl.length; i++){

				if(this.oPayUl.eq(i).height() < ih){
					index = i;
					ih = this.oPayUl.eq(i).height();
				}

			}
			
			return index;

		},
		reduce: function() {

			//订单reduce;
			var nowNum = parseInt(this.oPayNum.html());
			nowNum -= 1;
			this.oPayNum.html(nowNum);

			if(nowNum == 0) {
				this.oPayWrap.html('<div class="ba-tc">暂无下单数据</div>');
			}

		},
		search: function(sValue) {
			
			/*
				如果是完成的手机号则请求接口,如果有该用户则直接跳到用户信息页
				模糊搜索的跳到搜索列表页
			*/

			var _this = this;

			this.reqUrl = R.interfaces.member.query;
			this.reqParam = {
				likecontent: sValue
			};
			this.req(function(data){

				var oList = data.data.list;
				var uid;

				if(oList && oList.length == 1) {

					uid = oList[0].pkCustomer;
					window.location = R.route['user/add'].url + '?uid=' + uid;
				} else {

					//window.location = _this.searchLink + '?q=' + sValue;
					_this.reqUrl = R.interfaces.member.query;
					_this.reqParam = {
						pagesize: 10,
						likecontent: sValue
					};
					_this.req(function(data){
						if(data.data.total == 0){
							oTip.say('无搜索结果');
						}else{
							window.location = _this.searchLink + '?q=' + sValue;
						}
					});
				}

			});

		},
		queryScan: function() {

			var _this = this;

			this.reqUrl = R.interfaces.member.queryScan;
			this.req(function(data) {
				// console.log(data);
				var uid;

				if(data.data.list.length == 1){
					uid = data.data.list[0].pkCustomer;
					window.location = R.route['user/add'].url + '?uid=' + uid;
				}else if(data.data.list.length > 1){
					// console.log(data);
					window.location = _this.searchLink + '?type=scan';	
				}else{
					oTip.say('暂无扫描下单会员');
				}
			}, function(data) {
				oTip.say.data.msg;
			});

		},
		queryComboGoods: function(nowList) {
			var _this = this;

			this.reqUrl = R.interfaces.goods.platCombo;
			this.reqParam = {pkCombo: nowList.attr('pkCombo')};

			this.req(function(data) {
				
				var goodsArr = data.data.products;

				_this.dosageGoodsArr = [];

				if(nowList.data('dosage')){
					// console.log(nowList.data('dosage'));
					$('.dosage-dialog ul').html('');

					for(var i=0;i<nowList.data('dosage').length;i++){
						var oLi = $('<li dosage-list>'+
					            '<select class="form-control" combo-goods>'+
					                '<option value="">请选择商品</option>'+
					            '</select>'+
					            '<span class="multiply">X</span>'+
					            '<input class="form-control" type="text" combo-goods-num>'+
					            '<span class="unit" combo-goods-unit></span>'+
					            '<div class="func-btn" dosage-handle>'+
					                '<button class="btn btn-primary ba-fl ba-mr-5" sub-add>'+
					                    '<span class="fa fa-plus"></span>'+
					                '</button>'+
					                '<button class="btn btn-danger ba-fl" sub-reduce>'+
					                    '<span class="fa fa-minus"></span>'+
					                '</button>'+
					            '</div>'+
					        '</li>');

						$('.dosage-dialog ul').append(oLi);

						for(var j=0;j<goodsArr.length;j++){
							var option = $('<option unit="'+goodsArr[j].productunit+'" value="'+goodsArr[j].pkProduct+'">'+goodsArr[j].productname+'</option>');
							oLi.find('[combo-goods]').append(option);
							oLi.find('[combo-goods]').val(nowList.data('dosage')[i].pkProduct);
							oLi.find('[combo-goods-num]').val(nowList.data('dosage')[i].productnum);
							oLi.find('[combo-goods-unit]').html(nowList.data('dosage')[i].productunit);
						}
						_this.dosageGoodsArr.push(nowList.data('dosage')[i].pkProduct);
					}
				}else{
					$('[dosage-list]').eq(0).siblings().remove();
					$('[combo-goods]').html('<option value="">请选择商品</option>');
					$('[combo-goods-num]').val('');
					$('[combo-goods-unit]').html('');

					for(var i=0;i<goodsArr.length;i++) {
						var option = $('<option unit="'+goodsArr[i].productunit+'" value="'+goodsArr[i].pkProduct+'">'+goodsArr[i].productname+'</option>');
						$('[combo-goods]').append(option);
					}
				}

			}, function(data) {
				oTip.say(data.msg);
			});
		},
		dosageAdd: function(row) {
			var allReday = true;
			var goodsCombo = $('[combo-goods]');
			var goodsComboNum = $('[combo-goods-num]');
			var re = /^(?!(0[0-9]{0,}$))[0-9]{1,}[.]{0,}[0-9]{0,}$/;

			goodsCombo.each(function(){
				if($(this).val() == ''){
					allReday = false;
					return;
				}
			});

			goodsComboNum.each(function(){
				if($(this).val() == '' || !re.test($(this).val())){
					allReday = false;
					return;
				}
			});

			if(allReday){
				var newRow = row.clone(true);
				newRow.find('[dosage-handle]').hide();
				newRow.find('[combo-goods]').val('');
				newRow.find('[combo-goods-num]').val('');
				newRow.find('[combo-goods-unit]').html('');
				row.parent().append(newRow);
				if(this.dosageGoodsArr.indexOf(row.find('select').val()) == -1){
					this.dosageGoodsArr.push(row.find('select').val());
				}
			}else{
				oTip.say('请选择套餐商品并正确填写用量');
			}

		},
		dosageReduce: function(row) {
			if(row.parent().children().length == 1){
				oTip.say('至少保留一行');
			}else{
				// this.dosageGoodsArr.push(row.find('select').val());
				var removeVal = row.find('select').val(); 
				for(var i=0;i<this.dosageGoodsArr.length;i++){
					if(this.dosageGoodsArr[i] == removeVal){
						this.dosageGoodsArr.splice(i, 1);
					}
				}
				row.remove();
			}
		},
		checkDosage: function(nowSelect) {
			var nowVal = nowSelect.val();
			
			for(var i=0;i<this.dosageGoodsArr.length;i++){
				if(this.dosageGoodsArr[i] == nowVal){
					oTip.say('您已添加过此商品');
					nowSelect.val('');
				}
			}
		},
		saveDosage: function(box, pid) {

			var dosageGoodsArr = [];
			var dosageList = box.find('[dosage-list]');
			var re = /^(?!(0[0-9]{0,}$))[0-9]{1,}[.]{0,}[0-9]{0,}$/;

			dosageList.each(function(){
				var dosageItem = {};
				if($(this).find('[combo-goods]').val() && $(this).find('[combo-goods-num]').val() && re.test($(this).find('[combo-goods-num]').val())){
					dosageItem.pkProduct = $(this).find('[combo-goods]').val();
					dosageItem.productnum = $(this).find('[combo-goods-num]').val();
					dosageItem.productunit = $(this).find('[combo-goods-unit]').html();
					dosageItem.productname = $(this).find('[combo-goods] option:selected').text();
					dosageGoodsArr.push(dosageItem);
				}
			});
			if(dosageGoodsArr.length){
				$('li[pid='+pid+']').data('dosage', dosageGoodsArr);
				this.dosageBox.close();
			}else{
				oTip.say('请选择套餐商品并正确填写用量');
			}
		}
	});

	var oIndex = new Index();

});
