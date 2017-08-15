/*TMODJS:{"version":12,"md5":"3a163f1fc8b8bad52cc78f5917e2d873"}*/
define(function(require) {
    return require("../../templates")("customer/search/detail", function($data) {
        "use strict";
        var $utils = this, $helpers = $utils.$helpers, $each = $utils.$each, searchList = $data.searchList, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $string = $utils.$string, cut = $helpers.cut, $out = "";
        return $out += " ", $each(searchList, function($value) {
            $out += ' <li> <dl> <dt> <img src="', $out += $escape($value.img), $out += '"> <div class="fav"> <span class="icon icon-good ba-font-18"></span> <span>', 
            $out += $escape($value.good), $out += '</span> </div> </dt> <dd> <div class="col-11 ba-auto"> <p>', 
            $out += $string(cut($value.title, 10)), $out += "</p> </div> </dd> </dl> </li> ";
        }), new String($out);
    });
});