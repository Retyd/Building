import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import buildings.Building;
import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;
import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFloor;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {		
    	//Flat testFlat = new Flat();
    	//Office testOffice = new Office();
    	//DwellingFloor testDwellingFloor = new DwellingFloor(3);
    	//OfficeFloor testOfficeFloor = new OfficeFloor(3);
    	//HotelFloor testHotelFloor = new HotelFloor(3);
    	
    	//int[] spacesAmount = {1, 2, 3};
    	//Building testDwelling = new Dwelling(spacesAmount.length, spacesAmount);    	
    	//OfficeBuilding testOfficeBuilding = new OfficeBuilding(spacesAmount.length, spacesAmount);
    	    	
    	//сериализация в файл
    	/*ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("out.bin"));    	
		out.writeObject(testDwelling);
		out.close();*/
		
		//десериализация из файла и просмотр результатов
		/*ObjectInputStream in = new ObjectInputStream(new FileInputStream("out.bin"));
		Building dsbDwelling = (Dwelling)in.readObject();
		in.close();	*/	
		//System.out.println(dsbDwelling.getFloorsAmount() + " " + dsbDwelling.getSpacesAmount());	
		
		
		//System.out.println(testFlat.toString());		
		//System.out.println(testOffice.toString());
		//System.out.println(testDwellingFloor.toString());
		//System.out.println(testHotelFloor.toString());
		//System.out.println(testOfficeFloor.toString());
		//System.out.println(testDwelling.toString());
    	//System.out.println(testOfficeBuilding.getFloorsAmount());
    	//System.out.println(testOfficeBuilding.getFloorsArray());
		//System.out.println(testOfficeBuilding.toString());
    }
}
