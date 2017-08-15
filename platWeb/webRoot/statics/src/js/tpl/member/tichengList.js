/*TMODJS:{"version":1,"md5":"c0bc9b10aab48cee46014224e19ff0b0"}*/
define(function(require) {
    return require("../templates")("member/tichengList", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), data = $data.data, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $out += " ", $each(data, function($value) {
            $out += ' <li class="form-group ba-fl col-3 ba-mr-20" ticheng-item> <label class="ba-mb-2 ba-mr-5 ba-fl">', 
            $out += $escape($value.awardname), $out += ':</label> <input type="text" class="form-control" readonly="readonly" otherfairer="" awardmoney="', 
            $out += $escape($value.awardmoney), $out += '" israte="', $out += $escape($value.israte), 
            $out += '" awardname="', $out += $escape($value.awardname), $out += '" ticheng-list fairer> </li> ';
        }), new String($out);
    });
});