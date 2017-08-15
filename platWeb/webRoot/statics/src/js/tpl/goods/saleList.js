/*TMODJS:{"version":1,"md5":"d17cda2c6a29c4d9f7c8867d57d57a46"}*/
define(function(require) {
    return require("../templates")("goods/saleList", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), data = $data.data, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $out += " ", $each(data, function($value, $index) {
            $out += ' <span> <input type="checkbox" id="', $out += $escape($index), $out += '" ', 
            "Y" == $value.selectaward && ($out += 'checked="checked"'), $out += ' awardname="', 
            $out += $escape($value.awardname), $out += '" nowId="', $out += $escape($value.pkCustomeraward), 
            $out += '" selectId="', $out += $escape($value.pkComboAward), $out += '" awardvalue="', 
            $out += $escape($value.awardvalue), $out += '" sale-item> <label for="', $out += $escape($index), 
            $out += '">', $out += $escape($value.awardname), $out += "（", $out += $escape($value.awardvalue), 
            $out += "元）</label> </span> ";
        }), new String($out);
    });
});