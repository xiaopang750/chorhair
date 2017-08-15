/*TMODJS:{"version":47,"md5":"530f7c9f9795b893f4a0a35d6c98939e"}*/
define(function(require) {
    return require("../templates")("user/package", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), data = $data.data, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $out += " ", $each(data, function($value) {
            $out += ' <li> <dl> <dt> <div class="col-11 ba-auto"> <span>', $out += $escape($value.comboname), 
            $out += '</span> </div> </dt> <dd> <div class="col-11 ba-auto"> <table width="100%" cellpadding="0" cellspacing="0" border="0"> <tr> <td> 开始时间： </td> <td> ', 
            $out += $escape($value.combobegintime), $out += " </td> </tr> <tr> <td> 到期时间： </td> <td> ", 
            $out += $escape($value.comboendtime), $out += " </td> </tr> <tr> <td> 套餐内容： </td> <td> ", 
            $each($value.products, function($value) {
                $out += " <div>", $out += $escape($value.productname), $out += " X ", $out += $escape($value.productcount), 
                $out += "</div> ";
            }), $out += " ", $each($value.services, function($value) {
                $out += " ", "" == $value.servicecount ? ($out += " <div>", $out += $escape($value.servicename), 
                $out += "</div> ") : ($out += " <div>", $out += $escape($value.servicename), $out += " X ", 
                $out += $escape($value.servicecount), $out += "</div> "), $out += " ";
            }), $out += " </td> </tr> </table> </div> </dd> </dl> </li> ";
        }), new String($out);
    });
});