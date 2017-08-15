/*TMODJS:{"version":49,"md5":"77adfea47754ee83f578b93998596d83"}*/
define(function(require) {
    return require("../../templates")("shop/system/fileMangeBar", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), data = $data.data, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $each(data, function($value) {
            $out += ' <li list-main gid="', $out += $escape($value.pkShopGroup), $out += '"> <div class="item" hover-list> <span class="hover-item" list-item> <span gid="', 
            $out += $escape($value.pkShopGroup), $out += '" get-sub> ', $out += $escape($value.groupname), 
            $out += ' </span> <span class="fa fa-angle-down"></span> <span class="fa fa-angle-up"></span> </span> <span class="func-icon" func-icon>  <span class="hover-item fa fa-edit" title="编辑" gid="', 
            $out += $escape($value.pkShopGroup), $out += '" group-eidt></span> </span> </div> <div class="sub-wrap"> <ul sub-wrap> </ul> <div class="sub-add item" sub-add gid="', 
            $out += $escape($value.pkShopGroup), $out += '"> <span class="fa fa-plus"></span> <span>新价位</span> </div> </div> </li> ';
        }), new String($out);
    });
});