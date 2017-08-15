/*TMODJS:{"version":1,"md5":"a7b105edafa985ff53493b2714f8d90d"}*/
define(function(require) {
    return require("../templates")("goods/deliveryEdit", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), list = $data.list, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $out += " ", $each(list, function($value) {
            $out += " <tr list gid=", $out += $escape($value.pkProduct), $out += " pkProductDeliveryB=", 
            $out += $escape($value.pkProductDeliveryB), $out += '> <td class="ba-rel"> <input class="col-12 form-control" value=', 
            $out += $escape($value.productname), $out += ' type="text" goods-list-input> <div class="goods-list-wrap" goods-list-wrap> <ul goods-list></ul> </div> </td> <td> <input class="col-12 form-control" value=', 
            $out += $escape($value.productnum), $out += ' type="text" delivery-num-input> </td> <td data="unit">', 
            $out += $escape($value.productunit), $out += '</td> <td data="productprice">', $out += $escape($value.productprice), 
            $out += '</td> <td><a class="btn btn-normal" href="javascript:;" goods-remove>删除</a></td> </tr> ';
        }), $out += " ", new String($out);
    });
});