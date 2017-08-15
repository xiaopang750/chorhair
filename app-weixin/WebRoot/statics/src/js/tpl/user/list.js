/*TMODJS:{"version":1,"md5":"62e1822d01cea6870010fa9fc71e3c31"}*/
define(function(require) {
    return require("../templates")("user/list", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), appointArr = $data.appointArr, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $each(appointArr, function($value) {
            $out += ' <li> <p class="appoint-num"> <span>服务编号：</span> <span>', $out += $escape($value.orderno), 
            $out += "</span> </p> <p> <span>订单类型：</span> <span> ", $out += "Y" == $value.iscombo ? " 套餐订单 " : " 服务订单 ", 
            $out += " </span> </p> <p> <span>服务内容：</span> <span>", $out += $escape($value.comboname), 
            $out += "</span> </p> <p> <span>服务店铺：</span> <span>", $out += $escape($value.shopname), 
            $out += "</span> </p> <p> <span>服务时间：</span> <span>", $out += $escape($value.ordetime), 
            $out += "</span> </p> <p> <span>支付金额：</span> <span>", $out += $escape($value.ordermoney), 
            $out += "元</span> </p> </li> ";
        }), new String($out);
    });
});