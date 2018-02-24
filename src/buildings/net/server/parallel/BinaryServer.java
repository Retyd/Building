package buildings.net.server.parallel;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import buildings.Buildings;
import buildings.factory.DwellingFactory;
import buildings.factory.HotelFactory;
import buildings.factory.OfficeFactory;
import buildings.interfaces.Building;
import exceptions.BuildingUnderArrestException;

/**
 * Реализуйте серверную часть приложения в рамках 
 * модели параллельной обработки запросов.
 */
public class BinaryServer {
    static ExecutorService executeIt = Executors.newFixedThreadPool(2);
    
    private static class Server implements Runnable{
    	private Socket clientSocket;
    	
		public Server(Socket client) {
			this.clientSocket = client;
		}
		
		@Override
	    public void run() {
			try {
				DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
				DataInputStream in = new DataInputStream(clientSocket.getInputStream());
		        while (!clientSocket.isClosed()) {
		        	String t = new String(in.readUTF()); 
					switch (t) {
						case "Hotel" : Buildings.setBuildingFactory(new HotelFactory());
						case "OfficeBuilding": Buildings.setBuildingFactory(new OfficeFactory()); 
						case "Dwelling" : Buildings.setBuildingFactory(new DwellingFactory());
					}
					Building theBuilding = Buildings.inputBuilding(in);		
					out.writeDouble(value(t, theBuilding));			
					out.flush();
		        }
	
		        in.close();
		        out.close();
		        clientSocket.close();
		    } catch (IOException | BuildingUnderArrestException e) {
	            e.printStackTrace();
	        }
		}
    }
    
    private static boolean isArrested() {
		Random random = new Random();
		int res = random.nextInt(101);
		if(res > 10) return true;
		return false;
	}
    
    private static double value(String type, Building theBuilding) throws BuildingUnderArrestException {
		if (isArrested()) {
			throw new BuildingUnderArrestException("The building is under arrest");
		}
		
		double multiplier = 0;		
		switch (type) {
		case "Hotel" : multiplier = 2000;
		case "OfficeBuilding": multiplier = 1500; 
		case "Dwelling" : multiplier = 1000;
		}
		return theBuilding.getSpacesArea() * multiplier;
	}
    
    public static void main(String[] args) throws IOException {
    	ServerSocket server = new ServerSocket();
    	while (!server.isClosed()) {
    		Socket client = server.accept();
    		executeIt.execute(new Server(client));                
    	}
            executeIt.shutdown();
    }
}