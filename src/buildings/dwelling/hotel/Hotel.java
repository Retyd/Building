package buildings.dwelling.hotel;

import buildings.Floor;
import buildings.Space;
import buildings.dwelling.Dwelling;
import buildings.dwelling.DwellingFloor;

/**
 * Создайте класс отеля Hotel, расширяющий класс Dwelling.
 */
public class Hotel extends Dwelling{
	public Hotel(DwellingFloor[] floors) {
		super(floors);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Класс отеля должен позволять узнать показатель «количество звезд» всего отеля, который равен максимальному показателю «количество звезд» среди всех этажей отеля (если этаж в здании отеля не является этажом отеля, то он при подсчётах игнорируется).
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
	
	/** Переопределите метод getBestSpace() у класса отеля. Лучшим считается номер с максимальным значением показателя area*coeff, где area-площадь помещения, coeff-определяется следующим образом.
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
						double score = coeff[((Hotel) floor).getStars()]*flat.getArea();
						if (result < score) {
							result = score;
							bestSpace = flat;
						}
				}
			}
		}
		return bestSpace;
	}
}
