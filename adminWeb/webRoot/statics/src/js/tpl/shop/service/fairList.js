/*TMODJS:{"version":25,"md5":"d06d33a6b69ea5177373d71376fcc145"}*/
define(function(require) {
    return require("../../templates")("shop/service/fairList", function($data) {
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
            $out += "?pkType=", $out += $escape($value.pkFairer), $out += '&contenttype=fairer">内容维护</a> ', 
            0 == $value.pkUser ? ($out += ' <a class="btn btn-normal ba-mr-5" href="javascript:;" reguid="', 
            $out += $escape($value.pkFairer), $out += '">注册App</a> ') : $out += " <span>已注册App</span> ", 
            $out += " </td> </tr> ";
        }), $out += " ") : $out += ' <tr> <td colspan="4">暂无数据</td> </tr> ', new String($out);
    });
});