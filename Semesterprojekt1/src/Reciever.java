import java.util.ArrayList;
import jssc.*;

public class Reciever {
	// test
	// private String yolo = "";
	
	private boolean checkFirst = true;
	private int baudRate = 38400;
	private int numberOfInputs = 600;
	private int parsedInt = 0;
	private int v1 = -1,v2 = -1,v3 = -1,v4 = -1;
	private String bufferInput = "";
	
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
					if(checkFirst){
						Thread.sleep(20);
						
						//bufferInput += port.readString();
						//bufferInput += bufferInput.substring(bufferInput.indexOf("-"));
						bufferInput = null;
						checkFirst = false;
					}
					
					// loop vi er i, så længe der er en bindestreg Strengen og antallet af elementer i vores ArrayList er under numberOfInputs
					try{
					while(bufferInput.contains("-") && list.size()<numberOfInputs){
						
						// test om der er "null" i input strengen eller om målingen ikke kom med, altså er der 2 bindestreger i rap
						if(bufferInput.substring(0, bufferInput.indexOf("-")).contains("null") || bufferInput.substring(0, bufferInput.indexOf("-")).equals("")){
							//yolo = bufferInput.substring(0, bufferInput.indexOf("-"));
							//System.out.println("hva fan der var null eller ingen" + yolo);
							bufferInput = bufferInput.substring(bufferInput.indexOf("-") + 1);
						}
						
						// parser den næste værdi i strengen og gemmer den i parsedInt
						if(!(bufferInput.substring(0)=="-")){
							try{
							parsedInt=Integer.parseInt(bufferInput.substring(0, bufferInput.indexOf("-")));
							} catch (NumberFormatException ex){
								System.out.println("NFE ved parsing" + ex);
							}
						}
						
						// hvis tallene vi lige har konverteret fra strengen er i intervallet [0:1024], så brug 4 værdier og beregn gennemsnittet
						// Vi fylder en plads i listen op med gennemsnittet af fire værdier
						if((parsedInt<1024 && parsedInt>=0)){
							
							//System.out.println("ParsedInt " + parsedInt);
							
							if(v1==-1) {
								v1=parsedInt;
								//System.out.println("a = " + parsedInt);
							}
							else if(v2==-1) {
								v2=parsedInt;
								//System.out.println("b = " + parsedInt);
							}
							else if(v3==-1) {
								v3=parsedInt;
								//System.out.println("c = " + parsedInt);
							}
							else if (v4==-1) { 
								//System.out.println("D = " + parsedInt);
								v4=parsedInt;
								list.add(Math.round((v1+v2+v3+v4)/4));
								//System.out.println(" snit = " + Math.round((a+b+c+d)/4));
								v1=-1;v2=-1;v3=-1;v4=-1;
							}
							
														
						} else ; //System.out.println("Tallet var udenfor intervallet og vi sætter det ikke ind!");

						// slet det forerste som vi har taget, så vi er klar til at gemme det næste tal i rækken
						bufferInput = bufferInput.substring(bufferInput.indexOf("-") + 1);
					}
					} catch(NullPointerException ex){
						
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
			// - 9600 er standard baud rate, som sættes til at være det samme som arduinoen kører
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