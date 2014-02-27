import opennlp.tools.cmdline.PerformanceMonitor;
import opennlp.tools.cmdline.postag.POSModelLoader;
import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.ObjectStream;
import opennlp.tools.util.PlainTextByLineStream;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

public class OpenNlpTest {
//public static void main(String[] args) throws IOException {
public String posTagger(String input) throws IOException{
	System.out.println("----------------------within openNLP------------------------------");
    POSModel model = new POSModelLoader().load(new File("G:/WorkspaceNew/SolrWebsite1/WebContent/en-pos-maxent.bin"));
   // PerformanceMonitor perfMon = new PerformanceMonitor(System.err, "sent");
    POSTaggerME tagger = new POSTaggerME(model);
    String output = " ";
    StringBuilder finalOutput = new StringBuilder();

   //String input = "Stroms in Mumbai and China and Jenifer";
    ObjectStream<String> lineStream =
            new PlainTextByLineStream(new StringReader(input));

   // perfMon.start();
    String line;
    while ((line = lineStream.read()) != null) {

        String whitespaceTokenizerLine[] = WhitespaceTokenizer.INSTANCE.tokenize(line);
        String[] tags = tagger.tag(whitespaceTokenizerLine);

        POSSample sample = new POSSample(whitespaceTokenizerLine, tags);
        output = sample.toString();
        //System.out.println(output);
        
    }
    
    for(int i=0; i<output.length();i++){
    	//System.out.println("In1");
	if(output.contains("_")){
		//System.out.println("In2");
		int indexNew = output.indexOf("_");
		//System.out.println(indexNew);
		
		int indexSpace = output.indexOf(" ");
		
		if(indexSpace<0)
			indexSpace=output.length()-1;
		//System.out.println(indexSpace);
		//System.out.println(output.substring(indexNew, indexSpace));
		if(output.substring(indexNew, indexSpace).equals("_NNP")||output.substring(indexNew+1).equals("NNP")){
			finalOutput.append(output.substring(0,indexNew)).append(" ");
			//System.out.println(finalOutput.toString());
			
		}
		output = output.substring(indexSpace+1);
		//System.out.println(output);
		}
	//System.out.println(finalOutput.toString());
		}
    //System.out.println("final"+finalOutput.toString()+"String");
    finalOutput.toString().trim();
    if(!finalOutput.toString().equals(""))
    return finalOutput.toString();
    else 
    return input;
}
    
	}

//}