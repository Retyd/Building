import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.Semaphore;

import buildings.dwelling.DwellingFloor;
import buildings.threads.Cleaner;
import buildings.threads.Repairer;
import buildings.threads.SequentalCleaner;
import buildings.threads.SequentalRepairer;
import buildings.threads.Test;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {		
    	//Flat testFlat = new Flat();
    	//Office testOffice = new Office();
    	DwellingFloor testDwellingFloor = new DwellingFloor(9);    	
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
		
		
    	//тест toSting'ов
		//System.out.println(testFlat.toString());		
		//System.out.println(testOffice.toString());
		//System.out.println(testDwellingFloor.toString());
		//System.out.println(testHotelFloor.toString());
		//System.out.println(testOfficeFloor.toString());
		//System.out.println(testDwelling.toString());
    	//System.out.println(testOfficeBuilding.getFloorsAmount());
    	//System.out.println(testOfficeBuilding.getFloorsArray());
		//System.out.println(testOfficeBuilding.toString());
    	
    	//тест потоков
    	/*Cleaner cleaner = new Cleaner(testDwellingFloor);
    	Repairer repairer = new Repairer(testDwellingFloor);
    	cleaner.setPriority(9);
    	repairer.setPriority(2);
    	cleaner.start();    	
    	repairer.start();*/    	
    	
    	//тест синхронизованных потоков
    	Test data = new Test();
    	Thread repairer = new Thread(new SequentalRepairer(testDwellingFloor, data));
    	Thread cleaner = new Thread(new SequentalCleaner(testDwellingFloor, data));    	
    	repairer.start();
    	cleaner.start();
    }
}
