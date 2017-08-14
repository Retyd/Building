/**
 * Создайте класс Office офиса офисного здания.
 * Офис не хранит свой номер.
 * Разные офисы могут иметь разные площади.
 * Разные офисы могут иметь разное количество комнат.
 * Конструктор по умолчанию создает офис из 1 комнаты площадью 250 кв.м. (константы)
 * Конструктор может принимать площадь офиса (создается офис с 1 комнатой).
 * Конструктор может принимать площадь офиса и количество комнат.
 */
public class Office {
    public static final int DEFAULT_ROOMSAMOUNT = 1;
    public static final float DEFAULT_AREA = 250;

    private int roomsAmount;
    private float area;

    public Office() {
        this(DEFAULT_ROOMSAMOUNT, DEFAULT_AREA);
    }

    public Office(float area) {
        this(DEFAULT_ROOMSAMOUNT, area);
    }

    public Office(int roomsAmount, float area){
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
        this.roomsAmount = roomsAmount;
    }

    /**
     * Создайте метод получения площади офиса.
     */
    public float getArea() {
        return area;
    }

    /**
     * Создайте метод изменения площади офиса.
     */
    public void setArea (float area) {
        this.area = area;
    }
}
