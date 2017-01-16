import java.io.IOException;
import java.util.ArrayList;

public class MainController {

	// private static int yolo = 0;
	private static ArrayList<Integer> list = new ArrayList<Integer>();
	private static double pulse;
	
	public static void main(String[] args) throws InterruptedException {
		
		Reciever reciever = new Reciever();
		OurFileWriter filewriter = new OurFileWriter();
		Converter converter = new Converter();
			
		while(true){
			
		// napper listen
		list = reciever.getValue();
		// regner puls udfra listen 
		
		
		// vi vil estimere pulsen undervejs hvert 12 sek. som opdatere udfra det forrrige
		for(int i = 0; i<5;i++){
			pulse = converter.doPulse(list);
			System.out.println(pulse);
		}
		// efter for er der gået et minut og vi skriver svaret ind i filen
		try {
			filewriter.writeDoubleToFile(pulse);
		} catch (IOException e) {
			e.printStackTrace();
		}
				
//		for(int val:list){
//			System.out.println(val + "  ");
//		}	
		
		if(pulse > 0){
			System.out.println("\n " + " pulse " + pulse + "\n");
		}
		
		
		}
			/*
			 * Laver vi en form for kalibrering af tallene, s� vi ikke skriver snittet af 3 toppe ud men snittet af m�ske 5 par af toppe?
			 * Tid?
			 * kik lige om det egentligt er n�dvendigt at lave en liste til porte, hvis vi alligevel tager den f�rste hver gang?
			 */	
	}
	
}
