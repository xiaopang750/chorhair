/*TMODJS:{"version":1,"md5":"33043d162c5846d99235e2f504d6ddff"}*/
define(function(require) {
    return require("../templates")("system/addGroup", ' <div class="dialog-box add-group" add-group> <span class="fa fa-close" title="关闭" sc="close"></span> <div class="ba-mt-20"> <p class="ba-mb-5">请填写十个字以内分组名称：</p> <input type="text" class="form-control" add-group-input> </div> <div class="ba-tc ba-mt-20"> <a href="javascript:;" class="btn btn-primary" sc="confirm">确定</a> <a href="javascript:;" class="btn btn-danger" sc="close">取消</a> </div> </div>');
});