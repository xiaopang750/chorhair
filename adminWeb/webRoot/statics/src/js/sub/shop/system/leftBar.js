/**
 *description:档案管理左侧导航
 *author:fanwei
 *date:2015/3/24
 */
define(function(require, exports, module) {
	
	var global = require("../../../driver/global");
	var Dialog = require('../../../widget/dom/dialog');
	var oTplAddBox = require('../../../tpl/shop/system/addGroup');
	var oTip = require('../../../widget/dom/tip');

	var LeftBar = R.Class.create(R.util, {

		initialize: function(opts) {
			
			var opts = opts || {};

			//dom
			this.oWrap = opts.oWrap || null;
			this.oGroupAdd = opts.oGroupAdd || null;

			//property
			this.activeClassName = opts.activeClassName || 'active';
			this.type = opts.type || 'multi';
			this.maxGroup = 10;

			//tpl
			this.barTpl = opts.barTpl || null;
			this.barSubTpl = opts.barSubTpl || null;

			//request url
			this.lookGroupUrl = opts.lookGroupUrl || '';
			this.lookSubUrl = opts.lookSubUrl || '';
			this.addGroupUrl = opts.addGroupUrl || '';
			this.editGroupUrl = opts.editGroupUrl || '';

			//events;
			this.onSubAdd = opts.onSubAdd || null;
			this.onLook = opts.onLook || null;
			this.onEdit = opts.onEdit || null;

			
		},
		init: function() {

			this.getGroupList();
			this.showDialog();
			this.events();

		},
		events: function() {

			var _this = this;

			//点击列表显示子集
			this.oWrap.on('click', '[list-item]', function(){

				_this.showChild($(this));

			});

			//编辑分组
			this.oWrap.on('click', '[group-eidt]', function(){
				_this.groupEidt($(this));
			});

			//show-func-item
			this.oWrap.on('mouseenter', '[hover-list]', function(){

				_this.showFuncIcon($(this));

			});

			this.oWrap.on('mouseleave', '[hover-list]', function(){

				_this.hideFuncIcon($(this));

			});

			//click
			this.oGroupAdd.on('click', function(){

				if(_this.type == 'multi') {

					var checkGroupMax = _this.checkGroupMax();
					if(!checkGroupMax) return;
					_this.showAddGroup();

				} else {

					_this.onSubAdd && _this.onSubAdd($(this));
					
				}
				
			});

			//dialog
			this.oAddDialog.onConfirm = function(){
				_this.requestGroupOperate();
			};

			this.oAddDialog.onClosed = function(){
				_this.clearGroupBox();
			};

			//禁用
			this.oWrap.on('click', '[remove]', function(){

				_this.remove($(this));

			});

			//价位新增
			this.oWrap.on('click', '[sub-add]', function(){

				_this.onSubAdd && _this.onSubAdd($(this));

			});

			//查看
			this.oWrap.on('click', '[look]', function(){

				_this.onLook && _this.onLook($(this));

			});

			//获取子集
			this.oWrap.on('click', '[get-sub]', function(){

				_this.getSub($(this));

			});

			//编辑
			this.oWrap.on('click', '[edit]', function(){
				
				_this.onEdit && _this.onEdit($(this));

			});

		},
		showDialog: function() {

			this.oAddDialog = new Dialog({
				boxTpl: oTplAddBox
			});

			this.oAddInput = this.oAddDialog.dom().find('[add-group-input]');

		},
		showChild: function(oThis){

			//显示child
			var oMainList = oThis.parents('[list-main]');
			var isShow = oMainList.hasClass(this.activeClassName);

			if(isShow) {
				oMainList.removeClass(this.activeClassName);
			} else {
				oMainList.addClass(this.activeClassName);
			}

		},
		showFuncIcon: function(oThis){

			//移入显示功能按钮
			var oFuncArea = oThis.find('[func-icon]');

			oFuncArea.show();

		},
		hideFuncIcon: function(oThis){

			//移出隐藏功能按钮
			var oFuncArea = oThis.find('[func-icon]');

			oFuncArea.hide();

		},
		showAddGroup: function(){
			//显示添加弹框
			this.oAddDialog.show();
			this.oAddDialog.gid = '';
			this.oAddInput.focus();
		},
		getGroupList: function(cb){

			var _this = this;

			//请求分组
			this.reqUrl = this.lookGroupUrl;
			this.req(function(data){

				_this.render(_this.oWrap, _this.barTpl, data);
				cb && cb();

			});
		},
		groupEidt: function(oThis){

			this.oNowGroupName = oThis.parents('[list-main]').find('[get-sub]');
			var sName = $.trim(this.oNowGroupName.text());
			var gid = oThis.attr('gid');
			this.oAddDialog.show();
			this.oAddDialog.gid = gid;
			this.oAddInput.val(sName);	
			this.oAddInput.trigger('focus');

		},
		getSub: function(oThis) {

			//根据分组查询子集
			var gid = oThis.attr('gid');
			var nowSubWrap = oThis.parents('[list-main]').find('[sub-wrap]');
			var numChild = nowSubWrap.children().length;
	
			if(numChild) return;

			var _this = this;
			this.reqUrl = this.lookSubUrl;
			this.reqParam = {
				pkShopGroup: gid
			};
			this.req(function(data){

				_this.render(nowSubWrap, _this.barSubTpl, data);

			});

		},
		requestGroupOperate: function() {

			//添加分组保存
			var sGroupName = this.oAddInput.val();
			var isPass = this.checkGroupAdd(sGroupName);
			var _this = this;
			var gid = this.oAddDialog.gid;
			var reqUrl;
			
			if(isPass) {

				reqUrl = gid ? this.editGroupUrl : this.addGroupUrl;

				this.reqUrl = reqUrl;

				this.reqParam = {
					groupname: sGroupName,
					pkShopGroup: gid
				};

				this.req(function(data){

					if(gid) {
						_this.finishEditGroup(sGroupName);
					} else {
						_this.finishAddGroup(data);
					}

				}, function(data){

					oTip.say(data.msg);

				});

			}

		},
		checkGroupAdd: function(sValue){

			//验证分组添加
			var re = /^.{1,10}$/gi;
			var result = re.test(sValue);

			if(!result) {
				oTip.say('请填写10个字以内的分组名称');
			}  

			return result;
		},
		finishAddGroup: function(data){

			//添加分组完成
			var renderData = {
				data: [
					data.data
				]
			};

			//分组添加完成
			oTip.say(data.msg);
			this.oAddDialog.close();
			this.clearGroupBox();
			this.render(this.oWrap, this.barTpl, renderData, 'append');

		},
		finishEditGroup: function(sNewName){

			this.oNowGroupName.html('<span>'+sNewName+'</span> <span class="fa fa-angle-down"></span><span class="fa fa-angle-up"></span>');
			this.oAddDialog.close();

		},
		clearGroupBox: function(){

			this.oAddInput.val('');

		},
		checkGroupMax: function() {

			var nowLen = this.oWrap.find('[list-main]').length;
			if(nowLen >= this.maxGroup) {
				oTip.say('最多不能超过' + this.maxGroup + '个分组');
				return;
			} else {
				return true;
			}

		},
		remove: function(oThis){

			//禁用
			var aid = oThis.attr('aid');
			var nowSubList = oThis.parents('[list-sub]');
			var nowMainList = oThis.parents('[list-main]');
			var isGroupRemove = !nowSubList.length
			var aChildern = oThis.parents('[list-main]').find('[list-sub]');
			
			if(isGroupRemove) {

				//组删除
				if(aChildern.length) {

					oTip.say('该组下还有价位，不能删除');

				} else {

					this.requestRemoveList(this.removeGroupUrl, {
						pk: aid
					}, function(){
						nowMainList.remove();
					});
				}

			} else {

				//价位删除
				this.requestRemoveList(this.removeGroupUrl, {
					pk: aid
				}, function(){
					nowSubList.remove();
				});

			}

		},
		requestRemoveList: function(url, param, cb) {

			//删除组，删除价位请求
			var _this = this;

			this.reqUrl = url;
			this.reqParam = param;
			this.req(function(data){

				oTip.say(data.msg);
				cb && cb(data);

			}, function(data){

				oTip.say(data.msg);

			});

		}

	});

	module.exports = LeftBar;

});
