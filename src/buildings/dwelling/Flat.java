package buildings.dwelling;

import java.io.Serializable;

import buildings.Space;
import exceptions.InvalidRoomsCountException;
import exceptions.InvalidSpaceAreaException;

/**
 * Создайте класс Flat квартиры жилого дома.
 * Квартира не хранит свой номер.
 * Разные квартиры могут иметь разную площадь.
 * Разные квартиры могут иметь разное количество комнат.
 */
public class Flat implements Space, Serializable {
        public static final float DEFAULT_AREA = 50;
        public static final int DEFAULT_AMOUNTROOMS = 2;

        private double area;
        private int roomsAmount;

        /**
         * Конструктор по умолчанию создает квартиру из 2 комнат площадью 50 кв.м. (эти числа должны быть константами в классе)
         */
        public Flat () {
            this(DEFAULT_AREA, DEFAULT_AMOUNTROOMS);
        }

        /**
         * Конструктор может принимать площадь квартиры (создается квартира с 2 комнатами).
         */
        public Flat (double area) {
           this(area, DEFAULT_AMOUNTROOMS);
        }
        
        /**
         * Конструктор может принимать площадь квартиры и количество комнат.
         */
        public Flat (double area, int roomsAmount) {
        	if (area <= 0) {
        		throw new InvalidSpaceAreaException();
        	}
        	if (roomsAmount <= 0) {
        		throw new InvalidRoomsCountException();
        	}
            this.area = area;
            this.roomsAmount = roomsAmount;
        }

        /**
         * Создайте метод получения количества комнат в квартире.
         */
        public int getRoomsAmount() {
            return roomsAmount;
        }

        /**
         * Создайте метод получения площади квартиры.
         */
        public double getArea() {
            return area;
        }

        /**
         * Создайте метод изменения площади квартиры.
         */
        public void setArea (double area) {
        	if (area <= 0) {
        		throw new InvalidSpaceAreaException();
        	}
            this.area = area;
        }

        /**
         * Создайте метод изменения количества комнат в квартире.
         */
        public void setRoomsAmount(int roomsAmount) {
        	if (roomsAmount <= 0) {
        		throw new InvalidRoomsCountException();
        	}
            this.roomsAmount = roomsAmount;
        }
}
