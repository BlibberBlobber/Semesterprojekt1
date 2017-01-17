import java.io.IOException;
import java.util.ArrayList;

public class MainController {

	// private static int yolo = 0;
	private static int i=0;
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
			if(i==4) {
			filewriter.writeDoubleToFile(pulse);
			i=0;
		} else i++;
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		System.out.println("pulse: " + pulse + "");
		
		for(int val:list){
			System.out.println(val);
		}		
		}
			/*
			 * kik lige om det egentligt er n�dvendigt at lave en liste til porte, hvis vi alligevel tager den f�rste hver gang?
			 */	
	}
	
}
