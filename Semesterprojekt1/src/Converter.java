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
			
			// tæller pulseCounter op hvis vi har en systole og vi er over vores grænseværdi
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

		maxLine = (newBaseLine/list.size())*1.5;
		minLine = newBaseLine/list.size();
		newBaseLine = 0;

		
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
			newBaseLine = 0;
			pulseCounter = 0;
			arrayCounter = 0;
			return pulse;
		}
		return pulse;
	}
	
	private int calcSlope(int x, int y){
		return y-x;
	}	
}
