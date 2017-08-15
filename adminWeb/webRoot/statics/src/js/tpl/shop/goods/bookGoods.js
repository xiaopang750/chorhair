/*TMODJS:{"version":15,"md5":"c3c3b4da2e43c85753b75218cc1fd24d"}*/
define(function(require) {
    return require("../../templates")("shop/goods/bookGoods", function($data) {
        "use strict";
        var $utils = this, list = ($utils.$helpers, $data.list), $each = $utils.$each, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $out += " ", list && ($out += " ", $each(list, function($value) {
            $out += ' <li><a href="javascript:;" gid=', $out += $escape($value.pkShopProdcut), 
            $out += " unit=", $out += $escape($value.unit), $out += " capacity=", $out += $escape($value.capacity), 
            $out += " productprice=", $out += $escape($value.productprice), $out += " goods-item>", 
            $out += $escape($value.productname), $out += "</a></li> ";
        }), $out += " "), $out += " ", new String($out);
    });
});