/*TMODJS:{"version":104,"md5":"345bda3b8e0855474a79b3df7d96ec66"}*/
define(function(require) {
    return require("../templates")("performance/people", function($data) {
        "use strict";
        var $utils = this, data = ($utils.$helpers, $data.data), $each = $utils.$each, $escape = ($data.$value, 
        $data.$index, $utils.$escape), $out = "";
        return data.length ? ($out += " <thead> <tr> <td>消费者图片</td> ", $each(data, function($value) {
            $out += " <td>第", $out += $escape($value.dates), $out += "周</td> ";
        }), $out += " <td>当月总计</td> </tr> </thead> <tbody> <tr> <td>本周到店人数</td> ", $each(data, function($value) {
            $out += " <td>", $out += $escape($value.customercount), $out += "</td> ";
        }), $out += ' <td sum="customercount"></td> </tr> <tr> <td>本周新增会员数</td> ', $each(data, function($value) {
            $out += " <td>", $out += $escape($value.addcustomercount), $out += "</td> ";
        }), $out += ' <td sum="addcustomercount"></td> </tr> <tr> <td>本周单次体验用户数</td> ', 
        $each(data, function($value) {
            $out += " <td>", $out += $escape($value.comboonecount), $out += "</td> ";
        }), $out += ' <td sum="comboonecount"></td> </tr> <tr> <td>本周会员服务总数</td> ', $each(data, function($value) {
            $out += " <td>", $out += $escape($value.servicecount), $out += "</td> ";
        }), $out += ' <td sum="servicecount"></td> </tr> <tr> <td>截止本周累计会员数</td> ', $each(data, function($value) {
            $out += " <td>", $out += $escape($value.totalcustomercount), $out += "</td> ";
        }), $out += ' <td sum="totalcustomercount"></td> </tr> <tr> <td>本周套餐包个数</td> ', 
        $each(data, function($value) {
            $out += " <td>", $out += $escape($value.combocount), $out += "</td> ";
        }), $out += ' <td sum="combocount"></td> </tr> <tr> <td>截止本周累计总套餐包个数</td> ', $each(data, function($value) {
            $out += " <td>", $out += $escape($value.totalcombocount), $out += "</td> ";
        }), $out += ' <td sum="totalcombocount"></td> </tr> </tbody> ') : $out += " <tr> <td>暂无数据</td> </tr> ", 
        $out += " ", new String($out);
    });
});