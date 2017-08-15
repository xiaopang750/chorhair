/**
 *description:档案管理右侧表单
 *author:fanwei
 *date:2015/3/25
 */
define(function(require, exports, module) {
	
	var global = require("../../driver/global");
	var Dialog = require('../../widget/dom/dialog');
	var oTip = require('../../widget/dom/tip');
	var TableEdit = require('../../widget/dom/tableEdit');
	

	var RightTable = R.Class.create(R.util, {

		initialize: function(opts) {

			//dom
			this.oWrap = opts.oWrap || null;
			this.oTplContent = opts.oTplContent || null;
			this.oTplList = opts.oTplList || null;
			this.shop = $('[shop]');

			//property
			this.sListWrapName = opts.sListWrapName || '';
			this.blankDataTemplate = {
				data: {
					pricename: '',
					price: '',
					list: []
				}
			};
			this.blankRowData = {
				list: [
					{	
						name: '',
						percent: ''
					}
				]
			};
			this.awardsName = opts.awardsName || '';
			this.onAfterAdd = opts.onAfterAdd || null;
			this.onAfterEdit = opts.onAfterEdit || null;
			this.onFreshNew = opts.onFreshNew || null;

			//request-url
			this.getListUrl = opts.getListUrl || '';
			this.disableUrl = opts.disableUrl || '';
			this.addUrl = opts.addUrl || '';
			this.editUrl = opts.editUrl || '';

			//func
			this.initTableEdit();
			this.events();

		},
		initTableEdit: function() {

			this.oTableEdit = new TableEdit();
		},
		events: function(){

			var _this = this;

			this.oWrap.on('click', '[data-list-plus]', function(){

				_this.addRow();

			});

			this.oWrap.on('click', '[disable]', function(){

				_this.disable($(this));

			});

			this.oWrap.on('click', '[edit]', function(){

				_this.editRow($(this));

			});

			this.oWrap.on('click', '[confirm-save]', function(){

				_this.save();

			});

			this.oWrap.on('change', '[sub-name=israte]', function(){
				_this.changeCheck($(this));
			});

		},
		getListData: function(param, cb) {

			var _this = this;

			this.reqUrl = this.getListUrl;
			this.reqParam = param;
			this.req(function(data){

				cb && cb.call(_this, data);
				_this.oDataList = $(_this.sListWrapName);

			}, function(data) {
				cb && cb.call(_this, data);
				_this.oDataList = $(_this.sListWrapName);
			});
			
		},
		showList: function(data) {

			this.render(this.oWrap, this.oTplContent, data);
			this.oDataList = $(this.sListWrapName);

		},
		resort: function() {

			//重排序号
			var oSort;
			var _this = this;

			this.aSort = $('[list-sort]');
			this.aSort.each(function(i){

				oSort = _this.aSort.eq(i);
				oSort.html(parseInt(i) + 1);

			});

		},
		addGroup: function() {

			//添加一个组
			this.showList(this.blankDataTemplate);

		},
		addRow: function() {

			//添加一行
			var oList = this.render(this.oDataList, this.oTplList, this.blankRowData, 'append');
			var oAddAfterNeedId= oList.find('[addAfterNeedId]');
			oAddAfterNeedId.hide();
			this.resort();

		},
		disable: function(oThis){

			//启用/禁用
			var aid = oThis.attr('aid');
			var status = oThis.attr('disable');
			var destParam = {
				pkFaireraward: aid
			};
			var destMsg = '';
			var destStr = '';

			if(status == 'Y') {
				destParam.isvalidate = 'N';
				destMsg = '禁用成功';
				destStr = '启用';
			} else {
				destParam.isvalidate = 'Y';
				destMsg = '启用成功';
				destStr = '禁用';
			}

			this.reqUrl = this.disableUrl;
			this.reqParam = destParam;
			this.req(function(data){

				oThis.html(destStr);
				oThis.attr('disable', destParam.isvalidate);
				oTip.say(destMsg);

			}, function(data){

				oTip.say(data.msg);

			});

		},
		checkEdit: function(oTr){

			//验证输入项
			var aSubName = oTr.find('[sub-name]');
			var oSubName;
			var sName;
			var re;
			var tip;
			var msg;
			var result;
			var allResult;
			var num;
			var isIgnore;

			allResult = true;
			num = aSubName.length;

			for (var i=0; i<num; i++) {

				oSubName = aSubName.eq(i);
				re = new RegExp(oSubName.attr('re'), 'gi');

				if(oSubName[0].tagName == 'SELECT') {
					sName = oSubName.val();
				} else {
					sName = oSubName.attr('tmpvalue') || oSubName.text().replace(/\s+/gi, '');
				}

				tip = oSubName.attr('tip');
				msg = oSubName.attr('msg');
				isIgnore = oSubName.attr('ignorecheck') == 'yes' ? true : false;;
				
				if(isIgnore) {
					continue;
				}

				if(!sName) {
					
					oTip.say(tip);
					allResult = false;
					break;
				}	

				if(!re.test(sName)) {
					oTip.say(msg);
					allResult = false;
					break;
				}

			}

			return allResult;
		},
		editRow: function(oThis){

			//编辑
			var status = oThis.text();
			var oTr = oThis.parents('tr');
			var result;

			if(status == '编辑') {

				this.oTableEdit.beginEdit(oTr);
				oThis.html('确定');

			} else {
				
				result = this.checkEdit(oTr);

				if(result) {
					this.oTableEdit.endEdit(oTr);
					oThis.html('编辑');
				}
			}
		},
		save: function(){

			//保存
			var param = this.getData(this.operateParam);
			var _this = this;

			if(!param) return;

			var reqUrl = this.nowType == 'edit' ? this.editUrl : this.addUrl;
			this.reqUrl = reqUrl;
			this.reqParam = param;

			if(this.shop.length){
				this.reqParam.pkShop = this.shop.children().eq(this.shop[0].selectedIndex).attr('pkkey');
			}

			this.req(function(data){

				oTip.say(data.msg);

				if(_this.nowType != 'edit') {
					//add
					_this.oWrap.html('');
					_this.onAfterAdd && _this.onAfterAdd.call(_this, data.data);
				} else {
					//edit
					$('[pid]').each(function() {
						if($(this).attr('pid') == param.pkPrice){
							$(this).attr('price', param.price);
							$(this).attr('pname', param.servicerank);
							$(this).find('[look]').html(param.servicerank);
						}
						if($(this).attr('pid') == param.pkAddition){
							$(this).attr('pname', param.additionname);
							$(this).find('[look]').html(param.additionname);
						}
						if($(this).attr('pid') == param.pkShopGroup){
							$(this).attr('pname', param.groupname);
							$(this).find('[look]').html(param.groupname);
						}
					});

					_this.refreshNew(data.data);
					_this.onAfterEdit && _this.onAfterEdit.call(_this,data.data);
				}

			}, function(data){

				oTip.say(data.msg);

			});

		},
		getData: function(param) {

			//验证所有编辑行是否完成
			var aEditInput = $('[edit-input]');
			if(aEditInput.length) {
				oTip.say('请完成编辑行');
				return;
			}

			//获取保存的数据 加验证
			var aOrgData = this.oWrap.find('[org-sub-name]');
			var oOrgData;
			var name;
			var re;
			var sVal;
			var allResult;
			var msg;
			var tip;
			var result;
			var num;

			allResult = true;
			num = aOrgData.length;

			for (var i=0; i<num; i++) {

				oOrgData = aOrgData.eq(i);
				msg = oOrgData.attr('msg');
				tip = oOrgData.attr('tip');
				name = oOrgData.attr('org-sub-name');
				re = new RegExp(oOrgData.attr('re'), 'gi');
				sVal = oOrgData.val();
				result = re.test(sVal);

				if(!sVal) {
					oTip.say(tip);
					allResult = false;
					break;
				}

				if(!result) {
					oTip.say(msg);
					allResult = false;
					break;
				}

				param[name] = sVal;

			}

			//验证提交列表
			var aSubList = this.oWrap.find('[sub-name-list]');
			var oSubList;
			var aSub;
			var oSub;
			var arr = [];
			var num2;
			var num3;

			num2 = aSubList.length;

			for (var i=0; i<num2; i++) {

				var param2 = {};
				oSubList = aSubList.eq(i);
				aSub = oSubList.find('[sub-name]');
				num3 = aSub.length;

				for (var k=0; k<num3; k++) {

					var sVal;

					oSub = aSub.eq(k);

					if(oSub[0].tagName == 'SELECT') {
						sVal = oSub.val();
					} else {
						sVal = oSub.text().replace(/\s+/, '');
					}

					var name = oSub.attr('sub-name');
					var ignorecheck = oSub.attr('ignorecheck') == 'yes' ? true : false;

					if(!sVal && !ignorecheck) {
						oTip.say('请完整填写输入项');
						allResult = false;
						break;
					}
					
					param2[name] = sVal;
				}

				arr.push(param2);

			}

			param[this.awardsName] = JSON.stringify(arr);

			if(allResult) {
				return param;
			} else {
				return false;
			}

		},
		changeCheck: function(oThis){
			//更换验证
			var status = oThis.val();
			var oTarget = oThis.parents('[sub-name-list]').find('[sub-name = awardmoney]');
			var re;
			var msg;
			var tip;
			var ignorecheck;

			if(status == 1) {
				re = '^\\d+$';
				tip = '固定额度不能为空';
				msg = '固定额度只能为数字';
				ignorecheck = 'no';
			} else if(status == 2) {
				re = '^(([1-9]\\d?)|100)$';
				tip = '提成比例不能为空';
				msg = '提成比例为100以内的数字';
				ignorecheck = 'no';
			} else {
				re = '';
				tip = '';
				msg = '';
				ignorecheck = 'yes';
			}

			oTarget.attr('re', re);
			oTarget.attr('tip', tip);
			oTarget.attr('msg', msg);
			oTarget.attr('ignorecheck', ignorecheck);
		},
		refreshNew: function(arr){

			var isArray = Object.prototype.toString.apply(arr) == "[object Array]" ? true : false;

			if(!isArray) {
				return;
			}

			//新保存的后台返回新增的id,提供禁用功能
			var aAddAfterNeedId = this.oWrap.find('[addAfterNeedId]');
			var i,
				k,
				num,
				num2,
				aid,
				oAddAfterNeedId,
				arrNew;

			num = aAddAfterNeedId.length;
			arrNew = [];
			for (i=0; i<num; i++) {
				oAddAfterNeedId = aAddAfterNeedId.eq(i);			
				aid = oAddAfterNeedId.attr('aid');

				if(!aid) {
					arrNew.push(oAddAfterNeedId);
				}
			}

			num2 = arrNew.length;
			for (k=0; k<num2; k++) {
				arrNew[k].attr('aid', arr[k]);
				this.onFreshNew && this.onFreshNew.call(this, arrNew[k], arr[k]);
				arrNew[k].show();
			}	
		}	


	});	

	module.exports = RightTable;

});
