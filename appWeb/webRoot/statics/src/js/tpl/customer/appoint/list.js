/*TMODJS:{"version":47,"md5":"3044f1c3bb52e4ec9f3368290852df8b"}*/
define(function(require) {
    return require("../../templates")("customer/appoint/list", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), data = $data.data, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $out += " ", $each(data, function($value) {
            $out += ' <li> <div class="list-head"> <div class="col-11 ba-auto">服务编号：', $out += $escape($value.orderno), 
            $out += '</div> </div> <dl pkOrder="', $out += $escape($value.pkOrder), $out += '" class="col-11 ba-auto ba-clearfix">  <dd class="ba-fl"> <table width="100%"> <tr> <td>服务内容：</td> <td>', 
            $out += $escape($value.comboname), $out += "</td> </tr> <tr> <td>订单类型：</td> <td> ", 
            $out += "Y" == $value.iscombo ? " 套餐订单 " : " 服务订单 ", $out += " </td> </tr> <tr> <td>付款方式：</td> <td> ", 
            "" == $value.paymode && ($out += " 到店支付 "), $out += " </td> </tr> <tr> <td>支付金额：</td> <td>", 
            $out += $escape($value.ordermoney), $out += '元</td> </tr> </table> </dd> </dl> <span class="icon icon-right"></span> </li> ';
        }), $out += " ", new String($out);
    });
});