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
import java.util.Random;

import buildings.Buildings;
import buildings.factory.DwellingFactory;
import buildings.factory.HotelFactory;
import buildings.factory.OfficeFactory;
import buildings.interfaces.Building;
import exceptions.BuildingUnderArrestException;
import exceptions.FloorIndexOutOfBoundsException;

public class BinaryServer {
	/**
	 * Включите в начало метода оценки стоимости проверку на то, 
	 * не наложен ли на здание арест, и реализуйте в классе сервера 
	 * метод проверки ареста здания. В рамках учебного задания для простоты будем считать, 
	 * что метод проверки использует датчик случайных чисел и в среднем сообщает об аресте 
	 * здания в 10% случаев.
	 * Метод оценки стоимости в случае ареста здания должен выбросить 
	 * объявляемое исключение BuildingUnderArrestException 
	 * (также опишите класс этого исключения). 
	 * Клиенту же в таком случае вместо оценки стоимости следует отправить сообщение 
	 * об аресте здания.
	 */
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
	
	public static void main(String[] args) throws IOException, BuildingUnderArrestException {
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
	}
}
