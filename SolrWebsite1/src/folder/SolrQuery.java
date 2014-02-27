package folder;
import java.net.MalformedURLException;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.ModifiableSolrParams;

public class SolrQuery {
  public static String setQuery(String args) throws MalformedURLException, SolrServerException {
	  String s="";
    SolrServer server = new HttpSolrServer("http://localhost:8983/solr");
        ModifiableSolrParams params = new ModifiableSolrParams();
        params.set("q", args);
        QueryResponse response = server.query(params);
        //System.out.println("response = " + response);
        System.out.println("Results returned are..");
        SolrDocumentList documents = response.getResults();
        if(documents!=null)
        {
        for (SolrDocument document : documents) { 
        	  //Integer id = (Integer) document.get("id");
        	  s= document.toString();
        	  System.out.println(s);
        	  //load this product from the database using its id
        	}
        }
      return s;  
  }
  
} 


