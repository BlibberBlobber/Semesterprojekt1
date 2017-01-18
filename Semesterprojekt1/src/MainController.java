import java.io.IOException;
import java.util.ArrayList;

public class MainController {

	private static int datasetCounter=0;
	private static ArrayList<Integer> list = new ArrayList<Integer>();
	private static double pulse;
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
		Reciever reciever = new Reciever();
		OurFileWriter filewriter = new OurFileWriter();
		Converter converter = new Converter();
			
		while(true){
			
		// napper listen
		list = reciever.getValue();
		// regner puls udfra listen 
		
		// vi vil estimere pulsen undervejs hvert 12 sek. som opdatere udfra det forrrige

			pulse = converter.doPulse(list);
					
		// efter for er der gået et minut og vi skriver svaret ind i filen
		try {
			if(datasetCounter==4) {
			filewriter.writeDoubleToFile(pulse);
			datasetCounter=0;
		} else datasetCounter++;
		} catch (IOException e) {
			e.printStackTrace();
		}
		//System.out.println("pulse estimate: " + pulse + "");
		}
	}
	
}
