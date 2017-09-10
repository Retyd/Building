package buildings.office;
import java.io.Serializable;

import buildings.Space;
import exceptions.InvalidRoomsCountException;
import exceptions.InvalidSpaceAreaException;

/**
 * Создайте класс Office офиса офисного здания.
 * Офис не хранит свой номер.
 * Разные офисы могут иметь разные площади.
 * Разные офисы могут иметь разное количество комнат.
 */
public class Office implements Space, Serializable {
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
}
