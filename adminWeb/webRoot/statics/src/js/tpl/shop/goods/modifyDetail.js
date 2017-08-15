/*TMODJS:{"version":13,"md5":"0ae3880ce89a39ab354467267e983832"}*/
define(function(require) {
    return require("../../templates")("shop/goods/modifyDetail", function($data) {
        "use strict";
        var $utils = this, list = ($utils.$helpers, $data.list), $each = $utils.$each, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return list ? ($out += " ", $each(list, function($value) {
            $out += " <tr> <td>", $out += $escape($value.edittime), $out += "</td> <td>", $out += $escape($value.editor), 
            $out += "</td> <td>", $out += $escape($value.editcontent), $out += "</td> </tr> ";
        }), $out += " ") : $out += ' <tr> <td colspan="3">暂无数据</td> </tr> ', new String($out);
    });
});