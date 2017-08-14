package buildings;

/**
 * Создайте класс Flat квартиры жилого дома.
 *Квартира не хранит свой номер.
 *Разные квартиры могут иметь разную площадь.
 *Разные квартиры могут иметь разное количество комнат.
 *Конструктор по умолчанию создает квартиру из 2 комнат площадью 50 кв.м. (эти числа должны быть константами в классе)
 *Конструктор может принимать площадь квартиры (создается квартира с 2 комнатами).
 *Конструктор может принимать площадь квартиры и количество комнат.
 */
public class Flat {
        public static final float DEFAULT_AREA = 50;
        public static final int DEFAULT_AMOUNTROOMS = 2;

        private float area;
        private int roomsAmount;

        public Flat () {
            this(DEFAULT_AREA, DEFAULT_AMOUNTROOMS);
        }

        public Flat (float area) {
           this(area, DEFAULT_AMOUNTROOMS);
        }

        public Flat (float area, int roomsAmount) {
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
        public float getArea() {
            return area;
        }

        /**
         * Создайте метод изменения площади квартиры.
         */
        public void setArea (float area) {
            this.area = area;
        }

        /**
         * Создайте метод изменения количества комнат в квартире.
         */
        public void setRoomsAmount(int roomsAmount) {
            this.roomsAmount = roomsAmount;
        }
}
