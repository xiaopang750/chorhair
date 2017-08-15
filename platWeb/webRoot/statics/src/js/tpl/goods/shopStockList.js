/*TMODJS:{"version":5,"md5":"91c8c9959ed2f5190c512b553b972317"}*/
define(function(require) {
    return require("../templates")("goods/shopStockList", function($data) {
        "use strict";
        var $utils = this, list = ($utils.$helpers, $data.list), $each = $utils.$each, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $out += " ", list ? ($out += " ", $each(list, function($value) {
            $out += " <tr list> <td>", $out += $escape($value.productname), $out += "</td> <td>", 
            $out += $escape($value.brand), $out += "</td> <td>", $out += $escape($value.series), 
            $out += "</td> <td>", $out += $escape($value.itemno), $out += "</td> <td productnum>", 
            $out += $escape($value.productnum), $out += "</td> <td>", $out += $escape($value.capacity), 
            $out += "</td> <td>", $out += $escape($value.unit), $out += "</td> <td productprice>", 
            $out += $escape($value.productprice), $out += "</td> </tr> ";
        }), $out += " ") : $out += ' <tr> <td colspan="8">暂无数据</td> </tr> ', new String($out);
    });
});