/*TMODJS:{"version":1,"md5":"e91e3eddd14fe1d62feffcb83d56c9f1"}*/
define(function(require) {
    return require("../templates")("goods/bookEdit", function($data) {
        "use strict";
        var $utils = this, $each = ($utils.$helpers, $utils.$each), list = $data.list, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return $out += " ", $each(list, function($value) {
            $out += " <tr list gid=", $out += $escape($value.pkProduct), $out += " pkProductBookB=", 
            $out += $escape($value.pkProductBookB), $out += '> <td class="ba-rel"> <input class="col-12 form-control" value=', 
            $out += $escape($value.productname), $out += ' type="text" goods-list-input> <div class="goods-list-wrap" goods-list-wrap> <ul goods-list></ul> </div> </td> <td> <input class="col-12 form-control" value=', 
            $out += $escape($value.productnum), $out += ' type="text" book-num-input> </td> <td data="approvenum">', 
            $out += $escape($value.approvenum), $out += "</td> <td>", $out += $escape($value.realnum), 
            $out += '</td> <td data="unit">', $out += $escape($value.productunit), $out += '</td> <td data="capacity">', 
            $out += $escape($value.productcapacity), $out += '</td> <td data="productprice">', 
            $out += $escape($value.productprice), $out += '</td> <td><a class="btn btn-normal" href="javascript:;" goods-remove>删除</a></td> </tr> ';
        }), $out += " ", new String($out);
    });
});