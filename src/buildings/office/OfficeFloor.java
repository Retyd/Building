package buildings.office;
import java.io.Serializable;

import buildings.interfaces.Floor;
import buildings.interfaces.Space;
import exceptions.SpaceIndexOutOfBoundsException;

/**
 * Создайте класс OfficeFloor этажа офисного здания.
 * Работа класса должна быть основана на односвязном циклическом списке офисов с выделенной головой.
 * Номер офиса явно не хранится.
 */
public class OfficeFloor implements Floor, Serializable, Cloneable {
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
	
    /**
     * Добавьте в классы этажей DwellingFloor, OfficeFloor реализации метода String toString(). 
	 * Методы выводят тип этажа, текущее количество помещений этажа и соответствующую информацию по каждому помещению, используя метод toString() помещения. 
	 * Например, DwellingFloor (3, Flat (3, 55.0), Flat (2, 48.0), Flat (1, 37.0))
     */
    @Override
	public String toString() {
    	StringBuilder s = new StringBuilder();
    	Space[] offices = getSpaceArray();
    	s.append("OfficeFloor (").append(getRoomsAmount()).append(", ");
    	for(int i = 0; i < offices.length; i++) {
    		if (i > 0 ) s.append(", ");
    		s.append(offices[i].toString());
    	}
    	s.append(")");    	
    	return s.toString();
	}

    /**
	 * Добавьте в классы этажей реализации методов boolean equals(Object object). Метод должен возвращать true только в том случае, если объект, на который передана ссылка, является этажом соответствующего типа, количество помещений совпадает и сами помещения эквивалентны помещениям текущего объекта. Для экземпляров класса HotelFloor также должно проверяться совпадение количества звёзд.
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
		if (!(obj instanceof OfficeFloor))
			return false;
		OfficeFloor other = (OfficeFloor) obj;
		if (head == null) {
			if (other.head != null)
				return false;
		} else if (!head.equals(other.head))
			return false;
		return true;
	}  
    
	/**
	 * Добавьте в интерфейс и классы этажей метод Object clone(). 
	 * Клонирование должно быть глубоким.
	 */
	public Object clone() {
		Object result = null;
		try {
			result = super.clone();
			
		} catch (CloneNotSupportedException e) {
			throw new InternalError();
		}
		return result;
	}
}
