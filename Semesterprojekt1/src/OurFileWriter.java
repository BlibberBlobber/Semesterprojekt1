import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class OurFileWriter {
	private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private DateFormat dateTid = new SimpleDateFormat("hh:mm:ss");
	private String path = "C:\\Users\\Morten\\PulsMålinger\\Nåtz.txt";
	private Scanner sc = new Scanner(System.in);
	
	// constructor
	public OurFileWriter() throws IOException {
		noteSetup();
	}
	
	public void writeDoubleToFile(double pulse) throws IOException{
		
		// Instantier alle filskriverne på én linje
		BufferedWriter bf = new BufferedWriter(new FileWriter(new File(path),true));
		
		//skriv hvad vi har lyst til
		bf.write("Puls: " + String.valueOf(pulse) + "\n");
		
		// close dat fuckboi
		bf.close();
	}
	public void noteSetup() throws IOException{
		
		BufferedWriter bf = new BufferedWriter(new FileWriter(new File(path),true));
		System.out.println("Skriv dit navn: ");
		String name = sc.nextLine();
		bf.write("\n - - - - - - - - - - - - - ");
		bf.write("\nNavn: " + name +  "\nDato: " + dateFormat.format(new Date()) + "\n" + "Kl: " + dateTid.format(new Date()) + "\n");
		bf.write(" - - - - - - - - - - - - - \n \n");
		bf.close();
	}
	
	
	
}