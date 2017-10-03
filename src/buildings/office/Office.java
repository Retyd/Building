package buildings.office;
import java.io.Serializable;

import buildings.interfaces.Space;
import exceptions.InvalidRoomsCountException;
import exceptions.InvalidSpaceAreaException;

/**
 * Создайте класс Office офиса офисного здания.
 * Офис не хранит свой номер.
 * Разные офисы могут иметь разные площади.
 * Разные офисы могут иметь разное количество комнат.
 */
public class Office implements Space, Serializable, Cloneable {
    public static final int DEFAULT_ROOMSAMOUNT = 1;
    public static final double DEFAULT_AREA = 250;

    private int roomsAmount;
    private double area;

    /**
     * Конструктор по умолчанию создает офис из 1 комнаты площадью 250 кв.м. (константы).
     */
    public Office() {
        this(DEFAULT_ROOMSAMOUNT, DEFAULT_AREA);
    }

    /**
     * Конструктор может принимать площадь офиса (создается офис с 1 комнатой).
     */
    public Office(double area) {    	
        this(DEFAULT_ROOMSAMOUNT, area);
    }

    /**
     * Конструктор может принимать площадь офиса и количество комнат.
     */
    public Office(int roomsAmount, double area){
    	if (area <= 0) {
    		throw new InvalidSpaceAreaException();
    	}
    	if (roomsAmount <= 0) {
    		throw new InvalidRoomsCountException();
    	}
        this.roomsAmount = roomsAmount;
        this.area = area;
    }

    /**
     * Создайте метод получения количества комнат в офисе.
     */
    public int getRoomsAmount() {
        return roomsAmount;
    }

    /**
     * Создайте метод изменения количества комнат в офисе.
     */
    public void setRoomsAmount(int roomsAmount) {
    	if (roomsAmount <= 0) {
    		throw new InvalidRoomsCountException();
    	}
        this.roomsAmount = roomsAmount;
    }

    /**
     * Создайте метод получения площади офиса.
     */
    public double getArea() {
        return area;
    }

    /**
     * Создайте метод изменения площади офиса.
     */
    public void setArea(double area) {
    	if (area <= 0) {
    		throw new InvalidSpaceAreaException();
    	}
        this.area = area;
    }
    /**
     * Добавьте в классы помещений Flat и Office реализации метода String toString(), 
	 * выводящего тип помещения, текущее количество комнат помещения и его площадь через запятую в скобках. 
	 * Например, Flat (3, 55.0) 
     */

	@Override
	public String toString() {
		return "Office (" + roomsAmount + ", " + area + ")";
	}

	/**
     * Добавьте в классы помещений реализации методов boolean equals(Object object). Метод должен возвращать true только в том случае, если объект, на который передана ссылка, является помещением соответствующего типа и все соответствующие параметры помещений равны.
     * Добавьте в классы помещений реализации методов int hashCode(). Значение хеш-функции помещения можно вычислить как значение последовательного побитового исключающего ИЛИ битового представления количества комнат помещения, и, например, первых 4 байтов и вторых 4-х байтов (для типа double) битовых представлений площадей помещений этажа (следует воспользоваться вспомогательными методами классов-оберток).
     */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(area);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + roomsAmount;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Office))
			return false;
		Office other = (Office) obj;
		if (Double.doubleToLongBits(area) != Double.doubleToLongBits(other.area))
			return false;
		if (roomsAmount != other.roomsAmount)
			return false;
		return true;
	}
	
	/**
	 * Добавьте в интерфейс и классы помещений метод Object clone().
	 * Клонирование должно быть глубоким.
	 */
	@Override
	public Object clone() {
		Object result = null;
		try {
			result = super.clone();
		} catch (CloneNotSupportedException e) {}
		return result;
	}
}
