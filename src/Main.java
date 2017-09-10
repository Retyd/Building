import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import buildings.Building;
import buildings.dwelling.Dwelling;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {		
		int[] flatsAmount = {1, 2, 3};
    	Building testDwelling = new Dwelling(flatsAmount.length, flatsAmount);
    	
    	//сериализация в файл
    	ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("out.bin"));    	
		out.writeObject(testDwelling);
		out.close();
		
		//десериализация из файла и просмотр результатов
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("out.bin"));
		Building dsbDwelling = (Dwelling)in.readObject();
		in.close();		
		System.out.println(dsbDwelling.getFloorsAmount() + " " + dsbDwelling.getSpacesAmount());		
    }
}
