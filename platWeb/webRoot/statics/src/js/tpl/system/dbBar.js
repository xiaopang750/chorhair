/*TMODJS:{"version":1,"md5":"6eb8d2e1f88fef4a1c270a0f88b7b3ec"}*/
define(function(require) {
    return require("../templates")("system/dbBar", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), data = $data.data, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $each(data, function($value) {
            $out += ' <li list-sub pid="', $out += $escape($value.pkShopGroup), $out += '" pname="', 
            $out += $escape($value.groupname), $out += '"> <div class="item" hover-list> <span class="hover-item" look> ', 
            $out += $escape($value.groupname), $out += ' </span> <span class="func-icon" func-icon> <span class="hover-item fa fa-edit" title="编辑" edit></span>  </span> </div> </li> ';
        }), new String($out);
    });
});