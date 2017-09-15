package buildings.dwelling;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

import buildings.Building;
import buildings.Floor;
import buildings.Space;
import exceptions.FloorIndexOutOfBoundsException;
import exceptions.SpaceIndexOutOfBoundsException;

/**
 * Создайте класс Dwelling жилого здания, основанный на массиве этажей здания.
 * Номер квартиры явно не хранится.
 * Нумерация квартир в доме сквозная и начинается с нуля. 
 */
public class Dwelling implements Building, Serializable {
    protected DwellingFloor[] floors;
	
	/**
	 * Конструктор может принимать количество этажей и массив количества квартир по этажам.
	 */
    public Dwelling(int floorsAmount, int[] flatsAmount) {
        this.floors = new DwellingFloor[floorsAmount];

        for (int i = 0; i < floorsAmount; i++) {
            this.floors[i] = new DwellingFloor(flatsAmount[i]);
        }
    }

    /**
     * Конструктор может принимать массив этажей дома.
     */
    public Dwelling(DwellingFloor[] floors) {
        this.floors = floors;
    }

    /**
     * Создайте метод получения общего количества этажей дома.
     */
    public int getFloorsAmount() {
        return this.floors.length;
    }

    /**
     * Создайте метод получения общего количества квартир дома.
     */
    public int getSpacesAmount() {
        int sum = 0;
        for (int i = 0; i < floors.length; i++) {
            sum += floors[i].getSpacesAmount();
        }
        return sum;
    }

    /**
     * Создайте метод получения общей площади квартир дома.
     */
    public double getSpacesArea() {
        double sum = 0;
        for (int i = 0; i < floors.length; i++) {
            sum += floors[i].getSpacesArea();
        }
        return sum;
    }

    /**
     * Создайте метод получения общего количества комнат дома.
     */
    public int getRoomsAmount() {
        int sum = 0;
        for (int i = 0; i < floors.length; i++) {
            sum += floors[i].getRoomsAmount();
        }
        return sum;
    }

    /**
     * Создайте метод получения массива этажей жилого дома.
     */
    public Floor[] getFloorsArray() {
        return floors;
    }

    /**
     * Создайте метод получения объекта этажа по его номеру в доме.
     */
    public Floor getFloor(int index) {
    	if ((index >= floors.length)||(index < 0)) {
			throw new FloorIndexOutOfBoundsException();
		}
        return floors[index];
    }

    /**
     * Создайте метод изменения этажа по его номеру в доме и ссылке на обновленный этаж.
     */
    public void setFloor(int index, Floor oneFloor) {
    	if ((index >= floors.length)||(index < 0)) {
			throw new FloorIndexOutOfBoundsException();
		}
        this.floors[index] = (DwellingFloor) oneFloor;
    }

    /**
     * Создайте метод получения объекта квартиры по ее номеру в доме.
     */
    public Space getSpace(int index) {
    	if ((index >= getSpacesAmount())||(index < 0)) {
			throw new SpaceIndexOutOfBoundsException();
		}
        int sum = 0;
        for (int i = 0; i < floors.length; i++){
            DwellingFloor floor = floors[i];
            int flatsAmount = floor.getSpacesAmount();
            sum += flatsAmount;
            if (sum >= index) {
                return (Space)floor.getSpace(flatsAmount-(sum-index));
            }
        }
        return null;
    }

