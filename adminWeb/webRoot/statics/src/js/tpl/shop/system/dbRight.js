/*TMODJS:{"version":13,"md5":"9e62d0045b820859419ec73a6aabfa39"}*/
define(function(require) {
    return require("./dbRightlist"), require("../../templates")("shop/system/dbRight", function($data, $filename) {
        "use strict";
        var $utils = this, $escape = ($utils.$helpers, $utils.$escape), data = $data.data, include = function(filename, data) {
            data = data || $data;
            var text = $utils.$include(filename, data, $filename);
            return $out += text;
        }, $out = "";
        return $out += ' <table width="100%" class="ba-mb-20"> <tr> <td> 名称 </td> <td> <input type="text" class="form-control" value="', 
        $out += $escape(data.groupname), $out += '" ', "look" == data.type && ($out += 'disabled="disabled"'), 
        $out += ' org-sub-name="groupname" re="^.{1,10}$" msg="请填写10位数以内的名称" tip="名称不能为空"> </td> </tr> </table> <table width="100%" class="ba-mb-10 table bordered"> <thead> <tr> <td width="25%">序号</td> <td width="25%">系列名称</td> <td width="25%">抵用券额度</td> <td width="25%">操作</td> </tr> </thead> <tbody data-list> ', 
        include("./dbRightlist", data), $out += " </tbody> </table> ", "look" != data.type && ($out += ' <div class="ba-tc ba-mb-20"> <span class="fa fa-plus" data-list-plus></span> </div> <div class="ba-tc"> <a href="javascript:;" class="btn btn-primary" confirm-save>保存</a> </div> '), 
        new String($out);
    });
});