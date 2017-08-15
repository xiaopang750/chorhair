/*TMODJS:{"version":39,"md5":"ec5243bf86c7919be49059b63320f6bd"}*/
define(function(require) {
    return require("../templates")("user/package", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), packageArr = $data.packageArr, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $each(packageArr, function($value) {
            $out += ' <li> <p class="package-name"> <span>套餐名称：</span> <span>', $out += $escape($value.comboname), 
            $out += "</span> </p> <p> <span>开始时间：</span> <span> ", $value.combobegintime ? ($out += " ", 
            $out += $escape($value.combobegintime), $out += " ") : $out += " 未开始使用 ", $out += " </span> </p> <p> <span>到期时间：</span> <span> ", 
            $value.comboendtime ? ($out += " ", $out += $escape($value.comboendtime), $out += " ") : $out += " 未开始使用 ", 
            $out += ' </span> </p> <p> <span>套餐内容：</span> <span class="package-content"> ', 
            $each($value.products, function($value) {
                $out += " <span>", $out += $escape($value.productname), $out += " X ", $out += $escape($value.productcount), 
                $out += "</span> ";
            }), $out += " ", $each($value.services, function($value) {
                $out += " ", "" == $value.servicecount ? ($out += " <span>", $out += $escape($value.servicename), 
                $out += "</span> ") : ($out += " <span>", $out += $escape($value.servicename), $out += " X ", 
                $out += $escape($value.servicecount), $out += "</span> "), $out += " ";
            }), $out += " </span> </p> </li> ";
        }), new String($out);
    });
});