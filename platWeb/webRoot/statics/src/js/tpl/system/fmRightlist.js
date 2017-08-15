/*TMODJS:{"version":1,"md5":"de40b142ab5bf16c9a81a10fd5302270"}*/
define(function(require) {
    return require("../templates")("system/fmRightlist", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), list = $data.list, $escape = ($data.$value, 
        $data.$index, $utils.$escape), type = $data.type, $out = "";
        return $out += " ", $each(list, function($value, $index) {
            $out += " <tr sub-name-list> <td list-sort> ", $out += $escape($index + 1), $out += ' </td> <td can-edit sub-name="awardname" re="^.{1,10}$" msg="提成名称为10个字以内" tip="提成名称不能为空"> ', 
            $out += $escape($value.awardname), $out += " </td> <td> <select ", "look" == type && ($out += 'disabled="disabled"'), 
            $out += ' sub-name="israte" re="^.+$" msg="请选择提成方式" tip="请选择提成方式" class="form-control"> <option value="">请选择</option> <option value="1" ', 
            1 == $value.israte && ($out += 'selected="selected"'), $out += '>固定额度</option> <option value="2" ', 
            2 == $value.israte && ($out += 'selected="selected"'), $out += '>比例提成</option> </select> </td> <td can-edit sub-name="awardmoney" ', 
            $out += 1 == $value.israte ? 're="^\\d+$" msg="固定额度只能为数字" tip="固定额度不能为空"' : 2 == $value.israte ? 're="^([1-9]\\d?)|100$" msg="提成比例为100以内的数字" tip="提成比例不能为空"' : 'ignorecheck="yes"', 
            $out += "> ", $out += $escape($value.awardmoney), $out += " </td> <td> ", "look" != type && ($out += ' <a href="javascript:;" aid="', 
            $out += $escape($value.pkFaireraward), $out += '" edit>编辑</a> <a href="javascript:;" addAfterNeedId aid="', 
            $out += $escape($value.pkFaireraward), $out += '" disable="', $out += $escape($value.isvalidate), 
            $out += '"> ', $out += "Y" == $value.isvalidate ? " 禁用 " : " 启用 ", $out += " </a> "), 
            $out += ' <span class="ba-none" sub-name="pkFaireraward" ignorecheck="yes">', $out += $escape($value.pkFaireraward), 
            $out += "</span> </td> </tr> ";
        }), new String($out);
    });
});