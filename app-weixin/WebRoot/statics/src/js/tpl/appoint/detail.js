/*TMODJS:{"version":6,"md5":"aa76839dc8183e2e5c3cc6d899fcafbc"}*/
define(function(require) {
    return require("../templates")("appoint/detail", function($data) {
        "use strict";
        var $utils = this, $escape = ($utils.$helpers, $utils.$escape), orderno = $data.orderno, iscombo = $data.iscombo, comboname = $data.comboname, shopname = $data.shopname, fairername = $data.fairername, ordetime = $data.ordetime, ordermoney = $data.ordermoney, $out = "";
        return $out += '<p class="appoint-num"> <span>服务编号：</span> <span>', $out += $escape(orderno), 
        $out += "</span> </p> <p> <span>订单类型：</span> <span> ", $out += "Y" == iscombo ? " 套餐订单 " : " 服务订单 ", 
        $out += " </span> </p> <p> <span>服务内容：</span> <span>", $out += $escape(comboname), 
        $out += "</span> </p> <p> <span>服务店铺：</span> <span>", $out += $escape(shopname), 
        $out += "</span> </p> <p> <span>服务人员：</span> <span>", $out += $escape(fairername), 
        $out += "</span> </p> <p> <span>服务时间：</span> <span>", $out += $escape(ordetime), 
        $out += "</span> </p> <p> <span>支付金额：</span> <span>", $out += $escape(ordermoney), 
        $out += "元</span> </p> ", new String($out);
    });
});