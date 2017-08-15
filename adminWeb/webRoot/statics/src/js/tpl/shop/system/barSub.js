/*TMODJS:{"version":26,"md5":"3500c0865ca20de170c89d301199e469"}*/
define(function(require) {
    return require("../../templates")("shop/system/barSub", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), data = $data.data, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $each(data, function($value) {
            $out += ' <li list-sub pid="', $out += $escape($value.pkAddition), $out += '" pname="', 
            $out += $escape($value.additionname), $out += '" price="', $out += $escape($value.additionmoney), 
            $out += '"> <div class="item" hover-list> <span class="hover-item" look> ', $out += $escape($value.additionname), 
            $out += ' </span> <span class="func-icon" func-icon> <span class="hover-item fa fa-edit" title="编辑" edit></span>  </span> </div> </li> ';
        }), new String($out);
    });
});