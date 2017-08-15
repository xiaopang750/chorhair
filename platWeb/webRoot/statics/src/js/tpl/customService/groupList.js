/*TMODJS:{"version":23,"md5":"50d6063fb65c832e7adc705f25580dbe"}*/
define(function(require) {
    return require("../templates")("customService/groupList", function($data) {
        "use strict";
        var $utils = this, list = ($utils.$helpers, $data.list), $each = $utils.$each, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $out += " ", list && ($out += " ", $each(list, function($value) {
            $out += ' <li pkGroup="', $out += $escape($value.pkGroup), $out += '" groupnum="', 
            $out += $escape($value.groupnum), $out += '"> <p>', $out += $escape($value.groupname), 
            $out += '</p> <span class="group-edit" title="编辑分组" group-edit><i class="fa fa-users"></i></span> <span class="group-send" title="发送信息" group-send><i class="fa fa-comment"></i></span> <span class="group-remove" title="删除分组" group-remove><i class="fa fa-trash"></i></span> </li> ';
        }), $out += " "), new String($out);
    });
});