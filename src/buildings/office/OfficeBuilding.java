package buildings.office;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

import buildings.interfaces.Building;
import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import exceptions.FloorIndexOutOfBoundsException;
import exceptions.SpaceIndexOutOfBoundsException;

/**
 * Создайте класс OfficeBuilding офисного здания.
 * Работа класса должна быть основана на двусвязном циклическом списке этажей с выделенной головой.
 * Номер офиса явно не хранится.
 * Нумерация офисов в здании сквозная и начинается с нуля.
 */
public class OfficeBuilding implements Building, Serializable {	
	private static class Node{
		Node next;
		Node previous;
		OfficeFloor anOfficeFloor;
	}	
	
	private Node head;
		
	private OfficeBuilding() {		
		head = new Node();
		head.next = head;
		head.previous = head;
	}
	
	/**
	* Конструктор может принимать количество этажей и массив количества офисов по этажам.
	*/
	public OfficeBuilding(int size, int[] officesAmountOnFloor) {
		this();
		Node current = head;
		for (int i = 0; i < size; i++) {
			Node x = new Node();
			x.anOfficeFloor = new OfficeFloor(officesAmountOnFloor[i]);
			current.next = x;
			current.next.previous = current;
			current = current.next;
		}
		current.next = head.next;
		head.next.previous = current;
	}
	
	/**
	* Конструктор может принимать массив этажей офисного здания.
	*/
	public OfficeBuilding(OfficeFloor[] officeFloors) {
		this();
		Node current = head;
		for (int i = 0; i < officeFloors.length; i++) {
			Node x = new Node();
			current.next = x;
			x.previous = current;
			x.anOfficeFloor = officeFloors[i];
		}
		current.next = head.next;
		head.next.previous = current;
	}
	
    /**
	* Создайте приватный метод получения узла по его номеру.
	*/
	private Node getNode(int index) {
		Node temp = head;
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		return temp;
	}
	
