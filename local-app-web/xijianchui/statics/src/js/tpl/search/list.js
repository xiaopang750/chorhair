/*TMODJS:{"version":4,"md5":"48fb87433ca9d7da17d42bd6e2a60442"}*/
define(function(require) {
    return require("../templates")("search/list", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), data = $data.data, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $out += " ", $each(data, function($value) {
            $out += ' <li> <div class="col-11 ba-auto"> <dl> <dt class="ba-mb-10">', $out += $escape($value.type), 
            $out += "</dt> <dd> ", $each($value.words, function($value) {
                $out += " <span hot-tag>", $out += $escape($value.hotword), $out += "</span> ";
            }), $out += " </dd> </dl> </div> </li> ";
        }), new String($out);
    });
});