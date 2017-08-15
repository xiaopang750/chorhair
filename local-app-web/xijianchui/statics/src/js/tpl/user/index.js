/*TMODJS:{"version":6,"md5":"a6008bbcfc73b5dc9b4c0e0dc3b77f49"}*/
define(function(require) {
    return require("../templates")("user/index", ' <li> <img class="lazyload" style="width:\'+_this.width+\'px; height:\'+obj.height * (_this.width/obj.width)+\'px;" src="\'+obj.preview+\'"> <div class="info-area"> <div class="inner"> <span class="namer">杨瑞老师</span> <div class="assit ba-fr ba-tr"> <span class="icon icon-good ba-font-18 ba-mt-13"></span> <span num>200</span> </div> </div> </div> </li>');
});