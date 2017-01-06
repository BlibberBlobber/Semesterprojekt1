import java.util.ArrayList;

public class Monitor {

	private static ArrayList<Double> list = new ArrayList<Double>();
	
	public static void main(String[] args) throws InterruptedException {
		
		// test for git
		//heodjednk
		//instantiere reciever objektet så vi åbner porten og gør klar til at læse
		Reciever reciever = new Reciever();
		// instantiere FileWriter objektet så vi kan skrive til en txt fil på computeren
		FileWriter filewriter = new FileWriter();
		// instantiere converter objektet, så vi kan få omregnet alle vores værdier til en puls
		Converter converter = new Converter();
			
		//løkken vi er i så længe vi måler {
			
			// laver en liste med 600 double værdier i
			list = reciever.getValue();
			
			// giver converter listen med de 600 tal
			converter.setList(list);
			
			/*
			 * Skal vi controllere alt herfra eller sætter vi en chain igang der spytter en puls ud på den anden side?
			 * 
			 * Laver vi en form for kalibrering af tallene, så vi ikke skriver snittet af 3 toppe ud men snittet af måske 5 par af toppe?
			 * Tid?
			 * 
			 * kik lige om det egentligt er nødvendigt at lave en liste til porte, hvis vi alligevel tager den første hver gang?
			 */
			
			
			//System.out.println("Vi printer den modtaget liste i doubles, én på hver linje ");
			
//			for(double val:list){
//				System.out.println(val + "  ");
//			}		
	}
	
}
