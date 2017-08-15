/*TMODJS:{"version":29,"md5":"0bde40d1fc49894fef2864ae270095c4"}*/
define(function(require) {
    return require("../templates")("shop/list", function($data) {
        "use strict";
        var $utils = this, $helpers = $utils.$helpers, $each = $utils.$each, shopArr = $data.shopArr, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $each(shopArr, function($value) {
            $out += ' <li> <a href="', $out += $escape($value.url), $out += "?pkShop=", $out += $escape($value.pkShop), 
            $out += '"> <div class="img"> <img src="', $out += $escape($value.firstpage), $out += '" alt=""> </div> <div class="text"> <p class="name">', 
            $out += $escape($helpers.cut($value.topic, 14)), $out += "</p> </div> </a> </li> ";
        }), new String($out);
    });
});