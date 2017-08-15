/**
 *description:理发师业绩
 *author:fanwei
 *date:2015/1/25
 */
define(function(require, exports, module) {
	
	var global = require("../../../driver/global");
	var Calendar = require('../../../widget/form/calendar');
	var oTplList = require('../../../tpl/shop/performance/hairList');
	var fenye = require('../../../widget/dom/fenye');
	var oTip = require("../../../widget/dom/tip");

	var Hairer = R.Class.create(R.util, {

		initialize: function() {
			
			this.nowCondition = {
				pagesize: 10,
				begintime: '',
				endtime: ''
			};
			this.oWrap = $('[search-wrap]');
			this.oListWrap = $('[data-ele = data-wrap]');	
			this.events();
			this.showCalendar();
			this.search();	

		},
		events: function() {
			
			var _this = this;

			this.oWrap.on('click', '[search-btn]', function(){

				if($('[search=begintime]').val() && $('[search=endtime]').val()) {
					if($('[search=begintime]').val() > $('[search=endtime]').val()) {
						oTip.say('开始时间不能大于结束时间');
						return;
					}
				}

				_this.search();
			});
			
		},
		showCalendar: function() {

			var _this = this;

			var oCalendar = new Calendar({
				ele: '[calendar]',
				defaultHour: '00',
				defaultMinutes: '00',
				defaultSecond: '00',
				onSetDate: function(nowTime, b) {
					
					var oInput = $(this.inpE);
					var condition = oInput.attr('search');
					_this.nowCondition[condition] = nowTime;

				}
			});

		},
		search: function() {

			var _this = this;

			this.nowCondition.fairername = $('[fairername]').val();

			if(!this.oPage) {

				this.oPage = new fenye(R.interfaces.performance.getHairList, oTplList, this.nowCondition);

			} else {

				this.oPage.refresh(this.nowCondition);

			}

		}

	});

	var oHairer = new Hairer();

});
