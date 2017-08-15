/*TMODJS:{"version":83,"md5":"4477b09317871e290ac5bbb176131f4d"}*/
define(function(require) {
    return require("../../templates")("shop/user/reserveList", function($data) {
        "use strict";
        var $utils = this, $escape = ($utils.$helpers, $utils.$escape), pkOrder = $data.pkOrder, customername = $data.customername, pkCustomer = $data.pkCustomer, cellphone = $data.cellphone, comboname = $data.comboname, pkCombo = $data.pkCombo, servicemoney = $data.servicemoney, ordermoney = $data.ordermoney, fairername = $data.fairername, appointtime = $data.appointtime, pkShopCombo = $data.pkShopCombo, pkFairer = $data.pkFairer, $out = "";
        return $out += ' <li reserve-list class="ba-rel" spid="', $out += $escape(pkOrder), 
        $out += '"> <span class="packType"> <span class="ba-qing"> 预约单 </span> </span> <div> <table cellpadding="0" cellspacing="0" border="0" class="ba-mb-5"> <tr> <td width="65"> 客人: </td> <td name="', 
        $out += $escape(customername), $out += '" pkCustomer="', $out += $escape(pkCustomer), 
        $out += '"> ', $out += $escape(customername), $out += " </td> </tr> <tr> <td> 电话号码: </td> <td> ", 
        $out += $escape(cellphone), $out += ' </td> </tr> <tr> <td> 套餐: </td> <td pkName="', 
        $out += $escape(comboname), $out += '" comboid="', $out += $escape(pkCombo), $out += '"> ', 
        $out += $escape(comboname), $out += " </td> </tr> <tr> ", servicemoney ? ($out += ' <td width="65"> 服务金额: </td> <td base> ', 
        $out += $escape(servicemoney), $out += "元 </td> ") : ($out += ' <td width="65"> 套餐金额: </td> <td base> ', 
        $out += $escape(ordermoney), $out += "元 </td> "), $out += " </tr> <tr> <td> 理发师: </td> <td> ", 
        $out += $escape(fairername), $out += " </td> </tr> <tr> <td> 预约时间: </td> <td> ", 
        $out += $escape(appointtime), $out += ' </td> </tr> </table> </div> <div class="ba-tr"> <a href="appoint?pkName=', 
        $out += $escape(comboname), $out += "&pkCustomer=", $out += $escape(pkCustomer), 
        $out += "&spid=", $out += $escape(pkShopCombo), $out += "&comboid=", $out += $escape(pkCombo), 
        $out += "&name=", $out += $escape(customername), $out += "&pkorder=", $out += $escape(pkOrder), 
        $out += "&pkfairer=", $out += $escape(pkFairer), $out += '" class="btn btn-normal" edit-reserve-btn aid="', 
        $out += $escape(pkOrder), $out += '">编辑预约单</a> <a href="javascript:;" class="btn btn-danger" cancel-reserve-btn aid="', 
        $out += $escape(pkOrder), $out += '">取消预约单</a> <a href="javascript:;" class="btn btn-primary" create-order-btn aid="', 
        $out += $escape(pkOrder), $out += '">生成订单</a> </div> </li>', new String($out);
    });
});