package buildings;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.io.Writer;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;

public class Buildings {
	 /**       
       * Записанные данные о здании представляет собой последовательность чисел, первым из которых является количество этажей, далее следует количество помещений текущего этажа и соответствующие значения количества комнат и площадей помещений текущего этажа.
       * Например, символьная запись для трехэтажного здания: 
       * «3 2 3 150.0 2 100.0 1 3 250.0 3 2 140.0 1 60.0 1 50.0»
       * Для чтения этажа из символьного потока рекомендуется использовать StreamTokenizer.
       * 
       * Создайте отдельный класс Buildings, работающий со ссылками типа Space, Floor, Building, содержащий статические методы ввода-вывода зданий.
       * записи данных о здании в байтовый поток 
       * public static void outputBuilding (Building building, OutputStream out);
	   */
	public static void outputBuilding (Building building, OutputStream out) throws IOException {
		DataOutputStream dos = new DataOutputStream(out);
		Floor floor = null;
		Space space = null;		
		dos.writeInt(building.getFloorsAmount());		
		for(int i = 0, floorsAmount = building.getFloorsAmount(); i < floorsAmount; i++) {
			floor = building.getFloor(i);
			dos.writeInt(floor.getSpacesAmount());
			for (int j = 0, spacesAmount = floor.getSpacesAmount(); j < spacesAmount; j++) {
				space = floor.getSpace(j);
				dos.writeInt(space.getRoomsAmount());
				dos.writeDouble(space.getArea());
			}
		}
        dos.close();
    }
	
      /**чтения данных о здании из байтового потока 
       * public static Building inputBuilding (InputStream in);
	   */
	public static Building inputBuilding (InputStream in) throws IOException {		
		DataInputStream dis = new DataInputStream(in);
		DwellingFloor [] floors = new DwellingFloor[dis.readInt()];
		for(int i = 0, sizeFloors = floors.length; i < sizeFloors; i++) {
			Flat[] flats = new Flat[dis.readInt()];
			for (int j = 0, sizeFlats = flats.length; j < sizeFlats; j++) {				
				flats[j] = new Flat(dis.readDouble(), dis.readInt());
			}
			floors[i] = new DwellingFloor(flats);
		}
		dis.close();
		Dwelling building = new Dwelling(floors);
		return (Building)building;
	}
	  
      /** записи здания в символьный поток 
       * public static void writeBuilding (Building building, Writer out);
	   */
		public static void writeBuilding (Building building, Writer out) {
			PrintWriter pwo = new PrintWriter(out);
			Floor floor = null;
			Space space = null;		
			pwo.print(building.getFloorsAmount() + " ");		
			for(int i = 0, floorsAmount = building.getFloorsAmount(); i < floorsAmount; i++) {
				floor = building.getFloor(i);
				pwo.print(floor.getSpacesAmount() + " ");
				for (int j = 0, spacesAmount = floor.getSpacesAmount(); j < spacesAmount; j++) {
					space = floor.getSpace(j);
					pwo.print(space.getRoomsAmount() + " ");
					pwo.print(space.getArea() + " ");
				}
			}
	        pwo.close();
		}
	  
      /** чтения здания из символьного потока 
       * public static Building readBuilding (Reader in).
	   */
		public static Building readBuilding (Reader in) throws IOException {
			StreamTokenizer st = new StreamTokenizer(in);
			DwellingFloor [] floors = new DwellingFloor[(int)st.nextToken()];
			for(int i = 0, sizeFloors = floors.length; i < sizeFloors; i++) {
				Flat[] flats = new Flat[(int)st.nextToken()];
				for (int j = 0, sizeFlats = flats.length; j < sizeFlats; j++) {				
					flats[j] = new Flat((double)st.nextToken(), (int)st.nextToken());
				}
				floors[i] = new DwellingFloor(flats);
			}
			Dwelling building = new Dwelling(floors);
			return (Building)building;
		}	  
}
/**
 * Модифицировать классы помещений, этажей и зданий таким образом, чтобы они были сериализуемыми.
 * Продемонстрировать возможности сериализации в методе main(), записав в файл объект, затем считав и сравнив его с исходным (по сохраненным значениям).
 */