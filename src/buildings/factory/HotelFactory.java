/**
 * Создайте классы конкретных фабрик DwellingFactory, OfficeFactory,
 * HotelFactory, реализующие интерфейс BuildingFactory и
 * создающие объекты соответствующих типов.
 */
package buildings.factory;

import buildings.dwelling.Flat;
import buildings.dwelling.hotel.Hotel;
import buildings.dwelling.hotel.HotelFloor;
import buildings.interfaces.Building;
import buildings.interfaces.BuildingFactory;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;

public class HotelFactory implements BuildingFactory {
    public Space createSpace(double area) {
        return new Flat(area);
    }

    public Space createSpace(int roomsCount, double area) {
        return new Flat(roomsCount, area);
    }

    public Floor createFloor(int spacesCount) {
        return new HotelFloor(spacesCount);
    }

    public Floor createFloor(Space[] spaces) {
        return new HotelFloor((Flat[]) spaces);
    }

    public Building createBuilding(int floorsCount, int[] spacesCounts) {
        return new Hotel(floorsCount, spacesCounts);
    }

    public Building createBuilding(Floor[] floors) {
        return new Hotel((HotelFloor[]) floors);
    }
}
