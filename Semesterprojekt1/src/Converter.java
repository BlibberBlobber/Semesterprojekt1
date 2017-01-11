import java.util.ArrayList;

public class Converter {

	private ArrayList<Integer> list; 
	private double puls;
	private double slope;
	private int x,y;
	private boolean bSlope;
	
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
		
		return bSlope;
	}
	
	

	
}
