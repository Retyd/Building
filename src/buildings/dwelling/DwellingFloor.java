package buildings.dwelling;

import java.io.Serializable;
import java.util.Arrays;

import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import exceptions.SpaceIndexOutOfBoundsException;

/**
 * Создайте класс DwellingFloor этажа жилого здания, основанный на массиве квартир.
 * Номер квартиры явно не хранится.
 * Нумерация квартир на этаже сквозная и начинается с нуля.
 */
public class DwellingFloor implements Floor, Serializable, Cloneable {
    protected Flat[] flats;
    
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
	
    /**
     * Добавьте в классы этажей DwellingFloor, OfficeFloor реализации метода String toString(). 
	 * Методы выводят тип этажа, текущее количество помещений этажа и соответствующую информацию по каждому помещению, используя метод toString() помещения. 
	 * Например,
     * DwellingFloor (3, Flat (3, 55.0), Flat (2, 48.0), Flat (1, 37.0))
     */
    @Override
	public String toString() {
    	StringBuilder s = new StringBuilder();
    	s.append("DwellingFloor (").append(getSpacesAmount()).append(", ");
    	for(int i = 0; i < flats.length; i++) {
    		if (i > 0 ) s.append(", ");    		
    		s.append(flats[i].toString());
    		}
    	s.append(")");
    	return s.toString();
	}

    /**
	 * Добавьте в классы этажей реализации методов boolean equals(Object object). Метод должен возвращать true только в том случае, если объект, на который передана ссылка, является этажом соответствующего типа, количество помещений совпадает и сами помещения эквивалентны помещениям текущего объекта. Для экземпляров класса HotelFloor также должно проверяться совпадение количества звёзд.
	 */
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(flats);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof DwellingFloor))
			return false;
		DwellingFloor other = (DwellingFloor) obj;
		if (!Arrays.equals(flats, other.flats))
			return false;
		return true;
	}
    
	/**
	 * Добавьте в интерфейс и классы этажей метод Object clone(). 
	 * Клонирование должно быть глубоким.
	 */
	@Override
	public Object clone() {
		Floor result = null;
		try {
			result = (Floor) super.clone();
			for(int i = 0; i < result.getSpacesAmount(); i++) {
				result.setSpace(i, (Space)result.getSpace(i).clone());
			}
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
		return result;
	}
}
