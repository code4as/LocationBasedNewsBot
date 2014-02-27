import java.io.FileInputStream;
import java.io.InputStream;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.Span;
public class OpenNLP{
public String location(String q)
{
	 String coords = " ";
	try {
		   GeoNames g = new GeoNames();
		  
		   InputStream modelIn = new FileInputStream("G:/WorkspaceNew/SolrWebsite1/WebContent/en-token.bin");
		   TokenizerModel tokenModel = new TokenizerModel(modelIn);
		   modelIn.close();
		   Tokenizer tokenizer = new TokenizerME(tokenModel);
		   NameFinderME nameFinder =
		      new NameFinderME( new TokenNameFinderModel(new FileInputStream("G:/WorkspaceNew/SolrWebsite1/WebContent/en-ner-location.bin")));
		   String tokens[] = tokenizer.tokenize(q);
		   Span nameSpans[] = nameFinder.find(tokens);
		   String temp[] = Span.spansToStrings(nameSpans, tokens);
		   //System.out.println(Arrays.toString(Span.spansToStrings(nameSpans, tokens)));
		   for(int i=0; i<temp.length; i++)
		   {
		 	  coords = g.getCoords(temp[i]);
		   }
		}
		catch(Exception e) {
		   System.out.println(e.toString());
		}
	return coords;
}
}
