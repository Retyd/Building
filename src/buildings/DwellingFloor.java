package buildings;

/**
 * Создайте класс DwellingFloor этажа жилого здания, основанный на массиве квартир.
 *Номер квартиры явно не хранится.
 *Нумерация квартир на этаже сквозная и начинается с нуля.
 *Конструктор может принимать количество квартир на этаже.
 *Конструктор может принимать массив квартир этажа.
 */
public class DwellingFloor {
    private Flat[] flats;

    public DwellingFloor(int flatsAmount) {
    	this.flats = new Flat[flatsAmount];
    	for (int i = 0; i < flatsAmount; i++) {
    		flats[i] = new Flat();
    	}
    }

    public DwellingFloor(Flat[] flats) {
        this.flats = flats;
    }

    /**
     * Создайте метод получения количества квартир на этаже.
     */
    public int getFlatsAmount() {
        return flats.length;
    }

    /**
     * Создайте метод получения общей площади квартир этажа.
     */
    public float getFlatsGeneralArea () {
        float sumAreas = 0;
        for (int i = 0; i < flats.length; i++){
            sumAreas += flats[i].getArea();
        }
        return sumAreas;
    }

    /**
     * Создайте метод получения общего количества комнат этажа.
     */
    public int getRoomsGeneralAmount () {
        int sumRooms = 0;
        for (int i = 0; i < flats.length; i++) {
            sumRooms += flats[i].getRoomsAmount();
        }
        return sumRooms;
    }

    /**
     * Создайте метод получения массива квартир этажа.
     */
    public Flat[] getFlats () {
        return flats;
    }

    /**
     * Создайте метод получени¤ объекта квартиры, по ее номеру на этаже.
     */
    public Flat getFlat (int flatIndex) {
        return flats[flatIndex];
    }

    /**
     * Создайте метод изменения квартиры по ее номеру на этаже и ссылке на новую квартиру.
     */
    public void setFlat (int flatIndex, Flat oneFlat) {
        this.flats[flatIndex] = oneFlat;
    }

    /**
     * Создайте метод добавления новой квартиры на этаже по будущему номеру квартиры.
     */
    public void addFlat (int futureFlatIndex) {
        Flat[] newFlats = new Flat[flats.length+1];

        for (int i = 0; i < flats.length; i++) {
            newFlats[i] = flats[i];
        }

        for (int i = newFlats.length; i >= futureFlatIndex; i--) {
            newFlats[i] = newFlats[i-1];
        }

        newFlats[futureFlatIndex] = new Flat();

        flats = newFlats;
    }

    /**
     * Создайте метод удаления квартиры по ее номеру на этаже.
     */
    public void removeFlat (int flatIndex) {
        Flat[] newFlats = new Flat[flats.length-1];

        for (int i = 0; i < flatIndex; i++) {
            newFlats[i] = flats[i];
        }

        for (int i = flatIndex+1; i < flats.length; i++) {
            newFlats[i-1] = flats[i];
        }

        flats = newFlats;
    }

    /**
     * Создайте метод getBestSpace() получения самой большой по площади квартиры этажа.
     */
    public Flat getBestSpace () {
        float bestSpace = 0;
        Flat flatBestSpace = null;

        for (int i = 0; i < flats.length; i++) {
            if (flats[i].getArea() > bestSpace) {
                bestSpace = flats[i].getArea();
                flatBestSpace = flats[i];
            }
        }

        return flatBestSpace;
    }
}
