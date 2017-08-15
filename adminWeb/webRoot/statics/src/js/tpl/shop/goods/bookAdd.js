/*TMODJS:{"version":61,"md5":"bc5f160049a88f17c107df8efa33daf1"}*/
define(function(require) {
    return require("../../templates")("shop/goods/bookAdd", ' <tr list> <td class="ba-rel"> <input class="col-12 form-control" type="text" goods-list-input> <div class="goods-list-wrap" goods-list-wrap> <ul goods-list></ul> </div> </td> <td><input class="col-12 form-control" type="text" book-num-input></td> <td></td> <td></td> <td data="unit"></td> <td data="capacity"></td> <td data="productprice"></td> <td><a class="btn btn-normal" href="javascript:;" goods-remove>删除</a></td> </tr> ');
});