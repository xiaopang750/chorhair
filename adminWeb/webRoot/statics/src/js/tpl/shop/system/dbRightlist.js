/*TMODJS:{"version":24,"md5":"021827be49fecc42f3bc34e9795b00fb"}*/
define(function(require) {
    return require("../../templates")("shop/system/dbRightlist", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), list = $data.list, $escape = ($data.$value, 
        $data.$index, $utils.$escape), type = $data.type, $out = "";
        return $out += " ", $each(list, function($value, $index) {
            $out += " <tr sub-name-list> <td list-sort> ", $out += $escape($index + 1), $out += ' </td> <td can-edit sub-name="awardname" re="^.{1,10}$" msg="系列名称为10个字以内" tip="系列名称不能为空"> ', 
            $out += $escape($value.awardname), $out += ' </td> <td can-edit sub-name="awardvalue" re="^\\d+$" msg="抵用券额度只能为数字" tip="抵用券额度不能为空"> ', 
            $out += $escape($value.awardvalue), $out += " </td> <td> ", "look" != type && ($out += ' <a href="javascript:;" aid="', 
            $out += $escape($value.pkCustomeraward), $out += '" edit>编辑</a> <a href="javascript:;" addAfterNeedId aid="', 
            $out += $escape($value.pkCustomeraward), $out += '" adapt>适用套餐</a> '), $out += ' <span class="ba-none" sub-name="pkCustomeraward" ignorecheck="yes">', 
            $out += $escape($value.pkCustomeraward), $out += "</span> </td> </tr> ";
        }), new String($out);
    });
});