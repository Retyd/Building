package buildings.office;
import buildings.Floor;
import buildings.Space;
import exceptions.SpaceIndexOutOfBoundsException;

/**
 * Создайте класс OfficeFloor этажа офисного здания.
 * Работа класса должна быть основана на односвязном циклическом списке офисов с выделенной головой.
 * Номер офиса явно не хранится.
 */
public class OfficeFloor implements Floor {
	private static class Node{
		Node next;
		Office anOffice;
	}	
	
	private Node head;
		
	private OfficeFloor() {
		head = new Node();
		head.next = head;
	}
	
	/**
	* Конструктор может принимать количество офисов на этаже.
    */ 
	public OfficeFloor(int officeAmount) {
		this();
		Node current = head;		
		for (int i = 0; i < officeAmount; i++) {			
			Node x = new Node();
			x.anOffice = new Office();
			current.next = x;
			current = x;
		}		
		current.next = head.next;
	}	
	
	/**
	* Конструктор может принимать массив офисов этажа.
    */
	public OfficeFloor(Office[] offices) {
		this();
		Node current = head;
		for(int i = 0; i < offices.length; i++) {
			Node x = new Node();
			x.anOffice = offices[i];
			current.next = x;			
			current = x;
		}
		current.next = head.next;
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
		node.next = temp.next;		
		temp.next = node;
	}
	
	/**
	* Создайте приватный метод удаления узла из списка по его номеру.
    */
	private void removeNode(int index) {
		Node temp = head;		
		for (int i = 0; i < index; i++) {
			temp = temp.next;
		}		
		temp.next = temp.next.next;
	}	
		
	/**
	* Создайте метод получения количества офисов на этаже.
    */
	public int getSpacesAmount() {	
		Node current = head; 
		int result = 0;		
			do {
				current = current.next;
				result++;
			} while(current.next != head.next); 
		return result;
	}

	
	/**
	* Создайте метод получения общей площади помещений этажа.
    */
	public double getSpacesArea() {
		double result = 0;
		Node current = head;
		do {
			current = current.next;
			result += current.anOffice.getArea();
		} while(current.next != head.next); 
		return result;
	}
	
	/**
	* Создайте метод получения общего количества комнат этажа.
    */
	public int getRoomsAmount() {
		int result = 0;
		Node current = head;
		do {
			current = current.next;
			result += current.anOffice.getRoomsAmount();
		} while(current.next != head.next); 
		return result;
	}
	
	/**
	* Создайте метод получения массива офисов этажа.
    */
	public Space[] getSpaceArray() {
		Office[] offices = new Office[getSpacesAmount()];
		Node current = head;
		for (int i = 0; i < getSpacesAmount(); i++) {
			current = current.next;
			offices[i] = (Office) current.anOffice;			
		}	
		return offices;
	}
	
	/**
	* Создайте метод получения офиса по его номеру на этаже.
    */	
	public Space getSpace(int index) {
		if ((index >= getSpacesAmount())||(index < 0)) {
			throw new SpaceIndexOutOfBoundsException();
		}
		return getNode(index).anOffice;
	}
	
	/**
	* Создайте метод изменения офиса по его номеру на этаже и ссылке на обновленный офис.
    */
	public void setSpace(int index, Space newOffice) {
		if ((index >= getSpacesAmount())||(index < 0)) {
			throw new SpaceIndexOutOfBoundsException();
		}
		getNode(index).anOffice = (Office) newOffice;
	}
	
	/**
	* Создайте метод добавления нового офиса на этаже по будущему номеру офиса.
    */
	public void addSpace(int index, Space anOffice) {
		if ((index >= getSpacesAmount())||(index < 0)) {
			throw new SpaceIndexOutOfBoundsException();
		}
		Node newOffice = new Node();
		newOffice.anOffice = (Office) anOffice;
		addNode(newOffice, index);
	}
	
	/**
	* Создайте метод удаления офиса по его номеру на этаже.
    */
	public void removeSpace(int index) {
		if ((index >= getSpacesAmount())||(index < 0)) {
			throw new SpaceIndexOutOfBoundsException();
		}
		removeNode(index);
	}
	
	/**
	* Создайте метод getBestSpace() получения самого большого по площади офиса этажа.
	*/
	public Space getBestSpace() {
		double bestSpace = 0;
		Office officeBestSpace = null;
		Node current = head;
		for (int i = 0; i <= getRoomsAmount(); i++) {
			current = current.next;
			if(current.anOffice.getArea() > bestSpace) {
				bestSpace = current.anOffice.getArea();
				officeBestSpace = current.anOffice;
			}
		}
		return officeBestSpace;
	}
}
