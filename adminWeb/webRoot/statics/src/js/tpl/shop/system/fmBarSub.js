/*TMODJS:{"version":12,"md5":"0b41240ac259fdad18a110c0015f5c00"}*/
define(function(require) {
    return require("../../templates")("shop/system/fmBarSub", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), data = $data.data, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $each(data, function($value) {
            $out += ' <li list-sub pid="', $out += $escape($value.pkPrice), $out += '" pname="', 
            $out += $escape($value.servicerank), $out += '" price="', $out += $escape($value.price), 
            $out += '"> <div class="item" hover-list> <span class="hover-item" look> ', $out += $escape($value.servicerank), 
            $out += ' </span> <span class="func-icon" func-icon> <span class="hover-item fa fa-edit" title="编辑" edit></span>  </span> </div> </li> ';
        }), new String($out);
    });
});