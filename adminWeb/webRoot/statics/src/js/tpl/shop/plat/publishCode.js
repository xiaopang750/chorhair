/*TMODJS:{"version":72,"md5":"fba61dfe368f3ce41bb71f100143e02d"}*/
define(function(require) {
    return require("../../templates")("shop/plat/publishCode", ' <div class="dialog-box publish-box" script-bound="form-check"> <span class="fa ba-fr fa-close" sc="close"></span> <div class="ba-mt-10"> <label class="ba-mr-10"> <input type="radio" name="code-type" code-type="shop"><span>店铺二维码</span> </label> <label> <input type="radio" name="code-type" code-type="plat"><span>平台二维码</span> </label> </div> <div class="select-area ba-mt-20 ba-clearfix"> <div class="related-select" area-wrap script-role="check-wrap"> <select class="form-control" ischeck="true" form_check="sys" tip="请选择省" wrong="" re=".+" province name="province" pkArea="" areaname=""> <option value="">请选择省</option> </select> </div> <div class="related-select" area-wrap script-role="check-wrap"> <select class="form-control" ischeck="true" form_check="sys" tip="请选择市" wrong="" re=".+" city name="city" pkArea="" areaname=""> <option value="">请选择市</option> </select> </div> <div class="related-select" area-wrap script-role="check-wrap"> <select class="form-control" ischeck="true" form_check="sys" tip="请选择区" wrong="" re=".+" area name="county" pkArea="" areaname=""> <option value="">请选择区</option> </select> </div> <div class="shop-select" script-role="check-wrap"> <select class="form-control" ischeck="true" form_check="sys" tip="请选择区" wrong="" re=".+" shop> <option value="">请选择店铺</option> </select> </div> </div> <div class="field ba-mt-20" user-defined> <input class="form-control" type="text" placeholder="描述"> </div> <div class="generate ba-tc ba-mt-25 ba-mb-20"> <a href="javascript:;" class="btn btn-primary" sc="publish-confirm" script-role="confirm-btn">生成二维码</a> </div> </div>');
});