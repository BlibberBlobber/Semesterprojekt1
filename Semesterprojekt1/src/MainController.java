import java.util.ArrayList;

public class MainController {

	private static ArrayList<Integer> list = new ArrayList<Integer>();
	private static double puls;
	public static void main(String[] args) throws InterruptedException {
		
		Reciever reciever = new Reciever();
		OurFileWriter filewriter = new OurFileWriter();
		Converter converter = new Converter();
			
		// est fra toby
		
		//løkken vi er i så længe vi måler {
			
			// laver en liste med 600 double værdier i
			list = reciever.getValue();
			
			// giver converter listen med de 600 tal
			puls = converter.doPuls(list);
			
			/*
			 * Laver vi en form for kalibrering af tallene, s� vi ikke skriver snittet af 3 toppe ud men snittet af m�ske 5 par af toppe?
			 * Tid?
			 * kik lige om det egentligt er n�dvendigt at lave en liste til porte, hvis vi alligevel tager den f�rste hver gang?
			 */
			
			
			System.out.println("Vi printer den modtaget liste i integers");
			
			for(int val:list){
				System.out.println(val + "  ");
			}		
	}
	
}
