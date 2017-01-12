import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OurFileWriter {
	
	//private String path = "C:\\Users\\Morten\\PulsMålinger\\Nåtz.txt";
	
	// constructor
	public OurFileWriter() {
	}
	
	public void writeDoubleToFile(double puls, String path) throws IOException{
		
		// Instantier alle filskriverne på én linje
		BufferedWriter bf = new BufferedWriter(new FileWriter(new File(path),true));
		
		//skriv hvad vi har lyst til
		bf.write("Din puls er " + String.valueOf(puls) + "\n");
		
		// close dat fuckboi
		bf.close();
	}
	
}