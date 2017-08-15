/*TMODJS:{"version":9,"md5":"d3a25050252ea79c29bf7b7e6947f564"}*/
define(function(require) {
    return require("../../templates")("hairer/user/account", function($data) {
        "use strict";
        var $utils = this, $escape = ($utils.$helpers, $utils.$escape), fairercode = $data.fairercode, rankname = $data.rankname, servicemoney = $data.servicemoney, servicenum = $data.servicenum, $out = "";
        return $out += ' <ul> <li> <dl class="ba-clearfix"> <dt class="ba-fl"> 会员号 </dt> <dd class="ba-fr"> <span class="data">', 
        $out += $escape(fairercode), $out += '</span> </dd> </dl> </li> <li> <dl class="ba-clearfix"> <dt class="ba-fl"> 等级 </dt> <dd class="ba-fr"> <span class="data">', 
        $out += $escape(rankname), $out += '</span> </dd> </dl> </li> <li> <dl class="ba-clearfix"> <dt class="ba-fl"> 历史业绩 </dt> <dd class="ba-fr"> <span class="data">', 
        $out += $escape(servicemoney), $out += "元/", $out += $escape(servicenum), $out += "单</span> </dd> </dl> </li> </ul>", 
        new String($out);
    });
});