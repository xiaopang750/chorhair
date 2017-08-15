/*! company 罗格斯达 version 1.0.0 2015-06-17 */
define("widget/dom/cut",[],function(){function e(e,t){for(var r in t)e.style[r]=t[r]}function t(e,t){return e.currentStyle?e.currentStyle[t]:getComputedStyle(e,!1)[t]}function r(e,t,r){e.attachEvent?e.attachEvent("on"+t,r):e.addEventListener(t,r,!1)}function i(i){function n(){p.innerHTML="<img src="+k+">",p.id="cover_added",y=p.children[0],y.width=R,y.height=P,e(p,{position:"fixed",zIndex:12002,cursor:"move",left:L+"px",top:O+"px",width:N+"px",height:D+"px",background:"#fff",overflow:"hidden"});var t=document.getElementById("cover_added");t&&document.body.removeChild(t),document.body.appendChild(p)}function o(){e(y,{position:"absolute",left:-(L-q)+"px",top:-(O-E)+"px"})}function a(){h.id="shadow_added",e(h,{width:R+"px",height:P+"px",left:q+"px",top:E+"px",position:"fixed",zIndex:12001,background:"#000",opacity:.6,filter:"alpha(opacity=60)"});var t=document.getElementById("shadow_added");t&&document.body.removeChild(t),document.body.appendChild(h),r(window,"resize",function(){var t,r;t=$(m).offset().left,r=$(m).offset().top,e(h,{left:t+"px",top:r+"px"}),L=Math.floor((R-N)/2)+t,O=Math.floor((P-D)/2)+r,e(p,{left:L+"px",top:O+"px"}),e(y,{left:-(L-t)+"px",top:-(O-r)+"px"}),q=t,E=r})}function s(){g.style.overflow="hidden",g.style.position="relative",g.innerHTML="<img src="+k+">",x=g.children[0],x.style.position="absolute";var e=g.offsetWidth+parseInt(t(g,"borderLeftWidth"))+parseInt(t(g,"borderLeftWidth"));return e}function c(){for(var t=["lt","lb","rt","rb","l","r","t","b","c"],r=[],i=t.length,n=[{width:j+"px",height:M+"px",position:"absolute",zIndex:I,cursor:"nw-resize",overflow:"hidden",background:"#000",left:0,top:0,border:"1px solid #fff"},{width:j+"px",height:M+"px",position:"absolute",zIndex:I,cursor:"sw-resize",overflow:"hidden",background:"#000",left:0,bottom:0,border:"1px solid #fff"},{width:j+"px",height:M+"px",position:"absolute",zIndex:I,cursor:"ne-resize",overflow:"hidden",background:"#000",right:0,top:0,border:"1px solid #fff"},{width:j+"px",height:M+"px",position:"absolute",zIndex:I,cursor:"se-resize",overflow:"hidden",background:"#000",right:0,bottom:0,border:"1px solid #fff"},{width:B+"px",height:"100%",position:"absolute",zIndex:H,cursor:"w-resize",overflow:"hidden",background:"url("+W+") repeat-y",left:0,top:0},{width:B+"px",height:"100%",position:"absolute",zIndex:H,cursor:"e-resize",overflow:"hidden",background:"url("+W+") repeat-y",right:0,top:0},{width:"100%",height:U+"px",position:"absolute",zIndex:H,cursor:"n-resize",overflow:"hidden",background:"url("+W+") repeat-x",left:0,top:0},{width:"100%",height:U+"px",position:"absolute",zIndex:H,cursor:"s-resize",overflow:"hidden",background:"url("+W+") repeat-x",left:0,bottom:0},{width:"95%",height:"95%",position:"absolute",zIndex:H,cursor:"move",overflow:"hidden",background:"#000",left:"2.5%",top:"2.5%",opacity:"0",filter:"alpha(opacity=0)"}],o=0;i>o;o++){var a=document.createElement("div");a.className=t[o],e(a,n[o]),r.push(a),p.appendChild(a)}return r}function l(e){for(var t=e.length,r=0;t>r;r++)u(e[r])}function u(e){var t=e.parentNode,r=27,i=37;e.onmousedown=function(n){function o(n){var o=n||event,a=document.documentElement.scrollTop||document.body.scrollTop,s=document.documentElement.scrollLeft||document.body.scrollLeft;if(-1!=e.className.indexOf("l")){var c=o.clientX-u;c>=d-r&&(c=d-r),q-h>=c&&(c=q-h),d*F+t.offsetTop-P-E>=c*F&&(c=(d*F+t.offsetTop-P-E)*(1/F));var l=d-c,w=h+c,k=-(w-q);t.style.width=l+"px",t.style.height=l*F+"px",t.style.left=w+"px",y.style.left=k+"px"}if("r"==e.className){var S=o.clientX-m+e.offsetWidth;r>S&&(S=r),S>=R-(t.offsetLeft-q)&&(S=R-(t.offsetLeft-q)),F*S>=P-(t.offsetTop-E)&&(S=(P-(t.offsetTop-E))*(1/F)),t.style.width=S+"px",t.style.height=F*S+"px"}if(-1!=e.className.indexOf("t")){var C=o.clientY-g;C>=v-i&&(C=v-i),E-b>=C&&(C=E-b),t.offsetLeft+v*(1/F)-q-R>=C*(1/F)&&(C=(t.offsetLeft+v*(1/F)-q-R)*F);var T=v-C,$=b+C,_=-($-E);t.style.height=T+"px",t.style.width=T*(1/F)+"px",t.style.top=$+"px",y.style.top=_+"px"}if(-1!=e.className.indexOf("b")){var A=o.clientY-x+e.offsetHeight;i>=A&&(A=i),A>=P-(t.offsetTop-E)&&(A=P-(t.offsetTop-E)),A*(1/F)>=R-(t.offsetLeft-q)&&(A=(R-(t.offsetLeft-q))*F),t.style.height=A+"px",t.style.width=A*(1/F)+"px"}if("lt"==e.className){var c=o.clientX-u;c>=d-r&&(c=d-r),q-h>=c&&(c=q-h),E-b>=c&&(c=E-b),-(c/F/2)>P-(b-E+v)&&(c=2*-(P-(b-E+v))*F);var l=d-c,w=h+c,k=-(w-q),T=v-c,$=b+c,_=-($-E);t.style.width=l+"px",t.style.height=l*F+"px",t.style.left=w+"px",t.style.top=$+"px",y.style.left=k+"px",y.style.top=_+"px"}if("lb"==e.className){var c=o.clientX-u;c>=d-r&&(c=d-r),q-h>=c&&(c=q-h),d*F+t.offsetTop-P-E>=c*F&&(c=(d*F+t.offsetTop-P-E)*(1/F));var l=d-c,w=h+c,k=-(w-q);t.style.width=l+"px",t.style.height=l*F+"px",t.style.left=w+"px",y.style.left=k+"px"}if(-1!=e.className.indexOf("c")){var S=o.clientX-m-s,A=o.clientY-x-a,N=q,D=q+R-t.offsetWidth,L=E,O=E+P-t.offsetHeight;N>S&&(S=N),S>D&&(S=D),L>A&&(A=L),A>O&&(A=O);var k=-(S-q),j=-(A-E);y.style.left=k+"px",y.style.top=j+"px",e.parentNode.style.left=S+"px",e.parentNode.style.top=A+"px"}f(t.offsetWidth,p.offsetLeft,p.offsetTop)}function a(){this.onmouseup=null,this.onmousemove=null,this.releaseCapture&&this.releaseCapture()}var s=n||event,c=document.documentElement.scrollTop||document.body.scrollTop,l=document.documentElement.scrollLeft||document.body.scrollLeft;if(-1!=e.className.indexOf("l"))var u=s.clientX,d=t.offsetWidth,h=t.offsetLeft;if(-1!=e.className.indexOf("r"))var m=s.clientX-this.offsetLeft-l;if(-1!=e.className.indexOf("t"))var g=s.clientY,v=t.offsetHeight,b=t.offsetTop;if(-1!=e.className.indexOf("b"))var x=s.clientY-this.offsetTop;if(-1!=e.className.indexOf("c"))var m=s.clientX-t.offsetLeft-l,x=s.clientY-t.offsetTop-c;return e.setCapture?(e.onmousemove=o,e.onmouseup=a,e.setCapture()):(document.onmousemove=o,document.onmouseup=a),!1}}function d(){var e=RegExp("http://"+window.location.host,"gi");b.onclick=function(){var t={};t.scale=C>T?P/T:R/C,t.tmpimg=m.src.split("?")[0].replace(e,""),t.x=(p.offsetLeft-q)/t.scale,t.y=(p.offsetTop-E)/t.scale,t.cutwidth=p.offsetWidth/t.scale,t.cutheight=t.cutwidth*F,t.oldWidth=C,S&&S(t)}}function f(e,t,r){var i=t-q,n=r-E,o=R/(e-2*B),a=R/v,s=R*o/a,c=-i*o/a,l=-n*o/a;x.width=s,x.style.left=c+"px",x.style.top=l+"px"}var h=document.createElement("div"),p=document.createElement("div"),m=i.oImage||null,g=i.oView||null,v=0,b=i.oSubmintBtn||null,y=null,x=null,w=null,k=m.src,S=i.fnDo||null,R=i.width||0,P=i.height||0,C=i.oldWidth||0,T=i.oldHeight||0,q=i.left||0,E=i.top||0,_=parseInt(i.scaleWidth)||"",A=parseInt(i.scaleHeight)||"",N=Math.floor(R*_/100),D=Math.floor(R*A/100),L=Math.floor((R-N)/2)+q,O=Math.floor((P-D)/2)+E,j=i.dotWidth||6,M=i.dotHeight||6,I=i.dotIndex||6,B=i.lineWidth||2,U=i.lineHeight||2,H=i.lineIndex||5,W=i.lineUrl||"/static/images/lib/global/line.gif",F=i.cutScale||A/_;n(),o(),a(),v=s(),f(p.offsetWidth,p.offsetLeft,p.offsetTop),w=c(),l(w),d()}return i});