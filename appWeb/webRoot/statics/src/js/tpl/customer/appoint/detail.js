/*TMODJS:{"version":573,"md5":"0c8adcdb0ca5769857971465a00f1e24"}*/
define(function(require) {
    return require("../../templates")("customer/appoint/detail", function($data) {
        "use strict";
        var $utils = this, $helpers = $utils.$helpers, $escape = $utils.$escape, orderinfo = $data.orderinfo, $each = $utils.$each, records = $data.records, imgPath = ($data.$value, 
        $data.$index, $helpers.imgPath), isupload = $data.isupload, userphoto = $data.userphoto, usershortphoto = $data.usershortphoto, spitslots = $data.spitslots, $out = "";
        return $out += ' <section class="info-top"> <div class="ba-auto col-11"> <dl class="ba-clearfix">  <dd class="ba-fl ba-clearfix ba-font-1-3 ba-dark-gray"> <div class="ba-mt-5"> <span>服务内容:</span> <span>', 
        $out += $escape(orderinfo.comboname), $out += '</span> </div> <div class="ba-mt-5"> <span>价格:</span> <span>', 
        $out += $escape(orderinfo.ordermoney), $out += '元</span> </div> </dd> </dl> </div> </section> <section class="info-bottom"> <div class="ba-auto col-11"> <table width="100%" cellpadding="0" cellspacing="0" border="0" class="ba-dark-gray ba-font-1-2"> <tr> <td width="25%">服务时间：</td> <td>', 
        $out += $escape(orderinfo.ordetime), $out += " 至 ", $out += $escape(orderinfo.paytime), 
        $out += "</td> </tr> <tr> <td>服务店铺：</td> <td>", $out += $escape(orderinfo.shopname), 
        $out += "</td> </tr> <tr> <td>服务技师：</td> <td>", $out += $escape(orderinfo.fairername), 
        $out += "</td> </tr> <tr> <td>交付方式：</td> <td> ", $out += "" == orderinfo.paymode ? " 到店支付 " : " orderinfo.paymode ", 
        $out += " </td> </tr> <tr> <td>联系方式：</td> <td>", $out += $escape(orderinfo.cellphone), 
        $out += '</td> </tr> <tr> <td>服务评价：</td> <td> <div class="star ba-fl ba-mr-23 ba-clearfix"> <span class="icon icon-star ba-fl ba-mr-5" star></span> <span class="icon icon-star ba-fl ba-mr-5" star></span> <span class="icon icon-star ba-fl ba-mr-5" star></span> <span class="icon icon-star ba-fl ba-mr-5" star></span> <span class="icon icon-star ba-fl ba-mr-5" star></span> </div> ', 
        0 == orderinfo.evaluategoal && ($out += ' <div class="ba-fl submit-star"> <a class="btn btn-yellow ba-font-12" href="javascript:;" submit-apr-btn>提交评价</a> </div> '), 
        $out += " </td> </tr> ", "Y" != orderinfo.iscombo && ($out += ' <tr> <td>美丽记录：</td> <td> <p class="ba-mb-10">看看我的新发型~！</p> <ul class="ba-clearfix record-list ba-mb-10" gallery-wrap record-list-wrap> ', 
        $each(records, function($value) {
            $out += ' <li class="ba-fl" thumb="', $out += $escape($value.pictureshorturl), $out += '" pkBeautyrecord="', 
            $out += $escape($value.pkBeautyrecord), $out += '" photo-list uploaded="true"> <a href="', 
            $out += $escape($value.pictureurl), $out += '"><img alt="" src="', $out += $escape($value.pictureshorturl), 
            $out += '"></a> </li> ';
        }), $out += ' </ul> <div class="ba-fl ba-mr-30 ba-mb-5"> <img width="100" src="', 
        $out += $escape(imgPath("widget/camera.png")), $out += '" alt="拍照" camera-btn class="camera-img img-round"> </div> <button class="btn btn-pink ba-mt-35 ba-font-12" confirm-upload>确认上传</button> </td> </tr> '), 
        $out += " ", "Y" == orderinfo.iscombo && ($out += " <tr> <td>用户头像:</td> <td> ", 
        "Y" != isupload ? ($out += ' <div class="ba-fl ba-mr-30 ba-mb-5"> <img package-head width="100" src="', 
        $out += $escape(imgPath("widget/camera.png")), $out += '" alt="拍照" class="img-round"> </div> <button class="btn btn-pink ba-mt-35 ba-font-12" header-upload-btn>确认上传</button> ') : ($out += ' <div class="ba-mb-5" gallery-wrap> <a href="', 
        $out += $escape(userphoto), $out += '"><img width="100" src="', $out += $escape(usershortphoto), 
        $out += '" alt="用户头像" width="50%" class="img-round"></a> </div> '), $out += " </td> </tr> "), 
        $out += ' <tr> <td>评论详情：</td>  </tr> <tr> <td colspan="2"> <div comment-wrap> <ul class="arg-list-wrap" comment-list=""> ', 
        $each(spitslots, function($value) {
            $out += ' <li class="ba-mb-5" comment-item> <dl class="ba-clearfix"> <dd> <div class="ba-clearfix ba-mb-5"> ', 
            $value.replyman ? ($out += " <h5 spitslotor-id=", $out += $escape($value.spitslotorid), 
            $out += ' class="ba-fl namer">', $out += $escape($value.spitslotor), $out += '</h5><span class="ba-fl ba-ml-5 ba-mr-5">回复</span><h5 replyman-id=', 
            $out += $escape($value.replymanid), $out += ' class="ba-fl namer">', $out += $escape($value.replyman), 
            $out += "</h5> ") : ($out += " <h5 spitslotor-id=", $out += $escape($value.spitslotorid), 
            $out += ' class="ba-fl namer">', $out += $escape($value.spitslotor), $out += "</h5> "), 
            $out += ' <span class="comment-time ba-fr">', $out += $escape($value.ts), $out += "</span> </div> <p>", 
            $out += $escape($value.spitslotcontent), $out += "</p> </dd> </dl> </li> ";
        }), $out += ' </ul>  <div class="ba-none ba-tc ba-mt-11 ba-light-gray" extend>展开评论</div>  <section class="arg-input"> <div class="ba-clearfix"> <form comment-form> <input class="ba-font-1-0" type="text ba-fl" comment-input> <input class="ba-none" type="submit"> <span class="btn btn-pink ba-white ba-fr ba-pt-6 ba-pl-10 ba-pr-10" comment-send>发送</span> </form> </div> </section> </div> </td> </tr> </table> </div> </section> ', 
        new String($out);
    });
});