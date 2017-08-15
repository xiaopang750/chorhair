/**
 *description:美丽记录首页
 *author:fanwei
 *date:2015/1/22
 */
define(function(require, exports, module) {
	
	var global = require("../../../driver/global");
	var Comment = require('../../../widget/dom/comment');


	require('../../../lib/touch/hammer');

	var Gallery = require('../../../widget/dom/gallery');

	var Record = R.Class.create(R.util, {

		initialize: function() {
			
			this.reqUrl = R.interfaces.record.list;

			this.pageLoading = $('[page-loading]');
			this.loadingWrap = $('[loading-wrap]');

			this.loadData();

		},
		loadData: function(){

			var _this = this;

			this.req(function(data){

				//取消Loading，展示页面
				_this.showContent(_this.pageLoading, _this.loadingWrap);
				
				for(var i=0;i<data.data.length;i++){
					data.data[i].date = {
						day: _this.formatting(data.data[i].ordertime).day,
						month: _this.formatting(data.data[i].ordertime).month,
					}
				}

				var tpl = require('../../../tpl/customer/record/index');
				var oWrap = $('.record-list-wrap ul');

				_this.render(oWrap, tpl, data);

				//图片缩放
				var oGallery = new Gallery({
					galleryWrap: $('[gallery-wrap]')
				});
				
				var comments = $('[comment-wrap]');

				comments.each(function(i){

					//展开收缩
					$(this).find('li').hide();
					$(this).find('li').eq(0).show();
					$(this).find('li').eq(1).show();

					//评论操作
					var oComment = new Comment({
						pkOrder: data.data[i].pkOrder,
						index: i
					});

				});

				//显示更多按钮
				for(var i=0;i<data.data.length;i++){

					if(data.data[i].spitslots && data.data[i].spitslots.length > 2){		
						$('[extend]').eq(i).show();
					}

				}

				_this.events();

			},function(data){
				_this.uiInfo.tip({
					content:data.msg
				});
			});

		},
		events: function() {	
			
			var _this = this;

			$('[extend]').on('click',function(){
				_this.extend($(this));
			});
			
		},
		extend: function(obj){

			if(obj.html() == '展开评论'){

				obj.parent().find('[comment-list]').find('li').show();
				obj.html('收起评论');

			}else{

				obj.parent().find('[comment-list]').find('li').hide();
				obj.parent().find('[comment-list]').find('li').eq(0).show();
				obj.parent().find('[comment-list]').find('li').eq(1).show();
				obj.html('展开评论');

			}

		},
		formatting: function(str){//格式化日期

			var day = str.substring(8,10);
			var month = str.substring(5,7);

			switch(month){
				case '01': month = '一月'
				break;
				case '02': month = '二月'
				break;
				case '03': month = '三月'
				break;
				case '04': month = '四月'
				break;
				case '05': month = '五月'
				break;
				case '06': month = '六月'
				break;
				case '07': month = '七月'
				break;
				case '08': month = '八月'
				break;
				case '09': month = '九月'
				break;
				case '10': month = '十月'
				break;
				case '11': month = '十一月'
				break;
				case '12': month = '十二月'
				break;
			}

			var date = {
				day: day,
				month: month
			};

			return date;
		}

	});

	var oRecord = new Record();

});
