/*TMODJS:{"version":1,"md5":"835e92530b46673d2a47276aacce0102"}*/
define(function(require) {
    return require("../templates")("goods/deliveryAdd", ' <tr list> <td class="ba-rel"> <input class="col-12 form-control" type="text" goods-list-input> <div class="goods-list-wrap" goods-list-wrap> <ul goods-list></ul> </div> </td> <td><input class="col-12 form-control" type="text" delivery-num-input></td> <td data="unit"></td> <td data="productprice"></td> <td><a class="btn btn-normal" href="javascript:;" goods-remove>删除</a></td> </tr> ');
});