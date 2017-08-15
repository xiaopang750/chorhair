/*TMODJS:{"version":7,"md5":"b95c61848c551961a26e959c381bec22"}*/
define(function(require) {
    return require("../templates")("goods/stockRecord", function($data) {
        "use strict";
        var $utils = this, list = ($utils.$helpers, $data.list), $each = $utils.$each, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $out += " ", list ? ($out += " ", $each(list, function($value) {
            $out += " <tr list> <td>", $out += $escape($value.operatetime), $out += "</td> <td>", 
            $out += $escape($value.operator), $out += "</td> <td>", $out += $escape($value.operatecontent), 
            $out += "</td> </tr> ";
        }), $out += " ") : $out += ' <tr> <td colspan="3">暂无数据</td> </tr> ', new String($out);
    });
});