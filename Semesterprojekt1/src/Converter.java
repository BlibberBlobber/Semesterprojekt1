import java.util.ArrayList;

public class Converter { 
	
	private double currentSlope, currentBaseLine = 500, newBaseLine = 0;
	private boolean ready = false;
	private int pulseCounter = 0;
	private int arrayCounter = 0;
	private int pulse = 0;
	
	public Converter() {
		
	}
	
	public double doPulse(ArrayList<Integer> list){
		// tæller antal arrays op, hver gang vi bliver kaldt, så vi kan holde styr på tiden. (1 array er 12 sek)
		arrayCounter++;
		
		// går i for each loop og laver calc slope på alle par
		for(int index = 0; index < list.size(); index++){
			
			// returnere hældning i punktet vi er i, ved brug af punktet før og efter 
			if(index==list.size()-1 || index==0);
			else currentSlope = calcSlope(list.get(index - 1), list.get(index + 1));
			
			// tæller pulseCounter op hvis vi har en systole og vi er over vores grænseværdi(meanSlope/middelværdi)
			if(currentSlope > 0 && list.get(index) > currentBaseLine && ready){
				pulseCounter++;
				ready = false;
			}
			
			// sørger for at vi er klar igen, når vi er under middelværdi og en negativ hældning
			if(currentSlope < 0 && list.get(index) < currentBaseLine && !ready){
				ready = true;
			}
					
			// tæl pulseCounter op, når der sker en fortegnsændring fra + til -
			newBaseLine += list.get(index);
		}
		
		newBaseLine = newBaseLine/list.size();
		
		//12000 pladser 5 ms, 60000 er et minut
		
		pulse = pulseCounter;
		switch(arrayCounter){
		case 1:
			return pulse*5;
		case 2:
			return pulse*2.5;
		case 3:
			return pulse*1.67;
		case 4:
			return pulse*1.25;
		case 5:
			currentBaseLine = newBaseLine;
			pulseCounter = 0;
			arrayCounter = 0;
			return pulse;
		}
		
		//safety
		return pulse;

		// hvis arrayCounter er 5, så er der gået et minut, og vi vil gerne retunere værdien for pulsen 
//		if(arrayCounter==5)	{
//			
//			// vi sætter en ny baseline udfra de seneste 5 arrays
//			// pulsen er lig det antal puslsag vi har talt
//			// pulsetælleren og listetælleren sættes tilbage til 0, så vi kan blive ved med at måle
//			currentBaseLine = newBaseLine;
//			pulse = pulseCounter;
//			pulseCounter = 0;
//			arrayCounter = 0;
//			return pulse;
//		} else{
//			// System.out.println("pulseCounter " + pulseCounter );
//			// System.out.println("newBaseLine " + newBaseLine);
//			currentBaseLine = newBaseLine;
//			return -1.1;
//		}
	}
	
	private int calcSlope(int x, int y){
		return y-x;
	}	
}
