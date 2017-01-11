import java.util.ArrayList;

public class Converter {

	private ArrayList<Integer> list; 
	private String pulse;
	private double slope;
	private int x,y;
	private boolean isSlope=false;
	private int pulseCounter;
	private int arrayCounter=0;
	
	
	public Converter() {
		
	}
	
	public String doPulse(ArrayList<Integer> list){
		this.list = list;
		arrayCounter++;
		
		
	
			if (checkSlope()) pulseCounter++;
		
			
			//12000 pladser 5 ms, 60000 er et minnut
		
		
		return pulse;
		
	}
	
	public double calcSlope(int a, int b){
		x=a;
		y=b;
		
		// beregn hældning udfra de to koordinater
		
		return slope;
	}
	
	public boolean checkSlope(){
		
		// kig på værdier til venstre og højre
		// returner true, hvis hvis vi havde en ændring fra positiv til negativ hældning
		// returner false, hvis overstående 
		
		return isSlope;
	}
	
	

	
}
