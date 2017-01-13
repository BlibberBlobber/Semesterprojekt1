import java.util.ArrayList;
import jssc.*;

public class Reciever {

	private int i = 0;
	private boolean først = true;
	private int baudRate = 38400;
	private int numberOfInputs = 600;
	private int parsedInt = 0;
	private int a = -1,b = -1,c = -1,d = -1, snit=0;
	private String bufferInput = "";
	private String yolo = "";
	
	private SerialPort port;
	private ArrayList<Integer> list = new ArrayList<Integer>();
	
			
	// Constructor for klassen med setup af porten, så vi kan løse det arduinoen udskriver
	public Reciever(){
		setupSignal();
	}
			
	public ArrayList<Integer> getValue() throws InterruptedException {
		
		// gør listen tom, så vi er klar på ny 
		list.clear();
		
		// Hvis listen ikke er 600 lang, så putter vi flere værdier ind.
		while(list.size()<numberOfInputs){  
			
			try{
				// Ser om der ligger data i bufferen, hvis der gør, så skal vi da se på det!
				if(port.getInputBufferBytesCount() > 0){
					
					// Put hvad der er på buffen i en lang streng - bufferInput
					bufferInput += port.readString();
					
					//System.out.println(bufferInput);
					
					// Fjern den f�rste v�rdi, fordi den tit var trash
					if(først){
						Thread.sleep(20);
						bufferInput += port.readString();
						bufferInput += bufferInput.substring(bufferInput.indexOf("-"));
						først = false;
					}
					
					// loop vi er i, så længe der er en bindestreg Strengen og antallet af elementer i vores ArrayList er under numberOfInputs
					while(bufferInput.contains("-") && list.size()<numberOfInputs){
						
						if(bufferInput.substring(0, bufferInput.indexOf("-")).contains("null") || bufferInput.substring(0, bufferInput.indexOf("-")).equals("")){
							yolo = bufferInput.substring(0, bufferInput.indexOf("-"));
							System.out.println("hva fan der var null" + yolo);
							bufferInput = bufferInput.substring(bufferInput.indexOf("-") + 1);
						}
						
						// parser den næste værdi i strengen og gemmer den i parsedDouble
						if(!(bufferInput.substring(0)=="-")){
						parsedInt=Integer.parseInt(bufferInput.substring(0, bufferInput.indexOf("-")));
						}
						
						// hvis tallene vi lige har konverteret fra strengen er i intervallet [0:1024], så brug 4 værdier og beregn gennemsnittet
						// Vi fylder en plads i listen op med gennemsnittet af fire værdier
						if((parsedInt<1024 && parsedInt>=0)){
							
							//System.out.println("ParsedInt " + parsedInt);
							
							if(a==-1) {
								a=parsedInt;
								//System.out.println("a = " + parsedInt);
							}
							else if(b==-1) {
								b=parsedInt;
								//System.out.println("b = " + parsedInt);
							}
							else if(c==-1) {
								c=parsedInt;
								//System.out.println("c = " + parsedInt);
							}
							else if (d==-1) { 
								//System.out.println("D = " + parsedInt);
								d=parsedInt;
								list.add(Math.round((a+b+c+d)/4));
								//System.out.println(" snit = " + Math.round((a+b+c+d)/4));
								a=-1;b=-1;c=-1;d=-1;
							}
							
														
						} else System.out.println("Tallet var udenfor intervallet og vi sætter det ikke ind!");

						// slet det forerste som vi har taget, så vi er klar til at gemme det næste tal i rækken
						bufferInput = bufferInput.substring(bufferInput.indexOf("-") + 1);
					}
					
				} // vi venter lige 40 milisekunder, da der ikke var noget i bufferen, for lige at give dne en chance..
				else Thread.sleep(40);
			
			}catch (SerialPortException ex){
				System.out.println("Serial port exception: " + ex);
			}catch (StringIndexOutOfBoundsException ex) {
				System.out.println("substring error: " + bufferInput);
			}
		}
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
			port.setParams(baudRate, 8, 1, 0);
			port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
			port.setDTR(true);
			
		}
		catch (SerialPortException ex) {
			System.out.println("Serial Port Exception: " + ex);
		} 
	}
		
}