/*TMODJS:{"version":3,"md5":"23a881a0c4b7686d424ebd300636faca"}*/
define(function(require) {
    return require("../templates")("include/indexFooter", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), siteNav = $data.siteNav, title = ($data.$value, 
        $data.$index, $data.title), $escape = $utils.$escape, iosNavFix = $data.iosNavFix, $out = "";
        return $out += ' <ul class="ba-clearfix"> ', $each(siteNav, function($value) {
            $out += " <li ", $value.text == title && ($out += 'class="active"'), $out += '> <div class="', 
            $out += $escape(iosNavFix), $out += '"> <a href="', $out += $escape($value.url), 
            $out += '"> <p class="logo ba-mb-3"> <span class="icon ', $out += $escape($value.logo), 
            $out += '"></span> </p> <p class="text">', $out += $escape($value.text), $out += "</p> </a> </div> </li> ";
        }), $out += " </ul>", new String($out);
    });
});