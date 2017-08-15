/*TMODJS:{"version":81,"md5":"13cfcbe42d9767942f1eed1099867637"}*/
define(function(require) {
    return require("../templates")("appoint/list", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), appointArr = $data.appointArr, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $each(appointArr, function($value) {
            $out += ' <li aid="', $out += $escape($value.pkOrder), $out += '"> <p class="appoint-num"> <span>服务编号：</span> <span>', 
            $out += $escape($value.orderno), $out += "</span> </p> <p> <span>订单类型：</span> <span> ", 
            $out += "Y" == $value.iscombo ? " 套餐订单 " : "003" == $value.orderstatus ? " 预约单 " : " 服务订单 ", 
            $out += " </span> </p> <p> <span>服务内容：</span> <span>", $out += $escape($value.comboname), 
            $out += "</span> </p> <p> <span>服务店铺：</span> <span>", $out += $escape($value.shopname), 
            $out += "</span> </p> ", $value.fairername && ($out += " <p> <span>服务人员：</span> <span>", 
            $out += $escape($value.fairername), $out += "</span> </p> "), $out += " ", "" == $value.appointtime ? ($out += " <p> <span>服务时间：</span> <span>", 
            $out += $escape($value.ordetime), $out += "</span> </p> ") : ($out += " <p> <span>预约时间：</span> <span>", 
            $out += $escape($value.appointtime), $out += "</span> </p> "), $out += " ", "" == $value.appointtime && $value.discount && ($out += " <p> <span>优惠金额：</span> <span>", 
            $out += $escape($value.discount), $out += "元</span> </p> "), $out += " ", $value.paymode && ($out += " <p> <span>支付方式：</span> ", 
            "1" == $value.paymode ? $out += " <span>现金</span> " : "2" == $value.paymode ? $out += " <span>刷卡</span> " : "3" == $value.paymode ? $out += " <span>微信支付</span> " : "4" == $value.paymode && ($out += " <span>支付宝支付</span> "), 
            $out += " </p> "), $out += " <p> <span>支付金额：</span> <span>", $out += $escape($value.ordermoney), 
            $out += "元</span> </p> </li> ";
        }), new String($out);
    });
});