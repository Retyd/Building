package buildings.dwelling.hotel;

import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;

/**
 * Создайте класс отеля Hotel, расширяющий класс Dwelling.
 */
public class Hotel extends Dwelling {	
	public Hotel(HotelFloor[] hotelFloors) {
		super(hotelFloors);
	}

	public Hotel(int floorsCount, int[] spacesCounts) {
		super(floorsCount, spacesCounts);
	}

	/**
	 * Класс отеля должен позволять узнать показатель «количество звезд» всего отеля, 
	 * который равен максимальному показателю «количество звезд» среди всех этажей отеля 
	 * (если этаж в здании отеля не является этажом отеля, то он при подсчётах игнорируется).
	 */
	public int getStars() {
		int result = 0;
		for(Floor floor : floors) {
			if (floor instanceof HotelFloor) {
				if (result < ((Hotel) floor).getStars()) {
					result = ((Hotel) floor).getStars();
				}
			}
		}
		return result;
	}
	
	/** Переопределите метод getBestSpace() у класса отеля. 
	 * Лучшим считается номер с максимальным значением показателя area*coeff, 
	 * где area-площадь помещения, coeff-определяется следующим образом.
	 * * - coeff = 0.5
	 * ** - coeff = 0.75
	 * *** - coeff = 1
	 * **** - coeff = 1.25
	 * ***** - coeff = 1.5
	 */
	@Override
	public Space getBestSpace() {  
		double[] coeff = {0.5, 0.75, 1, 1.25, 1.5};
		Space bestSpace = null;
		double result = 0;
		for(Floor floor : floors) {
			if (floor instanceof HotelFloor) {
					for(Space flat: floor.getSpaceArray()) {
						double score = coeff[((Hotel) floor).getStars()+1]*flat.getArea();
						if (result < score) {
							result = score;
							bestSpace = flat;
						}
				}
			}
		}
		return bestSpace;
	}
	
	/**
	 * Переопределите в классе Hotel метод String toString(). 
	 * Метод выводит значение показателя звездности здания, текущее количество этажей 
	 * и соответствующую информацию о каждом помещении каждого этажа, используя toString() уровня этажа и помещения. Например,
	 * Hotel (5, 4, HotelFloor(3, 4, Flat(...),...), HotelFloor(4, 4, Flat(...),...), HotelFloor(4, 4, Flat(...),...), HotelFloor(5, 4, Flat(...),...))
	 */
	@Override
	public String toString() {
    	StringBuilder s = new StringBuilder();
    	s.append("Hotel (").append(getStars()).append(", ").append(getFloorsAmount()).append(", ");
    	for (int i = 0; i < getFloorsAmount(); i++) {
    		if(floors[i] instanceof HotelFloor) {
    			if (i > 0 ) s.append(", ");
        		s.append(floors[i].toString());
    		}
    	}
    	s.append(")");    	
    	return s.toString();
    }
}
