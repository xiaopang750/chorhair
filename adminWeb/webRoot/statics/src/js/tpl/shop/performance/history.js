/*TMODJS:{"version":39,"md5":"9f45284f4b20d46b27fec39ef413a894"}*/
define(function(require) {
    return require("../../templates")("shop/performance/history", function($data) {
        "use strict";
        var $utils = this, list = ($utils.$helpers, $data.list), $each = $utils.$each, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $out += " ", list ? ($out += " ", $each(list, function($value) {
            $out += " <tr> <td>", $out += $escape($value.customername), $out += "</td> <td> ", 
            "001" == $value.paystatus ? $out += " 未结算 " : "002" == $value.paystatus && ($out += " 已结算 "), 
            $out += " </td> <td> ", "001" == $value.orderstatus ? $out += " 服务中 " : "002" == $value.orderstatus ? $out += " 服务完成 " : "003" == $value.orderstatus ? $out += " 预约单 " : "004" == $value.orderstatus && ($out += " 预约取消 "), 
            $out += " </td> <td>", $out += $escape($value.orderno), $out += "</td> <td>", $out += $escape($value.ordetime), 
            $out += "</td> <td>", $out += $escape($value.ordermoney), $out += "</td> <td> ", 
            $out += "Y" == $value.iscombo ? " 套餐订单 " : " 服务订单 ", $out += " </td> <td> ", $out += $escape($value.operator), 
            $out += " </td> <td>", $out += $escape($value.fairername), $out += "</td> <td>", 
            $out += $escape($value.fairermoney), $out += "</td> </tr> ";
        }), $out += " ") : $out += ' <tr> <td colspan="10">暂无数据</td> </tr> ', new String($out);
    });
});