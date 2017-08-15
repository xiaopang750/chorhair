/*TMODJS:{"version":16,"md5":"7fd3c7c2d51438d82e26efc856328f68"}*/
define(function(require) {
    return require("../templates")("comment/new", function($data) {
        "use strict";
        var $utils = this, $escape = ($utils.$helpers, $utils.$escape), img = $data.img, user = $data.user, time = $data.time, content = $data.content, $out = "";
        return $out += ' <li comment-item> <dl class="ba-clearfix"> <dt class="ba-fl">  <img src="', 
        $out += $escape(img), $out += '" alt=""> </dt> <dd class="ba-fr"> <div class="ba-clearfix ba-mt-4 ba-mb-5"> <h5 class="ba-fl" comment-user>', 
        $out += $escape(user), $out += '</h5> <span class="comment-time">', $out += $escape(time), 
        $out += "</span> </div> <p>", $out += $escape(content), $out += "</p> </dd> </dl> </li> ", 
        new String($out);
    });
});