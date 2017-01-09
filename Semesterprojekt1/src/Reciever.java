import java.util.ArrayList;
import jssc.*;

public class Reciever {

	private int numberOfInputs = 20;
	private double parsedDouble = 0.0;
	private String bufferInput = "";
	private String rest = ""; 
	
	private SerialPort port;
	private ArrayList<Double> list = new ArrayList<Double>();
	
			
	// Constructor for klassen med setup af porten, så vi kan læse det arduinoen udskriver
	public Reciever(){
		setupSignal();
	}
			
	public ArrayList<Double> getValue() throws InterruptedException {
		
		// gør listen tom, så vi er klar på ny 
		list.clear();
		
		// Hvis listen ikke er 600 lang, så putter vi flere værdier ind.
		while(list.size()<numberOfInputs){  
			
			try{
				// Ser om der ligger data i bufferen, hvis der gør, så skal vi da se på det!
				if(port.getInputBufferBytesCount() > 0){
					
					// Put hvad der er på buffen i en lang streng - bufferInput
					bufferInput = port.readString();
					
					// Fjern den første værdi, fordi den tit var trash
					bufferInput = bufferInput.substring(bufferInput.indexOf("-")+1);
					
					// loop vi er i så længe Strængen er længere end 5 og antalet af elementer i vores ArrayList er under numberOfInputs
					while(bufferInput.length()>5 && list.size()<numberOfInputs){
						
						// parser den næste værdi i stængen og gemmer den i parsedDouble
						parsedDouble=Double.parseDouble(bufferInput.substring(0, bufferInput.indexOf("-")));
						
						// hvis det tal vi lige har konvertreet fra strængen ikke er i intervallet 0-5, smider vi det ud, for der er sket en fejl
						if(!(parsedDouble>5 || parsedDouble<0)){
							list.add(parsedDouble);
						} //else System.out.println("Tallet var udenfor intervallet og vi sætter det ikke ind!");

				// gem resten af det og sæt på enden af den bæste måling
						
						// slet det forerste som vi har taget, så vi er klar til at gemme det næste tal i rækken
						bufferInput = bufferInput.substring(bufferInput.indexOf("-") + 1);
					}
					
					if(bufferInput.length()>){}
					
				} // vi venter lige 40 milisekunder, da der ikke var noget i bufferen, for lige at give dne en chance..
				else Thread.sleep(40);
			
			}catch (SerialPortException ex){
				System.out.println("Serial port exception: " + ex);
			}catch (StringIndexOutOfBoundsException ex) {
				System.out.println("substring error");
			}
		}
		//returnere listen med 600 målinger til monitoren
		return list;
	}
			
	private void setupSignal(){
		try{
			// Laver et array af porte og sætter de tilgængelige porte ind
			String[] portArray = SerialPortList.getPortNames();
			// Gemmer navnet på den første port i portlisten i portName
			String portName = portArray[0];
			// laver en instans af SerialPort (jssc), port, med argumentet portName, som var dne første port i listen af porte
			port = new SerialPort(portName);
			// porten åbnes
			port.openPort();
			
			// standard parametre der skal sætes ved åbning af port
			// - 9600 er baud rate, som sættes til at være det samme som arduinoen kører
			// - 8 er antallet af dataBits
			// - 1 er stop bits
			// - 0 er paritetstypen 
			port.setParams(9600, 8, 1, 0);
			port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
			port.setDTR(true);
		}
		catch (SerialPortException ex) {
			System.out.println("Serial Port Exception: " + ex);
		} 
	}
		
}