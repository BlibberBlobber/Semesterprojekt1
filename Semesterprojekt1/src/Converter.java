import java.util.ArrayList;

public class Converter { 
	
	private double currentSlope = 0, newBaseLine = 0;
	private double minLine = 300, maxLine = 600;
	private boolean ready = true;
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
			if(currentSlope > 0 && list.get(index) > maxLine && ready){
				pulseCounter++;
				ready = false;
			}
			
			// sørger for at vi er klar igen, når vi er under middelværdi og en negativ hældning
			if(currentSlope < 0 && list.get(index) < minLine && !ready){
				ready = true;
			}
					
			//beregn ny max og min line
			newBaseLine += list.get(index);
		}

		maxLine = (newBaseLine/list.size())*1.7;
		minLine = newBaseLine/list.size();
//		System.out.println("maxLine " + maxLine);
//		System.out.println("minLine " + minLine);
		newBaseLine = 0;
		//System.out.println("newBaseLine: " + newBaseLine);
		
		//12000 pladser 5 ms, 60000 er et minut
		
		pulse = pulseCounter;
		switch(arrayCounter){
		case 1:
//			System.out.println("case 1 = " + pulse*5);
			return pulse*5;
		case 2:
//			System.out.println("case 2 = " + pulse*2.5);
			return pulse*2.5;
		case 3:
//			System.out.println("case 3 = " + pulse*1.67);
			return pulse*1.67;
		case 4:
//			System.out.println("case 4 = " + pulse*1.25);
			return pulse*1.25;
		case 5:
//			System.out.println("case 5 = " + pulse);
			newBaseLine = 0;
			pulseCounter = 0;
			arrayCounter = 0;
			return pulse;
		}
		
		//safety
		System.out.println("Saftevand");
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
