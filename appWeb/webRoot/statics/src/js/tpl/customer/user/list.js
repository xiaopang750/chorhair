/*TMODJS:{"version":1,"md5":"dd3e0a046c971a2ec6a157dca56af401"}*/
define(function(require) {
    return require("../../templates")("customer/user/list", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), list = $data.list, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $out += " ", $each(list, function($value) {
            $out += ' <li comment-item> <dl class="ba-clearfix"> <dt class="ba-fl">  <img src="', 
            $out += $escape($value.img), $out += '" alt=""> </dt> <dd class="ba-fr"> <div class="ba-clearfix ba-mt-4 ba-mb-5"> <h5 class="ba-fl" comment-user>', 
            $out += $escape($value.user), $out += '</h5> <span class="comment-time">', $out += $escape($value.time), 
            $out += "</span> </div> <p>", $out += $escape($value.content), $out += "</p> </dd> </dl> </li> ";
        }), new String($out);
    });
});