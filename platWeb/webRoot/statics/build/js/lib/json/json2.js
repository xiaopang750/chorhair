/*! company 罗格斯达 version 1.0.0 2015-06-17 */
define("lib/json/json2",[],function(require,exports,module){"object"!=typeof JSON&&(JSON={}),function(){"use strict";function f(e){return 10>e?"0"+e:e}function quote(e){return escapable.lastIndex=0,escapable.test(e)?'"'+e.replace(escapable,function(e){var t=meta[e];return"string"==typeof t?t:"\\u"+("0000"+e.charCodeAt(0).toString(16)).slice(-4)})+'"':'"'+e+'"'}function str(e,t){var r,i,n,o,s,a=gap,c=t[e];switch(c&&"object"==typeof c&&"function"==typeof c.toJSON&&(c=c.toJSON(e)),"function"==typeof rep&&(c=rep.call(t,e,c)),typeof c){case"string":return quote(c);case"number":return isFinite(c)?c+"":"null";case"boolean":case"null":return c+"";case"object":if(!c)return"null";if(gap+=indent,s=[],"[object Array]"===Object.prototype.toString.apply(c)){for(o=c.length,r=0;o>r;r+=1)s[r]=str(r,c)||"null";return n=0===s.length?"[]":gap?"[\n"+gap+s.join(",\n"+gap)+"\n"+a+"]":"["+s.join(",")+"]",gap=a,n}if(rep&&"object"==typeof rep)for(o=rep.length,r=0;o>r;r+=1)"string"==typeof rep[r]&&(i=rep[r],n=str(i,c),n&&s.push(quote(i)+(gap?": ":":")+n));else for(i in c)Object.prototype.hasOwnProperty.call(c,i)&&(n=str(i,c),n&&s.push(quote(i)+(gap?": ":":")+n));return n=0===s.length?"{}":gap?"{\n"+gap+s.join(",\n"+gap)+"\n"+a+"}":"{"+s.join(",")+"}",gap=a,n}}"function"!=typeof Date.prototype.toJSON&&(Date.prototype.toJSON=function(){return isFinite(this.valueOf())?this.getUTCFullYear()+"-"+f(this.getUTCMonth()+1)+"-"+f(this.getUTCDate())+"T"+f(this.getUTCHours())+":"+f(this.getUTCMinutes())+":"+f(this.getUTCSeconds())+"Z":null},String.prototype.toJSON=Number.prototype.toJSON=Boolean.prototype.toJSON=function(){return this.valueOf()});var cx,escapable,gap,indent,meta,rep;"function"!=typeof JSON.stringify&&(escapable=/[\\\"\x00-\x1f\x7f-\x9f\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,meta={"\b":"\\b","	":"\\t","\n":"\\n","\f":"\\f","\r":"\\r",'"':'\\"',"\\":"\\\\"},JSON.stringify=function(e,t,r){var i;if(gap="",indent="","number"==typeof r)for(i=0;r>i;i+=1)indent+=" ";else"string"==typeof r&&(indent=r);if(rep=t,t&&"function"!=typeof t&&("object"!=typeof t||"number"!=typeof t.length))throw Error("JSON.stringify");return str("",{"":e})}),"function"!=typeof JSON.parse&&(cx=/[\u0000\u00ad\u0600-\u0604\u070f\u17b4\u17b5\u200c-\u200f\u2028-\u202f\u2060-\u206f\ufeff\ufff0-\uffff]/g,JSON.parse=function(text,reviver){function walk(e,t){var r,i,n=e[t];if(n&&"object"==typeof n)for(r in n)Object.prototype.hasOwnProperty.call(n,r)&&(i=walk(n,r),void 0!==i?n[r]=i:delete n[r]);return reviver.call(e,t,n)}var j;if(text+="",cx.lastIndex=0,cx.test(text)&&(text=text.replace(cx,function(e){return"\\u"+("0000"+e.charCodeAt(0).toString(16)).slice(-4)})),/^[\],:{}\s]*$/.test(text.replace(/\\(?:["\\\/bfnrt]|u[0-9a-fA-F]{4})/g,"@").replace(/"[^"\\\n\r]*"|true|false|null|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?/g,"]").replace(/(?:^|:|,)(?:\s*\[)+/g,"")))return j=eval("("+text+")"),"function"==typeof reviver?walk({"":j},""):j;throw new SyntaxError("JSON.parse")})}()});