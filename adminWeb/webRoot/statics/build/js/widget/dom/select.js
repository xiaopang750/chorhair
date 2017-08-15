/*! company 罗格斯达 version 1.0.0 2015-06-17 */
define("widget/dom/select",["../../lib/template/artTemplate"],function(e,t,r){var i=e("../../lib/template/artTemplate"),n=R.Class.create(R.util,{initialize:function(e){e=e||{},this.oSelect=e.ele||"",this.loadUrl=e.url||"",this.param=e.param||"",this.renderData=e.renderData||"",this.tpl=e.tpl||"",this.changeData=e.changeData||null,this.onChange=e.onChange||null,this.onReady=e.onReady||null,this.oSelect.length&&(this._req(),this.events(),this.orgHTML=this.oSelect.html())},_req:function(){var e,t,r,i=this;r=this.oSelect[0],this.loadUrl?(this.reqUrl=this.loadUrl,this.reqParam=this.param,this.req(function(n){n=i.changeData?i.changeData(n):n,i.render(n),t=r.selectedIndex,e=$(r.options[t]),i.onReady&&i.onReady(i.oSelect,e,t,n)})):(data=this.renderData,this.render(data),t=r.selectedIndex,e=$(r.options[t]),this.onReady&&this.onReady(this.oSelect,e,t,data))},match:function(e,t){var r,i=this.oSelect.children(),n=this;i.each(function(o){r=i.eq(o).attr(e),t==r&&(n.oSelect[0].selectedIndex=o)})},getNowIndex:function(){var e=this.oSelect[0].selectedIndex;return e},getValue:function(){return this.oSelect.val()},getNowOption:function(){var e=this.getNowIndex();return this.oSelect.children().eq(e)},events:function(){var e,t,r,i;i=this,this.oSelect.on("change",function(){r=$(this)[0],t=r.selectedIndex,e=$(r.options[t]),i.onChange&&i.onChange($(this),e,t)})},render:function(e){var t=i.compile(this.tpl),r=t(e);this.oSelect.append($(r))},clear:function(){this.oSelect.html(this.orgHTML)}});r.exports=n}),define("lib/template/artTemplate",[],function(e,t,r){!function(e){"use strict";var i,n,o,a,s=function(e,t){return s["string"==typeof t?"compile":"render"].apply(s,arguments)};s.version="2.0.2",s.openTag="<%",s.closeTag="%>",s.isEscape=!0,s.isCompress=!1,s.parser=null,s.render=function(e,t){var r=s.get(e)||o({id:e,name:"Render Error",message:"No Template"});return r(t)},s.compile=function(e,t){function r(r){try{return new n(r,e)+""}catch(i){return l?o(i)():s.compile(e,t,!0)(r)}}var n,c=arguments,l=c[2],u="anonymous";"string"!=typeof t&&(l=c[1],t=c[0],e=u);try{n=a(e,t,l)}catch(d){return d.id=e||t,d.name="Syntax Error",o(d)}return r.prototype=n.prototype,r.toString=function(){return""+n},e!==u&&(i[e]=r),r},i=s.cache={},n=s.helpers={$include:s.render,$string:function(e,t){return"string"!=typeof e&&(t=typeof e,"number"===t?e+="":e="function"===t?n.$string(e()):""),e},$escape:function(e){var t={"<":"&#60;",">":"&#62;",'"':"&#34;","'":"&#39;","&":"&#38;"};return n.$string(e).replace(/&(?![\w#]+;)|[<>"']/g,function(e){return t[e]})},$each:function(e,t){var r,i,n=Array.isArray||function(e){return"[object Array]"==={}.toString.call(e)};if(n(e))for(r=0,i=e.length;i>r;r++)t.call(e,e[r],r,e);else for(r in e)t.call(e,e[r],r)}},s.helper=function(e,t){n[e]=t},s.onerror=function(t){var r,i="Template Error\n\n";for(r in t)i+="<"+r+">\n"+t[r]+"\n\n";e.console&&console.error(i)},s.get=function(t){var r,n,o;return i.hasOwnProperty(t)?r=i[t]:"document"in e&&(n=document.getElementById(t),n&&(o=n.value||n.innerHTML,r=s.compile(t,o.replace(/^\s*|\s*$/g,"")))),r},o=function(e){return s.onerror(e),function(){return"{Template Error}"}},a=function(){var e=n.$each,t="break,case,catch,continue,debugger,default,delete,do,else,false,finally,for,function,if,in,instanceof,new,null,return,switch,this,throw,true,try,typeof,var,void,while,with,abstract,boolean,byte,char,class,const,double,enum,export,extends,final,float,goto,implements,import,int,interface,long,native,package,private,protected,public,short,static,super,synchronized,throws,transient,volatile,arguments,let,yield,undefined",r=/\/\*[\w\W]*?\*\/|\/\/[^\n]*\n|\/\/[^\n]*$|"(?:[^"\\]|\\[\w\W])*"|'(?:[^'\\]|\\[\w\W])*'|[\s\t\n]*\.[\s\t\n]*[$\w\.]+/g,i=/[^\w$]+/g,o=RegExp(["\\b"+t.replace(/,/g,"\\b|\\b")+"\\b"].join("|"),"g"),a=/^\d[^,]*|,\d[^,]*/g,c=/^,+|,+$/g,l=function(e){return e.replace(r,"").replace(i,",").replace(o,"").replace(a,"").replace(c,"").split(/^$|,+/)};return function(t,r,i){function o(e){return b+=e.split(/\n/).length-1,s.isCompress&&(e=e.replace(/[\n\r\t\s]+/g," ").replace(/<!--.*?-->/g,"")),e&&(e=S[1]+d(e)+S[2]+"\n"),e}function a(e){var t,r,o=b;return m?e=m(e):i&&(e=e.replace(/\n/g,function(){return b++,"$line="+b+";"})),0===e.indexOf("=")&&(t=0!==e.indexOf("=="),e=e.replace(/^=*|[\s;]*$/g,""),t&&s.isEscape?(r=e.replace(/\s*\([^\)]+\)/,""),n.hasOwnProperty(r)||/^(include|print)$/.test(r)||(e="$escape("+e+")")):e="$string("+e+")",e=S[1]+e+S[2]),i&&(e="$line="+o+";"+e),c(e),e+"\n"}function c(t){t=l(t),e(t,function(e){y.hasOwnProperty(e)||(u(e),y[e]=!0)})}function u(e){var t;"print"===e?t=P:"include"===e?(x.$include=n.$include,t=C):(t="$data."+e,n.hasOwnProperty(e)&&(x[e]=n[e],t=0===e.indexOf("$")?"$helpers."+e:t+"===undefined?$helpers."+e+":"+t)),w+=e+"="+t+","}function d(e){return"'"+e.replace(/('|\\)/g,"\\$1").replace(/\r/g,"\\r").replace(/\n/g,"\\n")+"'"}var h,f=s.openTag,p=s.closeTag,m=s.parser,g=r,v="",b=1,y={$data:1,$id:1,$helpers:1,$out:1,$line:1},x={},w="var $helpers=this,"+(i?"$line=0,":""),k="".trim,S=k?["$out='';","$out+=",";","$out"]:["$out=[];","$out.push(",");","$out.join('')"],R=k?"if(content!==undefined){$out+=content;return content;}":"$out.push(content);",P="function(content){"+R+"}",C="function(id,data){data=data||$data;var content=$helpers.$include(id,data,$id);"+R+"}";e(g.split(f),function(e){var t,r;e=e.split(p),t=e[0],r=e[1],1===e.length?v+=o(t):(v+=a(t),r&&(v+=o(r)))}),g=v,i&&(g="try{"+g+"}catch(e){"+"throw {"+"id:$id,"+"name:'Render Error',"+"message:e.message,"+"line:$line,"+"source:"+d(r)+".split(/\\n/)[$line-1].replace(/^[\\s\\t]+/,'')"+"};"+"}"),g=w+S[0]+g+"return new String("+S[3]+");";try{return h=Function("$data","$id",g),h.prototype=x,h}catch(T){throw T.temp="function anonymous($data,$id) {"+g+"}",T}}}(),"function"==typeof define?define(function(){return s}):t!==void 0&&(r.exports=s),e.template=s,r.exports=s}(this),function(e){e.openTag="{{",e.closeTag="}}",e.parser=function(t){var r,i,n,o,a,s,c,l;switch(t=t.replace(/^\s/,""),r=t.split(" "),i=r.shift(),n=r.join(" "),i){case"if":t="if("+n+"){";break;case"else":r="if"===r.shift()?" if("+r.join(" ")+")":"",t="}else"+r+"{";break;case"/if":t="}";break;case"each":o=r[0]||"$data",a=r[1]||"as",s=r[2]||"$value",c=r[3]||"$index",l=s+","+c,"as"!==a&&(o="[]"),t="$each("+o+",function("+l+"){";break;case"/each":t="});";break;case"echo":t="print("+n+");";break;case"include":t="include("+r.join(",")+");";break;default:e.helpers.hasOwnProperty(i)?t="=="+i+"("+r.join(",")+");":(t=t.replace(/[\s;]*$/,""),t="="+t)}return t}}(template)});