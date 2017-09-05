package buildings;

/**
 * Создайте отдельный класс PlacementExchanger, работающий со ссылками типа Space, Floor, Building и содержащий следующие статические методы:
 */
public class PlacementExchanger {
    /**
     * Метод проверки возможности обмена помещениями.
     * Передаются две ссылки на объекты типа Space.
     * Метод возвращает true, если общая площадь и количество комнат в помещениях равны, и false в других случаях.
     */
    public boolean isExchangePossible(Space first, Space second){
        if(first.getArea() == second.getArea() && first.getRoomsAmount() == second.getRoomsAmount()){
            return true;
        }
        return false;
    }

    /**
     * Метод проверки возможности обмена этажами.
     * Методу передаются две ссылки на объекты типа Floor.
     * Метод возвращает true, если общая площадь этажей и количество помещений равны, и false в других случаях.
     */
    public boolean isExchangePossible(Floor first, Floor second){
        if(first.getSpacesArea() == second.getSpacesArea() && first.getSpacesAmount() == second.getSpacesAmount()){
            return true;
        }
        return false;
    }

    /**
     * Метод обмена помещениями двух этажей public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2).
     * Метод должен проверять возможность обмена помещениями и допустимость номеров помещений, выбрасывать при необходимости соответствующие исключения.
     */
    public static void exchangeFloorRooms(Floor floor1, int index1, Floor floor2, int index2){
        
    }

    /**
     * Метод обмена этажами двух зданий public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2).
     * Методу передаются две ссылки типа Building и номера соответствующих этажей.
     * Метод должен проверять возможность обмена этажами и допустимость номеров этажей, выбрасывать при необходимости соответствующие исключения.
     */
    public static void exchangeBuildingFloors(Building building1, int index1, Building building2, int index2){

    }
}
