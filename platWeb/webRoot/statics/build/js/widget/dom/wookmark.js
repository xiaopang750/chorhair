/*! company 罗格斯达 version 1.0.0 2015-06-17 */
define("widget/dom/wookmark",[],function(){}),function(e){"function"==typeof define&&define.amd?define("widget/dom/wookmark",[],e):e(jQuery)}(function(e){function t(e){a(function(){var t,r;for(t=0;e.length>t;t++)r=e[t],r.obj.css(r.css)})}function r(t){return e.trim(t).toLowerCase()}var i,n,o;o=function(e,t){return function(){return e.apply(t,arguments)}},n={align:"center",autoResize:!1,comparator:null,container:e("body"),ignoreInactiveItems:!0,itemWidth:0,fillEmptySpace:!1,flexibleWidth:0,direction:void 0,offset:2,onLayoutChanged:void 0,outerOffset:0,resizeDelay:50,possibleFilters:[]};var a=window.requestAnimationFrame||function(e){e()};i=function(){function i(t,r){this.handler=t,this.columns=this.containerWidth=this.resizeTimer=null,this.activeItemCount=0,this.itemHeightsDirty=!0,this.placeholders=[],e.extend(!0,this,n,r),this.update=o(this.update,this),this.onResize=o(this.onResize,this),this.onRefresh=o(this.onRefresh,this),this.getItemWidth=o(this.getItemWidth,this),this.layout=o(this.layout,this),this.layoutFull=o(this.layoutFull,this),this.layoutColumns=o(this.layoutColumns,this),this.filter=o(this.filter,this),this.clear=o(this.clear,this),this.getActiveItems=o(this.getActiveItems,this),this.refreshPlaceholders=o(this.refreshPlaceholders,this),this.sortElements=o(this.sortElements,this),this.updateFilterClasses=o(this.updateFilterClasses,this),this.updateFilterClasses(),this.autoResize&&e(window).bind("resize.wookmark",this.onResize),this.container.bind("refreshWookmark",this.onRefresh)}return i.prototype.updateFilterClasses=function(){for(var e,t,i,n,o=0,a=0,s=0,c={},l=this.possibleFilters;this.handler.length>o;o++)if(t=this.handler.eq(o),e=t.data("filterClass"),"object"==typeof e&&e.length>0)for(a=0;e.length>a;a++)i=r(e[a]),c[i]||(c[i]=[]),c[i].push(t[0]);for(;l.length>s;s++)n=r(l[s]),n in c||(c[n]=[]);this.filterClasses=c},i.prototype.update=function(t){this.itemHeightsDirty=!0,e.extend(!0,this,t)},i.prototype.onResize=function(){clearTimeout(this.resizeTimer),this.itemHeightsDirty=0!==this.flexibleWidth,this.resizeTimer=setTimeout(this.layout,this.resizeDelay)},i.prototype.onRefresh=function(){this.itemHeightsDirty=!0,this.layout()},i.prototype.filter=function(t,i){var n,o,a,s,c,l=[],u=e();if(t=t||[],i=i||"or",t.length){for(o=0;t.length>o;o++)c=r(t[o]),c in this.filterClasses&&l.push(this.filterClasses[c]);if(n=l.length,"or"==i||1==n)for(o=0;n>o;o++)u=u.add(l[o]);else if("and"==i){var d,h,f,p=l[0],m=!0;for(o=1;n>o;o++)l[o].length<p.length&&(p=l[o]);for(p=p||[],o=0;p.length>o;o++){for(h=p[o],m=!0,a=0;l.length>a&&m;a++)if(f=l[a],p!=f){for(s=0,d=!1;f.length>s&&!d;s++)d=f[s]==h;m&=d}m&&u.push(p[o])}}this.handler.not(u).addClass("inactive")}else u=this.handler;u.removeClass("inactive"),this.columns=null,this.layout()},i.prototype.refreshPlaceholders=function(t,r){for(var i,n,o,a,s,c,l=this.placeholders.length,u=this.columns.length,d=this.container.innerHeight();u>l;l++)i=e('<div class="wookmark-placeholder"/>').appendTo(this.container),this.placeholders.push(i);for(c=this.offset+2*parseInt(this.placeholders[0].css("borderLeftWidth"),10),l=0;this.placeholders.length>l;l++)if(i=this.placeholders[l],o=this.columns[l],l>=u||!o[o.length-1])i.css("display","none");else{if(n=o[o.length-1],!n)continue;s=n.data("wookmark-top")+n.data("wookmark-height")+this.offset,a=d-s-c,i.css({position:"absolute",display:a>0?"block":"none",left:l*t+r,top:s,width:t-c,height:a})}},i.prototype.getActiveItems=function(){return this.ignoreInactiveItems?this.handler.not(".inactive"):this.handler},i.prototype.getItemWidth=function(){var e=this.itemWidth,t=this.container.width()-2*this.outerOffset,r=this.handler.eq(0),i=this.flexibleWidth;if(void 0===this.itemWidth||0===this.itemWidth&&!this.flexibleWidth?e=r.outerWidth():"string"==typeof this.itemWidth&&this.itemWidth.indexOf("%")>=0&&(e=parseFloat(this.itemWidth)/100*t),i){"string"==typeof i&&i.indexOf("%")>=0&&(i=parseFloat(i)/100*t);var n=~~(.5+(t+this.offset)/(i+this.offset)),o=Math.min(i,~~((t-(n-1)*this.offset)/n));e=Math.max(e,o),this.handler.css("width",e)}return e},i.prototype.layout=function(e){if(this.container.is(":visible")){var t,r=this.getItemWidth()+this.offset,i=this.container.width(),n=i-2*this.outerOffset,o=~~((n+this.offset)/r),a=0,s=0,c=0,l=this.getActiveItems(),u=l.length;if(this.itemHeightsDirty||!this.container.data("itemHeightsInitialized")){for(;u>c;c++)t=l.eq(c),t.data("wookmark-height",t.outerHeight());this.itemHeightsDirty=!1,this.container.data("itemHeightsInitialized",!0)}o=Math.max(1,Math.min(o,u)),a=this.outerOffset,"center"==this.align&&(a+=~~(.5+(n-(o*r-this.offset))>>1)),this.direction=this.direction||("right"==this.align?"right":"left"),s=e||null===this.columns||this.columns.length!=o||this.activeItemCount!=u?this.layoutFull(r,o,a):this.layoutColumns(r,a),this.activeItemCount=u,this.container.css("height",s),this.fillEmptySpace&&this.refreshPlaceholders(r,a),void 0!==this.onLayoutChanged&&"function"==typeof this.onLayoutChanged&&this.onLayoutChanged()}},i.prototype.sortElements=function(e){return"function"==typeof this.comparator?e.sort(this.comparator):e},i.prototype.layoutFull=function(r,i,n){var o,a,s=0,c=0,l=e.makeArray(this.getActiveItems()),u=l.length,d=null,h=null,f=[],p=[],m="left"==this.align?!0:!1;for(this.columns=[],l=this.sortElements(l);i>f.length;)f.push(this.outerOffset),this.columns.push([]);for(;u>s;s++){for(o=e(l[s]),d=f[0],h=0,c=0;i>c;c++)d>f[c]&&(d=f[c],h=c);o.data("wookmark-top",d),a=n,(h>0||!m)&&(a+=h*r),(p[s]={obj:o,css:{position:"absolute",top:d}}).css[this.direction]=a,f[h]+=o.data("wookmark-height")+this.offset,this.columns[h].push(o)}return t(p),Math.max.apply(Math,f)},i.prototype.layoutColumns=function(e,r){for(var i,n,o,a,s=[],c=[],l=0,u=0,d=0;this.columns.length>l;l++){for(s.push(this.outerOffset),n=this.columns[l],a=l*e+r,i=s[l],u=0;n.length>u;u++,d++)o=n[u].data("wookmark-top",i),(c[d]={obj:o,css:{top:i}}).css[this.direction]=a,i+=o.data("wookmark-height")+this.offset;s[l]=i}return t(c),Math.max.apply(Math,s)},i.prototype.clear=function(){clearTimeout(this.resizeTimer),e(window).unbind("resize.wookmark",this.onResize),this.container.unbind("refreshWookmark",this.onRefresh),this.handler.wookmarkInstance=null},i}(),e.fn.wookmark=function(e){return this.wookmarkInstance?this.wookmarkInstance.update(e||{}):this.wookmarkInstance=new i(this,e||{}),this.wookmarkInstance.layout(!0),this.show()}});