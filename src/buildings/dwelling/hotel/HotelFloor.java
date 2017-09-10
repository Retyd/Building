package buildings.dwelling.hotel;

import buildings.Space;
import buildings.dwelling.DwellingFloor;
import buildings.dwelling.Flat;

/**
 * Создайте класс HotelFloor, расширяющий класс DwellingFloor.
 */
public class HotelFloor extends DwellingFloor{
	/**
	 * Каждый этаж отеля имеет показатель «количество звезд».
	 */
	public static final int DEFAULT_STARS = 1;
	
	private int stars;
	
	/**
	 * Этаж отеля можно создать как по количеству помещений этажа, так и по массиву помещений. 
	 * Количество звезд этажа при создании объекта должно браться из константы в классе, равной 1.
	 */	
	public HotelFloor(int spacesAmount) {
		super(spacesAmount);
		this.stars = DEFAULT_STARS;		
	}
	
	public HotelFloor(Flat[] spaces) {
		super(spaces);
		this.stars = DEFAULT_STARS;		
	}
	
	/**
	 * У объекта этажа отеля должны быть методы получения и изменения его количества звезд.
	 */
	public int getStars() {
		return stars;
	}
	
	public void setStars(int stars) {
		this.stars = stars;
	}
}
