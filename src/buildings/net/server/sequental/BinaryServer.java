/**
 * –еализуйте серверную часть приложени€ в новом классе 
 * buildings.net.server.sequental.BinaryServer, содержащем 
 * метод main(). —ервер должен выполн€ть последовательную 
 * обработку запросов в соответствии с созданным ранее 
 * протоколом общени€ клиентской и серверной части.
 * ќценка стоимости здани€ считаетс€ как сумма всех площадей 
 * помещений здани€, умноженна€ на 1000$ дл€ жилого дома, 
 * на 1500$ дл€ офиса и на 2000$ дл€ любой гостиницы.
 * ѕроцедуру оценки стоимости здани€ следует реализовать 
 * в виде отдельного метода класса сервера.
 * ѕри считывании данных из потока рекомендуетс€ использовать 
 * методы класса Buildings, включа€ механизм настройки фабрик.
 */
package buildings.net.server.sequental;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import buildings.Buildings;
import buildings.factory.HotelFactory;
import buildings.factory.OfficeFactory;
import buildings.interfaces.Building;

public class BinaryServer {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket();
		Socket client = server.accept();
		
		DataInputStream in = new DataInputStream(client.getInputStream());
		DataOutputStream out = new DataOutputStream(client.getOutputStream());
		
		while(!client.isClosed()) {
			
			out.flush();
		}
		in.close();
		out.close();
		client.close();
		System.out.println("Connection and channels were closed");
	}
}
