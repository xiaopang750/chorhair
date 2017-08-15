/*TMODJS:{"version":224,"md5":"aab6cfc88f9227dc416535bbda4ce9ef"}*/
define(function(require) {
    return require("../templates")("record/index", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), data = $data.data, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $out += " ", $each(data, function($value) {
            $out += ' <li class="record-list"> <div class="col-11 ba-auto"> <dl class="ba-clearfix"> <dt class="ba-fl"> <p class="ba-font-1-5">', 
            $out += $escape($value.date.day), $out += '</p> <p class="ba-font-1-3">', $out += $escape($value.date.month), 
            $out += '</p> </dt> <dd class="ba-fl"> <div class="ba-mb-7 ba-mt-5"> <span class="ba-font-1-2">今天换了个新发型~！</span> </div> <div gallery-wrap class="photo-list ba-clearfix"> ', 
            $each($value.records, function($value) {
                $out += ' <div><a href="', $out += $escape($value.pictureurl), $out += '"><img src="', 
                $out += $escape($value.pictureshorturl), $out += '" alt=""></a></div> ';
            }), $out += ' </div>  <div class="arg-area ba-mt-5"> <div comment-wrap> <ul class="arg-list-wrap" comment-list> ', 
            $each($value.spitslots, function($value) {
                $out += ' <li class="ba-mb-5" comment-item> <dl class="ba-clearfix"> <dd> <div class="ba-clearfix ba-mb-5"> ', 
                $value.replyman ? ($out += " <h5 spitslotor-id=", $out += $escape($value.spitslotorid), 
                $out += ' class="ba-fl namer">', $out += $escape($value.spitslotor), $out += '</h5><span class="ba-fl ba-ml-5 ba-mr-5">回复</span><h5 replyman-id=', 
                $out += $escape($value.replymanid), $out += ' class="ba-fl namer">', $out += $escape($value.replyman), 
                $out += "</h5> ") : ($out += " <h5 spitslotor-id=", $out += $escape($value.spitslotorid), 
                $out += ' class="ba-fl namer">', $out += $escape($value.spitslotor), $out += "</h5> "), 
                $out += ' <span class="comment-time ba-fr">', $out += $escape($value.ts), $out += "</span> </div> <p>", 
                $out += $escape($value.spitslotcontent), $out += "</p> </dd> </dl> </li> ";
            }), $out += ' </ul>  <div class="ba-none ba-tc ba-mt-10 ba-mb-4 ba-light-gray" extend>展开评论</div>  <section class="arg-input"> <div class="ba-auto ba-clearfix"> <form comment-form> <input class="ba-font-1-0" type="text ba-fl" comment-input> <input class="ba-none" type="submit"> <span class="ba-pink ba-fr ba-pt-5 ba-pl-5 ba-pr-5 ba-font-1-1" comment-send>发送</span> </form> </div> </section> </div> </div> </dd> </dl> </div> </li> ';
        }), new String($out);
    });
});