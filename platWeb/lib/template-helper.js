/**
 *description:后端模板渲染辅助方法
 *author:fanwei
 *date:2015/01/21
 */
module.exports = function(template) {

	//字符串裁切
	template.helper('cut', function(content, num){

	    var len;

	    if( typeof content !== 'string' ) {
	        return content;
	    } else {
	        
	        len = content.length;

	        if( len <= num ) {

	            return content;

	        } else {

	            return content.substring(0, num) + '...';

	        }

	    }

	});

	//检测有没有权限
	template.helper('checkPower', function(hasPower, nowPower){

	    if(hasPower.indexOf(nowPower) == -1) {
	   		return false;
	    } else {
	    	return true;
	    }

	});

};