import java.util.ArrayList;

public class Converter { 
	
	private double slope, meanSlope = 500, blahSlope = 0;
	private int x,y;
	private boolean ready = false;
	private int pulseCounter = 0;
	private int arrayCounter = 0;
	private int nabNutz = 0;
	
	public Converter() {
	}
	
	public double doPulse(ArrayList<Integer> list){
		// tæller antal arrays op, hver gang vi bliver kaldt, så vi kan holde styr på tiden. (1 array er 12 sek)
		arrayCounter++;
		
		// går i for each loop og laver calc slope på alle par
		for(int index = 0; index < list.size(); index++){
			
			// returnere hældning i punktet vi er i, ved brug af punktet før og efter 
			if(index==list.size()-1 || index==0);
			else slope = calcSlope(list.get(index - 1), list.get(index + 1));
			
			// tæller pulseCounter op hvis vi har en systole og vi er over vores grænseværdi(meanSlope/middelværdi)
			if(slope > 0 && list.get(index) > meanSlope && ready){
				pulseCounter++;
				ready = false;
			}
			
			// sørger for at vi er klar igen, når vi er under middelværdi og en negativ hældning
			if(slope < 0 && list.get(index) < meanSlope && !ready){
				ready = true;
			}
					
			// tæl pulseCounter op, når der sker en fortegnsændring fra + til -
			blahSlope += list.get(index);
		}
		
		blahSlope = blahSlope/list.size();
		
		//12000 pladser 5 ms, 60000 er et minut
		
		// hvis arrayCounter er ? så er der gået et minut, og vi vil gerne retunere værdien for pulsen 
		if(arrayCounter==5)	{
			
			meanSlope = blahSlope;
			nabNutz = pulseCounter;
			pulseCounter = 0;
			arrayCounter=0;
			return nabNutz;
		}
		else{
			System.out.println("pulseCounter " + pulseCounter );
			System.out.println("blahSlope " + blahSlope);
			meanSlope = blahSlope;
			return -1.1;
		}
	}
	
	public int calcSlope(int a, int b){
		x=a; y=b;
		return y-x;
	}	
}
