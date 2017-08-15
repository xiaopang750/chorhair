/**
 *description:table编辑组件
 *author:fanwei
 *date:2015/3/25
 */
define(function(require, exports, module){
	
	var global = require("../../driver/global");

	var TableEdit = R.Class.create(R.util, {

		initialize: function(opts) {

			this.events();

		},
		events: function() {

			var _this = this;


		},
		beginEdit: function(oTr) {

			var aEdit = oTr.find('[can-edit]');
			var oEdit;
			var sValue;
			var _this = this;

			aEdit.each(function(i){

				oEdit = aEdit.eq(i);
				sValue = oEdit.text();
				_this.createEditInput(oEdit, sValue);

			});

		},
		endEdit: function(oTr) {

			var aEdit = oTr.find('[can-edit]');
			var oEdit;
			var nowEditInput;
			var sValue;
			var _this = this;

			aEdit.each(function(i){

				oEdit = aEdit.eq(i);
				nowEditInput = oEdit.find('[edit-input]');
				sValue = nowEditInput.val();
				oEdit.html(sValue);

			});

		},
		createEditInput: function(oParent, sValue){

			oParent.css({
				position: 'relative'
			});

			var oInput = $('<input type="text" class="form-control" edit-input>');

			sValue = sValue.replace(/\s+/gi, '');

			oInput.val(sValue);

			var height = oParent.outerHeight() + 'px';

			oInput.css({
				position: 'absolute',
				width: '100%',
				height: height,
				left: 0,
				top: 0,
				zIndex: 2
			});

			oParent.append(oInput);

			this.tmpValue(oInput, oParent);
		},
		tmpValue: function(oInput, oTarget) {

			//缓存当前数据
			oInput.on('keyup', save);

			oInput.on('keypress', save);

			function save() {
				oTarget.attr('tmpValue', oInput.val());	
			}
			
		}

	});	

	module.exports = TableEdit;

}); 