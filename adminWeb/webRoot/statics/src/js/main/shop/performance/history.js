/** *description:历史订单 *author:fanwei *date:2015/3/2 */define(function(require, exports, module) {    var global = require("../../../driver/global");    var Calendar = require('../../../widget/form/calendar');    var oTplList = require('../../../tpl/shop/performance/history');    var fenye = require('../../../widget/dom/fenye');    var oTip = require("../../../widget/dom/tip");    var Hairer = R.Class.create(R.util, {        initialize: function() {            this.oSearchBtn = $('[search-btn]');            this.aCondition = $('[search]');        	this.defaultParam = {        		pagesize: 10        	};            this.showCalendar();        	this.showPage();            this.events();        },        events: function() {            var _this = this;            this.oSearchBtn.on('click', function(){                if($('[search=begintime]').val() && $('[search=endtime]').val()) {                    if($('[search=begintime]').val() > $('[search=endtime]').val()) {                        oTip.say('开始时间不能大于结束时间');                        return;                    }                }                _this.search();            });         },        search: function() {            var _this = this;            var oCondition;            var sConditionName;            var sConditionValue;            this.aCondition.each(function(i){                oCondition = _this.aCondition.eq(i);                sConditionName = oCondition.attr('search');                sConditionValue = oCondition.val();                _this.defaultParam[sConditionName] = sConditionValue;            });            _this.defaultParam.orderno = $('[orderno]').val();            _this.oPage.refresh(_this.defaultParam);        },        showPage: function() {			var _this = this;			this.oPage = new fenye(R.interfaces.performance.getHistoryList, oTplList, this.defaultParam);		},        showCalendar: function() {            var _this = this;            var oCalendar = new Calendar({                ele: '[calendar]',                format: 'yyyy-MM-dd HH:mm:ss',                defaultHour: '00',                defaultMinutes: '00',                defaultSecond: '00'            });        }    });    var oHairer = new Hairer();});