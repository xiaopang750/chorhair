/*TMODJS:{"version":10,"md5":"ce9f208201f09fa6b9bffcb94924c2b9"}*/
define(function(require) {
    return require("./fmRightlist"), require("../../templates")("shop/system/fmRight", function($data, $filename) {
        "use strict";
        var $utils = this, $escape = ($utils.$helpers, $utils.$escape), data = $data.data, include = function(filename, data) {
            data = data || $data;
            var text = $utils.$include(filename, data, $filename);
            return $out += text;
        }, $out = "";
        return $out += ' <table width="100%" class="ba-mb-20"> <tr> <td> 名称 </td> <td> <input type="text" class="form-control" value="', 
        $out += $escape(data.servicerank), $out += '" ', "look" == data.type && ($out += 'disabled="disabled"'), 
        $out += ' org-sub-name="servicerank" re="^.{1,10}$" msg="请填写10位数以内的名称" tip="名称不能为空"> </td> <td> 价位 </td> <td> <input type="text" class="form-control" value="', 
        $out += $escape(data.price), $out += '" ', "look" == data.type && ($out += 'disabled="disabled"'), 
        $out += ' org-sub-name="price" re="^\\d+$" msg="价位只能为数字" tip="价位不能为空"> </td> </tr> </table> <table width="100%" class="ba-mb-10 table bordered"> <thead> <tr> <td width="20%">序号</td> <td width="20%">提成名称</td> <td width="20%">提成方式</td> <td width="20%">提成值</td> <td width="20%">操作</td> </tr> </thead> <tbody data-list> ', 
        include("./fmRightlist", data), $out += " </tbody> </table> ", "look" != data.type && ($out += ' <div class="ba-tc ba-mb-20"> <span class="fa fa-plus" data-list-plus></span> </div> <div class="ba-tc"> <a href="javascript:;" class="btn btn-primary" confirm-save>保存</a> </div> '), 
        new String($out);
    });
});