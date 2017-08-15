seajs.config({
	base: R.uri.jsPath,
	alias: {
    	'jquery1.7': 'lib/jquery/1.7.1/jquery',
    	'jquery1.11': 'lib/jquery/1.11.1/jquery'
  	},
  	preload: ['jquery1.7'],
  	map: [
    	[ /^(.*\.(?:css|js))(.*)$/i, '$1?' + R.uri.nowVersion]
  	]
});