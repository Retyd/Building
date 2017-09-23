/**
 * Добавьте в класс Buildings со статическими методами обработки 
 * реализацию метода Floor synchronizedFloor (Floor floor), 
 * возвращающего ссылку на оболочку указанного объекта этажа, 
 * безопасную с точки зрения многопоточности.
 * Для этого потребуется в пакете buildings описать новый класс декоратора 
 * SynchronizedFloor, реализующий с обеспечением синхронизации 
 * методы интерфейса Floor, а также перегружающий ряд методов класса Object. 
 * Создание специальных итераторов и их синхронизация не требуются.
 */

package buildings;

public class SynchronizedFloor implements Floor {
	protected Floor floor;
	
	public SynchronizedFloor(Floor floor) {
		this.floor = floor;
	}
	
	@Override
	public synchronized int getSpacesAmount() {
		return floor.getSpacesAmount();
	}
	
	@Override
	public synchronized double getSpacesArea() {
		return floor.getSpacesArea();
	}
	
	@Override
	public synchronized int getRoomsAmount() {
		return floor.getRoomsAmount();
	}
	
	@Override
	public synchronized Space[] getSpaceArray() {
		return floor.getSpaceArray();
	}
	
	@Override
	public synchronized Space getSpace(int index) {
		return floor.getBestSpace();
	}
	
	@Override
	public synchronized void setSpace(int index, Space space) {
		floor.setSpace(index, space);
	}
	
	@Override
	public synchronized void addSpace(int index, Space space) {
		floor.addSpace(index, space);
	}
	
	@Override
	public synchronized void removeSpace(int index) {
		floor.removeSpace(index);
	}
	
	@Override
	public synchronized Space getBestSpace() {
		return floor.getBestSpace();
	}
	
	@Override
	public synchronized String toString() {
		return floor.toString();
	}
	
	@Override
	public synchronized int hashCode() {
		return floor.hashCode();
	}
	
	@Override
	public synchronized boolean equals(Object obj) {
		return floor.equals(obj);
	}
}
