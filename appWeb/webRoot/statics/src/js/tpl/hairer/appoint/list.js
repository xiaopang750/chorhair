/*TMODJS:{"version":9,"md5":"15ef15d80f9c49d600377b02d268e83c"}*/
define(function(require) {
    return require("../../templates")("hairer/appoint/list", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), data = $data.data, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $out += " ", $each(data, function($value) {
            $out += ' <li> <div class="list-head"> <div class="col-11 ba-auto">服务编号：', $out += $escape($value.orderno), 
            $out += '</div> </div> <dl pkOrder="', $out += $escape($value.pkOrder), $out += '" class="col-11 ba-auto ba-clearfix">  <dd class="ba-fl"> <table width="100%"> <tr> <td class="ba-vt-t">服务内容：</td> <td>', 
            $out += $escape($value.comboname), $out += '</td> </tr> <tr> <td class="ba-vt-t">订单类型：</td> <td> ', 
            $out += "Y" == $value.iscombo ? " 套餐订单 " : " 服务订单 ", $out += ' </td> </tr> <tr> <td class="ba-vt-t">付款方式：</td> <td> ', 
            "" == $value.paymode && ($out += " 到店支付 "), $out += ' </td> </tr> <!-- <tr> <td class="ba-vt-t">支付金额：</td> <td>', 
            $out += $escape($value.ordermoney), $out += '元</td> </tr> --> <tr> <td class="ba-vt-t">消费者：</td> <td>', 
            $out += $escape($value.customername), $out += '</td> </tr> <tr> <td class="ba-vt-t">联系方式：</td> <td>', 
            $out += $escape($value.cellphone), $out += '</td> </tr> </table> </dd> </dl> <span class="icon icon-right"></span> </li> ';
        }), $out += " ", new String($out);
    });
});