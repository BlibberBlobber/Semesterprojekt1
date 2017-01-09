import java.util.ArrayList;

public class Monitor {

	private static ArrayList<Integer> list = new ArrayList<Integer>();
	private static double puls;
	public static void main(String[] args) throws InterruptedException {
		
		//førstetest
		// test for git
		//heodjednk
		//instantiere reciever objektet s� vi �bner porten og g�r klar til at l�se
		Reciever reciever = new Reciever();
		// instantiere FileWriter objektet s� vi kan skrive til en txt fil p� computeren
		FileWriter filewriter = new FileWriter();
		// instantiere converter objektet, s� vi kan f� omregnet alle vores v�rdier til en puls
		Converter converter = new Converter();
			
		//l�kken vi er i s� l�nge vi m�ler {
			
			// laver en liste med 600 double v�rdier i
			list = reciever.getValue();
			
			// giver converter listen med de 600 tal
			puls = converter.doPuls(list);
			
			/*
			 * Laver vi en form for kalibrering af tallene, s� vi ikke skriver snittet af 3 toppe ud men snittet af m�ske 5 par af toppe?
			 * Tid?
			 * 
			 * kik lige om det egentligt er n�dvendigt at lave en liste til porte, hvis vi alligevel tager den f�rste hver gang?
			 */
			
			
			System.out.println("Vi printer den modtaget liste i integers");
			
			for(int val:list){
				System.out.println(val + "  ");
			}		
	}
	
}
