/*TMODJS:{"version":9,"md5":"d6e9cf2ebe2f5af1a0f3bf8b60d37339"}*/
define(function(require) {
    return require("../../templates")("customer/search/index", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), searchList = $data.searchList, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $out += " ", $each(searchList, function($value) {
            $out += ' <li> <div class="col-11 ba-auto"> <dl> <dt class="ba-mb-10">', $out += $escape($value.category), 
            $out += "</dt> <dd> ", $each($value.labels, function($value) {
                $out += " <span>", $out += $escape($value), $out += "</span> ";
            }), $out += " </dd> </dl> </div> </li> ";
        }), new String($out);
    });
});