/*TMODJS:{"version":434,"md5":"94a8ee3f95d40a252427df8dc4913019"}*/
define(function(require) {
    return require("../../templates")("shop/member/editPackage", function($data) {
        "use strict";
        var $utils = this, nowWay = ($utils.$helpers, $data.nowWay), $escape = $utils.$escape, comboname = $data.comboname, totalcount = $data.totalcount, combobuytime = $data.combobuytime, combobegintime = $data.combobegintime, comboendtime = $data.comboendtime, fairername = $data.fairername, commissionpeople = $data.commissionpeople, $each = $utils.$each, discount = ($data.$value, 
        $data.$index, $data.discount), combomoney = $data.combomoney, pkShopCombo = $data.pkShopCombo, pkCustomerCombo = $data.pkCustomerCombo, $out = "";
        return $out += ' <div class="edit-box" script-bound="package-wrap"> <table class="no-border table ba-tl" width="100%"> <tr> <td width="90"> 选择套餐名称： </td> <td colspan="3" width="300"> <div script-role="check-wrap"> <input type="text" class="form-control col-8 ', 
        "edit" == nowWay && ($out += "noInput"), $out += '" form_check="sys" ischeck="true" name="comboname" tip="此项为必填" wrong="此项为必填" re="(.+)" comboname value="', 
        $out += $escape(comboname), $out += '" ', "edit" == nowWay && ($out += 'infoDisabled="infoDisabled"'), 
        $out += '> </div> </td> </tr> <tr> <td width="90"> 套餐次数： </td> <td colspan="3"> <input type="text" class="form-control col-8 ', 
        "edit" == nowWay && ($out += "noInput"), $out += '" value="', $out += $escape(totalcount), 
        $out += '" disabled="disabled" pack-count> </td> </tr> <!-- <tr> <td width="90"> 购买时间： </td> <td colspan="3"> <input type="text" class="form-control combobuytime" name="combobuytime" value="', 
        $out += $escape(combobuytime), $out += '" disabled="disabled"> </td> </tr> <tr> <td width="90"> 开卡时间： </td> <td> <div script-role="check-wrap"> <input type="text" class="form-control" box-start name="combobegintime" value="', 
        $out += $escape(combobegintime), $out += '" disabled="disabled"> </div> </td> <td> 结束时间： </td> <td> <div script-role="check-wrap"> <input type="text" class="form-control" box-end name="comboendtime" value="', 
        $out += $escape(comboendtime), $out += '" disabled="disabled"> </div> </td> </tr> --> <tr> <td width="90" style="vertical-align:top;"> 提成方式： </td> <td colspan="3"> <div script-role="check-wrap"> <!-- <input type="text" class="form-control col-8 ', 
        "edit" == nowWay && ($out += "noInput"), $out += '" form_check="sys" ischeck="true" name="fairername" tip="此项为必填" wrong="此项为必填" re="(.+)" fairer readonly="readonly" value="', 
        $out += $escape(fairername), $out += '" ', "edit" == nowWay && ($out += 'infoDisabled="infoDisabled"'), 
        $out += "> --> ", "edit" != nowWay ? $out += ' <ul class="ba-clearfix" ticheng-list-wrap> </ul> ' : ($out += " ", 
        commissionpeople.length && ($out += " ", $each(commissionpeople, function($value) {
            $out += ' <li class="form-group ba-fl col-3 ba-mr-20" ticheng-item> <label class="ba-mb-2 ba-mr-5 ba-fl">', 
            $out += $escape($value.awardname), $out += ':</label> <input type="text" class="form-control noInput" readonly="readonly" value="', 
            $out += $escape($value.fairername), $out += '"> </li> ';
        }), $out += " "), $out += " "), $out += ' </div> </td> </tr> <tr> <td width="90"> 优惠金额： </td> <td colspan="3"> <div script-role="check-wrap"> <input type="text" class="form-control col-6 ', 
        "edit" == nowWay && ($out += "noInput"), $out += '" sale form_check="sys" ischeck="free" name="discount" tip="此项为必填" wrong="优惠金额格式不正确" re="((\\d|\\.)+)" value="', 
        $out += $escape(discount), $out += '" ', "edit" == nowWay && ($out += 'disabled="disabled"'), 
        $out += '> </div> </td> </tr> <tr sale-wrap class="ba-none"> <td width="90"> 选择抵用券： </td> <td colspan="3"> <div script-role="check-wrap"> <select sale-list-wrap class="form-control"> </select> </div> </td> </tr> <tr> <td width="90"> 合计： </td> <td colspan="3"> <span sum> ', 
        combomoney ? ($out += " ", $out += $escape(combomoney), $out += " ") : $out += " 0 ", 
        $out += ' </span>元 </td> </tr> </table> <div class="ba-tc"> <a href="javascript:;" class="btn btn-primary" script-role="package-btn" money="', 
        $out += $escape(combomoney), $out += '" pkShopCombo="', $out += $escape(pkShopCombo), 
        $out += '" fairerStr="', $out += $escape(commissionpeople), $out += '" pkCustomerCombo="', 
        $out += $escape(pkCustomerCombo), $out += '"> 确定 </a> <a href="javascript:;" class="btn btn-danger" sc="close">取消</a> </div> </div> ', 
        new String($out);
    });
});