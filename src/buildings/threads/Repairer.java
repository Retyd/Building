/**
 * В пакете buildings.threads создайте два класса нитей 
 * (наследуют от класса Thread) Repairer и Cleaner, 
 * взаимодействующих с помощью промежуточного объекта типа Floor.
 * Первая нить последовательно считывает значения площадей 
 * помещений этажа. Каждый раз, когда она читает значение 
 * площади помещения, она выводит в консоль сообщение вида 
 * «Repairing space number <index> with total area <area> square meters». 
 * По достижении конца этажа, 
 * а также в случае прерывания  нить заканчивает свое выполнение.
 */


package buildings.threads;

import buildings.Floor;
import buildings.Space;

public class Repairer extends Thread {
	protected Floor floor;
	
	public Repairer(Floor floor) {
		this.floor = floor;
	}
	
	@Override
	public void run() {
		int spacesAmount = floor.getSpacesAmount();
		Space[] spaces = floor.getSpaceArray();
		for(int i = 0; i < spacesAmount; i++) {			
			System.out.println("Repairing space number " + i + " with total area " + spaces[i].getArea() + " square meters");
		}
	}
}