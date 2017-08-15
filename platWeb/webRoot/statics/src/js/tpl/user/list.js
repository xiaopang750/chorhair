/*TMODJS:{"version":1,"md5":"d2b648629968cc71e01ebff8f40c619a"}*/
define(function(require) {
    return require("../templates")("user/list", function($data) {
        "use strict";
        var $utils = this, $escape = ($utils.$helpers, $utils.$escape), pkOrder = $data.pkOrder, iscombo = $data.iscombo, customername = $data.customername, cellphone = $data.cellphone, comboname = $data.comboname, servicemoney = $data.servicemoney, ordermoney = $data.ordermoney, fairername = $data.fairername, $each = $utils.$each, addition = $data.addition, discount = ($data.value2, 
        $data.index2, $data.$value3, $data.$index3, $data.discount), $out = "";
        return $out += ' <li sum-list class="ba-rel" pid="', $out += $escape(pkOrder), $out += '"> <span class="packType"> ', 
        $out += iscombo ? ' <span class="ba-red"> 套餐订单 </span> ' : ' <span class="ba-qing"> 服务订单 </span> ', 
        $out += ' </span> <div> <table cellpadding="0" cellspacing="0" border="0" class="ba-mb-5"> <tr> <td width="65"> 客人: </td> <td> ', 
        $out += $escape(customername), $out += " </td> </tr> <tr> <td> 电话号码: </td> <td> ", 
        $out += $escape(cellphone), $out += " </td> </tr> <tr> <td> 套餐: </td> <td> ", $out += $escape(comboname), 
        $out += " </td> </tr> <tr> ", servicemoney ? ($out += ' <td width="65"> 服务金额: </td> <td base> ', 
        $out += $escape(servicemoney), $out += "元 </td> ") : ($out += ' <td width="65"> 套餐金额: </td> <td base> ', 
        $out += $escape(ordermoney), $out += "元 </td> "), $out += " </tr> <tr> <td> 理发师: </td> <td> ", 
        $out += $escape(fairername), $out += ' </td> </tr> </table> <table cellpadding="0" cellspacing="0" border="0"> ', 
        $each(addition, function(value2) {
            $out += ' <tr> <td class="ba-vt-t ba-pt-5" width="70"> 附加项目: </td> <td colspan="3" other-list-wrap> <div class="ba-clearfix ba-mb-10"> <span class="ba-fl ba-mt-5 ba-mr-5">', 
            $out += $escape(value2.additionname), $out += ':</span> <input type="text" class="form-control col-3 ba-mr-5 ba-mr-10" sum-item otherPay id="', 
            $out += $escape(value2.id), $out += '" namer="', $out += $escape(value2.name), $out += '" pkAddition="', 
            $out += $escape(value2.pkAddition), $out += '" pkDetail="', $out += $escape(value2.pkDetail), 
            $out += '" additionname="', $out += $escape(value2.additionname), $out += '" value="', 
            $out += $escape(value2.additionmoney), $out += '"> <span class="ba-mt-5 ba-fl">元</span> </div> <div> ', 
            $each(value2.awardsplit, function($value3) {
                $out += ' <div class="form-group ba-fl col-4 ba-mr-5"> <label class="ba-mb-2 ba-mr-5 ba-fl">', 
                $out += $escape($value3.awardname), $out += ':</label> <input type="text" class="form-control" readonly="readonly" otherfairer="" awardmoney="', 
                $out += $escape($value3.awardmoney), $out += '" israte="', $out += $escape($value3.israte), 
                $out += '" other-list fairername="', $out += $escape($value3.fairername), $out += '" value="', 
                $out += $escape($value3.fairername), $out += '" pkfairer="', $out += $escape($value3.pkFairer), 
                $out += '" awardname="', $out += $escape($value3.awardname), $out += '"> </div> ';
            }), $out += " </div> </td> </tr> ";
        }), $out += ' <tr> <td> 优惠金额: </td> <td> <input class="form-control discount" discount type="text" value="', 
        $out += $escape(discount), $out += '"> <span class="ba-fl ba-mt-5">元</span> </td> </tr> <tr> <td> 结算方式： </td> <td> <select class="form-control paymode" paymode> <option value="1">现金</option> <option value="2">刷卡</option> <option value="3">微信</option> <option value="4">支付宝</option> </select> </td> </tr> </table> <div class="ba-clearfix"> <span class="ba-fl ba-mt-5"> 总计: <span sum-all org="', 
        $out += $escape(ordermoney), $out += '"> ', $out += $escape(ordermoney), $out += ' </span> 元 </span> </div> </div> <a href="javascript:;" class="btn btn-primary sum-btn" sum-btn aid="', 
        $out += $escape(pkOrder), $out += '"> 结算 </a> </li>', new String($out);
    });
});