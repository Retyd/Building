import java.util.Arrays;
import java.util.Comparator;

import exceptions.SpaceIndexOutOfBoundsException;

/**
 * Создайте класс OfficeBuilding офисного здания.
 * Работа класса должна быть основана на двусвязном циклическом списке этажей с выделенной головой.
 * Номер офиса явно не хранится.
 * Нумерация офисов в здании сквозная и начинается с нуля.
 */
public class OfficeBuilding {	
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
	public OfficeBuilding(int index, int[] officesAmountOnFloor) {
		this();
		Node current = head;
		for (int i = 0; i < index; i++) {
			Node x = new Node();
			current.next = x;
			x.previous = current;
			x.anOfficeFloor = new OfficeFloor(officesAmountOnFloor[i]);
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
	public int getOfficesFloorsAmount() {
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
	public int getOfficesGeneralAmount() {
		int result = 0;
		Node current = head;
		do {
			current = current.next;
			result += current.anOfficeFloor.getOfficesOnFloorAmount();
		} while (current.next != head.next);
		return result;
	}
	
	/**
	* Создайте метод получения общей площади помещений здания.
	*/
	public double getOfficesGeneralArea() {
		double result = 0;
		Node current = head;
		do {
			current = current.next;
			result += current.anOfficeFloor.getFloorArea();
		} while (current.next != head.next);
		return result;
	}
	
	/**
	* Создайте метод получения общего количества комнат здания.
	*/
	public int getRoomsGeneralAmount() {
		int result = 0;
		Node current = head;
		do {
			current = current.next;
			result += current.anOfficeFloor.getRoomsOnFloorAmount();
		} while (current.next != head.next);
		return result;
	}
	
	/**
	* Создайте метод получения массива этажей офисного здания.
	*/	
	public OfficeFloor[] getOfficeFloors() {
		OfficeFloor[] officeFloors = new OfficeFloor[getOfficesFloorsAmount()];
		Node current = head;
		for (int i = 0; i < getOfficesFloorsAmount(); i++) {
			current = current.next;
			officeFloors[i] = current.anOfficeFloor;
		}
		return officeFloors;
	}
	
	/**
	* Создайте метод получения объекта этажа по его номеру в здании.
	*/
	public OfficeFloor getOfficeFloor(int index) {
		if ((index >= getOfficesFloorsAmount())||(index < 0)) {
			throw new SpaceIndexOutOfBoundsException();
		}
		return getNode(index).anOfficeFloor;
	}
	
	/**
	* Создайте метод изменения этажа по его номеру в здании и ссылке на обновленный этаж.
	*/
	public void setOfficeFloor(int index, OfficeFloor newOfficeFloor) {
		if ((index >= getOfficesFloorsAmount())||(index < 0)) {
			throw new SpaceIndexOutOfBoundsException();
		}
		getNode(index).anOfficeFloor = newOfficeFloor;
	}
	
	/**
	* Создайте метод получения объекта офиса по его номеру в офисном здании.
	*/
	public Office getOfficeFromBuilding(int index) {
		if ((index >= getOfficesGeneralAmount())||(index < 0)) {
			throw new SpaceIndexOutOfBoundsException();
		}
		Node current = head;
		int sum = 0;
		for (int i = 0; i < getOfficesFloorsAmount(); i++) {
			current = current.next;
			sum += current.anOfficeFloor.getOfficesOnFloorAmount();
			if (sum >= index) {
				int indexOnFloor = current.anOfficeFloor.getOfficesOnFloorAmount() - (sum - index);
				return current.anOfficeFloor.getOfficeFromFloor(indexOnFloor);
			}
		}
		return null;
	}
	
	/**
	* Создайте метод изменения объекта офиса по его номеру в доме и ссылке типа офиса.
	*/
	public void setOfficeInBuildind(int index, Office newOffice) {
		if ((index >= getOfficesGeneralAmount())||(index < 0)) {
			throw new SpaceIndexOutOfBoundsException();
		}
		Node current = head;
		int sum = 0;
		for (int i = 0; i < getOfficesFloorsAmount(); i++) {
			current = current.next;
			sum += current.anOfficeFloor.getOfficesOnFloorAmount();
			if (sum >= index) {
				int indexOnFloor = current.anOfficeFloor.getOfficesOnFloorAmount() - (sum - index);
				current.anOfficeFloor.setOfficeOnFloor(indexOnFloor, newOffice);
			}
		}
	}
	
	/**
	* Создайте метод добавления офиса в здание по номеру офиса в здании и ссылке на офис.
	*/
	public void addOfficeInBuildind(int index, Office newOffice) {
		if ((index >= getOfficesGeneralAmount())||(index < 0)) {
			throw new SpaceIndexOutOfBoundsException();
		}
		Node current = head;
		int sum = 0;
		for (int i = 0; i < getOfficesFloorsAmount(); i++) {
			current = current.next;
			sum += current.anOfficeFloor.getOfficesOnFloorAmount();
			if (sum >= index) {
				int indexOnFloor = current.anOfficeFloor.getOfficesOnFloorAmount() - (sum - index);
				current.anOfficeFloor.addOfficeOnFloor(indexOnFloor);
				current.anOfficeFloor.setOfficeOnFloor(indexOnFloor, newOffice);
				}
		}
	}
	
	/**
	* Создайте метод удаления офиса по его номеру в здании.
	*/
	public void removeOfficeInBuildind(int index) {
		if ((index >= getOfficesGeneralAmount())||(index < 0)) {
			throw new SpaceIndexOutOfBoundsException();
		}
		Node current = head;
		int sum = 0;
		for (int i = 0; i < getOfficesFloorsAmount(); i++) {
			current = current.next;
			sum += current.anOfficeFloor.getOfficesOnFloorAmount();
			if (sum >= index) {
				int indexOnFloor = current.anOfficeFloor.getOfficesOnFloorAmount() - (sum - index);
				current.anOfficeFloor.removeOfficeOnFloor(indexOnFloor);
			}
		}
	}
	
	/**
	* Создайте метод getBestSpace() получения самого большого по площади офиса здания.
	*/
	public Office getBestSpace() {			
		if (head.next == head.previous) {
    		return null;
    	}		
		Node current = head.next;
		Office bestOffice = current.anOfficeFloor.getBestSpace();
		for (int i = 1; i < getOfficesFloorsAmount(); i++) {
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
	public Office[] getSortedOffices () {
		Office[] nonSortedOffices = new Office[getOfficesGeneralAmount()];
		Node current = head;
		int sum = 0;
		for (int i = 1; i < getOfficesFloorsAmount(); i++) {
			current = current.next;
			System.arraycopy(current.anOfficeFloor.getOfficesOnFloorArray(), 0, nonSortedOffices, sum, current.anOfficeFloor.getOfficesOnFloorAmount());
			sum += current.anOfficeFloor.getOfficesOnFloorAmount();	    	
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
}
