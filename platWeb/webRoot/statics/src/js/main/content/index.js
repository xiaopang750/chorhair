/**
 *description:内容管理列表
 *author:fanwei
 *date:2015/1/31
 */
define(function(require, exports, module) {
	
	var global = require("../../driver/global");
	var Dialog = require("../../widget/dom/dialog");
	var oTip = require("../../widget/dom/tip");
	var fenye = require('../../widget/dom/fenye');
	var oTplList = require('../../tpl/content/contentList');
	var Calendar = require('../../widget/form/calendar');

	var ContentList = R.Class.create(R.util, {

		initialize: function() {

			this.oWrap = $('[list-wrap]');

			this.showCalendar();
			this.searchContent();
			this.initRemoveBox();
			this.events();

		},
		showCalendar: function() {

			var _this = this;

			var oCalendar = new Calendar({
				ele: '[calendar]',
				format: 'yyyy-MM-dd'
			});

		},
		searchContent: function() {

			var begintime = $('[search=begintime]').val();
			var endtime = $('[search=endtime]').val();

			if(begintime && endtime  && begintime > endtime){
				oTip.say('开始时间不能大于结束时间');
				return;
			}

			var param = {
				pagesize: 10,
				begintime: begintime,
				endtime: endtime
			};

			if(!this.oPage){
				this.oPage = new fenye(R.interfaces.content.list, oTplList, param);
			}else{
				this.oPage.refresh(param, R.interfaces.content.list, oTplList);
			}

		},
		initRemoveBox: function() {

			this.oRemovBox = new Dialog({
				content: '确认取消发布么?'
			});
		},
		events: function() {
				
			var _this = this;
			
			$('[search-content-btn]').on('click', function(){
				_this.searchContent();
			});

			this.oWrap.on('click', '[remove]', function(){

				/*
					001: 发布
					002: 未发布
				*/
				var status,
					tip;

				_this.oRemoveBtn = $(this);
				_this.oEdit = _this.oRemoveBtn.parents('[data-list]').find('[edit]');
				status = _this.oRemoveBtn.attr('isCanceled');
				_this.removeDestStatus = status == '001' ? '002' : '001';
				_this.destshow = status == '001' ? 'inline-block' : 'none';
				_this.removeDestStr = _this.removeDestStatus == '001' ? '取消发布' : '发布';	
				_this.removeTipStr = _this.removeDestStatus == '001' ? '发布' : '取消发布';
				_this.removeId = $(this).attr('cid');
				tip = '确认' + _this.removeTipStr + '么?';

				_this.oRemovBox.boxContent().html(tip);
				_this.oRemovBox.show();

			});

			this.oRemovBox.onConfirm = function() {

				_this.reqUrl = R.interfaces.content.cancel;
				_this.reqParam = {
					pkContent: _this.removeId,
					status: _this.removeDestStatus
				};
				_this.req(function(data){

					oTip.say(data.msg);
					_this.oRemovBox.close();
					_this.oRemoveBtn.html(_this.removeDestStr);
					_this.oRemoveBtn.attr('isCanceled', _this.removeDestStatus);
					_this.oEdit[0].style.display = _this.destshow;


				}, function(data){
					oTip.say(data.msg);
				});

			};
		}

	});

	var oContentList = new ContentList();

});
