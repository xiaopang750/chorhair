package com.rockstar.o2o.lucene;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopFieldCollector;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.search.WildcardQuery;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.rockstar.o2o.util.RedisUtils;
public class IndexSearch {

	  private static String pathFile=System.getenv("CHORHAIR")==null?"d:/lucene/index":
		  (System.getenv("NEWLUCENEPATH")==null?"/application/tomcat-6.0.41/server_50002/lucene/index":(System.getenv("NEWLUCENEPATH"))); 
	  private static   ReturnPojo pojo=new ReturnPojo();
	  

     /**
      * 根据关键字,当前页数,每页显示条数
      * @param keyword
      * @param curpage
      * @param pageSize
      * @return
      * @throws Exception
      */
	 @SuppressWarnings("unchecked")
	public static ReturnPojo search(String openid,String keyword,String combotype,String contenttype,String fairtype,String pkShop,int curpage ,int pageSize) {  	
		 
		    IndexReader reader=null;		     
	        try{	
	        String errlog=System.getenv("NEWLUCENEPATH");
	        if(errlog==null){
	        	System.out.println("未设置lucene的系统变量");
	        }
		    int start =  (curpage - 1) * pageSize;  	    	
	     	        
	        Directory dir=FSDirectory.open(new File(pathFile));  
	        
	        reader=DirectoryReader.open(dir);  		
	        	
	        BooleanQuery bQuery = new BooleanQuery();
	      
	        IndexSearcher searcher=new IndexSearcher(reader);  
	        
	        if(keyword!=null&&!keyword.equals("")){

	        if(contenttype!=null&&!contenttype.equals("")){
	        	 WildcardQuery  topic = new WildcardQuery(new Term("contenttype", contenttype));  	     	    
	 	         bQuery.add(topic,Occur.MUST);
	        }
	        
	        if(pkShop!=null&&!pkShop.equals("")){
	        	 WildcardQuery  topic = new WildcardQuery(new Term("pkShop", pkShop));  	     	    
	 	         bQuery.add(topic,Occur.MUST);
	        }
	        
	        if(combotype!=null&&!combotype.equals("")){
	        	 WildcardQuery  topic = new WildcardQuery(new Term("combotype", combotype));  	     	    
	 	         bQuery.add(topic,Occur.MUST);
	        }
	        
	        if(fairtype!=null&&!fairtype.equals("")){
	        	 WildcardQuery  topic = new WildcardQuery(new Term("fairtype", fairtype));  	     	    
	 	         bQuery.add(topic,Occur.MUST);
	        }
	        
	        
	        WildcardQuery  topic = new WildcardQuery(new Term("topic", "*"+keyword+"*"));  
	    
	        bQuery.add(topic,Occur.SHOULD);
	        
	        for(int i=0;i<10;i++){
	        	
	        	WildcardQuery tag = new WildcardQuery(new Term("tag"+i, keyword));  
	        	
	 	        bQuery.add(tag,Occur.SHOULD);
	        }
	        
	        }else{
	            if(contenttype!=null&&!contenttype.equals("")){
		        	 WildcardQuery  topic = new WildcardQuery(new Term("contenttype", contenttype));  	     	    
		 	         bQuery.add(topic,Occur.MUST);
		        }
		        
		        if(combotype!=null&&!combotype.equals("")){
		        	 WildcardQuery  topic = new WildcardQuery(new Term("combotype", combotype));  	     	    
		 	         bQuery.add(topic,Occur.MUST);
		        }
		        
		        if(fairtype!=null&&!fairtype.equals("")){
		        	 WildcardQuery  topic = new WildcardQuery(new Term("fairtype", fairtype));  	     	    
		 	         bQuery.add(topic,Occur.MUST);
		        }
		        
		        
		        if(pkShop!=null&&!pkShop.equals("")){
		        	 WildcardQuery  topic = new WildcardQuery(new Term("pkShop", pkShop));  	     	    
		 	         bQuery.add(topic,Occur.MUST);
		        }
		        
	        	WildcardQuery tag = new WildcardQuery(new Term("all", ""));  
	            bQuery.add(tag,Occur.SHOULD);
	        }
	                 
            
	        int hm = start + pageSize;  
	        
	        Sort sort=null;
	        
	        TopFieldCollector res=null;
            if(contenttype!=null&&contenttype.equals("combo")){            	
                 sort = new Sort();
                 SortField f1 = new SortField("fairtype", SortField.Type.STRING, false);
                 SortField f2 = new SortField("comboprice",  SortField.Type.DOUBLE, false);
                 sort.setSort(new SortField[] { f1, f2 });
     	         res = TopFieldCollector.create(sort, hm, false, false, false, false);
            }else{
            	 sort = new Sort();
                 SortField f = new SortField("ts", SortField.Type.STRING, false);
                 sort.setSort(f);
            	 res = TopFieldCollector.create(sort, hm, false, false, false, false);
            }
	       
	        
	    	//TopScoreDocCollector res = TopScoreDocCollector.create(hm, false); 
	    	
	        searcher.search(bQuery, res); 
	        
//	        int rowCount = res.getTotalHits();  //所有的条数
//	        
//	        System.out.println(rowCount);
//	        
//	        int pages = (rowCount - 1) / pageSize + 1; //计算总页数  
//	        
//	        System.out.println(pages);
	        
	        TopDocs tds = res.topDocs(start, pageSize);  
	        
	        ScoreDoc[] scoreDocs = tds.scoreDocs;  
 
	        ArrayList<JSONObject> objlist=new ArrayList<JSONObject>();
	        

	        for(int i=0; i < scoreDocs.length; i++) {  
	        	
	            int doc = scoreDocs[i].doc;  
	            
	            Document document = searcher.doc(doc);  
	            
	            JSONObject obj=new JSONObject();
	            
	            obj.accumulate("id", document.get("id"));
	            
	            obj.accumulate("praisecount",document.get("praisecount")==null?"0":document.get("praisecount"));		
	            	            
	            obj.accumulate("url", document.get("url"));
	            
	            obj.accumulate("topic", document.get("topic"));
	            
	            obj.accumulate("firstpage", document.get("firstpage"));
	            
	            obj.accumulate("ts", document.get("ts"));
	            
	            obj.accumulate("width", document.get("width"));
	            
	            obj.accumulate("height", document.get("height"));
	            
	            if(document.get("taglist")!=null&&!document.get("taglist").equals("")){
	              obj.accumulate("taglist", document.get("taglist"));	
	            }
	            
	            //查询该内容页的收藏次数，以及查询的人是否收藏过此内容
	            try {
	            	Integer count=RedisUtils.getObject("collect:id:"+document.get("id"))==null?0:(Integer)RedisUtils.getObject("collect:id:"+document.get("id"));
				    obj.accumulate("collectcount", count);
				    
				    if(openid!=null&&!openid.equals("")){
				    	Object oldobj=RedisUtils.getObject("collect:id:"+document.get("id")+":"+"totalcount")==null?null:RedisUtils.getObject("collect:id:"+document.get("id")+":"+"totalcount");
				        if(oldobj!=null&&!oldobj.equals("")){
				        ArrayList<Object> oldlist=(ArrayList<Object>) oldobj;	
				        if(oldlist.size()>0){
				        	if(oldlist.contains(openid)){
				        	obj.accumulate("iscollect", "Y"); 
				        	}else{
				        	obj.accumulate("iscollect", "N"); 	
				        	}
				         }
				        }
				    
				    }
	            } catch (Exception e) {
					// TODO: handle exception
	            	e.printStackTrace();
	            	System.out.println("redis报错");
				}
	            
	            if(contenttype!=null&&contenttype.equals("combo")){
	            	
	            	obj.accumulate("comboname", document.get("comboname"));//套餐名称		            
		            obj.accumulate("comboprice", document.get("comboprice"));//套餐价格
		            
		            Integer combocount=Integer.parseInt(RedisUtils.getObject("comment:"+"combo:"+document.get("pk"))==null?"0":RedisUtils.getObject("comment:"+"combo:"+document.get("pk")).toString());
		
		            obj.accumulate("commentcount", combocount);//次数
	 		     }
	            
	            
	            
       		 if(contenttype!=null&&contenttype.equals("fairer")){
       			obj.accumulate("fairername", document.get("fairername") ); //理发师名称
       			obj.accumulate("pkShop", document.get("pkShop") ); //店铺
       			obj.accumulate("rankname", document.get("rankname") ); //职称
       			obj.accumulate("skills", document.get("skills") ); //技能  
       			
	            Integer fairercount=Integer.parseInt(RedisUtils.getObject("comment:"+"fairer:"+document.get("pk"))==null?"0":RedisUtils.getObject("comment:"+"fairer:"+document.get("pk")).toString());
	    		
	            obj.accumulate("commentcount", fairercount);//次数
	            
 		     }
       		 
    		   if(contenttype!=null&&contenttype.equals("shop")){
    			    obj.accumulate("pkShop", document.get("pkShop") ); //店铺
        			obj.accumulate("shopname", document.get("shopname") ); //店铺名称
        			obj.accumulate("fixphone", document.get("fixphone") ); //固定电话
        			obj.accumulate("cellphone", document.get("cellphone") ); //手机号
        			obj.accumulate("businessour", document.get("businessour") ); //营业时间  
        			obj.accumulate("addr", document.get("addr") ); //地址 	   
        			obj.accumulate("location", document.get("location") ); //坐标      
        			
        			Integer fairercount=Integer.parseInt(RedisUtils.getObject("comment:"+"shop:"+document.get("pk"))==null?"0":RedisUtils.getObject("comment:"+"shop:"+document.get("pk")).toString());
      	    		
      	            obj.accumulate("commentcount", fairercount);//次数
      	            
	            }  
    		 
    		   objlist.add(obj);
	        }
    		   
	        if(objlist.size()>0){	
	        	
		        pojo.setData(JSONArray.fromObject(objlist).toString());
		        
		        pojo.setIssuccess(true);
		        
		        pojo.setMsg("查询成功");
		        
	        	
	        }else{
	        	
	        	pojo.setData("");
	        	
	        	pojo.setIssuccess(true);
	        	
	        	pojo.setMsg("没有结果");
	        	

	        }
		   }catch (Exception e) {
			// TODO: handle exception
			   e.printStackTrace();
			   pojo.setIssuccess(false);
			   pojo.setMsg("没有结果");
		   }finally{
			   if(reader!=null){
				   try {
					reader.close();
				 } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				 }
			   }
		   }
	       return pojo;        
	  }  


}
