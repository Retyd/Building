package buildings;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Создайте класс Dwelling жилого здания, основанный на массиве этажей здания.
 *Номер квартиры явно не хранится.
 *Нумерация квартир в доме сквозная и начинается с нуля.
 *Конструктор может принимать количество этажей и массив количества квартир по этажам.
 *Конструктор может принимать массив этажей дома.
 */
public class Dwelling {
    private DwellingFloor[] floors;
	public int length;

    public Dwelling (int floorsAmount, int[] flatsAmountOnFloor) {
        this.floors = new DwellingFloor[floorsAmount];

        for (int i = 0; i < floorsAmount; i++) {
            this.floors[i] = new DwellingFloor(flatsAmountOnFloor[i]);
        }
    }

    public Dwelling (DwellingFloor[] floors) {
        this.floors = floors;
    }

    /**
     * Создайте метод получения общего количества этажей дома.
     */
    public int getFloorsAmount () {
        return this.floors.length;
    }

    /**
     * Создайте метод получения общего количества квартир дома.
     */
    public int getFlatsAmount () {
        int sum = 0;

        for (int i = 0; i < floors.length; i++) {
            sum += floors[i].getFlatsAmount();
        }

        return sum;
    }

    /**
     * Создайте метод получения общей площади квартир дома.
     */
    public float getFlatsArea () {
        float sum = 0;

        for (int i = 0; i < floors.length; i++) {
            sum += floors[i].getFlatsGeneralArea();
        }

        return sum;
    }

    /**
     * Создайте метод получения общего количества комнат дома.
     */
    public int getRoomsGeneralAmount () {
        int sum = 0;

        for (int i = 0; i < floors.length; i++) {
            sum += floors[i].getRoomsGeneralAmount();
        }

        return sum;
    }

    /**
     * Создайте метод получения массива этажей жилого дома.
     */
    public DwellingFloor[] getFloors() {
        return floors;
    }

    /**
     * Создайте метод получения объекта этажа, по его номеру в доме.
     */
    public DwellingFloor getFloorByIndex (int index) {
        return floors[index];
    }

    /**
     * Создайте метод изменения этажа по его номеру в доме и ссылке на обновленный этаж.
     */
    public void setFloor (int index, DwellingFloor oneFloor) {
        this.floors[index] = oneFloor;
    }

    /**
     * Создайте метод получения объекта квартиры по ее номеру в доме.
     */
    public Flat getFlat (int index) {
        int sum = 0;

        for (int i = 0; i < floors.length; i++){
            DwellingFloor floor = floors[i];
            int flatsAmount = floor.getFlatsAmount();
            sum += flatsAmount;
            if (sum >= index) {
                return floor.getFlat(flatsAmount-(sum-index));
            }
        }
        return null;
    }

    /**
     * Создайте метод изменения объекта квартиры по ее номеру в доме и ссылке типа квартиры.
     */
    public void setFlat (int index, Flat oneFlat) {
    	int sum = 0;

        for (int i = 0; i < floors.length; i++){
            DwellingFloor floor = floors[i];
            int flatsAmount = floor.getFlatsAmount();
            sum += flatsAmount;
            if (sum >= index) {
                floor.setFlat((flatsAmount-(sum-index)), oneFlat);
            }
        }       
    }
    
    
    /**
     * Создайте метод добавления квартиры в дом по номеру квартиры в доме и ссылке на квартиру
     * (количество этажей в доме при этом не увеличиваетс¤).    
     */
    public void addFlat (int index, Flat oneFlat) {    	
    	int sum = 0;

        for (int i = 0; i < floors.length; i++){
            DwellingFloor floor = floors[i];
            int flatsAmount = floor.getFlatsAmount();
            sum += flatsAmount;
            if (sum >= index) {
                int indexOnFloor = flatsAmount-(sum-index);
            	floor.addFlat(indexOnFloor); 
                floor.setFlat(indexOnFloor, oneFlat);
            }
        }     
    }
    
    
    /**
     * Создайте метод удаления квартиры по ее номеру в доме.
     */    
    public void removeFlat (int index) {
    	int sum = 0;

        for (int i = 0; i < floors.length; i++){
            DwellingFloor floor = floors[i];
            int flatsAmount = floor.getFlatsAmount();
            sum += flatsAmount;
            if (sum >= index) {
                int indexOnFloor = flatsAmount-(sum-index);
            	floor.removeFlat(indexOnFloor);                 
            }
        }     
    }
    
    
    /**
     * Создайте метод getBestSpace() получения самой большой по площади квартиры дома.
     */
    public Flat getBestSpace () {    	
    	if (floors.length == 0) {
    		return null;
    	}    	
    	  	
    	Flat bestFlat = floors[0].getBestSpace();
    	
    	for (int i = 1; i < floors.length; i++) {
    		Flat tempFlat = getBestSpace();
    		
    		if (tempFlat.getArea() > bestFlat.getArea()) {
    			bestFlat = tempFlat;
    		}    		
    	}    
    	return bestFlat;
    }
    
    
    /**
     * Создайте метод получения отсортированного по убыванию площадей массива квартир.
     */
    public Flat[] getSortedFlats () {
    	Flat[] nonSortedFlats = new Flat[getFlatsAmount()]; 
    	int sum = 0;
    	
    	for (int i = 0; i < floors.length; i++) {    		
    		System.arraycopy(floors[i].getFlats(), 0, nonSortedFlats, sum, floors[i].getFlatsAmount());
    		sum += floors[i].getFlatsAmount();
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
    
}   

    