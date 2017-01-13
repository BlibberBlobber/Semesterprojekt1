import java.io.IOException;
import java.util.ArrayList;

public class MainController {

	private static ArrayList<Integer> list = new ArrayList<Integer>();
	private static double pulse;
	public static void main(String[] args) throws InterruptedException {
		
		Reciever reciever = new Reciever();
		OurFileWriter filewriter = new OurFileWriter();
		Converter converter = new Converter();
			
		//while(true){
		
		// napper listen
		list = reciever.getValue();
		// regner puls udfra listen 
		pulse = converter.doPulse(list);
				
		// Hvis pulsen er -1.1, så er de rikke gået et minut, så vi venter med at printe det ud.
		if(!(pulse==-1.1))
			try {
				filewriter.writeDoubleToFile(pulse);
			} catch (IOException e) {
				e.printStackTrace();
			}
				
		for(int val:list){
			System.out.println(val + "  ");
		}		
		
		//}
			
			/*
			 * Laver vi en form for kalibrering af tallene, s� vi ikke skriver snittet af 3 toppe ud men snittet af m�ske 5 par af toppe?
			 * Tid?
			 * kik lige om det egentligt er n�dvendigt at lave en liste til porte, hvis vi alligevel tager den f�rste hver gang?
			 */	
	}
	
}
