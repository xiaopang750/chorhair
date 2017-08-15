/*TMODJS:{"version":28,"md5":"be75317b3b2e80bec7d55eeac786c02f"}*/
define(function(require) {
    return require("../../templates")("hairer/user/info", function($data) {
        "use strict";
        var $utils = this, $escape = ($utils.$helpers, $utils.$escape), headshorturl = $data.headshorturl, nickname = $data.nickname, cellphone = $data.cellphone, sex = $data.sex, shopname = $data.shopname, $each = $utils.$each, skills = $data.skills, downLoadUrl = ($data.$value, 
        $data.$index, $data.downLoadUrl), $out = "";
        return $out += ' <li user-header> <dl class="ba-clearfix"> <dt class="ba-fl ba-mt-15">头像</dt> <dd class="ba-fr"> <div class="ba-fr"> <span class="header-pic"> <img src="', 
        $out += $escape(headshorturl), $out += '" header-img> </span> <span class="icon icon-right"></span> </div> </dd> </dl> </li> <li nick-name> <dl class="ba-clearfix"> <dt class="ba-fl">昵称</dt> <dd class="ba-fr"> <span class="data">', 
        $out += $escape(nickname), $out += '</span> <span class="icon icon-right ba-vt-m"></span> </dd> </dl> </li> <li> <dl class="ba-clearfix"> <dt class="ba-fl">联系方式</dt> <dd class="ba-fr"> <span class="data">', 
        $out += $escape(cellphone), $out += '</span> </dd> </dl> </li> <li> <dl class="ba-clearfix"> <dt class="ba-fl">性别</dt> <dd class="ba-fr"> <span class="data"> ', 
        $out += 1 == sex ? " 男 " : 2 == sex ? " 女 " : " ", $out += ' </span> </dd> </dl> </li> <li user-package> <dl class="ba-clearfix"> <dt class="ba-fl">店铺名称</dt> <dd class="ba-fr"> <span class="data">', 
        $out += $escape(shopname), $out += '</span> </dd> </dl> </li> <li user-package> <dl class="ba-clearfix"> <dt class="ba-fl">服务能力</dt> <dd class="ba-fr"> <span class="data"> ', 
        $each(skills, function($value) {
            $out += ' <span class="ba-mr-5">', $out += $escape($value.skillname), $out += "</span> ";
        }), $out += ' </span> </dd> </dl> </li> <li user-package> <a href="', $out += $escape(downLoadUrl), 
        $out += '"> <dl class="ba-clearfix"> <dt class="ba-fl">推荐下载</dt> <dd class="ba-fr"> <span class="data"> <span class="icon icon-qcode"></span> </span> <span class="icon icon-right ba-vt-m"></span> </dd> </dl> </a> </li>', 
        new String($out);
    });
});