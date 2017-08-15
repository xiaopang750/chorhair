/*
 *description:瀑布流
 *author:wangwc
 *date:2015/1/27
 */
 
define(function(require, exports, module){
	
	require('../../lib/touch/hammer');

	var Falls = R.Class.create(R.util, {

		initialize: function(opts){

			opts = opts || {};

			this.wrap = opts.wrap;
			this.reqUrl = opts.reqUrl;
			this.reqParam = { pagesize:10, curpage:1 };
			this.search = opts.search || null;
			this.firstLoad = true;
			
			this.width = this.wrap.width();
			this.getMore = $('[get-more]');
			this.ready = false; 

			this.loadImg(this.firstLoad);
			this.events();

		},
		loadImg: function(firstLoad){

			var _this = this;

			if(this.search){
				this.reqParam.keyword = this.search;
			}

			this.req(function(data){
				// console.log(JSON.stringify(data));
				if(data.data.length == 0 && !_this.firstLoad){

					_this.getMore.html('没有更多啦').addClass('falls-loading-first').show();
					_this.getMore.hammer().off('tap');

				}else if(data.data.length == 0 && _this.firstLoad){

					_this.getMore.html('暂无内容').addClass('falls-loading-first').show();
					_this.getMore.hammer().off('tap');

				}else{
					$.each(data.data, function(index, obj){
						//获取最短的ul
						var index = _this.getShort();
						var li = $('<li>'+
								'<a href="'+obj.url+'">'+
									'<img class="lazyload" style="height:'+_this.width/obj.width*obj.height+'px" src="'+obj.firstpage+'">'+
									'<div class="info-area">'+
										'<div class="inner">'+
											'<span class="namer">'+obj.topic+'</span>'+
											'<div class="assit ba-fr ba-tr ba-mt-10">'+
												'<span class="icon icon-good ba-font-16 ba-mr-3"></span>'+
												'<span num>'+obj.praisecount+'</span>'+
											'</div>'+
										'</div>'+
									'</div>'+
								'</a>'+
							'</li>');

						_this.wrap.eq(index).append(li);
					});

					_this.getMore.html('加载更多').removeClass('falls-loading-first').show();
					_this.ready = true;
				}

			}, function(data){
				alert(data.msg);
				_this.getMore.hide();
			});
		},
		getShort: function(){

			var index = 0;
			var ih = this.wrap.eq(index).height();

			for(var i=1; i<this.wrap.length; i++){

				if(this.wrap.eq(i).height() < ih){
					index = i;
					ih = this.wrap.eq(i).height();
				}

			}
			
			return index;

		},
		events: function(){

			var _this = this;

			_this.getMore.hammer().on('tap',function(e){

				$(this).html('<img src="'+R.uri.imgPath+'lib/loading/btn_loading_r.gif" alt="">');
				
				setTimeout(function(){

					if(_this.ready){
						_this.firstLoad = false;
						_this.ready = false;
						_this.reqParam.curpage++;
						_this.loadImg();
					}
				},500);
				
			});
		},
		getTop: function(obj){

			var iTop = 0;

			while(obj.tagName){
				iTop += obj.offset().top;
				obj = obj.offsetParent;
			}

			return iTop;
			
		}
		
	});

	module.exports = Falls;

});
