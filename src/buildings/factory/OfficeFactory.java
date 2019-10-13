/**
 * Создайте классы конкретных фабрик DwellingFactory, OfficeFactory,
 * HotelFactory, реализующие интерфейс BuildingFactory и
 * создающие объекты соответствующих типов.
 */
package buildings.factory;

import buildings.interfaces.Building;
import buildings.interfaces.BuildingFactory;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import buildings.office.Office;
import buildings.office.OfficeBuilding;
import buildings.office.OfficeFloor;

public class OfficeFactory implements BuildingFactory {
    public Space createSpace(double area) {
        return new Office(area);
    }

    public Space createSpace(int roomsCount, double area) {
        return new Office(roomsCount, area);
    }

    public Floor createFloor(int spacesCount) {
        return new OfficeFloor(spacesCount);
    }

    public Floor createFloor(Space[] spaces) {
        return new OfficeFloor((Office[]) spaces);
    }

    public Building createBuilding(int floorsCount, int[] spacesCounts) {
        return new OfficeBuilding(floorsCount, spacesCounts);
    }

    public Building createBuilding(Floor[] floors) {
        return new OfficeBuilding((OfficeFloor[]) floors);
    }
}
