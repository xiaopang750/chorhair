package com.rockstar.o2o.lucene;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.rockstar.o2o.util.DateUtil;

public class IndexFile {

	    
	    private static Directory dir;  
	 
	    private static String pathFile=System.getenv("CHORHAIR")==null?"d:/lucene/index":
	    	(System.getenv("NEWLUCENEPATH")==null?"/application/tomcat-6.0.41/server_50002/lucene/index":(System.getenv("NEWLUCENEPATH")));; 
	    
	     /** 
	     * 初始添加文档 
	     * @throws Exception 
	     */  	 

	@SuppressWarnings("static-access")
	public static String init(String  content,String data) {  	        
		   IndexWriter writer=null;
		   try {
			   if(System.getenv("NEWLUCENEPATH")==null){
				   System.out.println("未设置lucene的系统变量");
			   }
		       dir=FSDirectory.open(new File(pathFile));  
	           writer=getWriter(); 
	           Document doc=new Document(); 
	           JSONObject contentdata=JSONObject.fromObject(content);
	           doc.add(new StringField("id", contentdata.get("pkContent").toString(), Store.YES));  //行id
	           doc.add(new StringField("width", contentdata.get("width")==null?"0":contentdata.get("width").toString(), Store.YES));  //宽
	           doc.add(new StringField("height",contentdata.get("height")==null?"0":contentdata.get("height").toString(), Store.YES));  //高
	           doc.add(new StringField("topic", (String)contentdata.get("contenttopic"), Store.YES));//主题为模糊索引
	           doc.add(new StringField("praisecount", contentdata.get("praisecount")==null?"0":contentdata.get("praisecount").toString(), Store.YES));//主题为模糊索引
	           doc.add(new StringField("url",(String)contentdata.get("contenturl"), Store.YES)); //URL
	           doc.add(new StringField("firstpage",(String)contentdata.get("firstpage"), Store.YES)); //搜索页首页图片
	           doc.add(new StringField("ts", DateUtil.getCurrDate(DateUtil.FORMAT_ONE), Store.YES)); //时间戳
	           doc.add(new StringField("contenttype",(String)contentdata.get("contenttype"), Store.YES)); //内容类型
	           doc.add(new StringField("all","", Store.YES));  //行id
	           JSONArray tags=(JSONArray) contentdata.get("tags");
	           if(data!=null&&!data.equals("")){
	        	   String contenttype=(String)contentdata.get("contenttype");
	        	   //如果是套餐的话,需要另外放置一些属性,供查询使用
	        	   if(contenttype!=null&&!contenttype.equals("")){	        		   
	        		 if(contenttype.equals("combo")){
	        			JSONObject combo=JSONObject.fromObject(data);
	 	   	        	doc.add(new StringField("comboname", (String)combo.get("comboname"), Store.YES)); //套餐名称
	 	   	        	doc.add(new StringField("comboprice",combo.get("combomoney")==null?"0":combo.get("combomoney").toString(), Store.YES)); //套餐价格
	 	   	        	doc.add(new StringField("combotype",(String)combo.get("combotype"), Store.YES)); //套餐名称
	 	   	        	doc.add(new StringField("fairtype",(String)combo.get("fairtype"), Store.YES)); //发型类型    
	 	   	            doc.add(new StringField("pk", combo.getString("pkCombo"), Store.YES)); //套餐主键
	        		 }
	        		 //如果是理发师的话，需要加一些额外的属性,包括理发师名称,所属店铺,职称,技能
	        		 if(contenttype.equals("fairer")){
	        			    JSONObject fair=JSONObject.fromObject(data);
		 	   	        	doc.add(new StringField("fairername", (String)fair.get("fairername"), Store.YES)); //理发师名称
		 	   	        	doc.add(new StringField("pkShop",fair.get("pkShop").toString(), Store.YES)); //所属店铺
		 	   	        	doc.add(new StringField("rankname",(String)fair.get("rankname"), Store.YES)); //职称
		 	   	        	doc.add(new StringField("skills",((JSONArray)fair.get("skills")).toString(), Store.YES)); //技能    
		 	   	            doc.add(new StringField("pk", fair.getString("pkFairer"), Store.YES)); //理发师主键
	        		 }
	        		 
	        		 //如果是店铺的话，需要加一些额外的属性
	        		 if(contenttype.equals("shop")){
	        			    JSONObject shop=JSONObject.fromObject(data);
	        			    doc.add(new StringField("pkShop",shop.get("pkShop").toString(), Store.YES)); //所属店铺
		 	   	        	doc.add(new StringField("shopname", (String)shop.get("shopname"), Store.YES)); //店铺名称
		 	   	        	doc.add(new StringField("fixphone",(String)shop.get("fixphone"), Store.YES)); //固定电话
		 	   	            doc.add(new StringField("cellphone",(String)shop.get("cellphone"), Store.YES)); //手机号
		 	   	        	doc.add(new StringField("businessour",(String)shop.get("businessour"), Store.YES)); //营业时间
		 	   	        	doc.add(new StringField("addr",(String)shop.get("addr"), Store.YES)); //地址
		 	   	            doc.add(new StringField("location",(String)shop.get("location"), Store.YES)); //坐标
		 	   	            doc.add(new StringField("pk", shop.getString("pkShop"), Store.YES)); //店铺主键
	        		 }
	        	   }
	        	
	           }
	           if(tags!=null&&!tags.equals("")){
	        	   Iterator<?> iter=tags.iterator();
	        	   int i=0;
	        	   while(iter.hasNext()){
	        		   JSONObject obj=(JSONObject) iter.next();
	        		   String tagname=(String)obj.get("tagname");
	        		   doc.add(new StringField("tag"+i, tagname, Store.YES));//标签为完全匹配索引
	        		   i++;
	        	   }
	        	   doc.add(new StringField("taglist", tags.toString(), Store.YES));
	           }	 
	          if(writer.isLocked(dir)){
	        	  dir.clearLock(dir.getLockID());
	          }
	        writer.addDocument(doc);   
	        return "success";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return "fail";
			}  finally{
				if(writer!=null){
			    try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				  }		
			   }
			}
	    }  
	  
	 /**
	 * 更新点赞数
	 * @param id
	 * @param count
	 */
	  public static void updatepraise(String id,String count){
		    IndexReader reader=null;	
		    IndexWriter writer=null;
		    try {
				   if(System.getenv("LUCENEPATH")==null){
					   System.out.println("未设置lucene的系统变量");
				   }
		    dir=FSDirectory.open(new File(pathFile));  
		     writer=getWriter(); 	         	        
	         reader=DirectoryReader.open(dir);  	
	         IndexSearcher searcher=new IndexSearcher(reader); 
	         Term term=new Term("id",id);
	         TermQuery query=new TermQuery(term);
	         TopDocs topdocs=searcher.search(query, 1);
	         ScoreDoc[] scoreDocs=topdocs.scoreDocs;
	         if(scoreDocs.length>0){	        	
	            int doc = scoreDocs[0].doc;  
	             Document document = searcher.doc(doc); 
	             if(document.get("praisecount")!=null){
	            	 document.removeField("praisecount"); 
	             }	            
	             document.add(new StringField("praisecount",count, Store.YES));//主题为模糊索引
	             document.add(new StringField("all","", Store.YES));//主题为模糊索引
	             writer.updateDocument(term, document);   
	             writer.commit();
	         } 
	        	        
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				if(reader!=null){
					try {
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(writer!=null){
					try {
						writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
	  }
	  
	  
	   /** 
	     * 获得IndexWriter对象 
	     * @return 
	     * @throws Exception 
	     */  
	    public static IndexWriter getWriter() throws Exception {  
	       Analyzer analyzer=new StandardAnalyzer(Version.LUCENE_46);  
	       IndexWriterConfig iwc=new IndexWriterConfig(Version.LUCENE_46, analyzer);  
	       return new IndexWriter(dir, iwc);  
	    }  
	    
	    
		 /** 
	     * 删除索引 
	     * @return 
	     * @throws Exception 
	     */ 	    
	    public static String delete(String id) {  
	    	IndexWriter writer=null;
	    	try {
				   if(System.getenv("LUCENEPATH")==null){
					   System.out.println("未设置lucene的系统变量");
				   }
	        dir=FSDirectory.open(new File(pathFile));  
	        writer=getWriter(); 
	        Term term=new Term("id", id);  
	        writer.deleteDocuments(term);
            writer.commit();//提交，正式删除  
            writer.close();//关闭  
			return "success";
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return "fail";
			}finally{
				if(writer!=null){
				    try {
						writer.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				    }		
				}
			}
	  }
}
