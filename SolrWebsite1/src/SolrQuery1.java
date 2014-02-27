
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.util.NamedList;
import org.noggit.JSONUtil;

import folder.Document_Class;



public class SolrQuery1 {
	public static String maps_constant;
	public static  String original_title ="";
	public static  String original_content ="";
	public static TreeSet<Document_Class> setQuery(String args) throws IOException 
		{ 
		  System.out.println("query here is : " + args);
		  Document_Class dc= new Document_Class("","", 0, "", "", "");
		  List<String> highightSnippet_Content = null;
		  List<String> highightSnippet_Title = null;
		  ArrayList<Document_Class> to_display = new ArrayList<Document_Class>();
		  String query=args;
		  TreeSet<Document_Class> ts = null;
		  try{
		  //setting up SOLR connection
		  SolrServer server = new HttpSolrServer("http://localhost:8983/solr");
		  ((HttpSolrServer) server).setParser(new XMLResponseParser()); 
		  System.out.println("connection established");
		  
		  //forming the solrquery and querying
	      SolrQuery sq= new SolrQuery();
	      sq.setQuery(query);
	      
	      //code for setting query type as dismax
	      /*sq.set("defType", "dismax");
	      sq.set("qf", "location^5");*/
	      
	      //code for highlighting
	      sq.setHighlight(true).setHighlightSnippets(2); //set other params as
	      sq.setParam("hl.fl", "content title");
	      
	      //code for boosting on qdate
	      sq.set("bf", "qdate");
	      
	      //code for spatial search -- getting coordinates of the location in query
	      int dist = stringExample.distance(query);
		  OpenNlpTest pos = new OpenNlpTest();
		  String newLocations = pos.posTagger(query);
		  OpenNLP nlp = new OpenNLP();
		  System.out.println("finding location..");
		  String coords1 = nlp.location(newLocations);
		  System.out.println("coords are.." + coords1);
	      if(!newLocations.equals(query)){
	        	 sq.addFilterQuery("{!func}geodist()"); 
	        	 sq.set("sort", "geodist() asc");
	        	 sq.set("pt", coords1);
	        	 sq.set("sfield", "coords");
	        	 sq.set("d", dist);
	      }
      //System.out.println("setin locations");
	    //code for getting response form the query and store it in document list
        QueryResponse queryResponse = server.query(sq);
		  if(queryResponse == null)
		  {
			  System.out.println("response is null");
			  return null;
		  }

        SolrDocumentList documents = queryResponse.getResults();
        
        //getting a json response for plotting on maps
        maps_constant = JSONUtil.toJSON(documents);
        
        //iterate through each individual document grabbing the highlighted title and content
        if(documents!=null)
        {
              ListIterator<SolrDocument> docs=documents.listIterator();
		      while (docs.hasNext()) {
		     SolrDocument document = (SolrDocument) docs.next();
	         int qdate= (Integer) document.getFieldValue("qdate");
	         String date=(String) document.getFieldValue("date");
	          String content = (String) document.getFieldValue("content");
	          //System.out.println("content is-------------->" + content);
		      String id = (String) document.getFieldValue("id"); 
		     // System.out.println("id is------------------>" + id);

		      if (queryResponse.getHighlighting().get(id) != null) {
		    	  highightSnippet_Content = queryResponse.getHighlighting().get(id).get("content");
		    	  highightSnippet_Title = queryResponse.getHighlighting().get(id).get("title");
		      }
		     
		      Iterator<String> hs=highightSnippet_Content.listIterator();
		      while(hs.hasNext())
		      {
		    	  original_content = original_content + " " +  hs.next().trim();
		      }
		      
		      original_content = original_content.replaceAll("\\s{2}+", " ");
		      //System.out.println("final content is---->" + original_content);
		      
		      Iterator<String> hs1=highightSnippet_Title.listIterator();
		      while(hs1.hasNext())
		      {
		    	  original_title = original_title + " " +  hs1.next().trim();
		      }
		      original_title = original_title.replaceAll("\\s{2}+", " ");
		      //System.out.println("final title is---->" + original_title);
		      dc=new Document_Class(id,original_title, qdate, date, content, original_content);
	          to_display.add(dc);
	          
	          //final adjustments
	        
	          
	          
	         ts=new TreeSet<Document_Class>(to_display);
	         //System.out.println("setting facets");
	         //setting facets
	         List<FacetField> dcmt_list=queryResponse.getFacetFields();
	         String Docs = dcmt_list.toString();
	  	     Docs = Docs.substring(18);
			 String[] newfacets = Docs.split("\\,");
			 String[] facets = new String[5] ;
			 if(newfacets.length<5){
				 for(int i=0;i<newfacets.length;i++){
					 facets[i]= newfacets[i];
				 }
			 }
			 else
			 {
				 for(int i=0;i<5;i++){
					 facets[i] = newfacets[i];
					 
				 }
			 }
			 
			 NamedList<Object> documentsCluster = queryResponse.getResponse();

		        ArrayList al = new ArrayList();
		        for(Entry<String, Object> e : documentsCluster) {

		            if(e.getKey().toString().equalsIgnoreCase("clusters"))
		            {
				al = (ArrayList) e.getValue();
			    }
		        }
		        
		        Iterator ir = al.iterator();
		        Pattern pl = Pattern.compile("labels\\=\\[(.*?)\\]");
		        Pattern pd = Pattern.compile("docs\\=\\[(.*?)\\]");
		        Matcher m = null;	
			//map to be accessed
		        Map<String,String[]> hm = new HashMap<String, String[]>();

		        while(ir.hasNext())
		        { String labels="default";
		        String [] docs1={};
		        String docstring="0";
		        	String list = ir.next().toString();
		        	m= pl.matcher(list);
		        	if (m.find()) {
		        		labels=m.group(1);
		                
		            }
		        	m=pd.matcher(list);
		        	if (m.find()) {
		        		docstring = m.group(1).toString().replaceAll("\\s", "");
		        		docs1 = docstring.split(",");
		               
		            }
		        	

		        	//System.out.println("Label is "+labels+" "+"Docs size "+ docs1.length);
		        	//insert cluster name and string array of doc ids into map
		        	hm.put(labels, docs1);
		        	dc.setMaps(hm);
		        	//System.out.println("maps set");
		        	
		        	
		        	
		        }
			 
			 dc.setFacets(facets);
			 dc=null;
	         original_content="";
	         original_title="";
			 
	          
		    } 
		      
		 }
        
        //storing in a treeset to return
         
		  }catch(SolrServerException e)
		  {
			  System.out.println("Error in SolrQuery1" + e);
			  
		  }
	      return ts;  
	  }
  
} 



