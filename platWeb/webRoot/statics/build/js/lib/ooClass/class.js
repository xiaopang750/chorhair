/*! company 罗格斯达 version 1.0.0 2015-06-17 */
define("lib/ooClass/class",[],function(e,t,r){function i(e){return this instanceof i||!d(e)?void 0:o(e)}function n(e){var t,r;for(t in e)r=e[t],i.Mutators.hasOwnProperty(t)?i.Mutators[t].call(this,r):this.prototype[t]=r}function o(e){return e.extend=i.extend,e.implement=n,e}function s(){}function a(e,t,r){for(var i in t)if(t.hasOwnProperty(i)){if(r&&-1===f(r,i))continue;"prototype"!==i&&(e[i]=t[i])}}i.create=function(e,t){function r(){e.apply(this,arguments),this.constructor===r&&this.initialize&&this.initialize.apply(this,arguments)}return d(e)||(t=e,e=null),t||(t={}),e||(e=t.Extends||i),t.Extends=e,e!==i&&a(r,e,e.StaticsWhiteList),n.call(r,t),o(r)},i.extend=function(e){return e||(e={}),e.Extends=this,i.create(e)},i.Mutators={Extends:function(e){var t=this.prototype,r=c(e.prototype);a(r,t),r.constructor=this,this.prototype=r,this.superclass=e.prototype},Implements:function(e){u(e)||(e=[e]);for(var t,r=this.prototype;t=e.shift();)a(r,t.prototype||t)},Statics:function(e){a(this,e)}};var c=Object.__proto__?function(e){return{__proto__:e}}:function(e){return s.prototype=e,new s},l=Object.prototype.toString,u=Array.isArray||function(e){return"[object Array]"===l.call(e)},d=function(e){return"[object Function]"===l.call(e)},f=Array.prototype.indexOf?function(e,t){return e.indexOf(t)}:function(e,t){for(var r=0,i=e.length;i>r;r++)if(e[r]===t)return r;return-1};r.exports=i});