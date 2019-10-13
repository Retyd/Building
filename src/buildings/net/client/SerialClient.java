package buildings.net.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.net.Socket;
import java.util.Scanner;

import buildings.Buildings;
import buildings.factory.DwellingFactory;
import buildings.factory.HotelFactory;
import buildings.factory.OfficeFactory;
import buildings.interfaces.Building;

/**
 * Создайте новые классы buildings.net.client.SerialClient,
 * buildings.net.server.sequental.SerialServer и buildings.net.server.parallel.SerialServer,
 * решающие ту же задачу, но отличающиеся по протоколу взаимодействия:
 * для передачи данных следует использовать сериализацию.
 * Данные о здании передаются в виде объекта, исключение передаётся
 * клиенту в виде объекта и т.д.
 */

public class SerialClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        File buildingType = new File(args[0]);
        Scanner type = new Scanner(buildingType);

        File buildingInfo = new File(args[1]);
        Reader in = new FileReader(buildingInfo);

        File buildingCosts = new File(args[2]);
        FileOutputStream fos = new FileOutputStream(buildingCosts);
        PrintStream writeCostInFile = new PrintStream(fos);

        Socket socket = new Socket();
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(new DataOutputStream(socket.getOutputStream()));

        System.out.println("Client connected to socket.");
        System.out.println("Client writing channel & reading channel initialized.");


        while (type.hasNext() && !socket.isOutputShutdown()) {
            System.out.println("Client start reading info about some building...");
            String t = new String(type.next());
            out.writeUTF(t);
            switch (t) {
                case "Hotel":
                    Buildings.setBuildingFactory(new HotelFactory());
                case "OfficeBuilding":
                    Buildings.setBuildingFactory(new OfficeFactory());
                case "Dwelling":
                    Buildings.setBuildingFactory(new DwellingFactory());
            }
            Building building = Buildings.readBuilding(in);
            out.writeObject(building);
            out.flush();
            System.out.println("Client sent message to server.");
            Thread.sleep(1000);
            writeCostInFile.println((dis.read()));
            System.out.println("Client read message from server and wrote it in the file.");
        }

        writeCostInFile.close();
        dis.close();
        out.close();
        in.close();
        System.out.println("Closing connections & channels on clentSide - DONE.");
    }
}
