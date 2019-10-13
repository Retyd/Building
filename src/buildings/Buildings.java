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

import buildings.factory.DwellingFactory;
import buildings.interfaces.Building;
import buildings.interfaces.BuildingFactory;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;

public class Buildings {
    /**
     * В классе Buildings создайте статическое поле типа BuildingFactory
     * и метод setBuildingFactory, позволяющие, соответственно, хранить
     * ссылку и устанавливать ссылку на текущую конкретную фабрику.
     * По умолчанию поле должно ссылаться на объект, порождающий экземпляры
     * класса Dwelling.
     * В классе Buildings реализуйте статические методы, которые
     * с помощью текущей фабрики создают новые экземпляры
     * соответствующих объектов. В остальных методах класса Buildings
     * замените прямое создание экземпляров объектов на вызов методов
     * фабрики.
     */
    public static BuildingFactory buildingFactory = new DwellingFactory();

    public static void setBuildingFactory(BuildingFactory buildingFactory) {
        Buildings.buildingFactory = buildingFactory;
    }

    public Space createSpace(double area) {
        return buildingFactory.createSpace(area);
    }

    public Space createSpace(int roomsCount, double area) {
        return buildingFactory.createSpace(roomsCount, area);
    }

    public Floor createFloor(int spacesCount) {
        return buildingFactory.createFloor(spacesCount);
    }

    public Floor createFloor(Space[] spaces) {
        return buildingFactory.createFloor(spaces);
    }

    public Building createBuilding(int floorsCount, int[] spacesCounts) {
        return buildingFactory.createBuilding(floorsCount, spacesCounts);
    }

    public Building createBuilding(Floor[] floors) {
        return buildingFactory.createBuilding(floors);
    }

    /**
     * Записанные данные о здании представляет собой последовательность чисел, первым из которых является количество этажей,
     * далее следует количество помещений текущего этажа и соответствующие значения количества комнат и площадей помещений текущего этажа.
     * Например, символьная запись для трехэтажного здания:
     * «3 2 3 150.0 2 100.0 1 3 250.0 3 2 140.0 1 60.0 1 50.0»
     * Для чтения этажа из символьного потока рекомендуется использовать StreamTokenizer.
     * <p>
     * Создайте отдельный класс Buildings, работающий со ссылками типа Space, Floor, Building, содержащий статические методы ввода-вывода зданий.
     * записи данных о здании в байтовый поток
     * public static void outputBuilding (Building building, OutputStream out);
     */
    public static void outputBuilding(Building building, OutputStream out) throws IOException {
        DataOutputStream dos = new DataOutputStream(out);
        Floor floor = null;
        Space space = null;
        dos.writeInt(building.getFloorsAmount());
        for (int i = 0, floorsAmount = building.getFloorsAmount(); i < floorsAmount; i++) {
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

    /**
     * чтения данных о здании из байтового потока
     * public static Building inputBuilding (InputStream in);
     */
    public static Building inputBuilding(InputStream in) throws IOException {
        DataInputStream dis = new DataInputStream(in);
        Floor[] floors = new Floor[dis.readInt()];
        for (int i = 0, sizeFloors = floors.length; i < sizeFloors; i++) {
            Space[] flats = new Space[dis.readInt()];
            for (int j = 0, sizeFlats = flats.length; j < sizeFlats; j++) {
                flats[j] = buildingFactory.createSpace(dis.readInt(), dis.readDouble());
            }
            floors[i] = buildingFactory.createFloor(flats);
        }
        dis.close();
        return buildingFactory.createBuilding(floors);
    }

    /**
     * записи здания в символьный поток
     * public static void writeBuilding (Building building, Writer out);
     */
    public static void writeBuilding(Building building, Writer out) {
        PrintWriter pwo = new PrintWriter(out);
        Floor floor = null;
        Space space = null;
        pwo.print(building.getFloorsAmount() + " ");
        for (int i = 0, floorsAmount = building.getFloorsAmount(); i < floorsAmount; i++) {
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

    /**
     * чтения здания из символьного потока
     * public static Building readBuilding (Reader in).
     */
    public static Building readBuilding(Reader in) throws IOException {
        StreamTokenizer st = new StreamTokenizer(in);
        Floor[] floors = new Floor[(int) st.nextToken()];
        for (int i = 0, sizeFloors = floors.length; i < sizeFloors; i++) {
            Space[] flats = new Space[(int) st.nextToken()];
            for (int j = 0, sizeFlats = flats.length; j < sizeFlats; j++) {
                flats[j] = buildingFactory.createSpace((int) st.nextToken(), (double) st.nextToken());
            }
            floors[i] = buildingFactory.createFloor(flats);
        }
        return buildingFactory.createBuilding(floors);
    }
}
/**
 * Модифицировать классы помещений, этажей и зданий таким образом, чтобы они были сериализуемыми.
 * Продемонстрировать возможности сериализации в методе main(), записав в файл объект, затем считав и сравнив его с исходным (по сохраненным значениям).
 */