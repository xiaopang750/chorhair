/*TMODJS:{"version":1,"md5":"5723a2ad68dfdba3f0d36f6a6d351d83"}*/
define(function(require) {
    return require("../templates")("goods/awardsList", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), awards = $data.awards, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $out += " ", $each(awards, function($value) {
            $out += ' <li class="ba-clearfix ba-mb-20" awards-list> <div script-role="check-wrap"> <label>提成名称:</label> <input type="text" class="form-control" sub-name="awardname" ischeck="true" form_check="sys" re="(.{1,10})" tip="请填写提成名称" wrong="提成名称不能超过10个字符" value="', 
            $out += $escape($value.awardname), $out += '"> </div> <div script-role="check-wrap"> <label>提成方式:</label> <select type="text" class="form-control" ischeck="true" form_check="sys" sub-name="israte" re="(.+)" tip="请选择提成方式" msg="请选择提成方式"> <option value="">请选择</option> <option value="1" ', 
            "1" == $value.israte && ($out += 'selected="selected"'), $out += '>固定额度</option> <option value="2" ', 
            "2" == $value.israte && ($out += 'selected="selected"'), $out += '>比例提成</option> </select> </div> <div script-role="check-wrap"> <label>值:</label> <input type="text" class="form-control" ischeck="true" form_check="sys" sub-name="awardmoney" re="^\\d+$" tip="请填写提成值" wrong="提成值为数字" ', 
            $out += 1 == $value.israte ? 're="^\\d+$" msg="固定额度只能为数字" tip="固定额度只能为数字" wrong="固定额度只能为数字"' : 2 == $value.israte ? 're="(^([1-9]\\d?)|100$)" msg="提成比例为100以内的数字" tip="提成比例为100以内的数字" wrong="提成比例为100以内的数字"' : 'ignorecheck="yes"', 
            $out += ' value="', $out += $escape($value.awardmoney), $out += '"> </div> <span class="ba-none" sub-name="pkFaireraward" ignorecheck="yes">', 
            $out += $escape($value.pkFaireraward), $out += "</span> </li> ";
        }), $out += " ", new String($out);
    });
});