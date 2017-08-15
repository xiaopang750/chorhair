/*TMODJS:{"version":49,"md5":"346dd331fc9dbf8c46a9750ec8f685ba"}*/
define(function(require) {
    return require("../templates")("user/info", function($data) {
        "use strict";
        var $utils = this, $escape = ($utils.$helpers, $utils.$escape), headurl = $data.headurl, headshorturl = $data.headshorturl, nickname = $data.nickname, cellphone = $data.cellphone, sex = $data.sex, $out = "";
        return $out += ' <li class="show-active" user-header> <dl class="ba-clearfix"> <dt class="ba-fl ba-mt-15">头像</dt> <dd class="ba-fr"> <div class="ba-fr" gallery-wrap> <span class="header-pic"> <a href="', 
        $out += $escape(headurl), $out += '"><img src="', $out += $escape(headshorturl), 
        $out += '" header-img></a> </span> <span class="icon icon-right"></span> </div> </dd> </dl> </li> <li class="show-active" nick-name> <dl class="ba-clearfix"> <dt class="ba-fl">昵称</dt> <dd class="ba-fr"> <span class="data">', 
        $out += $escape(nickname), $out += '</span> <span class="icon icon-right ba-vt-m"></span> </dd> </dl> </li> <li> <dl class="ba-clearfix"> <dt class="ba-fl">联系方式</dt> <dd class="ba-fr"> <span class="data">', 
        $out += $escape(cellphone), $out += '</span> </dd> </dl> </li> <li> <dl class="ba-clearfix"> <dt class="ba-fl">性别</dt> <dd class="ba-fr"> <span class="data"> ', 
        $out += 1 == sex ? " 男 " : 2 == sex ? " 女 " : " ", $out += ' </span> </dd> </dl> </li> <li class="show-active" user-package> <dl class="ba-clearfix"> <dt class="ba-fl">套餐信息</dt> <dd class="ba-fr"> <span class="data"></span> <span class="icon icon-right ba-vt-m"></span> </dd> </dl> </li>', 
        new String($out);
    });
});