seajs.config({
	base: '../../statics/src/js',
	alias: {
    	'zepto': 'lib/zepto/1.1.4/zepto',
    	'jquery': 'lib/jquery/1.7.1/jquery'
  	},
  	preload: ['jquery']
});