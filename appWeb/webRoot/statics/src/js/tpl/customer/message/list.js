/*TMODJS:{"version":42,"md5":"63b61900496c1de9d967650701422fad"}*/
define(function(require) {
    return require("../../templates")("customer/message/list", function($data) {
        "use strict";
        var $utils = this, $helpers = $utils.$helpers, $each = $utils.$each, data = $data.data, $escape = ($data.$value, 
        $data.$index, $utils.$escape), noShowStatus = $data.noShowStatus, $string = $utils.$string, cut = $helpers.cut, $out = "";
        return $out += " ", $each(data, function($value) {
            $out += ' <li list> <dl class="ba-clearfix"> <dt class="ba-mr-10 ba-fl"> </dt> <dd class="ba-fl text" look pkMessage="', 
            $out += $escape($value.pkMessage), $out += '" status="', $out += $escape($value.messagestatus), 
            $out += '" tranParam="002" fullMsg="', $out += $escape($value.content), $out += '"> <div class="ba-mb-5 ba-mt-2"> <span>系统消息</span> ', 
            "yes" != noShowStatus && ($out += ' <span class="ba-pink" msg-status="', $out += $escape($value.messagestatus), 
            $out += '"> ', "001" == $value.messagestatus ? $out += " (未读) " : "002" == $value.messagestatus ? $out += " (已读) " : "003" == $value.messagestatus && ($out += " (忽略) "), 
            $out += " </span> "), $out += " </div> <div> ", $out += $string(cut($value.content, 15)), 
            $out += ' </div> </dd> <dd class="ba-fr ba-mt-5 ba-tr"> ', "yes" == noShowStatus && ($out += ' <button class="btn btn-gray ba-mr-5" ignore pkMessage="', 
            $out += $escape($value.pkMessage), $out += '" status="', $out += $escape($value.messagestatus), 
            $out += '" tranParam="003">忽略</button> '), $out += " </dd> </dl> </li> ";
        }), new String($out);
    });
});