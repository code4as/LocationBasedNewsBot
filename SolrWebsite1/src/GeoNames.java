
import java.util.List;
import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;
 
public class GeoNames {
 
        public String getCoords(String query) throws Exception {
                // TODO Auto-generated method stub
                  WebService.setUserName("vjsa"); // add your username here
                  String coords = " ";
                  ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
                  searchCriteria.setQ(query);
                  ToponymSearchResult searchResult = WebService.search(searchCriteria);
                  int count = searchResult.getTotalResultsCount();
                 // System.out.println(toponym.getName()+":"+ toponym.getLatitude()+","+toponym.getLongitude());
                  if(count>0)
                  {
                          List<Toponym> toponym= searchResult.getToponyms();
                          Toponym t = new Toponym();
                          t=toponym.get(0);
                          double lat = t.getLatitude();
                          double lon = t.getLongitude();
                          coords = lat+","+lon;
                          
                  	     
                 }
                  return coords;
        }
 
}