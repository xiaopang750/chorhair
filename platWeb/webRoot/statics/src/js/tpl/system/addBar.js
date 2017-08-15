/*TMODJS:{"version":1,"md5":"96f820a49b6ce2dbd2c22ca8445b5b81"}*/
define(function(require) {
    return require("../templates")("system/addBar", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), data = $data.data, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $each(data, function($value) {
            $out += ' <li list-main gid="', $out += $escape($value.pkShopGroup), $out += '"> <div class="item" hover-list> <span class="hover-item" list-item gid="', 
            $out += $escape($value.pkShopGroup), $out += '" get-sub> <span> ', $out += $escape($value.groupname), 
            $out += ' </span> <span class="fa fa-angle-down"></span> <span class="fa fa-angle-up"></span> </span> <span class="func-icon" func-icon>  <span class="hover-item fa fa-edit" title="编辑" gid="', 
            $out += $escape($value.pkShopGroup), $out += '" group-eidt></span> </span> </div> <div class="sub-wrap"> <ul sub-wrap> </ul> <div class="sub-add item" sub-add gid="', 
            $out += $escape($value.pkShopGroup), $out += '"> <span class="fa fa-plus"></span> <span>新项目</span> </div> </div> </li> ';
        }), new String($out);
    });
});