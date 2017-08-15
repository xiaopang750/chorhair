/*TMODJS:{"version":64,"md5":"e198b7c19c036fa9b37a991eed7fa058"}*/
define(function(require) {
    return require("../templates")("goods/goodsList", function($data) {
        "use strict";
        var $utils = this, $helpers = $utils.$helpers, list = $data.list, $each = $utils.$each, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $string = $utils.$string, getRoute = $helpers.getRoute, $out = "";
        return $out += ' <thead> <tr> <td width="20%">套餐名称</td> <td width="20%">标准价格</td> <td width="20%">套餐办理数量</td> <td width="20%">备注</td> <td width="20%">操作</td> </tr> </thead> <tbody> ', 
        list ? ($out += " ", $each(list, function($value) {
            $out += " <tr list> <td>", $out += $escape($value.comboname), $out += "</td> <td>", 
            $out += $escape($value.combomoney), $out += "</td> <td>", $out += $escape($value.buycount), 
            $out += "</td> <td>", $out += $escape($value.combonote), $out += '</td> <td> <p class="ba-mb-10"> <a class="btn btn-normal" href="', 
            $out += $string(getRoute([ "goods/addCombo" ])), $out += "?gid=", $out += $escape($value.pk_combo), 
            $out += '">套餐修改</a> </p> <p class="ba-mb-10"> <a class="btn btn-normal" href="', 
            $out += $string(getRoute([ "goods/packageEdit" ])), $out += "?pkCombo=", $out += $escape($value.pk_combo), 
            $out += "&comboname=", $out += $escape($value.comboname), $out += "&combomoney=", 
            $out += $escape($value.combomoney), $out += '">店铺属性</a> </p> <p class="ba-mb-10"> <a class="btn btn-normal" href="javascript:;" get-sale pid="', 
            $out += $escape($value.pk_combo), $out += '">选择抵用券</a> </p> <p> <a class="btn btn-normal" href="', 
            $out += $string(getRoute([ "content/addEdit" ])), $out += "?pkType=", $out += $escape($value.pk_combo), 
            $out += '&contenttype=combo">内容维护</a> </p> </td> </tr> ';
        }), $out += " ") : $out += ' <tr> <td colspan="5">暂无数据</td> </tr> ', $out += " </tbody>", 
        new String($out);
    });
});