    /**
     * Создайте метод изменения объекта квартиры по ее номеру в доме и ссылке типа квартиры.
     */
    public void setSpace(int index, Space oneFlat) {
    	if ((index >= getSpacesAmount())||(index < 0)) {
			throw new SpaceIndexOutOfBoundsException();
		}
    	int sum = 0;
        for (int i = 0; i < floors.length; i++){
            DwellingFloor floor = floors[i];
            int flatsAmount = floor.getSpacesAmount();
            sum += flatsAmount;
            if (sum >= index) {
                floor.setSpace((flatsAmount-(sum-index)), oneFlat);
            }
        }       
    }
    
    
    /**
     * Создайте метод добавления квартиры в дом по номеру квартиры в доме и ссылке на квартиру
     * (количество этажей в доме при этом не увеличивается).    
     */
    public void addSpace(int index, Space oneFlat) {    
    	if ((index > getSpacesAmount())||(index < -1)) {
			throw new SpaceIndexOutOfBoundsException();
		}
    	int sum = 0;
        for (int i = 0; i < floors.length; i++){
            DwellingFloor floor = floors[i];
            int flatsAmount = floor.getSpacesAmount();
            sum += flatsAmount;
            if (sum >= index) {
                int indexOnFloor = flatsAmount-(sum-index);
            	floor.addSpace(indexOnFloor, oneFlat);
            }
        }     
    }
    
    
    /**
     * Создайте метод удаления квартиры по ее номеру в доме.
     */    
    public void removeSpace(int index) {
    	if ((index >= getSpacesAmount())||(index < 0)) {
			throw new SpaceIndexOutOfBoundsException();
		}
    	int sum = 0;
        for (int i = 0; i < floors.length; i++){
            DwellingFloor floor = floors[i];
            int flatsAmount = floor.getSpacesAmount();
            sum += flatsAmount;
            if (sum >= index) {
                int indexOnFloor = flatsAmount-(sum-index);
            	floor.removeSpace(indexOnFloor);                 
            }
        }     
    }
    
    
    /**
     * Создайте метод getBestSpace() получения самой большой по площади квартиры дома.
     */
    public Space getBestSpace() {    	
    	if (floors.length == 0) {
    		return null;
    	}    	  	
    	Space bestFlat = floors[0].getBestSpace();    	
    	for (int i = 1; i < floors.length; i++) {
    		Flat tempFlat = (Flat) getBestSpace();    		
    		if (tempFlat.getArea() > bestFlat.getArea()) {
    			bestFlat = tempFlat;
    		}    		
    	}    
    	return bestFlat;
    }
    
    
    /**
     * Создайте метод получения отсортированного по убыванию площадей массива квартир.
     */
    public Flat[] getSpaceArraySorted() {
    	Flat[] nonSortedFlats = new Flat[getSpacesAmount()]; 
    	int sum = 0;    	
    	for (int i = 0; i < floors.length; i++) {    		
    		System.arraycopy(floors[i].getSpaceArray(), 0, nonSortedFlats, sum, floors[i].getSpacesAmount());
    		sum += floors[i].getSpacesAmount();
    	}    	
    	Arrays.sort(nonSortedFlats, new Comparator<Flat>() {
			@Override
			public int compare(Flat arg0, Flat arg1) {
				if (arg0.getArea() == arg1.getArea()) {
					return 0;
				} else if (arg0.getArea() > arg1.getArea()) {
					return 1; 
				} else {
					return -1;
				}				
			}    		
    	});    	
    	return nonSortedFlats;
    }

	 
    
    /**
     * Добавьте в классы зданий Dwelling, OfficeBuilding реализации метода String toString(). 
	 * Методы выводят текущее количество этажей и соответствующую информацию о каждом помещении каждого этажа, 
	 * используя toString() уровня этажа и помещения. 
	 * Например, Dwelling (2, DwellingFloor (3, Flat (...), ...), DwellingFloor (3, Flat (...), ...)
     */
    @Override
	public String toString() {
    	StringBuilder s = new StringBuilder();
    	s.append("Dwelling (").append(getFloorsAmount()).append(", ");
    	for (int i = 0; i < getFloorsAmount(); i++) {
    		if (i > 0 ) s.append(", ");
    		s.append(floors[i].toString());
    	}
    	s.append(")");    	
    	return s.toString();
    }

    /**
	 * Добавьте в классы зданий реализации методов boolean equals(Object object). Метод должен возвращать true только в том случае, если объект, на который передана ссылка, является зданием соответствующего типа, количество этажей совпадает и сами этажи эквивалентны помещениям текущего объекта. 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(floors);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Dwelling))
			return false;
		Dwelling other = (Dwelling) obj;
		if (!Arrays.equals(floors, other.floors))
			return false;
		return true;
	}
    
    
}
