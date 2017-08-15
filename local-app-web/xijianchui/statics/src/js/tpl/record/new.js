/*TMODJS:{"version":10,"md5":"5c212b150a0299d279085a3ab241cc36"}*/
define(function(require) {
    return require("../templates")("record/new", function($data) {
        "use strict";
        var $utils = this, replyman = ($utils.$helpers, $data.replyman), $escape = $utils.$escape, spitslotorid = $data.spitslotorid, spitslotor = $data.spitslotor, replymanid = $data.replymanid, ts = $data.ts, spitslotcontent = $data.spitslotcontent, $out = "";
        return $out += ' <li class="ba-mb-5" comment-item> <dl class="ba-clearfix"> <dd> <div class="ba-clearfix ba-mb-5"> ', 
        replyman ? ($out += " <h5 spitslotor-id=", $out += $escape(spitslotorid), $out += ' class="ba-fl namer">', 
        $out += $escape(spitslotor), $out += '</h5><span class="ba-fl ba-ml-5 ba-mr-5">回复</span><h5 replyman-id=', 
        $out += $escape(replymanid), $out += ' class="ba-fl namer">', $out += $escape(replyman), 
        $out += "</h5> ") : ($out += " <h5 spitslotor-id=", $out += $escape(spitslotorid), 
        $out += ' class="ba-fl namer">', $out += $escape(spitslotor), $out += "</h5> "), 
        $out += ' <span class="comment-time ba-fr">', $out += $escape(ts), $out += "</span> </div> <p>", 
        $out += $escape(spitslotcontent), $out += "</p> </dd> </dl> </li> ", new String($out);
    });
});