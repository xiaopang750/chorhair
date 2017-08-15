/*TMODJS:{"version":1,"md5":"8565e84317159d45120e45ebd135a1b6"}*/
define(function(require) {
    return require("../templates")("plat/platCode", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), list = $data.list, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $out += ' <thead> <tr> <td width="10%">二维码图片</td> <td width="10%">二维码类型</td> <td width="10%">领取对象</td> <td width="10%">描述</td> <td width="10%">扫描次数</td> <td width="20%">扫描并关注数量</td> <td width="20%">成为会员数量</td> <td width="10%">管理</td> </tr> </thead> <tbody> ', 
        $each(list, function($value) {
            $out += ' <tr> <td> <img height="50px" src="', $out += $escape($value.qrcodeUrls), 
            $out += '" alt="" code-url> </td> <td> ', $out += "QR_LIMIT_SCENE" == $value.actionName ? " 永久二维码 " : " 临时二维码 ", 
            $out += " </td> <td> ", "1" == $value.operateobject ? $out += " 店铺 " : "2" == $value.operateobject ? $out += " 理发师 " : "3" == $value.operateobject ? $out += " 消费者 " : "4" == $value.operateobject ? $out += " 平台 " : "5" == $value.operateobject ? $out += " 订单 " : "6" == $value.operateobject && ($out += " 其他 "), 
            $out += " </td> <td>", $out += $escape($value.userDefined), $out += "</td> <td>", 
            $out += $escape($value.count), $out += "</td> <td>", $out += $escape($value.count), 
            $out += "</td> <td>", $out += $escape($value.count), $out += '</td> <td><a class="btn btn-normal" download-code>下载二维码</a></td> </tr> ';
        }), $out += " </tbody> ", new String($out);
    });
});