	/**
	* Создайте приватный метод добавления узла в список по номеру.
	*/
	private void addNode(Node node, int index) {
		Node temp = head;
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}
		temp.next.previous = node;
		node.next = temp.next;
		temp.next = node;
		node.previous = temp;
	}
	
	/**
	* Создайте приватный метод удаления узла из списка по его номеру.
	*/
	public void removeNode(int index) {		
			Node temp = head;		
			for (int i = 0; i < index; i++) {
				temp = temp.next;
			}			
			temp.next = temp.next.next;
			temp.next.previous = temp;
	}	
	
	/**
	* Создайте метод получения общего количества этажей здания.
	*/
	public int getFloorsAmount() {
		int result = 0;
		Node current = head;
		do {
			current = current.next;
			result++;
		} while (current.next != head.next);
		return result;
	}
	
	/**
	* Создайте метод получения общего количества офисов здания.
	*/
	public int getSpacesAmount() {
		int result = 0;
		Node current = head;
		do {
			current = current.next;
			result += current.anOfficeFloor.getSpacesAmount();
		} while (current.next != head.next);
		return result;
	}
	
	/**
	* Создайте метод получения общей площади помещений здания.
	*/
	public double getSpacesArea() {
		double result = 0;
		Node current = head;
		do {
			current = current.next;
			result += current.anOfficeFloor.getSpacesArea();
		} while (current.next != head.next);
		return result;
	}
	
	/**
	* Создайте метод получения общего количества комнат здания.
	*/
	public int getRoomsAmount() {
		int result = 0;
		Node current = head;
		do {
			current = current.next;
			result += current.anOfficeFloor.getRoomsAmount();
		} while (current.next != head.next);
		return result;
	}
	
	/**
	* Создайте метод получения массива этажей офисного здания.
	*/	
	public Floor[] getFloorsArray() {
		OfficeFloor[] officeFloors = new OfficeFloor[getFloorsAmount()];
		Node current = head;
		for (int i = 0; i < getFloorsAmount(); i++) {
			current = current.next;
			officeFloors[i] = current.anOfficeFloor;
		}
		return officeFloors;
	}
	
	/**
	* Создайте метод получения объекта этажа по его номеру в здании.
	*/
	public Floor getFloor(int index) {
		if ((index >= getFloorsAmount())||(index < 0)) {
			throw new FloorIndexOutOfBoundsException();
		}
		return getNode(index).anOfficeFloor;
	}
	
	/**
	* Создайте метод изменения этажа по его номеру в здании и ссылке на обновленный этаж.
	*/
	public void setFloor(int index, Floor newFloor) {
		if ((index >= getFloorsAmount())||(index < 0)) {
			throw new FloorIndexOutOfBoundsException();
		}
		getNode(index).anOfficeFloor = (OfficeFloor) newFloor;
	}
	
	/**
	* Создайте метод получения объекта офиса по его номеру в офисном здании.
	*/
	public Space getSpace(int index) {
		if ((index >= getSpacesAmount())||(index < 0)) {
			throw new SpaceIndexOutOfBoundsException();
		}
		Node current = head;
		int sum = 0;
		for (int i = 0; i < getFloorsAmount(); i++) {
			current = current.next;
			sum += current.anOfficeFloor.getSpacesAmount();
			if (sum >= index) {
				int indexOnFloor = current.anOfficeFloor.getSpacesAmount() - (sum - index);
				return current.anOfficeFloor.getSpace(indexOnFloor);
			}
		}
		return null;
	}
	
	/**
	* Создайте метод изменения объекта офиса по его номеру в доме и ссылке типа офиса.
	*/
	public void setSpace(int index, Space newOffice) {
		if ((index >= getSpacesAmount())||(index < 0)) {
			throw new SpaceIndexOutOfBoundsException();
		}
		Node current = head;
		int sum = 0;
		for (int i = 0; i < getFloorsAmount(); i++) {
			current = current.next;
			sum += current.anOfficeFloor.getSpacesAmount();
			if (sum >= index) {
				int indexOnFloor = current.anOfficeFloor.getSpacesAmount() - (sum - index);
				current.anOfficeFloor.setSpace(indexOnFloor, newOffice);
			}
		}
	}
	
	/**
	* Создайте метод добавления офиса в здание по номеру офиса в здании и ссылке на офис.
	*/
	public void addSpace(int index, Space newOffice) {
		if ((index > getSpacesAmount())||(index < -1)) {
			throw new SpaceIndexOutOfBoundsException();
		}
		Node current = head;
		int sum = 0;
		for (int i = 0; i < getFloorsAmount(); i++) {
			current = current.next;
			sum += current.anOfficeFloor.getSpacesAmount();
			if (sum >= index) {
				int indexOnFloor = current.anOfficeFloor.getSpacesAmount() - (sum - index);
				current.anOfficeFloor.addSpace(indexOnFloor, newOffice);
				}
		}
	}
	
	/**
	* Создайте метод удаления офиса по его номеру в здании.
	*/
	public void removeSpace(int index) {
		if ((index >= getSpacesAmount())||(index < 0)) {
			throw new SpaceIndexOutOfBoundsException();
		}
		Node current = head;
		int sum = 0;
		for (int i = 0; i < getFloorsAmount(); i++) {
			current = current.next;
			sum += current.anOfficeFloor.getSpacesAmount();
			if (sum >= index) {
				int indexOnFloor = current.anOfficeFloor.getSpacesAmount() - (sum - index);
				current.anOfficeFloor.removeSpace(indexOnFloor);
			}
		}
	}
	
	/**
	* Создайте метод getBestSpace() получения самого большого по площади офиса здания.
	*/
	public Space getBestSpace() {			
		if (head.next == head.previous) {
    		return null;
    	}		
		Node current = head.next;
		Space bestOffice = current.anOfficeFloor.getBestSpace();
		for (int i = 1; i < getFloorsAmount(); i++) {
			current = current.next;
			if (bestOffice.getArea() < current.anOfficeFloor.getBestSpace().getArea()) {
				bestOffice = current.anOfficeFloor.getBestSpace();
			}
		}
		return bestOffice;
	}
	
	/**
	* Создайте метод получения отсортированного по убыванию площадей массива офисов.
	*/
	public Office[] getSpaceArraySorted() {
		Office[] nonSortedOffices = new Office[getSpacesAmount()];
		Node current = head;
		int sum = 0;
		for (int i = 1; i < getFloorsAmount(); i++) {
			current = current.next;
			System.arraycopy(current.anOfficeFloor.getSpaceArray(), 0, nonSortedOffices, sum, current.anOfficeFloor.getSpacesAmount());
			sum += current.anOfficeFloor.getSpacesAmount();	    	
		}
		Arrays.sort(nonSortedOffices, new Comparator<Office>() {
			@Override
			public int compare(Office arg0, Office arg1) {
				if (arg0.getArea() == arg1.getArea()) {
					return 0;
				} else if (arg0.getArea() > arg1.getArea()) {
					return 1; 
				} else {
					return -1;
				}				
			}    		
    	});    	
		return nonSortedOffices;
	}
	
	/**
     * Добавьте в классы зданий Dwelling, OfficeBuilding реализации метода String toString(). 
	 * Методы выводят текущее количество этажей и соответствующую информацию о каждом помещении каждого этажа, используя toString() уровня этажа и помещения. 
	 * Например, Dwelling (2, DwellingFloor (3, Flat (...), ...), DwellingFloor (3, Flat (...), ...)
     */
    @Override
	public String toString() {
    	StringBuilder s = new StringBuilder();
    	OfficeFloor[] floors = (OfficeFloor[]) getFloorsArray();
    	s.append("OfficeBuilding (").append(getFloorsAmount()).append(", ");
    	for (int i = 0; i < floors.length; i++) {
    		if (i > 0 ) s.append(", ");
    		s.append(floors[i].toString());
    	}
    	s.append(")");    	
    	return s.toString();
    }

	/**
	 * Добавьте в классы зданий реализации методов boolean equals(Object object). Метод должен возвращать true только в том случае, если объект, на который передана ссылка, является зданием соответствующего типа, количество этажей совпадает и сами этажи эквивалентны помещениям текущего объекта. 
	 */
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((head == null) ? 0 : head.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof OfficeBuilding))
			return false;
		OfficeBuilding other = (OfficeBuilding) obj;
		if (head == null) {
			if (other.head != null)
				return false;
		} else if (!head.equals(other.head))
			return false;
		return true;
	}
    
    
}
