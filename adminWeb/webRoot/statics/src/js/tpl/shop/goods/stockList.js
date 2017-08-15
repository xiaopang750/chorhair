/*TMODJS:{"version":36,"md5":"423693f569b8a835c72a7475454f1f68"}*/
define(function(require) {
    return require("../../templates")("shop/goods/stockList", function($data) {
        "use strict";
        var $utils = this, list = ($utils.$helpers, $data.list), $each = $utils.$each, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $out += " ", list ? ($out += " ", $each(list, function($value) {
            $out += " <tr list> <td>", $out += $escape($value.productname), $out += "</td> <td>", 
            $out += $escape($value.brand), $out += "</td> <td>", $out += $escape($value.series), 
            $out += "</td> <td>", $out += $escape($value.itemno), $out += "</td> <td>", $out += $escape($value.productnum), 
            $out += "</td> <td>", $out += $escape($value.capacity), $out += "</td> <td>", $out += $escape($value.unit), 
            $out += "</td> <td>", $out += $escape($value.productprice), $out += "</td> </tr> ";
        }), $out += " ") : $out += ' <tr> <td colspan="8">暂无数据</td> </tr> ', new String($out);
    });
});