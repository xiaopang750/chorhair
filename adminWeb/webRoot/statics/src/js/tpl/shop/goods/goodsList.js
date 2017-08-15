/*TMODJS:{"version":75,"md5":"fefd000d3383ace3e3e2af917b5cb17b"}*/
define(function(require) {
    return require("../../templates")("shop/goods/goodsList", function($data) {
        "use strict";
        var $utils = this, $helpers = $utils.$helpers, userType = $helpers.userType, list = $data.list, $each = $utils.$each, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $string = $utils.$string, getRoute = $helpers.getRoute, $out = "";
        return $out += " ", $out += 1 == userType() ? ' <thead> <tr> <td width="10%">套餐编码</td> <td width="20%">套餐名称</td>  <td width="10%">标准价格</td>  <td width="10%">套餐购买数量</td> <td width="20%">说明</td> <td width="10%">操作</td> </tr> </thead> ' : ' <thead> <tr> <td width="40%">套餐名称</td> <td width="40%">平台价格</td> <td width="20%">操作</td> </tr> </thead> ', 
        $out += " <tbody> ", list ? ($out += " ", $each(list, function($value) {
            $out += " ", 1 == userType() ? ($out += " <tr list> <td>", $out += $escape($value.combocode), 
            $out += "</td> <td>", $out += $escape($value.comboname), $out += "</td> <!-- <td> ", 
            $out += 1 == $value.fitgroup ? " 男士 " : 2 == $value.fitgroup ? " 女士 " : " 不限 ", 
            $out += " </td> --> <td>", $out += $escape($value.standardmoney), $out += '</td> <!-- <td edit="shopmoney">', 
            $out += $escape($value.shopmoney), $out += '</td> --> <td edit="buycount">', $out += $escape($value.buycount), 
            $out += '</td> <td edit="combonote">', $out += $escape($value.combonote), $out += '</td> <td> <p class="ba-mb-10"> <a class="btn btn-normal" href="', 
            $out += $string(getRoute([ "goods/packageEdit" ])), $out += "?pkShopCombo=", $out += $escape($value.pkShopCombo), 
            $out += "&pkname=", $out += $escape($value.comboname), $out += '">修改</a> </p> <p> <a class="btn btn-normal" href="', 
            $out += $string(getRoute([ "goods/detail" ])), $out += "?gid=", $out += $escape($value.pkShopCombo), 
            $out += '">修改日志</a> </p> </td> </tr> ') : 2 == userType() && ($out += " <tr list> <td>", 
            $out += $escape($value.comboname), $out += "</td> <td>", $out += $escape($value.combomoney), 
            $out += '</td> <td> <p class="ba-mb-10"> <a class="btn btn-normal" href="javascript:;" get-sale pid="', 
            $out += $escape($value.pkCombo), $out += '">选择抵用券</a> </p> <p> <a class="btn btn-normal" href="', 
            $out += $string(getRoute([ "content/addEdit" ])), $out += "?pkType=", $out += $escape($value.pkCombo), 
            $out += '&contenttype=combo">内容维护</a> </p> </td> </tr> '), $out += " ";
        }), $out += " ") : $out += ' <tr> <td colspan="8">暂无数据</td> </tr> ', $out += " </tbody>", 
        new String($out);
    });
});