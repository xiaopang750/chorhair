/*TMODJS:{"version":29,"md5":"4a36d2f64fb4769fc04d708d64c3e5ee"}*/
define(function(require) {
    return require("../templates")("goods/stockList", function($data) {
        "use strict";
        var $utils = this, $helpers = $utils.$helpers, list = $data.list, $each = $utils.$each, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $string = $utils.$string, getRoute = $helpers.getRoute, $out = "";
        return $out += " ", list ? ($out += " ", $each(list, function($value) {
            $out += " <tr list> <td>", $out += $escape($value.productname), $out += "</td> <td>", 
            $out += $escape($value.brand), $out += "</td> <td>", $out += $escape($value.series), 
            $out += "</td> <td>", $out += $escape($value.itemno), $out += "</td> <td productnum>", 
            $out += $escape($value.productnum), $out += "</td> <td>", $out += $escape($value.capacity), 
            $out += "</td> <td>", $out += $escape($value.unit), $out += "</td> <td productprice>", 
            $out += $escape($value.productprice), $out += '</td> <td> <a class="btn btn-normal" href="javascript:;" modify-price pkProduct="', 
            $out += $escape($value.pkProduct), $out += '">修改</a> <a class="btn btn-normal" href="javascript:;" add-num pkProduct="', 
            $out += $escape($value.pkProduct), $out += '">新增</a> <a class="btn btn-normal" href="', 
            $out += $string(getRoute([ "goods/stockRecord" ])), $out += "?pkProduct=", $out += $escape($value.pkProduct), 
            $out += '">变更记录</a> </td> </tr> ';
        }), $out += " ") : $out += ' <tr> <td colspan="9">暂无数据</td> </tr> ', new String($out);
    });
});