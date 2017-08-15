/*TMODJS:{"version":1,"md5":"3e6500f88d41d668b0af59d9faca89d8"}*/
define(function(require) {
    return require("../../templates")("shop/plat/platCo", function($data) {
        "use strict";
        var $utils = this, $helpers = $utils.$helpers, userType = $helpers.userType, $escape = $utils.$escape, shopname = $data.shopname, addr = $data.addr, businessour = $data.businessour, fixphone = $data.fixphone, $string = $utils.$string, getRoute = $helpers.getRoute, pkShop = $data.pkShop, $each = $utils.$each, $out = ($data.$value, 
        $data.$index, "");
        return $out += " ", $out += 1 == userType() ? ' <thead> <tr> <td width="20%">店铺名称</td> <td width="20%">店铺地址</td> <td width="20%">营业时间</td> <td width="20%">联系电话</td> <td width="20%">操作</td> </tr> </thead> ' : ' <thead> <tr> <td width="25%">店铺名称</td> <td width="25%">店铺地址</td> <td width="25%">营业时间</td> <td width="25%">联系电话</td> </tr> </thead> ', 
        $out += " <tbody> ", 1 == userType() && ($out += " <tr> <td>", $out += $escape(shopname), 
        $out += "</td> <td>", $out += $escape(addr), $out += "</td> <td>", $out += $escape(businessour), 
        $out += "</td> <td>", $out += $escape(fixphone), $out += '</td> <td> <p class="ba-mb-10"> <a class="btn btn-normal">店铺信息</a> </p> <p> <a class="btn btn-normal" href="', 
        $out += $string(getRoute([ "content/addEdit" ])), $out += "?pkType=", $out += $escape(pkShop), 
        $out += "&contenttype=shop&address=", $out += $escape(addr), $out += "&time=", $out += $escape(businessour), 
        $out += "&phone=", $out += $escape(fixphone), $out += '">内容维护</a> </p> </td> </tr> '), 
        $out += " ", 2 == userType() && ($out += " ", $each($data, function($value) {
            $out += " <tr> <td>", $out += $escape($value.shopname), $out += "</td> <td>", $out += $escape($value.addr), 
            $out += "</td> <td>", $out += $escape($value.businessour), $out += "</td> <td>", 
            $out += $escape($value.fixphone), $out += "</td> </tr> ";
        }), $out += " "), $out += " </tbody> ", new String($out);
    });
});