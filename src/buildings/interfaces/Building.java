package buildings.interfaces;

/**
 * Создайте интерфейс Building здания, работающий со ссылками типа Floor и Space.
 * Интерфейс соответствует общей функциональности Dwelling и OfficeBuilding и должен содержать следующие методы:
 * получения количества этажей в здании,
 * получения количества помещений в здании,
 * получения общей площади всех помещений здания,
 * получения общего количества комнат в помещениях здания,
 * получения массива этажей здания,
 * получения этажа здания по его номеру,
 * изменения этажа по его номеру и ссылке на новый этаж,
 * получения помещения по его номеру в здании,
 * изменения помещения в здании по номеру и ссылке на новое помещение,
 * вставке помещения в здании по будущему номеру и ссылке на новое помещение,
 * удаления помещения из здания,
 * получения лучшего помещения в здании,
 * получения отсортированного массива всех помещений.
 * Классы зданий, соответственно, должны реализовывать интерфейс и работать со ссылками типа Space и Floor 
 * (с возможностью, например, заменить в офисном здании этаж на жилой).
 * Рекомендуется использовать возможности рефакторинга среды разработки.
 * Добавьте в интерфейс и классы помещений метод Object clone(). 
 * Клонирование должно быть глубоким.
 */
public interface Building {
	public int getFloorsAmount();
	public int getSpacesAmount();
	public double getSpacesArea();
	public int getRoomsAmount();
	public Floor[] getFloorsArray();
	public Floor getFloor(int index);
	public void setFloor(int index, Floor oneFloor);
	public Space getSpace(int index);
	public void setSpace(int index, Space oneSpace);
	public void addSpace(int index, Space oneSpace);
	public void removeSpace(int index);
	public Space getBestSpace();
	public Space[] getSpaceArraySorted();
}
