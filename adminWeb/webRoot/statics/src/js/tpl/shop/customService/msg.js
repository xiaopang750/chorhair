/*TMODJS:{"version":8,"md5":"721db12b7739fb9646bbf7b46180186a"}*/
define(function(require) {
    return require("../../templates")("shop/customService/msg", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), data = $data.data, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $out += " ", $each(data, function($value) {
            $out += ' <li> <p class="ba-mb-10"> <span>', $out += $escape($value.ts), $out += '</span> </p> <p class="ba-ml-10"> ', 
            $out += $escape($value.lastmessage), $out += " </p> </li> ";
        }), new String($out);
    });
});