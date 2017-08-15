/*TMODJS:{"version":56,"md5":"4fc6aba79a6738f68859a3f6d75df217"}*/
define(function(require) {
    return require("../../templates")("shop/performance/allList", function($data) {
        "use strict";
        var $utils = this, list = ($utils.$helpers, $data.list), $each = $utils.$each, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $out += " ", list ? ($out += " ", $each(list, function($value) {
            $out += " <tr> <td>", $out += $escape($value.curdate), $out += "</td> <td combomoney>", 
            $out += $escape($value.combomoney), $out += "</td> <td servicemoney>", $out += $escape($value.servicemoney), 
            $out += "</td> <td fairermoney>", $out += $escape($value.fairermoney), $out += "</td> <td allmoney>", 
            $out += $escape($value.combomoney + $value.servicemoney), $out += "</td> <td expense>", 
            $out += $escape($value.fairermoney), $out += "</td> <td balance>", $out += $escape($value.combomoney + $value.servicemoney - $value.fairermoney), 
            $out += "</td> </tr> ";
        }), $out += " ") : $out += ' <tr> <td colspan="7">暂无数据</td> </tr> ', new String($out);
    });
});