import java.util.ArrayList;

public class Converter {

	private ArrayList<Integer> list; 
	private double puls;
	private double slope;
	private int x,y;
	private boolean isSlope;
	
	public Converter() {
		
	}
	
	public double doPuls(ArrayList<Integer> list){
		this.list = list;
		
		while(checkSlope()){
			
			
		}
		
		return puls;
		
	}
	
	public double calcSlope(int a, int b){
		x=a;
		y=b;
		
		// beregn hældning udfra de to koordinater
		
		return slope;
	}
	
	public boolean checkSlope(){
		
		// kik på værdier til venstre og højre
		// returner true, hvis hvis vi havde en ændring fra positiv til negativ hældning
		// returner false, hvis overstående 
		
		return isSlope;
	}
	
	

	
}
