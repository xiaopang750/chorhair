/*TMODJS:{"version":49,"md5":"7b0fdda815696f392bba40721863b565"}*/
define(function(require) {
    return require("../templates")("goods/list", function($data) {
        "use strict";
        var $utils = this, $helpers = $utils.$helpers, $each = $utils.$each, goodsArr = $data.goodsArr, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $each(goodsArr, function($value) {
            $out += ' <li> <a href="', $out += $escape($value.url), $out += '"> <div class="img"> <img src="', 
            $out += $escape($value.firstpage), $out += '" alt=""> </div> <div class="text"> <p class="name">', 
            $out += $escape($helpers.cut($value.topic, 14)), $out += '</p> <p class="price"><em>￥', 
            $out += $escape($helpers.cutPrice($value.comboprice)), $out += "</em></p> </div> </a> </li> ";
        }), new String($out);
    });
});