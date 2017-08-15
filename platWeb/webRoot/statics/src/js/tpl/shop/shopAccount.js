/*TMODJS:{"version":46,"md5":"2580d432c646938d482f17b16e26bbfb"}*/
define(function(require) {
    return require("../templates")("shop/shopAccount", function($data) {
        "use strict";
        var $utils = this, list = ($utils.$helpers, $data.list), $each = $utils.$each, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $out += ' <thead> <tr> <td width="20%">账号</td> <td width="20%">所属店铺</td> <td width="10%">操作人</td> <td width="20%">手机号码</td> <td width="20%">注册时间</td> <td width="10%">操作</td> </tr> </thead> <tbody> ', 
        list ? ($out += " ", $each(list, function($value) {
            $out += " <tr> <td>", $out += $escape($value.shopusercode), $out += "</td> <td>", 
            $out += $escape($value.shopname), $out += "</td> <td>", $out += $escape($value.shopusername), 
            $out += "</td> <td>", $out += $escape($value.cellphone), $out += "</td> <td>", $out += $escape($value.registertime), 
            $out += '</td> <td> <p> <a class="btn btn-normal" account-edit pkShop="', $out += $escape($value.pkShop), 
            $out += '" pkShopuser="', $out += $escape($value.pkShopuser), $out += '" shopusercode="', 
            $out += $escape($value.shopusercode), $out += '" shopusername="', $out += $escape($value.shopusername), 
            $out += '" cellphone="', $out += $escape($value.cellphone), $out += '">编辑账号</a> </p> </td> </tr> ';
        }), $out += " ") : $out += ' <tr> <td colspan="6">暂无数据</td> </tr> ', $out += " </tbody> ", 
        new String($out);
    });
});