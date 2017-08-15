/*TMODJS:{"version":1,"md5":"9ffaaf982570237824b1d0a3ccff5200"}*/
define(function(require) {
    return require("../../templates")("shop/shop/goodsList", function($data) {
        "use strict";
        var $utils = this, $helpers = $utils.$helpers, userType = $helpers.userType, list = $data.list, $each = $utils.$each, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $string = $utils.$string, getRoute = $helpers.getRoute, $out = "";
        return $out += " ", $out += 1 == userType() ? ' <thead> <tr> <td width="20%">套餐名称</td> <td width="10%">面向群体</td> <td width="10%">标准价格</td> <td width="10%">本店价格</td> <td width="10%">套餐数量</td> <td width="10%">说明</td> <td width="20%">操作</td> </tr> </thead> ' : ' <thead> <tr> <td width="40%">套餐名称</td> <td width="40%">平台价格</td> <td width="20%">操作</td> </tr> </thead> ', 
        $out += " <tbody> ", list ? ($out += " ", $each(list, function($value) {
            $out += " ", 1 == userType() ? ($out += " <tr list> <td>", $out += $escape($value.comboname), 
            $out += "</td> <td> ", $out += 1 == $value.fitgroup ? " 男士 " : 2 == $value.fitgroup ? " 女士 " : " 不限 ", 
            $out += " </td> <td>", $out += $escape($value.standardmoney), $out += '</td> <td edit="shopmoney">', 
            $out += $escape($value.shopmoney), $out += '</td> <td edit="servicecount">', $out += $escape($value.servicecount), 
            $out += '</td> <td edit="combonote">', $out += $escape($value.combonote), $out += '</td> <td> <p class="ba-mb-10"> <a class="btn btn-normal" href="', 
            $out += $string(getRoute([ "goods/packageEdit" ])), $out += "?pkShopCombo=", $out += $escape($value.pkShopCombo), 
            $out += "&pkname=", $out += $escape($value.comboname), $out += '">修改</a> </p> <p class="ba-mb-10"> <a class="btn btn-normal" href="', 
            $out += $string(getRoute([ "goods/detail" ])), $out += "?gid=", $out += $escape($value.pkShopCombo), 
            $out += '">修改详情</a> </p> <p class="ba-mb-10"> <a class="btn btn-normal" href="', 
            $out += $string(getRoute([ "content/addEdit" ])), $out += "?pkType=", $out += $escape($value.pkShopCombo), 
            $out += '&contenttype=combo">内容维护</a> </p> </td> </tr> ') : 2 == userType() && ($out += " <tr list> <td>", 
            $out += $escape($value.comboname), $out += "</td> <td>", $out += $escape($value.combomoney), 
            $out += '</td> <td> <p> <a class="btn btn-normal" href="javascript:;" get-sale pid="', 
            $out += $escape($value.pkCombo), $out += '">选择抵用券</a> </p> </td> </tr> '), $out += " ";
        }), $out += " ") : $out += ' <tr> <td colspan="8">暂无数据</td> </tr> ', $out += " </tbody>", 
        new String($out);
    });
});