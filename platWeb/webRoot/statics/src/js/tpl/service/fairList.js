/*TMODJS:{"version":2,"md5":"1a2fb1771cc1492c2726b4967f03e7ce"}*/
define(function(require) {
    return require("../templates")("service/fairList", function($data) {
        "use strict";
        var $utils = this, $helpers = $utils.$helpers, list = $data.list, $each = $utils.$each, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $string = $utils.$string, getRoute = $helpers.getRoute, $out = "";
        return $out += " ", list ? ($out += " ", $each(list, function($value) {
            $out += " <tr> <td>", $out += $escape($value.fairername), $out += "</td> <td>", 
            $out += $escape($value.nickname), $out += "</td> <td>", $out += $escape($value.cellphone), 
            $out += '</td> <td class="ba-tl"> <a class="btn btn-normal ba-mr-5" href="', $out += $string(getRoute("service/addEdit")), 
            $out += "?cid=", $out += $escape($value.pkFairer), $out += '">编辑</a> <a class="btn btn-normal ba-mr-5" href="', 
            $out += $string(getRoute("service/addEdit")), $out += "?lid=", $out += $escape($value.pkFairer), 
            $out += '">详情</a> <a class="btn btn-normal ba-mr-5" href="', $out += $string(getRoute([ "content/addEdit" ])), 
            $out += "?pkShop=", $out += $escape($value.pkShop), $out += "&pkType=", $out += $escape($value.pkFairer), 
            $out += '&contenttype=fairer">内容维护</a> ', 0 == $value.pkUser ? ($out += ' <a class="btn btn-normal ba-mr-5" href="javascript:;" reguid="', 
            $out += $escape($value.pkFairer), $out += '">注册App</a> ') : $out += " <span>已注册App</span> ", 
            $out += " </td> </tr> ";
        }), $out += " ") : $out += ' <tr> <td colspan="4">暂无数据</td> </tr> ', new String($out);
    });
});