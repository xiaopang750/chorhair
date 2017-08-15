/*TMODJS:{"version":162,"md5":"312e11909a62cc65f2515da5207e2874"}*/
define(function(require) {
    return require("../../templates")("shop/member/package", function($data) {
        "use strict";
        var $utils = this, $helpers = $utils.$helpers, list = $data.list, $each = $utils.$each, $escape = ($data.$value, 
        $data.$index, $utils.$escape), timeLeft = $helpers.timeLeft, curdate = $data.curdate, $out = "";
        return $out += " ", list ? ($out += " ", $each(list, function($value) {
            $out += " <tr list-info> <td packname>", $out += $escape($value.comboname), $out += "</td> <td> ", 
            1 == $value.combotype ? $out += " 全年不限次 " : ($out += " ", $out += $escape($value.leftcount), 
            $out += "次 "), $out += " </td> <td>", $out += $escape($value.combobuytime), $out += "</td> <td>", 
            $out += $escape($value.combobegintime), $out += "</td> <td> ", $value.comboendtime && ($out += " ", 
            $out += $escape(timeLeft($value.comboendtime, curdate)), $out += "天 "), $out += ' </td> <td class="ba-tl"> <p class="ba-mb-5">理发师:', 
            $out += $escape($value.lastfairername), $out += '</p> <p class="ba-mb-5">金额:', $out += $escape($value.lastmoney), 
            $out += "</p> <p>时间:", $out += $escape($value.lastusetime), $out += "</p> </td> <td money>", 
            $out += $escape($value.combomoney), $out += '</td> <td> <a class="btn btn-normal" href="', 
            $out += $escape($value.orderUrl), $out += "&spid=", $out += $escape($value.pkShopCombo), 
            $out += "&comboid=", $out += $escape($value.pkCustomerCombo), $out += '" order spid="', 
            $out += $escape($value.pkShopCombo), $out += '">下单</a> <!-- <a class="btn btn-normal" href="javascript:;" aid="', 
            $out += $escape($value.pkCustomerCombo), $out += '" ', 0 == $value.pkCustomerCombo && ($out += 'class="ba-hidden"'), 
            $out += " edit>编辑</a> --> </td> </tr> ";
        }), $out += " ") : $out += ' <tr> <td colspan="8">暂无数据</td> </tr> ', new String($out);
    });
});