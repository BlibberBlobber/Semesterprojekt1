
public class FileWriter {
	
	private String fileName = "Puls.txt";
	private String path = "";
	private double puls;
	//hej
	
	
	public FileWriter() {
		if(!doFileExist(fileName)) makeEmptyFile();
	}
	
	public void writeDoubleToFile(double puls){
		
		// s�t ind hvis filen er tom ellers s� g� en linje ned og skriv
		
	}
	
	public void makeEmptyFile(){
		// brug fileName og path
		// laver en tom txt fil i path
	}
	
	public boolean doFileExist(String fileName){
		
		// check om filen med navnet fileName findes p� path 
		
		return true;
	}
}
