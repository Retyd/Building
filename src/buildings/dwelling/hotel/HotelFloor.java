package buildings.dwelling.hotel;

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
	
	/**
	 * Переопределите в классе HotelFloor метод String toString(). 
	 * Метод выводит тип этажа, значение показателя звездности этажа, текущее количество помещений этажа и соответствующую информацию по каждому помещению. 
	 * Например, HotelFloor (5, 4, Flat (3, 55.0), Flat (2, 48.0), Flat (1, 37.0), Flat (2, 48.0))
	 */
	@Override
	public String toString() {
    	StringBuilder s = new StringBuilder();
    	s.append("HotelFloor (").append(getStars()).append(", ").append(getSpacesAmount()).append(", ");
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
		int result = super.hashCode();
		result = prime * result + stars;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof HotelFloor))
			return false;
		HotelFloor other = (HotelFloor) obj;
		if (stars != other.stars)
			return false;
		return true;
	}  
	
	
}
