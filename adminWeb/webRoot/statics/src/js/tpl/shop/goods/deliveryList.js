/*TMODJS:{"version":36,"md5":"31a02bb88fc2c36c8ab531fdd2330bd2"}*/
define(function(require) {
    return require("../../templates")("shop/goods/deliveryList", function($data) {
        "use strict";
        var $utils = this, $helpers = $utils.$helpers, list = $data.list, $each = $utils.$each, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $string = $utils.$string, getRoute = $helpers.getRoute, $out = "";
        return $out += " ", list ? ($out += " ", $each(list, function($value) {
            $out += " <tr list pkProductDelivery=", $out += $escape($value.pkProductDelivery), 
            $out += "> <td>", $out += $escape($value.deliveryno), $out += "</td> <td>", $out += $escape($value.deliverytime), 
            $out += "</td> <td>", $out += $escape($value.operatorname), $out += "</td> <td>", 
            $out += $escape($value.deliverymoney), $out += '</td> <td edit="vbillstatus"> ', 
            1 == $value.vbillstatus ? $out += " 出库中 " : 8 == $value.vbillstatus && ($out += " 已完成 "), 
            $out += " </td> <td> ", 1 == $value.vbillstatus ? ($out += ' <a class="btn btn-normal" href="', 
            $out += $string(getRoute([ "goods/deliveryAddEdit" ])), $out += "?bid=", $out += $escape($value.pkProductDelivery), 
            $out += '" delivery-edit>编辑</a> <a class="btn btn-normal" href="javascript:;" delivery-confirm>确认出库</a> ') : 8 == $value.vbillstatus && ($out += ' <a class="btn btn-normal" href="', 
            $out += $string(getRoute([ "goods/deliveryAddEdit" ])), $out += "?bid=", $out += $escape($value.pkProductDelivery), 
            $out += '" delivery-detail>详情</a> '), $out += " </td> </tr> ";
        }), $out += " ") : $out += ' <tr> <td colspan="6">暂无数据</td> </tr> ', new String($out);
    });
});