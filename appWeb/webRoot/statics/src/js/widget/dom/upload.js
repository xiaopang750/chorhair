/*
 *description:上传图片底部action
 *author:wangwc
 *date:2015/1/29
 */
define(function(require, exports, module){
	
	require('../../lib/touch/hammer');

	function UpLoad(opts) {

		opts = opts || {};

		this.uploadBtn = opts.uploadBtn;
		this.mask = null;
		this.dialog = null;
		this.selHeight = 0;
		
	}

	UpLoad.prototype = {

		init: function(){

			this.events();

		},
		disable: function() {

			$('body').hammer().off('tap', this.uploadBtn);

		},
		events: function(){

			var _this = this; 

			//选择上传方式
			$('body').hammer().on('tap', this.uploadBtn, function(e){
				if(e.target.tagName != 'IMG' || $(e.target).hasClass('camera-img')){
					_this.createDom();
				}
			});

			//取消选择
			$('body').hammer().on('tap', '.body-mask, [cancel-photo]', function(){
				_this.remove();
			});

			//拍照
			$('body').hammer().on('tap', '[take-photo]', function(){
				_this.onPhoto && _this.onPhoto();
				_this.remove();
			});

			//相册选择
			$('body').hammer().on('tap', '[select-photo]', function(){
				_this.onSelect && _this.onSelect();
				_this.remove();
			});

		},
		createDom: function(){

			var _this = this;

			//创建遮罩
			this.mask = $('<div class="body-mask"></div>');
			this.mask.height($(window).height());

			$('body').append(this.mask);

			//创建弹出框
			if(!$('[upload-dialog]').html()){
					this.dialog = $('<section class="upload-type" upload-dialog>'+
								'<ul>'+
									'<li take-photo>拍照</li>'+
									'<li select-photo>从手机相册选择</li>'+
									'<li cancel-photo>取消</li>'+
								'</ul>'+
							'</section>');

				$('body').append(this.dialog);
			}else{
				this.dialog = $('[upload-dialog]');
			}
			
			this.selHeight = parseInt(this.dialog.height());
			this.dialog.addClass('show');

		},
		remove: function(){

			this.mask && this.mask.remove();
			this.dialog && this.dialog.removeClass('show');

		}

	};

	module.exports = UpLoad;

});
