/*TMODJS:{"version":5,"md5":"597d0564767bea1e498e6ccc55388431"}*/
define(function(require) {
    return require("../../templates")("shop/shop/publishCode", ' <div class="dialog-box publish-box" script-bound="form-check"> <span class="fa ba-fr fa-close" sc="close"></span> <div class="field ba-mt-20" user-defined> <input class="form-control" type="text" placeholder="描述"> </div> <div class="generate ba-tc ba-mt-25 ba-mb-20"> <a href="javascript:;" class="btn btn-primary" sc="publish-confirm" script-role="confirm-btn">生成二维码</a> </div> </div>');
});