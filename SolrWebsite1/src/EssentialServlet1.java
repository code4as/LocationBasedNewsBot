
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.solr.client.solrj.SolrServerException;

import folder.Document_Class;

/**
 * Servlet implementation class EssentialServlet
 */

public class EssentialServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
   // public EssentialServlet1() {
        // TODO Auto-generated constructor stub
    //}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 //System.out.println("hi");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String set;
		String[] facet_array = new String[5];
		//ArrayList<Document_Class> to_display = new ArrayList<Document_Class>();
		System.out.println("back in servlet");
		TreeSet<Document_Class> ts = null;
		String s1=request.getParameter("fn");
		ts = SolrQuery1.setQuery(s1);
		if(ts == null){
			 request.getRequestDispatcher("/home.jsp").forward(request, response);
		}
		request.setAttribute("message", ts);
		Iterator<Document_Class > tree_itr = ts.iterator();
		
		while(tree_itr.hasNext())
		{
			 
			facet_array= tree_itr.next().facet_array;
			
		}
		ArrayList<String> a=new ArrayList<String>();
		for(int i=0;i<facet_array.length;i++)
		{
			a.add(facet_array[i]);
		}
		HttpSession session = request.getSession();
		session.setAttribute("facet_array", a);
		session.setAttribute("cluster_of_docs", ts);
		set=SolrQuery1.maps_constant;
		request.setAttribute("maps_initial", set);
        request.getRequestDispatcher("/display.jsp").forward(request, response);
		
	}

}
