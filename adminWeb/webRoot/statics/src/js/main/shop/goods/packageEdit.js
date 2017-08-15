/**
 *description:套餐修改
 *author:fanwei
 *date:2015/3/25
 */
define(function(require, exports, module) {
	
	var global = require("../../../driver/global");
	var ajaxForm = require('../../../widget/form/ajaxForm');
	var bodyParse = require('../../../util/http/bodyParse');
	var oTip = require('../../../widget/dom/tip');
	var oTplTicheng = require('../../../tpl/shop/goods/ticheng');

	var PackageEdit = R.Class.create(R.util, {

		initialize: function() {
			
			this.oWrap = $('[awards-wrap]');
			this.oSubAddBtn = $('[sub-add]');
			this.oReduceBtn = $('[sub-reduce]');
			this.oFuncBtn = $('[func-btn]');
			this.removeArr = []; //删除的集合
			this.pageInfo = bodyParse();
			this.pid = this.pageInfo.pkShopCombo;
			this.initAjaxForm();
			this.events();
		},
		events: function() {

			var _this = this;

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
		initAjaxForm: function() {

			var _this = this;

			this.packForm = new ajaxForm({

				subUrl: R.interfaces.goods.goodsEdit,
				fnSumbit: function( data ) {

					var result = _this.getAwards();
					result = result.concat(_this.removeArr);
					data.currentmoney = data.shopmoney;
					data.pkShopCombo = _this.pid;
					data.awards = JSON.stringify(result);

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
