package folder;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.swing.text.html.HTMLDocument.Iterator;

public class Document_Class implements Comparable<Document_Class>{
	public String id="";
	public String title="";
	public Integer qdate=0;
	public String date="";
	public String content="";
	public String high_lighted_content="";
	public String facet_array[] = new String[5];
	public Map<String,String[]> cluster_map =new TreeMap<String,String[]>();
	
	public Document_Class(String id,String title,Integer qdate,String date,String content,String high_lighted_content)
	{
		this.title=title;
		this.date=date;
		this.qdate=qdate;
		this.content=content;
		this.high_lighted_content=high_lighted_content;
		this.id=id;
		
	}
	public String getId()
	{
		return this.id;
	}
	public String getTitle()
	{
		return this.title;
	}
	public String getDate()
	{
		return this.date;
	}
	public String getContent()
	{
		return this.content;
	}
	public Integer getQdate()
	{
		return this.qdate;
	}
	public String getHighLightedContent()
	{
		return this.high_lighted_content;
	}
	public void setFacets(String[] facets)
	{
		for(int i=0;i<facets.length;i++)
		{
		
			this.facet_array[i] = facets[i];
		}
	}
	public void setMaps(Map<String,String[]> clusters)
	{
		this.cluster_map=clusters;
		
	}
	public int compareTo(Document_Class o) {
		// TODO Auto-generated method stub
		int compareQuantity = ((Document_Class) o).getQdate();
		return compareQuantity - this.getQdate();
	}
	
}
