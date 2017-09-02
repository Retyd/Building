/**
 * Создайте класс OfficeFloor этажа офисного здания.
 * Работа класса должна быть основана на односвязном циклическом списке офисов с выделенной головой.
 * Номер офиса явно не хранится.
 */
public class OfficeFloor {
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
	public int getOfficesOnFloorAmount() {	
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
	public double getFloorArea() {
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
	public int getRoomsOnFloorAmount() {
		int sumRooms = 0;
		Node current = head;
		do {
			current = current.next;
			sumRooms += current.anOffice.getRoomsAmount();
		} while(current.next != head.next); 
		return sumRooms;
	}
	
	/**
	* Создайте метод получения массива офисов этажа.
    */
	public Office[] getOfficesOnFloorArray() {
		Office[] offices = new Office[getOfficesOnFloorAmount()];
		Node current = head;
		for (int i = 0; i < getOfficesOnFloorAmount(); i++) {
			current = current.next;
			offices[i] = current.anOffice;			
		}	
		return offices;
	}
	
	/**
	* Создайте метод получения офиса по его номеру на этаже.
    */	
	public Office getOfficeFromFloor(int index) {
		return getNode(index).anOffice;
	}
	
	/**
	* Создайте метод изменения офиса по его номеру на этаже и ссылке на обновленный офис.
    */
	public void setOfficeOnFloor(int index, Office newOffice) {
		getNode(index).anOffice = newOffice;
	}
	
	/**
	* Создайте метод добавления нового офиса на этаже по будущему номеру офиса.
    */
	public void addOfficeOnFloor(int index) {
		Node newOffice = new Node();
		addNode(newOffice, index);
	}
	
	/**
	* Создайте метод удаления офиса по его номеру на этаже.
    */
	public void removeOfficeOnFloor(int index) {
		removeNode(index);
	}
	
	/**
	* Создайте метод getBestSpace() получения самого большого по площади офиса этажа.
	*/
	public Office getBestSpace() {
		float bestSpace = 0;
		Office officeBestSpace = null;
		Node current = head;
		for (int i = 0; i <= getRoomsOnFloorAmount(); i++) {
			current = current.next;
			if(current.anOffice.getArea() > bestSpace) {
				bestSpace = current.anOffice.getArea();
				officeBestSpace = current.anOffice;
			}
		}
		return officeBestSpace;
	}
}
