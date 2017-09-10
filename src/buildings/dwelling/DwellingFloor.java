package buildings.dwelling;

import java.io.Serializable;

import buildings.Floor;
import buildings.Space;
import exceptions.SpaceIndexOutOfBoundsException;

/**
 * Создайте класс DwellingFloor этажа жилого здания, основанный на массиве квартир.
 * Номер квартиры явно не хранится.
 * Нумерация квартир на этаже сквозная и начинается с нуля.
 */
public class DwellingFloor implements Floor, Serializable {
    private Flat[] flats;
    
    /**
     * Конструктор может принимать количество квартир на этаже.
     */
    public DwellingFloor(int flatsAmount) {
    	this.flats = new Flat[flatsAmount];
    	for (int i = 0; i < flatsAmount; i++) {
    		flats[i] = new Flat();
    	}
    }

    /**
     * Конструктор может принимать массив квартир этажа.
     */
    public DwellingFloor(Flat[] flats) {
        this.flats = flats;
    }

    /**
     * Создайте метод получения количества квартир на этаже.
     */
    public int getSpacesAmount() {
        return flats.length;
    }

    /**
     * Создайте метод получения общей площади квартир этажа.
     */
    public double getSpacesArea() {
        double result = 0;
        for (int i = 0; i < flats.length; i++){
            result += flats[i].getArea();
        }
        return result;
    }

    /**
     * Создайте метод получения общего количества комнат этажа.
     */
    public int getRoomsAmount() {
        int result = 0;
        for (int i = 0; i < flats.length; i++) {
            result += flats[i].getRoomsAmount();
        }
        return result;
    }

    /**
     * Создайте метод получения массива квартир этажа.
     */
    public Space[] getSpaceArray() {
        return flats;
    }

    /**
     * Создайте метод получения объекта квартиры, по ее номеру на этаже.
     */
    public Space getSpace(int index) {
    	if ((index >= flats.length)||(index < 0)) {
    		throw new SpaceIndexOutOfBoundsException();
    	}
        return flats[index];
    }

    /**
     * Создайте метод изменения квартиры по ее номеру на этаже и ссылке на новую квартиру.
     */
    public void setSpace(int index, Space oneFlat) {
    	if ((index >= flats.length)||(index < 0)) {
			throw new SpaceIndexOutOfBoundsException();
		}
        this.flats[index] = (Flat) oneFlat;
    }

    /**
     * Создайте метод добавления новой квартиры на этаже по будущему номеру квартиры.
     */
    public void addSpace(int index, Space oneFlat) {
    	if ((index >= flats.length)||(index < 0)) {
			throw new SpaceIndexOutOfBoundsException();
		}
        Flat[] newFlats = new Flat[flats.length+1];
        for (int i = 0; i < flats.length; i++) {
            newFlats[i] = flats[i];
        }
        for (int i = newFlats.length; i >= index; i--) {
            newFlats[i] = newFlats[i-1];
        }
        newFlats[index] = (Flat) oneFlat;
        flats = newFlats;
    }

    /**
     * Создайте метод удаления квартиры по ее номеру на этаже.
     */
    public void removeSpace(int index) {
    	if ((index >= flats.length)||(index < 0)) {
			throw new SpaceIndexOutOfBoundsException();
		}
        Flat[] newFlats = new Flat[flats.length-1];
        for (int i = 0; i < index; i++) {
            newFlats[i] = flats[i];
        }
        for (int i = index+1; i < flats.length; i++) {
            newFlats[i-1] = flats[i];
        }
        flats = newFlats;
    }

    /**
     * Создайте метод getBestSpace() получения самой большой по площади квартиры этажа.
     */
    public Space getBestSpace() {
        double bestSpace = 0;
        Flat flatBestSpace = null;
        for (int i = 0; i < flats.length; i++) {
            if (flats[i].getArea() > bestSpace) {
                bestSpace = flats[i].getArea();
                flatBestSpace = flats[i];
            }
        }

        return flatBestSpace;
    }
}
