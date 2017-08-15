/**
 *description:套餐
 *author:wangwc
 *date:2015/04/9
 */
define(function(require, exports, module){

	require("../../driver/global");
    require("../../touch/hammer");

	var Index = R.Class.create(R.util, {
 		initialize: function() {

            this.curpage = 1;
            this.pagesize = 5;
            this.fairtype = '';
            this.ready = true;
            this.loading = $('[loading]');

            this.windowHeigth = $(window).height();
            this.scrollHeight = 0;
            this.setHeight();
 			this.loadData(this.fairtype);
        	this.events();
        },
        setHeight: function() {
            //设置筛选项高度
            var seriesH = $('.series').height();

            $('.series-item').height(seriesH/2);
            $('.series-item').css('line-height', seriesH/2 + 'px');

        },
        loadData: function() {
        	var _this = this;
        	var type = this.parse().type;
            var param = {
                pagesize: this.pagesize.toString(),
                curpage: this.curpage.toString(), 
                contenttype: 'combo', 
                combotype: type,
                fairtype: this.fairtype
            };

            this.req('/content/querybykey.php', param, function(data) {

                var oWrap = $('.goods-list');

                if(data.data != ''){
                    var goodsArr = JSON.parse(data.data);
                    var jsonData = {
                        goodsArr: goodsArr
                    };
                    
                    var tpl = require("../../tpl/goods/list");
                    _this.render(oWrap, tpl, jsonData, 'append');

                    //设置商品列表项高度
                    var goodsW = $('.goods-list .img').width();
                    $('.goods-list li').height(goodsW);

                    _this.scrollHeight = document.documentElement.scrollHeight;

                    if(goodsArr.length == _this.pagesize) {
                       _this.ready = true; 
                    }else{
                        _this.ready = false; 
                    }
                    
                    _this.loading.hide();

                }

            });
        },
        events: function() {
            var _this = this;

            $('.series-list').hammer().on('tap', function() {
                _this.showSeries();
            });

            $('.series-list .close').hammer().on('tap', function() {
                _this.hideSeries();
                return false;
            });

            $('.series-item').hammer().on('tap', function() {

                $('.goods-list').html('');
                _this.curpage = 1;

                $(this).addClass('active').siblings().removeClass('active');
                $('.series-title .cn').html($(this).html());

                _this.fairtype = $(this).attr('fairtype');
                _this.loadData(_this.fairtype);
                _this.hideSeries();

                return false;

            });

            $('[all-series-item]').hammer().on('tap', function() {

                $('.goods-list').html('');
                _this.curpage = 1;

                _this.fairtype = '';
                _this.loadData(_this.fairtype);
                _this.hideSeries();

                $('.series-item').removeClass('active');
                $('.series-title .cn').html('系列');

                return false;
            });

            $(window).on('scroll', function() {

                var scrollTop = $(this).scrollTop();

                if(_this.scrollHeight - scrollTop <= _this.windowHeigth + 50){

                    if(_this.ready) {
                        _this.ready = false;
                        _this.loading.show();
                        _this.curpage++;
                        _this.loadData(_this.fairtype);
                    }    
                }
    
            });

        },
        showSeries: function() {
            $('.series-wrap').css('-webkit-transform', 'translateX(0)');

            setTimeout(function() {
                $('.series-wrap .close').show();
                $('[all-series-item]').css('opacity', 1);
            },100);
        },
        hideSeries: function() {
            $('.series-wrap').css('-webkit-transform', 'translateX(100%)');
            $('.series-wrap .close').hide();
            $('[all-series-item]').css('opacity', 0);
        }
       
 	});

 	var oIndex = new Index();

});