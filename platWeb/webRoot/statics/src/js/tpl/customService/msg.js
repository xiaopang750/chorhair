/*TMODJS:{"version":19,"md5":"f6138e15af5a6e47c749773abe985654"}*/
define(function(require) {
    return require("../templates")("customService/msg", function($data) {
        "use strict";
        var $utils = this, list = ($utils.$helpers, $data.list), $each = $utils.$each, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $out += " ", list ? ($out += " ", $each(list, function($value) {
            $out += " ", $value.pkGroup ? ($out += ' <li> <p class="ba-mb-5">To：', $out += $escape($value.groupname), 
            $out += '</p> <p class="ba-mb-5 ba-ml-10">', $out += $escape($value.content), $out += '</p> <p class="ba-tr">', 
            $out += $escape($value.sendtime), $out += "</p> </li> ") : ($out += ' <li> <p class="ba-mb-5">To：', 
            $out += $escape($value.membername), $out += '</p> <p class="ba-mb-5 ba-ml-10">', 
            $out += $escape($value.content), $out += '</p> <p class="ba-tr">', $out += $escape($value.sendtime), 
            $out += "</p> </li> "), $out += " ";
        }), $out += " ") : $out += ' <li class="ba-tc">暂无消息记录</li> ', new String($out);
    });
});