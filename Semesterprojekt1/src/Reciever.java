import java.util.ArrayList;
import jssc.*;

public class Reciever {

	private int numberOfInputs = 20;
	private double parsedDouble = 0.0;
	private String bufferInput = "";
	private String rest = ""; 
	
	private SerialPort port;
	private ArrayList<Double> list = new ArrayList<Double>();
	
			
	// Constructor for klassen med setup af porten, s� vi kan l�se det arduinoen udskriver
	public Reciever(){
		setupSignal();
	}
			
	public ArrayList<Double> getValue() throws InterruptedException {
		
		// g�r listen tom, s� vi er klar p� ny 
		list.clear();
		
		// Hvis listen ikke er 600 lang, s� putter vi flere v�rdier ind.
		while(list.size()<numberOfInputs){  
			
			try{
				// Ser om der ligger data i bufferen, hvis der g�r, s� skal vi da se p� det!
				if(port.getInputBufferBytesCount() > 0){
					
					// Put hvad der er p� buffen i en lang streng - bufferInput
					bufferInput = port.readString();
					
					// Fjern den f�rste v�rdi, fordi den tit var trash
					bufferInput = bufferInput.substring(bufferInput.indexOf("-")+1);
					
					// loop vi er i s� l�nge Str�ngen er l�ngere end 5 og antalet af elementer i vores ArrayList er under numberOfInputs
					while(bufferInput.length()>5 && list.size()<numberOfInputs){
						
						// parser den n�ste v�rdi i st�ngen og gemmer den i parsedDouble
						parsedDouble=Double.parseDouble(bufferInput.substring(0, bufferInput.indexOf("-")));
						
						// hvis det tal vi lige har konvertreet fra str�ngen ikke er i intervallet 0-5, smider vi det ud, for der er sket en fejl
						if(!(parsedDouble>5 || parsedDouble<0)){
							list.add(parsedDouble);
						} //else System.out.println("Tallet var udenfor intervallet og vi s�tter det ikke ind!");

				// gem resten af det og s�t p� enden af den b�ste m�ling
						
						// slet det forerste som vi har taget, s� vi er klar til at gemme det n�ste tal i r�kken
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
		//returnere listen med 600 m�linger til monitoren
		return list;
	}
			
	private void setupSignal(){
		try{
			// Laver et array af porte og s�tter de tilg�ngelige porte ind
			String[] portArray = SerialPortList.getPortNames();
			// Gemmer navnet p� den f�rste port i portlisten i portName
			String portName = portArray[0];
			// laver en instans af SerialPort (jssc), port, med argumentet portName, som var dne f�rste port i listen af porte
			port = new SerialPort(portName);
			// porten �bnes
			port.openPort();
			
			// standard parametre der skal s�tes ved �bning af port
			// - 9600 er baud rate, som s�ttes til at v�re det samme som arduinoen k�rer
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