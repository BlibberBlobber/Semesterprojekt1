import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OurFileWriter {
	private DateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
	private Date date = new Date();
	private String path = "C:\\Users\\Morten\\PulsMålinger\\Nåtz.txt";
	
	// constructor
	public OurFileWriter() {
	}
	
	public void writeDoubleToFile(double pulse) throws IOException{
		
		// Instantier alle filskriverne på én linje
		BufferedWriter bf = new BufferedWriter(new FileWriter(new File(path),true));
		
		//skriv hvad vi har lyst til
		bf.write("Din puls er " + String.valueOf(pulse) + "\n" + "Dato: " + dateformat.format(date) + "\n");
		
		// close dat fuckboi
		bf.close();
	}
	
}