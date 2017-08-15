/*TMODJS:{"version":13,"md5":"01e618693c60228f043a82787a959994"}*/
define(function(require) {
    return require("../../templates")("shop/goods/confirmDelivery", ' <div class="confirm-box" script-bound="form-check" confirm-box> <div class="ba-mb-10"> <span class="text">确认出库吗？</span> </div> <div class="ba-tc"> <a href="javascript:;" class="btn btn-primary" sc="delivery-confirm" script-role="confirm-btn">确定</a> <a href="javascript:;" class="btn btn-danger" sc="close">取消</a> </div> </div>');
});