package buildings.interfaces;

/**
 * Создайте интерфейс Space помещения здания.
 * Интерфейс должен соответствовать общей функциональности Flat и Office и содержать следующие методы:
 * получения  количества комнат,
 * изменения количества комнат,
 * получения площади,
 * изменения площади.
 * Классы, соответственно, должны реализовывать интерфейс (при необходимости измените классы).
 * Рекомендуется использовать возможности рефакторинга среды разработки.
 */
public interface Space {
	public int getRoomsAmount();
	public double getArea();
	public void setRoomsAmount(int roomsAmount);
	public void setArea(double area);
}
