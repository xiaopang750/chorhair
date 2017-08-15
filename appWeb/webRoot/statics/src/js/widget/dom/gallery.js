/*
 *description:图片相册
 *author:wangwc
 *date:2015/1/30
 */
define(function(require, exports, module){

	require('./blueimp-gallery');

	var Gallery = R.Class.create(R.util, {

		initialize: function(opts){

			this.galleryWrap = opts.galleryWrap;
			this.gallery = null;
			this.events();

		},
		events: function(){
			var _this = this;

			this.galleryWrap.click(function(e){

				if(e.target.tagName == 'IMG'){
					_this.showGallery(e, this);
					$('#blueimp-gallery').addClass('blueimp-gallery-controls');
				}

			});

			$('body').on('click', '.slide-content', function(){
				_this.gallery.close();
			});

		},
		showGallery: function(event, oThis){

			var target = event.target,
		        link = target.src ? target.parentNode : target,
		        options = {index: link, event: event, closeOnSwipeUpOrDown: false,continuous: false, transitionSpeed: 300},
		        links = oThis.getElementsByTagName('a');

		    this.gallery = blueimp.Gallery(links, options);
		}

	});
	
	module.exports = Gallery;

});
