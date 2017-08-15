/*! company 罗格斯达 version 1.0.0 2015-06-17 */
define("util/holder/holder",[],function(){var e=e||{};(function(e,t){function r(e,t){var r="complete",i="readystatechange",n=!1,o=n,a=!0,s=e.document,c=s.documentElement,l=s.addEventListener?"addEventListener":"attachEvent",u=s.addEventListener?"removeEventListener":"detachEvent",d=s.addEventListener?"":"on",f=function(a){(a.type!=i||s.readyState==r)&&(("load"==a.type?e:s)[u](d+a.type,f,n),!o&&(o=!0)&&t.call(e,null))},h=function(){try{c.doScroll("left")}catch(e){return setTimeout(h,50),void 0}f("poll")};if(s.readyState==r)t.call(e,"lazy");else{if(s.createEventObject&&c.doScroll){try{a=!e.frameElement}catch(p){}a&&h()}s[l](d+"DOMContentLoaded",f,n),s[l](d+i,f,n),e[l](d+"load",f,n)}}function n(e){e=e.match(/^(\W)?(.*)/);var t=document["getElement"+(e[1]?"#"==e[1]?"ById":"sByClassName":"sByTagName")](e[2]),r=[];return null!=t&&(r=t.length?t:0==t.length?t:[t]),r}function o(e,t){var r={};for(var i in e)r[i]=e[i];for(var n in t)r[n]=t[n];return r}function a(e,t,r){var i=[t,e].sort(),n=Math.round(i[1]/16);Math.round(i[0]/16);var o=Math.max(r.size,n);return{height:o}}function s(e,t,r,i){var n=a(t.width,t.height,r),o=n.height,s=t.width*i,c=t.height*i,l=r.font?r.font:"sans-serif";p.width=s,p.height=c,e.textAlign="center",e.textBaseline="middle",e.fillStyle=r.background,e.fillRect(0,0,s,c),e.fillStyle=r.foreground,e.font="bold "+o+"px "+l;var u=r.text?r.text:t.width+"x"+t.height;return e.measureText(u).width/s>1&&(o=r.size/(e.measureText(u).width/s)),e.font="bold "+o*i+"px "+l,e.fillText(u,s/2,c/2,s),p.toDataURL("image/png")}function c(e,t,r,i){var n=r.dimensions,a=r.theme,c=r.text?decodeURIComponent(r.text):r.text,l=n.width+"x"+n.height;a=c?o(a,{text:c}):a,a=r.font?o(a,{font:r.font}):a,"image"==e?(t.setAttribute("data-src",i),t.setAttribute("alt",c?c:a.text?a.text+" ["+l+"]":l),(h||!r.auto)&&(t.style.width=n.width+"px",t.style.height=n.height+"px"),h?t.style.backgroundColor=a.background:t.setAttribute("src",s(m,n,a,b))):h||(t.style.backgroundImage="url("+s(m,n,a,b)+")",t.style.backgroundSize=n.width+"px "+n.height+"px")}function l(e,t){var r=t.dimensions,i=t.theme,n=t.text,a=r.width+"x"+r.height;i=n?o(i,{text:n}):i;var s=document.createElement("div");e.fluidRef&&(s=e.fluidRef),s.style.backgroundColor=i.background,s.style.color=i.foreground,s.className=e.className+" holderjs-fluid",s.style.width=t.dimensions.width+(t.dimensions.width.indexOf("%")>0?"":"px"),s.style.height=t.dimensions.height+(t.dimensions.height.indexOf("%")>0?"":"px"),s.id=e.id,e.style.width=0,e.style.height=0,e.fluidRef||(i.text?s.appendChild(document.createTextNode(i.text)):(s.appendChild(document.createTextNode(a)),y.push(s),setTimeout(u,0))),e.fluidRef=s,e.parentNode.insertBefore(s,e.nextSibling),window.jQuery&&jQuery(function(t){t(e).on("load",function(){e.style.width=s.style.width,e.style.height=s.style.height,t(e).show(),t(s).remove()})})}function u(){for(i in y)if(y.hasOwnProperty(i)){var e=y[i],t=e.firstChild;e.style.lineHeight=e.offsetHeight+"px",t.data=e.offsetWidth+"x"+e.offsetHeight}}function d(t,r){var i={theme:x.themes.gray},n=!1;for(sl=t.length,j=0;sl>j;j++){var o=t[j];e.flags.dimensions.match(o)?(n=!0,i.dimensions=e.flags.dimensions.output(o)):e.flags.fluid.match(o)?(n=!0,i.dimensions=e.flags.fluid.output(o),i.fluid=!0):e.flags.colors.match(o)?i.theme=e.flags.colors.output(o):r.themes[o]?i.theme=r.themes[o]:e.flags.text.match(o)?i.text=e.flags.text.output(o):e.flags.font.match(o)?i.font=e.flags.font.output(o):e.flags.auto.match(o)&&(i.auto=!0)}return n?i:!1}var f=!1,h=!1,p=document.createElement("canvas");if(document.getElementsByClassName||(document.getElementsByClassName=function(e){var t,r,i,n=document,o=[];if(n.querySelectorAll)return n.querySelectorAll("."+e);if(n.evaluate)for(r=".//*[contains(concat(' ', @class, ' '), ' "+e+" ')]",t=n.evaluate(r,n,null,0,null);i=t.iterateNext();)o.push(i);else for(t=n.getElementsByTagName("*"),r=RegExp("(^|\\s)"+e+"(\\s|$)"),i=0;t.length>i;i++)r.test(t[i].className)&&o.push(t[i]);return o}),window.getComputedStyle||(window.getComputedStyle=function(e){return this.el=e,this.getPropertyValue=function(t){var r=/(\-([a-z]){1})/g;return"float"==t&&(t="styleFloat"),r.test(t)&&(t=t.replace(r,function(){return arguments[2].toUpperCase()})),e.currentStyle[t]?e.currentStyle[t]:null},this}),Object.prototype.hasOwnProperty||(Object.prototype.hasOwnProperty=function(e){var t=this.__proto__||this.constructor.prototype;return e in this&&(!(e in t)||t[e]!==this[e])}),p.getContext)if(0>p.toDataURL("image/png").indexOf("data:image/png"))h=!0;else var m=p.getContext("2d");else h=!0;var g=1,v=1;h||(g=window.devicePixelRatio||1,v=m.webkitBackingStorePixelRatio||m.mozBackingStorePixelRatio||m.msBackingStorePixelRatio||m.oBackingStorePixelRatio||m.backingStorePixelRatio||1);var b=g/v,y=[],x={domain:"holder.js",images:"img",bgnodes:".holderjs",themes:{gray:{background:"#eee",foreground:"#aaa",size:12},social:{background:"#3a5a97",foreground:"#fff",size:12},industrial:{background:"#434A52",foreground:"#C2F200",size:12}},stylesheet:".holderjs-fluid {font-size:16px;font-weight:bold;text-align:center;font-family:sans-serif;margin:0}"};e.flags={dimensions:{regex:/^(\d+)x(\d+)$/,output:function(e){var t=this.regex.exec(e);return{width:+t[1],height:+t[2]}}},fluid:{regex:/^([0-9%]+)x([0-9%]+)$/,output:function(e){var t=this.regex.exec(e);return{width:t[1],height:t[2]}}},colors:{regex:/#([0-9a-f]{3,})\:#([0-9a-f]{3,})/i,output:function(e){var t=this.regex.exec(e);return{size:x.themes.gray.size,foreground:"#"+t[2],background:"#"+t[1]}}},text:{regex:/text\:(.*)/,output:function(e){return this.regex.exec(e)[1]}},font:{regex:/font\:(.*)/,output:function(e){return this.regex.exec(e)[1]}},auto:{regex:/^auto$/}};for(var w in e.flags)e.flags.hasOwnProperty(w)&&(e.flags[w].match=function(e){return e.match(this.regex)});e.add_theme=function(t,r){return null!=t&&null!=r&&(x.themes[t]=r),e},e.add_image=function(t,r){var i=n(r);if(i.length)for(var o=0,a=i.length;a>o;o++){var s=document.createElement("img");s.setAttribute("data-src",t),i[o].appendChild(s)}return e},e.run=function(t){var r=o(x,t),i=[],a=[],s=[];for("string"==typeof r.images?a=n(r.images):window.NodeList&&r.images instanceof window.NodeList?a=r.images:window.Node&&r.images instanceof window.Node&&(a=[r.images]),"string"==typeof r.bgnodes?s=n(r.bgnodes):window.NodeList&&r.elements instanceof window.NodeList?s=r.bgnodes:window.Node&&r.bgnodes instanceof window.Node&&(s=[r.bgnodes]),f=!0,m=0,p=a.length;p>m;m++)i.push(a[m]);var u=document.getElementById("holderjs-style");u||(u=document.createElement("style"),u.setAttribute("id","holderjs-style"),u.type="text/css",document.getElementsByTagName("head")[0].appendChild(u)),r.nocss||(u.styleSheet?u.styleSheet.cssText+=r.stylesheet:u.appendChild(document.createTextNode(r.stylesheet)));for(var h=RegExp(r.domain+'/(.*?)"?\\)'),p=s.length,m=0;p>m;m++){var g=window.getComputedStyle(s[m],null).getPropertyValue("background-image"),v=g.match(h),b=s[m].getAttribute("data-background-src");if(v){var y=d(v[1].split("/"),r);y&&c("background",s[m],y,g)}else if(null!=b){var y=d(b.substr(b.lastIndexOf(r.domain)+r.domain.length+1).split("/"),r);y&&c("background",s[m],y,g)}}for(p=i.length,m=0;p>m;m++){var w=attr_data_src=g=null;try{w=i[m].getAttribute("src"),attr_datasrc=i[m].getAttribute("data-src")}catch(k){}if(null==attr_datasrc&&w&&w.indexOf(r.domain)>=0?g=w:attr_datasrc&&attr_datasrc.indexOf(r.domain)>=0&&(g=attr_datasrc),g){var y=d(g.substr(g.lastIndexOf(r.domain)+r.domain.length+1).split("/"),r);y&&(y.fluid?l(i[m],y,g):c("image",i[m],y,g))}}return e},r(t,function(){window.addEventListener?(window.addEventListener("resize",u,!1),window.addEventListener("orientationchange",u,!1)):window.attachEvent("onresize",u),f||e.run()}),"function"==typeof define&&define.amd&&define("Holder",[],function(){return e})})(e,window)});