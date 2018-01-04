/**
 * Реализуйте серверную часть приложения в новом классе 
 * buildings.net.server.sequental.BinaryServer, содержащем 
 * метод main(). Сервер должен выполнять последовательную 
 * обработку запросов в соответствии с созданным ранее 
 * протоколом общения клиентской и серверной части.
 * Оценка стоимости здания считается как сумма всех площадей 
 * помещений здания, умноженная на 1000$ для жилого дома, 
 * на 1500$ для офиса и на 2000$ для любой гостиницы.
 * Процедуру оценки стоимости здания следует реализовать 
 * в виде отдельного метода класса сервера.
 * При считывании данных из потока рекомендуется использовать 
 * методы класса Buildings, включая механизм настройки фабрик.
 */
package buildings.net.server.sequental;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import buildings.Buildings;
import buildings.factory.DwellingFactory;
import buildings.factory.HotelFactory;
import buildings.factory.OfficeFactory;
import buildings.interfaces.Building;

public class BinaryServer {
	private static double value(String type, Building theBuilding) {
		double result = theBuilding.getSpacesArea();		
		switch (type) {
		case "Hotel" : result *= 2000;
		case "OfficeBuilding": result *= 1500; 
		case "Dwelling" : result *= 1000;
		}
		return result;
	}
	
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket();
		Socket client = server.accept();
		
		DataInputStream in = new DataInputStream(client.getInputStream());
		DataOutputStream out = new DataOutputStream(client.getOutputStream());
		
		while(!client.isClosed()) {
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
		client.close();
	}
}
