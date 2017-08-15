/*TMODJS:{"version":73,"md5":"b0d61cb4c477e899e6b418c5aad3df2b"}*/
define(function(require) {
    return require("../../templates")("shop/shop/shopList", function($data) {
        "use strict";
        var $utils = this, $helpers = $utils.$helpers, userType = $helpers.userType, $escape = $utils.$escape, shopname = $data.shopname, addr = $data.addr, businessour = $data.businessour, fixphone = $data.fixphone, shopshortcode = $data.shopshortcode, $string = $utils.$string, getRoute = $helpers.getRoute, pkShop = $data.pkShop, location = $data.location, $each = $utils.$each, list = $data.list, $out = ($data.$value, 
        $data.$index, "");
        return $out += " ", $out += 1 == userType() ? ' <thead> <tr> <td width="20%">店铺名称</td> <td width="20%">店铺地址</td> <td width="20%">营业时间</td> <td width="20%">联系电话</td> <td width="20%">操作</td> </tr> </thead> ' : ' <thead> <tr> <td width="25%">店铺名称</td> <td width="25%">店铺地址</td> <td width="25%">营业时间</td> <td width="25%">联系电话</td> </tr> </thead> ', 
        $out += " <tbody> ", 1 == userType() && ($out += " <tr shop-info> <td>", $out += $escape(shopname), 
        $out += "</td> <td>", $out += $escape(addr), $out += "</td> <td>", $out += $escape(businessour), 
        $out += "</td> <td>", $out += $escape(fixphone), $out += '</td> <td> <p class="ba-mb-10"> <a class="btn btn-normal" shop-edit shopshortcode="', 
        $out += $escape(shopshortcode), $out += '" shopcode="', $out += $escape(shopshortcode), 
        $out += '" shopname="', $out += $escape(shopname), $out += '">店铺信息</a> </p> <p class="ba-mb-10"> <a class="btn btn-normal" href="', 
        $out += $string(getRoute([ "content/addEdit" ])), $out += "?pkType=", $out += $escape(pkShop), 
        $out += "&pkShop=", $out += $escape(pkShop), $out += "&contenttype=shop&address=", 
        $out += $escape(addr), $out += "&time=", $out += $escape(businessour), $out += "&phone=", 
        $out += $escape(fixphone), $out += "&shopname=", $out += $escape(shopname), $out += "&location=", 
        $out += $escape(location), $out += '">内容维护</a> </p> <p> <a class="btn btn-normal" order-code>订单二维码</a> </p> </td> </tr> '), 
        $out += " ", 2 == userType() && ($out += " ", $each(list, function($value) {
            $out += " <tr> <td>", $out += $escape($value.shopname), $out += "</td> <td>", $out += $escape($value.addr), 
            $out += "</td> <td>", $out += $escape($value.businessour), $out += "</td> <td>", 
            $out += $escape($value.fixphone), $out += "</td> </tr> ";
        }), $out += " "), $out += " </tbody> ", new String($out);
    });
});