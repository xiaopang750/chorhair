/*TMODJS:{"version":14,"md5":"b960f49159b440afe4a15848f2ad7e0c"}*/
define(function(require) {
    return require("../templates")("goods/platBookList", function($data) {
        "use strict";
        var $utils = this, $helpers = $utils.$helpers, list = $data.list, $each = $utils.$each, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $string = $utils.$string, getRoute = $helpers.getRoute, $out = "";
        return $out += " ", list ? ($out += " ", $each(list, function($value) {
            $out += ' <tr list pkProductBook="', $out += $escape($value.pkProductBook), $out += '"> <td>', 
            $out += $escape($value.bookno), $out += "</td> <td>", $out += $escape($value.booktime), 
            $out += "</td> <td>", $out += $escape($value.shopname), $out += "</td> <td>", $out += $escape($value.operatorname), 
            $out += "</td> <td>", $out += $escape($value.bookmoney), $out += "</td> <td> ", 
            1 == $value.vbillstatus ? $out += " 制单中 " : 2 == $value.vbillstatus ? $out += " 待审批 " : 3 == $value.vbillstatus ? $out += " 审批中 " : 4 == $value.vbillstatus ? $out += " 待发货 " : 5 == $value.vbillstatus ? $out += " 审批不通过 " : 6 == $value.vbillstatus ? $out += " 已驳回 " : 7 == $value.vbillstatus ? $out += " 待收货 " : 8 == $value.vbillstatus && ($out += " 已完成 "), 
            $out += " </td> <td>", $out += $escape($value.apprivenote), $out += "</td> <td> ", 
            2 == $value.vbillstatus ? ($out += ' <a class="btn btn-normal" href="', $out += $string(getRoute([ "goods/platBookAddEdit" ])), 
            $out += "?bid=", $out += $escape($value.pkProductBook), $out += '" operation="detail">详情</a> <a class="btn btn-normal" href="javascript:;" operation="pass">通过</a> <a class="btn btn-normal" href="javascript:;" operation="reject">驳回</a> ') : 4 == $value.vbillstatus ? ($out += ' <a class="btn btn-normal" href="', 
            $out += $string(getRoute([ "goods/platBookAddEdit" ])), $out += "?bid=", $out += $escape($value.pkProductBook), 
            $out += '" operation="detail">详情</a> <a class="btn btn-normal" href="javascript:;" operation="send">发货</a> ') : (6 == $value.vbillstatus || 7 == $value.vbillstatus || 8 == $value.vbillstatus) && ($out += ' <a class="btn btn-normal" href="', 
            $out += $string(getRoute([ "goods/platBookAddEdit" ])), $out += "?bid=", $out += $escape($value.pkProductBook), 
            $out += '" operation="detail">详情</a> '), $out += " </td> </tr> ";
        }), $out += " ") : $out += ' <tr> <td colspan="8">暂无数据</td> </tr> ', new String($out);
    });
});