var Hongru={};
function H$(id){return document.getElementById(id)}
function H$$(c,p){return p.getElementsByTagName(c)}
Hongru.fader = function(){
 function init(anchor,options){
  this.anchor = anchor;
  var wp = H$(options.id),
   ul = H$$('ul',wp)[0],
   li = this.li = H$$('li',ul);
  this.index = options.position?options.position:0;
  this.a = options.auto?options.auto:2;
  this.cur = this.z = 0;
  this.l = li.length;
  this.img = [];
  for(var k=0;k<this.l;k++){
   this.img.push(H$$('img',this.li[k])[0]);
  }
  this.curC = options.curNavClass?options.curNavClass:'fader-cur-nav';
  nav_wp = document.createElement('div');
  nav_wp.id = 'fader-nav';
  nav_wp.style.cssText = 'position:absolute;left:10px;top:0;padding:8px 0;';
  var alt = this.alt = document.createElement('p');
  for(var i=0;i<this.l;i++){
   this.li[i].o = 100;
   //setOpacity(this.li[i],this.li.o);
   this.li[i].style.opacity = this.li[i].o/100;
   this.li[i].style.filter = 'alpha(opacity='+this.li[i].o+')';
   //���ƿ�����
   var nav;//�˴��Ҽ���var nav;��Ȼ�Ļ���IE6���浯������˵navδ���壡2012-12-17
   var nav = document.createElement('a');
   nav.className = options.navClass?options.navClass:'fader-nav';
   nav.innerHTML = i+1;
   nav.onclick = new Function(this.anchor+'.pos('+i+')');
   nav_wp.appendChild(nav);
  }
  wp.appendChild(alt);  
  wp.appendChild(nav_wp);
  this.textH = nav_wp.offsetHeight;
    alt.style.cssText = 'color:#fff;font-size:12px;margin:0;position:absolute;left:0;bottom:0;overflow:hidden;width:100%;background:#000;opacity:0.7;filter:alpha(opacity=70);';//�粻������֣�ɾ����һ�У�
    alt.style.height = alt.style.lineHeight = this.textH+'px';
  this.pos(this.index);
 }
 init.prototype={
  auto:function(){
   this.li.a = setInterval(new Function(this.anchor+'.move(1)'),this.a*2000); 
  },
  move:function(i){
   var n = this.cur+i;
   var m = i==1?n==this.l?0:n:n<0?this.l-1:n;
   this.pos(m);
  },
  pos:function(i){
   clearInterval(this.li.a);
   clearInterval(this.li[i].f);
   var curLi = this.li[i];
   this.z++;
   curLi.style.zIndex = this.z;
   this.alt.style.zIndex = this.z+1;
   nav_wp.style.zIndex = this.z+2;
   this.li.a=false;//��仰�Ǳ�Ҫ��
   this.cur = i;
   //setOpacity(curLi[i],0);
   if(this.li[i].o>=100){
    this.li[i].o = 0;
    curLi.style.opacity = 0;
    curLi.style.filter = 'alpha(opacity=0)';
    this.alt.style.height = '0px';
   }
   for(var x=0;x<this.l;x++){
    nav_wp.getElementsByTagName('a')[x].className = x==i?this.curC:'fader-nav';
    }
   this.alt.innerHTML = this.img[i].alt;
   this.li[i].f = setInterval(new Function(this.anchor+'.fade('+i+')'),20);
  },
  fade:function(i){
  var p=this.li[i];
   if(p.o>=100){
    clearInterval(p.f);
    if(!this.li.a){//һ��Ҫ�����������Ƿ��Ѿ�clearInterval���жϣ�Ҫ��Ȼ�ڿ��ٵ����ʱ��ᵼ��ͼƬ��ͣ����
     this.auto();
    }
   }
   else{
    p.o+=5;
    //setOpacity(this.li[i],this.li[i].o);
    p.style.opacity = p.o/100;
    p.style.filter = 'alpha(opacity='+p.o+')';
    this.alt.style.height = Math.ceil(p.o*this.textH/100)+'px';
   }
  }
 };
 return {init:init};
}();

//表格，鼠标拂过高亮
var  highlightcolor='#c1ebff';
//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
var  clickcolor='#51b2f6';
function  changeto(){
	source=event.srcElement;
	if  (source.tagName=="TR"||source.tagName=="THEAD")
	return;
	while(source!=null&&source.tagName!="TD")
	source=source.parentElement;
	if(source!=null){
		source=source.parentElement;
		cs  =  source.children;
		if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
			for(i=0;i<cs.length;i++){
				cs[i].style.backgroundColor=highlightcolor;
		}
	}
	
}

function  changeback(){
	if  (event.fromElement.contains(event.toElement)||source==null||source.contains(event.toElement)||source.id=="nc")
	return
	if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
	//source.style.backgroundColor=originalcolor
		for(i=0;i<cs.length;i++){
			cs[i].style.backgroundColor="";
		}
	}

function  clickto(){
	source=event.srcElement;
	if  (source.tagName=="TR"||source.tagName=="TABLE")
		return;
	while(source.tagName!="TD")
		source=source.parentElement;
		source=source.parentElement;
		cs  =  source.children;
		//alert(cs.length);
	if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
		for(i=0;i<cs.length;i++){
		cs[i].style.backgroundColor=clickcolor;
	}else
		for(i=0;i<cs.length;i++){
		cs[i].style.backgroundColor="";
	}
	}
