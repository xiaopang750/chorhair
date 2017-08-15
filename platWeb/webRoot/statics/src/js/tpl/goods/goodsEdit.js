/*TMODJS:{"version":1,"md5":"666023aa5cf9ba17ae18a10d43eb29d6"}*/
define(function(require) {
    return require("../templates")("goods/goodsEdit", function($data) {
        "use strict";
        var $utils = this, $escape = ($utils.$helpers, $utils.$escape), shopmoney = $data.shopmoney, fairermoney = $data.fairermoney, combonote = $data.combonote, $out = "";
        return $out += ' <div class="edit-box" script-bound = "form-check"> <div class="ba-mb-10"> <span>本店价格:</span> <div script-role="check-wrap"> <input type="text" class="form-control" now-price value="', 
        $out += $escape(shopmoney), $out += '" form_check="sys" ischeck="true" name="shopmoney" tip="此项为必填" wrong="请填写数字" re="(\\d+)"> </div> </div> <div class="ba-mb-10"> <span>提成比例:</span> <div class="ba-clearfix"> <div script-role="check-wrap"> <input type="text" class="form-control col-11" now-price value="', 
        $out += $escape(fairermoney), $out += '" form_check="sys" ischeck="true" name="fairermoney" tip="此项为必填" wrong="请填写100以内的数字" re="([1-9]\\d{0,1}|100)"> <span class="ba-fl ba-mt-5 ba-ml-5">%</span> </div> </div> </div> <div class="ba-mb-10"> <span>说明:</span> <div script-role="check-wrap"> <textarea type="text" class="form-control" note form_check="sys" ischeck="free" name="combonote" tip="此项为必填" wrong="说明不能超过100个字" re="((.|\\n){1,100})">', 
        $out += $escape(combonote), $out += '</textarea> </div> </div> <div class="ba-tc"> <a href="javascript:;" class="btn btn-primary" sc="confirm" script-role="confirm-btn">确定</a> <a href="javascript:;" class="btn btn-danger" sc="close">取消</a> </div> </div> ', 
        new String($out);
    });
});