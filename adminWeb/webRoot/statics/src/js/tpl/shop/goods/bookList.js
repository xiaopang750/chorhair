/*TMODJS:{"version":111,"md5":"4d1f20dc2bd6f2a4912cc2fd4f6fd09e"}*/
define(function(require) {
    return require("../../templates")("shop/goods/bookList", function($data) {
        "use strict";
        var $utils = this, $helpers = $utils.$helpers, list = $data.list, $each = $utils.$each, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $string = $utils.$string, getRoute = $helpers.getRoute, $out = "";
        return $out += " ", list ? ($out += " ", $each(list, function($value) {
            $out += " <tr list bid=", $out += $escape($value.pkProductBook), $out += "> <td>", 
            $out += $escape($value.bookno), $out += "</td> <td>", $out += $escape($value.booktime), 
            $out += "</td> <td>", $out += $escape($value.operatorname), $out += "</td> <td>", 
            $out += $escape($value.bookmoney), $out += "</td> <td data-status> ", 1 == $value.vbillstatus ? $out += " 制单中 " : 2 == $value.vbillstatus ? $out += " 已提交 " : 3 == $value.vbillstatus ? $out += " 审批中 " : 4 == $value.vbillstatus ? $out += " 待发货 " : 5 == $value.vbillstatus ? $out += " 审批不通过 " : 6 == $value.vbillstatus ? $out += " 已驳回 " : 7 == $value.vbillstatus ? $out += " 待收货 " : 8 == $value.vbillstatus && ($out += " 已完成 "), 
            $out += " </td> <td>", $out += $escape($value.apprivenote), $out += "</td> <td> ", 
            1 == $value.vbillstatus && ($out += ' <a class="btn btn-normal" href="', $out += $string(getRoute([ "goods/bookAddEdit" ])), 
            $out += "?bid=", $out += $escape($value.pkProductBook), $out += '" operation="edit">编辑</a> <a class="btn btn-normal" href="javascript:;" bid=', 
            $out += $escape($value.pkProductBook), $out += ' operation="submit">提交</a> '), $out += " ", 
            (2 == $value.vbillstatus || 4 == $value.vbillstatus || 8 == $value.vbillstatus) && ($out += ' <a class="btn btn-normal" href="', 
            $out += $string(getRoute([ "goods/bookAddEdit" ])), $out += "?bid=", $out += $escape($value.pkProductBook), 
            $out += '" operation="detail">详情</a> '), $out += " ", 7 == $value.vbillstatus && ($out += ' <a class="btn btn-normal" href="', 
            $out += $string(getRoute([ "goods/bookAddEdit" ])), $out += "?bid=", $out += $escape($value.pkProductBook), 
            $out += '" operation="detail">详情</a> <a class="btn btn-normal" href="javascript:;" bid="', 
            $out += $escape($value.pkProductBook), $out += '" operation="confirm">确认收货</a> '), 
            $out += " ", 6 == $value.vbillstatus && ($out += ' <a class="btn btn-normal" href="', 
            $out += $string(getRoute([ "goods/bookAddEdit" ])), $out += "?bid=", $out += $escape($value.pkProductBook), 
            $out += '" operation="edit">编辑</a> '), $out += ' <a class="btn btn-normal" href="', 
            $out += $string(getRoute([ "goods/bookAddEdit" ])), $out += "?bid=", $out += $escape($value.pkProductBook), 
            $out += '&sc=rebook" operation="rebook">再次订货</a> </td> </tr> ';
        }), $out += " ") : $out += ' <tr> <td colspan="7">暂无数据</td> </tr> ', new String($out);
    });
});