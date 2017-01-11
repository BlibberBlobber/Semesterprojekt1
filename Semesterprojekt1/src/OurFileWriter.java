import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class OurFileWriter {
	
	private String fileName = "Puls.txt";
	private String path = "";
	private double puls;
	public boolean append = false;
	
	public void OurFileWriter() throws IOException {
		if(!doFileExist(fileName)) makeEmptyFile();
	}
	
	public void writeDoubleToFile(double puls){
		
		// s?t ind hvis filen er tom ellers s? g? en linje ned og skriv
		// 
	}
	
	public void makeEmptyFile() throws IOException{
		FileWriter fw = new FileWriter(fileName, append = true);

	    BufferedWriter bw = new BufferedWriter(fw);
	    PrintWriter pw = new PrintWriter(bw);
	    pw.println(bw);
	    pw.close();
	    // System.out.println("Skrevet til fil:"+input);
	}
		
		
		
		//brug fileName og path
		// laver en tom txt fil i path
	
	public boolean doFileExist(String fileName){
		
		// check om filen med navnet fileName findes p? path 
		
		return true;
	}
}

/*
public class OurFileWriter {

private String fileName = "Puls.txt";
private String path = "";
private double puls;

public OurFileWriter() {
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
*/