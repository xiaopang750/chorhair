/*TMODJS:{"version":2,"md5":"e403919ab3a70801216371107769b701"}*/
define(function(require) {
    return require("../../templates")("shop/system/dbBarSub", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), data = $data.data, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $each(data, function($value) {
            $out += ' <li list-sub pid="', $out += $escape($value.pkAddition), $out += '" pname="', 
            $out += $escape($value.additionname), $out += '" price="', $out += $escape($value.price), 
            $out += '"> <div class="item" hover-list> <span class="hover-item" look> ', $out += $escape($value.additionname), 
            $out += ' </span> <span class="func-icon" func-icon> <span class="hover-item fa fa-edit" title="编辑" edit></span>  </span> </div> </li> ';
        }), new String($out);
    });
});