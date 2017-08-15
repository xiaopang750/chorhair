/*TMODJS:{"version":79,"md5":"f32f5c9eae8220b6108da1f543e4f6bf"}*/
define(function(require) {
    return require("../../templates")("hairer/appoint/detail", function($data) {
        "use strict";
        var $utils = this, $helpers = $utils.$helpers, $escape = $utils.$escape, orderinfo = $data.orderinfo, addition = $data.addition, $each = $utils.$each, records = ($data.$value, 
        $data.$index, $data.records), imgPath = $helpers.imgPath, spitslots = $data.spitslots, $out = "";
        return $out += ' <section class="info-top struct"> <div class="ba-auto col-11 ba-font-16"> 订单号: ', 
        $out += $escape(orderinfo.orderno), $out += ' </div> </section> <section class="info-top struct"> <div class="ba-auto col-11"> <dl class="ba-clearfix"> <dt class="ba-fl"> <img src="http://pic.rs07.com/group1/M00/00/6E/wKgKHVTUMEWALt6lAAMp8-d2G_k841.jpg "> </dt> <dd class="ba-fl ba-clearfix ba-font-1-3 ba-dark-gray"> <div class="ba-mt-10"> <span>服务内容:</span> <span>', 
        $out += $escape(orderinfo.comboname), $out += '</span> </div> <div class="ba-mt-5"> <span>价格:</span> <span orgPrice>', 
        $out += $escape(orderinfo.servicemoney), $out += "元</span> </div> </dd> </dl> </div> </section> ", 
        addition && ($out += ' <section class="info-add struct"> <div class="ba-auto col-11"> <h3 class="ba-font-14 ba-mb-5">附加项目：</h3> <div> <table cellpadding="0" cellspacing="0" border="0" width="100%"> ', 
        $each(addition, function($value) {
            $out += ' <tr addtion-list class="addtion-list"> <td width="25%">', $out += $escape($value.additionname), 
            $out += '：</td> <td width="30%"> <input type="number" class="normal-input" value="', 
            $out += $escape($value.additionmoney), $out += '" addtion pid="', $out += $escape($value.pkAddition), 
            $out += '" name="', $out += $escape($value.additionname), $out += '" pkDetail="', 
            $out += $escape($value.pkDetail), $out += '"> </td> <td width="45%"> <span class="ba-ml-5">元</span> <span class="ba-font-20 ba-ml-20 ba-mr-20 operate" addtion-plus>+</span> <span class="ba-font-20 operate" addtion-reduce>-</span> </td> </tr> ';
        }), $out += ' <tr> <td>总项：</td> <td colspan="2"> <span addtion-sum></span>元 </td> </tr> ', 
        "002" != orderinfo.paystatus && ($out += ' <tr> <td></td> <td colspan="2"> <button class="btn btn-yellow" addtion-confirm>修改</button> </td> </tr> '), 
        $out += " </table> </div> </div> </section> "), $out += ' <section class="info-bottom struct"> <div class="ba-auto col-11"> <table width="100%" cellpadding="0" cellspacing="0" border="0" class="ba-dark-gray ba-font-1-2"> <tr> <td width="25%">服务时间：</td> <td>', 
        $out += $escape(orderinfo.ordetime), $out += " 至 ", $out += $escape(orderinfo.paytime), 
        $out += "</td> </tr> <tr> <td>服务店铺：</td> <td>", $out += $escape(orderinfo.shopname), 
        $out += "</td> </tr> <tr> <td>服务技师：</td> <td>", $out += $escape(orderinfo.fairername), 
        $out += "</td> </tr> <tr> <td>交付方式：</td> <td> ", $out += "" == orderinfo.paymode ? " 到店支付 " : " orderinfo.paymode ", 
        $out += " </td> </tr> <tr> <td>联系方式：</td> <td>", $out += $escape(orderinfo.cellphone), 
        $out += '</td> </tr>  <tr> <td>美丽记录：</td> <td> <p class="ba-mb-8">看看我的新发型~！</p> <ul class="ba-clearfix record-list" record-list-wrap> ', 
        $each(records, function($value) {
            $out += ' <li class="ba-fl" url="', $out += $escape($value.pictureurl), $out += '" thumb="', 
            $out += $escape($value.pictureshorturl), $out += '" pkBeautyrecord="', $out += $escape($value.pkBeautyrecord), 
            $out += '" photo-list uploaded="true"> <img src="', $out += $escape($value.pictureshorturl), 
            $out += '"> </li> ';
        }), $out += ' </ul> <table width="100%" cellpadding="0" cellspacing="0" border="0" class="camera"> <tr> <td width="48%"> <img src="', 
        $out += $escape(imgPath("widget/camera.png")), $out += '" alt="拍照" camera-btn class="ba-fl"> </td> <td class="ba-tc" width="50%"> <button class="btn btn-yellow ba-mt-50 ba-font-12" href="javascript:;" confirm-upload>确认上传</button> </td> </tr> </table> </td> </tr> <tr> <td>评论详情：</td> <td class="ba-tr"> <button class="btn btn-gray ba-font-12 btn-arg" comment-btn>评论</button> </td> </tr> <tr> <td colspan="2"> <div comment-wrap> <ul class="arg-list-wrap" comment-list=""> ', 
        $each(spitslots, function($value) {
            $out += ' <li class="ba-mb-5" comment-item> <dl class="ba-clearfix"> <dd> <div class="ba-clearfix ba-mb-5"> ', 
            $value.replyman ? ($out += " <h5 spitslotor-id=", $out += $escape($value.spitslotorid), 
            $out += ' class="ba-fl namer">', $out += $escape($value.spitslotor), $out += '</h5><span class="ba-fl ba-ml-5 ba-mr-5">回复</span><h5 replyman-id=', 
            $out += $escape($value.replymanid), $out += ' class="ba-fl namer">', $out += $escape($value.replyman), 
            $out += "</h5> ") : ($out += " <h5 spitslotor-id=", $out += $escape($value.spitslotorid), 
            $out += ' class="ba-fl namer">', $out += $escape($value.spitslotor), $out += "</h5> "), 
            $out += ' <span class="comment-time ba-fr">', $out += $escape($value.ts), $out += "</span> </div> <p>", 
            $out += $escape($value.spitslotcontent), $out += "</p> </dd> </dl> </li> ";
        }), $out += ' </ul>  <div class="ba-none ba-tc ba-mt-10 ba-light-gray" extend>展开评论</div>  <section class="arg-input"> <div class="ba-auto ba-clearfix"> <form comment-form> <input class="ba-font-1-0" type="text ba-fl" comment-input> <input class="ba-none" type="submit"> <span class="ba-pink ba-fr ba-pt-5 ba-pl-5 ba-pr-5 ba-font-1-1" comment-send>发送</span> </form> </div> </section> </div> </td> </tr> </table> </div> </section>', 
        new String($out);
    });
});