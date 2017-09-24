package buildings;

import buildings.interfaces.Building;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import exceptions.FloorIndexOutOfBoundsException;
import exceptions.InexchangeableFloorsException;
import exceptions.InexchangeableSpacesException;
import exceptions.SpaceIndexOutOfBoundsException;

/**
 * Создайте отдельный класс PlacementExchanger, работающий со ссылками типа Space, Floor, Building и содержащий следующие статические методы:
 */
public class PlacementExchanger {
    /**
     * Метод проверки возможности обмена помещениями.
     * Передаются две ссылки на объекты типа Space.
     * Метод возвращает true, если общая площадь и количество комнат в помещениях равны, и false в других случаях.
     */
    public static boolean isExchangePossible(Space first, Space second){
        return first.getArea() == second.getArea() && first.getRoomsAmount() == second.getRoomsAmount();
    }

    /**
     * Метод проверки возможности обмена этажами.
     * Методу передаются две ссылки на объекты типа Floor.
     * Метод возвращает true, если общая площадь этажей и количество помещений равны, и false в других случаях.
     */
    public static boolean isExchangePossible(Floor first, Floor second){
        return first.getSpacesArea() == second.getSpacesArea() && first.getSpacesAmount() == second.getSpacesAmount();
    }

    /**
     * Метод обмена помещениями двух этажей public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2).
     * Метод должен проверять возможность обмена помещениями и допустимость номеров помещений, выбрасывать при необходимости соответствующие исключения.
     */
    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2) throws InexchangeableSpacesException{
        if (!isExchangePossible(floor1.getSpace(index1), floor2.getSpace(index2))) {
        	throw new InexchangeableSpacesException();
        } else if ((index1 >= floor1.getSpacesAmount() || index1 < 0) || (index2 >= floor2.getSpacesAmount() || index2 < 0)) {
        	throw new SpaceIndexOutOfBoundsException();
        } else {
        	Space temp = floor1.getSpace(index1);
        	floor1.setSpace(index1, floor2.getSpace(index2));
        	floor2.setSpace(index2, temp);
        }
    }

    /**
     * Метод обмена этажами двух зданий public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2).
     * Методу передаются две ссылки типа Building и номера соответствующих этажей.
     * Метод должен проверять возможность обмена этажами и допустимость номеров этажей, выбрасывать при необходимости соответствующие исключения.
     */
    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2) throws InexchangeableFloorsException{
    	if (!isExchangePossible(building1.getFloor(index1), building2.getFloor(index2))) {
        	throw new InexchangeableFloorsException();
        } else if ((index1 >= building1.getFloorsAmount() || index1 < 0) || (index2 >= building2.getFloorsAmount() || index2 < 0)) {
        	throw new FloorIndexOutOfBoundsException();
        } else {
        	Floor temp = building1.getFloor(index1);
        	building1.setFloor(index1, building2.getFloor(index2));
        	building2.setFloor(index2, temp);
        }
    }
}
