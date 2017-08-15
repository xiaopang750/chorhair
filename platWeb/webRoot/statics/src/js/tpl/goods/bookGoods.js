/*TMODJS:{"version":11,"md5":"713354932456775b241f3050e1ed9037"}*/
define(function(require) {
    return require("../templates")("goods/bookGoods", function($data) {
        "use strict";
        var $utils = this, list = ($utils.$helpers, $data.list), $each = $utils.$each, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $out += " ", list && ($out += " ", $each(list, function($value) {
            $out += ' <li><a href="javascript:;" gid="', $out += $escape($value.pkProduct), 
            $out += '" unit="', $out += $escape($value.unit), $out += '" productprice="', $out += $escape($value.productprice), 
            $out += '" goods-item>', $out += $escape($value.productname), $out += "</a></li> ";
        }), $out += " "), $out += " ", new String($out);
    });
});