define(function(e,n,t){!function(e){"use strict";var r,i,a,o,c=function(e,n){return c["string"==typeof n?"compile":"render"].apply(c,arguments)};c.version="2.0.2",c.openTag="<%",c.closeTag="%>",c.isEscape=!0,c.isCompress=!1,c.parser=null,c.render=function(e,n){var t=c.get(e)||a({id:e,name:"Render Error",message:"No Template"});return t(n)},c.compile=function(e,n){function t(t){try{return new i(t,e)+""}catch(r){return u?a(r)():c.compile(e,n,!0)(t)}}var i,s=arguments,u=s[2],l="anonymous";"string"!=typeof n&&(u=s[1],n=s[0],e=l);try{i=o(e,n,u)}catch(p){return p.id=e||n,p.name="Syntax Error",a(p)}return t.prototype=i.prototype,t.toString=function(){return i.toString()},e!==l&&(r[e]=t),t},r=c.cache={},i=c.helpers={$include:c.render,$string:function(e,n){return"string"!=typeof e&&(n=typeof e,"number"===n?e+="":e="function"===n?i.$string(e()):""),e},$escape:function(e){var n={"<":"&#60;",">":"&#62;",'"':"&#34;","'":"&#39;","&":"&#38;"};return i.$string(e).replace(/&(?![\w#]+;)|[<>"']/g,function(e){return n[e]})},$each:function(e,n){var t,r,i=Array.isArray||function(e){return"[object Array]"==={}.toString.call(e)};if(i(e))for(t=0,r=e.length;r>t;t++)n.call(e,e[t],t,e);else for(t in e)n.call(e,e[t],t)}},c.helper=function(e,n){i[e]=n},c.onerror=function(n){var t,r="Template Error\n\n";for(t in n)r+="<"+t+">\n"+n[t]+"\n\n";e.console&&console.error(r)},c.get=function(n){var t,i,a;return r.hasOwnProperty(n)?t=r[n]:"document"in e&&(i=document.getElementById(n),i&&(a=i.value||i.innerHTML,t=c.compile(n,a.replace(/^\s*|\s*$/g,"")))),t},a=function(e){return c.onerror(e),function(){return"{Template Error}"}},o=function(){var e=i.$each,n="break,case,catch,continue,debugger,default,delete,do,else,false,finally,for,function,if,in,instanceof,new,null,return,switch,this,throw,true,try,typeof,var,void,while,with,abstract,boolean,byte,char,class,const,double,enum,export,extends,final,float,goto,implements,import,int,interface,long,native,package,private,protected,public,short,static,super,synchronized,throws,transient,volatile,arguments,let,yield,undefined",t=/\/\*[\w\W]*?\*\/|\/\/[^\n]*\n|\/\/[^\n]*$|"(?:[^"\\]|\\[\w\W])*"|'(?:[^'\\]|\\[\w\W])*'|[\s\t\n]*\.[\s\t\n]*[$\w\.]+/g,r=/[^\w$]+/g,a=new RegExp(["\\b"+n.replace(/,/g,"\\b|\\b")+"\\b"].join("|"),"g"),o=/^\d[^,]*|,\d[^,]*/g,s=/^,+|,+$/g,u=function(e){return e.replace(t,"").replace(r,",").replace(a,"").replace(o,"").replace(s,"").split(/^$|,+/)};return function(n,t,r){function a(e){return y+=e.split(/\n/).length-1,c.isCompress&&(e=e.replace(/[\n\r\t\s]+/g," ").replace(/<!--.*?-->/g,"")),e&&(e=T[1]+p(e)+T[2]+"\n"),e}function o(e){var n,t,a=y;return g?e=g(e):r&&(e=e.replace(/\n/g,function(){return y++,"$line="+y+";"})),0===e.indexOf("=")&&(n=0!==e.indexOf("=="),e=e.replace(/^=*|[\s;]*$/g,""),n&&c.isEscape?(t=e.replace(/\s*\([^\)]+\)/,""),i.hasOwnProperty(t)||/^(include|print)$/.test(t)||(e="$escape("+e+")")):e="$string("+e+")",e=T[1]+e+T[2]),r&&(e="$line="+a+";"+e),s(e),e+"\n"}function s(n){n=u(n),e(n,function(e){w.hasOwnProperty(e)||(l(e),w[e]=!0)})}function l(e){var n;"print"===e?n=E:"include"===e?(v.$include=i.$include,n=O):(n="$data."+e,i.hasOwnProperty(e)&&(v[e]=i[e],n=0===e.indexOf("$")?"$helpers."+e:n+"===undefined?$helpers."+e+":"+n)),b+=e+"="+n+","}function p(e){return"'"+e.replace(/('|\\)/g,"\\$1").replace(/\r/g,"\\r").replace(/\n/g,"\\n")+"'"}var f,d=c.openTag,$=c.closeTag,g=c.parser,h=t,m="",y=1,w={$data:1,$id:1,$helpers:1,$out:1,$line:1},v={},b="var $helpers=this,"+(r?"$line=0,":""),x="".trim,T=x?["$out='';","$out+=",";","$out"]:["$out=[];","$out.push(",");","$out.join('')"],k=x?"if(content!==undefined){$out+=content;return content;}":"$out.push(content);",E="function(content){"+k+"}",O="function(id,data){data=data||$data;var content=$helpers.$include(id,data,$id);"+k+"}";e(h.split(d),function(e){var n,t;e=e.split($),n=e[0],t=e[1],1===e.length?m+=a(n):(m+=o(n),t&&(m+=a(t)))}),h=m,r&&(h="try{"+h+"}catch(e){throw {id:$id,name:'Render Error',message:e.message,line:$line,source:"+p(t)+".split(/\\n/)[$line-1].replace(/^[\\s\\t]+/,'')};}"),h=b+T[0]+h+"return new String("+T[3]+");";try{return f=new Function("$data","$id",h),f.prototype=v,f}catch(j){throw j.temp="function anonymous($data,$id) {"+h+"}",j}}}(),"function"==typeof define?define(function(){return c}):"undefined"!=typeof n&&(t.exports=c),e.template=c,t.exports=c}(this),function(e){e.openTag="{{",e.closeTag="}}",e.parser=function(n){var t,r,i,a,o,c,s,u;switch(n=n.replace(/^\s/,""),t=n.split(" "),r=t.shift(),i=t.join(" "),r){case"if":n="if("+i+"){";break;case"else":t="if"===t.shift()?" if("+t.join(" ")+")":"",n="}else"+t+"{";break;case"/if":n="}";break;case"each":a=t[0]||"$data",o=t[1]||"as",c=t[2]||"$value",s=t[3]||"$index",u=c+","+s,"as"!==o&&(a="[]"),n="$each("+a+",function("+u+"){";break;case"/each":n="});";break;case"echo":n="print("+i+");";break;case"include":n="include("+t.join(",")+");";break;default:e.helpers.hasOwnProperty(r)?n="=="+r+"("+t.join(",")+");":(n=n.replace(/[\s;]*$/,""),n="="+n)}return n}}(template)});