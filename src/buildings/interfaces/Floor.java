package buildings.interfaces;

/**
 * Создайте интерфейс Floor этажа здания, работающий со ссылками типа Space. 
 * Интерфейс должен соответствовать общей функциональности DwellingFloor и OfficeFloor и должен содержать следующие  методы:
 * получения количества помещений на этаже,
 * получения общей площади помещений на этаже,
 * получения общего количества комнат в помещениях этажа,
 * получения массива всех помещений этажа,
 * получения помещения по его номеру,
 * изменения помещения по его номеру и ссылке на новое помещение,
 * вставки помещения по его номеру и ссылке на новое помещение,
 * удаления помещения по его номеру,
 * получения лучшего помещения на этаже.
 * Классы, соответственно, должны реализовывать интерфейс и работать со ссылками типа Space (с возможностью, например, добавить на жилой этаж офисное помещение).
 * Рекомендуется использовать возможности рефакторинга среды разработки.
 */
public interface Floor {
	public int getSpacesAmount();
	public double getSpacesArea();
	public int getRoomsAmount();
	public Space[] getSpaceArray();
	public Space getSpace(int index);
	public void setSpace(int index, Space space);
	public void addSpace(int index, Space space);
	public void removeSpace(int index);
	public Space getBestSpace();
}
