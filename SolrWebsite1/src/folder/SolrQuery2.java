package folder;

import java.util.ArrayList;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.TermsResponse;
import org.apache.solr.client.solrj.response.TermsResponse.Term;
public class SolrQuery2 {
	public static String[] temp;

// TODO Auto-generated method stub
	public static ArrayList<String> query(String q) {
  // public static void main(String args[]){
		System.out.println("within solrquery2");
		temp=q.split("\\s");
		q=temp[temp.length-1];
		String original="";
		for(int i=0 ; i <= temp.length-2; i++)
       	{
			original = original + " " +  temp[i];
       		System.out.println(original);

       	}
		original = original + " ";
   int limit = 5;
   ArrayList<Term> items = null;
   ArrayList<Term> items1 = null; 
   ArrayList<String> return_list_1 = new ArrayList<String>();
   ArrayList<String> return_list_2 = new ArrayList<String>();
    SolrServer server = new HttpSolrServer("http://localhost:8983/solr");
    ((HttpSolrServer) server).setParser(new XMLResponseParser()); 
    // escape special characters
    SolrQuery query = new SolrQuery();
    query.addTermsField("title");
    query.addTermsField("location");
    query.setTerms(true);
    query.setTermsLimit(limit);
    query.setTermsLower(q);
    query.setTermsPrefix(q);
    query.set("qt","/terms");
 //   query.

    try {
        QueryResponse qr = server.query(query);
        TermsResponse resp = qr.getTermsResponse();
        items = (ArrayList<Term>) resp.getTerms("location");
        items1 = (ArrayList<Term>) resp.getTerms("title");
       // items = resp.getTerms("spell");
    } catch (SolrServerException e) {
     	items = null;
    }
    
   // Iterator<Term> itr = items.iterator();
    for(Term t : items){
         	//System.out.println(t.getTerm());
       
       	//System.out.println(original + t.getTerm());
        return_list_2.add(original + t.getTerm());
           }
    for(Term t : items1){
       //	System.out.println(t.getTerm());
    	
    	//System.out.println(original);
       	return_list_2.add(original + t.getTerm());
        }
    
    return_list_1.addAll(return_list_2);
    
   return return_list_1;
}
//	}

}

//}
