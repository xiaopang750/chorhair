/*TMODJS:{"version":42,"md5":"86b6ce00cfad56efcc12f34bb09da96e"}*/
define(function(require) {
    return require("../templates")("shop/shopList", function($data) {
        "use strict";
        var $utils = this, $helpers = $utils.$helpers, list = $data.list, $each = $utils.$each, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $string = $utils.$string, getRoute = $helpers.getRoute, $out = "";
        return $out += ' <thead> <tr> <td width="20%">店铺名称</td> <td width="20%">店铺地址</td> <td width="20%">营业时间</td> <td width="20%">联系电话</td> <td width="20%">操作</td> </tr> </thead> <tbody> ', 
        list ? ($out += " ", $each(list, function($value) {
            $out += " <tr> <td>", $out += $escape($value.shopname), $out += "</td> <td>", $out += $escape($value.addr), 
            $out += "</td> <td>", $out += $escape($value.businessour), $out += "</td> <td>", 
            $out += $escape($value.fixphone), $out += '</td> <td> <p class="ba-mb-10"> <a class="btn btn-normal" shop-edit pkshop="', 
            $out += $escape($value.pkShop), $out += '" shopname="', $out += $escape($value.shopname), 
            $out += '" provincename="', $out += $escape($value.provincename), $out += '" shopcode="', 
            $out += $escape($value.shopcode), $out += '" cityname="', $out += $escape($value.cityname), 
            $out += '" countyname="', $out += $escape($value.countyname), $out += '" addr="', 
            $out += $escape($value.addr), $out += '" businessour="', $out += $escape($value.businessour), 
            $out += '" fixphone="', $out += $escape($value.fixphone), $out += '">店铺信息</a> </p> <p> <a class="btn btn-normal" href="', 
            $out += $string(getRoute([ "content/addEdit" ])), $out += "?pkType=", $out += $escape($value.pkShop), 
            $out += "&pkShop=", $out += $escape($value.pkShop), $out += "&contenttype=shop&address=", 
            $out += $escape($value.addr), $out += "&time=", $out += $escape($value.businessour), 
            $out += "&phone=", $out += $escape($value.fixphone), $out += "&shopname=", $out += $escape($value.shopname), 
            $out += "&location=", $out += $escape($value.location), $out += '">内容维护</a> </p> </td> </tr> ';
        }), $out += " ") : $out += ' <tr> <td colspan="5">暂无数据</td> </tr> ', $out += " </tbody> ", 
        new String($out);
    });
});